package smoltwit;

import java.util.*;

public class UserGroup implements Visitation{
	
	private String groupID; // unique ID used to group users
	private static int numGroups = 2;
	
	ArrayList<String> allGroups = new ArrayList<String>(); // list stores the IDs of users and user groups
	
	public UserGroup() {
		numGroups++;
	}
	
	public String getID() {
		return groupID;
	}
	
	public void setID(String id) {
		groupID = id;
	}
	
	public void addToGroup(User user) {
		allGroups.add(user.getID());
	}	
	
	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String id) {
		groupID = id;
	}

	public static int getGroupTotal() {
		return numGroups;
	}
	
	@Override
	public void acceptVisitation(VisitorPattern visitor){
		visitor.goVisit(this);
	}
}
