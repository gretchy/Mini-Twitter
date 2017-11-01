package smoltwit;

import java.util.*;

public class Positivity {
	private double percentage = 0.0;
	private int totalMessages = 0;
	private int positiveMessages = 0;
	private List<String> messages = new ArrayList<String>();
	private List<String> positiveWords = new ArrayList<String>();
	
	public void positivePercentage() {
		addPositiveWords();
		addMessages();
		setTotalMessages(messages.size());
		calcPositiveMessages();
		setPercentage(getPositiveMessages()/getTotalMessages());
	}
	
	private void calcPositiveMessages(){
		
		int posCount = 0;
		for(String x: messages){
			for(String y: positiveWords){
				if(x.toLowerCase().contains(y)){
					posCount++;
				}
			}
		}
		setPositiveMessages(posCount);
	}
	
	private void addMessages(){
		this.messages.add("This project is so much fun");
		this.messages.add("CS356 is cool");
		this.messages.add("Hello World");
		this.messages.add("Horror night is good");
		this.messages.add("Testing 123");
	}
	
	private void addPositiveWords(){
		this.positiveWords.add("fun");
		this.positiveWords.add("cool");
		this.positiveWords.add("good");
	}

	public double getPositiveMessages() {
		return positiveMessages;
	}

	private void setPositiveMessages(int positiveMessages) {
		this.positiveMessages = positiveMessages;
	}

	public double getTotalMessages() {
		return totalMessages;
	}

	private void setTotalMessages(int totalMessages) {
		this.totalMessages = totalMessages;
	}

	public double getPercentage() {
		return percentage;
	}

	private void setPercentage(double percentage) {
		this.percentage = percentage*100;
	}
}
