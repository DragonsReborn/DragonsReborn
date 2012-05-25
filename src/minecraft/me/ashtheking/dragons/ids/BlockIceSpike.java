package me.ashtheking.dragons.ids;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockIceSpike extends Block {
	public BlockIceSpike(int par1, int par2) {
		super(par1, par2, Material.ice);
		setTickRandomly(true);
	}

	public int tickRate() {
		return 50;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return 1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3,
			int par4) {
		return null;
	}

	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4,
			Entity par5Entity) {
		par5Entity.setInWeb();
		par5Entity.setFire(0);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (random.nextBoolean())
			world.setBlock(x, y, z, 0);
	}
}
