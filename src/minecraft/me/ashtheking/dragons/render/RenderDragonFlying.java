package me.ashtheking.dragons.render;

import me.ashtheking.dragons.mob.DragonFlying;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderLiving;

import org.lwjgl.opengl.GL11;

public class RenderDragonFlying extends RenderLiving
{
    public RenderDragonFlying(ModelBase modelbase, float f)
    {
        super(modelbase, f);
    }
    
    protected void preRenderScale(DragonFlying entitygiantzombie, float f)
    {
        GL11.glScalef(4F, 4F, 4F);
    }

    protected void preRenderCallback(EntityLiving entityliving, float f)
    {
        preRenderScale((DragonFlying)entityliving, f);
    }
    
    protected float getWingRotation(DragonFlying entitychicken, float f)
    {
        float f1 = entitychicken.field_756_e + (entitychicken.field_752_b - entitychicken.field_756_e) * f;
        float f2 = entitychicken.field_757_d + (entitychicken.destPos - entitychicken.field_757_d) * f;
        return (MathHelper.sin(f1) + 1.0F) * f2;
    }

    protected float handleRotationFloat(EntityLiving entityliving, float f)
    {
        return getWingRotation((DragonFlying)entityliving, f);
    }

    public void renderCow(DragonFlying DragonFlying, double d, double d1, double d2,
            float f, float f1)
    {
        super.doRenderLiving(DragonFlying, d, d1, d2, f, f1);
    }

    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2,
            float f, float f1)
    {
        renderCow((DragonFlying)entityliving, d, d1, d2, f, f1);
    }

    public void doRender(Entity entity, double d, double d1, double d2,
            float f, float f1)
    {
        renderCow((DragonFlying)entity, d, d1, d2, f, f1);
    }
}
