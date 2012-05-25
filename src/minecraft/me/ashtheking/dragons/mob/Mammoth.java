package me.ashtheking.dragons.mob;

import me.ashtheking.dragons.magic.Shouts;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIAttackOnCollide;
import net.minecraft.src.EntityAIAvoidEntity;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAINearestAttackableTarget;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.Potion;
import net.minecraft.src.World;

public class Mammoth extends EntityAnimal {

	public Mammoth(World world) {
		super(world);
		texture = "/mob/mammoth.png";
        yOffset *= 3F;
        setSize(width * 3F, 6F);
        getNavigator().func_48664_a(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAvoidEntity(this, net.minecraft.src.EntityCreeper.class, 16F,
				0.23F, 0.4F));
		tasks.addTask(6, new EntityAIWander(this, .2F));
		tasks.addTask(6, new EntityAIWatchClosest(this, net.minecraft.src.EntityLiving.class, 10F));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, .3F, true));


		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityMob.class, 8F, 0, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityPlayer.class, 8F, 0, true));
	
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}
	
	@Override
	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
//		return new Mammoth(worldObj);
		return null;
	}

	@Override
	public int getMaxHealth() {
		return 80;
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}
	
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        if (super.attackEntityFrom(damagesource, i))
        {
            Entity entity = damagesource.getEntity();
            if (riddenByEntity == entity || ridingEntity == entity)
                return true;
            if (entity != this)
            {
                entityToAttack = entity;
            }
            return true;
        }
        else
            return false;
    }
    
    public boolean attackEntityAsMob(Entity entity)
    {
        int i = 13;
        if (isPotionActive(Potion.damageBoost))
            i += 3 << getActivePotionEffect(Potion.damageBoost).getAmplifier();
        if (isPotionActive(Potion.weakness))
            i -= 2 << getActivePotionEffect(Potion.weakness).getAmplifier();
        Shouts.knockBack(entity, 2, -(entity.posX - this.posX), -(entity.posZ - this.posZ));
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    protected void attackEntity(Entity entity, float f)
    {
        if (attackTime <= 0 && f < 3.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            attackEntityAsMob(entity);
            Shouts.knockBack(entity, 2, -(entity.posX - this.posX), -(entity.posZ - this.posZ));
        }
    	
    }
}

