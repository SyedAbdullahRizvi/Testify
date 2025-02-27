package onlineTestProcessor;

import java.io.Serializable;

// This is my student class representing a student in the database

public class Student implements Serializable {
	
	private String name;
	// private double score;
	
	
	
	public Student(String name) {
		
		this.name = name;
		
	}

}
