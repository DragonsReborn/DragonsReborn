package me.ashtheking.dragons.mob;

import java.util.ArrayList;
import java.util.List;

import me.ashtheking.dragons.ids.Weapons;
import me.ashtheking.dragons.meta.Fireball;
import me.ashtheking.dragons.mob.helper.AshDragonPart;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityXPOrb;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.StepSound;
import net.minecraft.src.Vec3D;
import net.minecraft.src.World;
import net.minecraft.src.mod_Dragon;

public class Dragon extends EntityLiving {

	
	public double targetX;
	public double targetY;
	public double targetZ;
	public double modelArray[][];
	public int arrayIndex;
	public AshDragonPart dragonPartList[];
	public AshDragonPart dragonHead;
	public AshDragonPart dragonBody;
	public AshDragonPart dragonTail1;
	public AshDragonPart dragonTail2;
	public AshDragonPart dragonTail3;
	public AshDragonPart dragonWing1;
	public AshDragonPart dragonWing2;
	public float newVar;
	public float oldVar;
	public boolean collide;
	public boolean needsToReset;
	public boolean differentMotion;
	public Entity prey;
	public int deathCount;
	public int attackCounter = 0;


	public Dragon(World world) {
		super(world);
		modelArray = new double[64][3];
		arrayIndex = -1;
		newVar = 0.0F;
		oldVar = 0.0F;
		needsToReset = false;
		differentMotion = false;
		deathCount = 0;
		dragonPartList = (new AshDragonPart[] {
				dragonHead = new AshDragonPart(this, "head", 4F, 4F),
				dragonBody = new AshDragonPart(this, "body", 8F, 8F),
				dragonTail1 = new AshDragonPart(this, "tail", 4F, 4F),
				dragonTail2 = new AshDragonPart(this, "tail", 4F, 4F),
				dragonTail3 = new AshDragonPart(this, "tail", 4F, 4F),
				dragonWing1 = new AshDragonPart(this, "wing", 4F, 4F),
				dragonWing2 = new AshDragonPart(this, "wing", 4F, 4F) });
		setEntityHealth(getMaxHealth());
		texture = "/mob/dragon.png";
		setSize(16F, 8F);
		noClip = false;
		isImmuneToFire = true;
		targetY = 60D;
		ignoreFrustumCheck = false;
	
		checkMaxDragons();
		//setPosition(posX, 128, posZ);
		maxHurtTime = 20;
	}

	protected void entityInit() {
		super.entityInit();
	}

	public boolean getCanSpawnHere() {
		return worldObj.checkIfAABBIsClear(boundingBox)
				&& worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0
				&& !worldObj.isAnyLiquid(boundingBox);
	}

	public void checkMaxDragons() 
	{
		List entityList = new ArrayList();
		List list = null;
		try {
			list = (List) ModLoader.getPrivateValue(World.class, worldObj,
					"unloadedEntityList");
		} catch (Exception e) {
			list = new ArrayList();
		}
		for (Object e : worldObj.loadedEntityList)
			entityList.add(e);
		for (Object e : list)
			entityList.add(e);
		List totalDragons = new ArrayList();
		for (Object e : entityList)
			if (e instanceof Dragon)
				if (!((Dragon) e).isDead)
					totalDragons.add(e);
		if (totalDragons.size() > 10)
			setDead();
	}

	// Some calculation
	public double[] calculateSomething(int i, float f) {
		
		if (health <= 0) {
			f = 0.0F;
		}
		f = 1.0F - f;
		int j = arrayIndex - i * 1 & 0x3f;
		int k = arrayIndex - i * 1 - 1 & 0x3f;
		double ad[] = new double[3];
		double d = modelArray[j][0];
		double d1;
		for (d1 = modelArray[k][0] - d; d1 < -180D; d1 += 360D) {
		}
		for (; d1 >= 180D; d1 -= 360D) {
		}
		ad[0] = d + d1 * (double) f;
		d = modelArray[j][1];
		d1 = modelArray[k][1] - d;
		ad[1] = d + d1 * (double) f;
		ad[2] = modelArray[j][2] + (modelArray[k][2] - modelArray[j][2])
				* (double) f;
		return ad;
	}

