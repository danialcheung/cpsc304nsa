package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Join extends Query {

	public Join(Connection con) {
		super(con);
		joinOwnerWithData("Mark Ruffalo");
	}

	public void join() {
		// TODO Auto-generated method stub
		
	}
	
	/* get a list of data (data_id) associated with a given owner */
	public List<Integer> joinOwnerWithData(String owner) {
		List<Integer> data = new ArrayList<Integer>();
		String query = "SELECT data_id FROM device, data WHERE device.device_id = data.device_id AND device.owner LIKE \"" + owner + "\";";
		
        ResultSet rs = null;
        Statement statement = null; 
        try {           
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	data.add(rs.getInt("data_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

		return data;
	}

}
