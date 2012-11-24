package com.socmodder.android.mstar;

import android.os.Bundle;
import android.widget.TextView;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 11/11/12
 * Time: 5:06 PM
 */
public class BuildingDetailActivity extends OrmLiteBaseActivity<Helper> {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_detail);

        String bName = this.getIntent().getExtras().getString("BUILDING_NAME");
        String bDesc = this.getIntent().getExtras().getString("BUILDING_DESC");

        //Get vending machine info for building (if any)
        List<VendingMachine> vmArray = new ArrayList<VendingMachine>();
        try{
            vmArray = getHelper().getVendingMachineDao().queryForAll();
        }catch(SQLException e){
            e.printStackTrace();
        }
        //Remove the vending machines that don't belong to this building
        for(int i=0; i<vmArray.size(); i++){
            if(!vmArray.get(i).getbName().equals(bName)){
                vmArray.remove(i);
            }
        }


        TextView name = (TextView)findViewById(R.id.tvBuildingName);
        TextView desc = (TextView)findViewById(R.id.tvBuildingDesc);
        TextView vending = (TextView)findViewById(R.id.tvHasVending);

        name.setText(bName);
        desc.setText(bDesc);
        if(vmArray.size()>0){
            vending.setText("Yes");
        }
        else{
            vending.setText("No");
        }
    }
}
