package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTable;

import GUI.ErrorFrame;
import GUI.UpdateTableFrame;

public class Update extends Query {

	public Update(Connection con) {
		super(con);
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	/* mark data as suspicious */
	public void changeDateOfData(Integer data_id, Integer date, UpdateTableFrame frame) {
//		date is formatted as YYYYMMDD
		String update = "UPDATE data SET date = " + date + " WHERE data_id = " + data_id + ";";
		
        Statement statement = null; 
        try {  
            statement = con.createStatement();
            statement.executeUpdate(update); 
        } catch (SQLException e) {
            e.printStackTrace();
            ErrorFrame error = new ErrorFrame(frame, "Invalid Date");
            error.setVisible(true);
        } 
	}
	
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	
	final static String DATE_FORMAT = "YYYYMMDD";

	public static boolean isDateValid(String date) 
	{
	        try {
	            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
	            df.setLenient(false);
	            df.parse(date);
	            return true;
	        } catch (ParseException e) {
	            return false;
	        }
	}
	
	public JTable doUpdate(String data_id, String newDate, UpdateTableFrame frame) {
		changeDateOfData(Integer.valueOf(data_id), Integer.valueOf(newDate), frame);
		return selectAllData();
	}

}
