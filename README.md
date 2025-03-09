# Save the formatted README content as a markdown file

readme_content = """# Courier Management System

This project is a **Courier Management System** developed using Spring Boot. It provides various APIs to manage courier information, track locations, and calculate distances.

![Courier Management System](path/to/image.png)

## Table of Contents

1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [Installation](#installation)
4. [Running the Application](#running-the-application)
5. [API Endpoints](#api-endpoints)
6. [Swagger Documentation](#swagger-documentation)
7. [Contributing](#contributing)
8. [License](#license)

## Introduction

This project aims to provide an efficient system for managing couriers, updating their locations, and calculating distances traveled. It uses various Spring Boot features such as REST APIs, Kafka for event processing, Redis for caching, and more.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17 or later
- Maven 3.6.0 or later
- Docker (for containerization)
- Kafka (for event processing)
- Redis (for caching)

## Installation

1. **Clone the Repository**

    ```bash
    git clone https://github.com/yourusername/courier-management-system.git
    cd courier-management-system
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
