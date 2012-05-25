package me.ashtheking.dragons.lockpicking;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Material;

public class BlockSkyrimLockedChest extends Block {

	public BlockSkyrimLockedChest(int i, int j, Material material) {
		super(i, j, material);
	}
	
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return Block.chest.blockID;
	}
	
}
