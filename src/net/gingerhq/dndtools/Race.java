package net.gingerhq.dndtools;

import java.util.LinkedList;
import java.util.List;

public abstract class Race
{
	public enum Size
	{
		Fine,
		Diminutive,
		Tiny,
		Small,
		Medium,
		Large,
		Huge,
		Gargantuan,
		Colossal
	}
	
	public class Trait
	{
		String title;
		String description;
		
		public String getTitle()
		{
			return this.title;
		}
		
		public void setTitle(String title)
		{
			this.title = title;
		}
		
		public String getDescription()
		{
			return this.description;
		}
		
		public void setDescription(String description)
		{
			this.description = description;
		}
		
		public Trait(String title, String description)
		{
			setTitle(title);
			setDescription(description);
		}
	}
	
	protected String name;
	protected String favoredClass;
	
	protected int strMod = 0;
	protected int dexMod = 0;
	protected int conMod = 0;
	protected int intMod = 0;	// Min of 3 when combined with character's abilities
	protected int wisMod = 0;
	protected int chaMod = 0;
	
	protected boolean isLiterate = true;
	
	protected Size size;
	protected int speed;
	protected int extraFeats = 0;
	protected int extraSkillPoints = 0;
	
	protected List<String> automaticLanguages = new LinkedList<String>();
	protected List<String> bonusLanguages = new LinkedList<String>();
	
	protected List<String> bonusFeats = new LinkedList<String>();
	
	protected List<Trait> traits = new LinkedList<Trait>();
	
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getFavoredClass()
	{
		return favoredClass;
	}

	public void setFavoredClass(String favoredClass)
	{
		this.favoredClass = favoredClass;
	}

	public int getStrMod()
	{
		return strMod;
	}

	public void setStrMod(int strMod)
	{
		this.strMod = strMod;
	}

	public int getDexMod()
	{
		return dexMod;
	}

	public void setDexMod(int dexMod)
	{
		this.dexMod = dexMod;
	}

	public int getConMod()
	{
		return conMod;
	}

	public void setConMod(int conMod)
	{
		this.conMod = conMod;
	}

	public int getIntMod()
	{
		return intMod;
	}

	public void setIntMod(int intMod)
	{
		this.intMod = intMod;
	}

	public int getWisMod()
	{
		return wisMod;
	}

	public void setWisMod(int wisMod)
	{
		this.wisMod = wisMod;
	}

	public int getChaMod()
	{
		return chaMod;
	}

	public void setChaMod(int chaMod)
	{
		this.chaMod = chaMod;
	}
	
	public boolean isLiterate()
	{
		return isLiterate;
	}

	public void setLiterate(boolean isLiterate)
	{
		this.isLiterate = isLiterate;
	}

	public Size getSize()
	{
		return this.size;
	}
	
	public void setSize(Size size)
	{
		this.size = size;
	}
	
	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	public int getExtraFeats()
	{
		return extraFeats;
	}

	public void setExtraFeats(int extraFeats)
	{
		this.extraFeats = extraFeats;
	}

	public int getExtraSkillPoints()
	{
		return extraSkillPoints;
	}

	public void setExtraSkillPoints(int extraSkillPoints)
	{
		this.extraSkillPoints = extraSkillPoints;
	}
	
	public List<String> getAutomaticLanguages()
	{
		return this.automaticLanguages;
	}
	
	public void setAutomaticLanguages(List<String> languages)
	{
		this.automaticLanguages = languages;
	}

	public List<String> getBonusLanguages()
	{
		return bonusLanguages;
	}

	public void setBonusLanguages(List<String> bonusLanguages)
	{
		this.bonusLanguages = bonusLanguages;
	}

	public List<String> getBonusFeats()
	{
		return bonusFeats;
	}

	public void setBonusFeats(List<String> bonusFeats)
	{
		this.bonusFeats = bonusFeats;
	}

	public List<Trait> getTraits()
	{
		return traits;
	}

	public void setTraits(List<Trait> traits)
	{
		this.traits = traits;
	}
}
