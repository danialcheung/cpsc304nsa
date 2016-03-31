package tables;

import java.util.Arrays;
import java.util.List;

import main.AttrType;
import main.Pair;

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
		return Arrays.asList(new Pair<AttrType, String>(AttrType.INT, "data_id"),
				new Pair<AttrType, String>(AttrType.INT, "size"));
				}
	@Override
	public String getName() {
		return "photo";
	}

}
