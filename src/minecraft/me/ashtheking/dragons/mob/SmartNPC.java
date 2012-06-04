package me.ashtheking.dragons.mob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.EntityAIPanic;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.ShapedRecipes;
import net.minecraft.src.World;
import net.minecraft.src.mod_Dragon;

public class SmartNPC extends EntityCreature {

	private HashMap<Block, ShapedRecipes> blockCraft = new HashMap<Block, ShapedRecipes>();
	private HashMap<Item, ShapedRecipes> itemCraft = new HashMap<Item, ShapedRecipes>();
	private ArrayList<Integer> inventory = new ArrayList<Integer>();
	private ItemStack heldItem;

	public SmartNPC(World par1World) {
		super(par1World);
		texture = "/mob/char.png";
        moveSpeed = 0.23F;
		tasks.addTask(2, new EntityAIWander(this, moveSpeed));
		tasks.addTask(0, new EntityAISwimming(this));
	}

	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public int getMaxHealth() {
		return 20;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		int id = worldObj.getBlockId((int) posX, (int) posY - 1, (int) posZ);
		Block b = Block.blocksList[id];
		if (id != 0 && b.blockMaterial != null && !inventory.contains(b.blockID))
			if (b.blockMaterial.isHarvestable()) {
				inventory.add(b.blockID);
				worldObj.setBlock((int) posX, (int) posY - 1, (int) posZ, 0);
				List l = CraftingManager.getInstance().getRecipeList();
				for (Object o : l) {
					if (!(o instanceof ShapedRecipes))
						break;
					ShapedRecipes s = (ShapedRecipes) o;
					List<ItemStack> li = new ArrayList<ItemStack>();
					for (ItemStack i : s.recipeItems)
						li.add(i);
					if (li.contains(new ItemStack(b, 1))) {
						blockCraft.put(b, s);
						Item result = Item.itemsList[s.getRecipeOutput().itemID];
						heldItem = new ItemStack(result, 1);
					}
				}
			}
	}

	public ItemStack getHeldItem() {
		return heldItem;
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		save();
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		load();
	}

	public void save() {
		mod_Dragon.save(blockCraft, "saves/" + worldObj.getSaveHandler().getSaveDirectoryName()
				+ "/dragons/smartBlock.data");
		mod_Dragon.save(itemCraft, "saves/" + worldObj.getSaveHandler().getSaveDirectoryName()
				+ "/dragons/smartItem.data");
		mod_Dragon.save(inventory, "save/" + worldObj.getSaveHandler().getSaveDirectoryName()
				+ "/dragons/smartInventory.data");
	}

	public void load() {
		blockCraft = (HashMap<Block, ShapedRecipes>) mod_Dragon.load("saves/"
				+ worldObj.getSaveHandler().getSaveDirectoryName() + "/dragons/smartBlock.data");
		if (blockCraft == null)
			blockCraft = new HashMap<Block, ShapedRecipes>();
		itemCraft = (HashMap<Item, ShapedRecipes>) mod_Dragon.load("saves/"
				+ worldObj.getSaveHandler().getSaveDirectoryName() + "/dragons/smartItem.data");
		if (itemCraft == null)
			itemCraft = new HashMap<Item, ShapedRecipes>();
		inventory = (ArrayList<Integer>) mod_Dragon
				.load("saves/" + worldObj.getSaveHandler().getSaveDirectoryName()
						+ "/dragons/smartInventory.data");
		if (inventory == null)
			inventory = new ArrayList<Integer>();
	}
}
