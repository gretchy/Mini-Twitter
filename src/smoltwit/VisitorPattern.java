package smoltwit;

public interface VisitorPattern {
	
	public void visit(User user);
	public void visit(UserGroup group);

}
