package smoltwit;

import java.util.*;

import javax.swing.text.html.ListView;

public class User implements Visitation, ObservedUser {
	
	String userID; // unique user ID
	
	ArrayList<String> followers = new ArrayList<String>();
	ArrayList<String> following = new ArrayList<String>();
	ArrayList<String> twitfeed = new ArrayList<String>();
	ArrayList<String> allTwits = new ArrayList<String>();
	ArrayList<ObserverPattern> usersObservers;
	
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
		
		for(String twit : SmallTwit.allUsers.get(user).twitfeed){
			if(!allTwits.contains(twit))
				allTwits.add(twit); // adds tweets by following user
		}
	}
	
	public void twit(String tweet){
		twitfeed.add(tweet);
		allTwits.add(tweet);
		SmallTwit.allTweets.add(tweet);
		for(String person : followers){
			SmallTwit.allUsers.get(person).addTweet(tweet);
		}
	}
	
	public void addTweet(String tweet) {
		allTwits.add(tweet);
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
		for(String twits : allTwits){
			listTweets += twits;
		}
		return listTweets;
	}
	
	public static int getUserTotal() {
		return SmallTwit.allUsers.size();
	}

	public static int getTweetTotal() {
		return SmallTwit.allTweets.size();
	}

	@Override
	public void attach(ObserverPattern observer) {
		usersObservers.add(observer);
		
	}

	@Override
	public void detach(ObserverPattern observer) {
		usersObservers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		List<ObserverPattern> presentObservers = new ArrayList<>(usersObservers);
		
		for(ObserverPattern observer: presentObservers){
			observer.Update();
		}
	}

	@Override
	public List<String> getUpdate(ObserverPattern observer) {
		return twitfeed;		
	}

	@Override
	public void acceptVisitation(VisitorPattern visitor) {
		visitor.goVisit(this);
		
	}
}
