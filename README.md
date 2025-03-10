# Courier Tracking App

This project is a **Courier Management System** developed using Spring Boot. It provides various APIs to manage courier information, track locations, and calculate distances.

## Table of Contents

1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [Installation](#installation)
4. [Running the Application](#running-the-application)
5. [API Endpoints](#api-endpoints)
6. [Swagger Documentation](#swagger-documentation)

## Introduction

This project aims to provide an efficient system for managing couriers, updating their locations, and calculating distances traveled. It uses various Spring Boot features such as REST APIs, Kafka for event processing, Redis for caching, and more.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17 or later
- Maven 3.6.0 or later
- Docker (for containerization)
- Kafka (for event processing)
- Redis (for caching)
- PostgreSQL


## Installation

1. **Clone the Repository**

    ```bash
    git clone https://github.com/emrcnort/courier-tracking-app.git
    cd courier-tracking-app
    ```

2. **Build the Project**

    ```bash
    mvn clean install
    ```

3. **Run Kafka and Redis Containers**

    ```bash
    docker-compose up -d
    ```

## Running the Application

To run the application, use the following command:

```bash
mvn spring-boot:run
```
The application will be available at http://localhost:8080.

# API Endpoints

## Get Total Distance

**Endpoint:** `/api/v1/couriers/total-distances/{courier-id}`

**Method:** `GET`

**Description:** Get the total distance covered by a courier.

**Response:** `CourierDistanceDto`

```json
{
  "totalKilometers": 1
}
```

## Update Courier Location

**Endpoint:** `/api/v1/couriers/location`

**Method:** `POST`

**Description:** Update the location of a courier.

**Request:** `CourierLocationEvent`

```json
{
  "courierId": 1,
  "lat": 40.7128,
  "lng": -74.0060,
  "timestamp": "2025-03-09T12:00:00Z"
}
```

## Get Couriers with Pagination

**Endpoint:** `/api/v1/couriers`

**Method:** `GET`

**Description:**  Get a list of couriers with pagination.

**Query Parameters:** page, size

**Response:** `Page<CourierDto>`

```json
[
  {
    "id": 1,
    "name": "John Doe",
    "employeeNumber": 12345,
    "isActive": true
  }
]
```

## Save or Update Courier

**Endpoint:** `/api/v1/couriers`

**Method:** `POST`

**Description:**  Save or update a courier.

**Request:** `CourierRequest`

```json
{
  "name": "Jane Doe",
  "employeeNumber": 67890
}
```

# Swagger Documentation
To access the Swagger UI for API documentation, navigate to:

http://localhost:8080/swagger-ui.html
