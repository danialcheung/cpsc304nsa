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

import javax.swing.JTable;

import GUI.Login2;
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

		for (int i = 0; i < ATTR_SIZE; i++){
			attrNames[i] = table.getAttrs().get(i).getRight();
		}

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
	public JTable selectDataFromCountry(String country) {
		String[] columnNames = {"data_id", "date", "suspicious", "lat", "lng", "device_id"};
		ArrayList<List<Object>> data = new ArrayList<List<Object>>();
		
		String query = "SELECT data.* FROM data, location WHERE data.lat = location.lat AND data.lng = location.lng "
				+ "AND location.country LIKE \"" + country + "\";";
		
        ResultSet rs = null;
        Statement statement = null;

        try {
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	data.add(Arrays.asList(
            			rs.getInt("data_id"),
            			rs.getDate("date"),
            			rs.getBoolean("suspicious"),
            			rs.getFloat("lat"),
            			rs.getFloat("lng"),
            			rs.getInt("device_id")));
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
		return selectDataFromCountry(arg);
	}
}
