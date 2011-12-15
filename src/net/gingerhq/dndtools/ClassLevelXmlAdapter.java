package net.gingerhq.dndtools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;

public class ClassLevelXmlAdapter implements ClassLevelAdapter
{
	private static final String CLASS_LEVEL_TAG = "ClassLevel";
	private static final String NAME_TAG = "Name";
	private static final String LEVEL_TAG = "Level";
	private static final String BASE_ATTACK_BONUS_TAG = "BaseAttackBonus";
	private static final String FORTITUDE_SAVE_TAG = "FortitudeSave";
	private static final String REFLEX_SAVE_TAG = "ReflexSave";
	private static final String WILL_SAVE_TAG = "WillSave";
	private static final String SPECIALS_TAG = "Special";
	
	private Context context;
	
	private Map<String, Map<Integer, ClassLevel>> classLevels =
			new HashMap<String, Map<Integer, ClassLevel>>();
	
	public ClassLevelXmlAdapter(Context context) throws XmlPullParserException, IOException
	{
		this.context = context;
		parse();
	}
	
	private void parse() throws XmlPullParserException, IOException
	{
		XmlResourceParser xml = this.context.getResources().getXml(R.xml.class_levels);
		Stack<String> nodes = new Stack<String>();
		ClassLevel c = null;
		
		int eventType = xml.next();
		while (eventType != XmlPullParser.END_DOCUMENT)
		{
			String node;
			String text;
			
			switch (eventType)
			{
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				node = xml.getName();
				nodes.push(node);
				if (node.equals(CLASS_LEVEL_TAG))
				{
					c = new ClassLevel();
				}
				break;
			case XmlPullParser.END_TAG:
				if (nodes.pop().equals(CLASS_LEVEL_TAG))
				{
					Map<Integer, ClassLevel> levelMap;
					
					if (!classLevels.containsKey(c.getName()))
					{
						levelMap = new HashMap<Integer, ClassLevel>();
						classLevels.put(c.getName(), levelMap);
					}
					else
					{
						levelMap = classLevels.get(c.getName());
					}
					
					levelMap.put(c.getLevel(), c);
				}
				break;
			case XmlPullParser.TEXT:
				node = nodes.peek();
				text = xml.getText();
				if (node.equals(NAME_TAG))
				{
					c.setName(text);
				}
				else if (node.equals(LEVEL_TAG))
				{
					c.setLevel(Integer.parseInt(text));
				}
				else if (node.equals(BASE_ATTACK_BONUS_TAG))
				{
					String[] bonuses = text.split("/");
					c.setBaseAttackBonus(Integer.parseInt(bonuses[0]));
				}
				else if (node.equals(FORTITUDE_SAVE_TAG))
				{
					c.setFortitudeSave(Integer.parseInt(text));
				}
				else if (node.equals(REFLEX_SAVE_TAG))
				{
					c.setReflexSave(Integer.parseInt(text));
				}
				else if (node.equals(WILL_SAVE_TAG))
				{
					c.setWillSave(Integer.parseInt(text));
				}
				else if (node.equals(SPECIALS_TAG))
				{
					String[] specials = text.split(",");
					for (int i = 0; i < specials.length; i++)
					{
						c.addSpecial(specials[i]);
					}
				}
				break;
			}
			eventType = xml.next();
		}
	}
	
	public ClassLevel find(String name, int level)
	{	
		if (this.classLevels.containsKey(name))
		{
			if (this.classLevels.get(name).containsKey(level))
			{
				return this.classLevels.get(name).get(level);
			}
		}
		
		return null;
	}
}
