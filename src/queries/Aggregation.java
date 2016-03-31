package queries;

import java.sql.Connection;
import java.util.Arrays;

import main.AttrType;
import main.Pair;
import tables.Table;

public class Aggregation extends Query {
	
	private Table table;
	
	public Aggregation(Connection con) {
		super(con);
	}
	
	public void aggregate() {
		table = selectTable();
		
		int aggrTypeInt = selectGeneric("select type of aggregation:", Arrays.asList("average", "count", "min", "max"));
		String aggrType = "";
		switch (aggrTypeInt) {
		case 1: aggrType = "AVG"; break;
		case 2: aggrType = "COUNT"; break;
		case 3: aggrType = "MIN"; break;
		case 4: aggrType = "MAX"; break;
		default:
			System.out.println("invalid input");
		}

		Pair<AttrType, String> aggrAttr = selectAttr(table, "select attribute to aggregate by:");

//		runQuery( table.getName() + aggrType + aggrAttr.getRight() , Arrays.asList());
	}

}
