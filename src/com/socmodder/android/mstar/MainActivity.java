package com.socmodder.android.mstar;

import android.app.*;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.db.SqliteAndroidDatabaseType;
import com.j256.ormlite.android.*;
import com.j256.ormlite.db.*;
import com.j256.ormlite.support.*;
import com.wikitude.architect.ArchitectUrlListener;
import com.wikitude.architect.ArchitectView;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends OrmLiteBaseActivity<Helper> implements ArchitectUrlListener, LocationListener
{
	private String key = "BRKlXP3V5iv6RI/wrBX5xEKK8kDFdXVGi29IQOdNyleals3BnO7iLIbhtaFWOnVp1L2v1CM9LiiCUAln" +
            "yRuVQPNpgLijVQB 0tBUhxN+4Zcle8iGv0MRy/+ltsLTd+LecLZ70bd/Eoo2DG0aaqJMXwJuZzHRhOij1HdTB9NHWWQRTYWx0ZWRfX6uL+" +
            " WjpAeMNJsgruSkyYiJ927jL2jy1V4WaltO4j2tzqk00dBslgtIF87dnBCjDI2uMK/RIZS0S9e4wAd8hTF0SacWJOtrj/ESwV8P" +
            " obCvOGr6yBqVz";

    private ArchitectView architectView;
    private LocationManager locManager;
    private Location loc;
    private List<PoiBean> poiBeanList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Method for checking device requirements
        if(!ArchitectView.isDeviceSupported(this)){
            Toast.makeText(this, "requirements not fulfilled", Toast.LENGTH_LONG).show();
            this.finish();
            return;
        }

        setContentView(R.layout.main);

        this.architectView = (ArchitectView) this.findViewById(R.id.architectView);

        architectView.onCreate(key);

        //inform the architect framework about the user's location
        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

        //IMPORTANT: creates ARchitect core modules
        if(this.architectView != null){
            this.architectView.onPostCreate();
        }

        //register this activity as handler of "architectsdk://" urls
        this.architectView.registerUrlListener(this);

        try{
            loadSampleWorld();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean urlWasInvoked(String s) {
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void loadSampleWorld() throws IOException{
        this.architectView.load("world.html");

        JSONArray array = new JSONArray();
        poiBeanList = new ArrayList<PoiBean>();

    }

    private void getDatabaseStuff(){
        RuntimeExceptionDao<Building, Integer> buildingDao = getHelper().getRuntimeExceptionDao(Building.class);
        List<Building> bList = buildingDao.queryForAll();

        //TODO: finish getDatabaseStuff()
    }
}