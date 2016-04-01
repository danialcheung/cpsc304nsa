package queries;

import java.sql.Connection;

import main.AttrType;
import main.Pair;
import tables.Table;

public class Deletion extends Query {

	public Deletion(Connection con) {
		super(con);
	}

	public void delete() {
		Table table = selectTable();
		String attr = table.primaryKey();
		
		String val = enterInput("enter primary key of entry to delete");
	}

}
