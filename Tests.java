package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import onlineTestProcessor.SystemManager;


public class Tests {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	// This test tests the method for adding exams, different type of questions 
	// the method which returns the key.
	
	@Test
	public void testingAddExamAndQuestionsAndGettingKey() {
		
		StringBuffer result = new StringBuffer();
		SystemManager systemManager = new SystemManager();
		
		systemManager.addExam(10, "Exam 1");
		
		systemManager.addTrueFalseQuestion(10, 1, "Is this CMSC216", 2, false);
		
		String questionTextMultipleChoice = "Which of the following are Toyota car models?\n";
		questionTextMultipleChoice += "A: Camry   B: Accord   C: RAV4   D: Sonata";
		double points = 3;
		systemManager.addMultipleChoiceQuestion(10, 2, questionTextMultipleChoice, points, new String[]{"A","C"});
		
		String questionTextFillInTheBlank = "Name the two Toyota Trucks: ";
		points = 4;
		systemManager.addFillInTheBlanksQuestion(10, 3, questionTextFillInTheBlank, points, new String[]{"Tundra","Tacoma"});
		
		result.append(systemManager.getKey(10));
		
		String expectedResult = "Question Text: Is this CMSC216\n"
				+ "Points: 2.0\n" + "Correct Answer: False\n"
				+ "Question Text: Which of the following are Toyota car models?\n"
				+ "A: Camry   B: Accord   C: RAV4   D: Sonata\n"
				+ "Points: 3.0\n" + "Correct Answer: [A, C]\n"
				+ "Question Text: Name the two Toyota Trucks: \n"
				+ "Points: 4.0\n"
				+ "Correct Answer: [Tacoma, Tundra]" + "\n";
		
		//System.out.println(result.toString());
		
		assertEquals(result.toString(), expectedResult);
		
	}
	
	// This test tests the addStudent method
	
	@Test
	public void testingAddStudent() {
		
		StringBuffer result = new StringBuffer();
		SystemManager systemManager = new SystemManager();
		
		
		boolean bool = systemManager.addStudent("Ronaldo");
		boolean bool1 = systemManager.addStudent("Ronaldo");
		
		assertEquals(bool, true);
		assertEquals(bool1, false);
		
	}
	
	// This test tests the answering questions and grading report method
	
	@Test
	public void testingAnswerQuestionsAndGradingReport() {
		
		StringBuffer result = new StringBuffer();
		SystemManager systemManager = new SystemManager();
		
		systemManager.addExam(10, "Exam 1");
		
		systemManager.addTrueFalseQuestion(10, 1, "Is this CMSC216", 2, false);
		
		String questionTextMultipleChoice = "Which of the following are Toyota car models?\n";
		questionTextMultipleChoice += "A: Camry   B: Accord   C: RAV4   D: Sonata";
		double points = 3;
		systemManager.addMultipleChoiceQuestion(10, 2, questionTextMultipleChoice, points, new String[]{"A","C"});
		
		String questionTextFillInTheBlank = "Name the two Toyota Trucks: ";
		points = 4;
		systemManager.addFillInTheBlanksQuestion(10, 3, questionTextFillInTheBlank, points, new String[]{"Tundra","Tacoma"});
		
		result.append(systemManager.getKey(10));
		
		boolean bool = systemManager.addStudent("Ronaldo, Cristiano");
		boolean bool1 = systemManager.addStudent("Ronaldo, Cristiano");
		
		systemManager.answerTrueFalseQuestion("Ronaldo, Cristiano", 10, 1, false);
		
		systemManager.answerMultipleChoiceQuestion("Ronaldo, Cristiano", 10, 2, 
				new String[]{"A","D"});
		
		systemManager.answerFillInTheBlanksQuestion("Ronaldo, Cristiano", 10, 3,
				new String[]{"Tundra","Tacoma"});
		
		result.append(systemManager.getGradingReport("Ronaldo, Cristiano", 10));
		
		// System.out.println(systemManager.getGradingReport("Ronaldo, Cristiano", 10));
		
		String expectedResult = "Question Text: Is this CMSC216\n"
				+ "Points: 2.0\n" + "Correct Answer: False\n"
				+ "Question Text: Which of the following are Toyota car models?\n"
				+ "A: Camry   B: Accord   C: RAV4   D: Sonata\n"
				+ "Points: 3.0\n" + "Correct Answer: [A, C]\n"
				+ "Question Text: Name the two Toyota Trucks: \n"
				+ "Points: 4.0\n"
				+ "Correct Answer: [Tacoma, Tundra]\n"
				+ "Question #1 2.0 points out of 2.0\n"
				+ "Question #2 0.0 points out of 3.0\n"
				+ "Question #3 4.0 points out of 4.0\n"
				+ "Final Score: 6.0 out of 9.0";
		
		// System.out.println(result.toString());
		
		assertEquals(bool, true);
		assertEquals(bool1, false);
		assertEquals(result.toString(), expectedResult);
		
	}
	
