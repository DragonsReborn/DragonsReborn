
package me.ashtheking.dragons.model;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelDwarvenSpider extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg8;
    ModelRenderer leg6;
    ModelRenderer leg4;
    ModelRenderer leg2;
    ModelRenderer leg7;
    ModelRenderer leg5;
    ModelRenderer leg3;
    ModelRenderer leg1;
  
  public ModelDwarvenSpider()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      head = new ModelRenderer(this, 42, 4);
      head.addBox(-4F, -4F, -8F, 6, 6, 6);
      head.setRotationPoint(1F, 14F, 5F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 0, 11);
      body.addBox(-5F, -4F, -6F, 8, 8, 13);
      body.setRotationPoint(1F, 17F, 1F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      leg8 = new ModelRenderer(this, 18, 0);
      leg8.addBox(-1F, -1F, -1F, 16, 2, 2);
      leg8.setRotationPoint(4F, 15F, -1F);
      leg8.setTextureSize(64, 32);
      leg8.mirror = true;
      setRotation(leg8, 0F, 1.570796F, 0F);
      leg6 = new ModelRenderer(this, 18, 0);
      leg6.addBox(-1F, -1F, -1F, 16, 2, 2);
      leg6.setRotationPoint(4F, 20F, -3F);
      leg6.setTextureSize(64, 32);
      leg6.mirror = true;
      setRotation(leg6, 0F, 0.2792527F, 0.1919862F);
      leg4 = new ModelRenderer(this, 18, 0);
      leg4.addBox(-1F, -1F, -1F, 16, 2, 2);
      leg4.setRotationPoint(4F, 20F, 2F);
      leg4.setTextureSize(64, 32);
      leg4.mirror = true;
      setRotation(leg4, 0F, 0F, 0.1919862F);
      leg2 = new ModelRenderer(this, 18, 0);
      leg2.addBox(-1F, -1F, -1F, 16, 2, 2);
      leg2.setRotationPoint(4F, 20F, 6F);
      leg2.setTextureSize(64, 32);
      leg2.mirror = true;
      setRotation(leg2, 0F, -0.5759587F, 0.1919862F);
      leg7 = new ModelRenderer(this, 18, 0);
      leg7.addBox(-15F, -1F, -1F, 16, 2, 2);
      leg7.setRotationPoint(-4F, 15F, -1F);
      leg7.setTextureSize(64, 32);
      leg7.mirror = true;
      setRotation(leg7, 0F, -1.570796F, 0F);
      leg5 = new ModelRenderer(this, 18, 0);
      leg5.addBox(-15F, -1F, -1F, 16, 2, 2);
      leg5.setRotationPoint(-4F, 20F, -3F);
      leg5.setTextureSize(64, 32);
      leg5.mirror = true;
      setRotation(leg5, 0F, -0.2792527F, -0.1919862F);
      leg3 = new ModelRenderer(this, 18, 0);
      leg3.addBox(-15F, -1F, -1F, 16, 2, 2);
      leg3.setRotationPoint(-4F, 20F, 2F);
      leg3.setTextureSize(64, 32);
      leg3.mirror = true;
      setRotation(leg3, 0F, 0F, -0.1919862F);
      leg1 = new ModelRenderer(this, 18, 0);
      leg1.addBox(-15F, -1F, -1F, 16, 2, 2);
      leg1.setRotationPoint(-4F, 20F, 6F);
      leg1.setTextureSize(64, 32);
      leg1.mirror = true;
      setRotation(leg1, 0F, 0.5759587F, -0.1919862F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    head.render(f5);
    body.render(f5);
    leg8.render(f5);
    leg6.render(f5);
    leg4.render(f5);
    leg2.render(f5);
    leg7.render(f5);
    leg5.render(f5);
    leg3.render(f5);
    leg1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6)
  {
//	  head.rotateAngleY = par4 / (180F / (float)Math.PI);
//      head.rotateAngleX = par5 / (180F / (float)Math.PI);
      float f = ((float)Math.PI / 4F);
      leg1.rotateAngleZ = -f;
      leg2.rotateAngleZ = f;
      leg3.rotateAngleZ = -f * 0.74F;
      leg4.rotateAngleZ = f * 0.74F;
      leg5.rotateAngleZ = -f * 0.74F;
      leg6.rotateAngleZ = f * 0.74F;
//      leg7.rotateAngleZ = -f;
//      leg8.rotateAngleZ = f;
      float f1 = -0F;
      float f2 = 0.3926991F;
      leg1.rotateAngleY = f2 * 2.0F + f1;
      leg2.rotateAngleY = -f2 * 2.0F - f1;
      leg3.rotateAngleY = f2 * 1.0F + f1;
      leg4.rotateAngleY = -f2 * 1.0F - f1;
      leg5.rotateAngleY = -f2 * 1.0F + f1;
      leg6.rotateAngleY = f2 * 1.0F - f1;
//      leg7.rotateAngleY = -f2 * 2.0F + f1;
//      leg8.rotateAngleY = f2 * 2.0F - f1;
      float f3 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
      float f4 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * par2;
      float f5 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * par2;
      float f6 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + ((float)Math.PI * 3F / 2F)) * 0.4F) * par2;
      float f7 = Math.abs(MathHelper.sin(par1 * 0.6662F + 0.0F) * 0.4F) * par2;
      float f8 = Math.abs(MathHelper.sin(par1 * 0.6662F + (float)Math.PI) * 0.4F) * par2;
      float f9 = Math.abs(MathHelper.sin(par1 * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * par2;
      float f10 = Math.abs(MathHelper.sin(par1 * 0.6662F + ((float)Math.PI * 3F / 2F)) * 0.4F) * par2;
      leg1.rotateAngleY += f3;
      leg2.rotateAngleY += -f3;
      leg3.rotateAngleY += f4;
      leg4.rotateAngleY += -f4;
      leg5.rotateAngleY += f5;
      leg6.rotateAngleY += -f5;
//      leg7.rotateAngleY += f6;
//      leg8.rotateAngleY += -f6;
      leg1.rotateAngleZ += f7;
      leg2.rotateAngleZ += -f7;
      leg3.rotateAngleZ += f8;
      leg4.rotateAngleZ += -f8;
      leg5.rotateAngleZ += f9;
      leg6.rotateAngleZ += -f9;
//      leg7.rotateAngleZ += f10;
//      leg8.rotateAngleZ += -f10;
  }

}
