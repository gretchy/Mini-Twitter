package smoltwit;

import java.util.*;

public class User implements Visitation, ObservedUser{
	
	String userID; // unique user ID
	private static int totalTweets = 0;
	
	ArrayList<String> followers = new ArrayList<String>();
	ArrayList<String> following = new ArrayList<String>();
	ArrayList<String> twitfeed = new ArrayList<String>();
	ArrayList<ObserverPattern> observers;
	
	public User() {}
	
	public String getID() {
		return userID;
	}
	
	public void setID(String id) {
		userID = id;
	}
	
	public void addFollower(String user) {
		followers.add(user);
	}
	
	public void startFollowing(String user) {
		following.add(user);
		twitfeed.add(SmallTwit.allUsers.get(user).getTweets()); // adds tweets by following user
	}
	
	public void twit(String tweet){
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
	
	public String getFollowing(){
		String listFollowing = "";
		for(String person : following){
			listFollowing += person;
			listFollowing += "\n";
		}
		return listFollowing;
	}
	
	public String getTweets(){
		String listTweets = "";
		for(String twits : twitfeed){
			listTweets += twits;
		}
		return listTweets;
	}
	
	public static int getUserTotal() {
		return SmallTwit.allUsers.size();
	}

	public static int getTweetTotal() {
		return totalTweets;
	}

	@Override
	public void attach(ObserverPattern obj) {
		observers.add(obj);
		
	}

	@Override
	public void detach(ObserverPattern obj) {
		observers.remove(obj);
	}

	@Override
	public void notifyObservers() {
		ArrayList<ObserverPattern> presentObservers = new ArrayList<ObserverPattern>(this.observers);
		
		for(ObserverPattern obj: presentObservers){
			obj.Update();
		}
	}

	@Override
	public List<String> getUpdate(ObserverPattern obj) {
		return this.twitfeed;		
	}

	@Override
	public void acceptVisitation(VisitorPattern visitor) {
		visitor.goVisit(this);
		
	}
}
