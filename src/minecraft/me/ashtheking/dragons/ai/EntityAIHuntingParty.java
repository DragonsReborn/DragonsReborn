package me.ashtheking.dragons.ai;

import java.util.List;



import me.ashtheking.dragons.world.Hold;
import me.ashtheking.dragons.world.Location;
import net.minecraft.src.*;


public class EntityAIHuntingParty extends EntityAIBase {

	EntityLiving groupMember;
	EntityPlayer player = ModLoader.getMinecraftInstance().thePlayer;
	List nearbyFriendlies;
	EntityLiving closestEntity;
	Class enemyType;
	Class enemyType2;
	State state;
	double xTarget;
	double yTarget;
	double zTarget;
	Hold hold;
	Location loc;
	Location holdLoc;
	public enum State
	{
	Idle,
	Fleeing,
	
		
	}

	
	public EntityAIHuntingParty(EntityLiving entityliving, Class enemy) {
		groupMember = entityliving;
		enemyType = enemy;
	}
	public EntityAIHuntingParty(EntityLiving entityliving, Class enemy, Class enemy2) {
		groupMember = entityliving;
		enemyType = enemy;
		enemyType2 = enemy2;
	}
	
	

	public void setState(State state)
	{
		this.state = state;
	}
	public State getState()
	{
		return state;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
		return shouldExecute();
	}

	/**
	 * Returns whether the task requires multiple updates or not
	 */
	public boolean isContinuous() {
		return true;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() 
	{
		state = State.Idle;
	}

	/**
	 * Resets the task
	 */
	public void resetTask() {

	}

	/**
	 * Updates the task
	 */
	public void updateTask() 
	{
		
		
		switch(state)
		{
			case Idle:
			SentryDuty();
				break;
			
				
			case Fleeing:
				// attack();
				break;
				
		}
		
	}

	
	
	public void SentryDuty()
	{
		nearbyFriendlies = groupMember.worldObj.getEntitiesWithinAABBExcludingEntity(groupMember,groupMember.boundingBox.expand(32D, 16D, 32D));
		closestEntity = (EntityLiving)groupMember.worldObj.findNearestEntityWithinAABB(EntityLiving.class, groupMember.boundingBox.expand(16d, 3D, 16d), groupMember);
		for(int AllySize = 0; AllySize < nearbyFriendlies.size(); AllySize++)
		{
		
			if(groupMember.getAttackTarget() != null)
			{
			EntityLiving target = groupMember.getAttackTarget();
			
			if(target != null)
			{
			loc = new Location((int) target.posX, (int) target.posY,
					(int) target.posZ);
			}                              
			}
			if(groupMember.getAttackTarget() != null && groupMember.getAttackTarget().getAttackTarget() == null)
			{
				groupMember.getAttackTarget().setAttackTarget(groupMember);
			}
			Entity friendly = (Entity)nearbyFriendlies.get(AllySize);		
		//Begin territorial part
	
		if(mod_Dragon.holdManager != null && loc != null)
		{
		hold = mod_Dragon.holdManager.getHold(loc);
		}
		if (hold != null) {
			holdLoc = new Location((int) xTarget, (int) yTarget, (int) zTarget);
			xTarget = hold.center.x;
			yTarget = hold.center.y;
			zTarget = hold.center.z;
			//end
			if(loc.distanceTo(holdLoc) < 60)
			{
				if(closestEntity instanceof EntityLiving && !groupMember.getNavigator().noPath() && closestEntity != null && loc.distanceTo(holdLoc) < 60 && groupMember.getAttackTarget() == null && player != null && (closestEntity.getClass() == enemyType || closestEntity.getClass() == enemyType2))
				{		
					
					player.addChatMessage("Stop right there, criminal scum!");
					groupMember.setAttackTarget(closestEntity);
				
					if( friendly.getClass() == groupMember.getClass() && friendly != null  && groupMember.getAttackTarget() != null && ((EntityLiving) friendly).getAttackTarget() == null)
					{
						
						EntityLiving friendly1 = (EntityLiving)friendly;	
						friendly1.setAttackTarget(groupMember.getAttackTarget());
					
					}
					if(groupMember.getAttackTarget().getHealth() <= 0 || groupMember.getAttackTarget() == null)
					{
						groupMember.setAttackTarget(null);
					if(friendly.getClass() == groupMember.getClass() && friendly != null )
						{

						EntityLiving friendly1 = (EntityLiving)friendly;	
						friendly1.setAttackTarget(null);
					
						}
					}
				}
					
						}
					}
				}
			}
	
	
	public Class getMemberClass() {
		return groupMember.getClass();
	}

}
