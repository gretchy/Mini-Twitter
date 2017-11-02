package smoltwit;

// Observer is implemented in this class
public class Observation implements ObserverPattern{
	private String name;
	private ObservedUser topic;
	
	public Observation(String name){
		setName(name);
	}

	@Override
	public void Update() {}

	@Override
	public void setSubject(ObservedUser sub) {
		this.topic = sub;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
