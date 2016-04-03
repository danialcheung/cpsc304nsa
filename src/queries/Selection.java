package queries;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import GUI.TableWindow;
import main.AttrType;
import main.Pair;
import tables.Table;

public class Selection extends Query {
		
	public Selection(Connection con) {
		super(con);
	}
	
	public void select() {
		Table table = selectTable();
		Stack<Pair<Pair<AttrType, String>, String>> attrVals = new Stack<Pair<Pair<AttrType,String>,String>>();
		Pair<AttrType, String> attr;
		String val;
		
		do {
			attr = selectAttr(table.getAttrs(), "select an attribute:", true);
			if (attr == null) { break; }
			val = enterInput();
			if (val == "") { break; }
			attrVals.push(new Pair(attr,val));
		} while (attr != null && val != "");

		String query = buildQuery(table, attrVals);
		runQuery(query, table.getAttrs());

		int ATTR_SIZE = table.getAttrs().size();
		String[] attrNames = new String[ATTR_SIZE];

		for (int i = 0; i < ATTR_SIZE-1; i++){
			attrNames[i] = table.getAttrs().get(i).getRight();
		}

		TableWindow tableWindow = new TableWindow();
		tableWindow.initializeTables(attrNames);
		tableWindow.setVisible(true);


	}
	
	private String buildQuery(Table table, Stack<Pair<Pair<AttrType, String>, String>> attrVals) {
        String query = "SELECT * FROM " + table.getName();
        Pair<Pair<AttrType,String>,String> av;
        if (attrVals.size() > 0) {
        	av  = attrVals.pop();
        	query += " WHERE " +  attrEqualsValString(av.getLeft(),av.getRight());
        }
        
        while (attrVals.size() > 0) {
        	av = attrVals.pop();
        	query += " AND " + attrEqualsValString(av.getLeft(),av.getRight());
        }
        
        query += ";";
        return query;
	}
	
	/* get data in a given country */
	public List<List<String>> selectDataFromCountry(String country) {
		List<List<String>> table = new ArrayList<List<String>>();
		table.add(Arrays.asList("data_id", "date", "suspicious", "lat", "lng", "device_id"));
		
		String query = "SELECT data.* FROM data, location WHERE data.lat = location.lat AND data.lng = location.lng "
				+ "AND location.country LIKE \"" + country + "\";";
		
        ResultSet rs = null;
        Statement statement = null;

        try {
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	List<String> row = Arrays.asList(
            			String.valueOf(rs.getInt("data_id")),
            			String.valueOf(rs.getDate("date")),
            			(rs.getBoolean("suspicious")) ? "true" : "false",
            			String.valueOf(rs.getFloat("lat")),
            			String.valueOf(rs.getFloat("lng")),
            			String.valueOf(rs.getInt("device_id")));
            	table.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
	}

}
