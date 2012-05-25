package me.ashtheking.dragons.mob;

import java.util.List;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIAttackOnCollide;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAINearestAttackableTarget;
import net.minecraft.src.EntityAIPanic;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.EnumCreatureAttribute;
import net.minecraft.src.Item;
import net.minecraft.src.World;

public class Necromancer extends EntityMob {
	public Necromancer(World par1World) {
		super(par1World);
		texture = "/mob/zombie.png";
		moveSpeed = 0.23F;
		getNavigator().func_48664_a(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, net.minecraft.src.EntityPlayer.class,
				moveSpeed, true));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, net.minecraft.src.EntityVillager.class,
				moveSpeed, true));
		tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, net.minecraft.src.EntityPlayer.class, 8F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityPlayer.class, 16F, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityVillager.class, 16F, 0, false));
	}

	public int getMaxHealth() {
		return 20;
	}

	@Override
	public boolean attackEntityAsMob(Entity e) {
		EntityMob en = this.rand.nextBoolean() ? new EntitySkeleton(worldObj) : new EntityZombie(
				worldObj);
		en.setLocationAndAngles(e.posX, e.posY, e.posZ, 0.0F, 0.0F);
		worldObj.spawnEntityInWorld(en);
		en.setAttackTarget((EntityLiving) e);
		List<EntityLiving> l = worldObj.getEntitiesWithinAABB(EntityMob.class,
				this.boundingBox.expand(10D, 10D, 10D));
		for(EntityLiving el : l)
			if(!(el.getAttackTarget() == null))
				el.setAttackTarget((EntityLiving) e);
		return super.attackEntityAsMob(e);
	}

	/**
	 * Returns the current armor value as determined by a call to
	 * InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue() {
		return 2;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled() {
		return true;
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

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	protected int getDropItemId() {
		return -1;
	}

	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	protected void dropRareDrop(int par1) {
		switch (rand.nextInt(4)) {
		case 0:
			dropItem(Item.swordSteel.shiftedIndex, 1);
			break;

		case 1:
			dropItem(Item.helmetSteel.shiftedIndex, 1);
			break;

		case 2:
			dropItem(Item.ingotIron.shiftedIndex, 1);
			break;

		case 3:
			dropItem(Item.shovelSteel.shiftedIndex, 1);
			break;
		}
	}
}
