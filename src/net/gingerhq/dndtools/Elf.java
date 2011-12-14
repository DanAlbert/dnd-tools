package net.gingerhq.dndtools;

public class Elf extends Race
{
	public Elf()
	{
		this.name = "Elf";
		this.dexMod = 2;
		this.conMod = -2;
		this.size = Size.Medium;
		this.speed = 30;
		this.bonusFeats.add("Martial Weapon Proficiency");
		this.automaticLanguages.add("Common");
		this.automaticLanguages.add("Elven");
		this.favoredClass = "Wizard";
		
		this.traits.add(new Trait("Immunities",
				"Immune to magic sleep effects, and a +2 racial saving throw " +
				"bonus against enchantment spells or effects."));
		
		this.traits.add(new Trait("Low-Light Vision",
				"An elf can see twice as far as a human in starlight, " +
				"moonlight, torchlight, and similar conditions of poor " +
				"illumination. She retains the ability to distinguish color " +
				"and detail under these conditions."));
		
		this.traits.add(new Trait("Bonus Checks",
				"+2 racial bonus on Listen, Search, and Spot checks. An elf " +
				"who merely passes within 5 feet of a secret or concealed " +
				"door is entitled to a Search check to notice it as if she " +
				"were actively looking for it."));
	}
}
