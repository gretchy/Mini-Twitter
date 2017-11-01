package smoltwit;

import java.util.*;

public class UserGroup {
	
	private String groupID; // unique ID used to group users
	private static int numGroups = 3;
	
	ArrayList<String> group = new ArrayList<String>(); // list stores the IDs of users and user groups
	ArrayList<UserGroup> allGroups = new ArrayList<UserGroup>();
	
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
		group.add(user.getID());
	}
	
	public void acceptVisitor(VisitorPattern visitor){
		visitor.visit(this);
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

	public static void setGroupTotal(int totalGroups) {
		numGroups = totalGroups;
	}
}
