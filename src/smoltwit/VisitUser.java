package smoltwit;

import java.util.List;

// Visitor is implemented in this class
public class VisitUser {
	private List<Visitation> items;
	
	public int showUserTotal(){
		displayVisitor visitor = new displayVisitor();
		
		for(Visitation item: items){
			item.acceptVisitation(visitor);
		}
		return visitor.getTotalUsers();
		
	}
	
	public int showGroupTotal(){
		displayVisitor visitor = new displayVisitor();
		
		for(Visitation item: items){
			item.acceptVisitation(visitor);
		}
		return visitor.getTotalGroups();
	}
	
	public int showMessageTotal(){
		displayVisitor visitor = new displayVisitor();
		
		for(Visitation item: items){
			item.acceptVisitation(visitor);
		}
		return visitor.getTotalTweets();
	}
}
