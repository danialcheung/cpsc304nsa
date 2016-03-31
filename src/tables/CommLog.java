package tables;

import java.util.Arrays;
import java.util.List;

import main.AttrType;
import main.Pair;

public class CommLog extends Data implements Table {

	private int sender;
	private int receiver;
	
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	
	@Override
	public List<Pair<AttrType, String>> getAttrs() {
		return Arrays.asList(new Pair<AttrType, String>(AttrType.INT, "data_id"),
				new Pair<AttrType, String>(AttrType.INT, "sender"),
				new Pair<AttrType, String>(AttrType.INT, "reciever"));
				}
	@Override
	public String getName() {
		return "commlog";
	}

}
