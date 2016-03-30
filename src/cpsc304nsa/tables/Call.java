package cpsc304nsa.tables;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import cpsc304nsa.main.AttrType;
import cpsc304nsa.main.Pair;

public class Call extends CommLog implements Table{

	private Timestamp start;
	private Timestamp end;
	
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	
	@Override
	public List<Pair<AttrType, String>> getAttrs() {
		return Arrays.asList(new Pair(AttrType.INT, "data_id"),
				new Pair(AttrType.DATETIME, "beginning"),
				new Pair(AttrType.DATETIME, "end"));
	}
	@Override
	public String getName() {
		return "`call`";
	}

}
