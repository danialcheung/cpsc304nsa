package cpsc304nsa.tables;

import java.util.Arrays;
import java.util.List;

import cpsc304nsa.main.AttrType;
import cpsc304nsa.main.Pair;

public class Photo extends Data implements Table {

	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public List<Pair<AttrType, String>> getAttrs() {
		return Arrays.asList(new Pair(AttrType.INT, "data_id"),
				new Pair(AttrType.INT, "size"));
				}
	@Override
	public String getName() {
		return "photo";
	}

}
