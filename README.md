Step 1: Basic REST API with In-Memory Storage

Implemented TaskController, UserController, and NotificationController with required endpoints.
Tasks are stored in-memory using a List or Map (no database).
Tasks support creation, soft-delete (marked as deleted), and retrieval (all and pending).
Users can register and "login" to see their tasks and notifications.
Notifications support GET endpoints for all and pending notifications.
All endpoints return JSON responses with proper HTTP codes.
Classes are separated per OOP best practices.
Step 2: Unit Tests
Introduced unit testing using JUnit.
Each controller and service has dedicated tests to ensure correctness.
Business logic and REST endpoints are covered.
Tests can be run locally or integrated with CI pipelines.
Step 3: In-Memory Database (H2)
Replaced in-memory data structures with H2 in-memory database.
Configured Spring Data JPA for data access.
Implemented repository interfaces for tasks, users, and notifications.
All storage/retrieval operations now use H2 via JPA repositories.
Step 4: Docker Support
Added Dockerfile to containerize the Spring Boot application.
Provided docker-compose.yml to orchestrate the app and H2 database together.
Application can be built and run as a Docker container.
Tested the system in a containerized environment.
Step 5: Switch to PostgreSQL
Replaced H2 with PostgreSQL as the main database.
Updated application.properties for PostgreSQL connection.
Used Flyway for automated database migrations.
Added/updated tests using Mockito to mock database interactions.
Step 6: Implement Caching (Redis)
Integrated Redis with Spring Cache abstraction.
Task retrieval endpoints are now cached for improved performance.
Configured cache expiry (timeouts).
On cache miss, data is fetched from PostgreSQL and cached.
Step 7: Implement Messaging (Kafka)
Integrated Kafka as a message broker.
Published a message when new tasks are created.
Reworked Notification Service to receive updates only from the message broker.
Created message listeners for asynchronous processing.
Step 8: Scheduling & Async Tasks
Used @Scheduled to periodically check and update overdue tasks.
Implemented @Async for background task processing (non-blocking).
Ensured scheduled and async tasks work reliably in production and container environments
