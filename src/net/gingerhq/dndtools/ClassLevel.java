package net.gingerhq.dndtools;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;

/**
 * @author Dan Albert
 *
 */
public class ClassLevel
{
	private long id;
	
	private String name;
	private int level;
	private int baseAttackBonus;
	private int fortitudeSave;
	private int reflexSave;
	private int willSave;
	private List<String> specials;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}
	
	/**
	 * @return the baseAttackBonus
	 */
	public int getBaseAttackBonus()
	{
		return baseAttackBonus;
	}

	/**
	 * @param baseAttackBonus the baseAttackBonus to set
	 */
	public void setBaseAttackBonus(int baseAttackBonus)
	{
		this.baseAttackBonus = baseAttackBonus;
	}

	/**
	 * @return the fortitudeSave
	 */
	public int getFortitudeSave()
	{
		return fortitudeSave;
	}

	/**
	 * @param fortitudeSave the fortitudeSave to set
	 */
	public void setFortitudeSave(int fortitudeSave)
	{
		this.fortitudeSave = fortitudeSave;
	}

	/**
	 * @return the reflexSave
	 */
	public int getReflexSave()
	{
		return reflexSave;
	}

	/**
	 * @param reflexSave the reflexSave to set
	 */
	public void setReflexSave(int reflexSave)
	{
		this.reflexSave = reflexSave;
	}

	/**
	 * @return the willSave
	 */
	public int getWillSave()
	{
		return willSave;
	}

	/**
	 * @param willSave the willSave to set
	 */
	public void setWillSave(int willSave)
	{
		this.willSave = willSave;
	}

	/**
	 * @return the specials
	 */
	public List<String> getSpecials()
	{
		return specials;
	}

	/**
	 * @param specials the specials to set
	 */
	public void setSpecials(List<String> specials)
	{
		this.specials = specials;
	}
	
	public void addSpecial(String special)
	{
		this.specials.add(special);
	}
	
	public ClassLevel()
	{
		this.specials = new ArrayList<String>();
	}
	
	public String toString()
	{
		return getName() + " " + getLevel();
	}
}
