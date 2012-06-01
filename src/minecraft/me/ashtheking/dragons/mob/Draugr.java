package me.ashtheking.dragons.mob;

import java.util.Random;

import me.ashtheking.dragons.ids.Weapons;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class Draugr extends EntityMob {

	State state;
	Random rand = new Random();
	private EntityLiving closestEntity;
	private ItemStack heldItem;
	int i = rand.nextInt(3);

	public Draugr(World par1World) {
		super(par1World);
		texture = "/mob/draugr.png";
		setInitialState();
		moveSpeed = .4f;
		this.landMovementFactor = .2f;
		pickWeapon();
	}

	public Draugr(World par1World, EntityLiving par2EntityLiving, float par3) {
		super(par1World);
		texture = "/mob/draugr.png";
		setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY
				+ (double) par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ,
				par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
		setInitialState();
		moveSpeed = .4f;
		this.landMovementFactor = .2f;
		pickWeapon();

	}

	
	public void pickWeapon() {

		if (i == 0) {
			heldItem = new ItemStack(Weapons.swords.get(0));
		}
		if (i == 1) {
			heldItem = new ItemStack(Weapons.maces.get(0));
		}
		if (i == 2) {
			heldItem = new ItemStack(Weapons.battleaxes.get(0));
		}
		if (i == 3) {
			heldItem = new ItemStack(Weapons.daggers.get(0));
		}
	}

	public void setInitialState() {
		if (this.state == null) {
			this.state = State.Dormant;
		}
	}

	@Override
	public int getMaxHealth() {

		return 40;
	}

	public enum State {
		Dormant, Active,
	}

	public State getState() {
		return state; 
	}

	public void setState(State state) {
		this.state = state;
	}
@Override
	protected void updateEntityActionState() {

		switch (state) {
		case Dormant:
			Dormant();
			break;

		case Active:
			Active();
			super.updateEntityActionState();
			break;

		}

	}

	public void Active() {
		closestEntity = (EntityLiving) worldObj.findNearestEntityWithinAABB(EntityPlayer.class,
				boundingBox.expand(64d, 3D, 64d), this);

		if (closestEntity != null) {
			setAttackTarget(closestEntity);
			entityToAttack = closestEntity;
		}
		if (closestEntity == null) {

			super.updateEntityActionState();
		}

	}

	public void Dormant() {

		closestEntity = (EntityLiving) worldObj.findNearestEntityWithinAABB(EntityPlayer.class,
				boundingBox.expand(5d, 3D, 5d), this);

		if (closestEntity != null && this.canEntityBeSeen(closestEntity)) {

			setState(State.Active);
		} else {
			return;
		}

	}

	public boolean isAwake() {
		return state == State.Active;
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {

		par1NBTTagCompound.setBoolean("State", isAwake());
		super.writeEntityToNBT(par1NBTTagCompound);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {

		if (par1NBTTagCompound.getBoolean("State")) {
			this.setState(State.Active);
		}
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	public ItemStack getHeldItem() {
		return heldItem;
	}

}