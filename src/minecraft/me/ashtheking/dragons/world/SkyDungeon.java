package me.ashtheking.dragons.world;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class SkyDungeon extends WorldGenerator {

	public boolean generate(World world, Random rand, int x, int y, int z) {
		return false;
	}

	//x 7
	//y 7
	//z 8
	public void genWordWallRoom(int posX, int posY, int posZ, World world) {
		for(int x = 0; x < 7; x++)
			for(int y = 0; y < 7; y++)
				for(int z = 0; z < 8; z++)
					world.setBlock(posX + x, posY + y, posZ + z, 0);
		for(int x = 6; x < 7; x++)
			for(int y = 2; y < 5; y++)
				for(int z = 2; z < 6; z++)
					world.setBlock(posX + x, posY + y, posZ + z, Block.glowStone.blockID); // Word Wall
		world.setBlock(posX + 6, posY + 2, posZ + 1, Block.stone.blockID);
		world.setBlock(posX + 6, posY + 2, posZ + 6, Block.stone.blockID);
		world.setBlock(posX + 6, posY + 3, posZ + 1, Block.torchWood.blockID);
		world.setBlock(posX + 6, posY + 3, posZ + 6, Block.torchWood.blockID);
		for(int x = 3; x < 7; x++)
			for(int y = 0; y < 2; y++)
				for(int z = 1; z < 7; z+=5)
					world.setBlock(posX + x, posY + y, posZ + z, Block.stone.blockID);
		for(int y = 0; y < 2; y++)
			for(int z = 1; z < 7; z++)
				world.setBlock(posX + 6, posY + y, posZ + z, Block.stone.blockID);
		for(int y = 0; y < 1; y++)
			for(int z = 1; z < 7; z++)
				world.setBlock(posX + 5, posY + y, posZ + z, Block.stone.blockID);
		for(int z = 2; z < 6; z++){
			world.setBlock(posX + 5, posY + 1, posZ + z, Block.stairsStoneBrickSmooth.blockID);
			world.setBlock(posX + 4, posY, posZ + z, Block.stairsStoneBrickSmooth.blockID);
		}
	}
}
