package me.ashtheking.dragons.world;

import net.minecraft.src.*;

	public class ItemTestGen extends Item
	{

		public ItemTestGen(int j) 
		{
			super(j);
		}

		 public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int i, int j, int k, int l)
		 {
			 
			 makeAHouse(world, i, j, k);
			 
			 return true;
		 }
		 
		 /** 
		  * This will eventually build the main village house
		  * @param theWorld = World
		  * @param x = The x coordinate of the block you right clicked
		  * @param y = The y coordinate of the block you right clicked
		  * @param z = The z coordinate of the block you right clicked
		  */
		 public void makeAHouse(World theWorld, int x, int y, int z)
		 {
			 
			 int fence = Block.fence.blockID;
			 int stairs = Block.stairCompactPlanks.blockID;
			 int cobblestone = Block.cobblestone.blockID;
			 int logs = Block.wood.blockID;
			 int planks = Block.planks.blockID;
			 
			 //First set
			 theWorld.setBlockAndMetadataWithNotify(x + 1, y + 1, z, stairs, 2);
			 theWorld.setBlockAndMetadataWithNotify(x + 2, y + 1, z, stairs, 2);
			 theWorld.setBlockAndMetadataWithNotify(x + 3, y + 1, z, stairs, 2);
			 
			 theWorld.setBlockWithNotify(x + 1, y + 1, z + 1, cobblestone);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 1, cobblestone);
			 
			 theWorld.setBlockWithNotify(x, y + 1, z + 2, fence);
			 theWorld.setBlockWithNotify(x + 1, y + 1, z + 2, cobblestone);
			 theWorld.setBlockWithNotify(x + 3, y + 1, z + 2, fence);
			 theWorld.setBlockWithNotify(x + 4, y + 1, z + 2, fence);
			 
			 //Second set
			 theWorld.setBlockWithNotify(x + 1, y + 1, z + 3, cobblestone);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 3, cobblestone);
			 theWorld.setBlockWithNotify(x + 3, y + 1, z + 3, cobblestone);
			 theWorld.setBlockWithNotify(x + 4, y + 1, z + 3, fence);
			 theWorld.setBlockWithNotify(x + 7, y + 1, z + 3, fence);
			 theWorld.setBlockWithNotify(x + 10, y + 1, z + 3, fence);
			 
			 //Z + 4 generation
			 theWorld.setBlockWithNotify(x, y + 1, z + 4, fence);
			 theWorld.setBlockWithNotify(x + 1, y + 1, z + 4, cobblestone);
			 theWorld.setBlockWithNotify(x + 3, y + 1, z + 4, fence);
			 theWorld.setBlockWithNotify(x + 4, y + 1, z + 4, cobblestone);
			 for (int i = 5; i < 11; i++)
			 {
				 theWorld.setBlockWithNotify(x + i, y + 1, z + 4, cobblestone);
			 }
			 
			 //Z + 5 generation
			 for (int i = 1; i < 11; i++)
			 {
				 theWorld.setBlockWithNotify(x + i, y + 1, z + 5, cobblestone);
			 }
			 
			 //Z + 6 generation
			 theWorld.setBlockWithNotify(x, y + 1, z + 6, fence);
			 theWorld.setBlockWithNotify(x + 1, y + 1, z + 6, cobblestone);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 6, cobblestone);
			 theWorld.setBlockWithNotify(x + 3, y + 1, z + 6, fence);
			 theWorld.setBlockWithNotify(x + 4, y + 1, z + 6, cobblestone);
			 theWorld.setBlockWithNotify(x + 6, y + 1, z + 6, cobblestone);
			 theWorld.setBlockWithNotify(x + 7, y + 1, z + 6, fence);
			 for (int i = 7; i < 11; i++)
			 {
				 theWorld.setBlockWithNotify(x + i, y + 1, z + 6, cobblestone);
			 }
			 
			 //Z + 7 generation
			 for (int i = 1; i < 11; i++)
			 {
				 theWorld.setBlockWithNotify(x + i, y + 1, z + 7, cobblestone);
			 }
			 
			 theWorld.setBlockWithNotify(x, y + 1, z + 8, fence);
			 
			 //Z + 8 generation
			 for (int i = 1; i < 6; i++)
			 {
				 theWorld.setBlockWithNotify(x + i, y + 1, z + 8, cobblestone);
			 }
			 theWorld.setBlockWithNotify(x + 9, y + 1, z + 8, cobblestone);
			 theWorld.setBlockWithNotify(x + 10, y + 1, z + 8, fence);
			 
			 //Fourth set 
			 theWorld.setBlockWithNotify(x + 1, y + 1, z + 9, fence);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 9, cobblestone);
			 theWorld.setBlockWithNotify(x + 4, y + 1, z + 9, cobblestone);
			 theWorld.setBlockWithNotify(x + 5, y + 1, z + 9, cobblestone);
			 
			 theWorld.setBlockWithNotify(x, y + 1, z + 10, fence);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 10, cobblestone);
			 theWorld.setBlockWithNotify(x + 4, y + 1, z + 10, fence);
			 theWorld.setBlockWithNotify(x + 5, y + 1, z + 10, cobblestone);
			 
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 11, cobblestone);
			 
			 //Fifth set
			 theWorld.setBlockWithNotify(x, y + 1, z + 12, fence);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 12, cobblestone);
			 
			 theWorld.setBlockWithNotify(x + 1, y + 1, z + 13, fence);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 13, cobblestone);
			 
			 theWorld.setBlockWithNotify(x, y + 1, z + 14, fence);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 14, cobblestone);
			 theWorld.setBlockWithNotify(x + 5, y + 1, z + 14, fence);
			 theWorld.setBlockWithNotify(x + 10, y + 1, z + 14, fence);
			 
			 //Sixth set
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 15, cobblestone);
			 
			 theWorld.setBlockWithNotify(x, y + 1, z + 16, fence);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 16, cobblestone);
			 
			 theWorld.setBlockWithNotify(x + 1, y + 1, z + 17, fence);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 17, cobblestone);
			 
			 //Seventh set
			 theWorld.setBlockWithNotify(x, y + 1, z + 18, fence);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 18, cobblestone);
			 theWorld.setBlockWithNotify(x + 5, y + 1, z + 18, fence);
			 
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 19, cobblestone);
			 
			 theWorld.setBlockWithNotify(x, y + 1, z + 20, fence);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 20, cobblestone);
			 
			 //Eighth set
			 theWorld.setBlockWithNotify(x + 1, y + 1, z + 21, fence);
			 theWorld.setBlockWithNotify(x + 2, y + 1, z + 21, cobblestone);
			 theWorld.setBlockWithNotify(x + 3, y + 1, z + 21, cobblestone);
			 theWorld.setBlockWithNotify(x + 4, y + 1, z + 21, cobblestone);
			 theWorld.setBlockWithNotify(x + 5, y + 1, z + 21, cobblestone);
			 
			 theWorld.setBlockWithNotify(x, y + 1, z + 22, fence);
			 theWorld.setBlockWithNotify(x + 5, y + 1, z + 22, fence);
			 theWorld.setBlockWithNotify(x + 10, y + 1, z + 22, fence);
			 
			 //Sideways generation
			 for (int i = 4; i < 22; i++)
			 {
				 theWorld.setBlockWithNotify(x + 9, y + 1, z + i, cobblestone);
			 }
			 
			 for (int i = 11; i < 22; i++)
			 {
				 theWorld.setBlockWithNotify(x + 6, y + 1, z + i, cobblestone);
			 }
			 
			 //Generate the fireplace
			 theWorld.setBlockWithNotify(x + 7, y + 1, z + 19, cobblestone);
			 theWorld.setBlockAndMetadataWithNotify(x + 7, y + 1, z + 20, logs, 0);
			 theWorld.setBlockWithNotify(x + 7, y + 1, z + 21, cobblestone);
			 theWorld.setBlockWithNotify(x + 8, y + 1, z + 19, cobblestone);
			 theWorld.setBlockAndMetadataWithNotify(x + 8, y + 1, z + 20, logs, 0);
			 theWorld.setBlockWithNotify(x + 8, y + 1, z + 21, cobblestone);
			 
			 
			 //Second floor
			 theWorld.setBlockAndMetadataWithNotify(x + 1, y + 2, z + 1, stairs, 2);
			 theWorld.setBlockAndMetadataWithNotify(x + 2, y + 2, z + 1, stairs, 2);
			 theWorld.setBlockAndMetadataWithNotify(x + 3, y + 2, z + 1, stairs, 2);
			 
			 theWorld.setBlockWithNotify(x, y + 2, z + 2, fence);
			 theWorld.setBlockAndMetadataWithNotify(x + 1, y + 2, z + 2, planks, 1);
			 theWorld.setBlockAndMetadataWithNotify(x + 2, y + 2, z + 2, planks, 1);
			 theWorld.setBlockAndMetadataWithNotify(x + 3, y + 2, z + 2, planks, 1);
			 theWorld.setBlockWithNotify(x + 4, y + 2, z + 2, fence);
			 
			 for (int i = 1; i < 11; i++)
			 {
				 for (int j = 3; j < 23; j++)
				 {
					 theWorld.setBlockAndMetadataWithNotify(x + i, y + 2, z + j, planks, 1);
				 }
			 }
			 
			 for (int i = 8; i < 23; i++)
			 {
				 theWorld.setBlockWithNotify(x, y + 2, z + i, fence);
			 }
			 
			 theWorld.setBlockWithNotify(x, y + 2, z + 4, fence);
			 theWorld.setBlockWithNotify(x, y + 2, z + 6, fence);
			 
			 theWorld.setBlockWithNotify(x + 8, y + 2, z + 5, Block.lavaStill.blockID);
			 
			 theWorld.setBlockWithNotify(x + 6, y + 2, z + 20, cobblestone);
			 theWorld.setBlockWithNotify(x + 6, y + 2, z + 21, cobblestone);
			 
			 theWorld.setBlockWithNotify(x + 7, y + 2, z + 19, cobblestone);
			 theWorld.setBlockWithNotify(x + 7, y + 2, z + 20, logs);
			 theWorld.setBlockWithNotify(x + 7, y + 2, z + 21, cobblestone);

			 theWorld.setBlockWithNotify(x + 8, y + 2, z + 19, cobblestone);
			 theWorld.setBlockWithNotify(x + 8, y + 2, z + 20, logs);
			 theWorld.setBlockWithNotify(x + 8, y + 2, z + 21, cobblestone);
			 
			 theWorld.setBlockWithNotify(x + 9, y + 2, z + 20, cobblestone);
			 theWorld.setBlockWithNotify(x + 9, y + 2, z + 21, cobblestone);		 
			 
			 //Third layer
			 theWorld.setBlockWithNotify(x + 9, y + 3, z + 4, cobblestone);
			 theWorld.setBlockWithNotify(x + 9, y + 3, z + 5, cobblestone);	
			 theWorld.setBlockWithNotify(x + 9, y + 3, z + 6, cobblestone);	
			 
			 theWorld.setBlockWithNotify(x + 7, y + 3, z + 4, cobblestone);	
			 theWorld.setBlockWithNotify(x + 7, y + 3, z + 5, cobblestone);	
			 theWorld.setBlockWithNotify(x + 7, y + 3, z + 6, cobblestone);	
			 
			 theWorld.setBlockWithNotify(x + 8, y + 3, z + 4, cobblestone);	
			 theWorld.setBlockWithNotify(x + 8, y + 3, z + 6, cobblestone);	

			 for (int i = 2; i < 23; i++)
			 {
				 theWorld.setBlockWithNotify(x, y + 3, z + i, fence);
			 }
			 
			 for (int i = 1; i < 5; i++)
			 {
				 theWorld.setBlockWithNotify(x + i, y + 3, z + 22, fence);
			 }
			 
			 for (int i = 3; i < 9; i++)
			 {
				 theWorld.setBlockWithNotify(x + 10, y + 3, z + i, fence); 
			 }
			 
			 for (int i = 5; i < 10; i++)
			 {
				 theWorld.setBlockWithNotify(x + i, y + 3, z + 3, fence);
			 }
			 
			 for (int i = 2; i < 7; i++)
			 {
				 theWorld.setBlockWithNotify(x + 4, y + 3, z + i, fence); 
			 }
			 
			 theWorld.setBlockWithNotify(x + 4, y + 3, z + 8, fence); 
			 
		 } 
	}
	
