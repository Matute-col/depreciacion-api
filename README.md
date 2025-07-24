# 📉 Depreciation API

A simple and efficient **Spring Boot REST API** designed to calculate and manage asset depreciation using straight-line depreciation method. Ideal for accounting systems, ERP integration, or educational projects.

---

## 🚀 Features

- 📌 Register assets with their initial cost and useful life
- 🧮 Calculate depreciation over time
- 📅 Automatically determine remaining value per year
- 📤 RESTful endpoints ready for integration
- 🛡️ Secured and well-structured using Spring Boot best practices

---

## 🛠️ Tech Stack

- ☕ Java 23
- 🌱 Spring Boot 3
- 🗄️ Spring Data JPA
- 🧪 Spring Validation
- 🐘 PostgreSQL or MySQL
- 🌐 RESTful Web Services

---

## 📂 Project Structure

```bash
src/
├── main/
│   ├── java/
│   │   └── com/yourproject/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── service/
│   │       └── repository/
│   └── resources/
│       ├── application.properties
│       └── static/
🧪 Sample Endpoint
http
POST /api/assets
{
  "name": "Laptop Dell",
  "initialValue": 2000,
  "usefulLifeYears": 5
}
http
GET /api/assets/depreciation/{id}
Returns yearly depreciation breakdown.

🔧 How to Run Locally
bash
# 1. Clone the repository
git clone https://github.com/Matute-col/depreciacion-api.git

# 2. Navigate into the project folder
cd depreciacion-api

# 3. Import into your IDE (e.g. IntelliJ or Eclipse)

# 4. Configure your DB credentials in application.properties

# 5. Run the app
./mvnw spring-boot:run
📌 TODO / Coming Soon
🔐 Authentication and Authorization (Spring Security)

🧾 PDF export for depreciation reports

📊 Admin dashboard (optional front-end)

🤝 Contributing
Pull requests are welcome. Feel free to fork the project and suggest improvements!

📄 License
This project is licensed under the MIT License.

👤 Author
Mateo Bernal
📧 mateobernalcardona@gmail.com



