package smoltwit;

public class displayInfo implements VisitorPattern {
	private int totalUsers, totalTweets, totalGroups;	
	
	@Override
	public void visit(User user) {
		setTotalUsers(User.getUserTotal());
		setTotalTweets(User.getTweetTotal());
	}

	@Override
	public void visit(UserGroup group) {
		setTotalGroups(UserGroup.getGroupTotal());
		
	}

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
}
