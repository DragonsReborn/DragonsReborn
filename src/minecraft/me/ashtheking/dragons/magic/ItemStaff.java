package me.ashtheking.dragons.magic;

import java.io.File;

import me.ashtheking.dragons.world.QuestHandler;
import me.ashtheking.dragons.world.QuestManager;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.mod_Dragon;

public class ItemStaff extends Item {

	public String spell;
	public int craft;
	public boolean item;

	public ItemStaff(int i, String s, int craft, boolean item) {
		super(i);
		maxStackSize = 1;
		setMaxDamage(64);
		spell = s;
		this.craft = craft;
		this.item = item;
	}

	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer ep, int i) {
		onStopUse(itemstack, world, ep, i);
		// GenuineNpcGuiStats gui = new GenuineNpcGuiStats(world, ep);
		// gui.initGui();
		// ModLoader.openGUI(ep, gui);
	}

	public void onStopUse(ItemStack itemstack, World world, EntityLiving ep, int i) {
		int j = getMaxItemUseDuration(itemstack) - i;
		float f = (float) j / 20F;
		f = (f * f + f * 2.0F) / 3F;
		if ((double) f < 0.1D)
			return;
		if (f > 1.0F)
			f = 1.0F;
		StaffEntity fp = getFireball(world, ep, f);
		if (fp == null)
			return;
		if (f == 1.0F)
			fp.arrowCritical = true;
		if (ep != null)
			itemstack.damageItem(4 * (int) f, ep);
		world.spawnEntityInWorld(fp);
		if (ep instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) ep;
		}
	}

	public ItemStack onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		return itemstack;
	}

	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 0x11940;
	}

	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.bow;
	}

	public StaffEntity getFireball(World world, final EntityLiving ep, float f) {
		return null;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
		return itemstack;
	}
}
