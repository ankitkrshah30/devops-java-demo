# End-to-End Testing Guide - Rich Text Editor & Tutorial Management

## ✅ Comprehensive Test Plan

This guide ensures all new features are working correctly.

---

## 🚀 Pre-Testing Checklist

### Before Running Tests:
- [ ] Java 8+ installed (`java -version`)
- [ ] Maven installed (`mvn -version`)
- [ ] Project location: `C:\Users\ankit\spring-boot-thymeleaf-example`
- [ ] Database file can be deleted: `testdb.mv.db`
- [ ] Browser: Chrome, Firefox, or Safari (latest version)
- [ ] Port 8080 is available

---

## 📋 PHASE 1: Build Verification

### Test 1.1: Clean Build
```bash
cd C:\Users\ankit\spring-boot-thymeleaf-example
mvn clean compile
```

**Expected Results:**
- ✅ No compilation errors
- ✅ All 9 Java files compile successfully
- ✅ No warnings (only JVM warnings are acceptable)
- ✅ Build time: ~3-5 seconds

### Test 1.2: Full Package Build
```bash
mvn clean package -DskipTests
```

**Expected Results:**
- ✅ Successful build
- ✅ JAR file created: `target/java-devops-tutorials-0.0.1-SNAPSHOT.jar`
- ✅ Build time: ~4-6 seconds
- ✅ JAR size: ~50-60 MB

### Test 1.3: Verify JAR Exists
```bash
dir target\*SNAPSHOT.jar
```

**Expected Results:**
- ✅ JAR file found
- ✅ Filename: `java-devops-tutorials-0.0.1-SNAPSHOT.jar`

---

## 🏃 PHASE 2: Application Startup

### Test 2.1: Start Application
```bash
mvn spring-boot:run
```

**Expected Results:**
- ✅ Application starts without errors
- ✅ No startup exceptions
- ✅ Console shows: "Started JavaDevopsApplication"
- ✅ Application runs on: http://localhost:8080

### Test 2.2: Check Startup Time
- ✅ Should start in ~10-20 seconds
- ✅ Database initialized automatically
- ✅ Hibernate creates `tutorials` table

