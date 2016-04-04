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

public class Division extends Query {

	public Division(Connection con) {
		super(con);
	}

	public void divide() {
		Table dividend = selectTable("select dividend table:");
		Table divisor = selectTable("select divisor table:");
		
		List<Pair<AttrType, String>> divisibleAttrs = new ArrayList<Pair<AttrType, String>>();
		while (divisibleAttrs.isEmpty()) {
			for (Pair<AttrType, String> attr: dividend.getAttrs()) {
				if (divisor.getAttrs().contains(attr)) {
					divisibleAttrs.add(attr);
				}
			}
			
			if (divisibleAttrs.isEmpty()) {
				System.out.println("no common attributes between " + dividend.getName() + " and " + divisor.getName());
				divisor = selectTable("select divisor table:");
			}
		}
		
		Pair<AttrType, String> attr = selectAttr(divisibleAttrs, "select attribute to divide by:");

		String query = buildQuery(dividend.getName(), divisor.getName(), attr.getRight(), dividend.primaryKey());	
		runQuery(query,Arrays.asList());

}

	private String buildQuery(String dividend, String divisor, String attr, String primaryKey) {
		// TODO fix this
		String q = "";
		q += "SELECT DISTINCT d1." + primaryKey + " as " + primaryKey + "\n";
		q += "FROM " +  dividend + " d1\n";
		q += "WHERE NOT EXISTS\n";
		q += 	"(SELECT *\n";
		q +=	"FROM " + divisor + "\n";
		q += 	"WHERE NOT EXISTS\n";
		q += "(SELECT *\n";
		q += "FROM " + dividend + " d2 ";
		q += "WHERE d2." + primaryKey + " = d1." + primaryKey + "\n";
		q += "AND d2." + attr + " = " + divisor + "." + attr + "));";

//		SELECT DISTINCT d1.y as y
//		FROM dividend d1
//		WHERE NOT EXISTS
//		(SELECT * 
//		from divisor 
//		WHERE NOT EXISTS 
//		(SELECT * 
//		FROM dividend d2 
//		WHERE d2.y = d1.y
//		AND d2.attr = divisor.attr));		
		return q;
	}
	
	private JTable potentialNewPersonsOfInterest() {
		String[] columnNames = {"person_of_interest"};
		ArrayList<List<Object>> data = new ArrayList<List<Object>>();

		String query = 
				"SELECT DISTINCT owner\n" +
				"FROM device, data, commlog, (\n" +
				"	SELECT DISTINCT device.device_id\n" +
				"	FROM data, device\n" +
				"	WHERE Data.device_id = Device.device_id AND Data.suspicious = 1\n" +
				"	) AS suspects\n" +
				"WHERE device.device_id = data.device_id\n" +
				"AND\n" +
				"Data.data_id = Commlog.data_id\n" +
				"AND \n" +
				"Commlog.sender = suspects.device_id\n" +
				"AND\n" +
				"Device.device_id = Commlog.reciever\n" +
				"AND \n" +
				"data.suspicious = 0;\n";
		
		ResultSet rs = null;
		Statement statement = null; 
		try {           
		    statement = con.createStatement();
		    rs = statement.executeQuery(query);
		    while (rs.next()) {
		    	data.add(Arrays.asList(
		    			rs.getString("owner")));
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
		return potentialNewPersonsOfInterest();
	}
}