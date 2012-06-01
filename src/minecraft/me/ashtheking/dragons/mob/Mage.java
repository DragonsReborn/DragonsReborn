package me.ashtheking.dragons.mob;

import me.ashtheking.dragons.ai.EntityAIMagic;
import me.ashtheking.dragons.magic.Magic;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAINearestAttackableTarget;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityMob;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class Mage extends EntityMob {

	public Mage(World par1World) {
		super(par1World);
		texture = "/mob/zombie.png";
		moveSpeed = 0.23F;
		getNavigator().func_48664_a(true);
		tasks.addTask(1, new EntityAISwimming(this));
		// tasks.addTask(2, new EntityAIAttackOnCollide(this,
		// net.minecraft.src.EntityPlayer.class,
		// moveSpeed, true));
		// tasks.addTask(2, new EntityAIAttackOnCollide(this,
		// net.minecraft.src.EntityVillager.class,
		// moveSpeed, true));
		tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, net.minecraft.src.EntityPlayer.class, 8F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityPlayer.class, 16F, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityVillager.class, 16F, 0, false));
		// tasks.addTask(2, new EntityAIArrowAttack(this, moveSpeed, 3, 60));
		tasks.addTask(2, new EntityAIMagic(this, moveSpeed, Magic.staves.get(6).shiftedIndex, 60));
		tasks.addTask(2, new EntityAIMagic(this, moveSpeed, Magic.staves.get(8).shiftedIndex, 60));
	}

	@Override
	public int getMaxHealth() {
		return 20;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled() {
		return true;
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	protected int getDropItemId() {
		return -1;
	}

	// Defaults to Staff of Fireball
	public ItemStack getHeldItem() {
		return new ItemStack(Magic.staves.get(2), 1);
	}

}
