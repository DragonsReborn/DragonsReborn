package me.ashtheking.dragons.render;

import me.ashtheking.dragons.mob.EntityDeer;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderLiving;

public class RenderDeer extends RenderLiving {
	public RenderDeer(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	public void renderDeer(EntityDeer par1EntityDeer, double par2, double par4,
			double par6, float par8, float par9) {
		super.doRenderLiving(par1EntityDeer, par2, par4, par6, par8, par9);
	}

	public void doRenderLiving(EntityLiving par1EntityLiving, double par2,
			double par4, double par6, float par8, float par9) {
		renderDeer((EntityDeer) par1EntityLiving, par2, par4, par6, par8, par9);
	}
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		renderDeer((EntityDeer) par1Entity, par2, par4, par6, par8, par9);
	}
}
