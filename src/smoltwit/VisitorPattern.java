package smoltwit;

// Visitor is implemented in this interface
public interface VisitorPattern {
	
	public void goVisit(User user);
	public void goVisit(UserGroup group);
}
