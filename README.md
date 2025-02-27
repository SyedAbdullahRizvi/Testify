# Testify
_A powerful Java-based system for managing online tests, grading students, and tracking performance._

## Overview
**Testify** is a Java-based application that allows users to create and manage online exams with different question types, track student submissions, and generate grading reports. This project implements **Object-Oriented Programming (OOP) principles**, **data serialization**, and **collections framework** to ensure efficient and scalable test processing.

## Features
- **Exam Management**: Create and store multiple exams with unique IDs and titles.
- **Diverse Question Types**:
  - **True/False Questions**
  - **Multiple Choice Questions**
  - **Fill in the Blanks Questions**
- **Student Management**: Add students and track their submissions.
- **Automatic Grading**:
  - **Correct answer verification**
  - **Partial credit allocation for Fill-in-the-Blanks**
- **Grade Calculation & Reports**:
  - **Individual exam scores**
  - **Course-wide grade distribution**
  - **Letter grade assignment based on customizable cutoffs**
- **Persistence with Serialization**: Save and restore system state using object serialization.
- **CLI-Based Interaction**: User-friendly menu-driven interface for managing exams and students.

## Technical Info
- **Language**: Java
- **File Handling**: Serialization with `ObjectOutputStream` and `ObjectInputStream`

## Project Structure
```plaintext
📦 onlineTestProcessor
 ┣ 📜 Question.java              # Base class for all question types
 ┣ 📜 MCQ.java                   # Multiple Choice Question class
 ┣ 📜 FillInTheBlanks.java        # Fill-in-the-Blanks Question class
 ┣ 📜 TrueFalse.java              # True/False Question class
 ┣ 📜 Student.java                # Student class
 ┣ 📜 Manager.java                # Interface defining system operations
 ┣ 📜 SystemManager.java          # Implementation of Manager interface
 ┣ 📜 Test.java                   # Represents an exam and handles student scores
 ┣ 📜 Processor.java              # Main class with CLI-based user interaction
 ┣ 📜 README.md                   # Project documentation (You are here!)

```

## Potential Enhancements

- **Implement a GUI** for a more interactive experience.
- **Extend the system** with customizable exam timing and deadlines.
- **Integrate database storage** to make it scalable
