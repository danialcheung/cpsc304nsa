package tables;

import java.util.Arrays;
import java.util.List;

import main.AttrType;
import main.Pair;

public class Transaction extends Data implements Table {
	
	private double amount;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public List<Pair<AttrType, String>> getAttrs() {
		return Arrays.asList(new Pair<AttrType, String>(AttrType.INT, "data_id"),
				new Pair<AttrType, String>(AttrType.FLOAT, "amount"));
				}
	@Override
	public String getName() {
		return "transaction";
	}

}
