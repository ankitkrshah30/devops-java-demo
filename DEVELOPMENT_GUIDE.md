# Java DevOps - Development Guide

## Architecture Overview

The Java DevOps application follows the **Model-View-Controller (MVC)** pattern with Spring Boot.

```
┌─────────────────────────────────────────────────────────┐
│                      Client Browser                      │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│              Thymeleaf Templates (View)                  │
│   - index.html (Home/Dashboard)                          │
│   - tutorials.html (List)                                │
│   - tutorial_form.html (Create/Edit)                     │
│   - fragments/* (Reusable components)                    │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│              Controllers (HTTP Handlers)                 │
│   - HomeController → Dashboard logic                     │
│   - TutorialController → CRUD operations                 │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│              Services (Business Logic)                   │
│   - Optional: Service layer for reusable logic           │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│         Repositories (Data Access Layer)                 │
│   - TutorialRepository → Database queries                │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│            Entities (Data Models)                        │
│   - Tutorial → JPA entity mapping to DB table            │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│         H2 Database (Data Persistence)                   │
│   - File-based or In-memory storage                      │
└─────────────────────────────────────────────────────────┘
```

## Project Configuration

### Key Files

1. **pom.xml** - Maven configuration with dependencies
2. **application.properties** - Spring Boot settings
3. **JavaDevopsApplication.java** - Application entry point

### Dependency Structure

```
Spring Boot 2.7.5
├── spring-boot-starter-web (Web MVC)
├── spring-boot-starter-data-jpa (ORM)
├── spring-boot-starter-thymeleaf (Template Engine)
├── h2 (Embedded Database)
├── bootstrap 4.6.2 (Frontend Framework)
├── jquery 3.6.1 (DOM manipulation)
├── font-awesome 6.2.0 (Icons)
└── spring-boot-devtools (Development tools)
```

## Code Structure

### Entity Layer (com.javadevops.tutorials.entity)

**Tutorial.java** - Represents a tutorial in the database

```java
@Entity
@Table(name = "tutorials")
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;          // Unique identifier
    
    @Column(length = 128, nullable = false)
    private String title;        // Tutorial title
    
    @Column(length = 256)
    private String description;  // Tutorial description
    
    @Column(nullable = false)
    private int level;           // Difficulty level
    
    @Column
    private boolean published;   // Publication status
}
```

### Repository Layer (com.javadevops.tutorials.repository)

**TutorialRepository.java** - Data access interface

```java
@Repository
@Transactional
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {
    // Custom query methods
    List<Tutorial> findByTitleContainingIgnoreCase(String keyword);
    long countByPublished(boolean published);
    
    @Query("UPDATE Tutorial t SET t.published = :published WHERE t.id = :id")
    @Modifying
    void updatePublishedStatus(Integer id, boolean published);
}
```

### Controller Layer (com.javadevops.tutorials.controller)

**HomeController.java** - Handles home page and dashboard

**TutorialController.java** - Handles CRUD operations

## How to Add New Features

### Feature 1: Add Tutorial Categories

#### Step 1: Create Category Entity

```java
// src/main/java/com/javadevops/tutorials/entity/Category.java
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    // Getters/Setters
}
```

#### Step 2: Update Tutorial Entity

```java
@Entity
public class Tutorial {
    // ... existing fields ...
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    // Getters/Setters
}
```

#### Step 3: Create Category Repository

```java
// src/main/java/com/javadevops/tutorials/repository/CategoryRepository.java
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
```

#### Step 4: Update TutorialController

```java
@Autowired
private CategoryRepository categoryRepository;

@GetMapping("/tutorials/new")
public String addTutorial(Model model) {
    Tutorial tutorial = new Tutorial();
    tutorial.setPublished(true);
    
    model.addAttribute("tutorial", tutorial);
    model.addAttribute("categories", categoryRepository.findAll());
    model.addAttribute("pageTitle", "Create new Tutorial");
    
    return "tutorial_form";
}
```

#### Step 5: Update Templates

Update `tutorial_form.html` to include category dropdown:

```html
<div class="form-group row">
    <label class="col-sm-3 col-form-label" for="category">Category</label>
    <div class="col-sm-9">
        <select th:field="*{category}" class="form-control" id="category">
            <option value="">-- Select Category --</option>
            <option th:each="cat : ${categories}" 
                    th:value="${cat.id}" 
                    th:text="${cat.name}"></option>
        </select>
    </div>
</div>
```

---

### Feature 2: Add Tutorial Tags/Labels

#### Step 1: Create Tag Entity with Many-to-Many Relationship

```java
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @ManyToMany(mappedBy = "tags")
    private Set<Tutorial> tutorials = new HashSet<>();
}

// Update Tutorial entity
@Entity
public class Tutorial {
    // ... existing fields ...
    
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "tutorial_tags",
        joinColumns = @JoinColumn(name = "tutorial_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
}
```

#### Step 2: Create Tag Repository

```java
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);
    List<Tag> findByNameContainingIgnoreCase(String keyword);
}
```

---

### Feature 3: Add Tutorial Ratings

#### Step 1: Create Rating Entity

```java
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "tutorial_id", nullable = false)
    private Tutorial tutorial;
    
    @Column(nullable = false)
    private int score;  // 1-5
    
    private String comment;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
```

#### Step 2: Create Rating Repository

```java
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByTutorialId(Integer tutorialId);
    
    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.tutorial.id = :tutorialId")
    Double getAverageRating(@Param("tutorialId") Integer tutorialId);
}
```

