package com.socmodder.android.mstar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 11/1/12
 * Time: 4:31 PM
 */
public class PoiDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_detail);

        String name = this.getIntent().getExtras().getString("POI_NAME");
        String desc = this.getIntent().getExtras().getString("POI_DESC");

        ((TextView) this.findViewById(R.id.tvBuildingName)).setText(name);
        ((TextView) this.findViewById(R.id.tvBuildingDesc)).setText(desc);
    }
}
