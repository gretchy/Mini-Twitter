package smoltwit;

import java.text.DateFormat;
import java.util.*;

//Observer and Visitor is implemented in this class
public class User implements Visitation, ObservedUser {
	
	String userID; // unique user ID
	private long creationTime; // stores the time when user object is created
	private long lastUpdateTime; // stores the time when user last updated
	
	ArrayList<String> followers = new ArrayList<String>(); // stores the IDs of user's followers
	ArrayList<String> following = new ArrayList<String>(); // stores the IDs that user is following
	ArrayList<String> twitfeed = new ArrayList<String>(); // stores only the personal tweets made by user
	ArrayList<String> allTwits = new ArrayList<String>(); // stores all tweets on user's newsfeed (including tweets from those that user is following)
	ArrayList<ObserverPattern> usersObservers;
	
	public User() { // constructor
		creationTime = System.currentTimeMillis();
		lastUpdateTime = System.currentTimeMillis();
	}
	
	public String getID() { // returns user ID
		return userID;
	}
	
	public void setID(String id) { // sets the user ID
		userID = id;
	}
	
	public long getCreationTime() { // returns time when user object was created
		return creationTime;
	}
	
	public long getUpdateTime() { // returns time when user last tweeted/updated
		return lastUpdateTime;
	}
	
	public void setUpdateTime(long newTime) { // sets new update time when someone the user is following tweets
		lastUpdateTime = newTime;
	}
	
	public void addFollower(String user) { // adds new follower of user
		followers.add(user);
	}
	
	public void startFollowing(String user) { // adds to following ArrayList
		following.add(user);
		
		for(String twit : SmallTwit.allUsers.get(user).twitfeed){ // adds tweets by following user
			if(!allTwits.contains(twit)) // checks for duplicate tweets
				allTwits.add(twit); 
		}
	}
	
	public void twit(String tweet){ // function that allows user to post a tweet to their newsfeed/other's newsfeeds
		lastUpdateTime = System.currentTimeMillis();
		twitfeed.add(tweet);
		allTwits.add(tweet);
		SmallTwit.allTweets.add(tweet);
		for(String person : followers){
			SmallTwit.allUsers.get(person).addTweet(tweet);
			SmallTwit.allUsers.get(person).setUpdateTime(lastUpdateTime);
		}
	}
	
	public void addTweet(String tweet) { // adds a tweet to a user's allTwits Arraylist (usually for any new tweets made by following)
		allTwits.add(tweet);
	}
	
	public String getFollowers(){ // returns the followers of a user
		String listFollowers = "";
		for(String person : followers){
			listFollowers += person;
			listFollowers += "\n";
		}
		return listFollowers;
	}
	
	public String getFollowing(){ // return the list of IDs a user is following
		String listFollowing = "";
		for(String person : following){
			listFollowing += person;
			listFollowing += "\n";
		}
		return listFollowing;
	}
	
	public String getTweets(){ // gets all the tweets from a user's newsfeed (personal and ones made by others)
		String listTweets = "";
		for(String twits : allTwits){
			listTweets += twits;
		}
		return listTweets;
	}
	
	public static int getUserTotal() { // returns thhe total amount of users in Small Twitter
		return SmallTwit.allUsers.size();
	}

	public static int getTweetTotal() { // returns total amount of tweets made in Small Twitter
		return SmallTwit.allTweets.size();
	}

	
	// Overrides for the Observer/Visitor pattern
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
