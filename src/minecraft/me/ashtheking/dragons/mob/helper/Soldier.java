package me.ashtheking.dragons.mob.helper;

import me.ashtheking.dragons.world.Hold;
import me.ashtheking.dragons.world.HoldManager;
import me.ashtheking.dragons.world.Location;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAILeapAtTarget;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAINearestAttackableTarget;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityArrow;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraft.src.mod_Dragon;

public class Soldier extends EntityCreature {

	private ItemStack heldItem = new ItemStack(Item.swordSteel, 1);
	private int swingProgressInt = 0;
	private boolean isSwinging = false;
	public String name = "Soldier";

	public Soldier(World world) {
		super(world);
        moveSpeed = 0.3F;
		// texture = faction ? "/mob/stormcloak.png" : "/mob/imperial.png";
		
        tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this, net.minecraft.src.EntityPlayer.class, 8F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				me.ashtheking.dragons.mob.Dragon.class, 64F, 0, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityMob.class, 32F, 0, false));
	}
	public boolean attackEntityAsMob(Entity par1Entity) {

		par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 7 + rand.nextInt(3));

		return true;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		Entity entity = par1DamageSource.getEntity();

		if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
			par2 = (par2 + 1) / 2;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public int getMaxHealth() {
		return 20;
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkIfAABBIsClear(boundingBox)
				&& worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0
				&& !worldObj.isAnyLiquid(boundingBox) && checkSpawn();
	}

	private boolean checkSpawn() {
		Location loc = new Location((int) posX, (int) posY, (int) posZ);
		if (mod_Dragon.holdManager == null)
			mod_Dragon.holdManager = new HoldManager(worldObj);
		Hold h = mod_Dragon.holdManager.getHold(loc);
		int x = 5;
		if (h != null) {
			if (h.faction.equals(this.getClass()))
				x = 8;
			else
				x = 4;
		}
		return rand.nextInt(10) + 1 <= x;
	}

	@Override
	protected void attackEntity(Entity entity, float f) {
		if (attackTime == 0) {
			if (f < 20F && f > 2.0F) {
				heldItem = new ItemStack(Item.bow, 1);
				EntityArrow entityarrow = new EntityArrow(worldObj, this, 1.0F);
				double d = entity.posX - posX;
				double d1 = entity.posZ - posZ;
				double d2 = (entity.posY + (double) entity.getEyeHeight()) - 0.69999998807907104D
						- entityarrow.posY;
				float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
				worldObj.playSoundAtEntity(this, "random.bow", 1.0F,
						1.0F / (rand.nextFloat() * 0.4F + 0.8F));
				worldObj.spawnEntityInWorld(entityarrow);
				entityarrow.setArrowHeading(d, d2 + (double) f1, d1, 2.6F, 12F);
			}
			if (f <= 2.0F && entity.boundingBox.maxY > boundingBox.minY
					&& entity.boundingBox.minY < boundingBox.maxY) {
				heldItem = new ItemStack(Item.swordSteel, 1);
				swingItem();
				entity.attackEntityFrom(DamageSource.causeMobDamage(this),
						heldItem.getDamageVsEntity(entity));
			}
			attackTime = 20;
		}
	}

	public void swingItem() {
		if (!isSwinging || swingProgressInt >= 3 || swingProgressInt < 0) {
			swingProgressInt = -1;
			isSwinging = true;
		}
	}

	protected void updateEntityActionState() {
		int i = 6;
		if (this.getEntityToAttack() == null)
			isSwinging = false;
		if (isSwinging) {
			swingProgressInt++;
			if (swingProgressInt >= i)
				swingProgressInt = 0;
		} else
			swingProgressInt = 0;
		swingProgress = (float) swingProgressInt / (float) i;
		super.updateEntityActionState();
	}

	public ItemStack getHeldItem() {
		return heldItem;
	}

	@Override
	public int getDropItemId() {
		return heldItem.itemID;
	}

	protected String getLivingSound() {
		return "mob.dragon";
	}

	protected String getHurtSound() {
		return "mod.mob.dragon";
	}

	protected String getDeathSound() {
		return "dragon";
	}

}
