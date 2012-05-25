package me.ashtheking.dragons.model;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelSlaughterfish extends ModelBase {
	// fields
	ModelRenderer vin1_right;
	ModelRenderer vin2_right;
	ModelRenderer tailvin1;
	ModelRenderer tailvin2;
	ModelRenderer tailvin3;
	ModelRenderer backvin;
	ModelRenderer backvin2;
	ModelRenderer backvin3;
	ModelRenderer vin1_left;
	ModelRenderer vin2_left;
	ModelRenderer head;
	ModelRenderer mouth1;
	ModelRenderer Mouth2;
	ModelRenderer Body;
	ModelRenderer Body2;
	ModelRenderer tail1;
	ModelRenderer tail2;

	public ModelSlaughterfish() {
		textureWidth = 128;
		textureHeight = 64;

		vin1_right = new ModelRenderer(this, 0, 32);
		vin1_right.addBox(0F, 0F, 0F, 0, 3, 1);
		vin1_right.setRotationPoint(1F, 3F, -3F);
		vin1_right.setTextureSize(64, 32);
		vin1_right.mirror = true;
		setRotation(vin1_right, 1.152537F, 0.1115358F, -0.2974289F);
		vin2_right = new ModelRenderer(this, 0, 32);
		vin2_right.addBox(0F, 0F, 0F, 0, 2, 1);
		vin2_right.setRotationPoint(-1F, 3F, 1F);
		vin2_right.setTextureSize(64, 32);
		vin2_right.mirror = true;
		setRotation(vin2_right, 1.33843F, -0.185895F, 0.185895F);
		tailvin1 = new ModelRenderer(this, 0, 32);
		tailvin1.addBox(0F, 0F, 0F, 0, 3, 1);
		tailvin1.setRotationPoint(0F, 3F, 10F);
		tailvin1.setTextureSize(64, 32);
		tailvin1.mirror = true;
		setRotation(tailvin1, 2.268928F, 0F, 0F);
		tailvin2 = new ModelRenderer(this, 0, 32);
		tailvin2.addBox(0F, 0F, 0F, 0, 3, 1);
		tailvin2.setRotationPoint(0F, 3F, 10F);
		tailvin2.setTextureSize(64, 32);
		tailvin2.mirror = true;
		setRotation(tailvin2, 1.937315F, 0F, 0F);
		tailvin3 = new ModelRenderer(this, 0, 32);
		tailvin3.addBox(0F, 0F, 0F, 0, 3, 1);
		tailvin3.setRotationPoint(0F, 3F, 10F);
		tailvin3.setTextureSize(64, 32);
		tailvin3.mirror = true;
		setRotation(tailvin3, 1.58825F, 0F, 0F);
		backvin = new ModelRenderer(this, 3, 31);
		backvin.addBox(0F, 0F, 0F, 0, 1, 4);
		backvin.setRotationPoint(0F, 0F, -2F);
		backvin.setTextureSize(64, 32);
		backvin.mirror = true;
		setRotation(backvin, 0.4089647F, 0F, 0F);
		backvin2 = new ModelRenderer(this, 3, 31);
		backvin2.addBox(0F, 0F, 0F, 0, 1, 4);
		backvin2.setRotationPoint(0F, 0F, 0F);
		backvin2.setTextureSize(64, 32);
		backvin2.mirror = true;
		setRotation(backvin2, 0.4089647F, 0F, 0F);
		backvin3 = new ModelRenderer(this, 12, 30);
		backvin3.addBox(0F, 0F, 0F, 0, 1, 5);
		backvin3.setRotationPoint(0F, 0F, 0F);
		backvin3.setTextureSize(64, 32);
		backvin3.mirror = true;
		setRotation(backvin3, 0.1487144F, 0F, 0F);
		vin1_left = new ModelRenderer(this, 0, 32);
		vin1_left.addBox(0F, 0F, 0F, 0, 3, 1);
		vin1_left.setRotationPoint(-1F, 3F, -3F);
		vin1_left.setTextureSize(64, 32);
		vin1_left.mirror = true;
		setRotation(vin1_left, 1.152537F, -0.111544F, 0.2974216F);
		vin2_left = new ModelRenderer(this, 0, 32);
		vin2_left.addBox(0F, 0F, 0F, 0, 2, 1);
		vin2_left.setRotationPoint(1F, 3F, 1F);
		vin2_left.setTextureSize(64, 32);
		vin2_left.mirror = true;
		setRotation(vin2_left, 1.33843F, 0.1858931F, -0.1858931F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(0F, 0F, 0F, 1, 2, 3);
		head.setRotationPoint(-0.5F, 1.333333F, -6F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		mouth1 = new ModelRenderer(this, 8, 0);
		mouth1.addBox(0F, 0F, 0F, 1, 1, 6);
		mouth1.setRotationPoint(-0.5333334F, 1.666667F, -9F);
		mouth1.setTextureSize(64, 32);
		mouth1.mirror = true;
		setRotation(mouth1, 0F, 0F, 0F);
		Mouth2 = new ModelRenderer(this, 22, 0);
		Mouth2.addBox(0F, 0F, -8F, 1, 1, 8);
		Mouth2.setRotationPoint(-0.5F, 2.5F, -2F);
		Mouth2.setTextureSize(64, 32);
		Mouth2.mirror = true;
		setRotation(Mouth2, 0.0743572F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 11);
		Body.addBox(0F, 0F, 0F, 1, 5, 8);
		Body.setRotationPoint(-0.4666667F, 0F, -3F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Body2 = new ModelRenderer(this, 20, 11);
		Body2.addBox(0F, 0F, 0F, 2, 3, 10);
		Body2.setRotationPoint(-1F, 1F, -4F);
		Body2.setTextureSize(64, 32);
		Body2.mirror = true;
		setRotation(Body2, 0F, 0F, 0F);
		tail1 = new ModelRenderer(this, 0, 25);
		tail1.addBox(0F, 0F, 0F, 1, 2, 3);
		tail1.setRotationPoint(-0.5F, 1.5F, 6F);
		tail1.setTextureSize(64, 32);
		tail1.mirror = true;
		setRotation(tail1, 0F, 0F, 0F);
		tail2 = new ModelRenderer(this, 9, 25);
		tail2.addBox(0F, 0F, 0F, 1, 1, 3);
		tail2.setRotationPoint(-0.5F, 2F, 8F);
		tail2.setTextureSize(64, 32);
		tail2.mirror = true;
		setRotation(tail2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		vin1_right.render(f5);
		vin2_right.render(f5);
		tailvin1.render(f5);
		tailvin2.render(f5);
		tailvin3.render(f5);
		backvin.render(f5);
		backvin2.render(f5);
		backvin3.render(f5);
		vin1_left.render(f5);
		vin2_left.render(f5);
		head.render(f5);
		mouth1.render(f5);
		Mouth2.render(f5);
		Body.render(f5);
		Body2.render(f5);
		tail1.render(f5);
		tail2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
	}

}
