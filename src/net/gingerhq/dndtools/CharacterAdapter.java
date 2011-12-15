package net.gingerhq.dndtools;

import java.util.List;

public interface CharacterAdapter
{
	public boolean create(Character character);
	public boolean update(Character character);
	public boolean delete(Character character);
	
	public Character find(long id);
	public List<Character> fetchAll();
}
