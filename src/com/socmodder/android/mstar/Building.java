package com.socmodder.android.mstar;
import com.j256.ormlite.field.*;
import com.j256.ormlite.table.*;

@DatabaseTable(tableName = "buildings")
public class Building
{
	@DatabaseField(id = true)
	private String name;
    @DatabaseField
    private String desc;
	@DatabaseField
	private double lon;
	@DatabaseField
	private double lat;
	@DatabaseField 
	private double alt;
	@DatabaseField
	private int restroomCount;
	
	public Building(){
		
	}
	
	public Building(String n, String d, float lon, float lat, float alt){
		this.name = n;
        this.desc = d;
		this.lon = lon;
		this. lat = lat;
		this.alt = alt;
	}
	
	public String getName(){
		return name;
	}

    public String getDesc(){
        return desc;
    }

	public double getLon(){
		return lon;
    }

    public double getLat(){
        return lat;
    }

    public double getAlt(){
        return alt;
    }

    public int getRestroomCount(){
        return restroomCount;
    }
}
