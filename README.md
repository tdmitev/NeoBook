# 📘 NeoBook

**NeoBook** is a **web-based electronic school diary system** built on a modern **microservice architecture**.  
It integrates three Spring Boot microservices — `user-service`, `school-service`, and `notebook-service` — along with a centralised `Spring Cloud Gateway`.  
The platform provides a complete digital environment for managing students, teachers, parents, classes, grades, and absences — all secured and synchronised through **Keycloak** authentication.  
Every component runs in a unified **Docker Compose** stack, backed by **two PostgreSQL databases** and **one MongoDB instance**, ensuring scalable, modular, and easily deployable development.


---

## 🧠 Overview

Keycloak provides centralized authentication and authorization with predefined realm roles:

| Role | Description |
|------|--------------|
| `ROLE_STUDENT` | Student access and diary view |
| `ROLE_TEACHER` | Teacher panel and grading access |
| `ROLE_PARENT` | Parent portal and monitoring |
| `ROLE_HEADMASTER` | School management and reporting |
| `ROLE_ADMIN` | Full system administration |

Each backend service is registered as a **Keycloak client**, ensuring that JWT tokens include the appropriate `roles` claims.

---

## ⚙️ Architecture

| Directory | Description |
|------------|-------------|
| 📦 **api-gateway/** | Spring Cloud Gateway — entry point handling routing and JWT validation |
| 👤 **user-service/** | Manages users — students, teachers, and parents; integrates with Keycloak and PostgreSQL |
| 🏫 **school-service/** | Handles schools, classes, schedules, and subjects (PostgreSQL) |
| 📝 **notebook-service/** | Reactive service for grades and absences (MongoDB + WebFlux) |
| 🐳 **neobook-deploy/** | Docker Compose orchestration for all containers |
| 💻 **frontend/** | Angular 19 application (NeoBook UI) with Keycloak SSO |

---

## 🧩 Microservices

### 👤 User-Service
**Spring Boot 3.4**, using:
- **Spring Web**, **Spring Data JPA**, **MapStruct**, **Keycloak Admin Client**
- Database: **PostgreSQL**
- Acts as an **OAuth2 Resource Server** with method-level security (`@PreAuthorize`).

#### Key Features
- REST controllers for managing **students**, **teachers**, and **parents**
- Keycloak role assignment during registration
- Cross-service sync with **school-service** via `WebClient`
- Entity relations for **student–parent** and **class assignment**
- MapStruct mappers extracting data directly from Keycloak

---

### 🏫 School-Service
**Spring Boot 3.4** microservice managing educational structure.

#### Stack
- **Spring Data JPA**, **MapStruct**, **Jakarta Validation**
- Database: **PostgreSQL**

#### Key Features
- CRUD for **schools**, **classes**, **subjects**, **specialities**, and **schedules**
- Aggregated models combining teachers and students
- Synchronization with **user-service**
- REST endpoints for class creation, subject assignments, and timetables

---

### 📝 Notebook-Service
**Spring Boot 3.5 (Reactive)** — optimized for diary management and scalability.

#### Stack
- **Spring WebFlux**, **Reactive MongoDB**
- Database: **MongoDB**

#### Key Features
- Reactive API (Flux/Mono) for **grades and absences**
- JWT validation from Keycloak
- Cross-service enrichment via reactive `WebClient`
- Fully asynchronous, non-blocking design

---

## 🌐 API Gateway
**Spring Cloud Gateway** serves as the unified entry point.

#### Responsibilities
- Handles **OAuth2 login** (PKCE flow) with Keycloak
- Validates **JWTs** for every route
- Forwards traffic to services via secure routing:

| Path Prefix | Target Service | Description |
|--------------|----------------|--------------|
| `/users/**` | `user-service` | User management APIs |
| `/school/**` | `school-service` | School and class APIs |
| `/notebook/**` | `notebook-service` | Diary APIs |

---

## 💻 Front-End — NeoBook UI

Built with **Angular 19** and **Angular Material**.

#### Highlights
- Integration with **Keycloak Angular** for PKCE-based SSO
- `AuthService` wraps Keycloak JS client
- `RoleGuard` protects routes based on user roles
- Views for:
  - **Students** — grades & absences
  - **Teachers** — class management
  - **Parents** — student overview
  - **Headmasters/Admins** — full control and insights

Client-side services communicate with the REST APIs of all microservices.

---

## 🐳 Containerization & Deployment

Each backend module uses a **multi-stage Dockerfile**:

1. **Build Stage** — Gradle compiles and packages the JAR  
2. **Runtime Stage** — lightweight JDK image runs the final build

### Docker Compose Services
| Service | Purpose |
|----------|----------|
| `keycloak` | Identity provider issuing JWTs |
| `db-user`, `db-school` | PostgreSQL databases |
| `mongo` | MongoDB for notebook-service |
| `user-service`, `school-service`, `notebook-service` | Business logic microservices |
| `api-gateway` | Entry point handling routing and token validation |

### Run the stack
```bash
cd neobook-deploy
docker compose up --build
