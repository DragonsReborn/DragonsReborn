
package me.ashtheking.dragons.model;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelDeer extends ModelBase
{
  //fields
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer body;
    ModelRenderer tail;
    ModelRenderer neck;
    ModelRenderer head;
    ModelRenderer left_horn1;
    ModelRenderer left_horn2;
    ModelRenderer left_horn3;
    ModelRenderer left_horn4;
    ModelRenderer left_horn5;
    ModelRenderer right_horn1;
    ModelRenderer right_horn2;
    ModelRenderer right_horn3;
    ModelRenderer right_horn4;
    ModelRenderer right_horn5;
  
  public ModelDeer()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      leg1 = new ModelRenderer(this, 0, 33);
      leg1.addBox(0F, 0F, 0F, 3, 12, 3);
      leg1.setRotationPoint(-4F, 12F, -1F);
      leg1.setTextureSize(64, 32);
      leg1.mirror = true;
      setRotation(leg1, 0F, 0F, 0F);
      leg2 = new ModelRenderer(this, 0, 33);
      leg2.addBox(0F, 0F, 0F, 3, 12, 3);
      leg2.setRotationPoint(2F, 12F, 17F);
      leg2.setTextureSize(64, 32);
      leg2.mirror = true;
      setRotation(leg2, 0F, 0F, 0F);
      leg3 = new ModelRenderer(this, 0, 33);
      leg3.addBox(0F, 0F, 0F, 3, 12, 3);
      leg3.setRotationPoint(-4F, 12F, 17F);
      leg3.setTextureSize(64, 32);
      leg3.mirror = true;
      setRotation(leg3, 0F, 0F, 0F);
      leg4 = new ModelRenderer(this, 0, 33);
      leg4.addBox(0F, 0F, 0F, 3, 12, 3);
      leg4.setRotationPoint(2F, 12F, -1F);
      leg4.setTextureSize(64, 32);
      leg4.mirror = true;
      setRotation(leg4, 0F, 0F, 0F);
      body = new ModelRenderer(this, 35, 0);
      body.addBox(0F, 0F, 0F, 11, 10, 22);
      body.setRotationPoint(-5F, 2F, -1.5F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      tail = new ModelRenderer(this, 0, 23);
      tail.addBox(0F, 0F, 0F, 3, 2, 5);
      tail.setRotationPoint(-1F, 3F, 19F);
      tail.setTextureSize(64, 32);
      tail.mirror = true;
      setRotation(tail, 0.8922867F, 0F, 0F);
      neck = new ModelRenderer(this, 16, 23);
      neck.addBox(0F, -11F, 0F, 4, 11, 4);
      neck.setRotationPoint(-1.5F, 5F, -1F);
      neck.setTextureSize(64, 32);
      neck.mirror = true;
      setRotation(neck, 0.5576792F, 0F, 0F);
      head = new ModelRenderer(this, 0, 0);
      head.addBox(1F, 0F, -8F, 5, 5, 9);
      head.setRotationPoint(-3.033333F, -7F, -3F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0.1487144F, 0F, 0F);
      left_horn1 = new ModelRenderer(this, 0, 15);
      left_horn1.addBox(0F, -5F, 0F, 1, 5, 1);
      left_horn1.setRotationPoint(2F, -6F, -4F);
      left_horn1.setTextureSize(64, 32);
      left_horn1.mirror = true;
      setRotation(left_horn1, 0F, 0.0743572F, 1.152537F);
      left_horn2 = new ModelRenderer(this, 0, 15);
      left_horn2.addBox(0F, -5F, 0F, 1, 5, 1);
      left_horn2.setRotationPoint(6F, -8F, -4F);
      left_horn2.setTextureSize(64, 32);
      left_horn2.mirror = true;
      setRotation(left_horn2, 0F, 0.4833219F, 1.07818F);
      left_horn3 = new ModelRenderer(this, 0, 15);
      left_horn3.addBox(0F, 0F, 0F, 1, 4, 1);
      left_horn3.setRotationPoint(11F, -10F, -5F);
      left_horn3.setTextureSize(64, 32);
      left_horn3.mirror = true;
      setRotation(left_horn3, 0F, -1.412787F, 2.13777F);
      left_horn4 = new ModelRenderer(this, 0, 15);
      left_horn4.addBox(0F, 0F, 0F, 1, 4, 1);
      left_horn4.setRotationPoint(7F, -8F, -4F);
      left_horn4.setTextureSize(64, 32);
      left_horn4.mirror = true;
      setRotation(left_horn4, 0F, -1.412787F, 2.13777F);
      left_horn5 = new ModelRenderer(this, 0, 15);
      left_horn5.addBox(0F, 0F, 0F, 1, 4, 1);
      left_horn5.setRotationPoint(4F, -6.5F, -3F);
      left_horn5.setTextureSize(64, 32);
      left_horn5.mirror = true;
      setRotation(left_horn5, 0F, -1.412787F, 2.13777F);
      right_horn1 = new ModelRenderer(this, 0, 15);
      right_horn1.addBox(-1F, -5F, 0F, 1, 5, 1);
      right_horn1.setRotationPoint(-1F, -6F, -4F);
      right_horn1.setTextureSize(64, 32);
      right_horn1.mirror = true;
      setRotation(right_horn1, 0F, -0.074351F, -1.152546F);
      right_horn2 = new ModelRenderer(this, 0, 15);
      right_horn2.addBox(-1F, -5F, 0F, 1, 5, 1);
      right_horn2.setRotationPoint(-5F, -8F, -4F);
      right_horn2.setTextureSize(64, 32);
      right_horn2.mirror = true;
      setRotation(right_horn2, 0F, -0.4833166F, -1.078177F);
      right_horn3 = new ModelRenderer(this, 0, 15);
      right_horn3.addBox(-1F, 0F, 0F, 1, 4, 1);
      right_horn3.setRotationPoint(-10F, -10F, -5F);
      right_horn3.setTextureSize(64, 32);
      right_horn3.mirror = true;
      setRotation(right_horn3, 0F, 1.412792F, -2.137767F);
      right_horn4 = new ModelRenderer(this, 0, 15);
      right_horn4.addBox(-1F, 0F, 0F, 1, 4, 1);
      right_horn4.setRotationPoint(-6F, -8F, -4F);
      right_horn4.setTextureSize(64, 32);
      right_horn4.mirror = true;
      setRotation(right_horn4, 0F, 1.412792F, -2.144748F);
      right_horn5 = new ModelRenderer(this, 0, 15);
      right_horn5.addBox(-1F, 0F, 0F, 1, 4, 1);
      right_horn5.setRotationPoint(-3F, -6.5F, -3F);
      right_horn5.setTextureSize(64, 32);
      right_horn5.mirror = true;
      setRotation(right_horn5, 0F, 1.412792F, -2.137767F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    leg1.render(f5);
    leg2.render(f5);
    leg3.render(f5);
    leg4.render(f5);
    body.render(f5);
    tail.render(f5);
    neck.render(f5);
    head.render(f5);
    left_horn1.render(f5);
    left_horn2.render(f5);
    left_horn3.render(f5);
    left_horn4.render(f5);
    left_horn5.render(f5);
    right_horn1.render(f5);
    right_horn2.render(f5);
    right_horn3.render(f5);
    right_horn4.render(f5);
    right_horn5.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {

		 leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
	        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
	        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	  super.setRotationAngles(f, f1, f2, f3, f4, f5);
  }

}
