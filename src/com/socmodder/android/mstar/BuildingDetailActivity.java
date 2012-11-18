package com.socmodder.android.mstar;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: Development
 * Date: 11/11/12
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuildingDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_detail);

        String bName = this.getIntent().getExtras().getString("BUILDING_NAME");
        String bDesc = this.getIntent().getExtras().getString("BUILDING_DESC");
    }
}
