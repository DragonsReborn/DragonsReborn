package me.ashtheking.dragons.ids;

import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StatList;
import net.minecraft.src.World;

public class GrandSoulGem extends Item {

	public GrandSoulGem(int par1) {
		super(par1);
		setMaxDamage(200);
	}
	   public boolean hasEffect(ItemStack par1ItemStack)
	    {
	        return par1ItemStack.getItemDamage()  != getMaxDamage();
	    }
	   
	   public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
		 drainGem(par1ItemStack,10, par3EntityPlayer);
		   return par1ItemStack;
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
