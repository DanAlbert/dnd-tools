package net.gingerhq.dndtools;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

/**
 * @author Dan Albert
 *
 */
public class CharacterClassTable
{
	public static final String TABLE_NAME = "character_classes";
	public static final String KEY_ID = "_id";
	public static final String KEY_CHARACTER_ID = "character_id";
	public static final String KEY_CLASS_ID = "class_id";
	
	private static final String CHARACTER_CLASSES_TABLE_CREATE =
			"CREATE TABLE " + TABLE_NAME + " (" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			KEY_CHARACTER_ID + " INT NOT NULL, " +
			KEY_CLASS_ID + " INT NOT NULL);";
	
	public static void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CHARACTER_CLASSES_TABLE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE " + TABLE_NAME);
		onCreate(db);
	}

}
