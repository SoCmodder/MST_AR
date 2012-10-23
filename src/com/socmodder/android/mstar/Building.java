package com.socmodder.android.mstar;
import com.j256.ormlite.field.*;
import com.j256.ormlite.table.*;

@DatabaseTable(tableName = "buildings")
public class Building
{
	@DatabaseField(id = true)
	private String name;
	@DatabaseField
	private double lon;
	@DatabaseField
	private double lat;
	@DatabaseField 
	private double alt;
	@DatabaseField
	private int restroomCount;
	@DatabaseField
	private String vendingMachines;
	
	public Building(){
		
	}
	
	public Building(String n, double lon, double lat, double alt){
		this.name = n;
		this.lon = lon;
		this. lat = lat;
		this.alt = alt;
	}
	
	public String getName(){
		return name;
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

    public String getVendingMachines(){
        return vendingMachines;
    }
}
