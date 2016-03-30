package cpsc304nsa.tables;

import java.util.Arrays;
import java.util.List;

import cpsc304nsa.main.AttrType;
import cpsc304nsa.main.Pair;

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
		return Arrays.asList(new Pair(AttrType.INT, "data_id"),
				new Pair(AttrType.INT, "length"));
				}
	@Override
	public String getName() {
		return "text";
	}

}
