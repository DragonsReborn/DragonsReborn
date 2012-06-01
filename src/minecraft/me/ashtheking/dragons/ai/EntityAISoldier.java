package me.ashtheking.dragons.ai;

import java.util.List;

import me.ashtheking.dragons.world.Hold;
import me.ashtheking.dragons.world.Location;
import net.minecraft.src.*;

public class EntityAISoldier extends EntityAIBase {

	EntityLiving groupMember;
	EntityPlayer player = ModLoader.getMinecraftInstance().thePlayer;
	List nearbyFriendlies;
	EntityLiving closestEntity;
	Class enemyType;
	Class enemyType2;
	State state;
	float moveSpeed;
	int timer = 0;

	public enum State {
		Idle, Fleeing, Melee, Ranged

	}

	public EntityAISoldier(EntityLiving entityliving, Class enemy, float f) {
		groupMember = entityliving;
		enemyType = enemy;
		timer = 0;
		moveSpeed = f;
		setMutexBits(3);
	}

	public EntityAISoldier(EntityLiving entityliving, Class enemy, Class enemy2, float f) {
		groupMember = entityliving;
		enemyType = enemy;
		enemyType2 = enemy2;
		timer = 0;
		moveSpeed = f;
		setMutexBits(3);
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
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
	public void startExecuting() {
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
	public void updateTask() {
		SentryDuty();

		switch (state) {

		case Fleeing:

			break;

		case Ranged:
			Ranged();
			break;

		case Melee:
			Melee();
			break;
		}

	}

	public void Melee() {
		if (groupMember.getAttackTarget() != null) {
			timer++;

			double d = groupMember.width * 2.0F * (groupMember.width * 2.0F);
			groupMember.getLookHelper().setLookPositionWithEntity(groupMember.getAttackTarget(),
					30F, 30F);
			groupMember.getNavigator().func_48667_a(groupMember.getAttackTarget(), moveSpeed);

			if (groupMember.getDistanceSq(groupMember.getAttackTarget().posX,
					groupMember.getAttackTarget().boundingBox.minY,
					groupMember.getAttackTarget().posZ) > d) {
				return;

			}
			if (timer < 20) {
				return;
			}

			else if (timer > 20) {
				timer = 0;
				groupMember.attackEntityAsMob(groupMember.getAttackTarget());
			}
		}
	}

	public void Ranged() {
		if (groupMember.getAttackTarget() != null) {
			groupMember.faceEntity(groupMember.getAttackTarget(), 10F,
					groupMember.getVerticalFaceSpeed());
			double maxRange = 100d;
			boolean flag = groupMember.func_48090_aM().canSee(groupMember.getAttackTarget());
			double distance = groupMember.getDistanceToEntity(groupMember.getAttackTarget());
			if (distance >= maxRange) {
				groupMember.getNavigator().func_48667_a(groupMember.getAttackTarget(), moveSpeed);

			}

			if (flag) {
				timer++;

				groupMember.getLookHelper().setLookPositionWithEntity(
						groupMember.getAttackTarget(), 30F, 30F);

				if (timer > 100) {
					timer = 0;
					doRangedAttack();
					if (groupMember.getAttackTarget().getAttackTarget() == null) {
						groupMember.getAttackTarget().setAttackTarget(groupMember);

					}

				}
			}
		}
	}

	public void SentryDuty() {
		if (groupMember.getAttackTarget() != null) {
			if (groupMember.getAttackTarget().getClass() == groupMember.getClass()) {
				groupMember.setAttackTarget(null);
			}
		}
		nearbyFriendlies = groupMember.worldObj.getEntitiesWithinAABBExcludingEntity(groupMember,
				groupMember.boundingBox.expand(32D, 16D, 32D));
		closestEntity = (EntityLiving) groupMember.worldObj.findNearestEntityWithinAABB(
				EntityLiving.class, groupMember.boundingBox.expand(16d, 3D, 16d), groupMember);
		for (int AllySize = 0; AllySize < nearbyFriendlies.size(); AllySize++) {

			if (closestEntity instanceof EntityLiving
					&& !groupMember.getNavigator().noPath()
					&& closestEntity != null
					&& groupMember.getAttackTarget() == null
					&& player != null
					&& (closestEntity.getClass() == enemyType || closestEntity.getClass() == enemyType2)) {

				player.addChatMessage("Stop right there, criminal scum!");
				groupMember.setAttackTarget(closestEntity);
			}

			if (groupMember.getAttackTarget() != null
					&& groupMember.getAttackTarget().getAttackTarget() == null) {
				groupMember.getAttackTarget().setAttackTarget(groupMember);
			}
			Entity friendly = (Entity) nearbyFriendlies.get(AllySize);

			{
				if (closestEntity instanceof EntityLiving
						&& !groupMember.getNavigator().noPath()
						&& closestEntity != null
						&& groupMember.getAttackTarget() == null
						&& player != null
						&& (closestEntity.getClass() == enemyType || closestEntity.getClass() == enemyType2)) {

					player.addChatMessage("Stop right there, criminal scum!");
					groupMember.setAttackTarget(closestEntity);

					if (friendly.getClass() == groupMember.getClass() && friendly != null
							&& groupMember.getAttackTarget() != null
							&& ((EntityLiving) friendly).getAttackTarget() == null) {

						EntityLiving friendly1 = (EntityLiving) friendly;
						friendly1.setAttackTarget(groupMember.getAttackTarget());

					}
					if (groupMember.getAttackTarget().getHealth() <= 0
							|| groupMember.getAttackTarget() == null) {
						groupMember.setAttackTarget(null);
						if (friendly.getClass() == groupMember.getClass() && friendly != null) {

							EntityLiving friendly1 = (EntityLiving) friendly;
							friendly1.setAttackTarget(null);

						}
					}
				}

			}
		}

		if (groupMember.getAttackTarget() != null
				&& groupMember.getDistanceToEntity(groupMember.getAttackTarget()) <= 20F) {
			setState(State.Melee);

		}

		if (groupMember.getAttackTarget() != null
				&& groupMember.getDistanceToEntity(groupMember.getAttackTarget()) > 20F) {
			setState(State.Ranged);
		}
	}

	private void doRangedAttack() {

		EntityArrow entityarrow = new EntityArrow(groupMember.worldObj, groupMember,
				groupMember.getAttackTarget(), 1.4f, 12f);
		entityarrow.shootingEntity = groupMember;
		groupMember.worldObj.playSoundAtEntity(groupMember, "random.bow", 1.0F, 1.0F / (groupMember
				.getRNG().nextFloat() * 0.4F + 0.8F));
		groupMember.worldObj.spawnEntityInWorld(entityarrow);

	}

	public Class getMemberClass() {
		return groupMember.getClass();
	}

}
