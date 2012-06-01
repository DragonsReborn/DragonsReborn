package me.ashtheking.dragons.mob;

import me.ashtheking.dragons.ai.EntityAISoldier;
import me.ashtheking.dragons.ai.EntityAIRandomEvent;
import me.ashtheking.dragons.mob.helper.Soldier;
import net.minecraft.src.EntityAIAttackOnCollide;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAIMoveThroughVillage;
import net.minecraft.src.EntityAINearestAttackableTarget;
import net.minecraft.src.EntityAIOpenDoor;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityMob;
import net.minecraft.src.World;

public class Stormcloak extends Soldier {

	protected int attackStrength;
	public Stormcloak(World world) {
		super(world);
		attackStrength = 4;
		name = "Stormcloak";
		texture = "/mob/stormcloak.png";
		  moveSpeed = 0.3F;
		 
		  tasks.addTask(1, new EntityAIRandomEvent(this, moveSpeed, world));
			 tasks.addTask(1, new EntityAIOpenDoor(this, true));
			tasks.addTask(0, new EntityAISoldier(this, EntityMob.class, Imperial.class,moveSpeed));
		    
			 tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, true));
			targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
					me.ashtheking.dragons.mob.SabreTooth.class, 16F, 0, false));
			tasks.addTask(6, new EntityAIWander(this, .2F));
			tasks.addTask(1, new EntityAIAttackOnCollide(this, moveSpeed, true));
			 targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
	}
//	public boolean attackEntityFrom(DamageSource damagesource, int i)
//	{
//		Entity entity = damagesource.getEntity();
//		List list = worldObj.getEntitiesWithinAABB(Stormcloak.class, boundingBox.expand(32D, 32D, 32D));
//		if(entity instanceof EntityArrow)
//			if(((EntityArrow)entity).shootingEntity != null)
//				entity = ((EntityArrow)entity).shootingEntity;
//		if(entity instanceof Stormcloak)
//			return false;
//		if(entity != this)
//			for(int j = 0; j < list.size(); j++)
//			{
//				Entity entity1 = (Entity)list.get(j);
//				if(entity1 instanceof Stormcloak)
//				{
//					Stormcloak soldier = (Stormcloak)entity1;
//					soldier.setTarget(entity);
//				}
//			}
//		return super.attackEntityFrom(damagesource, i);
//	}
}