	public void onLivingUpdate() 
	{
		newVar = oldVar;
		/*
		if (health <= 0) {
			float f = (rand.nextFloat() - 0.5F) * 8F;
			float f2 = (rand.nextFloat() - 0.5F) * 4F;
			float f4 = (rand.nextFloat() - 0.5F) * 8F;
			worldObj.spawnParticle("largeexplode", posX + (double) f, posY + 2D
					+ (double) f2, posZ + (double) f4, 0.0D, 0.0D, 0.0D);
			return;
		}
		*/
		//checkMaxDragons();
	/*
		if (prey == null)
		{
			if (posY < 60)
			{
				motionY = (60 - posY);
			}
		}
		if (prey != null)
		{
			if (posY < prey.posY)
			{
				motionY = (prey.posY - posY);
			}
		}
		*/
			float f1 = 0.2F / (MathHelper.sqrt_double(motionX * motionX + motionZ
				* motionZ) * 10F + 1.0F);
		f1 *= (float) Math.pow(2D, motionY);
		if (differentMotion) {
			oldVar += f1 * 0.5F;
		} else {
			oldVar += f1;
		}
		for (; rotationYaw >= 180F; rotationYaw -= 360F) {
		}
		for (; rotationYaw < -180F; rotationYaw += 360F) {
		}
		if (arrayIndex < 0) {
			for (int i = 0; i < modelArray.length; i++) {
				modelArray[i][0] = rotationYaw;
				modelArray[i][1] = posY;
			}
		}
		if (++arrayIndex == modelArray.length) {
			arrayIndex = 0;
		}
		modelArray[arrayIndex][0] = rotationYaw;
		modelArray[arrayIndex][1] = posY;
		double d1 = targetX - posX;
		double d3 = targetY - posY;
		double d5 = targetZ - posZ;
		double d7 = d1 * d1 + d3 * d3 + d5 * d5;
		if (prey != null) {
			targetX = prey.posX;
			targetZ = prey.posZ;
			double d8 = targetX - posX;
			double d10 = targetZ - posZ;
			double d12 = Math.sqrt(d8 * d8 + d10 * d10);
			double d13 = (0.40000000596046448D + d12 / 80D) - 1.0D;
			if (d13 > 10D) {
				d13 = 10D;
			}
			targetY = prey.boundingBox.minY + .15;
	
			
			
		} else {
			targetX += rand.nextGaussian() * 2D;
			targetZ += rand.nextGaussian() * 2D;
		}
		if (needsToReset || d7 > 22500D
				|| prey == null) {
			resetTarget();
		}
		double d4 = 100D;
		if (prey != null && prey.getDistanceSqToEntity(this) < d4 * d4) {
			shootFireball(prey);
		}
		d3 /= MathHelper.sqrt_double(d1 * d1 + d5 * d5);
		float f10 = 0.6F;
		if (d3 < (double) (-f10)) {
			d3 = -f10;
		}
		if (d3 > (double) f10) {
			d3 = f10;
		}
		motionY += d3 * 0.10000000149011612D;
		for (; rotationYaw < -180F; rotationYaw += 360F) {
		}
		for (; rotationYaw >= 180F; rotationYaw -= 360F) {
		}
		double d9 = 180D - (Math.atan2(d1, d5) * 180D) / 3.1415927410125732D;
		double d11;
		for (d11 = d9 - (double) rotationYaw; d11 < -180D; d11 += 360D) {
		}
		for (; d11 >= 180D; d11 -= 360D) {
		}
		if (d11 > 50D) {
			d11 = 50D;
		}
		if (d11 < -50D) {
			d11 = -50D;
		}
		Vec3D vec3d = Vec3D.createVector(targetX - posX, targetY - posY,
				targetZ - posZ).normalize();
		Vec3D vec3d1 = Vec3D.createVector(
				MathHelper.sin((rotationYaw * 3.141593F) / 180F), motionY,
				-MathHelper.cos((rotationYaw * 3.141593F) / 180F))
				.normalize();
		float f18 = (float) (vec3d1.dotProduct(vec3d) + 0.5D) / 1.5F;
		if (f18 < 0.0F) {
			f18 = 0.0F;
		}
		randomYawVelocity *= 0.8F;
		float f19 = MathHelper.sqrt_double(motionX * motionX + motionZ
				* motionZ) * 1.0F + 1.0F;
		double d14 = Math.sqrt(motionX * motionX + motionZ * motionZ) * 1.0D + 1.0D;
		if (d14 > 40D) {
			d14 = 40D;
		}
		randomYawVelocity += d11
				* (0.69999998807907104D / d14 / (double) f19);
		rotationYaw += randomYawVelocity * 0.1F;
		float f20 = (float) (2D / (d14 + 1.0D));
		float f21 = 0.06F;
		moveFlying(0.0F, -1F, f21 * (f18 * f20 + (1.0F - f20)));
		if (differentMotion) {
			moveEntity(motionX * 0.80000001192092896D,
					motionY * 0.80000001192092896D,
					motionZ * 0.80000001192092896D);
		} else {
			moveEntity(motionX, motionY, motionZ);
		}
		Vec3D vec3d2 = Vec3D.createVector(motionX, motionY, motionZ)
				.normalize();
		float f22 = (float) (vec3d2.dotProduct(vec3d1) + 1.0D) / 2.0F;
		f22 = 0.8F + 0.15F * f22;
		motionX *= f22;
		motionZ *= f22;
		motionY *= 0.9100000262260437D;
		renderYawOffset = rotationYaw;
		dragonHead.width = dragonHead.height = 3F;
		dragonTail1.width = dragonTail1.height = 2.0F;
		dragonTail2.width = dragonTail2.height = 2.0F;
		dragonTail3.width = dragonTail3.height = 2.0F;
		dragonBody.height = 3F;
		dragonBody.width = 5F;
		dragonWing1.height = 2.0F;
		dragonWing1.width = 4F;
		dragonWing2.height = 3F;
		dragonWing2.width = 4F;
		float f3 = (((float) (calculateSomething(5, 1.0F)[1] - calculateSomething(
				10, 1.0F)[1]) * 10F) / 180F) * 3.141593F;
		float f5 = MathHelper.cos(f3);
		float f6 = -MathHelper.sin(f3);
		float f7 = (rotationYaw * 3.141593F) / 180F;
		float f8 = MathHelper.sin(f7);
		float f9 = MathHelper.cos(f7);
		dragonBody.onUpdate();
		dragonBody.setLocationAndAngles(posX + (double) (f8 * 0.5F), posY, posZ
				- (double) (f9 * 0.5F), 0.0F, 0.0F);
		dragonWing1.onUpdate();
		dragonWing1.setLocationAndAngles(posX + (double) (f9 * 4.5F),
				posY + 2D, posZ + (double) (f8 * 4.5F), 0.0F, 0.0F);
		dragonWing2.onUpdate();
		dragonWing2.setLocationAndAngles(posX - (double) (f9 * 4.5F),
				posY + 2D, posZ - (double) (f8 * 4.5F), 0.0F, 0.0F);
			func_41007_az();
if(maxHurtTime == 0) {
			// func_41008_a(worldObj.getEntitiesWithinAABBExcludingEntity(this,
			// dragonWing1.boundingBox.expand(4D, 2D, 4D).offset(0.0D, -2D,
			// 0.0D)));
			// func_41008_a(worldObj.getEntitiesWithinAABBExcludingEntity(this,
			// dragonWing2.boundingBox.expand(4D, 2D, 4D).offset(0.0D, -2D,
			// 0.0D)));
			collideAttack(worldObj.getEntitiesWithinAABBExcludingEntity(this,
					dragonHead.boundingBox.expand(1.0D, 1.0D, 1.0D)));
		}
		double ad[] = calculateSomething(5, 1.0F);
		double ad1[] = calculateSomething(0, 1.0F);
		float f11 = MathHelper.sin((rotationYaw * 3.141593F) / 180F
				- randomYawVelocity * 0.01F);
		float f12 = MathHelper.cos((rotationYaw * 3.141593F) / 180F
				- randomYawVelocity * 0.01F);
		dragonHead.onUpdate();
		dragonHead.setLocationAndAngles(posX + (double) (f11 * 5.5F * f5), posY
				+ (ad1[1] - ad[1]) * 1.0D + (double) (f6 * 5.5F), posZ
				- (double) (f12 * 5.5F * f5), 0.0F, 0.0F);
		for (int j = 0; j < 3; j++) {
			AshDragonPart AshDragonPart = null;
			if (j == 0) {
				AshDragonPart = dragonTail1;
			}
			if (j == 1) {
				AshDragonPart = dragonTail2;
			}
			if (j == 2) {
				AshDragonPart = dragonTail3;
			}
			double ad2[] = calculateSomething(12 + j * 2, 1.0F);
			float f13 = (rotationYaw * 3.141593F) / 180F
					+ ((func_40159_b(ad2[0] - ad[0]) * 3.141593F) / 180F)
					* 1.0F;
			float f14 = MathHelper.sin(f13);
			float f15 = MathHelper.cos(f13);
			float f16 = 1.5F;
			float f17 = (float) (j + 1) * 2.0F;
			AshDragonPart.onUpdate();
			AshDragonPart
					.setLocationAndAngles(
							posX - (double) ((f8 * f16 + f14 * f17) * f5),
							((posY + (ad2[1] - ad[1]) * 1.0D) - (double) ((f17 + f16) * f6)) + 1.5D,
							posZ + (double) ((f9 * f16 + f15 * f17) * f5),
							0.0F, 0.0F);
		}

			differentMotion = false;
	}

