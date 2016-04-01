package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Update extends Query {

	public Update(Connection con) {
		super(con);
		markDataSuspicious(7712323);
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	/* mark data as suspicious */
	public void markDataSuspicious(Integer data_id) {
		String update = "UPDATE data SET suspicious = 1 WHERE data_id = " + data_id + ";";
		
        Statement statement = null; 
        try {           
            statement = con.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
