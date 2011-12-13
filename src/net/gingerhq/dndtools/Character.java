package net.gingerhq.dndtools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Dan Albert
 *
 */
public class Character implements Serializable
{
	private long id;
	
	private String name;
	private String race;
	private List<CharacterClass> classes;
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
	
	public String getRace()
	{
		return race;
	}
	
	public void setRace(String race)
	{
		this.race = race;
	}
	
	/**
	 * @return the classes
	 */
	public List<CharacterClass> getClasses()
	{
		return classes;
	}

	/**
	 * @param classes the classes to set
	 */
	public void setClasses(List<CharacterClass> classes)
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
	public int getStrength()
	{
		return strength;
	}
	
	public int getStrengthMod()
	{
		return (int)Math.floor((getStrength() - 10) / 2.0f);
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
	public int getDexterity()
	{
		return dexterity;
	}
	
	public int getDexterityMod()
	{
		return (int)Math.floor((getDexterity() - 10) / 2.0f);
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
	public int getConstitution()
	{
		return constitution;
	}
	
	public int getConstitutionMod()
	{
		return (int)Math.floor((getConstitution() - 10) / 2.0f);
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
	public int getIntelligence()
	{
		return intelligence;
	}
	
	public int getIntelligenceMod()
	{
		return (int)Math.floor((getIntelligence() - 10) / 2.0f);
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
	public int getWisdom()
	{
		return wisdom;
	}
	
	public int getWisdomMod()
	{
		return (int)Math.floor((getWisdom() - 10) / 2.0f);
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
	public int getCharisma()
	{
		return charisma;
	}
	
	public int getCharismaMod()
	{
		return (int)Math.floor((getCharisma() - 10) / 2.0f);
	}

	/**
	 * @param charisma the charisma to set
	 */
	public void setCharisma(int charisma)
	{
		this.charisma = charisma;
	}
	
	public int getFortitudeSave()
	{
		int total = 0;
		Iterator<CharacterClass> iter = getClasses().iterator();
		while (iter.hasNext())
		{
			total += iter.next().getFortitudeSave();
		}
		
		return total;
	}
	
	public int getReflexSave()
	{
		int total = 0;
		Iterator<CharacterClass> iter = getClasses().iterator();
		while (iter.hasNext())
		{
			total += iter.next().getReflexSave();
		}
		
		return total;
	}
	
	public int getWillSave()
	{
		int total = 0;
		Iterator<CharacterClass> iter = getClasses().iterator();
		while (iter.hasNext())
		{
			total += iter.next().getWillSave();
		}
		
		return total;
	}
	
	public Character()
	{
		this.classes = new ArrayList<CharacterClass>();
	}
}
