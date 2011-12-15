package net.gingerhq.dndtools;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Dan Albert
 *
 */
public class Character implements Serializable
{
	private static final long serialVersionUID = -3598354638331153399L;

	private long id;
	
	private String name;
	private Race race;
	private List<ClassLevel> classes;
	private int experience;

	private int strength;
	private int dexterity;
	private int constitution;
	private int intelligence;
	private int wisdom;
	private int charisma;

	/**
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * @return The character's name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @param name Name to give the character
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Race getRace()
	{
		return race;
	}
	
	public void setRace(Race race)
	{
		this.race = race;
	}
	
	/**
	 * @return the classes
	 */
	public List<ClassLevel> getClasses()
	{
		return classes;
	}

	/**
	 * @param classes the classes to set
	 */
	public void setClasses(List<ClassLevel> classes)
	{
		this.classes = classes;
	}

	/**
	 * @return the experience
	 */
	public int getExperience()
	{
		return experience;
	}

	/**
	 * @param experience the experience to set
	 */
	public void setExperience(int experience)
	{
		this.experience = experience;
	}

	/**
	 * @return the strength
	 */
	public int getStr()
	{
		return strength + this.race.getStrMod();
	}
	
	public int getStrMod()
	{
		return (int)Math.floor((getStr() - 10) / 2.0f);
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(int strength)
	{
		this.strength = strength;
	}

	/**
	 * @return the dexterity
	 */
	public int getDex()
	{
		return dexterity + this.race.getDexMod();
	}
	
	public int getDexMod()
	{
		return (int)Math.floor((getDex() - 10) / 2.0f);
	}

	/**
	 * @param dexterity the dexterity to set
	 */
	public void setDexterity(int dexterity)
	{
		this.dexterity = dexterity;
	}

	/**
	 * @return the constitution
	 */
	public int getCon()
	{
		return constitution + this.race.getConMod();
	}
	
	public int getConMod()
	{
		return (int)Math.floor((getCon() - 10) / 2.0f);
	}

	/**
	 * @param constitution the constitution to set
	 */
	public void setConstitution(int constitution)
	{
		this.constitution = constitution;
	}

	/**
	 * @return the intelligence
	 */
	public int getInt()
	{
		return intelligence + this.race.getIntMod();
	}
	
	public int getIntMod()
	{
		return (int)Math.floor((getInt() - 10) / 2.0f);
	}

	/**
	 * @param intelligence the intelligence to set
	 */
	public void setIntelligence(int intelligence)
	{
		this.intelligence = intelligence;
	}

	/**
	 * @return the wisdom
	 */
	public int getWis()
	{
		return wisdom + this.race.getWisMod();
	}
	
	public int getWisMod()
	{
		return (int)Math.floor((getWis() - 10) / 2.0f);
	}

	/**
	 * @param wisdom the wisdom to set
	 */
	public void setWisdom(int wisdom)
	{
		this.wisdom = wisdom;
	}

	/**
	 * @return the charisma
	 */
	public int getCha()
	{
		return charisma + this.race.getChaMod();
	}
	
	public int getChaMod()
	{
		return (int)Math.floor((getCha() - 10) / 2.0f);
	}

	/**
	 * @param charisma the charisma to set
	 */
	public void setCharisma(int charisma)
	{
		this.charisma = charisma;
	}
	
	public int getFortSave()
	{
		int total = 0;
		Iterator<ClassLevel> iter = getClasses().iterator();
		while (iter.hasNext())
		{
			total += iter.next().getFortitudeSave();
		}
		
		return total;
	}
	
	public int getRefSave()
	{
		int total = 0;
		Iterator<ClassLevel> iter = getClasses().iterator();
		while (iter.hasNext())
		{
			total += iter.next().getReflexSave();
		}
		
		return total;
	}
	
	public int getWillSave()
	{
		int total = 0;
		Iterator<ClassLevel> iter = getClasses().iterator();
		while (iter.hasNext())
		{
			total += iter.next().getWillSave();
		}
		
		return total;
	}
	
	public Character()
	{
		this.classes = new LinkedList<ClassLevel>();
	}
}
