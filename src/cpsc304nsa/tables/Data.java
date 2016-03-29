package cpsc304nsa.tables;

import java.sql.Timestamp;

public class Data {

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
}
