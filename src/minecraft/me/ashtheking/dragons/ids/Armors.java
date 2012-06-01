package me.ashtheking.dragons.ids;

import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.RenderPlayer;
import net.minecraft.src.ModLoader;

public class Armors {

	public static void addMageAmors() {

	}

	public static void addHeavyArmors() {
		int k = 3000;
		for (int x = 0; x < Weapons.itemTypes.length; x++) {
			String type = Weapons.itemTypes[x];
			ItemArmor helm = (ItemArmor) new ItemArmor(k++, EnumArmorMaterial.DIAMOND, 6 + x, 0)
					.setItemName("helmet" + type);
			ModLoader.addName(helm, type + " Helmet");
			ItemArmor chest = (ItemArmor) new ItemArmor(k++, EnumArmorMaterial.DIAMOND, 6 + x, 1)
					.setItemName("chestplate" + type);
			ModLoader.addName(chest, type + " Chestplate");
			ItemArmor leg = (ItemArmor) new ItemArmor(k++, EnumArmorMaterial.DIAMOND, 6 + x, 2)
					.setItemName("leggings" + type);
			ModLoader.addName(leg, type + " Leggings");
			ItemArmor boot = (ItemArmor) new ItemArmor(k++, EnumArmorMaterial.DIAMOND, 6 + x, 3)
					.setItemName("boots" + type);
			ModLoader.addName(boot, type + " Boots");
			ModLoader.addArmor(type.toLowerCase());
		}
	}
}
