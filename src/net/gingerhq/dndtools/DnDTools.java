package net.gingerhq.dndtools;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.gingerhq.dndtools.R;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * @author Dan Albert
 *
 */
public class DnDTools extends TabActivity
{
	private CharacterDbAdapter characterDbAdapter;
	private CharacterClassDbAdapter classDbAdapter;
	private ClassLevelAdapter classLevelAdapter;
	private Character character;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		this.characterDbAdapter = new CharacterDbAdapter(this);
		this.characterDbAdapter.open();
		
		this.classDbAdapter = new CharacterClassDbAdapter(this);
		this.classDbAdapter.open();
		
		try
		{
			this.classLevelAdapter = new ClassLevelXmlAdapter(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		loadCharacter();
		
		TabHost tabHost = getTabHost();
		TabHost.TabSpec tabSpec;
		Intent intent;

		intent = new Intent().setClass(this, StatsActivity.class);
		intent.putExtra("character", character);
		tabSpec = tabHost.newTabSpec("statistics").setIndicator("Stats").setContent(intent);
		tabHost.addTab(tabSpec);
		
		intent = new Intent().setClass(this, SkillsActivity.class);
		intent.putExtra("character", character);
		tabSpec = tabHost.newTabSpec("skills").setIndicator("Skills").setContent(intent);
		tabHost.addTab(tabSpec);
		
		intent = new Intent().setClass(this, SpellsActivity.class);
		intent.putExtra("character", character);
		tabSpec = tabHost.newTabSpec("spells").setIndicator("Spells").setContent(intent);
		tabHost.addTab(tabSpec);
		
		intent = new Intent().setClass(this, InventoryActivity.class);
		intent.putExtra("character", character);
		tabSpec = tabHost.newTabSpec("inventory").setIndicator("Inventory").setContent(intent);
		tabHost.addTab(tabSpec);
		
		tabHost.setCurrentTab(0);
	}
	
	private void loadCharacter()
	{
		List<Character> characters = characterDbAdapter.fetchAll();
		
		if (characters.size() > 0)
		{
			Iterator<Character> iter = characters.iterator();
			while (iter.hasNext())
			{
				Character current = iter.next();
				if (current.equals("Talandil Orren"))
				{
					this.character = current;
					
					// Load character's classes
					List<ClassLevel> classes = classDbAdapter.fetchAll(this.character);
					this.character.setClasses(classes);
					
					return;
				}
			}
		}
		
		// If not found, create
		this.character = new Character();
		character.setName("Talandil Orren");
		character.setRace(new Elf());
		character.setStrength(11);
		character.setDexterity(14);
		character.setConstitution(15);
		character.setIntelligence(13);
		character.setWisdom(12);
		character.setCharisma(18);
		
		// Add level 4 sorcerer to class list
		List<ClassLevel> classes = new LinkedList<ClassLevel>();
		ClassLevel c = this.classLevelAdapter.find("Sorcerer", 4);
		if (c != null)
		{
			classes.add(c);
		}
		else
		{
			throw new Error("Could not load character class");
		}
		
		// Set character's classes
		character.setClasses(classes);
		
		characterDbAdapter.create(character);
		classDbAdapter.reconcile(character);
	}
}