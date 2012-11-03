package com.socmodder.android.mstar;

import android.app.Activity;
import android.os.Bundle;

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
    }
}
