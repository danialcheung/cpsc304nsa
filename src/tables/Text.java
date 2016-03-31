package tables;

import java.util.Arrays;
import java.util.List;

import main.AttrType;
import main.Pair;

public class Text extends CommLog implements Table {

	private int length;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	@Override
	public List<Pair<AttrType, String>> getAttrs() {
		return Arrays.asList(new Pair<AttrType, String>(AttrType.INT, "data_id"),
				new Pair<AttrType, String>(AttrType.INT, "length"));
				}
	@Override
	public String getName() {
		return "text";
	}

}
