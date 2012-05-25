package me.ashtheking.dragons.enchanting;

import java.util.HashMap;
import java.util.Map;

import me.ashtheking.dragons.mob.Imperial;
import me.ashtheking.dragons.mob.Stormcloak;
import net.minecraft.src.EntityPigZombie;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntitySlime;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.*;



public class SoulTiers 
{
	public Map soulTiers = new HashMap();
	/*
	public int petty = mod_Dragon.petty.shiftedIndex;
	public int lesser = mod_Dragon.lesser.shiftedIndex;
	public int common = mod_Dragon.common.shiftedIndex;
	public int greater =  mod_Dragon.greater.shiftedIndex;
	public int grand =  mod_Dragon.grand.shiftedIndex;

	public SoulTiers()
	{
		setSoulTier(EntityZombie.class, petty);
		setSoulTier(EntitySkeleton.class, petty);
		setSoulTier(EntitySlime.class, petty);
		setSoulTier(EntityPigZombie.class, lesser);
		setSoulTier(Imperial.class, common);
		setSoulTier(Stormcloak.class, common);
	}
*/	
	
	public void setSoulTier(Class entity, int soulLevel)
	{
		soulTiers.put(entity, soulLevel);
	}
	
	public int getSoulTier(Class entity)
	{
		return (Integer)soulTiers.get(entity);
	}
}
