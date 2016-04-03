package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;

public class Join extends Query {

	public Join(Connection con) {
		super(con);
	}

	public void join() {
		// TODO Auto-generated method stub
		
	}
	
	/* join a list of data (data_id) with its owner */
	public JTable joinOwnerWithData() {
		String[] columnNames = {"owner", "data_id", "date", "suspicious"};
		ArrayList<List<Object>> data = new ArrayList<List<Object>>();

		String query = "SELECT owner, data_id, date, suspicious FROM device, data WHERE device.device_id = data.device_id ORDER BY owner;";
		
        ResultSet rs = null;
        Statement statement = null; 
        try {           
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	data.add(Arrays.asList(
            			rs.getString("owner"),
            			rs.getInt("data_id"),
            			rs.getDate("date"),
            			rs.getBoolean("suspicious")));
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
		return joinOwnerWithData();
	}

}
