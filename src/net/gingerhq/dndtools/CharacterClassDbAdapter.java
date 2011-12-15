package net.gingerhq.dndtools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

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
	private ClassLevelAdapter classLevelAdapter;
	
	public CharacterClassDbAdapter(Context context)
	{
		this.context = context;
	}
	
	public void open() throws SQLException
	{
		this.dbHelper = new CharacterDbHelper(context);
		db = dbHelper.getWritableDatabase();
		
		try
		{
			this.classLevelAdapter = new ClassLevelXmlAdapter(context);
		}
		catch (XmlPullParserException e)
		{
			throw new Error("An error occured while parsing the XML file");
		}
		catch (IOException e)
		{
			throw new Error("An error occured while reading the XML file");
		}
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public boolean reconcile(Character character)
	{
		// Remove all classes currently associated with the character since some may no longer be valid
		Iterator<ClassLevel> iter = fetchAll(character).iterator();
		while (iter.hasNext())
		{
			delete(iter.next());
		}
		
		// Add the correct classes
		iter = character.getClasses().iterator();
		while (iter.hasNext())
		{
			ClassLevel current = iter.next();
			
			ContentValues values = new ContentValues();
			values.put(CharacterClassTable.KEY_CHARACTER_ID, character.getId());
			values.put(CharacterClassTable.KEY_CLASS_NAME, current.getName());
			values.put(CharacterClassTable.KEY_CLASS_LEVEL, current.getLevel());
			
			long id = db.insert(CharacterClassTable.TABLE_NAME, null, values);
			if (id == -1)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean delete(ClassLevel c)
	{
		return db.delete(CharacterClassTable.TABLE_NAME, CharacterClassTable.KEY_ID + "=" + c.getId(), null) > 0;
	}
	
	public List<ClassLevel> fetchAll(Character character)
	{
		List<ClassLevel> classes = new ArrayList<ClassLevel>();
		String[] columns = new String[]
		{
			CharacterClassTable.KEY_ID,
			CharacterClassTable.KEY_CHARACTER_ID,
			CharacterClassTable.KEY_CLASS_NAME,
			CharacterClassTable.KEY_CLASS_LEVEL
		};
		
		Cursor result = db.query(CharacterClassTable.TABLE_NAME, columns, null, null, null, null, null);
		
		if (result.moveToFirst())
		{
			do
			{
				long rowId = result.getLong(0);
				long characterId = result.getLong(1);
				String className = result.getString(2);
				int classLevel = result.getInt(3);
				
				if (characterId == character.getId())
				{
					ClassLevel c = this.classLevelAdapter.find(className, classLevel);
					
					if (c != null)
					{
						classes.add(c);
					}
					else
					{
						throw new Error("Could not load character class from SRD");
					}
					
					c.setId(rowId);
				}
			} while (result.moveToNext());
		}
		
		result.close();
		return classes;
	}
}
