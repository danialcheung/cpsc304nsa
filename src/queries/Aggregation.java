package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	public List<Pair<String,Integer>> countSuspiciousDataByCountry() {
		List<Pair<String,Integer>> countryCount = new ArrayList<Pair<String,Integer>>();
		String query = "SELECT country, COUNT(data_id)\n"
				+ "FROM data, location\n"
				+ "WHERE data.lat = location.lat AND data.lng = location.lng AND suspicious = 1\n"
				+ "GROUP BY country;";
		
        ResultSet rs = null;
        Statement statement = null; 
        try {           
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	countryCount.add(new Pair<String,Integer>(rs.getString("country"),rs.getInt("COUNT(data_id)")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

		return countryCount;	
	}
}