	// Unknown method
	private void func_41007_az() {
		if (ticksExisted % 20 == 0) {
			Vec3D vec3d = getLook(1.0F);
			double d = 0.0D;
			double d1 = -1D;
			double d2 = 0.0D;
		}
	}

	// //Knockback method
	// private void func_41008_a(List list)
	// {
	// double d = (dragonBody.boundingBox.minX + dragonBody.boundingBox.maxX) /
	// 2D;
	// double d1 = (dragonBody.boundingBox.minZ + dragonBody.boundingBox.maxZ) /
	// 2D;
	// Iterator iterator = list.iterator();
	// do
	// {
	// if(!iterator.hasNext())
	// {
	// break;
	// }
	// Entity entity = (Entity)iterator.next();
	// if(entity instanceof EntityLiving)
	// {
	// double d2 = entity.posX - d;
	// double d3 = entity.posZ - d1;
	// double d4 = d2 * d2 + d3 * d3;
	// entity.addVelocity((d2 / d4) * 4D, 0.20000000298023224D, (d3 / d4) * 4D);
	// }
	// } while(true);
	// }
	// contact hurt method
	private void collideAttack(List list) {
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			boolean b = false;
			for (Entity e : dragonPartList)
				if (entity == e)
					b = true;
			if (entity instanceof EntityLiving && !b && entity != this) {
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), 10);
			}
		}

	}

	public Entity getNewTarget() {
		float f = 64F;
		List list = worldObj.getEntitiesWithinAABB(EntityPlayer.class,
				boundingBox.expand(f, f, f));
		for (int x = 0; x < list.size(); x++)
			if (list.get(x) instanceof Dragon)
				list.remove(x);
		if (list.size() > 0) {
			Entity e = (Entity) list.get(rand.nextInt(list.size()));
			while (!canEntityBeSeen(e))
				e = (Entity) list.get(rand.nextInt(list.size()));
			return e;
		} else
			return null;
	}
	@Override
	  protected void fall(float par1)
    {
         
   
    }

	public void shootFireball(Entity targetedEntity) {
		if (canEntityBeSeen(targetedEntity)) {
			attackCounter++;
		
			if (attackCounter >= 0) {
				
				targetY = prey.boundingBox.minY + 25;
				if(posY - targetedEntity.posY >= 20)
				{
				double d5 = targetedEntity.posX - dragonHead.posX;
				double d6 = (targetedEntity.boundingBox.minY + (double) (targetedEntity.height / 2.0F))
						- (dragonHead.posY + (double) (dragonHead.height / 2.0F) - 10);
				double d7 = targetedEntity.posZ - dragonHead.posZ;
				Fireball entityfireball = new Fireball(worldObj, this, d5, d6,
						d7);
				double d8 = 2D;
				Vec3D vec3d = getLook(1.0F);
				entityfireball.posX = dragonHead.posX + vec3d.xCoord * d8;
				entityfireball.posY = dragonHead.posY - 2;
					//	+ (double) (dragonHead.height / 2.0F) + 0.5D;
				entityfireball.posZ = dragonHead.posZ + vec3d.zCoord * d8;
				for (int x = 0; x < 3; x++)
					entityfireball.onUpdate();
				
				entityfireball.motionX *= 2;
				entityfireball.motionY *= 2;
				entityfireball.motionZ *= 2;
			
				worldObj.spawnEntityInWorld(entityfireball);
				attackCounter = -20 - rand.nextInt(50);
				}
				}
		} else if (attackCounter < 0) {
			attackCounter--;
			targetY = prey.boundingBox.minY + .8;
		}
	}

	// get prey method
	private void resetTarget() {
		needsToReset = false;
		if (prey == null
				|| (prey != null && (prey.isDead || getDistanceSqToEntity(prey) >= 81D)))
			prey = getNewTarget();
		// else
		// {
		// boolean flag = false;
		// do
		// {
		// targetX = 0.0D;
		// targetY = 70F + rand.nextFloat() * 50F;
		// targetZ = 0.0D;
		// targetX += rand.nextFloat() * 120F - 60F;
		// targetZ += rand.nextFloat() * 120F - 60F;
		// double d = posX - targetX;
		// double d1 = posY - targetY;
		// double d2 = posZ - targetZ;
		// flag = d * d + d1 * d1 + d2 * d2 > 100D;
		// } while(!flag);
		// prey = null;
		// }
	}

	// unknown method
	private float func_40159_b(double d) {
		for (; d >= 180D; d -= 360D) {
		}
		for (; d < -180D; d += 360D) {
		}
		return (float) d;
	}

	// AshDragonPart attacked method
	public boolean dragonPartHurt(AshDragonPart AshDragonPart,DamageSource damagesource, int i) 
	{
		
		if (AshDragonPart != dragonHead) {
			i = i / 4 + 1;
		}
		float f = (rotationYaw * 3.141593F) / 180F;
		float f1 = MathHelper.sin(f);
		float f2 = MathHelper.cos(f);
		targetX = posX + (double) (f1 * 5F)
				+ (double) ((rand.nextFloat() - 0.5F) * 2.0F);
		targetY = posY + (double) (rand.nextFloat() * 3F) + 1.0D;
		targetZ = (posZ - (double) (f2 * 5F))
				+ (double) ((rand.nextFloat() - 0.5F) * 2.0F);
		// prey = null;
		boolean b = false;
		for (Entity e : dragonPartList)
			if (damagesource.getSourceOfDamage() == e)
				b = true;
		if (damagesource == DamageSource.explosion
				|| damagesource.getSourceOfDamage() != this || !b)
			return false;
		if(damagesource == DamageSource.inWall)
		{
			return false;
		}
		if ((damagesource.getSourceOfDamage() instanceof EntityPlayer)
				|| damagesource == DamageSource.explosion) {
			if (damagesource.getSourceOfDamage() instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) damagesource
						.getSourceOfDamage();
				if (p.inventory.getCurrentItem() != null)
					if (p.inventory.getCurrentItem().itemID == Weapons.swords
							.get(0).shiftedIndex)
						i = Weapons.swords.get(0).getDamageVsEntity(this);
			}
		}
		if (damagesource.getSourceOfDamage() != null)
			prey = damagesource.getSourceOfDamage();
		return super.attackEntityFrom(damagesource, i);
	}

	// death exp drop method
	protected void onDeathUpdate() {
		deathCount++;
		if (deathCount >= 180 && deathCount <= 200) {
			float f = (rand.nextFloat() - 0.5F) * 8F;
			float f1 = (rand.nextFloat() - 0.5F) * 4F;
			float f2 = (rand.nextFloat() - 0.5F) * 8F;
			worldObj.spawnParticle("hugeexplosion", posX + (double) f, posY
					+ 2D + (double) f1, posZ + (double) f2, 0.0D, 0.0D, 0.0D);
		}
		if (deathCount > 150
				&& deathCount % 5 == 0)
			for (int i = 30; i > 0;) {
				int k = EntityXPOrb.getXPSplit(i);
				i -= k;
				worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, posX,
						posY, posZ, k));
			}
		moveEntity(0.0D, -0.3D, 0.0D);
		renderYawOffset = rotationYaw += 20F;
		if (deathCount == 200) {
			for (int j = 30; j > 0;) {
				int l = EntityXPOrb.getXPSplit(j);
				j -= l;
				worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, posX,
						posY, posZ, l));
			}
			// if(rand.nextInt(10) < 2) {
			// int x = rand.nextInt(ItemShout.shoutNames.length);
			// worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY,
			// posZ, new ItemStack(mod_Dragon.shoutId, 1, x)));
			// }
			worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY,
					posZ, new ItemStack(mod_Dragon.scaleId,
							rand.nextInt(4) + 2, 0)));
			onEntityDeath();
			setDead();
		}
	}
	


	// returning dragon parts
	public Entity[] getParts() {
		return dragonPartList;
	}

	@Override
	public int getMaxHealth() {
		return 150;
	}

	protected String getLivingSound() {
		return "dragonroar";
	}

	protected String getHurtSound() {
		return "dragonroar";
	}

	protected String getDeathSound() {
		return "dragonroar";
	}
}
