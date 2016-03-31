package tables;

import java.util.List;

import main.AttrType;
import main.Pair;

public interface Table {
	public List<Pair<AttrType,String>> getAttrs();
	public String getName();
	
}
