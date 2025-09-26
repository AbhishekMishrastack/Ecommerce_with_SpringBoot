Ecommerce Backend API

Features

Product Management - Full CRUD operations for products with category support

Shopping Cart - Complete cart functionality with item management

Order Processing - Checkout system with Stripe payment integration

User Management - User profiles, addresses, and account management

Database Migrations - Flyway for schema version control

RESTful API - Clean, well-structured REST endpoints

Input Validation - Comprehensive request validation with custom annotations

Error Handling - Global exception handling with consistent error responses


🛠️ Tech Stack
Java 17 - Primary programming language

Spring Boot 3.5.4 - Application framework

PostgreSQL - Relational database

Flyway - Database migration tool

Stripe API - Payment processing

MapStruct - Object mapping

Lombok - Code reduction

Maven - Dependency management

📋 Prerequisites
Before running this application, ensure you have:

Java 17 or higher

Maven 3.6+

PostgreSQL database

Stripe account (for payment processing)

⚙️ Configuration
Environment Variables
Create a .env file in the root directory with the following variables:

env
DB_PASSWORD=your_postgres_password
STRIPE_SECRET_KEY=your_stripe_secret_key
STRIPE_WEBHOOK_SECRET_KEY=your_stripe_webhook_secret
Database Setup
Create a PostgreSQL database named ecom_store_db

Update the database connection details in application-dev.yml if needed

🚀 Running the Application
Using Maven
bash
# Clone the repository
git clone <repository-url>
cd Ecommerce_with_SpringBoot

# Install dependencies
mvn clean install

# Run the application
mvn spring-boot:run
Using the packaged JAR
bash
mvn clean package
java -jar target/Ecommerce-with-1.0.0.jar

The application will start on http://localhost:8080

📚 API Documentation
Once the application is running, API documentation is available at:

Swagger UI: http://localhost:8080/swagger-ui.html

OpenAPI JSON: http://localhost:8080/v3/api-docs

Authentication Endpoints
POST /api/v1/auth/users/register - Register a new user

POST /api/v1/auth/users/authenticate - Login 


📁 API Endpoints
Products
GET /api/v1/products - Get all products (optional category filter)

GET /api/v1/products/{id} - Get a specific product

POST /api/v1/products - Create a new product (Admin only)

PUT /api/v1/products/{id} - Update a product (Admin only)

DELETE /api/v1/products/{id} - Delete a product (Admin only)

Cart

POST /api/v1/carts - Create a new cart

GET /api/v1/carts/{cartId} - Get cart details

POST /api/v1/carts/{cartId}/items - Add item to cart

PUT /api/v1/carts/{cartId}/items/{productId} - Update cart item quantity

DELETE /api/v1/carts/{cartId}/items/{productId} - Remove item from cart

DELETE /api/v1/carts/{cartId}/items - Clear all items from cart

Orders
GET /api/v1/orders - Get user's order history

GET /api/v1/orders/{orderId} - Get specific order details

POST /api/v1/checkout - Checkout cart and create order

Users
GET /api/v1/users - Get all users (Admin only)

GET /api/v1/users/{id} - Get user by ID

PUT /api/v1/users/{id} - Update user information

DELETE /api/v1/users/{id} - Delete user

POST /api/v1/users/{id}/change-password - Change user password

Admin
GET /api/v1/admin/hello - Admin test endpoint

💳 Payment Integration
The application integrates with Stripe for payment processing:

Creates checkout sessions for orders

Handles webhook events for payment status updates

Supports successful and failed payment scenarios

Webhook Setup
Configure your Stripe webhook to point to:

text
POST /api/v1/checkout/webhook
🗃️ Database Schema
The application uses Flyway migrations to manage database schema:

V1 - Initial schema (users, products, categories, addresses, profiles)

V2 - Make category ID auto-increment

V3 - Cart and cart items tables

V4 - Add role column to users

V5 - Orders and order items tables

🧪 Testing
Run tests with:

bash
mvn test
🐛 Troubleshooting
Common Issues
Database connection errors: Ensure PostgreSQL is running and credentials are correct


Stripe errors: Check Stripe API keys are correctly configured

Migration errors: Ensure Flyway has permissions to modify database schema

Logs
Check application logs for detailed error information. Logging is configured to show SQL statements in development mode.

📦 Deployment
Production Deployment
Set the active profile to prod in application.yml

Configure production database connection in application-prod.yml

Set environment variables for production

Build the application with mvn clean package -DskipTests

Deploy the generated JAR file

Docker (Example)
dockerfile

FROM openjdk:17-jdk-slim
COPY target/Ecommerce-with-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
🤝 Contributing
Fork the repository

Create a feature branch (git checkout -b feature/amazing-feature)

Commit your changes (git commit -m 'Add amazing feature')

Push to the branch (git push origin feature/amazing-feature)

Open a Pull Request

📄 License
This project is licensed under the Apache License 2.0 - see the LICENSE file for details.

🆘 Support
If you encounter any issues or have questions, please open an issue on the GitHub repository.

🔜 Future Enhancements
Email notifications

Inventory management


Production environments:
**SPRING_PROFILES_ACTIVE=prod
PROD_DB_URL=
PROD_DB_USERNAME=
PROD_DB_PASSWORD=
<!-- In future , we will implement JWT_TOKEN SECURITY,THATS WHY WE ARE USING HERE JWT_SECRET -->
JWT_SECRET=
<!-- just for in future use -->
STRIPE_SECRET_KEY=

STRIPE_WEBHOOK_SECRET_KEY=**