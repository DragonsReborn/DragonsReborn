package me.ashtheking.dragons.ids;

import java.util.ArrayList;

import net.minecraft.src.Entity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemSword;
import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_Dragon;

public class Weapons {

	public static String[] itemTypes = { "Iron", "Steel", "Silver", "Orcish",
			"Dwarven", "Elven", "Glass", "Ebony", "Daedric" };

	public static double[] itemTypeMod = { 1.0, 1.1, 1.1, 1.2, 1.3, 1.4, 1.7,
			1.85, 2.0 };

	public static String[] weaponTypes = { "Sword", "Greatsword", "Mace",
			"Warhammer", "War Axe", "Battleaxe", "Dagger" };

	public static ArrayList<ItemSword> swords = new ArrayList<ItemSword>();
	public static ArrayList<ItemSword> greatSwords = new ArrayList<ItemSword>();
	public static ArrayList<ItemSword> maces = new ArrayList<ItemSword>();
	public static ArrayList<ItemSword> warhammers = new ArrayList<ItemSword>();
	public static ArrayList<ItemSword> waraxes = new ArrayList<ItemSword>();
	public static ArrayList<ItemSword> battleaxes = new ArrayList<ItemSword>();
	public static ArrayList<ItemSword> daggers = new ArrayList<ItemSword>();
	public static ArrayList<ItemSword>[] arrayWeapon = new ArrayList[] {
			swords, greatSwords, maces, warhammers, waraxes, battleaxes,
			daggers };

	public static void addNames() {
		for (int x = 0; x < itemTypes.length; x++)
			for (int y = 0; y < weaponTypes.length; y++) {
				if (x == 0 && y == 0) {
					ModLoader.addName(arrayWeapon[y].get(x), "Dragonbane");
					continue;
				}
				String s = itemTypes[x];
				((ItemSword) arrayWeapon[y].get(x)).setItemName(s.toLowerCase()
						+ weaponTypes[y]);
				ModLoader.addName(arrayWeapon[y].get(x), s + " "
						+ weaponTypes[y]);
				// System.out.println("Loaded Weapon: " + itemTypes[x] + " " +
				// weaponTypes[y]);
			}

	}

	public static int k = mod_Dragon.LAST_ID;

	public static void load() {
		ItemSword dragonSword = (ItemSword) new ItemDragonSword(k - 256)
				.setItemName("dragonSword");
		swords.add(dragonSword);
		k++;
		for (int x = 1; x < itemTypes.length; x++) {
			swords.add(new Weapon(k - 256, (int) ((7 + x) * itemTypeMod[x]),
					EnumToolMaterial.EMERALD));
			ModLoader.addRecipe(new ItemStack(k, 1, 0),
					swordCraft(Ores.ingots.get(x)));
			k++;
		}
		for (int x = 0; x < itemTypes.length; x++) {
			greatSwords
					.add(new Weapon(k - 256, (int) ((15 + x) * itemTypeMod[x]),
							EnumToolMaterial.EMERALD));
			ModLoader.addRecipe(new ItemStack(k, 1, 0),
					greatswordCraft(Ores.ingots.get(x)));
			k++;
		}
		for (int x = 0; x < itemTypes.length; x++) {
			maces.add(new Weapon(k - 256, (int) ((9 + x) * itemTypeMod[x]),
					EnumToolMaterial.EMERALD));
			ModLoader.addRecipe(new ItemStack(k, 1, 0),
					maceCraft(Ores.ingots.get(x)));
			k++;
		}
		for (int x = 0; x < itemTypes.length; x++) {
			warhammers
					.add(new Weapon(k - 256, (int) ((18 + x) * itemTypeMod[x]),
							EnumToolMaterial.EMERALD));
			ModLoader.addRecipe(new ItemStack(k, 1, 0),
					warhammerCraft(Ores.ingots.get(x)));
			k++;
		}
		for (int x = 0; x < itemTypes.length; x++) {
			waraxes.add(new Weapon(k - 256, (int) ((8 + x) * itemTypeMod[x]),
					EnumToolMaterial.EMERALD));
			ModLoader.addRecipe(new ItemStack(k, 1, 0),
					waraxeCraft(Ores.ingots.get(x)));
			k++;
		}
		for (int x = 0; x < itemTypes.length; x++) {
			battleaxes
					.add(new Weapon(k - 256, (int) ((16 + x) * itemTypeMod[x]),
							EnumToolMaterial.EMERALD));
			ModLoader.addRecipe(new ItemStack(k, 1, 0),
					battleaxeCraft(Ores.ingots.get(x)));
			k++;
		}
		for (int x = 0; x < itemTypes.length; x++) {
			daggers.add(new Weapon(k - 256, (int) ((4 + x) * itemTypeMod[x]),
					EnumToolMaterial.EMERALD));
			ModLoader.addRecipe(new ItemStack(k, 1, 0),
					daggerCraft(Ores.ingots.get(x)));
			k++;
		}
		mod_Dragon.LAST_ID = k;
		for (int x = 0; x < weaponTypes.length; x++)
			for (int y = 0; y < itemTypes.length; y++) {
				int z = ModLoader.addOverride("/gui/items.png",
						"/dragons/" + weaponTypes[x].toLowerCase().replace(" ", "") + "/"
								+ itemTypes[y].toLowerCase() + ".png");
				arrayWeapon[x].get(y).setIconIndex(z);
			}
	}

	public static Object[] swordCraft(Item material) {
		Object[] t = { "X", "X", "x", Character.valueOf('X'), material,
				Character.valueOf('x'), Item.stick };
		return t;
	}

	public static Object[] greatswordCraft(Item material) {
		Object[] t = { " X ", " X ", "XxX", Character.valueOf('X'), material,
				Character.valueOf('x'), Item.stick };
		return t;
	}

	public static Object[] maceCraft(Item material) {
		Object[] t = { "X", "x", "x", Character.valueOf('X'), material,
				Character.valueOf('x'), Item.stick };
		return t;
	}

	public static Object[] warhammerCraft(Item material) {
		Object[] t = { "XX ", " xX", " x ", Character.valueOf('X'), material,
				Character.valueOf('x'), Item.stick };
		return t;
	}

	public static Object[] waraxeCraft(Item material) {
		Object[] t = { "XXX", "Xx ", " x ", Character.valueOf('X'), material,
				Character.valueOf('x'), Item.stick };
		return t;
	}

	public static Object[] battleaxeCraft(Item material) {
		Object[] t = { "XXX", "XxX", " x ", Character.valueOf('X'), material,
				Character.valueOf('x'), Item.stick };
		return t;
	}

	public static Object[] daggerCraft(Item material) {
		Object[] t = { "X", "x", Character.valueOf('X'), material,
				Character.valueOf('x'), Item.stick };
		return t;
	}

	public static class Weapon extends ItemSword {

		public int weaponX;

		public Weapon(int id, int weapon, EnumToolMaterial enumtoolmaterial) {
			super(id, enumtoolmaterial);
			weaponX = weapon;
		}

		public int getDamageVsEntity(Entity entity) {
			return weaponX / 2;
		}

	}
}
