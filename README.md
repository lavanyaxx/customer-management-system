# Customer Management System - Full Stack Application

A complete web application for managing customer information with a Spring Boot REST API backend and Angular 17 frontend.

**Application running at:** `http://localhost:8080`  
**Angular Frontend:** `http://localhost:8080` (served by Spring Boot)  
**Backend API:** `http://localhost:8080/customer-*`

## ✨ Features

### Frontend (Angular 17)
- **Dashboard:** Overview with statistics
- **Classifications:** Manage customer classification types
- **Customers:** View and manage customer records
- **Responsive Design:** Works on desktop and mobile
- **Real-time Updates:** Automatic refresh after operations
- **Error Handling:** User-friendly error messages

### Backend (Spring Boot)
- **RESTful APIs:** Complete CRUD operations
- **Data Validation:** Client and server-side validation
- **Error Handling:** Comprehensive exception handling
- **In-Memory Database:** H2 database for easy testing
- **Service Layer:** Business logic separation
- **Repository Pattern:** Clean data access

## Project Overview

The application manages customer data with the following modules:
- Customer Classifications
- Customer Details (main customer info)
- Customer Names (multiple names per customer)
- Customer Addresses (multiple addresses per customer)
- Customer Contact Information (phone, email, etc.)
- Customer Identification (ID types and values)
- Customer Proof of ID (proof documents)

## Project Architecture

```
src/main/java/com/example/first/
├── controller/          # HTTP endpoints
├── service/            # Business logic interfaces
├── service/impl/       # Business logic implementations
├── dto/                # Data Transfer Objects
├── entity/             # JPA Entity models
└── repository/         # Database access layer (JpaRepository)

src/main/resources/
└── application.properties  # Configuration
```

## How the Project Works

### 1. **Request Flow**
```
HTTP Request → Controller → Service → Repository → Database (H2 In-Memory)
                  ↓
            Return DTO as JSON
```

### 2. **Technology Stack**
- **Language:** Java 21
- **Framework:** Spring Boot 4.0.2
- **Database:** H2 (In-Memory, no MySQL needed)
- **Build Tool:** Maven (with mvnw wrapper)
- **ORM:** JPA/Hibernate
- **Validation:** Spring Validation (Jakarta)

### 3. **Database**
- Currently running with **H2 in-memory database** for easy testing
- Database auto-creates tables on startup
- Data persists for the session duration only
- Configuration in `src/main/resources/application.properties`

## Running the Project

### Prerequisites
- Java 21 installed
- Maven (included via `mvnw`)

### Start the Application
```bash
cd Flow-main
sh mvnw spring-boot:run -DskipTests
```

### Stop the Application
- Press `Ctrl+C` in the terminal

### Build Only (without running)
```bash
sh mvnw clean package -DskipTests
```

## Using the Angular Frontend

### Access the Application
1. Start the application with: `sh mvnw spring-boot:run -DskipTests`
2. Open browser: `http://localhost:8080`
3. Navigate using the menu bar

### Frontend Pages

#### 📊 Dashboard
- View statistics and system information
- Displays total customers and classifications count
- Technology stack information

#### 🏷️ Classifications
- **List:** View all customer classification types
- **Add:** Create new classification type
  - Type: e.g., "Status"
  - Value: e.g., "Premium"
  - Effective Date: Date when classification becomes effective
- **Delete:** Remove a classification type

#### 👥 Customers
- **List:** View all registered customers
- **Add:** Register new customer
  - Full Name
  - Gender (Male/Female/Other)
  - Date of Birth
  - Preferred Language
  - Status (Active/Inactive)
  - Country of Origin
  - Customer Type (Classification ID)
- **Delete:** Remove a customer record

### Frontend Architecture

```
src/main/angular/
├── main.ts                 # Application bootstrap
├── app.component.ts        # Root component
├── app.routes.ts           # Routing configuration
├── components/
│   ├── navbar/            # Navigation bar
│   ├── dashboard/         # Dashboard page
│   ├── classifications/   # Classifications management
│   └── customers/         # Customers management
├── services/
│   └── api.service.ts     # Backend API communication
├── index.html             # HTML template
└── styles.css             # Global styles
```

## API Endpoints

### Root Endpoint
```
GET /
```
Returns welcome message and list of available endpoints.

---

## 1. Customer Classification Type Endpoints

**Base Path:** `/customer-classification`

