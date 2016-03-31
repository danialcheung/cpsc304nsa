package queries;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import tables.Table;
import main.AttrType;
import main.Pair;

public class Projection extends Query {

	private Table table;
	private List<Pair<AttrType, String>> attrs;
	
	public Projection(Connection con) {
		super(con);	
		attrs = new LinkedList<Pair<AttrType, String>>();
	}

	public void project() {
		table = selectTable();
		Pair<AttrType, String> attr = selectAttr(table, "select an attribute:");
		while (attr != null) {
			attrs.add(attr);
			attr = selectAttr(table, "select another attribute:");
		}
		
		runQuery("SELECT * FROM " + table.getName() + ";", attrs);
		
	}

}