package me.ashtheking.dragons.ai;

import me.ashtheking.dragons.world.Hold;
import me.ashtheking.dragons.world.Location;
import net.minecraft.src.*;

public class EntityAIRandomEvent extends EntityAIBase {

	EntityLiving wanderer;
	double xTarget;
	double yTarget;
	double zTarget;
	float moveSpeed;
	public PathNavigate navigator;
	Hold hold;
	Location loc;
	Location holdLoc;

	double distanceToHoldCenter;

	public EntityAIRandomEvent(EntityLiving entityLiving, float speed,
			World world) {
		moveSpeed = speed;
		wanderer = entityLiving;
		navigator = new PathNavigate(wanderer, world, 32F);

	}


	public boolean continueExecuting() {
		return wanderer.getAttackTarget() == null;
	}

	public boolean shouldExecute() {

		return true;
	}

	public boolean isContinuous() {
		return true;
	}

	public void updateTask() 
	{

		
		loc = new Location((int) wanderer.posX, (int) wanderer.posY,
				(int) wanderer.posZ);

		hold = mod_Dragon.holdManager.getHold(loc);
		if (hold != null) {
			holdLoc = new Location((int) xTarget, (int) yTarget, (int) zTarget);
			xTarget = hold.center.x;
			yTarget = hold.center.y;
			zTarget = hold.center.z;
			
			double distance = loc.distanceTo(holdLoc);
			//System.out.print(" "+distance);
			try {
				ModLoader.setPrivateValue(PathNavigate.class,wanderer.getNavigator(), "pathSearchRange", 78);
						
			} catch (IllegalArgumentException e) {
			
				e.printStackTrace();
			} catch (SecurityException e) {
				
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
			
				e.printStackTrace();
			}

			
				if (distance > 40) 
					{
					wanderer.getNavigator().func_48666_a(xTarget, yTarget,zTarget, .5F);										
					}
				else if (wanderer.getAttackTarget() != null)
				{
					return;
				}

		}
	}

}
