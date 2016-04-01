package queries;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.AttrType;
import main.Pair;
import tables.Table;

public class Division extends Query {

	public Division(Connection con) {
		super(con);
	}

	public void divide() {
		Table dividend = selectTable("select dividend table:");
		Table divisor = selectTable("select divisor table:");
		
		List<Pair<AttrType, String>> divisibleAttrs = new ArrayList<Pair<AttrType, String>>();
		while (divisibleAttrs.isEmpty()) {
			for (Pair<AttrType, String> attr: dividend.getAttrs()) {
				if (divisor.getAttrs().contains(attr)) {
					divisibleAttrs.add(attr);
				}
			}
			
			if (divisibleAttrs.isEmpty()) {
				System.out.println("no common attributes between " + dividend.getName() + " and " + divisor.getName());
				divisor = selectTable("select divisor table:");
			}
		}
		
		Pair<AttrType, String> attr = selectAttr(divisibleAttrs, "select attribute to divide by:");

		String query = buildQuery(dividend.getName(), divisor.getName(), attr.getRight(), dividend.primaryKey());	
		runQuery(query,Arrays.asList());

}

	private String buildQuery(String dividend, String divisor, String attr, String primaryKey) {
		// TODO fix this
		String q = "";
		q += "SELECT DISTINCT d1." + primaryKey + " as " + primaryKey + "\n";
		q += "FROM " +  dividend + " d1\n";
		q += "WHERE NOT EXISTS\n";
		q += 	"(SELECT *\n";
		q +=	"FROM " + divisor + "\n";
		q += 	"WHERE NOT EXISTS\n";
		q += "(SELECT *\n";
		q += "FROM " + dividend + " d2 ";
		q += "WHERE d2." + primaryKey + " = d1." + primaryKey + "\n";
		q += "AND d2." + attr + " = " + divisor + "." + attr + "));";

//		SELECT DISTINCT d1.y as y
//		FROM dividend d1
//		WHERE NOT EXISTS
//		(SELECT * 
//		from divisor 
//		WHERE NOT EXISTS 
//		(SELECT * 
//		FROM dividend d2 
//		WHERE d2.y = d1.y
//		AND d2.attr = divisor.attr));		
		return q;
	}
}