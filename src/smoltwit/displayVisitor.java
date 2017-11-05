package smoltwit;

// Visitor is implemented in this class
public class displayVisitor implements VisitorPattern {
	private int totalUsers, totalTweets;	

	public int getTotalUsers() {
		return totalUsers;
	}

	public int getTotalTweets() {
		return totalTweets;
	}
	
	@Override
	public void goVisit(User user) {
		totalUsers = User.getUserTotal();
		totalTweets = User.getTweetTotal();
	}

	@Override
	public void goVisit(UserGroup group) {}
}
