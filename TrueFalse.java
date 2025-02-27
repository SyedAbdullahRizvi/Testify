package onlineTestProcessor;

//This is my TrueFalse class extending the Question class, 
//representing the true false questions in the exam

public class TrueFalse extends Question{

	private boolean answer;
	
	public TrueFalse(String text, double points, boolean answer) {
		
		super(text, points);
		
		this.answer = answer;
		
	}
	
	public boolean answerCheck(boolean answer) {
		
		return this.answer == answer;
		
	}
	
	public double getPoints() {
		
		return super.getPoints();
		
	}
	
	public String getQuesWithKey() {
		
		return super.getQuesWithKey() +
				"\nCorrect Answer: " + ((answer == true) ? "True" : "False") + "\n";
		
	}

}
