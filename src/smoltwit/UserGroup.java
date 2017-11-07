package smoltwit;

import java.util.*;

// Visitor is implemented in this class
public class UserGroup implements Visitation{
	
	private String groupID; // unique ID used to group users
	private static int numGroups = 2; // stores the total amount of groups in the tree
	
	ArrayList<String> groupMems = new ArrayList<String>(); // list stores the IDs of users in the group
	
	public UserGroup() { // constructor
		numGroups++;
	}
	
	public String getID() { // returns group ID
		return groupID;
	}
	
	public void setID(String id) { // sets the ID for a user group
		groupID = id;
	}
	
	public void addToGroup(User user) { // adds user to the group
		groupMems.add(user.getID());
	}	

	public static int getGroupTotal() { // returns total number of groups in the tree
		return numGroups;
	}
	
	// Overrides for the Visitor pattern
	@Override
	public void acceptVisitation(VisitorPattern visitor){
		visitor.goVisit(this);
	}
}
