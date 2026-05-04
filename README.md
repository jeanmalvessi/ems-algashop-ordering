# ordering

Microservice responsible for managing customer orders, shopping carts, and checkout flows in the [AlgaShop](https://github.com/jeanmalvessi/ems-algashop-meta) platform.

Built using a **purist DDD** approach with Hexagonal Architecture (Ports & Adapters), fully isolating the domain model from frameworks, persistence, and external integrations.

## Responsibilities

- Customer registration and management
- Shopping cart creation and item management
- Checkout (from cart) and Buy Now (direct purchase) flows
- Order lifecycle management
- Integration with `product-catalog` for product data
- Integration with RapiDex (shipping provider)

## Architecture

- **Domain Layer:** Aggregates (`Order`, `ShoppingCart`, `Customer`), Value Objects (`Money`, `Address`, `Email`, `Phone`, `Quantity`, `ZipCode`, `Document`), Domain Events, Domain Services
- **Application Layer:** Use-case services (`CheckoutApplicationService`, `BuyNowApplicationService`, customer/order/cart management and query services)
- **Ports & Adapters:** Input/output port interfaces decoupling the domain from infrastructure
- **Infrastructure Layer:** JPA persistence adapters, REST clients, cache configuration

## Tech Stack

- **Java 25**, Spring Boot 4.0.1
- **Spring Data JPA** + PostgreSQL 17 (persistence)
- **Flyway** (database migrations)
- **Spring Cache** + Redis (server-side caching — Cache-Aside and Write-Through patterns)
- **Spring REST Client** (integration with `product-catalog` and RapiDex shipping)
- **Spring Cloud Circuit Breaker** (resilience and retries)
- **Spring Boot Actuator** (monitoring and health checks)
- **Spring Cloud Contract 5.0.0** (consumer-driven contract testing)
- **Testcontainers** (PostgreSQL integration tests)
- **WireMock** (mocks for external service calls in tests)
- **Hypersistence TSID** (type-safe identifiers)
- **ModelMapper** (entity/DTO mapping)
- **Commons Validator** (input validation)
- **Lombok**

## API

Base path: `/api/v1`

| Method | Path | Description |
|--------|------|-------------|
| POST | `/orders` | Create order (Buy Now or from cart) |
| GET | `/orders/{orderId}` | Get order by ID |
| GET | `/orders` | List/filter orders with pagination |
| POST | `/shopping-carts` | Create shopping cart |
| GET/PUT/DELETE | `/shopping-carts/{cartId}` | Manage cart |
| POST | `/customers` | Register customer |
| GET | `/customers/{customerId}` | Get customer |

## Running

```bash
./gradlew bootRun
```

Default port: **8080** (development profile)

Database: PostgreSQL `ordering` database on `localhost:5432`
Cache: Redis on `localhost:6379` (db 1)