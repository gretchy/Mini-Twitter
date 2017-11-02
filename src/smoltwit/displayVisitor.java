package smoltwit;

// Visitor is implemented in this class
public class displayVisitor implements VisitorPattern {
	private int totalUsers, totalTweets, totalGroups;	

	public int getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}
	
	public int getTotalGroups() {
		return totalGroups;
	}

	public void setTotalGroups(int totalGroups) {
		this.totalGroups = totalGroups;
	}

	public int getTotalTweets() {
		return totalTweets;
	}

	public void setTotalTweets(int totalMessages) {
		this.totalTweets = totalMessages;
	}
	
	@Override
	public void goVisit(User user) {
		setTotalUsers(User.getUserTotal());
		setTotalTweets(User.getTweetTotal());
	}

	@Override
	public void goVisit(UserGroup group) {
		setTotalGroups(UserGroup.getGroupTotal());
		
	}
}
