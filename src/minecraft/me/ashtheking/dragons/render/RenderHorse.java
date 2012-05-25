// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package me.ashtheking.dragons.render;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderLiving;

import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            RenderLiving, EntityLiving, ModelBase, EntityLiving

public class RenderHorse extends RenderLiving
{

    private float scale;

    public RenderHorse(ModelBase modelbase, float f, float f1)
    {
        super(modelbase, f * f1);
        scale = f1;
    }

    protected void preRenderScale(EntityLiving entityliving, float f)
    {
        GL11.glScalef(scale, scale, scale);
    }

 

    protected void rotateAnimal(EntityLiving entityliving)
    {
            GL11.glRotatef(45F, -1F, 0.0F, 0.0F);
    }
   
    protected void preRenderCallback(EntityLiving entityliving, float f)
    {
            EntityLiving entity = entityliving;
          
 
            if (entity.motionY < -.0001 && entity.isAirBorne)
            {
       //             rotateAnimal(entityliving);
            }
            isInAir(entity);
    }
   
    protected void isInAir(EntityLiving entity)
    {
            if (!entity.onGround)
            {
            	
                    if (entity.motionY > 0.1D && entity.motionY < 0.5D)
                    {
                            GL11.glRotatef(25F, -1F, 0.0F, 0.0F);
                    }
                    else
                    {
                            GL11.glRotatef((float)(entity.motionY * 70D), -1F, 0.0F, 0.0F);
                    }
            }
    }

}
