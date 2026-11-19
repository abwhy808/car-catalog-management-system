# Car Catalog Management System

A full-stack web application for managing car manufacturers, vehicle models, and car listings. Built as **Laboratory Work 9** using Java Spring Boot, React, and PostgreSQL.

## Features

- **Manufacturers:** create, view, edit, and delete brands with country information and logo uploads.
- **Vehicle models:** manage models and associate them with manufacturers, including body types.
- **Cars:** create, view, edit, and delete listings with year, price, color, mileage, fuel type, transmission, description, and photo uploads.
- **Search and filtering:** browse cars by text, manufacturer, and model.
- **Persistent data:** records are saved in PostgreSQL, not just in the browser.
- **Image handling:** uploaded images are saved to the server's `uploads/` folder; the database stores their URLs.

## Tech stack

| Part | Technology |
| --- | --- |
| Frontend | React 18, Vite 5, JavaScript, CSS, lucide-react |
| Backend | Java 17, Spring Boot 3.3.5, Spring Web, Spring Data JPA, Validation |
| Database | PostgreSQL |
| Build tools | Maven, npm |
| Communication | REST API, JSON, multipart/form-data for images |

## Project structure

```text
car-catalog/
├── backend/
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/example/carcatalog/
│       │   ├── config/
│       │   ├── controller/
│       │   ├── dto/
│       │   ├── entity/
│       │   ├── exception/
│       │   ├── repository/
│       │   └── service/
│       └── resources/
├── frontend/
│   ├── src/
│   │   ├── App.jsx
│   │   ├── api.js
│   │   ├── main.jsx
│   │   └── style.css
│   ├── package.json
│   └── vite.config.js
├── database/
│   └── create_database.sql
└── README.md
```

## Getting started

### Prerequisites

- Java JDK 17 or later
- Maven (or IntelliJ IDEA with Maven integration)
- Node.js and npm
- PostgreSQL

### 1. Clone the repository

```bash
git clone https://github.com/abwhy808/car-catalog-management-system.git
cd car-catalog-management-system
```

### 2. Create the database

In pgAdmin 4, create a PostgreSQL database named `car_catalog`, or run the following SQL while connected to an existing database such as `postgres`:

```sql
CREATE DATABASE car_catalog;
```

The application uses Spring Data JPA / Hibernate to create or update its tables on startup (`ddl-auto=update`).

### 3. Configure the backend

Create `backend/src/main/resources/application.properties` with the following configuration:

```properties
spring.application.name=car-catalog
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/car_catalog
spring.datasource.username=postgres
spring.datasource.password=YOUR_POSTGRESQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
app.upload-dir=uploads
```

Replace `YOUR_POSTGRESQL_PASSWORD` with your local PostgreSQL password. **Do not commit this file or share your real password.** The project's `.gitignore` excludes `application.properties`.

Open the `backend` folder in IntelliJ IDEA, load Maven dependencies, and run `CarCatalogApplication.java`.

Alternatively, from the `backend` folder, run:

```bash
mvn spring-boot:run
```

Backend address: `http://localhost:8080`

### 4. Start the frontend

Open another terminal in the `frontend` folder:

```bash
npm install
npm run dev
```

On Windows PowerShell, if execution policy blocks `npm.ps1`, use:

```powershell
npm.cmd install
npm.cmd run dev
```

Open **http://localhost:5173/** in a browser. Vite proxies `/api` and `/uploads` to the Spring Boot server on port `8080`.

### 5. Try the application

1. Add a manufacturer such as **Toyota** or **Dodge**.
2. Add a vehicle model, e.g. **Camry** or **Challenger**, and associate it with its manufacturer.
3. Add a car with its characteristics and upload an image.
4. Search or filter the catalog.
5. Edit the car and refresh the page to verify persistent storage.
6. Delete a test car using the delete action.

**Note:** Delete dependent cars before deleting their models, and delete models before their manufacturer.

## Architecture

```text
React UI
   │ HTTP / JSON
   ▼
Spring Boot REST Controllers
   │
   ▼
Service layer (business logic)
   │
   ▼
Spring Data JPA Repositories
   │
   ▼
PostgreSQL
```

The three main entities have the following relationships:

```text
Manufacturer (1) ─── (many) VehicleModel (1) ─── (many) Car
```

## REST API

| Method | Endpoint | Purpose |
| --- | --- | --- |
| GET | `/api/manufacturers` | List manufacturers |
| POST | `/api/manufacturers` | Create a manufacturer |
| PUT | `/api/manufacturers/{id}` | Update a manufacturer |
| DELETE | `/api/manufacturers/{id}` | Delete a manufacturer |
| GET | `/api/models` | List models; optional `manufacturerId` filter |
| POST | `/api/models` | Create a model |
| PUT | `/api/models/{id}` | Update a model |
| DELETE | `/api/models/{id}` | Delete a model |
| GET | `/api/cars` | List cars; optional `q`, `manufacturerId`, `modelId` |
| POST | `/api/cars` | Create a car |
| PUT | `/api/cars/{id}` | Update a car |
| DELETE | `/api/cars/{id}` | Delete a car |
| POST | `/api/uploads` | Upload an image (multipart field: `file`) |

The API also supports retrieving individual manufacturers, models, and cars by ID.

## Screenshots

Add real screenshots from your running application to a `screenshots/` folder, then uncomment these lines:

<!-- ![Car catalog](screenshots/catalog.png) -->
<!-- ![Manufacturers](screenshots/manufacturers.png) -->
<!-- ![Vehicle models](screenshots/models.png) -->

## Notes

- This is an academic project created for Laboratory Work 9.
- The backend and frontend must both be running for the web interface to access saved data.
- Image files and local database credentials are intentionally excluded from the repository.
