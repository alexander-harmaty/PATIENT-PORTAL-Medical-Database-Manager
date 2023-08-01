# PATIENT PORTAL Medical Database Manager

Computer Programming and Information Systems Senior Project

(FSC-BCS-430W: Senior Project)

This is a comprehensive application designed to manage a medical database for doctors, patients, and support services like test centers and pharmacies. 
The application provides a unique dashboard for each user-type upon login, activating relevant features tailored to that user. 

## Assignment Context

This application was the product of a semester long development as the overall senior capstone project for my CPIS degree. 

The goal was to take a team of five students through the SDLC and develop an original application from scratch, taking everything we've learned from all previous programming classes.

The only requirements were to have an easy to use UI, and to use MS SQL Server as a database. 

To fulfill these requirements, my team I created a medical database and patient portal. 

This project was made using Java in Apache Netbeans.

## Features
- **Login**: Determines the user type and directs them to the appropriate dashboard.
- **Register**: Creates an account after the user enters personal info. 
- **Home**: Home page shortcut to greet users upon login.
- **Search**: Allows doctors to search for patients and services, while patients can search for doctors and services.
- **Account Info**: A personal info manager to update contact info, addresses, and insurance info.
- **Medical Records**: A database of appointment and diagnostic history.
- **Appointments**: A feature to create, update, and cancel appointments.
- **Prescriptions**: A prescription manager for doctors to prescribe medication, and for patients and servies to view details.
- **Test Results**: A test manager for doctors to order tests, for services to send results, and for patients to view results. 

## How it Works

### Login Page
The login page authenticates users and redirects them to the corresponding dashboard (doctor, patient, or service) based on their user type. Users can also create accounts and enter their personal info for the first time depending on the user type.

### Search
For doctors and patients, the search functionality allows them to find doctors, patients, or service centers such as test labs and pharmacies. 

### Account Info
The Account Info tab allows users to update their contact information, addresses, and insurance information. 

### Medical Records
Medical records are a collection of appointment and diagnostic history. Doctors can access all their patients' information and create new records, while patients can only view their own records.

### Appointments
The appointments tab allows doctors, patients, and service staff to create, update, and cancel appointments. 

### Prescriptions
The prescriptions tab displays a list of prescriptions. Doctors can create prescriptions, while patients can only view their own prescriptions.

### Test Results
The test results tab shows a list of test results. Doctors can create and view test results for their patients, while patients can only view their own test results.
