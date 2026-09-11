# Book Management API

REST API built with Spring Boot for managing books.

## Features

- Create book
- Get all books
- Get book by ID
- Update book
- Delete book
- Search by title/author
- Filter by price range
- Pagination
- Sorting
- Request/Response DTOs
- Validation
- Global exception handling
- Swagger/OpenAPI documentation
- MySQL database
- JPA Specifications

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Lombok
- Swagger/OpenAPI
- Maven

## API

### Create Book

POST `/books`

### Get Books

GET `/books`

Example:

`/books/?search=Robert&minPrice=150&maxPrice=700&page=0&size=10&sortBy=price&direction=asc`

...
