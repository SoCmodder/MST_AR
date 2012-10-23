package com.socmodder.android.mstar;
import com.j256.ormlite.android.apptools.*;
import com.j256.ormlite.support.*;
import android.database.sqlite.*;
import android.content.*;

public class Helper extends OrmLiteSqliteOpenHelper
{
	private static final String DATABASE_NAME = "AR.db";
	private static final int DATABASE_VERSION = 1;
	
	//This needs to be edited, the super constructor
	//or whatever does not have all of the correct parameters
	//look at the DatabaseHelper.java file that I downloaded in 
	// ~/sdcard/Downloads/DatabaseHelper.java
	public Helper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase p1, ConnectionSource p2)
	{
		// TODO: Implement this method
	}

	public void onUpgrade(SQLiteDatabase p1, ConnectionSource p2, int p3, int p4)
	{
		// TODO: Implement this method
	}
	
}
