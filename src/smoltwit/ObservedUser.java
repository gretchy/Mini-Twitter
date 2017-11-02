package smoltwit;

import java.util.List;

// Observer is implemented in this interface
public interface ObservedUser {
	public void attach(ObserverPattern observer);
	public void detach(ObserverPattern observer);
	public void notifyObservers();
	public List<String> getUpdate(ObserverPattern observer);
}
