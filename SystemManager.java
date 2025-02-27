package onlineTestProcessor;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// I have not taken care of the 80 character limit per line for readibility 
// since it was said in a piazza post that this should be fine 

public class SystemManager implements Manager, Serializable{

	// The data structures I am mostly using in this project are HashMaps and TreeMaps
	// I use TreeMaps when I need the keys to be sorted 
	private HashMap<Integer, Test> mapExam = new HashMap<>();
	
	private TreeMap<String, Student> mapStudents = new TreeMap<>();
	
	private TreeMap<String, Double> gradesAndCutoffs = new TreeMap<>();
	
	public Test getExam(int examId) {
		
		return mapExam.get(examId);
		
	}
	
	@Override
	public boolean addExam(int examId, String title) {
		
		// checking if the exam is already in the database
		if(mapExam.containsKey(examId)) {
			
			return false;
			
		}
		
		Test exam = new Test(examId, title);
		
		// Putting the exam into the database
		mapExam.put(examId, exam);
		
		return true;
	}

	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		
		TrueFalse tfQuestion = new TrueFalse(text, points, answer);
		
		// checking if the exam is in the database
		if(mapExam.containsKey(examId)) {
			
			// putting the question into the database
			mapExam.get(examId).addQuestion(questionNumber, tfQuestion);
			
		}
		
	}

	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		
		MCQ mCQuestion = new MCQ(text, points, answer);
		
		// checking if the exam is in the database
		if(mapExam.containsKey(examId)) {
			
			// putting the question into the database
			mapExam.get(examId).addQuestion(questionNumber, mCQuestion);
			
		}
		
	}

	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
			String[] answer) {
				
		FillInTheBlanks fillQuestion = new FillInTheBlanks(text, points, answer);
		
		// checking if the exam is in the database
		if(mapExam.containsKey(examId)) {
			
			// putting the question into the database
			mapExam.get(examId).addQuestion(questionNumber, fillQuestion);
			
		}
		
	}

	@Override
	public String getKey(int examId) {
		
		StringBuffer keyForExam = new StringBuffer("");
		
		// checking if the exam is in the database
		if(mapExam.containsKey(examId)) {
			
			Test currExam = mapExam.get(examId);
			
			Set<Integer> keys = currExam.getQuestions().keySet();
			
			for(Integer i : keys) {
				
				// Using the getQuesWithKey method from the questions class
				// for getting the question key
				keyForExam.append(mapExam.get(examId).getQuestions()
						.get(i).getQuesWithKey());
				
			}
			
		}
		
		return keyForExam.toString();
	}

	@Override
	public boolean addStudent(String name) {
		
		// checking if the student is already in the database
		if(mapStudents.containsKey(name)) {
			
			return false;
			
		} 
		
		// putting the student in the database
		mapStudents.put(name, new Student(name));
		
		return true;
	}

	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {
		
		// checking if the exam is in the database
		if(mapExam.containsKey(examId)) {
			
			Question question = mapExam.get(examId).getQuestion(questionNumber);
			
			if(question instanceof TrueFalse) {
				
				TrueFalse tfQuestion = (TrueFalse) question;
				
				// checking if the answer is correct
				boolean answerCorrect = tfQuestion.answerCheck(answer);
				
				// using if-else to check if the answer is right or wrong and
				// increasing the score respectively
				if(answerCorrect == true) {
					
					mapExam.get(examId).appendStudentScore(studentName, tfQuestion.getPoints());
					
					mapExam.get(examId).appendStudentScoreForEachQuestion(
							studentName, questionNumber, tfQuestion.getPoints());
					
				} else {
					
					mapExam.get(examId).appendStudentScore(studentName, 0.0);
					
					mapExam.get(examId).appendStudentScoreForEachQuestion(
							studentName, questionNumber, 0.0);
					
				}
				
			}
			
		}
		
	}

	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		
		// checking if the exam is in the database
		if(mapExam.containsKey(examId)) {
			
			Question question = mapExam.get(examId).getQuestion(questionNumber);
			
			if(question instanceof MCQ) {
				
				MCQ mcQuestion = (MCQ) question;
				
				// checking if the answer is correct
				boolean answerCorrect = mcQuestion.answerCheck(answer);
				
				// using if-else to check if the answer is right or wrong and
				// increasing the score respectively
				if(answerCorrect == true) {
					
					mapExam.get(examId).appendStudentScore(studentName, mcQuestion.getPoints());
					
					mapExam.get(examId).appendStudentScoreForEachQuestion(
							studentName, questionNumber, mcQuestion.getPoints());
					
				} else {
					
					mapExam.get(examId).appendStudentScoreForEachQuestion(
							studentName, questionNumber, 0.0);
					
				}
				
			}
			
		}
		
	}

	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		
		// checking if the exam is in the database
		if(mapExam.containsKey(examId)) {
			
			Question question = mapExam.get(examId).getQuestion(questionNumber);
			
			if(question instanceof FillInTheBlanks) {
				
				FillInTheBlanks fillQuestion = (FillInTheBlanks) question;
				
				// checking if the answer is correct
				boolean answerCorrect = fillQuestion.answerCheck(answer);
				
				// using if-else to see how much of the question is correct and 
				// increasing the score respectively
				if(answerCorrect == true) {
					
					mapExam.get(examId).appendStudentScore(studentName, fillQuestion.getPoints());
					
					mapExam.get(examId).appendStudentScoreForEachQuestion(
							studentName, questionNumber, fillQuestion.getPoints());
					
				} else if(fillQuestion.partialCreditEligibility(answer)){
					
					mapExam.get(examId).appendStudentScore(studentName, fillQuestion.getPartialCredit(answer));
					
					mapExam.get(examId).appendStudentScoreForEachQuestion(
							studentName, questionNumber, fillQuestion.getPartialCredit(answer));
					
				} else {
					
					mapExam.get(examId).appendStudentScoreForEachQuestion(
							studentName, questionNumber, 0.0);
					
				}
				
			}
			
		}
		
	}

	@Override
	public double getExamScore(String studentName, int examId) {
		
		// checking if the exam and student are in the databases
		if(mapExam.containsKey(examId) && mapStudents.containsKey(studentName)) {
			
			return mapExam.get(examId).getStudentScore(studentName);
			
		}
		
		return 0;
	}

	@Override
	public String getGradingReport(String studentName, int examId) {
		
		StringBuffer reportForExam = new StringBuffer("");
		
		// checking if the exam and student are in the databases
		if(mapExam.containsKey(examId) && mapStudents.containsKey(studentName)) {
			
			Double totalScoreCounter = 0.0;
			
			TreeMap<Integer, Double> scoreOfQuestions = mapExam.get(examId)
					.getStudentsScorePerQuestion().get(studentName);
			
			Set<Integer> keys = scoreOfQuestions.keySet();
			
			for(int i : keys) {
				
				reportForExam.append("Question #" + i + " " + scoreOfQuestions.get(i)
									+ " points out of " + mapExam.get(examId)
									.getQuestions().get(i).getPoints() + "\n");
									
				totalScoreCounter += mapExam.get(examId).getQuestions()
						.get(i).getPoints();
			
			}
			
			reportForExam.append("Final Score: " + getExamScore(studentName, examId)
								+ " out of " + totalScoreCounter);
			
		}
		
		return reportForExam.toString();
	}

	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		
		for(int i = 0; i < letterGrades.length; i++) {
			
			// putting the grade and cuttoffs into the database respectvely
			gradesAndCutoffs.put(letterGrades[i], cutoffs[i]);
			
		}
		
	}

	@Override
	public double getCourseNumericGrade(String studentName) {
		
//		if(mapStudents.containsKey(studentName)) {
//			
//			double points = 0.0;
//			int counter = 0;
//			
//			Set<Integer> examIds = mapExam.keySet();
//			
//			for(Integer i : examIds) {
//				
//				points += mapExam.get(i).getStudentScore(studentName);
//				counter++;
//				
//			}
//			
//			return points / counter;
//			
//		} 
		
		// checking if the student is in the database
		if(mapStudents.containsKey(studentName)) {
			
			double points = 0.0;
			int counter = 0;
			
			Set<Integer> examIds = mapExam.keySet();
			
			for(Integer i : examIds) {
				
				points += mapExam.get(i).getStudentScore(studentName) / mapExam.get(i).getTotalPoints();
				counter++;
				
			}
			
			return (points / counter) * 100;
			
		} 
		
		return 0;
	}

	@Override
	public String getCourseLetterGrade(String studentName) {
		
		// checking if the student is in the database
		if(mapStudents.containsKey(studentName)) {
			
			double courseNumericGrade = getCourseNumericGrade(studentName);
			
			StringBuffer courseLetterGrade = new StringBuffer("");
			
//			if(courseNumericGrade >= gradesAndCutoffs.get("A")) {
//				
//				courseLetterGrade = "A";
//				
//			} else if(courseNumericGrade >= gradesAndCutoffs.get("B") && courseNumericGrade < gradesAndCutoffs.get("A")) {
//				
//				courseLetterGrade = "B";
//				
//			} else if(courseNumericGrade >= gradesAndCutoffs.get("C") && courseNumericGrade < gradesAndCutoffs.get("B")) {
//				
//				courseLetterGrade = "C";
//				
//			} else if(courseNumericGrade >= gradesAndCutoffs.get("D") && courseNumericGrade < gradesAndCutoffs.get("C")) {
//				
//				courseLetterGrade = "D";
//				
//			} else if(courseNumericGrade >= gradesAndCutoffs.get("F") && courseNumericGrade < gradesAndCutoffs.get("D")) {
//				
//				courseLetterGrade = "F";
//				
//			} else {
//				
//				courseLetterGrade = "";
//				
//			}
			
			Set<String> letterGrades = gradesAndCutoffs.keySet();
			
			for(String letterGrade: letterGrades) {
				
				// Checking which letter grade should be assigned
				if(courseNumericGrade >= gradesAndCutoffs.get(letterGrade)) {
					
					courseLetterGrade.append(letterGrade);
					break;
					
				}
				
			}
			
			return courseLetterGrade.toString();
			
		}
		
		return null;
	}

	@Override
	public String getCourseGrades() {
		// TODO Auto-generated method stub
		
		StringBuffer resultString = new StringBuffer("");
		
		Set<String> studentNames = mapStudents.keySet();
		
		for(String name: studentNames) {
			
			resultString.append(name + " ");
			resultString.append(getCourseNumericGrade(name) + " ");
			resultString.append(getCourseLetterGrade(name));
			resultString.append("\n");
			
		}
		
		return resultString.toString();
	}

	@Override
	public double getMaxScore(int examId) {
		// TODO Auto-generated method stub
		
		// checking if the exam is in the database
		if(mapExam.containsKey(examId)) {
			
			return mapExam.get(examId).getMaximumScore();
			
		}
		
		return 0;
		
	}

	@Override
	public double getMinScore(int examId) {
		// TODO Auto-generated method stub
		
		// checking if the exam is in the database
		if(mapExam.containsKey(examId)) {
			
			return mapExam.get(examId).getMinimumScore();
			
		}
		
		return 0;
	}

	@Override
	public double getAverageScore(int examId) {
		// TODO Auto-generated method stub
		
		// checking if the exam is in the database
		if(mapExam.containsKey(examId)) {
			
			return mapExam.get(examId).getAverageScore();
			
		}
		
		return 0;
	}

	@Override
	public void saveManager(Manager manager, String fileName) {
		// TODO Auto-generated method stub
		
		// using try-catch to catch exceptions
		try {
			
			FileOutputStream outputFile = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(outputFile);
			
			out.writeObject(manager);
			
			out.close();
			outputFile.close();
			
		} catch(IOException e) {
			
			System.out.println("IOException");
			
		}
		
	}

	@Override
	public Manager restoreManager(String fileName) {
		// TODO Auto-generated method stub
		
		Manager manager = null;
		
		// using try-catch to catch exceptions
		try {
			
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);
			
			manager = (Manager)in.readObject();
			
			in.close();
			file.close();
			
		} catch(IOException e) {
			
			System.out.println("IOException");
			
		} catch(ClassNotFoundException e) {
			
			System.out.println("IOException");
			
		}
		
		return manager;
	}
	
	public Test getQuestion(int examId) {
		
		
		if(mapExam.containsKey(examId)) {
			
			return mapExam.get(examId);
			
		}
		
		return null;
		
	}

}
