package me.ashtheking.dragons.mob;

import java.util.List;

import me.ashtheking.dragons.ai.EntityAIMagic;
import me.ashtheking.dragons.magic.Magic;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EnumCreatureAttribute;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class Necromancer extends Mage {
	public Necromancer(World par1World) {
		super(par1World);
		texture = "/mob/zombie.png";
		moveSpeed = 0.23F;
		tasks.addTask(2, new EntityAIMagic(this, moveSpeed, Magic.staves.get(9).shiftedIndex, 60));
	}

	@Override
	public boolean attackEntityAsMob(Entity e) {
		List<EntityLiving> l = worldObj.getEntitiesWithinAABB(EntityMob.class,
				this.boundingBox.expand(10D, 10D, 10D));
		for(EntityLiving el : l)
			if(!(el.getAttackTarget() == null))
				el.setAttackTarget((EntityLiving) e);
		return super.attackEntityAsMob(e);
	}
	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound() {
		return "mob.zombie";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound() {
		return "mob.zombiehurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound() {
		return "mob.zombiedeath";
	}
	
	@Override
	public ItemStack getHeldItem() {
		return new ItemStack(Magic.staves.get(9), 1);
	}

}