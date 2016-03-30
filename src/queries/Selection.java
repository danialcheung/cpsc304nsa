package queries;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Stack;

import cpsc304nsa.main.Pair;
import cpsc304nsa.tables.Device;
import cpsc304nsa.tables.Table;

public class Selection extends Query {
	
	private Table table;
	private Stack<Pair<String,String>> attrVals;
	
	public Selection(Connection con) {
		super(con);
		this.attrVals = new Stack<Pair<String,String>>();
	}
	
	public void select() {
		table = selectTable();
		String attr;
		String val;
		
		do {
			// There is nothing here to protect duplicate attrs
			attr = selectAttr(table);
			val = enterVal();
			attrVals.push(new Pair(attr,val));
		} while (attr != "" && val != "");
		attrVals.pop();
		
		String query = buildQuery();
		runQuery(query);
    
	}
	
	private String buildQuery() {
        String query = "SELECT * FROM " + table.getName();
        Pair<String,String> av;
        if (attrVals.size() > 0) {
        	av  = attrVals.pop();
        	query += " WHERE " +  av.getLeft() + "=" + av.getRight();
        }
        
        while (attrVals.size() > 0) {
        	av = attrVals.pop();
        	query += " AND " + av.getLeft() + "=" + av.getRight();
        }
        
        query += ";";
        return query;
		
	}

	
	private String enterVal() {
		
		return "";
	}
	

	private void runQuery(String query)  {      
        ResultSet rs = null;
        Statement statement = null; 
         
        try {           
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	
            	System.out.println(rs.getInt("device_id") + ", " + rs.getString("owner") +", " +        	
            			rs.getString("model") + ", " + rs.getString("device_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    	
}
