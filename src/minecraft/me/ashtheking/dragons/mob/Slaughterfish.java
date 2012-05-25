package me.ashtheking.dragons.mob;

import java.util.List;

import me.ashtheking.dragons.*;
import me.ashtheking.dragons.ai.EntityAIRandomEvent;
import net.minecraft.src.*;

public class Slaughterfish extends EntityWaterMob {

	  private PathEntity pathToEntity;
    private float randomMotionSpeed;
    private float MotionVecX;
    private float MotionVecY;
    private float MotionVecZ;
		float buoyancy;
	 int timer = 0;
		PathEntity pathEntity;
		
	 
	
		
		
		public Slaughterfish(World par1World) 
		{
			super(par1World);
			texture = "/mob/slaughterfish.png";
			yOffset = -1F;  
			moveSpeed = 0.5F;
			tasks.addTask(0, new EntityAIRandomEvent(this, moveSpeed, par1World));
			 tasks.addTask(1, new EntityAIMoveThroughVillage(this, 0.16F, true));
			tasks.addTask(2, new EntityAIWander(this, .2F));
			
		}


	
	public Slaughterfish(World par1World, EntityLiving par2EntityLiving, float par3) {
			super(par1World);
			texture = "/mob/slaughterfish.png";
			  yOffset = -1F;  
				moveSpeed = 0.5F;
		tasks.addTask(0, new EntityAIRandomEvent(this, moveSpeed, par1World));
		 tasks.addTask(1, new EntityAIMoveThroughVillage(this, 0.3F, true));
		tasks.addTask(2, new EntityAIWander(this, .2F));
			moveSpeed = 0.4F;
			setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY+ (double) par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ,	par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);  
	 }
	
	@Override
	public boolean isAIEnabled()
	{
		return true;
	}
	
	
@Override
public boolean getCanSpawnHere()
{
		int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(boundingBox.minY);
        int k = MathHelper.floor_double(posZ);
        int coord = worldObj.getBlockId(i, j, k);
        
        return coord == Block.waterStill.blockID;

}



	
	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 10;

	}
	
	protected void attackEntity(Entity entity, float f)
    {
        if (attackTime <= 0 && f < 3.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 10;
            attackEntityAsMob(entity);
        
        }
    	
    }
	

	
	public boolean attackEntityAsMob(Entity par1Entity) {

		par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2 + rand.nextInt(3));

		return true;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		Entity entity = par1DamageSource.getEntity();

		if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
			par2 = (par2 + 1) / 2;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}
	 
	public boolean isInWater()
	    {
	        return worldObj.handleMaterialAcceleration(boundingBox.expand(0D, buoyancy, 0D), Material.water, this);
	    }
	  public void moveEntityWithHeading(float par1, float par2)
	    {
	     super.moveEntityWithHeading(par1, par2);
		  if(isInWater())
	      {
		  moveEntity(motionX * 2.4, motionY * 1.6, motionZ * 2.4);
	      }
	      }
	
	  protected void updateEntityActionState()
    {
       
       super.updateEntityActionState();
       List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(16D, 16D, 16D));

       if(!isInWater())
       {
    	    timer++;
    	    if(timer == 60)
    	    {
    	    	timer = 0;
    	    	this.damageEntity(DamageSource.generic, 3);
    	    }
       }
       
       
       if(isInWater())
       {
    	   isJumping = false;
       }
       for (int i = 0; i < list.size(); i++)
       {
           Entity entity1 = (Entity)list.get(i);
           if( (entity1 instanceof  EntityAnimal && entity1.isInWater()) || (!this.isInWater() && !(entity1 instanceof Slaughterfish) )|| entity1 instanceof EntityPlayer)
           {
        	   
        	   this.setTarget(entity1);	
           }
    }

       if (entityToAttack != null)
       {
           faceEntity(entityToAttack, 30F, 30F);
           double d6 = entityToAttack.posY - posY;
           double d4 = entityToAttack.posX - posX;
           double d5 = entityToAttack.posZ - posZ;
           rotationYaw = (float)((Math.atan2(d5, d4) * 180D) / Math.PI) - 90F;
           rotationPitch = (float)((Math.atan(d6) * 180D) / Math.PI) - 90F;
       }   

       if (entityToAttack != null && entityToAttack.posY > this.posY + 2 && worldObj.getBlockId((int)this.posX, (int)this.posY + 1, (int)this.posZ) != 0)
       {
         buoyancy = .4F;
         isJumping = true;
       }
       if (entityToAttack != null && entityToAttack.posY < this.posY)
       {
         buoyancy = -.2F;
       }
      
       if(entityToAttack == null)
       {
    	   buoyancy = 0F;
       }
       if(!isInWater())
       {
    	   isJumping = true;
       }
  
    }
}

