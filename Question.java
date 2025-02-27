package onlineTestProcessor;

import java.io.Serializable;

// This is my Question class representing a questions in the exam

public class Question implements Serializable {
	
	protected String text;
	protected double points;
	
	public Question(String text, double points) {
		
		this.text = text;
		this.points = points;
		
	}
	
	public double getPoints() {
		
		return points;
		
	}
	
	public String getQuesWithKey() {
		
		return "Question Text: " + text + "\nPoints: " + points;
		
	}

}
