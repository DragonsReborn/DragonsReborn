package me.ashtheking.dragons.generation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Item;
import net.minecraft.src.World;

import org.lwjgl.util.vector.Vector3f;

public class StructureSaver extends Item
{

	protected StructureSaver(int j) 
	{
		super(j);
	}

	 public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int l)
	 {
		 
		 WriteData(world, x, y, z);
		 ReadData(world, x, y, z);
		 
		 return true;
	 }
	 
	 /** 
	  * This will eventually build the main village house
	  * @param theWorld = World
	  * @param x = The x coordinate of the block you right clicked
	  * @param y = The y coordinate of the block you right clicked
	  * @param z = The z coordinate of the block you right clicked
	  */
	public void WriteData(World world, int x, int y, int z)
	{ 
		
		int blockMeta;
		
		try
		{
			File file = new File("JarlsHouse.dat");

			FileOutputStream lastStats = new FileOutputStream(file, true);
			DataOutputStream writer = new DataOutputStream(lastStats);
			 			

			for (int k = 1; k < 10; k++)
			{
				for (int i = 0; i < 30; i++)
				{
					for (int j = 0; j < 30; j++)
					{	

						blockMeta = world.getBlockMetadata(x + i, y + k, z + j);
						
						if (world.getBlockId(x + i, y + k, z + j) != 0)
						{
							
							writer.writeInt(i);
						 	writer.writeInt(k);
						 	writer.writeInt(j);
						 	writer.writeInt(world.getBlockId(x + i, y + k, z + j));		 	
						 	writer.writeInt(world.getBlockMetadata(x + i, y + k, z + j));
						 	System.out.println(world.getBlockMetadata(x + i, y + k, z + j));
						}	
					}
				}
			}
	 
			writer.close();
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			System.exit(1);
		} 
		
	}
	
	public void ReadData(World world, int x, int y, int z)
	{
		
		try
		{
			
			FileInputStream lastStats = new FileInputStream("JarlsHouse.dat");
			BufferedInputStream input = new BufferedInputStream(lastStats);
			DataInputStream reader = new DataInputStream(input);

			while (input != null)
			{
				world.setBlockAndMetadataWithNotify(x + reader.readInt(), y + reader.readInt(), z + reader.readInt(), reader.readInt(), reader.readInt());			
			}
			
			reader.close();
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
}