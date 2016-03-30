package queries;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import cpsc304nsa.tables.Table;
import cpsc304nsa.main.AttrType;
import cpsc304nsa.main.Pair;

public class Projection extends Query {

	private Table table;
	private List<Pair<AttrType, String>> attrs;
	
	public Projection(Connection con) {
		super(con);	
		attrs = new LinkedList<Pair<AttrType, String>>();
	}

	public void project() {
		table = selectTable();
		Pair<AttrType, String> attr = selectAttr(table);
		while (attr != null) {
			attrs.add(attr);
			attr = selectAttr(table);
		}
		
		runQuery("SELECT * FROM " + table.getName() + ";", attrs);
		
	}

}