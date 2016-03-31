package tables;

import java.util.Arrays;
import java.util.List;

import main.AttrType;
import main.Pair;

public class User implements Table {

	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public List<Pair<AttrType, String>> getAttrs() {
		return Arrays.asList(new Pair<AttrType, String>(AttrType.INT, "user_id"));
	}

	@Override
	public String getName() {
		return "user";
	}
}
