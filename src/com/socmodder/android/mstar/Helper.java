package com.socmodder.android.mstar;
import com.j256.ormlite.android.apptools.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.*;
import android.database.sqlite.*;
import android.content.*;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Helper extends OrmLiteSqliteOpenHelper
{
	private static final String DATABASE_NAME = "AR.db";
	private static final int DATABASE_VERSION = 1;
	
	private Dao<Building, Integer> buildingDao = null;
    private RuntimeExceptionDao<Building, Integer> runtimeDao = null;

	public Helper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

    @Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs){
		// TODO: Implement this method
        try {
            TableUtils.createTable(cs, Building.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //test to see if data is put into table
        RuntimeExceptionDao<Building, Integer> dao = getRuntimeExceptionDao(Building.class);
        //Name, lon, lat, alt
        Building b = new Building("Test", 1234, 1234, 0);
        dao.create(b);

    }

    @Override
	public void onUpgrade(SQLiteDatabase p1, ConnectionSource p2, int p3, int p4)
	{
		// TODO: Implement this method
        try{
            TableUtils.dropTable(connectionSource, Building.class, true);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
	}

    @Override
    public void close(){
        super.close();
        runtimeDao = null;
    }

    public void createBuildings(){
        RuntimeExceptionDao<Building, Integer> bDao = getRuntimeExceptionDao(Building.class);
        Building b = new Building("Computer Science", -91.77383683047405, 37.95561634965694, 0);
        bDao.create(b);

        b = new Building("Library", -91.77332205172004, 37.95543497056854, 0);
        bDao.create(b);

        b = new Building("HSS", -91.77409015632608, 37.9553411304625, 0);
        bDao.create(b);

        b = new Building("Engineering Managment", -91.77479519078301, 37.95511551589411, 0);
        bDao.create(b);

        b = new Building("Shrenk Hall", 0, 0, 0);
        bDao.create(b);

        b = new Building("Havener", 0, 0, 0);
        bDao.create(b);

        b = new Building("IDE", 0, 0, 0);
        bDao.create(b);

        b = new Building("Centenial Hall", 0, 0, 0);
        bDao.create(b);

        b = new Building("Toomey Hall", 0, 0, 0);
        bDao.create(b);

        b = new Building("Butler-Carlton Hall", 0, 0, 0);
        bDao.create(b);

        b = new Building("Electrical Engineering", 0, 0, 0);
        bDao.create(b);
    }
}
