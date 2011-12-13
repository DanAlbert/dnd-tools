package net.gingerhq.dndtools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CharacterClassDbAdapter
{
	private Context context;
	private SQLiteDatabase db;
	private CharacterDbHelper dbHelper;
	private SrdDbAdapter srd;
	
	public CharacterClassDbAdapter(Context context)
	{
		this.context = context;
	}
	
	public void open() throws SQLException
	{
		this.dbHelper = new CharacterDbHelper(context);
		db = dbHelper.getWritableDatabase();
		
		this.srd = new SrdDbAdapter(context);
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public boolean reconcile(Character character)
	{
		// Remove all classes currently associated with the character since some may no longer be valid
		Iterator<CharacterClass> iter = fetchAll(character).iterator();
		while (iter.hasNext())
		{
			delete(iter.next());
		}
		
		// Add the correct classes
		iter = character.getClasses().iterator();
		while (iter.hasNext())
		{
			CharacterClass current = iter.next();
			
			ContentValues values = new ContentValues();
			values.put(CharacterClassTable.KEY_CHARACTER_ID, character.getId());
			values.put(CharacterClassTable.KEY_CLASS_ID, current.getSrdId());
			
			long id = db.insert(CharacterClassTable.TABLE_NAME, null, values);
			if (id == -1)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean delete(CharacterClass c)
	{
		return db.delete(CharacterClassTable.TABLE_NAME, CharacterClassTable.KEY_ID + "=" + c.getId(), null) > 0;
	}
	
	public List<CharacterClass> fetchAll(Character character)
	{
		List<CharacterClass> classes = new ArrayList<CharacterClass>();
		String[] columns = new String[]
		{
			CharacterClassTable.KEY_ID,
			CharacterClassTable.KEY_CHARACTER_ID,
			CharacterClassTable.KEY_CLASS_ID
		};
		
		Cursor result = db.query(CharacterClassTable.TABLE_NAME, columns, null, null, null, null, null);
		
		if (result.moveToFirst())
		{
			do
			{
				if (result.getLong(1) == character.getId())
				{
					CharacterClass c = this.srd.find(result.getLong(2));
					
					if (c != null)
					{
						classes.add(c);
					}
					else
					{
						throw new Error("Could not load character class from SRD");
					}
					
					c.setId(result.getLong(0));
				}
			} while (result.moveToNext());
		}
		
		result.close();
		return classes;
	}
}
