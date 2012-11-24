package com.socmodder.android.mstar;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 11/19/12
 * Time: 8:37 PM
 */
@DatabaseTable(tableName = "VendingMachine")
public class VendingMachine {
    @DatabaseField(id=true)
    private String name;
    //@DatabaseField
    //private ArrayList<String> items;
    @DatabaseField
    private String bName;

    public VendingMachine(){

    }

    public VendingMachine(String name, String building){
        this.name = name;
        //this.items = items;
        this.bName = building;
    }

    public String getName(){
        return this.name;
    }

    /*public ArrayList<String> getItems(){
        return this.items;
    }*/

    public String getbName(){
        return this.bName;
    }

    public void setName(String n){
        this.name = n;
    }

    /*public void setItems(ArrayList<String> i){
        this.items = i;
    }*/

    public void setbName(String b){
        this.bName = b;
    }
}
