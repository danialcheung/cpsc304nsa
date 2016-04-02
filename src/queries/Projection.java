package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tables.Table;
import main.AttrType;
import main.Pair;

public class Projection extends Query {

	private Table table;
	private List<Pair<AttrType, String>> attrs;
	
	public Projection(Connection con) {
		super(con);	
		attrs = new LinkedList<Pair<AttrType, String>>();
	}

	public void project() {
		table = selectTable();
		Pair<AttrType, String> attr = selectAttr(table.getAttrs(), "select an attribute:");
		while (attr != null) {
			attrs.add(attr);
			attr = selectAttr(table.getAttrs(), "select another attribute:", true);
		}
		
		runQuery("SELECT * FROM " + table.getName() + ";", attrs);	
	}

	/* get a list of names of people who own a given device type */
	public List<String> projectOwnerOfDeviceType(String deviceType) {
		List<String> owners = new ArrayList<String>();
		String query = "SELECT owner FROM device WHERE device_type LIKE \"" + deviceType + "\";";
		
        ResultSet rs = null;
        Statement statement = null; 
        try {           
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	owners.add(rs.getString("owner"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return owners;
	}

	
}