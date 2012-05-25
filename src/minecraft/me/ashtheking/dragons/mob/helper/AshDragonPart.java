// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package me.ashtheking.dragons.mob.helper;

import me.ashtheking.dragons.mob.Dragon;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.NBTTagCompound;


// Referenced classes of package net.minecraft.src:
//            Entity, EntityDragonBase, NBTTagCompound, DamageSource

public class AshDragonPart extends Entity
{

    public final Dragon myDragon;
    public final String field_40072_b;

    public AshDragonPart(Dragon dragon, String s, float f, float f1)
    {
        super(dragon.worldObj);
        setSize(f, f1);
        myDragon = dragon;
        field_40072_b = s;
    }

    protected void entityInit()
    {
    }

    protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
    }

    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
    }

    public boolean canBeCollidedWith()
    {
        return true;
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        return myDragon.dragonPartHurt(this, damagesource, i);
    }

    public boolean func_41004_h(Entity entity)
    {
        return this == entity || myDragon == entity;
    }
}