#### Step 3: Add Endpoint to TutorialController

```java
@PostMapping("/tutorials/{id}/rate")
public String rateTutorial(@PathVariable Integer id, 
                          @RequestParam int score,
                          @RequestParam(required = false) String comment,
                          RedirectAttributes redirectAttributes) {
    try {
        Rating rating = new Rating();
        rating.setTutorial(tutorialRepository.findById(id).get());
        rating.setScore(Math.min(Math.max(score, 1), 5)); // Ensure 1-5
        rating.setComment(comment);
        rating.setCreatedAt(new Date());
        ratingRepository.save(rating);
        
        redirectAttributes.addFlashAttribute("message", "Thanks for rating!");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("message", e.getMessage());
    }
    return "redirect:/tutorials/" + id;
}
```

---

### Feature 4: Add User Authentication

#### Step 1: Add Spring Security Dependency

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

#### Step 2: Create User Entity

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private boolean enabled;
}
```

#### Step 3: Create Security Configuration

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/webjars/**", "/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
}
```

---

### Feature 5: Add Tutorial Search with Pagination

#### Step 1: Update Repository

```java
@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer>, 
                                           JpaSpecificationExecutor<Tutorial> {
    Page<Tutorial> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
        String title, String description, Pageable pageable);
}
```

#### Step 2: Update Controller

```java
@GetMapping("/tutorials")
public String getAll(Model model, 
                     @Param("keyword") String keyword,
                     @PageableDefault(size = 10) Pageable pageable) {
    try {
        Page<Tutorial> page;
        
        if (keyword == null || keyword.isEmpty()) {
            page = tutorialRepository.findAll(pageable);
        } else {
            page = tutorialRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    keyword, keyword, pageable);
            model.addAttribute("keyword", keyword);
        }
        
        model.addAttribute("tutorials", page.getContent());
        model.addAttribute("page", page);
    } catch (Exception e) {
        model.addAttribute("message", e.getMessage());
    }
    
    return "tutorials";
}
```

#### Step 3: Update Template (tutorials.html)

```html
<!-- Add pagination controls -->
<nav>
  <ul class="pagination">
    <li th:class="${page.first} ? 'page-item disabled' : 'page-item'">
      <a class="page-link" th:href="@{/tutorials(page=0, keyword=${keyword})}">First</a>
    </li>
    <li th:class="${page.last} ? 'page-item disabled' : 'page-item'">
      <a class="page-link" 
         th:href="@{/tutorials(page=${page.number + 1}, keyword=${keyword})}">Next</a>
    </li>
  </ul>
</nav>
```

---

## Testing

### Unit Testing Repository

```java
@DataJpaTest
public class TutorialRepositoryTest {
    
    @Autowired
    private TutorialRepository tutorialRepository;
    
    @Test
    public void testFindByTitleContainingIgnoreCase() {
        Tutorial tutorial = new Tutorial("Docker", "Docker basics", 1, true);
        tutorialRepository.save(tutorial);
        
        List<Tutorial> results = tutorialRepository
            .findByTitleContainingIgnoreCase("docker");
        
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getTitle()).contains("Docker");
    }
}
```

### Controller Testing

```java
@WebMvcTest(TutorialController.class)
public class TutorialControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private TutorialRepository tutorialRepository;
    
    @Test
    public void testGetAllTutorials() throws Exception {
        mockMvc.perform(get("/tutorials"))
            .andExpect(status().isOk())
            .andExpect(view().name("tutorials"));
    }
}
```

---

## Best Practices

1. **Always use transactions** - Use `@Transactional` for data modifications
2. **Validate input** - Use Spring Validation annotations
3. **Use DTOs** - Create separate objects for API responses
4. **Handle exceptions gracefully** - Use `@ExceptionHandler`
5. **Log appropriately** - Use SLF4J for logging
6. **Keep controllers thin** - Move business logic to services
7. **Write tests** - Unit and integration tests for reliability
8. **Use pagination** - For large datasets
9. **Cache results** - Use `@Cacheable` for frequently accessed data
10. **Secure endpoints** - Use Spring Security for access control

---

## Performance Optimization Tips

1. **Use @Query(nativeQuery = true)** - For complex queries
2. **Add database indexes** - On frequently searched columns
3. **Use pagination** - Never load all data at once
4. **Cache static data** - Category, Tag lists
5. **Lazy load relationships** - Use `FetchType.LAZY`
6. **Monitor query N+1 problems** - Use EntityGraph or join fetching

---

## Deployment

### Build Production WAR

```bash
mvn clean package -Pproduction
```

### Run with Different Profiles

```bash
java -jar app.jar --spring.profiles.active=production
```

### Docker Deployment

See `Dockerfile` for containerization setup.

---

## Common Issues & Solutions

### Issue: Lazy initialization error

**Solution:** Use `@Transactional` or eager loading

```java
@Query("SELECT DISTINCT t FROM Tutorial t LEFT JOIN FETCH t.tags WHERE t.id = :id")
Optional<Tutorial> findByIdWithTags(@Param("id") Integer id);
```

### Issue: Duplicates in results with JOINs

**Solution:** Use `DISTINCT` or `Set` instead of `List`

### Issue: Changes not persisting

**Solution:** Ensure method is `@Transactional` and not read-only

---

## Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Thymeleaf Documentation](https://www.thymeleaf.org/)
- [Bootstrap Documentation](https://getbootstrap.com/docs)
- [Hibernate ORM](https://hibernate.org/)

---

**Created:** May 15, 2026  
**Version:** v1.0.0

