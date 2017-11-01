package smoltwit;

import java.util.*;

public class User {
	
	String userID; // unique user ID
	private static int numUsers = 12, totalTweets = 0;
	private int totalFollowers = 0;
	
	ArrayList<String> followers = new ArrayList<String>();
	ArrayList<String> following = new ArrayList<String>();
	ArrayList<String> twitfeed = new ArrayList<String>();
	ArrayList<ObserverPattern> observers;
	
	public User() {
		numUsers++;
	}
	
	public String getID() {
		return userID;
	}
	
	public void setID(String id) {
		userID = id;
	}
	
	public void addFollower(String user) {
		followers.add(user);
		totalFollowers++;
	}
	
	public void startFollowing(String user) {
		following.add(user);
	}
	
	public void twit(String tweet){
		//setTotalMessages(getTotalMessages() + 1);
		totalTweets++;
		twitfeed.add(tweet);		
	}
	
	public String getFollowers(){
		String listFollowers = "";
		for(String person : followers){
			listFollowers += person;
			listFollowers += "\n";
		}
		return listFollowers;
	}
	
	public String getTweets(){
		String listTweets = "";
		for(String twits : twitfeed){
			listTweets += twits;
			listTweets += "\n";
		}
		return listTweets;
	}
	
	public void printTweets(){
		for(String news:twitfeed){
			System.out.println(news);			
		}
	}
	
	public static int getUserTotal() {
		return numUsers;
	}

	/*public static void setTotalUsers(int totalUsers) {
		User.numUsers = totalUsers;
	}*/

	public static int getTweetTotal() {
		return totalTweets;
	}

	/*public static void setTotalMessages(int totalMessages) {
		User.totalTweets = totalMessages;
	}*/
	
	public void acceptVisitor(VisitorPattern visitor){
		visitor.visit(this);
	}

	public void attach(ObserverPattern obj) {
		observers.add(obj);
		
	}

	public void detach(ObserverPattern obj) {
		observers.remove(obj);
	}

	public void notifyObservers() {
		ArrayList<ObserverPattern> presentObservers = new ArrayList<ObserverPattern>(this.observers);
		
		for(ObserverPattern obj: presentObservers){
			obj.Update();
		}
	}

	public List<String> getUpdate(ObserverPattern obj) {
		return this.twitfeed;		
	}
}
