package me.ashtheking.dragons.mob;

import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIAttackOnCollide;
import net.minecraft.src.EntityAIAvoidEntity;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAILeapAtTarget;
import net.minecraft.src.EntityAINearestAttackableTarget;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityArrow;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class SabreTooth extends EntityCreature {

	public SabreTooth(World par1World) {
		super(par1World);
		texture = "/mob/sabretooth.png";

		moveSpeed = 0.4F;

		getNavigator().func_48664_a(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAvoidEntity(this, net.minecraft.src.EntityCreeper.class, 16F,
				0.23F, 0.4F));
		tasks.addTask(1, new EntityAIAvoidEntity(this, me.ashtheking.dragons.mob.Troll.class, 16F,
				0.23F, 0.4F));
		tasks.addTask(6, new EntityAIWander(this, .2F));
		tasks.addTask(6, new EntityAIWatchClosest(this, net.minecraft.src.EntityPlayer.class, 10F));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(2, new EntityAILeapAtTarget(this, 0.3F));

		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityPlayer.class, 16F, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityVillager.class, 16F, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityMob.class, 16F, 0, true, false));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityAnimal.class, 24F, 0, true, false));
	}

	public SabreTooth(World par1World, EntityLiving par2EntityLiving, float par3) {
		super(par1World);
		texture = "/mob/sabretooth.png";

		moveSpeed = 0.4F;
		setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY
				+ (double) par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ,
				par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
		getNavigator().func_48664_a(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAvoidEntity(this, net.minecraft.src.EntityCreeper.class, 16F,
				0.23F, 0.4F));
		tasks.addTask(1, new EntityAIAvoidEntity(this, me.ashtheking.dragons.mob.Troll.class, 16F,
				0.23F, 0.4F));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(2, new EntityAILeapAtTarget(this, 0.3F));

		tasks.addTask(6, new EntityAIWander(this, .2F));
		tasks.addTask(6, new EntityAIWatchClosest(this, net.minecraft.src.EntityPlayer.class, 10F));

		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityPlayer.class, 16F, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityVillager.class, 16F, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityMob.class, 16F, 0, true, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityAnimal.class, 24F, 0, true, false));

	}

	protected void entityInit() {
		super.entityInit();
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

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	public int getTotalArmorValue() {
		return 4;
	}

	@Override
	public int getMaxHealth() {
		return 20;
	}

	protected String getHurtSound() {
		return "mob.wolf.growl";
	}

	public void attackAsPack() {

	}
	
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound() {
		return "mob.wolf.death";
	}

	protected float getSoundVolume() {
		return 0.4F;
	}
}