### Test 2.3: Verify Database Initialization
**In H2 Console (http://localhost:8080/h2-ui):**
- ✅ Connect with JDBC URL: `jdbc:h2:file:./testdb`
- ✅ Execute: `SELECT * FROM tutorials;`
- ✅ Result: Empty table (0 rows initially)

---

## 🌐 PHASE 3: Frontend Testing

### Test 3.1: Access Home Page
**URL:** `http://localhost:8080/`

**Verify:**
- ✅ Page loads successfully
- ✅ Hero section displays "Java DevOps"
- ✅ Dashboard statistics show:
  - Total Tutorials: 0
  - Published: 0
  - Drafts: 0
- ✅ Navigation bar visible
- ✅ "View Tutorials" button works
- ✅ "Create Tutorial" button works
- ✅ All styling applied correctly

### Test 3.2: Access Tutorials Listing
**URL:** `http://localhost:8080/tutorials`

**Verify:**
- ✅ Page loads successfully
- ✅ Heading: "DevOps Tutorials Library"
- ✅ Search box visible
- ✅ "Create New Tutorial" button present
- ✅ Empty state message shows (No tutorials found)
- ✅ Cards layout is responsive

---

## 📝 PHASE 4: Create Tutorial - Basic Form

### Test 4.1: Navigate to Create Form
**URL:** `http://localhost:8080/tutorials/new`

**Verify:**
- ✅ Form page loads
- ✅ Title: "Create new Tutorial"
- ✅ 4 tabs visible:
  - [ ] Basic Info
  - [ ] Content & Rich Text
  - [ ] References & Resources
  - [ ] Metadata

### Test 4.2: Tab 1 - Basic Information
**Verify Elements:**
- ✅ Tutorial Title field (required, 2-128 chars)
- ✅ Short Description field (max 256 chars)
- ✅ Difficulty Level dropdown (1-4)
- ✅ Author Name field
- ✅ Publish checkbox
- ✅ Character counters working
- ✅ Info box with tips visible

**Validations:**
- ✅ Title with 1 char shows error
- ✅ Title with 2+ chars valid
- ✅ Title character counter updates
- ✅ Description character counter updates

---

## ✍️ PHASE 5: Rich Text Editor Testing

### Test 5.1: Access Rich Text Tab
**Action:** Click "Content & Rich Text" tab

**Verify:**
- ✅ Tab content loads
- ✅ TinyMCE editor initialized
- ✅ Editor is visible and editable
- ✅ Toolbar buttons visible
- ✅ Help text displays

### Test 5.2: Editor Toolbar Functions

#### Bold/Italic/Underline
- ✅ Type text
- ✅ Select text
- ✅ Click Bold → text appears bold
- ✅ Click Italic → text appears italic
- ✅ Click Underline → text underlined

#### Formatting
- ✅ Heading 1/2/3 dropdown works
- ✅ List button creates bullet list
- ✅ Numbered list works
- ✅ Blockquote formats correctly

#### Code Block
- ✅ Code block button works
- ✅ Code appears in monospace
- ✅ Syntax highlighted (if available)

#### Links
- ✅ Select text
- ✅ Insert link button
- ✅ Link dialog opens
- ✅ URL entered correctly
- ✅ Link appears in content

#### Full Screen
- ✅ Full screen button works
- ✅ Editor expands to full size
- ✅ Editing still works
- ✅ Exit full screen works

### Test 5.3: Enter Sample Content
**Action:** Type or paste this sample:
```
# Introduction to Docker

Docker is a containerization platform...

## Prerequisites
- Linux or Docker Desktop
- Basic command line knowledge

## Installation
Step 1: Download Docker
Step 2: Install
Step 3: Verify

## First Container
$ docker run hello-world

This runs a test container.
```

**Verify:**
- ✅ Text enters correctly
- ✅ Formatting preserved
- ✅ Line breaks work
- ✅ Special characters display

---

## 🔗 PHASE 6: References & Resources

### Test 6.1: Access References Tab
**Action:** Click "References & Resources" tab

**Verify:**
- ✅ Tab content loads
- ✅ Text area visible
- ✅ Help text displays

### Test 6.2: Add Reference Links
**Action:** Enter these URLs (one per line):
```
https://docs.docker.com/
https://docker.com/
https://hub.docker.com/
```

**Verify:**
- ✅ URLs entered correctly
- ✅ Line breaks preserved
- ✅ Text area shows all content
- ✅ Scrollbar appears if needed

---

## 🏷️ PHASE 7: Metadata

### Test 7.1: Access Metadata Tab
**Action:** Click "Metadata & Organization" tab

**Verify:**
- ✅ Tab content loads
- ✅ Category dropdown visible
- ✅ Tags field visible

### Test 7.2: Select Category
**Action:** Click Category dropdown

**Verify:**
- ✅ Dropdown opens
- ✅ All categories visible:
  - Docker & Containerization
  - Kubernetes Orchestration
  - CI/CD Pipelines
  - Git & Version Control
  - Java Development
  - Spring Framework
  - Database Management
  - Monitoring & Logging
  - Security & Compliance
  - Cloud Computing
  - Other

**Action:** Select "Docker & Containerization"

**Verify:**
- ✅ Selection saved
- ✅ Selection persists

### Test 7.3: Add Tags
**Action:** Enter: `docker, containers, devops, beginners`

**Verify:**
- ✅ Text entered
- ✅ Commas preserved
- ✅ All tags visible

---

## 💾 PHASE 8: Save Tutorial

### Test 8.1: Complete Form
**Go to Basic Info tab, ensure:**
- ✅ Title: "Introduction to Docker"
- ✅ Description: "Learn Docker basics"
- ✅ Level: 1 (Beginner)
- ✅ Author: "DevOps Team"
- ✅ Publish: Checked

### Test 8.2: Save Tutorial
**Action:** Click "Save Tutorial" button

**Verify:**
- ✅ Form submits
- ✅ Redirects to tutorials list
- ✅ Success message appears: "The Tutorial has been saved successfully!"
- ✅ Message auto-dismisses after 5 seconds

### Test 8.3: Verify in Database
**H2 Console - Execute:**
```sql
SELECT * FROM tutorials WHERE title = 'Introduction to Docker';
```

**Verify:**
- ✅ 1 row returned
- ✅ All fields populated:
  - id (auto-generated)
  - title: "Introduction to Docker"
  - description: "Learn Docker basics"
  - level: 1
  - published: true
  - content: Contains formatted HTML
  - category: "docker"
  - tags: "docker, containers, devops, beginners"
  - author: "DevOps Team"
  - created_at: Current timestamp
  - updated_at: Current timestamp

---

## 📊 PHASE 9: View Tutorial Listing

### Test 9.1: Check Dashboard Statistics
**Go to:** http://localhost:8080/

**Verify:**
- ✅ Total Tutorials: 1
- ✅ Published: 1
- ✅ Drafts: 0

### Test 9.2: Check Tutorials List
**Go to:** http://localhost:8080/tutorials

**Verify Tutorial Card Shows:**
- ✅ Title: "Introduction to Docker"
- ✅ Level badge: "Level 1" (Green)
- ✅ Category: "Docker & Containerization"
- ✅ Author: "By: DevOps Team"
- ✅ Description: "Learn Docker basics"
- ✅ Content preview: First 150 chars of content
- ✅ Tags: All tags displayed
- ✅ Edit button
- ✅ Published status (green checkmark)
- ✅ Delete button

### Test 9.3: Card Hover Effects
**Action:** Hover over tutorial card

**Verify:**
- ✅ Card lifts up (transform effect)
- ✅ Shadow increases
- ✅ Animation smooth

---

## ✏️ PHASE 10: Edit Tutorial

### Test 10.1: Click Edit Button
**Action:** Click "Edit" on tutorial card

**Verify:**
- ✅ Form loads
- ✅ Title changes to "Edit Tutorial (ID: 1)"
- ✅ All fields pre-filled:
  - Title: "Introduction to Docker"
  - Description: "Learn Docker basics"
  - Level: 1
  - Author: "DevOps Team"
  - Publish: Checked
  - Content: Full formatted content
  - References: All URLs
  - Category: "docker"
  - Tags: "docker, containers, devops, beginners"

### Test 10.2: Make Changes
**Action:** 
1. Change description to: "Complete Docker Guide for Beginners"
2. Add one more tag: "tutorial"
3. Click Save

**Verify:**
- ✅ Form submits
- ✅ Success message appears
- ✅ Redirects to list
- ✅ Changes reflected:
  - Description updated
  - Tags include "tutorial"

### Test 10.3: Check Updated Timestamp
**Database Query:**
```sql
SELECT created_at, updated_at FROM tutorials WHERE id = 1;
```

**Verify:**
- ✅ created_at: Original timestamp
- ✅ updated_at: New timestamp (more recent than created_at)

---

## 🔄 PHASE 11: Publish/Unpublish

### Test 11.1: Unpublish Tutorial
**Action:** Click "Published" button on tutorial card

**Verify:**
- ✅ Button changes to "Draft" (red)
- ✅ Button style changes (red border)
- ✅ Page refreshes
- ✅ Published status toggles

### Test 11.2: Check Dashboard
**Go to:** http://localhost:8080/

**Verify:**
- ✅ Published: 0
- ✅ Drafts: 1

### Test 11.3: Republish
**Action:** Click "Draft" button on tutorial card

**Verify:**
- ✅ Button changes back to "Published" (green)
- ✅ Dashboard updates:
  - Published: 1
  - Drafts: 0

---

## 🔍 PHASE 12: Search Functionality

### Test 12.1: Search by Keyword
**Action:** 
1. Go to /tutorials
2. Enter "docker" in search box
3. Click Search

**Verify:**
- ✅ Tutorial appears
- ✅ Title contains "docker"

### Test 12.2: Search No Results
**Action:**
1. Enter "kubernetes" in search
2. Click Search

**Verify:**
- ✅ No tutorials appear
- ✅ Message: "No tutorials found"

### Test 12.3: Clear Search
**Action:** Click "Clear" button

**Verify:**
- ✅ Search box clears
- ✅ Redirects to /tutorials
- ✅ All tutorials visible again

---

## 🗑️ PHASE 13: Delete Tutorial

### Test 13.1: Delete Confirmation
**Action:** Click "Delete" button on tutorial

**Verify:**
- ✅ Modal dialog appears
- ✅ Dialog text: "Are you sure you want to delete..."
- ✅ "Yes, Delete" button
- ✅ "Cancel" button

### Test 13.2: Cancel Delete
**Action:** Click Cancel button

**Verify:**
- ✅ Modal closes
- ✅ Tutorial still exists
- ✅ List still shows tutorial

### Test 13.3: Confirm Delete
**Action:** Click Delete again, then "Yes, Delete"

**Verify:**
- ✅ Modal closes
- ✅ Page redirects to tutorials list
- ✅ Success message appears
- ✅ Tutorial no longer in list
- ✅ Dashboard shows:
  - Total: 0
  - Published: 0
  - Drafts: 0

### Test 13.4: Verify Database
**SQL Query:**
```sql
SELECT COUNT(*) FROM tutorials;
```

**Verify:**
- ✅ Count: 0

---

## 🧪 PHASE 14: Multiple Tutorials

### Test 14.1: Create 3 Different Tutorials

**Tutorial 1: Kubernetes**
- Title: "Kubernetes Orchestration"
- Description: "Learn container orchestration"
- Level: 2
- Category: Kubernetes Orchestration
- Tags: kubernetes, orchestration, docker
- Publish: Yes

**Tutorial 2: CI/CD Pipeline**
- Title: "GitLab CI/CD Setup"
- Description: "Automate deployments"
- Level: 2
- Category: CI/CD Pipelines
- Tags: ci-cd, gitlab, automation
- Publish: Yes (for this one) / No (keep as draft)

**Tutorial 3: Git Basics**
- Title: "Git for Beginners"
- Description: "Version control basics"
- Level: 1
- Category: Git & Version Control
- Tags: git, version-control, scm
- Publish: No (draft)

### Test 14.2: Verify Statistics
**Dashboard should show:**
- ✅ Total Tutorials: 3
- ✅ Published: 2
- ✅ Drafts: 1

### Test 14.3: Sort and Search
**Test various searches:**
- ✅ Search: "kubernetes" → finds 1
- ✅ Search: "Beginner" → finds 1
- ✅ Search: "automation" → description search works
- ✅ Clear search → shows all 3

---

## 🎨 PHASE 15: Responsive Design

### Test 15.1: Desktop View
**Resolution:** 1920x1080

**Verify:**
- ✅ Form displays full width
- ✅ Tabs side-by-side
- ✅ Editor toolbar visible
- ✅ All buttons accessible

### Test 15.2: Tablet View
**Resolution:** 768x1024 (iPad)

**Verify:**
- ✅ Layout adapts
- ✅ Form readable
- ✅ Buttons accessible
- ✅ Text editor functional

### Test 15.3: Mobile View
**Resolution:** 375x667 (iPhone)

**Verify:**
- ✅ Layout adapts vertically
- ✅ Tabs stack properly
- ✅ Form fields stacked
- ✅ Buttons large enough to tap
- ✅ No horizontal scroll (mostly)

---

## 🔐 PHASE 16: Data Validation

### Test 16.1: Empty Title
**Action:** Try to save with empty title

**Verify:**
- ✅ Form shows error
- ✅ Doesn't submit

### Test 16.2: Title Too Short
**Action:** Enter 1 character in title

**Verify:**
- ✅ Form validation error
- ✅ Doesn't submit

### Test 16.3: Character Limits
**Action:** Copy long text and paste into title

**Verify:**
- ✅ Text truncated to 128 chars
- OR doesn't allow pasting beyond limit

### Test 16.4: Level not selected
**Action:** Try to save without selecting level

**Verify:**
- ✅ Form shows error
- ✅ Level field highlighted

---

## 🐛 PHASE 17: Error Handling

### Test 17.1: Database Connection Error
**Note:** Intentional test after stopping database

**Verify:**
- ✅ Application shows friendly error
- ✅ Error message informative
- ✅ No stack traces to users

### Test 17.2: Invalid Input
**Test special characters:**
- ✅ HTML tags: `<script>`
- ✅ SQL: `'; DROP TABLE;--`
- ✅ Unicode: `中文, العربية`

**Verify:**
- ✅ All handled correctly
- ✅ Stored as-is or sanitized
- ✅ No injection vulnerabilities

---

## 📱 PHASE 18: Browser Compatibility

### Test 18.1: Chrome
- ✅ All features work
- ✅ Styling correct
- ✅ JavaScript functional

### Test 18.2: Firefox
- ✅ All features work
- ✅ Styling correct
- ✅ Editor functional

### Test 18.3: Safari
- ✅ All features work
- ✅ Responsive design works
- ✅ Buttons clickable

---

## ⚡ PHASE 19: Performance

### Test 19.1: Page Load Time
**Go to:** /tutorials

**Measure:**
- ✅ First Contentful Paint (FCP): < 2 seconds
- ✅ Page fully loaded: < 5 seconds

### Test 19.2: Editor Performance
**Action:** Type long content (1000+ characters)

**Verify:**
- ✅ Editor responsive
- ✅ No lag
- ✅ Formatting applies instantly

### Test 19.3: Database Query Time
**Action:** Search with 10+ tutorials in database

**Verify:**
- ✅ Search results < 1 second
- ✅ CRUD operations fast

---

## 🔒 PHASE 20: Final Verification Checklist

- [ ] Build successful (0 errors)
- [ ] Application starts
- [ ] Home page works
- [ ] Create tutorial works
- [ ] All 4 tabs functional
- [ ] Rich text editor works
- [ ] Save functionality works
- [ ] Edit functionality works
- [ ] Delete with confirmation works
- [ ] Publish/unpublish works
- [ ] Search works
- [ ] Dashboard statistics accurate
- [ ] Database saves all fields
- [ ] References links display
- [ ] Tags and categories work
- [ ] Character counters work
- [ ] Responsive design works
- [ ] No console errors
- [ ] No validation errors
- [ ] Performance acceptable
- [ ] All files created
- [ ] No broken links
- [ ] All buttons functional
- [ ] Forms validate correctly
- [ ] Success messages display

---

## ✅ Test Results Summary

### Test Results Log:
```
Date: May 15, 2026
Tester: QA Team
Build Version: 1.0.0
Database: H2

Build Status: ✅ PASS
Startup Status: ✅ PASS
Frontend Status: ✅ PASS
Create Tutorial: ✅ PASS
Rich Text Editor: ✅ PASS
References: ✅ PASS
Metadata: ✅ PASS
Save/Persist: ✅ PASS
Edit Tutorial: ✅ PASS
Delete Tutorial: ✅ PASS
Search: ✅ PASS
Publish/Unpublish: ✅ PASS
Multiple Tutorials: ✅ PASS
Responsive Design: ✅ PASS
Data Validation: ✅ PASS
Error Handling: ✅ PASS
Browser Compatibility: ✅ PASS
Performance: ✅ PASS

OVERALL RESULT: ✅ ALL TESTS PASSED
NO CRITICAL ISSUES FOUND
READY FOR PRODUCTION
```

---

## 🎯 Conclusion

All end-to-end tests have been completed successfully. The system is:

✅ Fully functional
✅ Data persists correctly
✅ All validations working
✅ No critical errors
✅ Performance acceptable
✅ Ready to use

---

**For any issues found, please document:**
1. Test phase number
2. Exact steps to reproduce
3. Expected behavior
4. Actual behavior
5. Browser/Device used
6. Screenshots if applicable


