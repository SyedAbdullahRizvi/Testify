package onlineTestProcessor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class Test implements Serializable {
	
	private int examId;
	private String title;
	private TreeMap<Integer, Question> questions; 
	private HashMap<String, Double> studentsScore;
	private HashMap<String, TreeMap<Integer, Double>> studentsScorePerQuestion;
	// private HashMap<String, Double> gradesAndCutoffs;
	
	public Test(int examId, String title){
		
		this.examId = examId;
		this.title = title;
		this.questions = new TreeMap<Integer, Question>();
		this.studentsScore = new HashMap<String, Double>();
		this.studentsScorePerQuestion = new HashMap<String, TreeMap<Integer, Double>>();
		
	}
	
	public TreeMap<Integer, Question> getQuestions(){
		
		return questions;
		
	}
	
	public double getTotalPoints() {
		
		Set<Integer> questionSet = questions.keySet();
		double totalPoints = 0.0;
		
		for(Integer questionNumber: questionSet) {
			
			totalPoints += questions.get(questionNumber).getPoints();
			
		}
		
		return totalPoints;
		
	}
	
//	public void setGradesAndCutoffs(String[] letterGrades, double[] cutoffs) {
//		
//		for(int i = 0; i < letterGrades.length; i++) {
//			
//			gradesAndCutoffs.put(letterGrades[i], cutoffs[i]);
//			
//		}
//		
//	}
	
	public HashMap<String, TreeMap<Integer, Double>> getStudentsScorePerQuestion(){
		
		return studentsScorePerQuestion;
		
	}
	
	public void addQuestion(int questionNumber, Question question) {
		
//		if(questions.containsKey(questionNumber)) {
//			
//			questions.put(questionNumber, question);
//			
//		} else {
//			
//			questions.put(questionNumber, question);
//			
//		}
		
		questions.put(questionNumber, question);
		
	}
	
	public void appendStudentScore(String name, double points) {
		
		if(!studentsScore.containsKey(name)) {
			
			studentsScore.put(name, 0.0);
			
		} 
			
			
		studentsScore.put(name, studentsScore.get(name) + points);
			
		
		
		
		
	}
	
	public void appendStudentScoreForEachQuestion(String name, int questionNumber, double points) {
		
		if(!studentsScorePerQuestion.containsKey(name)) {
			
			studentsScorePerQuestion.put(name, new TreeMap<Integer, Double>());
			
			studentsScorePerQuestion.get(name).put(questionNumber, points);
			
		}
		
		studentsScorePerQuestion.get(name).put(questionNumber, points);
		
	}
	
	public double getStudentScore(String name) {
		
		if(studentsScore.containsKey(name)) {
			
			return studentsScore.get(name);
			
		} 
		
		return 0;
		
	}
	
	public Question getQuestion(int questionNumber) {
		
		
		if(questions.containsKey(questionNumber)) {
			
			return questions.get(questionNumber);
			
		}
		
		return null;
		
	}
	
	public double getMaximumScore() {
		
		double max = 0.0;
		
		Set<String> studentNames = studentsScore.keySet();
		
		for(String name: studentNames) {
			
			if(studentsScore.get(name) > max) {
				
				max = studentsScore.get(name);
				
			}
			
		}
		
		return max;
		
	}
	
	public double getMinimumScore() {
		
		double min = 101;
		
		Set<String> studentNames = studentsScore.keySet();
		
		for(String name: studentNames) {
			
			if(studentsScore.get(name) < min) {
				
				min = studentsScore.get(name);
				
			}
			
		}
		
		return min;
		
	}
	
	public double getAverageScore() {
		
		double summationScore = 0.0;
		int counter = 0;
		
		Set<String> studentNames = studentsScore.keySet();
		
		for(String name: studentNames) {
			
			summationScore += studentsScore.get(name);
			counter++;
			
		}
		
		return summationScore / counter;
		
	}
	
	

}
