package me.ashtheking.dragons.mob;

import java.util.List;

import me.ashtheking.dragons.ai.EntityAIHuntingParty;
import me.ashtheking.dragons.ai.EntityAIRandomEvent;
import me.ashtheking.dragons.mob.helper.Soldier;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIAttackOnCollide;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAIMoveThroughVillage;
import net.minecraft.src.EntityAINearestAttackableTarget;
import net.minecraft.src.EntityAIOpenDoor;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityArrow;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPigZombie;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Potion;
import net.minecraft.src.World;

public class Imperial extends Soldier {
	protected int attackStrength;
	public Imperial(World world) {
		super(world);
		attackStrength = 4;
		name = "Imperial";
		  moveSpeed = 0.3F;
		texture = "/mob/imperial.png";
		tasks.addTask(1, new EntityAIRandomEvent(this, moveSpeed, world));
		 tasks.addTask(1, new EntityAIOpenDoor(this, true));
		tasks.addTask(0, new EntityAIHuntingParty(this, EntityMob.class, Stormcloak.class));
	    
		 tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				me.ashtheking.dragons.mob.SabreTooth.class, 16F, 0, false));
		tasks.addTask(6, new EntityAIWander(this, .2F));
		tasks.addTask(1, new EntityAIAttackOnCollide(this, moveSpeed, true));
		 targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
			
	}
	public Imperial(World par1World, EntityLiving par2EntityLiving, float par3) 
	{
		super(par1World);
		attackStrength = 4;
		  moveSpeed = 0.3F;
		name = "Imperial";
		texture = "/mob/imperial.png";
		 tasks.addTask(1, new EntityAIOpenDoor(this, true));
		tasks.addTask(1, new EntityAIRandomEvent(this, moveSpeed, par1World));
		tasks.addTask(0, new EntityAIHuntingParty(this, EntityMob.class, Stormcloak.class));
		 tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				me.ashtheking.dragons.mob.SabreTooth.class, 16F, 0, false));
	
		tasks.addTask(6, new EntityAIWander(this, .2F));
		setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY+ (double) par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ,	par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);  
		tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityMob.class, moveSpeed, true));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
	}
	 
	 public boolean isAIEnabled() 
		{
			return true;
		}
		
	    
//	public boolean attackEntityFrom(DamageSource damagesource, int i)
//	{
//		Entity entity = damagesource.getEntity();
//		List list = worldObj.getEntitiesWithinAABB(Imperial.class, boundingBox.expand(32D, 32D, 32D));
//		if(entity instanceof EntityArrow)
//			if(((EntityArrow)entity).shootingEntity != null)
//				entity = ((EntityArrow)entity).shootingEntity;
//		if(entity instanceof Imperial)
//			return false;
//		if(entity != this)
//			for(int j = 0; j < list.size(); j++)
//			{
//				Entity entity1 = (Entity)list.get(j);
//				if(entity1 instanceof Imperial)
//				{
//					Imperial soldier = (Imperial)entity1;
//					if(entity != this)
//						soldier.setTarget(entity);
//				}
//			}
//		return super.attackEntityFrom(damagesource, i);
//	}
}
