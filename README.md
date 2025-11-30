
# 📄 Documentation

## **1. Setup Instructions**

1. Install **Java 17**, **Maven**, **Docker**, and **Docker Compose**.
2. Build each microservice:

   ```bash
   mvn clean package -DskipTests
   ```
3. Start all services using Docker:

   ```bash
   docker-compose up -d
   ```
4. Make sure PostgreSQL schemas exist:

   ```sql
   CREATE SCHEMA customer_schema;
   CREATE SCHEMA driver_schema;
   CREATE SCHEMA auth_schema;
   ```

---

## **2. How to Run Locally (Without Docker)**

1. Start PostgreSQL locally.
2. Update `application.properties`:

   ```
   jdbc:postgresql://localhost:5432/rides_db
   ```
3. Run each microservice:

   ```bash
   mvn spring-boot:run
   ```

### Local Service URLs

* **Customer Service:** [http://localhost:8081](http://localhost:8081)
* **Driver Service:** [http://localhost:8082](http://localhost:8082)
* **Gateway / Auth Service:** [http://localhost:8080](http://localhost:8080)

---

## **3. How Authentication Works (Session-Based)**

Authentication is implemented using **Spring Security** and **HttpSession**

### 🔐 Login Flow

1. Client sends a POST request to `/auth/login` with `username` and `password`.
2. Backend creates an authentication token:

   ```java
   UsernamePasswordAuthenticationToken authToken =
       new UsernamePasswordAuthenticationToken(
           request.getUsername(),
           request.getPassword()
       );
   ```
3. Spring Security authenticates the user:

   ```java
   Authentication authentication = authenticationManager.authenticate(authToken);
   ```
4. A new `SecurityContext` is created and stored in the session:

   ```java
   SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
   securityContext.setAuthentication(authentication);
   SecurityContextHolder.setContext(securityContext);

   HttpSession session = httpRequest.getSession(true);
   session.setAttribute(
       HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
       securityContext
   );
   ```
5. The authenticated session is maintained using the `JSESSIONID` cookie.
6. The login endpoint returns:

   ```java
   LoginResponseDto(
       id,
       username,
       role
   )
   ```

---

## **4. Endpoint List**

### 🔐 Gateway / Auth Service

| Method | Endpoint         | Description                  |
| ------ | ---------------- | ---------------------------- |
| POST   | `/auth/register` | Create a new user            |
| POST   | `/auth/login`    | Login (creates user session) |

---

### 👥 Customer Service


| Method | Endpoint               | Description            |
|--------|-------------------------|------------------------|
| POST    | `/customer/create`         | Create new request  |
| GET   | `/customer/get-history`         | get all requests 


---

### 🚗 Driver Service

| Method | Endpoint           | Description            |
| ------ | ------------------ | ---------------------- |
| POST    | `/driver/update-availability`      | update driver availability  |
| POST   | `/driver/assign-ride?requestId=` | Assign driver to a ride  |
| GET   | `/driver/get-history`   | Driver requests history |
| GET    | `driver/get-unchosen-rides`     | Get all unchosen rides       |

