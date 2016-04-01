package tables;

import java.util.Arrays;
import java.util.List;

import main.AttrType;
import main.Pair;

public class Device implements Table {
     
    private int devId;
    private String owner;
    private String model;
    private double lat;
    private double lng;
    private String devType;
     
    public int getDevId() {
        return devId;
    }
    public void setDevId(int devId) {
        this.devId = devId;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String empName) {
        this.owner = empName;
    }
    public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public String getDevType() {
		return devType;
	}
	public void setDevType(String devType) {
		this.devType = devType;
	}
     
    public String toString() {
        return "Device [devId=" + devId + ", owner=" + owner + ", model="
                + model + ", devType=" + devType + "]";
    }
	@Override
	public List<Pair<AttrType, String>> getAttrs() {
		return Arrays.asList(new Pair<AttrType, String>(AttrType.INT,"device_id"), 
				new Pair<AttrType, String>(AttrType.STRING,"owner"), 
				new Pair<AttrType, String>(AttrType.STRING,"model"), 
				new Pair<AttrType, String>(AttrType.FLOAT,"lat"), 
				new Pair<AttrType, String>(AttrType.FLOAT,"lng"), 
				new Pair<AttrType, String>(AttrType.STRING,"device_type"));
	}
	@Override
	public String getName() {
		return "device";
	}
	@Override
	public String primaryKey() {
		return "device_id";
	}       
}
