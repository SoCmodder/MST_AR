package com.socmodder.android.mstar;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Helper extends OrmLiteSqliteOpenHelper
{
	private static final String DATABASE_NAME = "AR.sqlite";
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
        //Name, lon, lat, alt
        createBuildings();
    }

    @Override
	public void onUpgrade(SQLiteDatabase p1, ConnectionSource p2, int p3, int p4)
	{
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

    /**
     * Returns the Database Access Object (DAO) for our Building class. It will create it, or just give the cached
     * value.
     */
    public Dao<Building, Integer> getDao() throws SQLException{
        if(buildingDao == null){
            buildingDao = getDao(Building.class);
        }
        return buildingDao;
    }

    public void createBuildings(){
        Dao<Building, Integer> bDao = null;
        try {
            bDao = getDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            String desc = "The computer science building. The most awesome building ever!";
            Building b = new Building("Computer Science", desc, -91.77383683047405f, 37.95561634965694f, 0);
            bDao.create(b);

            desc = "This is the library BOOOOOOO";
            b = new Building("Library", desc, -91.77332205172004f, 37.95543497056854f, 0);
            bDao.create(b);

            b = new Building("HSS", desc, -91.77409015632608f, 37.9553411304625f, 0);
            bDao.create(b);

            b = new Building("Engineering Managment", desc, -91.77479519078301f, 37.95511551589411f, 0);
            bDao.create(b);

            b = new Building("Shrenk Hall", desc, 0, 0, 0);
            bDao.create(b);

            b = new Building("Havener Center", desc, -91.77592959113267f, 37.95476631091687f, 0);
            bDao.create(b);

            b = new Building("IDE", desc, 0, 0, 0);
            bDao.create(b);

            b = new Building("Centenial Hall", desc, 0, 0, 0);
            bDao.create(b);

            b = new Building("Toomey Hall", desc, 0, 0, 0);
            bDao.create(b);

            b = new Building("Butler-Carlton Hall", desc, -91.77369385505148f, 37.95520863546415f, 0);
            bDao.create(b);

            b = new Building("Electrical Engineering", desc, -91.77369385505148f, 37.95520863546415f, 0);
            bDao.create(b);

            b = new Building("Physics", desc, -91.77318392752757f, 37.95486703799107f, 0);
            bDao.create(b);

            b = new Building("McNutt Hall", desc, -91.77544507921532f, 37.95566916837905f, 0);
            bDao.create(b);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
