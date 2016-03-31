package tables;

public class Police extends User implements Table {

	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
