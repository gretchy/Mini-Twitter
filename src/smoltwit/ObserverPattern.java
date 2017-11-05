package smoltwit;

// Observer is implemented in this interface
public interface ObserverPattern {
	
	public void Update();
	public void userToObserve(ObservedUser user);
}
