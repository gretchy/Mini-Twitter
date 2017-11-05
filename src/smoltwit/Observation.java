package smoltwit;

// Observer is implemented in this class
public class Observation implements ObserverPattern{
	private String name;
	private ObservedUser user;
	
	public Observation(String name){
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}
	
	@Override
	public void Update() {}

	@Override
	public void userToObserve(ObservedUser u) {
		user = u;
	}
}
