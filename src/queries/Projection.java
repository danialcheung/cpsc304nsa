package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;

import tables.Table;
import main.AttrType;
import main.Pair;

public class Projection extends Query {

	
	public Projection(Connection con) {
		super(con);	
	}

	public void project() {
		List<Pair<AttrType, String>> attrs = new LinkedList<Pair<AttrType, String>>();
		Table table = selectTable();
		Pair<AttrType, String> attr = selectAttr(table.getAttrs(), "select an attribute:");
		while (attr != null) {
			attrs.add(attr);
			attr = selectAttr(table.getAttrs(), "select another attribute:", true);
		}
		
		runQuery("SELECT * FROM " + table.getName() + ";", attrs);	
	}

	/* get a list of names of people who own a given device type */
	public JTable projectOwnerOfDeviceType(String deviceType) {
		String[] columnNames = {"deviceType", "owner"};
		ArrayList<List<Object>> data = new ArrayList<List<Object>>();
		
		String query = "SELECT owner FROM device WHERE device_type LIKE \"" + deviceType + "\";";
		
        ResultSet rs = null;
        Statement statement = null; 
        try {           
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	data.add(Arrays.asList(deviceType, rs.getString("owner")));
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
		return projectOwnerOfDeviceType(arg);
	}

}