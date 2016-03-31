package tables;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import main.AttrType;
import main.Pair;

public class Data implements Table{

	private int dataId;
	private Timestamp date;
	private Boolean suspicious;
	private double lat;
	private double lng;
	private int devId;
	
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Boolean getSuspicious() {
		return suspicious;
	}
	public void setSuspicious(Boolean suspicious) {
		this.suspicious = suspicious;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public int getDevId() {
		return devId;
	}
	public void setDevId(int devId) {
		this.devId = devId;
	}
	@Override
	public List<Pair<AttrType, String>> getAttrs() {
		return Arrays.asList(new Pair<AttrType, String>(AttrType.INT, "data_id"),
				new Pair<AttrType, String>(AttrType.DATETIME, "date"),
				new Pair<AttrType, String>(AttrType.BOOL, "suspicious"),
				new Pair<AttrType, String>(AttrType.FLOAT, "lat"),
				new Pair<AttrType, String>(AttrType.FLOAT, "lng"),
				new Pair<AttrType, String>(AttrType.INT, "device_id"));
	}
	@Override
	public String getName() {
		return "data";
	}
}
