package cpsc304nsa.tables;

import java.util.Arrays;
import java.util.List;

import cpsc304nsa.main.AttrType;
import cpsc304nsa.main.Pair;

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
		return Arrays.asList(new Pair(AttrType.INT, "data_id"),
				new Pair(AttrType.INT, "sender"),
				new Pair(AttrType.INT, "reciever"));
				}
	@Override
	public String getName() {
		return "commlog";
	}

}
