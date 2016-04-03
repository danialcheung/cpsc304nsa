package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;

import main.AttrType;
import main.Pair;
import tables.Table;

public class NestedAggregationWithGroupBy extends Query {
	
	public NestedAggregationWithGroupBy(Connection con) {
		super(con);
	}

	public void nestedAggregateWithGroupBy() {
		Table table = selectTable();

		Pair<AttrType, String> groupAttr = selectAttr(table.getAttrs(), "select attribute to group by:");
		Pair<AttrType, String> avgAttr = selectAttr(table.getAttrs(), "select attribute to average:");

		if (avgAttr.getLeft() == AttrType.STRING) {
			System.out.println("cannot average string data, query failed");
			return;
		}
		
		int aggrTypeInt = selectGeneric("aggregation by min or max:", Arrays.asList("min", "max"));
		String aggrType = "";
		switch (aggrTypeInt) {
		case 1: aggrType = "MIN"; break;
		case 2: aggrType = "MAX"; break;
		default:
			System.out.println("invalid input");
		}
		
		// TODO use aggrType
		String query = "SELECT " + groupAttr.getRight() + ", " + "AVG(" + avgAttr.getRight() + ") FROM " + table.getName() + " GROUP BY " + groupAttr.getRight() + ";";
		runQuery(query, Arrays.asList(groupAttr, new Pair<AttrType, String>(avgAttr.getLeft(), "AVG(" + avgAttr.getRight() + ")")));
	}
	
	/* get country with the min/max avg transaction amounts  */
	public JTable countryWithMinMAxAvgTransactions(Boolean isMax) {
		String[] columnNames = {"country", (isMax ? "max_avg_suspicious_amount" : "min_avg_suspicious_amount")};
		ArrayList<List<Object>> data = new ArrayList<List<Object>>();

		String query = 
				"SELECT country, max_amount  " +
				"FROM  " +
				"( " +
				"    SELECT country, avg(amount) AS max_amount  " +
				"    FROM transaction, location, data  " +
				"    WHERE data.lat = location.lat AND data.lng = location.lng AND data.data_id = transaction.data_id " +
				"    GROUP BY country " +
				"    ) AS avg " +
				"WHERE max_amount = ( " +
				"    SELECT  " + (isMax ? "MAX" : "MIN") + "(avg_amount)  " +
				"    FROM ( " +
				"        SELECT country, avg(amount) as avg_amount  " +
				"        FROM transaction, location, data " +
				"        WHERE data.lat = location.lat AND data.lng = location.lng AND data.data_id = transaction.data_id " +
				"        GROUP BY country " +
				"        ) AS max " +
				"    );";
		
        ResultSet rs = null;
        Statement statement = null; 
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	data.add(Arrays.asList(
            			rs.getString("country"),
            			rs.getFloat("max_amount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        Object[][] dataArray = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            List<Object> row = data.get(i);
            dataArray[i] = row.toArray(new Object[row.size()]);
        }

		return new JTable(dataArray, columnNames);
	}
	
	@Override
	public JTable doQuery(String arg) {
		Boolean isMax = !arg.toLowerCase().equals("min");
		return countryWithMinMAxAvgTransactions(isMax);
		}


}
