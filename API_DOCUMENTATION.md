# Java DevOps - API Documentation

## Overview
This document describes all available API endpoints in the Java DevOps Tutorial Management System.

## Base URL
```
http://localhost:8080
```

## Endpoints

### 1. Home & Dashboard

#### Get Dashboard
- **Endpoint:** `GET /`
- **Description:** Display the home page with dashboard statistics
- **Response Type:** HTML
- **Query Parameters:** None
- **Example:**
  ```
  GET http://localhost:8080/
  ```

---

### 2. Tutorial Management

#### List All Tutorials
- **Endpoint:** `GET /tutorials`
- **Description:** Get list of all tutorials with optional search
- **Response Type:** HTML
- **Query Parameters:**
  - `keyword` (optional): Search keyword to filter tutorials by title
- **Example:**
  ```
  GET http://localhost:8080/tutorials
  GET http://localhost:8080/tutorials?keyword=Docker
  ```

#### View Tutorial Form (Create)
- **Endpoint:** `GET /tutorials/new`
- **Description:** Display form to create a new tutorial
- **Response Type:** HTML
- **Query Parameters:** None
- **Example:**
  ```
  GET http://localhost:8080/tutorials/new
  ```

#### Save Tutorial (Create/Update)
- **Endpoint:** `POST /tutorials/save`
- **Description:** Create a new tutorial or update an existing one
- **Content Type:** `application/x-www-form-urlencoded` or `multipart/form-data`
- **Form Parameters:**
  - `id` (optional): Tutorial ID (omit for create, include for update)
  - `title` (required): Tutorial title (2-128 characters)
  - `description` (optional): Tutorial description (max 256 characters)
  - `level` (required): Difficulty level (positive integer)
  - `published` (optional): Boolean flag (checked = true, unchecked = false)
- **Response:** Redirect to `/tutorials` with success message
- **Example:**
  ```
  POST http://localhost:8080/tutorials/save
  Form Data:
  - title: "Introduction to Docker"
  - description: "Learn Docker basics"
  - level: 1
  - published: on
  ```

#### Edit Tutorial Form
- **Endpoint:** `GET /tutorials/{id}`
- **Description:** Display form to edit an existing tutorial
- **Path Parameters:**
  - `id` (required): Tutorial ID
- **Response Type:** HTML
- **Example:**
  ```
  GET http://localhost:8080/tutorials/1
  ```

#### Delete Tutorial
- **Endpoint:** `GET /tutorials/delete/{id}`
- **Description:** Delete a tutorial by ID
- **Path Parameters:**
  - `id` (required): Tutorial ID
- **Response:** Redirect to `/tutorials` with success message
- **Example:**
  ```
  GET http://localhost:8080/tutorials/delete/1
  ```

#### Toggle Published Status
- **Endpoint:** `GET /tutorials/{id}/published/{status}`
- **Description:** Update the published status of a tutorial
- **Path Parameters:**
  - `id` (required): Tutorial ID
  - `status` (required): Boolean status (true or false)
- **Response:** Redirect to `/tutorials` with status update message
- **Example:**
  ```
  GET http://localhost:8080/tutorials/1/published/true
  GET http://localhost:8080/tutorials/1/published/false
  ```

---

### 3. Database Management

#### H2 Database Console
- **Endpoint:** `GET /h2-ui`
- **Description:** Access the H2 database web console
- **Response Type:** HTML
- **Query Parameters:** None
- **Note:** Only available when `spring.h2.console.enabled=true`
- **Example:**
  ```
  GET http://localhost:8080/h2-ui
  ```
- **Connection Details:**
  - JDBC URL: `jdbc:h2:file:./testdb`
  - Driver: `org.h2.Driver`
  - Username: `sa`
  - Password: (leave empty)

---

## HTTP Status Codes

| Code | Description |
|------|-------------|
| 200 | OK - Request successful |
| 302 | Found - Redirect (after POST/DELETE operations) |
| 400 | Bad Request - Invalid parameters |
| 404 | Not Found - Tutorial ID doesn't exist |
| 500 | Internal Server Error - Server error |

---

## Request/Response Examples

### Example 1: Create a Tutorial

**Request:**
```
POST /tutorials/save HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded

title=Kubernetes+for+Beginners&description=Learn+K8s+basics&level=2&published=on
```

**Response:**
```
HTTP/1.1 302 Found
Location: /tutorials?message=The+Tutorial+has+been+saved+successfully!
```

### Example 2: Search Tutorials

**Request:**
```
GET /tutorials?keyword=Docker HTTP/1.1
Host: localhost:8080
```

**Response:**
```
HTTP/1.1 200 OK
Content-Type: text/html;charset=UTF-8

[Returns tutorials.html with filtered results containing "Docker"]
```

### Example 3: Toggle Published Status

**Request:**
```
GET /tutorials/1/published/true HTTP/1.1
Host: localhost:8080
```

**Response:**
```
HTTP/1.1 302 Found
Location: /tutorials?message=The+Tutorial+id=1+has+been+published
```

---

## Error Handling

All errors are handled gracefully with user-friendly messages displayed on the page.

### Common Error Scenarios

#### Tutorial Not Found
- **Cause:** Attempting to edit/delete a tutorial with invalid ID
- **Response:** Redirect to `/tutorials` with error message

#### Validation Error
- **Cause:** Missing required fields or invalid data
- **Response:** Form redisplayed with validation error messages

#### Database Error
- **Cause:** Database connection or query issues
- **Response:** Error message displayed to user

---

## Form Validation Rules

### Tutorial Title
- **Required:** Yes
- **Type:** String
- **Min Length:** 2 characters
- **Max Length:** 128 characters

### Tutorial Description
- **Required:** No
- **Type:** String
- **Min Length:** 2 characters
- **Max Length:** 256 characters

### Tutorial Level
- **Required:** Yes
- **Type:** Integer
- **Min Value:** 1
- **Max Value:** Unlimited
- **Examples:** 1 (Beginner), 2 (Intermediate), 3 (Advanced)

### Published Status
- **Required:** No
- **Type:** Boolean
- **Default:** false (unchecked)

---

## Rate Limiting & Performance

- No rate limiting implemented
- Recommended for up to 10,000 tutorials
- For larger datasets, consider implementing pagination

---

## Authentication & Authorization

Currently, the application has no authentication/authorization. Future versions will include:
- User login/registration
- Role-based access control
- Tutorial ownership/permissions

---

## CORS Configuration

Currently, CORS is not configured. Cross-origin requests from other domains are not allowed.

---

## Version History

- **v1.0.0** - Initial API documentation
  - Basic CRUD endpoints
  - H2 database console
  - Search functionality

---

## Support & Questions

For issues or questions regarding the API:
1. Check the README.md for setup instructions
2. Review application logs for error details
3. Verify database connection settings
4. Contact the development team

---

**Last Updated:** May 15, 2026

