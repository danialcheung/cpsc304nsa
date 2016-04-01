package queries;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import main.AttrType;
import main.Pair;
import tables.Table;

public class Deletion extends Query {

	public Deletion(Connection con) {
		super(con);
		deleteDevice(8823);
	}

	public void delete() {
		Table table = selectTable();
		String attr = table.primaryKey();
		
		String val = enterInput("enter primary key of entry to delete");
	}
	
	/* remove all records of a device from database */
	public void deleteDevice(Integer device_id) {
        Statement statement = null; 
        try {           
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM `call` WHERE EXISTS (SELECT * FROM data WHERE data.device_id = " + device_id + " AND data.data_id = `call`.data_id);");
            statement.executeUpdate("DELETE FROM text WHERE EXISTS (SELECT * FROM data WHERE data.device_id = " + device_id + " AND data.data_id = text.data_id);");
            statement.executeUpdate("DELETE FROM commlog WHERE EXISTS (SELECT * FROM data WHERE data.device_id = " + device_id + " AND data.data_id = commlog.data_id);");
            statement.executeUpdate("DELETE FROM photo WHERE EXISTS (SELECT * FROM data WHERE data.device_id = " + device_id + " AND data.data_id = photo.data_id);");
            statement.executeUpdate("DELETE FROM transaction WHERE EXISTS (SELECT * FROM data WHERE data.device_id = " + device_id + " AND data.data_id = transaction.data_id);");
            statement.executeUpdate("DELETE FROM data WHERE device_id = " + device_id + ";");
            statement.executeUpdate("DELETE FROM device WHERE device_id = " + device_id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
