# OrderHub – Order status integration hub

A Spring Boot application that combines orders from two different systems (JSON and CSV) into a single, normalized format.
It exposes REST APIs and a simple web page to view and filter orders.

## Tech Used
* Java 17
* Spring Boot (REST + MVC)
* Jackson (JSON parsing)
* OpenCSV (CSV parsing)
* Thymeleaf + JavaScript

Chosen to keep the solution simple, readable, and easy to run.

## Live URL : Render
* To check html : https://orderstatusintegration.onrender.com/orders 
* Health check : https://orderstatusintegration.onrender.com/api/health
* Filter by Id : https://orderstatusintegration.onrender.com/api/orders/A-2024-1002
* Filter by Status : https://orderstatusintegration.onrender.com/api/orders/search?status=Pending
* Filter by Date : https://orderstatusintegration.onrender.com/api/orders/search?startDate=2024-11-01&endDate=2024-11-25


## How to Run

bash
```
 ./gradlew clean build
 ./gradlew bootRun
```

Then open:
* UI: [http://localhost:8080/orders](http://localhost:8080/orders)
* Health check: [http://localhost:8080/api/health](http://localhost:8080/api/health)

## API Endpoints
* `GET /api/orders` – get all orders
* `GET /api/orders/{orderId}` – get order by ID
* `GET /api/orders/search?status=Pending` – filter by status
* `GET /api/orders/search?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD` – filter by date range

## Approach
* Used a single order model to normalize data from both systems
* Mapper classes handle differences in:
    * Field names
    * Date formats
    * Status values
* Focused on clean structure and defensive handling of bad input

## Limitations
* Data is loaded in-memory (no database)
* No pagination or authentication
* Basic error handling only

## With More Time
* Add database persistence
* Better API error responses by adding centralized exception handling class.
* Add pagination and tests
* Add Swagger/OpenAPI docs
* Add bootstrap on frontend with color coded status
* Deploy it on AWS ec2
