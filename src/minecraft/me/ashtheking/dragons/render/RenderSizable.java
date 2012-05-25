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

public class RenderSizable extends RenderLiving
{

    private float scale;

    public RenderSizable(ModelBase modelbase, float f, float f1)
    {
        super(modelbase, f * f1);
        scale = f1;
    }

    protected void preRenderScale(EntityLiving entityliving, float f)
    {
        GL11.glScalef(scale, scale, scale);
    }

    protected void preRenderCallback(EntityLiving entityliving, float f)
    {
        preRenderScale((EntityLiving)entityliving, f);
    }
}
