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
}
