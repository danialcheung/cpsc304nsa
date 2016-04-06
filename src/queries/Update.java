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
	public void changeDateOfData(Integer data_id, Integer date) throws SQLException {
//		date is formatted as YYYYMMDD
		String update = "UPDATE data SET date = " + date + " WHERE data_id = " + data_id + ";";
		
        Statement statement = null; 
        statement = con.createStatement();
        statement.executeUpdate(update); 
	}
	
	public JTable doUpdate(String data_id, String newDate) throws NumberFormatException, SQLException {
		changeDateOfData(Integer.valueOf(data_id), Integer.valueOf(newDate));
		return selectAllData();
	}

}