#### Create Classification Type
```bash
POST /customer-classification
Content-Type: application/json

{
  "classificationType": "INDIVIDUAL",
  "classificationValue": "Single Person Customer",
  "effectiveDate": "2024-01-01"
}
```
**Response:** Returns created classification with auto-generated ID.

#### Get Classification by ID
```bash
GET /customer-classification/{id}
```
**Response:** Returns classification type details.

#### Get All Classifications
```bash
GET /customer-classification
```
**Response:** Returns list of all classification types.

#### Update Classification
```bash
PUT /customer-classification/{id}
Content-Type: application/json

{
  "classificationType": "CORPORATE",
  "classificationValue": "Business Organization",
  "effectiveDate": "2024-01-15"
}
```

#### Delete Classification
```bash
DELETE /customer-classification/{id}
```
**Response:** No content (204).

---

## 2. Customer Detail Endpoints

**Base Path:** `/customer-detail`

**IMPORTANT:** Create a classification type first! Use the ID from classification in customerType field.

#### Create Customer
```bash
POST /customer-detail
Content-Type: application/json

{
  "customerFullName": "John Doe",
  "customerGender": "M",
  "customerType": 1,                    # Use classification ID
  "customerDateOfBirth": "1990-05-15",
  "customerPreferredLanguage": "English",
  "customerStatus": "ACTIVE",
  "customerCountryOfOrigin": "USA"
}
```
**Response:** Returns customer with auto-generated customerIdentifier (ID).

#### Get Customer by ID
```bash
GET /customer-detail/{id}
```

#### Get All Customers
```bash
GET /customer-detail
```

#### Update Customer
```bash
PUT /customer-detail/{id}
Content-Type: application/json

{
  "customerFullName": "John Doe Updated",
  "customerGender": "M",
  "customerType": 1,
  "customerDateOfBirth": "1990-05-15",
  "customerPreferredLanguage": "English",
  "customerStatus": "INACTIVE",
  "customerCountryOfOrigin": "USA"
}
```

#### Delete Customer
```bash
DELETE /customer-detail/{id}
```

---

## 3. Customer Names Endpoints

**Base Path:** `/customer-names`

#### Create Customer Name
```bash
POST /customer-names
Content-Type: application/json

{
  "customerIdentifier": 1,              # Customer ID
  "customerNameType": "LEGAL",
  "customerNameValue": "John Michael Doe",
  "effectiveDate": "2024-01-01"
}
```

#### Get Name by ID
```bash
GET /customer-names/{id}
```

#### Get All Customer Names
```bash
GET /customer-names
```

#### Update Name
```bash
PUT /customer-names/{id}
Content-Type: application/json

{
  "customerIdentifier": 1,
  "customerNameType": "NICKNAME",
  "customerNameValue": "Johnny",
  "effectiveDate": "2024-02-01"
}
```

#### Delete Name
```bash
DELETE /customer-names/{id}
```

---

## 4. Customer Address Endpoints

**Base Path:** `/customer-address`

#### Create Address
```bash
POST /customer-address
Content-Type: application/json

{
  "customerIdentifier": 1,              # Customer ID
  "customerAddressType": "HOME",
  "customerAddressValue": "123 Main St, New York, NY 10001",
  "effectiveDate": "2024-01-01"
}
```

#### Get Address by ID
```bash
GET /customer-address/{id}
```

#### Get All Addresses
```bash
GET /customer-address
```

#### Update Address
```bash
PUT /customer-address/{id}
Content-Type: application/json

{
  "customerIdentifier": 1,
  "customerAddressType": "OFFICE",
  "customerAddressValue": "456 Business Ave, New York, NY 10002",
  "effectiveDate": "2024-02-01"
}
```

#### Delete Address
```bash
DELETE /customer-address/{id}
```

---

## 5. Customer Contact Information Endpoints

**Base Path:** `/customer-contact-information`

#### Create Contact Info
```bash
POST /customer-contact-information
Content-Type: application/json

{
  "customerIdentifier": 1,              # Customer ID
  "customerContactType": "EMAIL",
  "customerContactValue": "john@example.com",
  "effectiveDate": "2024-01-01",
  "startDate": "2024-01-01",
  "endDate": "2025-12-31"
}
```

#### Get Contact Info by ID
```bash
GET /customer-contact-information/{id}
```

#### Get All Contact Information
```bash
GET /customer-contact-information
```

