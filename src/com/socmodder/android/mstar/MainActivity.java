package com.socmodder.android.mstar;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.wikitude.architect.ArchitectUrlListener;
import com.wikitude.architect.ArchitectView;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
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
    private String provider;

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

        Criteria criteria = new Criteria();

        //inform the architect framework about the user's location
        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        provider = locManager.getBestProvider(criteria, false);
        loc = locManager.getLastKnownLocation(provider);
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
    protected void onResume(){
        super.onResume();

        this.architectView.onResume();
        locManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    protected void onPause(){
        super.onPause();

        if(this.architectView != null){
            this.architectView.onPause();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if(this.architectView != null){
            this.architectView.onDestroy();
        }
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();

        if(this.architectView != null){
            this.architectView.onLowMemory();
        }
    }

    /**
     * called when a url with hose "architectsdk://" is discovered.
     * @param url
     * @return
     */
    @Override
    public boolean urlWasInvoked(String url) {
        //parsing the retrieved url string
        List<NameValuePair> queryParams = URLEncodedUtils.parse(URI.create(url), "UTF-8");
        String id = "";
        //getting the values of the contained GET-parameters
        for(NameValuePair pair : queryParams){
            if(pair.getName().equals("id")){
                id = pair.getValue();
            }
        }

        //get the corresponding poi bean for the given id
        PoiBean bean = poiBeanList.get(Integer.parseInt(id));
        //start a new intent for displaying the content of the bean
        //TODO: make this custom
        //Intent intent = new Intent(this, PoiDetailActivity.class);
        //intent.putExtra("POI_NAME", bean.getName());
        //intent.putExtra("POI_DESC", bean.getDescription());
        //this.startActivity(intent);
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {
        if(this.architectView != null){
            this.architectView.setLocation(loc.getLatitude(), loc.getLongitude(), loc.getAccuracy());
        }
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
        List<Building> buildingList = null;
        try {
            buildingList = getHelper().getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i=0; i<buildingList.size(); i++){
            PoiBean bean = new PoiBean(
                    ""+i,
                    "POI #" + i,
                    "Best POI Bean ever!" + i,
                    1, buildingList.get(i).getLat(),
                    buildingList.get(i).getLon(),
                    buildingList.get(i).getAlt()
            );
            try {
                array.put(bean.toJSONObject());
                poiBeanList.add(bean);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.architectView.callJavascript("newData(" + array.toString() + ");");
    }
}