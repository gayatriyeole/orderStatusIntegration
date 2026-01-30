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