#### Update Contact Info
```bash
PUT /customer-contact-information/{id}
Content-Type: application/json

{
  "customerIdentifier": 1,
  "customerContactType": "PHONE",
  "customerContactValue": "+1-202-555-0173",
  "effectiveDate": "2024-02-01",
  "startDate": "2024-02-01",
  "endDate": "2025-12-31"
}
```

#### Delete Contact Info
```bash
DELETE /customer-contact-information/{id}
```

---

## 6. Customer Identification Endpoints

**Base Path:** `/customer-identification`

#### Create Identification
```bash
POST /customer-identification
Content-Type: application/json

{
  "customerIdentifier": 1,              # Customer ID
  "customerIdentificationType": "PASSPORT",
  "customerIdentificationItem": "AB123456",
  "effectiveDate": "2024-01-01"
}
```

#### Get Identification by ID
```bash
GET /customer-identification/{id}
```

#### Get All Identifications
```bash
GET /customer-identification
```

#### Update Identification
```bash
PUT /customer-identification/{id}
Content-Type: application/json

{
  "customerIdentifier": 1,
  "customerIdentificationType": "DRIVER_LICENSE",
  "customerIdentificationItem": "DL789012",
  "effectiveDate": "2024-02-01"
}
```

#### Delete Identification
```bash
DELETE /customer-identification/{id}
```

---

## 7. Customer Proof of ID Endpoints

**Base Path:** `/customer-proof-of-id`

#### Create Proof of ID
```bash
POST /customer-proof-of-id
Content-Type: application/json

{
  "customerIdentifier": 1,              # Customer ID
  "proofOfIdType": "PASSPORT_COPY",
  "proofOfIdValue": "POI_DOC_001",
  "effectiveDate": "2024-01-01",
  "startDate": "2024-01-01",
  "endDate": "2026-01-01"
}
```

#### Get Proof of ID by ID
```bash
GET /customer-proof-of-id/{id}
```

#### Get All Proofs of ID
```bash
GET /customer-proof-of-id
```

#### Update Proof of ID
```bash
PUT /customer-proof-of-id/{id}
Content-Type: application/json

{
  "customerIdentifier": 1,
  "proofOfIdType": "DRIVER_LICENSE_COPY",
  "proofOfIdValue": "POI_DOC_002",
  "effectiveDate": "2024-02-01",
  "startDate": "2024-02-01",
  "endDate": "2026-02-01"
}
```

---

---

## 🌐 How to Use Through Web Browser

### Step 1: Start the Application
```bash
cd Flow-main
sh mvnw spring-boot:run -DskipTests
```
Wait 30-60 seconds for the application to start.

### Step 2: Access the Web Interface
Open your browser and visit: **http://localhost:8080**

You'll see a web interface with these pages:

#### Dashboard
- Displays total count of customers
- Displays total count of classifications
- Shows system status
- Quick navigation buttons to other sections

#### Classifications Management
- **View all classifications** in a table
- **Add new classification:**
  - Enter Classification Type (e.g., "PREMIUM")
  - Enter Classification Value (e.g., "Premium Customer")
  - Select Effective Date
  - Click "Add Classification"
- **Delete classifications:** Click the Delete button next to each classification
- Success/error messages appear automatically

#### Customers Management
- **View all customers** in a table with columns:
  - Customer Name
  - Type
  - Gender
  - Date of Birth
  - Country
  - Status badge
  
- **Add new customer:**
  - Enter Full Name (required)
  - Select Type: Individual or Business (required)
  - Select Gender: M, F, or Other
  - Enter Date of Birth
  - Enter Country of Origin
  - Click "Add Customer"
- **Delete customers:** Click the Delete button next to each customer
- Real-time updates after each action

#### Navigation
- Use the navbar at the top to switch between pages
- Current page is highlighted in the navbar
- Mobile-responsive design for small screens

---

## 📱 How to Use Through Postman

### Step 1: Install Postman
1. Download from https://www.postman.com/downloads/
2. Install and open Postman

### Step 2: Create a New Request
1. Click "+" to create a new request
2. Choose HTTP method (GET, POST, PUT, DELETE)
3. Enter request URL
4. Add headers and body as needed
5. Click "Send"

### Step 3: API Workflow Example

#### 1. Create a Classification
**Method:** POST
**URL:** http://localhost:8080/customer-classification
**Headers:** Content-Type: application/json
**Body (JSON):**
```json
{
  "classificationType": "PREMIUM",
  "classificationValue": "Premium Customer",
  "effectiveDate": "2026-03-18"
}
```
**Click Send** → Copy the returned `classificationId` (e.g., ID: 1)

