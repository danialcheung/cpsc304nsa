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
	public void changeDateOfData(Integer data_id, String date, UpdateTableFrame frame) {
//		date is formatted as YYYYMMDD
		Integer dateInt = Integer.valueOf(date);
		String update = "UPDATE data SET date = " + dateInt + " WHERE data_id = " + data_id + ";";
		
		if (!isDateValid(date)) {
        	ErrorFrame error = new ErrorFrame(frame, "Invalid Date");
            error.setVisible(true);
        }
		
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
		changeDateOfData(Integer.valueOf(data_id), newDate, frame);
		return selectAllData();
	}

}
