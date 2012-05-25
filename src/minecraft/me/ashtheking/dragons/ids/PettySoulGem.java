package me.ashtheking.dragons.ids;

import net.minecraft.src.*;

public class PettySoulGem extends Item
{

	public PettySoulGem(int par1) {
		super(par1);
		setMaxDamage(25);
	}
	public boolean hasEffect(ItemStack par1ItemStack)
    {
        return par1ItemStack.getItemDamage()  != getMaxDamage();
    }
   public void drainGem(ItemStack par1ItemStack,int par1, EntityLiving par2EntityLiving)
    {
        if (!isDamageable())
        {
            return;
        }

        if (par1 > 0 && (par2EntityLiving instanceof EntityPlayer))
        {
         if(par1ItemStack.getItemDamage() < getMaxDamage())
         {
         par1ItemStack.setItemDamage(par1ItemStack.getItemDamage() + par1);
         }
         }

      
    }
}
