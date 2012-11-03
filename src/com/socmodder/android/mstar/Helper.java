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

            b = new Building("Havener Center", -91.77592959113267, 37.95476631091687, 0);
            bDao.create(b);

            b = new Building("IDE", 0, 0, 0);
            bDao.create(b);

            b = new Building("Centenial Hall", 0, 0, 0);
            bDao.create(b);

            b = new Building("Toomey Hall", 0, 0, 0);
            bDao.create(b);

            b = new Building("Butler-Carlton Hall", -91.77369385505148, 37.95520863546415, 0);
            bDao.create(b);

            b = new Building("Electrical Engineering", -91.77369385505148, 37.95520863546415, 0);
            bDao.create(b);

            b = new Building("Physics", -91.77318392752757, 37.95486703799107, 0);
            bDao.create(b);

            b = new Building("McNutt Hall", -91.77544507921532, 37.95566916837905, 0);
            bDao.create(b);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
