package me.ashtheking.dragons.model;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelQuadruped;
import net.minecraft.src.ModelRenderer;

public class ModelMammoth extends ModelQuadruped
{
	//fields
	ModelRenderer tusk2;
	ModelRenderer tusk1;
	ModelRenderer snout;
	ModelRenderer back;

	public ModelMammoth()
	{
		super(6, 0.0F);
		textureWidth = 128;
		textureHeight = 32;

		tusk2 = new ModelRenderer(this, 76, 0);
		tusk2.addBox(0F, 0F, 0F, 1, 1, 10);
		tusk2.setRotationPoint(3F, 10F, -23F);
		tusk2.setTextureSize(128, 32);
		tusk2.mirror = true;
		setRotation(tusk2, 0F, 0F, 0F);
		tusk1 = new ModelRenderer(this, 76, 0);
		tusk1.addBox(0F, 0F, 0F, 1, 1, 10);
		tusk1.setRotationPoint(-4F, 10F, -23F);
		tusk1.setTextureSize(128, 32);
		tusk1.mirror = true;
		setRotation(tusk1, 0F, 0F, 0F);
		snout = new ModelRenderer(this, 0, 0);
		snout.addBox(0F, 0F, 0F, 4, 15, 4);
		snout.setRotationPoint(-2F, 4F, -18F);
		snout.setTextureSize(128, 32);
		snout.mirror = true;
		setRotation(snout, 0F, 0F, 0F);
		head = new ModelRenderer(this, 43, 0);
		head.addBox(-4F, -4F, -8F, 8, 11, 8);
		head.setRotationPoint(0F, 6F, -6F);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 0);
		body.addBox(-5F, -10F, -7F, 10, 16, 11);
		body.setRotationPoint(0F, 11F, 3F);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 1.570796F, 0F, 0F);
		
		leg1 = new ModelRenderer(this, 42, 20);
		leg1.addBox(-2F, 0F, -2F, 4, 6, 4);
		leg1.setRotationPoint(-3F, 18F, 7F);
		leg1.setTextureSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		
		leg2 = new ModelRenderer(this, 42, 20);
		leg2.addBox(-2F, 0F, -2F, 4, 6, 4);
		leg2.setRotationPoint(3F, 18F, 7F);
		leg2.setTextureSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		
		leg3 = new ModelRenderer(this, 42, 20);
		leg3.addBox(-2F, 0F, -2F, 4, 6, 4);
		leg3.setRotationPoint(-3F, 18F, -5F);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		
		leg4 = new ModelRenderer(this, 42, 20);
		leg4.addBox(-2F, 0F, -2F, 4, 6, 4);
		leg4.setRotationPoint(3F, 18F, -5F);
		leg4.setTextureSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		
		back = new ModelRenderer(this, 0, 0);
		back.addBox(0F, 0F, 0F, 5, 8, 16);
		back.setRotationPoint(-2F, 4F, -7F);
		back.setTextureSize(128, 32);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		tusk2.render(f5);
		tusk1.render(f5);
		snout.render(f5);
		head.render(f5);
		body.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		back.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
		head.rotateAngleX = 0F;
		head.rotateAngleY = 0F;
		 	leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
	        
	        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
	        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	}

}
