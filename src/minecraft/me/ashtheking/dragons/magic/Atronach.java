package me.ashtheking.dragons.magic;

import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIAttackOnCollide;
import net.minecraft.src.EntityAIFollowOwner;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAIOwnerHurtByTarget;
import net.minecraft.src.EntityAIOwnerHurtTarget;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityArrow;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class Atronach extends EntityTameable {

	public Atronach(World world) {
		super(world);
		texture = "/mob/atronach.png";
		moveSpeed = 1.3F;
		this.isImmuneToFire = true;
		getNavigator().func_48664_a(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(2, new EntityAIFollowOwner(this, moveSpeed, 10F, 2.0F));
		tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(2, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
	}

	public boolean isAIEnabled() {
		return true;
	}

	public int getMaxHealth() {
		return 80;
	}

	protected void entityInit() {
		super.entityInit();
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
	}

	protected String getLivingSound() {
		return "mob.wolf.bark";
	}

	protected String getHurtSound() {
		return "mob.wolf.hurt";
	}

	protected String getDeathSound() {
		return "mob.wolf.death";
	}

	protected float getSoundVolume() {
		return 0.4F;
	}

	protected int getDropItemId() {
		return 0;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.showSmokeFX();
		this.setFire(100);
		if (worldObj.getBlockId((int) posX, (int) posY, (int) posZ) != Block.fire.blockID) {
			new Thread(new Watcher(worldObj, (int) posX, (int) posY,
					(int) posZ, worldObj.getBlockId((int) posX, (int) posY,
							(int) posZ), 2)).start();
			worldObj.setBlock((int) posX, (int) posY, (int) posZ,
					Block.fire.blockID);
		}
	}

	public float getEyeHeight() {
		return height * 0.8F;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		Entity entity = par1DamageSource.getEntity();
		aiSit.func_48407_a(false);

		if (entity != null && !(entity instanceof EntityPlayer)
				&& !(entity instanceof EntityArrow)) {
			par2 = (par2 + 1) / 2;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void attackEntity(Entity entity, float f) {
		if (f < 10F) {
			double d = entity.posX - posX;
			double d1 = entity.posZ - posZ;
			if (attackTime == 0) {
				StaffEntity entityarrow = new StaffEntity(worldObj, this, 1.0F) {
					public void onCollide() {
						worldObj.createExplosion(shootingEntity, posX, posY,
								posZ, 1F);
					}
				};
				double d2 = (entity.posY + (double) entity.getEyeHeight())
						- 0.69999998807907104D - entityarrow.posY;
				float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
				worldObj.playSoundAtEntity(this, "random.bow", 1.0F,
						1.0F / (rand.nextFloat() * 0.4F + 0.8F));
				worldObj.spawnEntityInWorld(entityarrow);
				entityarrow.setArrowHeading(d, d2 + (double) f1, d1, 1.6F, 12F);
				attackTime = 40;
			}
			rotationYaw = (float) ((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
			hasAttacked = true;
		}
	}

	void showSmokeFX() {
		for (int i = 0; i < 7; i++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("smoke", (posX + (double) (rand.nextFloat()
					* width * 2.0F))
					- (double) width, posY + 0.5D
					+ (double) (rand.nextFloat() * height),
					(posZ + (double) (rand.nextFloat() * width * 2.0F))
							- (double) width, d, d1, d2);
		}
	}

	public int getMaxSpawnedInChunk() {
		return 8;
	}

	private static class Watcher implements Runnable {

		public int oldId;
		public int duration;
		public World worldObj;
		public int posX;
		public int posY;
		public int posZ;

		public Watcher(World world, int x, int y, int z, int id, int dur) {
			oldId = id;
			duration = dur;
			worldObj = world;
			posX = x;
			posY = y;
			posZ = z;
		}

		@Override
		public void run() {
			for (int x = 0; x < duration; x++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			worldObj.setBlock(posX, posY, posZ, oldId);
		}

	}

	@Override
	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		// TODO Auto-generated method stub
		return null;
	}
}
