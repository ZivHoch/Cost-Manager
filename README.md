# Cost Manager

## About

CostManager is a Java Swing–based MVC desktop application that lets users register, log in, and perform full CRUD operations on expense items and categories stored in a MySQL database via JDBC.

## Features

* User authentication (register, login, logout)
* Create, read, update, and delete expense entries
* Create, read, update, and delete expense categories
* Filter and search expenses by date, category, or amount
* Summary reports of total spending per category or time period
* Clean MVC architecture for maintainability

## Technologies

* **Language:** Java SE 8+
* **UI:** Java Swing
* **Database:** MySQL
* **Connectivity:** JDBC
* **Build Tool:** Maven or Gradle

## Prerequisites

* Java Development Kit (JDK) 8 or higher
* MySQL Server (community edition)
* Maven (if using Maven) or Gradle (if using Gradle)

## Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/ZivHoch/Cost-Manager.git
   cd Cost-Manager
   ```

2. **Configure the database**

   * Create a new MySQL database, e.g., `cost_manager_db`.
   * Execute the SQL script located at `db/schema.sql` (if provided) to create the tables.

3. **Configure application settings**

   * Copy `src/main/resources/config.example.properties` to `config.properties`:

     ```properties
     db.url=jdbc:mysql://localhost:3306/cost_manager_db
     db.user=YOUR_DB_USERNAME
     db.password=YOUR_DB_PASSWORD
     ```

4. **Build the project**

   * **Maven**:

     ```bash
     mvn clean package
     ```
   * **Gradle**:

     ```bash
     gradle clean build
     ```

## Running the Application

After a successful build, run the JAR file:

```bash
java -jar target/CostManager.jar
```

## Project Structure

```plaintext
Cost-Manager/
├── db/
│   └── schema.sql             # SQL script for database schema
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/zivhoch/costmanager/
│   │   │       ├── controller/    # Business logic and event handling
│   │   │       ├── model/         # Data models and JDBC access
│   │   │       └── view/          # Swing UI components
│   │   └── resources/
│   │       └── config.example.properties
│   └── test/                     # Unit and integration tests
├── pom.xml or build.gradle      # Build configuration
└── README.md                    # Project documentation
```

## Contributing

Contributions are welcome! Please fork the repository, create a new branch for your feature or bugfix, and submit a pull request. Ensure your code follows the existing style and includes appropriate tests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

Created by Ziv Hochman. For support or questions, please open an issue or contact \[[ziv.h252@gmail.com](mailto:ziv.h252@gmail.com)].
