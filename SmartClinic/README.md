# 🏥 SmartClinic — Hospital Appointment & Patient Management System

A full-stack Java web application built with **Spring Boot**, **Spring MVC**, **JDBC**, **MySQL**, and **Bootstrap**.

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java 17, Spring Boot 3.2, Spring MVC |
| REST API | Spring REST (@RestController) |
| Database Access | JDBC (JdbcTemplate) |
| Database | MySQL |
| Frontend | HTML5, CSS3, Bootstrap 5, Thymeleaf |
| Testing | JUnit 5, Mockito |
| Logging | Log4j2 |
| Build Tool | Maven |

---

## 📁 Project Structure

```
SmartClinic/
├── src/main/java/com/smartclinic/
│   ├── SmartClinicApplication.java   ← Spring Boot entry point
│   ├── model/                        ← Patient, Doctor, Appointment POJOs
│   ├── dao/                          ← JDBC database access layer
│   ├── service/                      ← Business logic layer
│   └── controller/                   ← Spring MVC + REST controllers
├── src/main/resources/
│   ├── application.properties        ← DB config
│   └── templates/                    ← Thymeleaf HTML pages
├── src/test/java/com/smartclinic/   ← JUnit + Mockito tests
├── schema.sql                        ← MySQL tables + sample data
└── pom.xml                           ← Maven dependencies
```

---

## ⚙️ Setup Instructions

### Step 1 — Prerequisites
Install these before running:
- Java 17+ → https://adoptium.net
- Maven → https://maven.apache.org
- MySQL 8.0+ → https://dev.mysql.com/downloads/

### Step 2 — Database Setup
Open MySQL Workbench or terminal and run:
```sql
source /path/to/SmartClinic/schema.sql
```
Or copy-paste the contents of `schema.sql` into MySQL Workbench and execute.

### Step 3 — Configure Database Password
Open `src/main/resources/application.properties` and update:
```properties
spring.datasource.password=your_mysql_password_here
```

### Step 4 — Run the Application
```bash
cd SmartClinic
mvn spring-boot:run
```
Then open your browser: **http://localhost:8080**

---

## 🧪 Running Tests

```bash
mvn test
```
Expected output: 12 tests, all passing.

---

## 🌐 REST API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | /api/appointments | Get all appointments |
| GET | /api/appointments/{id} | Get appointment by ID |
| POST | /api/appointments | Book new appointment |
| PUT | /api/appointments/{id}/cancel | Cancel appointment |
| PUT | /api/appointments/{id}/complete | Complete appointment |
| DELETE | /api/appointments/{id} | Delete appointment |
| GET | /api/patients | Get all patients |
| POST | /api/patients | Register new patient |

Test with browser or Postman.

---

## 📤 Pages

| URL | Page |
|-----|------|
| / | Home with stats |
| /patients | Register & view patients |
| /appointments | Book & manage appointments |
| /dashboard | Admin overview |

---

## 👩‍💻 Built By
Himaja D — Java Full Stack Developer 
