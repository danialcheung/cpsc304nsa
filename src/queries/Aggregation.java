package queries;

import java.sql.Connection;
import java.util.Arrays;

import main.AttrType;
import main.Pair;
import tables.Table;

public class Aggregation extends Query {
	
	
	public Aggregation(Connection con) {
		super(con);
		// (1 point) Aggregation query: pick one query that requires the use of aggregation (min, max, average, or count are all fine).

	}
	
	public void aggregate() {
		Table table = selectTable();
		
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

		Pair<AttrType, String> aggrAttr = selectAttr(table.getAttrs(), "select attribute to aggregate by:");
		
		String query = "SELECT " + aggrType + "(" + aggrAttr.getRight() + ") FROM " + table.getName() + ";";
		runQuery(query, Arrays.asList(new Pair<AttrType, String>(aggrAttr.getLeft(), aggrType + "(" + aggrAttr.getRight() + ")")));
	}

}
