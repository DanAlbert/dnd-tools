package net.gingerhq.dndtools;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SrdDbAdapter
{
	private Context context;
	private SQLiteDatabase db;
	private SrdDbHelper dbHelper;
	
	public SrdDbAdapter(Context context)
	{
		this.context = context;
	}
	
	public void open() throws SQLException
	{
		this.dbHelper = new SrdDbHelper(context);
		db = dbHelper.getReadableDatabase();
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public CharacterClass find(long id)
	{
		String[] columns = new String[]
		{
			SrdClassTable.KEY_ID,
			SrdClassTable.KEY_NAME,
			SrdClassTable.KEY_LEVEL,
			SrdClassTable.KEY_BASE_ATTACK_BONUS,
			SrdClassTable.KEY_FORTITUDE_SAVE,
			SrdClassTable.KEY_REFLEX_SAVE,
			SrdClassTable.KEY_WILL_SAVE,
			SrdClassTable.KEY_SPECIALS
		};
		
		Cursor result = db.query(SrdClassTable.TABLE_NAME, columns, SrdClassTable.KEY_ID + "=" + id, null, null, null, null);
		
		CharacterClass c = null;
		if (result.moveToFirst())
		{
			c = new CharacterClass();
			c.setSrdId(result.getLong(0));
			c.setName(result.getString(1));
			c.setLevel(Integer.parseInt(result.getString(2)));
			c.setBaseAttackBonus(Integer.parseInt(result.getString(3)));
			c.setFortitudeSave(Integer.parseInt(result.getString(4)));
			c.setReflexSave(Integer.parseInt(result.getString(5)));
			c.setWillSave(Integer.parseInt(result.getString(6)));
			
			String[] specials = result.getString(7).split(",");
			for (int i = 0; i < specials.length; i++)
			{
				c.addSpecial(specials[i]);
			}
		}
		
		result.close();
		return c;
	}
	public List<CharacterClass> fetchAll()
	{
		List<CharacterClass> classes = new ArrayList<CharacterClass>();
		String[] columns = new String[]
		{
			SrdClassTable.KEY_ID,
			SrdClassTable.KEY_NAME,
			SrdClassTable.KEY_LEVEL,
			SrdClassTable.KEY_BASE_ATTACK_BONUS,
			SrdClassTable.KEY_FORTITUDE_SAVE,
			SrdClassTable.KEY_REFLEX_SAVE,
			SrdClassTable.KEY_WILL_SAVE,
			SrdClassTable.KEY_SPECIALS
		};
		
		Cursor result = db.query(SrdClassTable.TABLE_NAME, columns, null, null, null, null, null);
		
		if (result.moveToFirst())
		{
			do
			{
				CharacterClass c = new CharacterClass();
				c.setSrdId(result.getLong(0));
				c.setName(result.getString(1));
				c.setLevel(Integer.parseInt(result.getString(2)));
				c.setBaseAttackBonus(Integer.parseInt(result.getString(3)));
				c.setFortitudeSave(Integer.parseInt(result.getString(4)));
				c.setReflexSave(Integer.parseInt(result.getString(5)));
				c.setWillSave(Integer.parseInt(result.getString(6)));
				
				String[] specials = result.getString(7).split(",");
				for (int i = 0; i < specials.length; i++)
				{
					c.addSpecial(specials[i]);
				}
				
				classes.add(c);
			} while (result.moveToNext());
		}
		
		result.close();
		return classes;
	}

	public CharacterClass find(String name, int level)
	{
		String[] columns = new String[]
		{
			SrdClassTable.KEY_ID,
			SrdClassTable.KEY_BASE_ATTACK_BONUS,
			SrdClassTable.KEY_FORTITUDE_SAVE,
			SrdClassTable.KEY_REFLEX_SAVE,
			SrdClassTable.KEY_WILL_SAVE,
			SrdClassTable.KEY_SPECIALS
		};
		
		String where = SrdClassTable.KEY_NAME + "='" + name + "' AND " + SrdClassTable.KEY_LEVEL + "=" + level;
		Cursor result = db.query(SrdClassTable.TABLE_NAME, columns, where, null, null, null, null);
		
		CharacterClass c = null;
		if (result.moveToFirst())
		{
			c = new CharacterClass();
			c.setSrdId(result.getLong(0));
			c.setName(name);
			c.setLevel(level);
			c.setBaseAttackBonus(Integer.parseInt(result.getString(1).replace("+",	"")));
			c.setFortitudeSave(Integer.parseInt(result.getString(2).replace("+",	"")));
			c.setReflexSave(Integer.parseInt(result.getString(3).replace("+",	"")));
			c.setWillSave(Integer.parseInt(result.getString(4).replace("+",	"")));
			
			String[] specials = result.getString(5).split(",");
			for (int i = 0; i < specials.length; i++)
			{
				c.addSpecial(specials[i]);
			}
		}
		
		result.close();
		return c;
	}
}
