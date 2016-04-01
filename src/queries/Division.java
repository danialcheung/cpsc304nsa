package queries;

import java.sql.Connection;

import main.AttrType;
import main.Pair;
import tables.Table;

public class Division extends Query {

	public Division(Connection con) {
		super(con);
	}

	public void divide() {
		Table dividend = selectTable();//TODO add msg param "select dividend/divisor table"
		Table divisor = selectTable();
		Pair<AttrType, String> attr = selectAttr(divisor, "select attribute to divide by:");

		// TODO Auto-generated method stub
		
	}

}
