package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Update extends Query {

	public Update(Connection con) {
		super(con);
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	/* mark data as suspicious */
	public void changeDateOfData(Integer data_id, Integer date) {
//		date is formatted as YYYYMMDD
		String update = "UPDATE data SET date = " + date + " WHERE data_id = " + data_id + ";";
		
        Statement statement = null; 
        try {           
            statement = con.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
