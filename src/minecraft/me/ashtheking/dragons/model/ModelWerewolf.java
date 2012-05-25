package me.ashtheking.dragons.model;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBiped;
import net.minecraft.src.ModelRenderer;
import net.minecraft.src.mod_Dragon;

public class ModelWerewolf extends ModelBiped {
	// fields
	ModelRenderer wereMane;
	ModelRenderer wereTail;
	ModelRenderer wereRightEar;
	ModelRenderer wereLeftEar;
	ModelRenderer wereSnout;

	public ModelWerewolf() {
		super();
		textureWidth = 64;
		textureHeight = 32;
		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-3F, -3F, -2F, 6, 6, 4);
		bipedHead.setRotationPoint(-1F, 1F, -5F);
		bipedHead.setTextureSize(64, 32);
		bipedHead.mirror = true;
		setRotation(bipedHead, 0F, 0F, 0F);
		bipedHeadwear = new ModelRenderer(this, 0, 0);
		bipedHeadwear.addBox(0, 0, 0, 0, 0, 0);
		bipedHeadwear.setRotationPoint(0, -2, 0);
		bipedHeadwear.setTextureSize(64, 32);
		bipedHeadwear.mirror = true;
		setRotation(bipedHeadwear, 0F, 0F, 0F);
		bipedBody = new ModelRenderer(this, 27, 14);
		bipedBody.addBox(-4F, -2F, -3F, 6, 12, 6);
		bipedBody.setRotationPoint(0F, 4F, 2F);
		bipedBody.setTextureSize(64, 32);
		bipedBody.mirror = true;
		setRotation(bipedBody, 0.5235988F, 0F, 0F);
		wereMane = new ModelRenderer(this, 21, 0);
		wereMane.addBox(-4F, -3F, -3F, 8, 6, 7);
		wereMane.setRotationPoint(-1F, 2F, 0F);
		wereMane.setTextureSize(64, 32);
		wereMane.mirror = true;
		setRotation(wereMane, 1.570796F, 0F, 0F);
		bipedLeftLeg = new ModelRenderer(this, 0, 19);
		bipedLeftLeg.addBox(-1F, 0F, 2F, 3, 10, 3);
		bipedLeftLeg.setRotationPoint(-4F, 12F, 6F);
		bipedLeftLeg.setTextureSize(64, 32);
		bipedLeftLeg.mirror = true;
		setRotation(bipedLeftLeg, 0F, 0F, 0F);
		bipedRightLeg = new ModelRenderer(this, 0, 19);
		bipedRightLeg.addBox(-1F, 0F, 2F, 3, 10, 3);
		bipedRightLeg.setRotationPoint(1F, 12F, 6F);
		bipedRightLeg.setTextureSize(64, 32);
		bipedRightLeg.mirror = true;
		setRotation(bipedRightLeg, 0F, 0F, 0F);
		wereTail = new ModelRenderer(this, 13, 20);
		wereTail.addBox(-1F, 0F, -1F, 2, 8, 2);
		wereTail.setRotationPoint(-1F, 10F, 8F);
		wereTail.setTextureSize(64, 32);
		wereTail.mirror = true;
		setRotation(wereTail, 1.130069F, 0F, 0F);
		wereRightEar = new ModelRenderer(this, 19, 14);
		wereRightEar.addBox(-3F, -5F, 0F, 2, 2, 1);
		wereRightEar.setRotationPoint(-1F, 1F, -4F);
		wereRightEar.setTextureSize(64, 32);
		wereRightEar.mirror = true;
		setRotation(wereRightEar, 0F, 0F, 0F);
		wereLeftEar = new ModelRenderer(this, 19, 14);
		wereLeftEar.addBox(1F, -5F, 0F, 2, 2, 1);
		wereLeftEar.setRotationPoint(-1F, 1F, -4F);
		wereLeftEar.setTextureSize(64, 32);
		wereLeftEar.mirror = true;
		setRotation(wereLeftEar, 0F, 0F, 0F);
		wereSnout = new ModelRenderer(this, 0, 10);
		wereSnout.addBox(-2F, 0F, -5F, 3, 3, 5);
		wereSnout.setRotationPoint(-0.5F, 1F, -5F);
		wereSnout.setTextureSize(64, 32);
		wereSnout.mirror = true;
		setRotation(wereSnout, 0F, 0F, 0F);
		bipedLeftArm = new ModelRenderer(this, 0, 19);
		bipedLeftArm.addBox(-2F, 0F, -1F, 3, 10, 3);
		bipedLeftArm.setRotationPoint(3F, 1F, -1F);
		bipedLeftArm.setTextureSize(64, 32);
		bipedLeftArm.mirror = true;
		setRotation(bipedLeftArm, 0F, 0F, 0F);
		bipedRightArm = new ModelRenderer(this, 0, 19);
		bipedRightArm.addBox(-3F, 0F, -1F, 3, 10, 3);
		bipedRightArm.setRotationPoint(-6F, 1F, -1F);
		bipedRightArm.setTextureSize(64, 32);
		bipedRightArm.mirror = true;
		setRotation(bipedRightArm, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		wereMane.render(f5);
		wereTail.render(f5);
		wereRightEar.render(f5);
		wereLeftEar.render(f5);
		wereSnout.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
		setRotation(bipedHead, 0F, 0F, 0F);
		bipedBody.setRotationPoint(0F, 4F, 2F);
		setRotation(bipedBody, 0.5235988F, 0F, 0F);
	}

}
