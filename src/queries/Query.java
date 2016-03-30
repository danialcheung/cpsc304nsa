package queries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

import cpsc304nsa.tables.*;

public class Query {
	private BufferedReader br;
	protected Connection con;
	public Query(Connection con) {
		this.con = con;
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	protected Table selectTable() {
		Table table = null;
		System.out.println("select a table:\n"
				+ "\t1. Call\n"
				+ "\t2. CommLog\n"
				+ "\t3. Data\n"
				+ "\t4. Device\n"
				+ "\t5. Location\n"
				+ "\t6. Photo\n"
				+ "\t7. Text\n"
				+ "\t8. Transaction\n"
				+ "\t9. User");
		try {
			int input = Integer.parseInt(br.readLine());
			switch (input) {
			case 1: table = new Call(); break;
			case 2: table = new CommLog(); break;
			case 3: table = new Data(); break;
			case 4: table = new Device(); break;
			case 5: table = new Location(); break;
			case 6: table = new Photo(); break;
			case 7: table = new Text(); break;
			case 8: table = new Transaction(); break;
			case 9: table = new User(); break;
			}
			
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
		return table;
	}

	protected String selectAttr(Table t) {
		String result = "";

		System.out.println("select an attribute:");
		int i;
		for (i = 0; i < t.getAttrs().size(); i++) {
			System.out.println("\t" + (i + 1) + ". " + t.getAttrs().get(i));
		}
		System.out.println("\t" + (i + 1) + ". done");
		
		try {
			int input = Integer.parseInt(br.readLine());
			if (input == (i + 1)) {
				return result;
			} else if (input > t.getAttrs().size() ||
				input <= 0) {
				System.out.println("invalid input");
				result = selectAttr(t);
			} else {
			result = t.getAttrs().get(input - 1);
			}
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
		return result;
	}
	
	protected String enterInput() {
		String result = "";
		System.out.println("enter value:");
		
		try {
			result = br.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
		return result;
	}

}
