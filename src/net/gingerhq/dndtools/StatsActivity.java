package net.gingerhq.dndtools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.gingerhq.dndtools.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
		
		TextView strModText = (TextView)findViewById(R.id.strMod);
		TextView dexModtext = (TextView)findViewById(R.id.dexMod);
		TextView conModText = (TextView)findViewById(R.id.conMod);
		TextView intModText = (TextView)findViewById(R.id.intMod);
		TextView wisModText = (TextView)findViewById(R.id.wisMod);
		TextView chaModText = (TextView)findViewById(R.id.chaMod);
		
		TextView fortSave = (TextView)findViewById(R.id.fortScore);
		TextView refSave = (TextView)findViewById(R.id.refScore);
		TextView willSave = (TextView)findViewById(R.id.willScore);
		
		ListView traits = (ListView)findViewById(R.id.traits);
		
		characterName.setText(this.character.getName());
		race.setText(this.character.getRace().getName());
		
		String classText = "";
		Iterator<ClassLevel> classIter = this.character.getClasses().iterator();
		while (classIter.hasNext())
		{
			ClassLevel current = classIter.next();
			classText += current.toString();
			
			if (classIter.hasNext())
			{
				classText += ", ";
			}
		}
		
		characterClass.setText(classText);
		
		strength.setText(Integer.toString(this.character.getStr()));
		strModText.setText(getModText(this.character.getStrMod()));
		
		dexterity.setText(Integer.toString(this.character.getDex()));
		dexModtext.setText(getModText(this.character.getDexMod()));
		
		constitution.setText(Integer.toString(this.character.getCon()));
		conModText.setText(getModText(this.character.getConMod()));
		
		intelligence.setText(Integer.toString(this.character.getInt()));
		intModText.setText(getModText(this.character.getIntMod()));
		
		wisdom.setText(Integer.toString(this.character.getWis()));
		wisModText.setText(getModText(this.character.getWisMod()));
		
		charisma.setText(Integer.toString(this.character.getCha()));
		chaModText.setText(getModText(this.character.getChaMod()));
		
		fortSave.setText(Integer.toString(this.character.getFortSave()));
		refSave.setText(Integer.toString(this.character.getRefSave()));
		willSave.setText(Integer.toString(this.character.getWillSave()));
		
		List<Map<String, String>> traitsMapList = new LinkedList<Map<String, String>>();
		
		Iterator<Race.Trait> traitIter = this.character.getRace().getTraits().iterator();
		while (traitIter.hasNext())
		{
			Race.Trait current = traitIter.next();
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("title", current.getTitle());
			map.put("description", current.getDescription());
			
			traitsMapList.add(map);
		}
		
		traits.setAdapter(new SimpleAdapter(
				this,
				traitsMapList,
				R.layout.list_item,
				new String[] {"title", "description"},
				new int[] {R.id.title, R.id.description}));
	}
	
	private String getModText(int modifier)
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
