package queries;

import java.sql.Connection;
import java.util.Arrays;

import main.AttrType;
import main.Pair;
import tables.Table;

public class NestedAggregationWithGroupBy extends Query {
	
	private Table table;
	
	public NestedAggregationWithGroupBy(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	public void nestedAggregateWithGroupBy() {
		table = selectTable();

		Pair<AttrType, String> groupAttr = selectAttr(table, "select attribute to group by:");
		Pair<AttrType, String> avgAttr = selectAttr(table, "select attribute to average by:");

		int aggrTypeInt = selectGeneric("aggregation by min or max:", Arrays.asList("min", "max"));
		String aggrType;
		switch (aggrTypeInt) {
		case 1: aggrType = "MIN"; break;
		case 2: aggrType = "MAX"; break;
		default:
			System.out.println("invalid input");
		}


//		runQuery( ... );
	}

}
