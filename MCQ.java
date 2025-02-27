package onlineTestProcessor;

import java.util.Arrays;

// This is my MCQ class extending the Question class, 
// representing the multiple choice questions in the exam

public class MCQ extends Question{

	private String[] answer;
	
	public MCQ(String text, double points, String[] answer) {
		
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
	
	public boolean answerCheck(String[] answer) {
		
		return Arrays.equals(this.answer , answer);
		
	}

}
