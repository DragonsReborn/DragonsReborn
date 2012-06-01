package me.ashtheking.dragons.ids;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_Dragon;

public class Ores {

	public static String[] oreList = { "Corundum", "Silver", "Orichalum", "Dwarven", "Moonstone",
			"Malachite", "Ebony" };

	public static ArrayList<Block> ores = new ArrayList<Block>();
	public static ArrayList<Item> ingots = new ArrayList<Item>();

	public static void load() {
		ingots.add(Item.ingotIron);
		for (int x = 0; x < oreList.length; x++) {
			Item i = new ItemIngot(mod_Dragon.LAST_ID + x).setItemName(oreList[x].toLowerCase()
					+ "Ingot");
			if (x != 3) {
				int m = ModLoader.addOverride("/terrain.png",
						"/dragons/ores/" + oreList[x].toLowerCase() + ".png");
				Block b = new BlockOre(150 + x, m, Material.rock).setBlockName("ore" + oreList[x]);
				ModLoader.addName(b, oreList[x] + " Ore");
				ModLoader.registerBlock(b);
				ores.add(b);
				ModLoader.addSmelting(b.blockID, new ItemStack(i.shiftedIndex, 1, 0));
			}
			ModLoader.addName(i, oreList[x] + " Ingot");
			if (x != 0)
				ingots.add(i);
			int k = ModLoader.addOverride("/gui/items.png",
					"/dragons/ingots/" + oreList[x].toLowerCase() + ".png");
			i.setIconIndex(k);
		}
		mod_Dragon.LAST_ID = mod_Dragon.LAST_ID + oreList.length;
		Item steel = new ItemIngot(mod_Dragon.LAST_ID).setItemName("steelIngot");
		ModLoader.addName(steel, "Steel Ingot");
		ModLoader.addRecipe(new ItemStack(steel.shiftedIndex, 1, 0),
				new Object[] { "XY", Character.valueOf('X'), ingots.get(0), Character.valueOf('Y'),
						ingots.get(1) });
		ingots.add(1, steel);
		int k = ModLoader.addOverride("/gui/items.png", "/dragons/ingots/steel.png");
		steel.setIconIndex(k);
		mod_Dragon.LAST_ID += 1;
		Item daedric = new ItemIngot(mod_Dragon.LAST_ID).setItemName("daedricIngot");
		ModLoader.addName(daedric, "Daedra Heart");
		ModLoader.addRecipe(new ItemStack(daedric.shiftedIndex, 1, 0), new Object[] { "XX",
				Character.valueOf('X'), ingots.get(7) });
		int j = ModLoader.addOverride("/gui/items.png", "/dragons/ingots/daedric.png");
		daedric.setIconIndex(j);
		ingots.add(daedric);

	}

	private static class BlockOre extends Block {
		public BlockOre(int x, int y, Material m) {
			super(x, y, m);
			setHardness(3F);
			setResistance(5F);
			setStepSound(Block.soundStoneFootstep);
		}
	}

	private static class ItemIngot extends Item {
		public ItemIngot(int id) {
			super(id);
		}
	}
}