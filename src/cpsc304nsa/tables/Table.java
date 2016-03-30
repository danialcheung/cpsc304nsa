package cpsc304nsa.tables;

import java.util.List;

import cpsc304nsa.main.AttrType;
import cpsc304nsa.main.Pair;

public interface Table {
	public List<Pair<AttrType,String>> getAttrs();
	public String getName();
	
}
