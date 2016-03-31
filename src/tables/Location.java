package tables;

import java.util.Arrays;
import java.util.List;

import main.AttrType;
import main.Pair;

public class Location implements Table {

	private double lat;
	private double lng;
	private String country;
	
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public List<Pair<AttrType, String>> getAttrs() {
		return Arrays.asList(new Pair<AttrType, String>(AttrType.FLOAT,"lat"), 
				new Pair<AttrType, String>(AttrType.FLOAT,"lng"), 
				new Pair<AttrType, String>(AttrType.STRING,"country"));
	}
	@Override
	public String getName() {
		return "location";
	}	
}
