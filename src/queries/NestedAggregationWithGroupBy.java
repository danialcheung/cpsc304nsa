package queries;

import java.sql.Connection;
import java.util.Arrays;

import main.AttrType;
import main.Pair;
import tables.Table;

public class NestedAggregationWithGroupBy extends Query {
	
	
	public NestedAggregationWithGroupBy(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
		// (2 points) Nested aggregation with group-by: pick one query that finds the average for each group and then finds either the minimum or maximum across all those averages. Provide an interface for the user to specify whether the minimum or maximum is requested.

	}

	public void nestedAggregateWithGroupBy() {
		Table table = selectTable();

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
