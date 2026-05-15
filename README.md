# Java DevOps Tutorial Management System

A professional Spring Boot Thymeleaf CRUD application for managing DevOps and Java tutorials with Maven that uses Spring Data JPA to interact with H2/MySQL/PostgreSQL database.

## Overview

Java DevOps is a web-based tutorial management system designed for DevOps engineers and Java developers to create, manage, and organize their learning resources. It provides a clean, intuitive interface built with Bootstrap for managing tutorials with full CRUD operations.

## Key Features

✅ **Complete CRUD Operations** - Create, Read, Update, Delete tutorials  
✅ **Search & Filter** - Find tutorials quickly by keyword  
✅ **Publish/Unpublish** - Control which tutorials are visible  
✅ **Dashboard** - View statistics and tutorial metrics  
✅ **Responsive Design** - Works on desktop, tablet, and mobile devices  
✅ **Professional UI** - Built with Bootstrap 4.6.2 and Font Awesome icons  
✅ **Database Flexibility** - H2 (default), MySQL, or PostgreSQL support  
✅ **RESTful Architecture** - Clean separation of concerns with MVC pattern

## Technology Stack

| Component | Technology |
|-----------|-----------|
| **Framework** | Spring Boot 2.7.5 |
| **Template Engine** | Thymeleaf |
| **ORM** | Spring Data JPA with Hibernate |
| **Database** | H2 (In-Memory, default) |
| **Frontend** | Bootstrap 4.6.2 |
| **Icons** | Font Awesome 6.2.0 |
| **Build Tool** | Maven |
| **Java Version** | 1.8+ |

## Project Structure

```
src/
├── main/
│   ├── java/com/javadevops/tutorials/
│   │   ├── controller/
│   │   │   ├── HomeController.java      # Dashboard and home page
│   │   │   └── TutorialController.java  # Tutorial CRUD operations
│   │   ├── entity/
│   │   │   └── Tutorial.java            # Tutorial data model
│   │   ├── repository/
│   │   │   └── TutorialRepository.java  # Data access layer
│   │   └── JavaDevopsApplication.java   # Spring Boot main application
│   └── resources/
│       ├── templates/
│       │   ├── index.html               # Home page with dashboard
│       │   ├── tutorials.html           # Tutorials listing page
│       │   ├── tutorial_form.html       # Create/Edit tutorial page
│       │   └── fragments/
│       │       ├── header.html          # Navigation bar
│       │       └── footer.html          # Footer component
│       ├── static/css/
│       │   └── style.css                # Custom CSS styling
│       └── application.properties       # Application configuration
└── test/
    └── java/com/javadevops/tutorials/
        └── JavaDevopsApplicationTests.java
```

## Installation & Setup

### Prerequisites

- Java 8 or higher installed
- Maven 3.6+ installed
- Git (optional)

### Step 1: Clone or Download the Project

```bash
# Using Git
git clone <repository-url>
cd spring-boot-thymeleaf-example

# Or download and extract the ZIP file
```

### Step 2: Build the Project

```bash
# Using Maven
mvn clean install

# On Windows (using included wrapper)
mvnw.cmd clean install

# On Linux/Mac (using included wrapper)
./mvnw clean install
```

### Step 3: Run the Application

```bash
# Using Maven
mvn spring-boot:run

# Using Java directly (after building)
java -jar target/java-devops-tutorials-0.0.1-SNAPSHOT.jar

# Using the wrapper
mvnw.cmd spring-boot:run
```

### Step 4: Access the Application

Open your browser and navigate to:

```
http://localhost:8080
```

## API Endpoints

### Home & Dashboard
- `GET /` - Home page with dashboard statistics

### Tutorials Management
- `GET /tutorials` - List all tutorials with search capability
- `GET /tutorials/new` - Create new tutorial form
- `POST /tutorials/save` - Save tutorial (create/update)
- `GET /tutorials/{id}` - Edit tutorial form
- `GET /tutorials/delete/{id}` - Delete tutorial
- `GET /tutorials/{id}/published/{status}` - Toggle publish status

### Database Management
- `GET /h2-ui` - H2 Database Console (if enabled)

## Database Configuration

### Default Configuration (H2 - In-Memory)

The application comes pre-configured with H2 embedded database, perfect for development and testing.

```properties
# H2 Configuration (default)
spring.datasource.url=jdbc:h2:file:./testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

### Switching to MySQL

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/javadevops
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

Add MySQL dependency to `pom.xml`:
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

### Switching to PostgreSQL

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/javadevops
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL10Dialect
```

Add PostgreSQL dependency to `pom.xml`:
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.6.0</version>
</dependency>
```

## Tutorial Entity

Each tutorial contains the following fields:

```java
- id (Integer) - Unique identifier
- title (String, 2-128 chars) - Tutorial title
- description (String, 2-256 chars) - Tutorial description
- level (Integer) - Difficulty level
- published (Boolean) - Publication status
```

## Features Explanation

### 1. Home Dashboard
- Displays total, published, and draft tutorials
- Quick links to tutorials and create new tutorial
- Feature overview cards

### 2. Tutorial Management
- Create new tutorials with title, description, and level
- Edit existing tutorials
- Delete tutorials with confirmation
- Search tutorials by keyword
- Toggle publish/unpublish status

### 3. Search & Filter
- Real-time search by tutorial title
- Clear search functionality
- Case-insensitive search

### 4. Responsive Design
- Mobile-friendly Bootstrap layout
- Works seamlessly on all screen sizes
- Touch-friendly navigation

## Development Tips

### Run in Development Mode with Hot Reload

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.devtools.livereload.enabled=true"
```

