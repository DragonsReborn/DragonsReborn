package me.ashtheking.dragons.mob;

import me.ashtheking.dragons.ids.Ores;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.World;

public class DwarvenSpider extends EntitySpider {

	public DwarvenSpider(World par1World) {
		super(par1World);
		texture = "/mob/dwarvenspider.png";
	}
	
	@Override
	public int getDropItemId() {
		return Ores.ingots.get(3).shiftedIndex;
	}

}
