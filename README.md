# ShopService

A Java console application designed to manage product inventories, construct customer orders, and maintain order histories. The project demonstrates core Object-Oriented Programming (OOP) concepts, immutable record structures, CSV file reading, and interactive CLI management.

---

## Features

- **Product Catalog Management**: Loads products and stock levels dynamically from a CSV file (`products.csv`).
- **Interactive Console Interface (CLI)**: Easy navigation with ANSI-colored terminal output.
- **Order Management**:
  - Browse available products and quantities.
  - Create new orders with flexible quantities.
  - View order summaries with auto-calculated total prices (`BigDecimal`).
  - Update or modify existing orders.
  - Cancel/delete orders with automatic inventory replenishment.
- **Flexible Repository Architecture**: Polymorphic storage design using the `OrderRepo` interface (supports list-based and map-based repositories).

---

## Tech Stack

- **Language**: Java 17+ (utilizing Java Records)
- **Build Tool**: Maven / Gradle (or standard Java project structure)
- **Data Persistence**: In-memory storage with CSV data loader (`products.csv`)

---

## Class Architecture & Diagram
![Class Diagram](images/class_diagram.png)