### Access H2 Console

H2 console is enabled by default:
- URL: `http://localhost:8080/h2-ui`
- JDBC URL: `jdbc:h2:file:./testdb`
- Username: `sa`
- Password: (leave empty)

### View SQL Logs

SQL logging is enabled in `application.properties`:
```properties
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG
```

## Future Enhancements

📝 **Planned Features:**
- User authentication and authorization
- Tutorial categories/tags
- Tutorial ratings and comments
- Markdown support for descriptions
- Tutorial images/thumbnails
- Export/Import functionality
- Advanced filtering and sorting
- Audit logging
- REST API endpoints
- Docker containerization

## Troubleshooting

### Issue: Port 8080 already in use

```bash
# Change port in application.properties
server.port=8081
```

### Issue: H2 database file permission error

Delete the `testdb.mv.db` file in the project root and restart:
```bash
rm testdb.mv.db
mvn spring-boot:run
```

### Issue: Thymeleaf template not found

Ensure templates are in: `src/main/resources/templates/`

## Contributing

Contributions are welcome! Please feel free to:
- Report bugs
- Suggest features
- Submit pull requests
- Improve documentation

## License

This project is open-source and available under the MIT License.

## Support

For questions or issues:
- Check the troubleshooting section
- Review the source code comments
- Open an issue in the repository

## Version History

**v0.0.1** (Current)
- Initial release
- Basic CRUD operations
- Dashboard with statistics
- Responsive design
- H2 database support

---

**Happy Learning! Happy Coding!** 🚀

Developed with ❤️ for Java DevOps

More Practice:
> [Thymeleaf Pagination example](https://www.bezkoder.com/thymeleaf-pagination/)

> [Thymeleaf Pagination and Sorting example](https://www.bezkoder.com/thymeleaf-pagination-and-sorting-example/)

> [Spring Boot File upload example with Multipart File](https://www.bezkoder.com/spring-boot-file-upload/)

> [Spring Boot Pagination & Filter example | Spring JPA, Pageable](https://www.bezkoder.com/spring-boot-pagination-filter-jpa-pageable/)

> [Spring Data JPA Sort/Order by multiple Columns | Spring Boot](https://www.bezkoder.com/spring-data-sort-multiple-columns/)

> [Spring Boot Repository Unit Test with @DataJpaTest](https://www.bezkoder.com/spring-boot-unit-test-jpa-repo-datajpatest/)

> [Deploy Spring Boot App on AWS – Elastic Beanstalk](https://www.bezkoder.com/deploy-spring-boot-aws-eb/)

Exception Handling:
> [Spring Boot @ControllerAdvice & @ExceptionHandler example](https://www.bezkoder.com/spring-boot-controlleradvice-exceptionhandler/)

> [@RestControllerAdvice example in Spring Boot](https://www.bezkoder.com/spring-boot-restcontrolleradvice/)

Rest API:
> [Spring Boot + MySQL: CRUD Rest API example](https://www.bezkoder.com/spring-boot-jpa-crud-rest-api/)

> [Spring Boot + PostgreSQL: CRUD Rest API example](https://www.bezkoder.com/spring-boot-postgresql-example/)

> [Spring Boot + SQL Server: CRUD Rest API example](https://www.bezkoder.com/spring-boot-sql-server/)

> [Spring Boot + H2: CRUD Rest API example](https://www.bezkoder.com/spring-boot-jpa-h2-example/)

> [Spring Boot + MongoDB: CRUD Rest API example](https://www.bezkoder.com/spring-boot-mongodb-crud/)

> [Spring Boot + Oracle: CRUD Rest API example](https://www.bezkoder.com/spring-boot-hibernate-oracle/)

> [Spring Boot + Cassandra: CRUD Rest API example](https://www.bezkoder.com/spring-boot-cassandra-crud/)

Security:
> [Spring Boot + Spring Security JWT Authentication & Authorization](https://www.bezkoder.com/spring-boot-jwt-authentication/)

Fullstack:
> [Vue + Spring Boot example](https://www.bezkoder.com/spring-boot-vue-js-crud-example/)

> [Angular 8 + Spring Boot example](https://www.bezkoder.com/angular-spring-boot-crud/)

> [Angular 10 + Spring Boot example](https://www.bezkoder.com/angular-10-spring-boot-crud/)

> [Angular 11 + Spring Boot example](https://www.bezkoder.com/angular-11-spring-boot-crud/)

> [Angular 12 + Spring Boot example](https://www.bezkoder.com/angular-12-spring-boot-crud/)

> [Angular 13 + Spring Boot example](https://www.bezkoder.com/spring-boot-angular-13-crud/)

> [Angular 14 + Spring Boot example](https://www.bezkoder.com/spring-boot-angular-14-crud/)

> [React + Spring Boot + MySQL example](https://www.bezkoder.com/react-spring-boot-crud/)

> [React + Spring Boot + PostgreSQL example](https://www.bezkoder.com/spring-boot-react-postgresql/)

Run both Back-end & Front-end in one place:
> [Integrate Angular with Spring Boot Rest API](https://www.bezkoder.com/integrate-angular-spring-boot/)

> [Integrate React.js with Spring Boot Rest API](https://www.bezkoder.com/integrate-reactjs-spring-boot/)

> [Integrate Vue.js with Spring Boot Rest API](https://www.bezkoder.com/integrate-vue-spring-boot/)

## Run Spring Boot application
```
mvn spring-boot:run
```

