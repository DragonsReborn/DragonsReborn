package me.ashtheking.dragons.mob;

import net.minecraft.src.EntityAIAttackOnCollide;
import net.minecraft.src.EntityAIFollowOwner;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAINearestAttackableTarget;
import net.minecraft.src.EntityAIOwnerHurtByTarget;
import net.minecraft.src.EntityAIOwnerHurtTarget;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.World;

public class Follower extends EntityTameable {

	
	
	public Follower(World par1World) 
	{
		super(par1World);
        getNavigator().func_48664_a(true);

		tasks.addTask(1, new EntityAISwimming(this));
			tasks.addTask(6, new EntityAIWander(this, moveSpeed));
			tasks.addTask(6, new EntityAIWatchClosest(this, net.minecraft.src.EntityPlayer.class, 8F));
			tasks.addTask(6, new EntityAILookIdle(this));
		    tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
	        tasks.addTask(5, new EntityAIFollowOwner(this, moveSpeed, 10F, 2.0F));
			targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
			targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
					me.ashtheking.dragons.mob.Dragon.class, 64F, 0, false));
			targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
					net.minecraft.src.EntityMob.class, 12F, 0, false));
			targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
	        targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
	        targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
	}
	public Follower(World par1World, EntityLiving par2EntityLiving, float par3) 
	{
		super(par1World);
		setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY+ (double) par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ,	par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);  
        getNavigator().func_48664_a(true);

		tasks.addTask(1, new EntityAISwimming(this));
			tasks.addTask(6, new EntityAIWander(this, moveSpeed));
			tasks.addTask(6, new EntityAIWatchClosest(this, net.minecraft.src.EntityPlayer.class, 8F));
			tasks.addTask(6, new EntityAILookIdle(this));
		    tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
	        tasks.addTask(5, new EntityAIFollowOwner(this, moveSpeed, 10F, 2.0F));
			targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
			targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
					me.ashtheking.dragons.mob.Dragon.class, 64F, 0, false));
			targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
					net.minecraft.src.EntityMob.class, 12F, 0, false));
			targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
	        targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
	        targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
	}
	
  
	@Override
	public int getMaxHealth() 
	{
		return 100;
	}
   
	@Override
	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		return null;
	}
	public boolean interact(EntityPlayer par1EntityPlayer)
    {
		if(!this.isTamed())
		{
			setTamed(true);
           
            setAttackTarget(null);
            aiSit.func_48407_a(true);
            setEntityHealth(20);
            setOwner(par1EntityPlayer.username);
            func_48142_a(true);
            worldObj.setEntityState(this, (byte)7);
		}
		boolean emptyHands = par1EntityPlayer.getCurrentEquippedItem() == null;
        return super.interact(par1EntityPlayer);
		
    }
}
