package net.gingerhq.dndtools;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CharacterDbAdapter
{
	private Context context;
	private SQLiteDatabase db;
	private CharacterDbHelper dbHelper;
	
	public CharacterDbAdapter(Context context)
	{
		this.context = context;
	}
	
	public void open() throws SQLException
	{
		this.dbHelper = new CharacterDbHelper(context);
		db = dbHelper.getWritableDatabase();
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public boolean create(Character character)
	{
		ContentValues values = new ContentValues();
		values.put(CharacterTable.KEY_NAME, character.getName());
		values.put(CharacterTable.KEY_RACE, character.getRace());
		values.put(CharacterTable.KEY_EXPERIENCE, character.getExperience());
		values.put(CharacterTable.KEY_STRENGTH, character.getStrength());
		values.put(CharacterTable.KEY_DEXTERITY, character.getDexterity());
		values.put(CharacterTable.KEY_CONSTITUTION, character.getConstitution());
		values.put(CharacterTable.KEY_INTELLIGENCE, character.getIntelligence());
		values.put(CharacterTable.KEY_WISDOM, character.getWisdom());
		values.put(CharacterTable.KEY_CHARISMA, character.getCharisma());
		
		long id = db.insert(CharacterTable.TABLE_NAME, null, values);
		if (id == -1)
		{
			return false;
		}
		
		character.setId(id);
		
		return true;
	}
	
	public boolean update(Character character)
	{
		ContentValues values = new ContentValues();
		values.put(CharacterTable.KEY_NAME, character.getName());
		values.put(CharacterTable.KEY_RACE, character.getRace());
		values.put(CharacterTable.KEY_EXPERIENCE, character.getExperience());
		values.put(CharacterTable.KEY_STRENGTH, character.getStrength());
		values.put(CharacterTable.KEY_DEXTERITY, character.getDexterity());
		values.put(CharacterTable.KEY_CONSTITUTION, character.getConstitution());
		values.put(CharacterTable.KEY_INTELLIGENCE, character.getIntelligence());
		values.put(CharacterTable.KEY_WISDOM, character.getWisdom());
		values.put(CharacterTable.KEY_CHARISMA, character.getCharisma());
		
		return db.update(CharacterTable.TABLE_NAME, values, CharacterTable.KEY_ID + "=" + character.getId(), null) > 0;
	}
	
	public boolean delete(Character character)
	{
		return db.delete(CharacterTable.TABLE_NAME, CharacterTable.KEY_ID + "=" + character.getId(), null) > 0;
	}
	
	public Character find(long id)
	{
		String[] columns = new String[]
		{
			CharacterTable.KEY_ID,
			CharacterTable.KEY_NAME,
			CharacterTable.KEY_RACE,
			CharacterTable.KEY_EXPERIENCE,
			CharacterTable.KEY_STRENGTH,
			CharacterTable.KEY_DEXTERITY,
			CharacterTable.KEY_CONSTITUTION,
			CharacterTable.KEY_INTELLIGENCE,
			CharacterTable.KEY_WISDOM,
			CharacterTable.KEY_CHARISMA
		};
		
		Cursor result = db.query(CharacterTable.TABLE_NAME, columns, CharacterTable.KEY_ID + "=" + id, null, null, null, null);
		
		Character character = null;
		if (result.moveToFirst())
		{
			character = new Character();
			character.setId(result.getLong(0));
			character.setName(result.getString(1));
			character.setRace(result.getString(2));
			character.setExperience(result.getInt(3));
			character.setStrength(result.getInt(4));
			character.setDexterity(result.getInt(5));
			character.setConstitution(result.getInt(6));
			character.setIntelligence(result.getInt(7));
			character.setWisdom(result.getInt(8));
			character.setCharisma(result.getInt(9));
		}
		
		result.close();
		return character;
	}
	public List<Character> fetchAll()
	{
		List<Character> characters = new ArrayList<Character>();
		String[] columns = new String[]
		{
			CharacterTable.KEY_ID,
			CharacterTable.KEY_NAME,
			CharacterTable.KEY_RACE,
			CharacterTable.KEY_EXPERIENCE,
			CharacterTable.KEY_STRENGTH,
			CharacterTable.KEY_DEXTERITY,
			CharacterTable.KEY_CONSTITUTION,
			CharacterTable.KEY_INTELLIGENCE,
			CharacterTable.KEY_WISDOM,
			CharacterTable.KEY_CHARISMA
		};
		
		Cursor result = db.query(CharacterTable.TABLE_NAME, columns, null, null, null, null, null);
		
		if (result.moveToFirst())
		{
			do
			{
				Character character = new Character();
				character.setId(result.getLong(0));
				character.setName(result.getString(1));
				character.setRace(result.getString(2));
				character.setExperience(result.getInt(3));
				character.setStrength(result.getInt(4));
				character.setDexterity(result.getInt(5));
				character.setConstitution(result.getInt(6));
				character.setIntelligence(result.getInt(7));
				character.setWisdom(result.getInt(8));
				character.setCharisma(result.getInt(9));
				
				characters.add(character);
			} while (result.moveToNext());
		}
		
		result.close();
		return characters;
	}
}