	// This test tests the answering questions and get exam score method
	
	@Test
	public void testingAnswerQuestionsAndGetExamScore() {
		
		StringBuffer result = new StringBuffer();
		SystemManager systemManager = new SystemManager();
		
		systemManager.addExam(10, "Exam 1");
		
		systemManager.addTrueFalseQuestion(10, 1, "Is this CMSC216", 2, false);
		
		String questionTextMultipleChoice = "Which of the following are Toyota car models?\n";
		questionTextMultipleChoice += "A: Camry   B: Accord   C: RAV4   D: Sonata";
		double points = 3;
		systemManager.addMultipleChoiceQuestion(10, 2, questionTextMultipleChoice, points, new String[]{"A","C"});
		
		String questionTextFillInTheBlank = "Name the two Toyota Trucks: ";
		points = 4;
		systemManager.addFillInTheBlanksQuestion(10, 3, questionTextFillInTheBlank, points, new String[]{"Tundra","Tacoma"});
		
		result.append(systemManager.getKey(10));
		
		boolean bool = systemManager.addStudent("Ronaldo, Cristiano");
		boolean bool1 = systemManager.addStudent("Ronaldo, Cristiano");
		
		systemManager.answerTrueFalseQuestion("Ronaldo, Cristiano", 10, 1, false);
		
		systemManager.answerMultipleChoiceQuestion("Ronaldo, Cristiano", 10, 2, 
				new String[]{"A","D"});
		
		systemManager.answerFillInTheBlanksQuestion("Ronaldo, Cristiano", 10, 3,
				new String[]{"Tundra","Tacoma"});
		
		result.append("Exam score for " + "Ronaldo, Cristiano" + " " 
		+ systemManager.getExamScore("Ronaldo, Cristiano", 10));
		
		// System.out.println("\nExam score for " + "Ronaldo, Cristiano" + " " 
		// + systemManager.getExamScore("Ronaldo, Cristiano", 10));
		
		String expectedResult = "Question Text: Is this CMSC216\n"
				+ "Points: 2.0\n" + "Correct Answer: False\n"
				+ "Question Text: Which of the following are Toyota car models?\n"
				+ "A: Camry   B: Accord   C: RAV4   D: Sonata\n" + "Points: 3.0\n"
				+ "Correct Answer: [A, C]\n"
				+ "Question Text: Name the two Toyota Trucks: \n"
				+ "Points: 4.0\n" + "Correct Answer: [Tacoma, Tundra]\n"
				+ "Exam score for Ronaldo, Cristiano 6.0";
		
		// System.out.println(result.toString());
		
		assertEquals(bool, true);
		assertEquals(bool1, false);
		assertEquals(result.toString(), expectedResult);
		
	}
	
	// This test tests GradesAndCuttoffs related method
	
