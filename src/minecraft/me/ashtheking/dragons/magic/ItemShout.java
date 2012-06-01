package me.ashtheking.dragons.magic;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class ItemShout extends Item {
	public static final String[] shoutNames = new String[] { "Fus Ro Dah", "Strun Bah Qo",
			"Joor Zah Frul", "Lok Vah Koor", "Yol Toor Shol", "Kaan Drem Ov", "Wuld Nah Kest" };

	public static final String[] shoutDetails = new String[] {
			"Scream at your enemies, blasting them all \n backward in a burst of force.",
			"Call down the destructive power of Skyrim's \n lightning on all nearby mobs.",
			"Lash at the very soul of Dragons, forcing \n them to land in confusion.",
			"Calm the storm, as you shout at its \n very existance to not exist.",
			"Shout at the fabric of space to create \n a fiery explosion at every nearby mob's feet.",
			"Sooth the anger that exists in all mobs, \n causing them to stop attacking.",
			"Shout, and have space bend around you as \n all your movements are multiplied instantly." };

	public static final boolean[] shoutEnabled = new boolean[] { false, false, false, false, false,
			false, false };

	public ItemShout(int i) {
		super(i);
		setHasSubtypes(true);
		setMaxDamage(shoutNames.length);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (itemstack.getItemDamage() == 0)
			Shouts.FusRoDah(world, player, player.boundingBox);
		else if (itemstack.getItemDamage() == 1)
			Shouts.StrunBahQo(world, player);
		else if (itemstack.getItemDamage() == 2)
			Shouts.JoorZahFrul(world, player);
		else if (itemstack.getItemDamage() == 3)
			Shouts.LokVahKoor(world, player);
		else if (itemstack.getItemDamage() == 4)
			Shouts.YolToorShol(world, player);
		else if (itemstack.getItemDamage() == 5)
			Shouts.KaanDremOv(world, player);
		else if (itemstack.getItemDamage() == 6)
			Shouts.WuldNahKest(world, player);
		itemstack.stackSize--;
		return itemstack;
	}

	public String getItemNameIS(ItemStack itemstack) {
		int i = MathHelper.clamp_int(itemstack.getItemDamage(), 0, shoutNames.length);
		return (new StringBuilder()).append(super.getItemName()).append(".").append(shoutNames[i])
				.toString();
	}
}