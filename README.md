# Java Student Quiz & Management System

## Description
This Java application simulates a quiz and classroom management system. Users can log in as students or teachers, with different functionality based on their role. 

The system demonstrates object-oriented programming, file handling, and role-based access control.

---

## Project Files

This repository includes the following files:

1. **Source Code**
   - `DSFinal.java` (or your actual main class file)
   - `DLL.java` (or other supporting source files)

2. **Data Files**
   - `sample_students.txt` — contains sample user accounts for testing  
   - `TestBank.txt` — contains all quiz questions  
   - `Answers.txt` — contains corresponding correct answers  

> **Note:** `sample_students.txt` uses dummy data only, with safe passwords and no real student information. Passwords are 6 letters in all caps.

---

## Features

### Student Functionality
- Secure login using username and password  
- Automatically generates a quiz with 10 random questions from `TestBank.txt`  
- Displays final score at the end of the quiz  

### Teacher / Instructor Functionality
- Login with elevated permissions  
- Add or delete questions in `TestBank.txt`  
- View student scores and performance  

---

## Technologies Used
- Java  
- File I/O (reading and writing data to text files)  
- Object-Oriented Programming (classes, methods, encapsulation)  

---

## How to Run

1. Clone or download this repository  
2. Ensure all files are in the same project folder:
   - `Main.java` and other source files  
   - `sample_students.txt`  
   - `TestBank.txt`  
   - `Answers.txt`  
3. Open the project in an IDE (NetBeans, IntelliJ, or VS Code)  
4. Run the main class  
5. Follow the prompts to log in and take quizzes  

> Optional: You can replace `sample_students.txt` with your own test accounts, keeping the format:  
> `FirstName LastName UserName Password Role`  

---

## Key Concepts Demonstrated
- Object-oriented design (classes, methods, encapsulation)  
- Role-based functionality for students and teachers  
- Randomized question selection from a test bank  
- File handling for persistent data storage  
- User input validation and program flow control  

---

## Future Improvements
- Add a graphical user interface (GUI)  
- Store student and quiz data in a database instead of text files  
- Implement password encryption for better security  
- Add detailed analytics and reporting for student performance  

---

## Author
Mia Howell  
Computer Science (Cybersecurity) Student  

---

## Notes
This project was developed as part of a Data Structures course. The repository includes all necessary files to run the program safely with sample data.
