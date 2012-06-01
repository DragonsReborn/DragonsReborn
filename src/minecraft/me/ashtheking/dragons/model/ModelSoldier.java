package me.ashtheking.dragons.model;

import net.minecraft.src.ModelBiped;

public class ModelSoldier extends ModelBiped {

	public boolean isBowOut;
	public ModelSoldier() {
		super();
	}
	   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
	    {
		   if(isBowOut)
		   {
	        aimedBow = true;
		   }
	        super.setRotationAngles(par1, par2, par3, par4, par5, par6);
	    }

}