#### 2. Get All Classifications
**Method:** GET
**URL:** http://localhost:8080/customer-classification
**Click Send** → View all classifications in response

#### 3. Create a Customer
**Method:** POST
**URL:** http://localhost:8080/customer-detail
**Headers:** Content-Type: application/json
**Body (JSON):**
```json
{
  "customerFullName": "John Michael Doe",
  "customerGender": "M",
  "customerType": 1,
  "customerDateOfBirth": "1990-05-15",
  "customerPreferredLanguage": "English",
  "customerStatus": "ACTIVE",
  "customerCountryOfOrigin": "USA"
}
```
**Click Send** → Note the returned `customerIdentifier`

#### 4. Get All Customers
**Method:** GET
**URL:** http://localhost:8080/customer-detail
**Click Send** → View all customers

#### 5. Update a Customer
**Method:** PUT
**URL:** http://localhost:8080/customer-detail/1
**Headers:** Content-Type: application/json
**Body (JSON):**
```json
{
  "customerFullName": "John Michael Doe Updated",
  "customerGender": "M",
  "customerType": 1,
  "customerDateOfBirth": "1990-05-15",
  "customerPreferredLanguage": "English",
  "customerStatus": "INACTIVE",
  "customerCountryOfOrigin": "USA"
}
```

#### 6. Delete a Customer
**Method:** DELETE
**URL:** http://localhost:8080/customer-detail/1
**Click Send** → Customer deleted (response code: 204)

### API Response Examples

**GET /customer-classification (Success)**
```json
[
  {
    "classificationId": 1,
    "classificationType": "PREMIUM",
    "classificationValue": "Premium Customer",
    "effectiveDate": "2026-03-18"
  }
]
```

**POST /customer-detail (Success - 200)**
```json
{
  "customerIdentifier": 1,
  "customerFullName": "John Michael Doe",
  "customerGender": "M",
  "customerType": 1,
  "customerDateOfBirth": "1990-05-15",
  "customerPreferredLanguage": "English",
  "customerStatus": "ACTIVE",
  "customerCountryOfOrigin": "USA"
}
```

**DELETE (Success - 204)**
```
No content returned
```

---

## 🏗️ Architecture & How It Works

### Complete Request Flow
```
1. User Action
   ↓
2. Frontend (Angular)
   ├─ If using web browser: Sends HTTP request from UI
   └─ If using Postman: Manual HTTP request
   ↓
3. HTTP Request reaches Spring Boot Controller
   → @RestController handles the request
   ↓
4. Controller calls Service Layer
   → Business logic execution
   ↓
5. Service calls Repository Layer
   → JPA/Hibernate translates to SQL
   ↓
6. Repository interacts with Database
   → H2 in-memory database
   ↓
7. Response returned through layers
   ↓
8. JSON Response sent back to client
   ↓
9. Browser/Postman displays response
```

### Project Structure

```
Flow-main/
├── src/main/java/com/example/first/
│   ├── FirstApplication.java                    # Main entry point
│   ├── controller/
│   │   ├── CustomerDetailController.java
│   │   ├── CustomerClassificationTypeController.java
│   │   ├── CustomerNamesController.java
│   │   ├── CustomerAddressController.java
│   │   ├── CustomerContactInformationController.java
│   │   ├── CustomerIdentificationController.java
│   │   └── CustomerProofOfIdController.java
│   ├── service/
│   │   ├── (interface definitions)
│   │   └── impl/
│   │       ├── CustomerDetailServiceImpl.java
│   │       ├── CustomerClassificationTypeServiceImpl.java
│   │       └── (other implementations)
│   ├── entity/
│   │   ├── CustomerDetailEntity.java
│   │   ├── CustomerClassificationTypeEntity.java
│   │   └── (other entities)
│   ├── dto/
│   │   ├── CustomerDetailDTO.java
│   │   ├── CustomerClassificationTypeDTO.java
│   │   └── (other DTOs)
│   └── repository/
│       ├── CustomerDetailRepository.java
│       ├── CustomerClassificationTypeRepository.java
│       └── (other repositories)
│
├── src/main/angular/                            # Angular Frontend
│   ├── main.ts                                  # Bootstrap
│   ├── index.html                               # Main HTML
│   ├── app/
│   │   ├── app.component.ts                     # Root component
│   │   ├── app.routes.ts                        # Routes config
│   │   ├── services/
│   │   │   └── api.service.ts                   # HTTP service
│   │   └── components/
│   │       ├── dashboard/
│   │       ├── classifications/
│   │       ├── customers/
│   │       ├── navbar/
│   │       └── header/
│   └── styles.css                               # Global styles
│
├── src/main/resources/
│   └── application.properties                   # Configuration
│
├── pom.xml                                      # Maven config
├── package.json                                 # npm config
├── angular.json                                 # Angular CLI config
└── README.md                                    # This file
```

### Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Backend Framework** | Spring Boot | 4.0.2 |
| **Language (Backend)** | Java | 21 |
| **Frontend Framework** | Angular | 17 |
| **Language (Frontend)** | TypeScript | ~5.2 |
| **Database** | H2 (In-Memory) | - |
| **Build Tool** | Maven | (via mvnw) |
| **ORM** | JPA/Hibernate | (Spring Boot) |
| **Web Server** | Tomcat | (Spring Boot) |
| **UI Framework** | Bootstrap | 5.3.0 |

### Design Pattern: Layered Architecture

```
┌──────────────────────────────────────┐
│   Presentation Layer (Controller)    │  ← HTTP Endpoints
├──────────────────────────────────────┤
│      Business Logic Layer (Service)  │  ← Business Rules
├──────────────────────────────────────┤
│    Data Access Layer (Repository)    │  ← Database Operations
├──────────────────────────────────────┤
│           Database (H2)              │  ← Data Storage
└──────────────────────────────────────┘
```

Each request flows through all layers:
- **Controller** receives HTTP request and validates input
- **Service** implements business logic and transactions
- **Repository** provides database access via JPA
- **DTO** converts internal model to/from JSON for API

---

## 🔑 Key Concepts for Your Exam

### 1. REST API Principles
- **GET:** Retrieve data (idempotent, safe)
- **POST:** Create new resource
- **PUT:** Update existing resource
- **DELETE:** Remove resource

### 2. HTTP Status Codes
- **200 OK:** Request succeeded, response body included
- **201 Created:** New resource created
- **204 No Content:** Success, no response body (DELETE)
- **400 Bad Request:** Invalid request data
- **404 Not Found:** Resource doesn't exist
- **500 Internal Server Error:** Server error

### 3. Request/Response Format
All data is in JSON format:
```json
{
  "field1": "value1",
  "field2": "value2",
  "dateField": "YYYY-MM-DD"
}
```

### 4. Spring Boot Annotations
- `@RestController` - Marks class as REST controller
- `@RequestMapping` - Maps HTTP requests to methods
- `@GetMapping` - Maps GET requests
- `@PostMapping` - Maps POST requests
- `@PutMapping` - Maps PUT requests
- `@DeleteMapping` - Maps DELETE requests
- `@Service` - Marks class as service
- `@Repository` - Marks interface as repository
- `@Entity` - Maps class to database table
- `@Transactional` - Manages database transactions

### 5. Browser vs Postman
- **Browser:** Good for viewing GET responses, can't easily send POST/PUT/DELETE
- **Postman:** Complete API testing tool, can send all HTTP methods with custom headers/body

---

## How to Make Edits

### 1. **Adding a New Endpoint**

**Step 1:** Create/Update the DTO (Data Transfer Object)
```java
// src/main/java/com/example/first/dto/YourDTO.java
public class YourDTO {
    private Long id;
    private String name;
    // getters and setters
}
```

**Step 2:** Create/Update the Entity
```java
// src/main/java/com/example/first/entity/YourEntity.java
@Entity
@Table(name = "YourTable")
public class YourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    // getters and setters
}
```

**Step 3:** Create/Update Repository
```java
// src/main/java/com/example/first/repository/YourRepository.java
public interface YourRepository extends JpaRepository<YourEntity, Long> {
}
```

**Step 4:** Create Service Interface
```java
// src/main/java/com/example/first/service/YourService.java
public interface YourService {
    YourDTO create(YourDTO dto);
    YourDTO getById(Long id);
    List<YourDTO> getAll();
    YourDTO update(Long id, YourDTO dto);
    void delete(Long id);
}
```

**Step 5:** Create Service Implementation
```java
// src/main/java/com/example/first/service/impl/YourServiceImpl.java
@Service
@Transactional
public class YourServiceImpl implements YourService {
    // implementation
}
```

**Step 6:** Create Controller
```java
// src/main/java/com/example/first/controller/YourController.java
@RestController
@RequestMapping("/your-endpoint")
public class YourController {
    private final YourService service;
    // Controller methods
}
```

