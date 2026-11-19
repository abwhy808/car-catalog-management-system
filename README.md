# AutoVault — Car Catalog Management System (Lab 9)

A full-stack car catalog made with **Spring Boot (Java 17)**, **React + Vite**, and **PostgreSQL**.

## Features

- Manufacturer CRUD: name, country, uploaded brand logo
- Vehicle model CRUD: each model belongs to a manufacturer
- Car CRUD: year, price, color, mileage, fuel, transmission, description, uploaded photograph
- Search by manufacturer/model/color/year and filter by manufacturer and model
- Responsive dark UI with dashboard counters, cards, tables, modal forms, and image previews
- Persistent PostgreSQL records via Spring Data JPA
- Uploads saved on disk in backend/uploads; image paths stored in PostgreSQL

## Requirements

- JDK 17 or newer
- Maven 3.9+ (or IntelliJ IDEA's Maven runner)
- Node.js 20+ with npm
- PostgreSQL (pgAdmin 4 is optional)

## 1. Create the database

In pgAdmin, open Query Tool on the **postgres** database and execute:

```sql
CREATE DATABASE car_catalog;
```

Use the same PostgreSQL username/password you configured in PostgreSQL. You can view the new database in pgAdmin after refreshing Databases. Spring Boot creates all three tables automatically on its first successful startup.

## 2. Configure and run backend

Open the `backend` folder in IntelliJ IDEA as a Maven project. In a terminal:

**Windows PowerShell** (replace with your real password):

```powershell
$env:DB_USER="postgres"
$env:DB_PASSWORD="YOUR_PASSWORD"
cd backend
mvn spring-boot:run
```

**macOS/Linux**:

```bash
export DB_USER=postgres
export DB_PASSWORD=YOUR_PASSWORD
cd backend
mvn spring-boot:run
```

Alternatively edit `backend/src/main/resources/application.properties` to set `spring.datasource.password` (avoid committing your password). The default password placeholder is `postgres`.

Backend: http://localhost:8080/api/manufacturers

## 3. Run React frontend

In a **second terminal**, starting from the project root:

```bash
cd frontend
npm install
npm run dev
```

Open **http://localhost:5173**. The frontend forwards `/api` and `/uploads` to port 8080 through the Vite dev proxy.

## 4. Use the application

1. Open **Manufacturers** and add a brand (e.g. Toyota) with a logo.
2. Open **Vehicle Models** and add a model for that manufacturer (e.g. Camry).
3. Open **Car Inventory** and add a car: choose the model, enter year/price, and upload an image.
4. Search and filter cars; edit and delete records.
5. Check the `car_catalog` database in pgAdmin: `manufacturers`, `vehicle_models`, `cars`.

**Deletion order:** cars first, then vehicle models, then manufacturers. The API blocks deleting a parent record if children still reference it.

## Architecture

```text
frontend/src/App.jsx (React)
         | fetch REST API
backend/controller (HTTP endpoints)
         | calls
backend/service (business rules)
         | calls
backend/repository (Spring Data JPA)
         | SQL / Hibernate
PostgreSQL database
```

Backend also contains `entity/`, `dto/`, `config/`, and `exception/` packages.

## REST API

| Method | URL | Action |
| --- | --- | --- |
| GET | `/api/manufacturers` | List manufacturers |
| POST | `/api/manufacturers` | Create manufacturer |
| PUT | `/api/manufacturers/{id}` | Update manufacturer |
| DELETE | `/api/manufacturers/{id}` | Delete manufacturer |
| GET | `/api/models?manufacturerId=1` | List/filter models |
| POST | `/api/models` | Create model |
| PUT | `/api/models/{id}` | Update model |
| DELETE | `/api/models/{id}` | Delete model |
| GET | `/api/cars?q=bmw&manufacturerId=1&modelId=2` | Search/filter cars |
| POST | `/api/cars` | Create car |
| PUT | `/api/cars/{id}` | Update car |
| DELETE | `/api/cars/{id}` | Delete car |
| POST | `/api/uploads` | Multipart file upload (`file` field) |

GET by ID is also available for all three resources.

**Example** create manufacturer request:

```json
{"name":"Toyota","country":"Japan","logoUrl":null}
```

**Example** create vehicle model request:

```json
{"name":"Camry","manufacturerId":1}
```

**Example** create car request:

```json
{"modelId":1,"year":2022,"price":25000,"color":"Black","mileage":35000,"fuelType":"Petrol","transmission":"Automatic","imageUrl":null,"description":"Well maintained"}
```

## Evidence for submission

Create screenshots yourself after running the project: `frontend-cars.png`, `frontend-manufacturers.png`, `frontend-models.png`, `form-modal.png`, `pgadmin-tables.png`. Submit this complete project with these screenshots. The database isn't populated automatically: your own added records serve as proof of persistence.

## Demo / defense explanation

- **Controller** accepts HTTP requests and sends responses.
- **Service** applies rules and coordinates data operations.
- **Repository** uses JPA to read/write PostgreSQL.
- **Entity** defines the database tables and relationships (`Manufacturer` 1→many `VehicleModel`, `VehicleModel` 1→many `Car`).
- **DTO** validates data sent by React.
- **Config** provides CORS settings and image serving.
- Uploaded images are stored in the upload folder; only their paths are stored in database columns.
- React sends requests through `fetch` and refreshes data after CRUD operations.

## Notes

This is a local-development laboratory project, **not a production-hardened application**. It does not include login/permissions, production-grade migration scripts or automated orphaned-image cleanup. Do not expose the API to the public internet without adding authentication and authorization.