	@Test
	public void testingGradesAndCuttoffs() {
		
		StringBuffer result = new StringBuffer();
		SystemManager systemManager = new SystemManager();
		
		systemManager.addExam(10, "Exam 1");
		
		systemManager.addTrueFalseQuestion(10, 1, "Is this CMSC216", 2, false);
		
		String questionTextMultipleChoice = "Which of the following are Toyota car models?\n";
		questionTextMultipleChoice += "A: Camry   B: Accord   C: RAV4   D: Sonata";
		double points = 3;
		systemManager.addMultipleChoiceQuestion(10, 2, questionTextMultipleChoice, 
				points, new String[]{"A","C"});
		
		String questionTextFillInTheBlank = "Name the two Toyota Trucks: ";
		points = 4;
		systemManager.addFillInTheBlanksQuestion(10, 3, questionTextFillInTheBlank,
				points, new String[]{"Tundra","Tacoma"});
		
		result.append(systemManager.getKey(10));
		
		boolean bool = systemManager.addStudent("Ronaldo, Cristiano");
		boolean bool1 = systemManager.addStudent("Ronaldo, Cristiano");
		
		systemManager.answerTrueFalseQuestion("Ronaldo, Cristiano", 10, 1, false);
		
		systemManager.answerMultipleChoiceQuestion("Ronaldo, Cristiano", 10, 2, 
				new String[]{"A","D"});
		
		systemManager.answerFillInTheBlanksQuestion("Ronaldo, Cristiano", 10, 3,
				new String[]{"Tundra","Tacoma"});
		
		result.append("Exam score for " + "Ronaldo, Cristiano" + " " 
		+ systemManager.getExamScore("Ronaldo, Cristiano", 10));
		
		systemManager.setLetterGradesCutoffs(new String[]{"A+","A", "B+", "B", "C", "D", "F"},
				new double[]{95,90,85,80,70,60,0});
		
		result.append("\nLetter Grade for " + "Ronaldo, Cristiano" + " " 
		+ systemManager.getCourseLetterGrade("Ronaldo, Cristiano"));
		
		result.append("\nNumeric Grade for " + "Ronaldo, Cristiano" + " " 
				+ systemManager.getCourseNumericGrade("Ronaldo, Cristiano") + "\n");
		
		// System.out.println("\nExam score for " + "Ronaldo, Cristiano" + " " 
		// + systemManager.getExamScore("Ronaldo, Cristiano", 10));
		
		result.append(systemManager.getCourseGrades());
		
		String expectedResult = "Question Text: Is this CMSC216\n"
				+ "Points: 2.0\n"
				+ "Correct Answer: False\n"
				+ "Question Text: Which of the following are Toyota car models?\n"
				+ "A: Camry   B: Accord   C: RAV4   D: Sonata\n"
				+ "Points: 3.0\n"
				+ "Correct Answer: [A, C]\n"
				+ "Question Text: Name the two Toyota Trucks: \n"
				+ "Points: 4.0\n"
				+ "Correct Answer: [Tacoma, Tundra]\n"
				+ "Exam score for Ronaldo, Cristiano 6.0\n"
				+ "Letter Grade for Ronaldo, Cristiano D" + "\n"
				+ "Numeric Grade for Ronaldo, Cristiano 66.66666666666666\n" 
				+ "Ronaldo, Cristiano 66.66666666666666 D" + "\n";
		
		// System.out.println(result.toString());
		
		
		assertEquals(bool, true);
		assertEquals(bool1, false);
		assertEquals(result.toString(), expectedResult);
		
	}
	
	// This test tests max, min, average methods
	
	@Test
	public void testingMinMaxAverageAndOther() {
		
		StringBuffer result = new StringBuffer();
		SystemManager systemManager = new SystemManager();
		
		systemManager.addExam(10, "Exam 1");
		
		systemManager.addTrueFalseQuestion(10, 1, "Is this CMSC216", 2, false);
		
		String questionTextMultipleChoice = "Which of the following are Toyota car models?\n";
		questionTextMultipleChoice += "A: Camry   B: Accord   C: RAV4   D: Sonata";
		double points = 3;
		systemManager.addMultipleChoiceQuestion(10, 2, questionTextMultipleChoice, 
				points, new String[]{"A","C"});
		
		String questionTextFillInTheBlank = "Name the two Toyota Trucks: ";
		points = 4;
		systemManager.addFillInTheBlanksQuestion(10, 3, questionTextFillInTheBlank,
				points, new String[]{"Tundra","Tacoma"});
		
		systemManager.addExam(11, "Exam 2");
		
		systemManager.addTrueFalseQuestion(10, 4, "Is this CMSC216", 2, false);
		
		String questionTextMultipleChoice1 = "Which of the following are Toyota car models?\n";
		questionTextMultipleChoice += "A: Camry   B: Accord   C: RAV4   D: Sonata";
		double points1 = 3;
		systemManager.addMultipleChoiceQuestion(10, 5, questionTextMultipleChoice, 
				points1, new String[]{"A","C"});
		
		String questionTextFillInTheBlank1 = "Name the two Toyota Trucks: ";
		points1 = 4;
		systemManager.addFillInTheBlanksQuestion(10, 6, questionTextFillInTheBlank,
				points1, new String[]{"Tundra","Tacoma"});
		
//			
//			result.append(systemManager.getKey(10));
		
		boolean bool = systemManager.addStudent("Ronaldo, Cristiano");
		boolean bool1 = systemManager.addStudent("Ronaldo, Cristiano");
		
		
		systemManager.answerTrueFalseQuestion("Ronaldo, Cristiano", 10, 1, false);
		
		systemManager.answerMultipleChoiceQuestion("Ronaldo, Cristiano", 10, 2, 
				new String[]{"A","D"});
		
		systemManager.answerFillInTheBlanksQuestion("Ronaldo, Cristiano", 10, 3,
				new String[]{"Tundra","Tacoma"});
		
		
		systemManager.answerTrueFalseQuestion("Ronaldo, Cristiano", 11, 4, true);
		
		systemManager.answerMultipleChoiceQuestion("Ronaldo, Cristiano", 11, 5, 
				new String[]{});
		
		systemManager.answerFillInTheBlanksQuestion("Ronaldo, Cristiano", 11, 6,
				new String[]{});

		boolean bool3 = systemManager.addStudent("Messi, Lionel");
		boolean bool4 = systemManager.addStudent("Messi, Lionel");
		
		systemManager.answerTrueFalseQuestion("Messi, Lionel", 10, 1, false);
		
		systemManager.answerMultipleChoiceQuestion("Messi, Lionel", 10, 2, 
				new String[]{"A","C"});
		
		systemManager.answerFillInTheBlanksQuestion("Messi, Lionel", 10, 3,
				new String[]{"Tundra","Tacoma"});
		
		
		systemManager.answerTrueFalseQuestion("Messi, Lionel", 11, 4, true);
		
		systemManager.answerMultipleChoiceQuestion("Messi, Lionel", 11, 5, 
				new String[]{});
		
		systemManager.answerFillInTheBlanksQuestion("Messi, Lionel", 11, 6,
				new String[]{});
		
		
		systemManager.setLetterGradesCutoffs(new String[]{"A+","A", "B+", "B", "C", "D", "F"},
				new double[]{95,90,85,80,70,60,0});
		
		
		result.append("Min score for for " + "Exam 1" + " " 
				+ systemManager.getMinScore(10));
		
		result.append("\nMax score for for " + "Exam 2" + " " 
				+ systemManager.getMaxScore(11));
		
		result.append("\nAvg score for for " + "Exam 1" + " " 
				+ systemManager.getAverageScore(10));
		
		// System.out.println("\nExam score for " + "Ronaldo, Cristiano" + " " 
		// + systemManager.getExamScore("Ronaldo, Cristiano", 10));
		
		String expectedResult = "Min score for for Exam 1 6.0\n"
				+ "Max score for for Exam 2 0.0\n"
				+ "Avg score for for Exam 1 7.5";
		
		// System.out.println(result.toString());
		
		assertEquals(bool, true);
		assertEquals(bool1, false);
		assertEquals(bool3, true);
		assertEquals(bool4, false);
		assertEquals(result.toString(), expectedResult);
		
	}
	
