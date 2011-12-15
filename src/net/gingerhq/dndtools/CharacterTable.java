package net.gingerhq.dndtools;

import android.database.sqlite.SQLiteDatabase;

/**
 * @author Dan Albert
 *
 */
public class CharacterTable
{
	public static final String TABLE_NAME = "characters";
	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_RACE = "race";
	public static final String KEY_EXPERIENCE = "experience";
	public static final String KEY_STRENGTH = "strength";
	public static final String KEY_DEXTERITY = "dexterity";
	public static final String KEY_CONSTITUTION = "constitution";
	public static final String KEY_INTELLIGENCE = "intelligence";
	public static final String KEY_WISDOM = "wisdom";
	public static final String KEY_CHARISMA = "charisma";
	
	private static final String CHARACTER_TABLE_CREATE =
			"CREATE TABLE " + TABLE_NAME + " (" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			KEY_NAME + " TEXT NOT NULL, " +
			KEY_RACE + " TEXT NOT NULL, " +
			KEY_EXPERIENCE + " INT NOT NULL, " +
			KEY_STRENGTH + " INT NOT NULL, " +
			KEY_DEXTERITY + " INT NOT NULL, " +
			KEY_CONSTITUTION + " INT NOT NULL, " +
			KEY_INTELLIGENCE + " INT NOT NULL, " +
			KEY_WISDOM + " INT NOT NULL, " +
			KEY_CHARISMA + " INT NOT NULL);";
	
	public static void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CHARACTER_TABLE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE " + TABLE_NAME);
		onCreate(db);
	}

}
