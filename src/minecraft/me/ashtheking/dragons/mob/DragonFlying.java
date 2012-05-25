package me.ashtheking.dragons.mob;

import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class DragonFlying extends EntityMob {

	public float field_752_b;
	public float destPos;
	public float field_757_d;
	public float field_756_e;
	public float field_755_h;

	public DragonFlying(World world) {
		super(world);
		texture = "/mob/dragonf.png";
		setSize(16F, 8F);
	}

	public boolean getCanSpawnHere() {
		return worldObj.difficultySetting > 0
				&& worldObj.checkIfAABBIsClear(boundingBox)
				&& worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0
				&& !worldObj.isAnyLiquid(boundingBox);
	}

	public void onUpdate() {
		super.onUpdate();
		if (worldObj.difficultySetting == 0) {
			setDead();
		}
		field_756_e = field_752_b;
		field_757_d = destPos;
		destPos += (double) (onGround ? -1 : 4) * 0.29999999999999999D;
		if (destPos < 0.0F)
			destPos = 0.0F;
		if (destPos > 1.0F)
			destPos = 1.0F;
		if (!onGround && field_755_h < 1.0F)
			field_755_h = 1.0F;
		field_755_h *= 0.09D;
		field_752_b += field_755_h * 2.0F;
		// if(entityToAttack.posY > this.posY)
		// motionY += (entityToAttack.posY - this.posY) / 10;
		// if(entityToAttack.posY < this.posY)
		// motionY -= (entityToAttack.posY - this.posY) / 10;
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
	}

	@Override
	public int getMaxHealth() {
		return 100;
	}

	protected void attackEntity(Entity entity, float f) {
		if (f < 2.0F)
			entity.attackEntityFrom(
					DamageSource.causeThrownDamage(entity, this), 5);
	}

	protected void attackBlockedEntity(Entity entity, float f) {
		if (f < 2.0F)
			entity.attackEntityFrom(
					DamageSource.causeThrownDamage(entity, this), 3);
	}

	protected Entity findPlayerToAttack() {
		float f = 32F;
		List list = worldObj.getEntitiesWithinAABB(EntityLiving.class,
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
	
    protected String getLivingSound()
    {
        return "dragonroar";
    }

    protected String getHurtSound()
    {
        return "dragonroar";
    }

    protected String getDeathSound()
    {
        return "dragonroar";
    }
    
    protected void fall(float f)
    {
    }

    public void moveEntityWithHeading(float f, float f1)
    {
        if (isInWater())
        {
            moveFlying(f, f1, 0.02F);
            moveEntity(motionX, motionY, motionZ);
            motionX *= 0.80000001192092896D;
            motionY *= 0.80000001192092896D;
            motionZ *= 0.80000001192092896D;
        }
        else if (handleLavaMovement())
        {
            moveFlying(f, f1, 0.02F);
            moveEntity(motionX, motionY, motionZ);
            motionX *= 0.5D;
            motionY *= 0.5D;
            motionZ *= 0.5D;
        }
        else
        {
            float f2 = 0.91F;
            if (onGround)
            {
                f2 = 0.5460001F;
                int i = worldObj.getBlockId(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ));
                if (i > 0)
                {
                    f2 = Block.blocksList[i].slipperiness * 0.91F;
                }
            }
            float f3 = 0.1627714F / (f2 * f2 * f2);
            moveFlying(f, f1, onGround ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;
            if (onGround)
            {
                f2 = 0.5460001F;
                int j = worldObj.getBlockId(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ));
                if (j > 0)
                {
                    f2 = Block.blocksList[j].slipperiness * 0.91F;
                }
            }
            moveEntity(motionX, motionY, motionZ);
            motionX *= f2;
            motionY *= f2;
            motionZ *= f2;
        }
        field_705_Q = field_704_R;
        double d = posX - prevPosX;
        double d1 = posZ - prevPosZ;
        float f4 = MathHelper.sqrt_double(d * d + d1 * d1) * 4F;
        if (f4 > 1.0F)
        {
            f4 = 1.0F;
        }
        field_704_R += (f4 - field_704_R) * 0.4F;
        field_703_S += field_704_R;
    }

    public boolean isOnLadder()
    {
        return false;
    }
}