### 2. **Modifying Existing Endpoints**

1. **Update the DTO** in `src/main/java/com/example/first/dto/`
2. **Update the Entity** in `src/main/java/com/example/first/entity/`
3. **Update Service Implementation** in `src/main/java/com/example/first/service/impl/`
4. **Update Controller** in `src/main/java/com/example/first/controller/`
5. Rebuild and restart

### 3. **Rebuild and Redeploy**

```bash
# Stop the current run (Ctrl+C)

# Rebuild
sh mvnw clean package -DskipTests

# Restart
sh mvnw spring-boot:run -DskipTests
```

## Testing with cURL or Postman

### Example: Create and Fetch Customer Flow

**Step 1: Create Classification Type**
```bash
curl -X POST http://localhost:8080/customer-classification \
  -H "Content-Type: application/json" \
  -d '{
    "classificationType": "INDIVIDUAL",
    "classificationValue": "Personal Customer",
    "effectiveDate": "2024-01-01"
  }'
```

**Step 2: Create Customer (use ID from Step 1)**
```bash
curl -X POST http://localhost:8080/customer-detail \
  -H "Content-Type: application/json" \
  -d '{
    "customerFullName": "Jane Smith",
    "customerGender": "F",
    "customerType": 1,
    "customerDateOfBirth": "1995-08-20",
    "customerPreferredLanguage": "English",
    "customerStatus": "ACTIVE",
    "customerCountryOfOrigin": "Canada"
  }'
```

**Step 3: Fetch Customer**
```bash
curl -X GET http://localhost:8080/customer-detail/1
```

## Project Structure Summary

| Layer | Purpose | Location |
|-------|---------|----------|
| **Controller** | HTTP endpoints, request mapping | `controller/` |
| **Service** | Business logic, validations | `service/` & `service/impl/` |
| **DTO** | Data transfer objects for API | `dto/` |
| **Entity** | JPA database models | `entity/` |
| **Repository** | Database access (JpaRepository) | `repository/` |

## Important Notes

- **Dates Format:** Always use `yyyy-MM-dd` format for dates
- **Required Fields:** All fields marked with `@NotNull` or `@NotBlank` are required
- **Customer Dependencies:** Most entities require a valid `customerIdentifier` (must exist in Customer Detail table)
- **Database:** H2 is in-memory, so data is lost when the app stops
- **Port:** Application runs on port 8080 (configurable in `application.properties`)

## Troubleshooting

### Port Already in Use
```bash
# Kill existing process on port 8080
lsof -i :8080
kill -9 <PID>
```

### Build Errors
```bash
# Clean and rebuild
sh mvnw clean install -DskipTests
```

### Database Issues
- Delete the application and restart (H2 will recreate tables)
- Check `application.properties` for database URL

## Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [H2 Database](https://www.h2database.com/)
- [RESTful API Best Practices](https://restfulapi.net/)

## Python Analytics & Reports (Labs 10 & 11)

The application includes supplemental Python scripts to generate offline CSV reports and visual analytic charts by querying the local database natively, utilizing `pandas` and `matplotlib`. All scripts and generated assets exist inside the `python_analytics/` directory.

### Report Generation (Lab 10)
**Script:** `python_analytics/lab10_reports.py`
Connects directly to the backend database to generate local CSV aggregations encompassing:
1. `language_age_group_report.csv` - Classifies customers globally by preferred language and grouped by granular age brackets.
2. `gender_ratio_report.csv` - Outputs the ratio and explicit aggregated counts of male vs female customers.
3. `country_language_distribution.csv` - Segments and calculates global clusters of languages preferred filtered by customer country of origin.

### Data Visualisation (Lab 11)
**Script:** `python_analytics/lab11_visualisation.py`
Generates high-resolution `.png` file visualisations measuring metric trends:
1. `address_types_bar_chart.png` - Standard bar chart determining frequency counts of different registered address types.
2. `active_vs_expired_records.png` - Visual differentiation tracking currently active accounts against expired status limits based on `end_date` restrictions.
3. `preferred_languages_horizontal_bar.png` - Legible Horizontal Bar Chart establishing ranking priorities of explicitly stated customer languages.

**How to run locally:**
```bash
cd python_analytics
pip3 install mysql-connector-python pandas matplotlib
python3 lab10_reports.py
python3 lab11_visualisation.py
```

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/xLeHmdin)
