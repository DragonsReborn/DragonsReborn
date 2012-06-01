package me.ashtheking.dragons.mob;

import me.ashtheking.dragons.magic.Shouts;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.World;

public class Troll extends EntityMob {

	public Troll(World world) {
		super(world);
		attackStrength = 10;
		texture = "/mob/troll.png";
	}

	@Override
	public int getMaxHealth() {
		return 100;
	}

	@Override
	protected void attackEntity(Entity e, float f) 
	{
		super.attackEntity(e, f);
		if (f < 2.0F)
			Shouts.knockBack(e, 2, -(e.posX - this.posX), -(e.posZ - this.posZ));
		EntityLiving el = (EntityLiving) e;
		/*
		if (el.getHealth() - attackStrength <= 0)
			Shouts.knockBack(el, Math.abs(el.getHealth() - attackStrength), -(e.posX - this.posX),
					-(e.posZ - this.posZ));
	*/
	}

}
