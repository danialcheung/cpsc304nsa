package queries;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import main.AttrType;
import main.Pair;
import tables.Device;
import tables.Table;

public class Selection extends Query {
	
	private Table table;
	private Stack<Pair<Pair<AttrType, String>, String>> attrVals;
	
	public Selection(Connection con) {
		super(con);
		this.attrVals = new Stack<Pair<Pair<AttrType,String>,String>>();
	}
	
	public void select() {
		table = selectTable();
		Pair<AttrType, String> attr;
		String val;
		
		do {
			attr = selectAttr(table.getAttrs(), "select an attribute:", true);
			if (attr == null) { break; }
			val = enterInput();
			if (val == "") { break; }
			attrVals.push(new Pair(attr,val));
		} while (attr != null && val != "");
		
		String query = buildQuery();
		runQuery(query, table.getAttrs());
    
	}
	
	private String buildQuery() {
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
	
	/* get a list of names of people who own a given device type */
	public List<String> selectOwnerByDeviceType(String deviceType) {
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
