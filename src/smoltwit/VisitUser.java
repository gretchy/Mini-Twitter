package smoltwit;

import java.util.List;

// Visitor is implemented in this class
public class VisitUser {
	private List<Visitation> users;
	
	public int showUsers(){
		displayVisitor visitor = new displayVisitor();
		
		for(Visitation person: users){
			person.acceptVisitation(visitor);
		}
		return visitor.getTotalUsers();
		
	}
	
	public int showTweets(){
		displayVisitor visitor = new displayVisitor();
		
		for(Visitation person: users){
			person.acceptVisitation(visitor);
		}
		return visitor.getTotalTweets();
	}
}
