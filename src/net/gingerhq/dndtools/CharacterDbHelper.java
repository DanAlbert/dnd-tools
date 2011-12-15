package net.gingerhq.dndtools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CharacterDbHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "data.db";
	private static final int DATABASE_VERSION = 5;
	
	public CharacterDbHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		CharacterTable.onCreate(db);
		CharacterClassTable.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		CharacterTable.onUpgrade(db, oldVersion, newVersion);
		CharacterClassTable.onUpgrade(db, oldVersion, newVersion);
	}
}
