package com.socmodder.android.mstar;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Helper extends OrmLiteSqliteOpenHelper
{
	private static final String DATABASE_NAME = "AR.sqlite";
	private static final int DATABASE_VERSION = 4;
	
	private Dao<Building, Integer> buildingDao = null;
    private Dao<VendingMachine, Integer> vendingMachineDao = null;
    //private RuntimeExceptionDao<Building, Integer> runtimeDao = null;

	public Helper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

    @Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs){
		// TODO: Implement this method
        try {
            TableUtils.createTable(cs, Building.class);
            TableUtils.createTable(cs, VendingMachine.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Name, lon, lat, alt
        createBuildings();
        createVendingMachines();
    }

    @Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource1, int p3, int p4)
	{
        try{
            TableUtils.dropTable(connectionSource1, Building.class, true);
            TableUtils.dropTable(connectionSource1, VendingMachine.class, true);
            onCreate(db, connectionSource1);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
	}

    @Override
    public void close(){
        super.close();
    }

    /**
     * Returns the Database Access Object (DAO) for our Building class. It will create it, or just give the cached
     * value.
     */
    public Dao<Building, Integer> getBuildingDao() throws SQLException{
        if(buildingDao == null){
            buildingDao = getDao(Building.class);
        }
        return buildingDao;
    }

    public Dao<VendingMachine, Integer> getVendingMachineDao() throws SQLException{
        if(vendingMachineDao == null){
            vendingMachineDao = getDao(VendingMachine.class);
        }
        return vendingMachineDao;
    }

    /**
     * Function: createBuildings()
     * Description: creates the needed buildings and adds them to the database
     */
    public void createBuildings(){
        Dao<Building, Integer> bDao = null;
        try {
            bDao = getBuildingDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            String desc = "The computer science building. The most awesome building ever! " +
                    "Only the coolest and most awesome people take classes in this building. " +
                    "Computer Science is the bomb diggity!";
            Building b = new Building("Computer Science", desc, -91.774447f, 37.955555f, 1250);
            bDao.create(b);

            desc = "Books, books, and more books! Sometimes people sleep here when they're studying, too!";
            b = new Building("Library", desc, -91.77332205172004f, 37.95543497056854f, 1250);
            bDao.create(b);

            desc = "Humanities and Social Sciences";
            b = new Building("HSS", desc, -91.77409015632608f, 37.9553411304625f, 1250);
            bDao.create(b);

            desc = "Engineering Management Building...";
            b = new Building("Engineering Managment", desc, -91.775128f, 37.955475f, 1250);
            bDao.create(b);

            desc = "Chemistry!";
            b = new Building("Shrenk Hall", desc, -91.774091f, 37.952875f, 1250);
            bDao.create(b);

            desc = "The bookstore is located here. You can also come here for food!";
            b = new Building("Havener Center", desc, -91.77592959113267f, 37.95476631091687f, 1250);
            bDao.create(b);

            desc = "Inter-Disciplenary Engineering";
            b = new Building("IDE", desc, -91.771995f, 37.953963f, 1250);
            bDao.create(b);

            desc = "There are alot of labs and IST classes here...";
            b = new Building("Centenial Hall", desc, -91.773116f, 37.953083f, 1250);
            bDao.create(b);

            desc = "Aerospace and Mechanical Engineering";
            b = new Building("Toomey Hall", desc, -91.774025f, 37.954098f, 1250);
            bDao.create(b);

            desc = "Civil Engineering";
            b = new Building("Butler-Carlton Hall", desc, -91.77369385505148f, 37.95520863546415f, 1250);
            bDao.create(b);

            desc = "This is the double E building!";
            b = new Building("Electrical Engineering", desc, -91.772375f, 37.956401f, 1250);
            bDao.create(b);

            desc = "This is where you get to take the most fun physics classes ever!";
            b = new Building("Physics", desc, -91.77318392752757f, 37.95486703799107f, 1250);
            bDao.create(b);

            desc = "Mining Engineering is here!";
            b = new Building("McNutt Hall", desc, -91.775837f, 37.955635f, 1250);
            bDao.create(b);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Function: createVendingMachines()
     * Description: Used to create a table of vending machines in the database
     * that can later be accessed and tied to individual buildings.
     */
    public void createVendingMachines(){
        Dao<VendingMachine, Integer> d = null;
        try{
            d = getVendingMachineDao();
        }catch(SQLException e){
            e.printStackTrace();
        }

        //ArrayList<String> items = new ArrayList<String>();

        try {
            //items.add("Chips");
            //items.add("Candy");
            //items.add("Gum");
            //items.add("Crackers");
            VendingMachine vm = new VendingMachine("McNutt Snack Machine", "McNutt Hall");
            d.create(vm);

            /*items.clear();
            items.add("Mountain Dew");
            items.add("Pepsi");
            items.add("Diet Pepsi");
            items.add("Mountain Dew - CR");
            items.add("Mountain Dew - LW");
            vm = new VendingMachine("McNutt Soda Machine", items, "McNutt Hall");
            d.create(vm);*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
