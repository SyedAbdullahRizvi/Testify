package onlineTestProcessor;

import java.util.Arrays;

//This is my FillInTheBlanks class extending the Question class, 
//representing the fill in the blanks questions in the exam

public class FillInTheBlanks extends Question {

	private String[] answer;
	
	public FillInTheBlanks(String text, double points, String[] answer) {
		
		super(text, points);
		
		this.answer = answer;
		
	}
	
	public String getQuesWithKey() {
		
		String[] answerCopy = answer.clone();
		
		Arrays.sort(answerCopy);
		
		StringBuffer stringAns = new StringBuffer("");
		
		stringAns.append("[");
		
		for(int i = 0; i < answerCopy.length; i++) {
			
			if(i == answerCopy.length - 1) {
				
				stringAns.append(answerCopy[i]);
				
			} else {

				stringAns.append(answerCopy[i] + ", ");
				
			}
			
			
		}
		
		stringAns.append("]");
		
		return super.getQuesWithKey() +
				"\nCorrect Answer: " + stringAns.toString() + "\n";
		
	}
	
	public boolean partialCreditEligibility(String[] answer) {
		
		for(int i = 0; i < answer.length; i++) {
			
			for(int j = 0; j < this.answer.length; j++) {
				
				if(answer[i].equals(this.answer[j])) {
					
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	public double getPartialCredit(String[] answer) {
		
		double points = 0.0;
		
		double partialPoints = this.points / this.answer.length;
		
		for(int i = 0; i < answer.length; i++) {
			
			for(int j = 0; j < this.answer.length; j++) {
				
				if(answer[i].equals(this.answer[j])) {
					
					points += partialPoints;
					
					break;
					
				}
				
			}
			
		}
		
		return points;
		
	}
	
	public boolean answerCheck(String[] answer) {
		
		return Arrays.equals(this.answer , answer);
		
	}

}
