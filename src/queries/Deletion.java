package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;

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
	
	public JTable selectAllDevices() {
		String[] columnNames = {"device_id", "owner", "model", "lat", "lng", "device_type"};
		ArrayList<List<Object>> data = new ArrayList<List<Object>>();

		String query = "SELECT device_id, owner, model, lat, lng, device_type FROM device;";
		
        ResultSet rs = null;
        Statement statement = null; 
        try {           
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	data.add(Arrays.asList(
            			rs.getInt("device_id"),
            			rs.getString("owner"),
            			rs.getString("model"),
            			rs.getFloat("lat"),
            			rs.getFloat("lng"),
            			rs.getString("device_type")));
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
		deleteDevice(Integer.parseInt(arg));
		return selectAllDevices();
	}

	

}
