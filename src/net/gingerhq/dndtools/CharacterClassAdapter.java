package net.gingerhq.dndtools;

import java.util.List;

public interface CharacterClassAdapter
{
	public boolean reconcile(Character character);
	public boolean delete(ClassLevel c);
	
	public List<ClassLevel> fetchAll(Character character);
}
