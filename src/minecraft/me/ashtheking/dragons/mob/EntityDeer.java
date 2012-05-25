package me.ashtheking.dragons.mob;

import net.minecraft.src.EntityAIAvoidEntity;
import net.minecraft.src.EntityAIFollowParent;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAIMate;
import net.minecraft.src.EntityAIPanic;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAITempt;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.Item;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityDeer extends EntityAnimal {
	public EntityDeer(World par1World) {
		super(par1World);
		texture = "/mob/deer.png";
		setSize(0.9F, 0.9F);
		getNavigator().func_48664_a(true);
		float f = 0.25F;
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIPanic(this, 0.38F));
		tasks.addTask(5, new EntityAIMate(this, f));
		tasks.addTask(4, new EntityAITempt(this, 0.25F,
				Item.wheat.shiftedIndex, false));
		tasks.addTask(6, new EntityAIFollowParent(this, 0.28F));
		tasks.addTask(6, new EntityAIWander(this, f));
		tasks.addTask(6, new EntityAIWatchClosest(this,	net.minecraft.src.EntityPlayer.class, 6F)); 
			
		tasks.addTask(6, new EntityAILookIdle(this));
		 tasks.addTask(2, new EntityAIAvoidEntity(this, me.ashtheking.dragons.mob.SabreTooth.class, 18F, 0.4F, 0.4F));
		 tasks.addTask(2,new EntityAIPanic(this, .38f));
		 tasks.addTask(2, new EntityAIAvoidEntity(this, net.minecraft.src.EntityPlayer.class, 18F, 0.4F, 0.4F));
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled() {
		return true;
	}

	public int getMaxHealth() {
		return 10;
	}

	protected void entityInit() {
		super.entityInit();
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound() {
		return "mob.deer";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound() {
		return "mob.deer";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound() {
		return "mob.deerdeath";
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	protected int getDropItemId() {
		if (isBurning()) {
			return Item.porkCooked.shiftedIndex;
		} else {
			return Item.porkRaw.shiftedIndex;
		}
	}

	/**
	 * This function is used when two same-species animals in 'love mode' breed
	 * to generate the new baby animal.
	 */
	public EntityAnimal spawnBabyAnimal(EntityAnimal par1EntityAnimal) {
		return new EntityDeer(worldObj);
	}
}
