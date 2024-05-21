# Medical Center Management Application

## Overview

This application is a comprehensive solution for managing a medical center. It provides CRUD (Create, Read, Update, Delete) functionalities for managing patients, appointments, doctors, administrators, and nurses. The application is built using Spring Boot for the backend and Vue.js for the frontend.

## Features

- **Patient Management**: Add, view, update, and delete patient information.
- **Appointment Management**: Schedule, view, update, and cancel appointments.
- **Doctor Management**: Add, view, update, and delete doctor profiles.
- **Administrator Management**: Manage admin users who have control over the system.
- **Nurse Management**: Add, view, update, and delete nurse profiles.

## Technologies Used

- **Backend**: Spring Boot
- **Frontend**: Vue.js
- **Database**: MySQL (or any other preferred relational database)
- **Build Tools**: Maven for Spring Boot, npm/yarn for Vue.js


## Getting Started

### Prerequisites

- Java 8 or higher
- Node.js and npm/yarn
- MySQL or any other preferred relational database

### Backend Setup

1. **Clone the repository**:
    ```sh
    git clone https://github.com/your-repo/medical-center-management.git
    cd medical-center-management/backend
    ```

2. **Configure the database**:
    Update the `application.properties` file in `src/main/resources` with your database configuration:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/edgi_db
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build and run the backend**:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

### Frontend Setup

1. **Navigate to the frontend directory**:
    ```sh
    cd ../frontend
    ```

2. **Install dependencies**:
    ```sh
    npm install
    ```

3. **Run the frontend**:
    ```sh
    npm run serve
    ```

### Accessing the Application

- The backend API can be accessed at `http://localhost:8080`.
- The frontend application can be accessed at `http://localhost:8081` (or the port specified in your Vue.js configuration).

## API Endpoints

### Patients
- `GET /api/patients` - Retrieve all patients
- `POST /api/patients` - Add a new patient
- `GET /api/patients/{id}` - Retrieve a specific patient
- `PUT /api/patients/{id}` - Update a specific patient
- `DELETE /api/patients/{id}` - Delete a specific patient

### Appointments
- `GET /api/appointments` - Retrieve all appointments
- `POST /api/appointments` - Schedule a new appointment
- `GET /api/appointments/{id}` - Retrieve a specific appointment
- `PUT /api/appointments/{id}` - Update a specific appointment
- `DELETE /api/appointments/{id}` - Cancel a specific appointment

### Doctors
- `GET /api/doctors` - Retrieve all doctors
- `POST /api/doctors` - Add a new doctor
- `GET /api/doctors/{id}` - Retrieve a specific doctor
- `PUT /api/doctors/{id}` - Update a specific doctor
- `DELETE /api/doctors/{id}` - Delete a specific doctor

### Administrators
- `GET /api/admins` - Retrieve all administrators
- `POST /api/admins` - Add a new administrator
- `GET /api/admins/{id}` - Retrieve a specific administrator
- `PUT /api/admins/{id}` - Update a specific administrator
- `DELETE /api/admins/{id}` - Delete a specific administrator

### Nurses
- `GET /api/nurses` - Retrieve all nurses
- `POST /api/nurses` - Add a new nurse
- `GET /api/nurses/{id}` - Retrieve a specific nurse
- `PUT /api/nurses/{id}` - Update a specific nurse
- `DELETE /api/nurses/{id}` - Delete a specific nurse

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements

We would like to thank all the contributors and the open-source community for their support and contributions to this project.