	// This test tests serialization
	
	@Test
	public void testingSerialization() {
		
		StringBuffer result = new StringBuffer();
		SystemManager systemManager = new SystemManager();
		
		systemManager.addExam(10, "Exam 1");
		
		systemManager.addTrueFalseQuestion(10, 1, "Is this CMSC216", 2, false);
		
		String questionTextMultipleChoice = "Which of the following are Toyota car models?\n";
		questionTextMultipleChoice += "A: Camry   B: Accord   C: RAV4   D: Sonata";
		double points = 3;
		systemManager.addMultipleChoiceQuestion(10, 2, questionTextMultipleChoice, points, new String[]{"A","C"});
		
		String questionTextFillInTheBlank = "Name the two Toyota Trucks: ";
		points = 4;
		systemManager.addFillInTheBlanksQuestion(10, 3, questionTextFillInTheBlank, points, new String[]{"Tundra","Tacoma"});
		
		result.append(systemManager.getKey(10));
		
		String fileName = "fileTest.ser";
		systemManager.saveManager(systemManager, fileName);
		SystemManager restoredManager = (SystemManager) systemManager.restoreManager(fileName);	
		
		result.append("After restoring\n");
		result.append(restoredManager.getKey(10));
		
		String expectedResult = "Question Text: Is this CMSC216\n"
				+ "Points: 2.0\n"
				+ "Correct Answer: False\n"
				+ "Question Text: Which of the following are Toyota car models?\n"
				+ "A: Camry   B: Accord   C: RAV4   D: Sonata\n"
				+ "Points: 3.0\n"
				+ "Correct Answer: [A, C]\n"
				+ "Question Text: Name the two Toyota Trucks: \n"
				+ "Points: 4.0\n"
				+ "Correct Answer: [Tacoma, Tundra]\n"
				+ "After restoring\n"
				+ "Question Text: Is this CMSC216\n"
				+ "Points: 2.0\n"
				+ "Correct Answer: False\n"
				+ "Question Text: Which of the following are Toyota car models?\n"
				+ "A: Camry   B: Accord   C: RAV4   D: Sonata\n"
				+ "Points: 3.0\n"
				+ "Correct Answer: [A, C]\n"
				+ "Question Text: Name the two Toyota Trucks: \n"
				+ "Points: 4.0\n"
				+ "Correct Answer: [Tacoma, Tundra]\n";
		
		System.out.println(result.toString());
		
		assertEquals(result.toString(), expectedResult);
		
	}


}
