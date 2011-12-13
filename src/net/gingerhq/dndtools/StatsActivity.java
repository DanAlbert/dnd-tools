package net.gingerhq.dndtools;

import java.util.Iterator;

import net.gingerhq.dndtools.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Dan Albert
 *
 */
public class StatsActivity extends Activity
{
	private Character character;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats);
		
		Bundle bundle = getIntent().getExtras();
		this.character = (Character)bundle.getSerializable("character");
		
		TextView characterName = (TextView)findViewById(R.id.characterName);
		TextView race = (TextView)findViewById(R.id.race);
		TextView characterClass = (TextView)findViewById(R.id.classText);
		
		TextView strength = (TextView)findViewById(R.id.strScore);
		TextView dexterity = (TextView)findViewById(R.id.dexScore);
		TextView constitution = (TextView)findViewById(R.id.conScore);
		TextView intelligence = (TextView)findViewById(R.id.intScore);
		TextView wisdom = (TextView)findViewById(R.id.wisScore);
		TextView charisma = (TextView)findViewById(R.id.chaScore);
		
		TextView strMod = (TextView)findViewById(R.id.strMod);
		TextView dexMod = (TextView)findViewById(R.id.dexMod);
		TextView conMod = (TextView)findViewById(R.id.conMod);
		TextView intMod = (TextView)findViewById(R.id.intMod);
		TextView wisMod = (TextView)findViewById(R.id.wisMod);
		TextView chaMod = (TextView)findViewById(R.id.chaMod);
		
		TextView fortSave = (TextView)findViewById(R.id.fortScore);
		TextView refSave = (TextView)findViewById(R.id.refScore);
		TextView willSave = (TextView)findViewById(R.id.willScore);
		
		characterName.setText(this.character.getName());
		race.setText(this.character.getRace());
		
		String classText = "";
		Iterator<CharacterClass> iter = this.character.getClasses().iterator();
		while (iter.hasNext())
		{
			CharacterClass current = iter.next();
			classText += current.toString();
			
			if (iter.hasNext())
			{
				classText += ", ";
			}
		}
		
		characterClass.setText(classText);
		
		strength.setText(Integer.toString(this.character.getStrength()));
		strMod.setText(getModifierText(this.character.getStrengthMod()));
		
		dexterity.setText(Integer.toString(this.character.getDexterity()));
		dexMod.setText(getModifierText(this.character.getDexterityMod()));
		
		constitution.setText(Integer.toString(this.character.getConstitution()));
		conMod.setText(getModifierText(this.character.getConstitutionMod()));
		
		intelligence.setText(Integer.toString(this.character.getIntelligence()));
		intMod.setText(getModifierText(this.character.getIntelligenceMod()));
		
		wisdom.setText(Integer.toString(this.character.getWisdom()));
		wisMod.setText(getModifierText(this.character.getWisdomMod()));
		
		charisma.setText(Integer.toString(this.character.getCharisma()));
		chaMod.setText(getModifierText(this.character.getCharismaMod()));
		
		fortSave.setText(Integer.toString(this.character.getFortitudeSave()));
		refSave.setText(Integer.toString(this.character.getReflexSave()));
		willSave.setText(Integer.toString(this.character.getWillSave()));
	}
	
	private String getModifierText(int modifier)
	{
		if (modifier >= 0)
		{
			return "+" + modifier;
		}
		else
		{
			return Integer.toString(modifier);
		}
	}
}
