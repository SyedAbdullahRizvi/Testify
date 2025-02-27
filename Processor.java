package onlineTestProcessor;

import java.util.Scanner;

public class Processor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SystemManager systemManager = new SystemManager();
        boolean running = true;

        while (running) {
            System.out.println("\n--- System Manager ---");
            System.out.println("1. Add Exam");
            System.out.println("2. Add Student");
            System.out.println("3. Add True/False Question");
            System.out.println("4. Add Multiple Choice Question");
            System.out.println("5. Add Fill in the Blank Question");
            System.out.println("6. Answer Question");
            System.out.println("7. View Exam Key");
            System.out.println("8. Set Letter Grade Cutoffs");
            System.out.println("9. View Course Grades");
            System.out.println("10. Serialize and Save Manager");
            System.out.println("11. Restore Manager from File");
            System.out.println("12. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            int examId1;
			switch (choice) {
                case 1: // Add Exam
                    System.out.print("Enter Exam ID: ");
                    int examId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Exam Name: ");
                    String examName = scanner.nextLine();
                    systemManager.addExam(examId, examName);
                    System.out.println("Exam added.");
                    break;
                case 2: // Add Student
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    boolean added = systemManager.addStudent(studentName);
                    System.out.println(added ? "Student added." : "Student already exists.");
                    break;
                case 3: // Add True/False Question
                    System.out.print("Enter Exam ID: ");
                    examId = scanner.nextInt();
                    System.out.print("Enter Question Number: ");
                    int questionNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Question Text: ");
                    String tfText = scanner.nextLine();
                    System.out.print("Enter Points: ");
                    double tfPoints = scanner.nextDouble();
                    System.out.print("Enter Correct Answer (true/false): ");
                    boolean tfAnswer = scanner.nextBoolean();
                    systemManager.addTrueFalseQuestion(examId, questionNumber, tfText, tfPoints, tfAnswer);
                    System.out.println("True/False question added.");
                    break;
                case 4: // Add Multiple Choice Question
                    System.out.print("Enter Exam ID: ");
                    examId = scanner.nextInt();
                    System.out.print("Enter Question Number: ");
                    questionNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Question Text: ");
                    String mcText = scanner.nextLine();
                    System.out.print("Enter Points: ");
                    double mcPoints = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Correct Answers (comma-separated): ");
                    String[] mcAnswers = scanner.nextLine().split(",");
                    systemManager.addMultipleChoiceQuestion(examId, questionNumber, mcText, mcPoints, mcAnswers);
                    System.out.println("Multiple choice question added.");
                    break;
                case 5: // Add Fill in the Blank Question
                    System.out.print("Enter Exam ID: ");
                    examId = scanner.nextInt();
                    System.out.print("Enter Question Number: ");
                    questionNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Question Text: ");
                    String fbText = scanner.nextLine();
                    System.out.print("Enter Points: ");
                    double fbPoints = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Correct Answers (comma-separated): ");
                    String[] fbAnswers = scanner.nextLine().split(",");
                    systemManager.addFillInTheBlanksQuestion(examId, questionNumber, fbText, fbPoints, fbAnswers);
                    System.out.println("Fill in the Blank question added.");
                    break;
                case 6:
                    // Answer questions based on their type
                    System.out.println("Enter the student name:");
                    String studentName11 = scanner.nextLine();
                    System.out.println("Enter the exam ID:");
                    int examId2 = scanner.nextInt();
                    scanner.nextLine(); // consume newline character
                    System.out.println("Enter the question number:");
                    int qNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline character

                    // Fetch the question to determine its type
                    Question question = (systemManager.getExam(examId2)).getQuestion(qNumber);
                    
                    if (question instanceof TrueFalse) {
                        System.out.println("Enter your answer (true/false):");
                        boolean answer = scanner.nextBoolean();
                        systemManager.answerTrueFalseQuestion(studentName11, examId2, qNumber, answer);
                    } else if (question instanceof MCQ) {
                        System.out.println("Enter your answers (separated by spaces):");
                        String[] answers = scanner.nextLine().split(" ");
                        systemManager.answerMultipleChoiceQuestion(studentName11, examId2, qNumber, answers);
                    } else if (question instanceof FillInTheBlanks) {
                        System.out.println("Enter your answers (separated by commas):");
                        String[] answers = scanner.nextLine().split(",");
                        systemManager.answerFillInTheBlanksQuestion(studentName11, examId2, qNumber, answers);
                    } else {
                        System.out.println("Invalid question type.");
                    }
                    break;

                case 7: // View Exam Key
                    System.out.print("Enter Exam ID: ");
                    examId1 = scanner.nextInt();
                    System.out.println(systemManager.getKey(examId1));
                    break;
                case 8: // Set Letter Grade Cutoffs
                    System.out.print("Enter Letter Grades (comma-separated): ");
                    String[] letterGrades = scanner.nextLine().split(",");
                    System.out.print("Enter Cutoffs (comma-separated): ");
                    double[] cutoffs = new double[letterGrades.length];
                    for (int i = 0; i < letterGrades.length; i++) {
                        cutoffs[i] = scanner.nextDouble();
                    }
                    scanner.nextLine();
                    systemManager.setLetterGradesCutoffs(letterGrades, cutoffs);
                    System.out.println("Grade cutoffs set.");
                    break;
                case 9: // View Course Grades
                    System.out.println(systemManager.getCourseGrades());
                    break;
                case 10: // Serialize and Save Manager
                    System.out.print("Enter File Name to Save: ");
                    String saveFile = scanner.nextLine();
                    systemManager.saveManager(systemManager, saveFile);
                    System.out.println("Manager saved to file.");
                    break;
                case 11: // Restore Manager from File
                    System.out.print("Enter File Name to Restore: ");
                    String restoreFile = scanner.nextLine();
                    systemManager = (SystemManager) systemManager.restoreManager(restoreFile);
                    System.out.println("Manager restored from file.");
                    break;
                case 12: // Exit
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
