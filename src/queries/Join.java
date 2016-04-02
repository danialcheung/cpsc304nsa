package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Join extends Query {

	public Join(Connection con) {
		super(con);
		joinOwnerWithData("Mark Ruffalo");
	}

	public void join() {
		// TODO Auto-generated method stub
		
	}
	
	/* join a list of data (data_id) with its owner */
	public List<List<String>> joinOwnerWithData(String owner) {
		List<List<String>> table = new ArrayList<List<String>>();
		table.add(Arrays.asList("owner", "data_id", "date", "suspicious"));
		
		String query = "SELECT owner, data_id, date, suspicious FROM device, data WHERE device.device_id = data.device_id;";
		
        ResultSet rs = null;
        Statement statement = null; 
        try {           
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	table.add(Arrays.asList(
            			rs.getString("owner"),
            			String.valueOf(rs.getInt("data_id")),
            			String.valueOf(rs.getDate("date")),
            			(rs.getBoolean("suspicious")) ? "true" : "false"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

		return table;
	}

}
