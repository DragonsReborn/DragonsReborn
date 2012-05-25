package me.ashtheking.dragons.mob;

import net.minecraft.src.EntityMob;
import net.minecraft.src.World;

public class Draugr extends EntityMob {

	public Draugr(World par1World) {
		super(par1World);
		//texture = "/mob/draugr.png";
	}

	@Override
	public int getMaxHealth() {
		return 20;
	}

}
