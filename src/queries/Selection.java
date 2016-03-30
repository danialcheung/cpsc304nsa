package queries;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Stack;

import cpsc304nsa.main.AttrType;
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
			if (attr == "") { break; }
			val = enterInput();
			if (val == "") { break; }
			attrVals.push(new Pair(attr,val));
		} while (attr != "" && val != "");
		
		String query = buildQuery();
		System.out.println("query: " + query);
		runQuery(query, table.getAttrs());
    
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

	private void runQuery(String query, List<Pair<AttrType, String>> attrs)  {      
        ResultSet rs = null;
        Statement statement = null; 
         
        try {           
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	for (int i = 0; i < attrs.size(); i++) {
            		Pair<AttrType, String> attr = attrs.get(i);
            		switch (attr.getLeft()) {
            		case INT: 
            			System.out.print(attr.getRight() + ": " + rs.getInt(attr.getRight()));
            			break;
            		case BOOL:
	        			System.out.print(attr.getRight() + ": " + rs.getBoolean(attr.getRight()));
	        			break;
            		case FLOAT: 
	        			System.out.print(attr.getRight() + ": " + rs.getFloat(attr.getRight()));
	        			break;
            		case STRING: 
	        			System.out.print(attr.getRight() + ": " + rs.getString(attr.getRight()));
	        			break;
            		case DATETIME: 
	        			System.out.print(attr.getRight() + ": " + rs.getDate(attr.getRight()));
	        			break;
            		default:
	        			System.out.println("something went wrong");
            			break;
            		}
            		if (i + 1 < attrs.size()) {
            			System.out.print(", ");
            		}
            	}
        		System.out.println("");
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
