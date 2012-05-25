package me.ashtheking.dragons.ids;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockLight extends Block
{
	
    public BlockLight(int par1, int par2, Material par3Material)
    {
        super(par1, par2, par3Material);
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int i)
    {
        return null;
    }

    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Item.lightStoneDust.shiftedIndex;
    }
    
}
