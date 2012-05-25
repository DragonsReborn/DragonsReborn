package me.ashtheking.dragons.model;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelDragonFlying extends ModelBase {
	// fields
	ModelRenderer tail2;
	ModelRenderer tail1;
	ModelRenderer body;
	ModelRenderer frontleg2;
	ModelRenderer frontleg1;
	ModelRenderer backlegtip2;
	ModelRenderer backleg2;
	ModelRenderer backlegtip1;
	ModelRenderer backleg1;
	ModelRenderer lowerJaw;
	ModelRenderer upperJaw;
	ModelRenderer head;
	ModelRenderer neck1;
	ModelRenderer neck2;
	ModelRenderer neck3;
	ModelRenderer wing2;
	ModelRenderer wingtip2;
	ModelRenderer wing1;
	ModelRenderer wingtip1;

	public ModelDragonFlying() {
		textureWidth = 64;
		textureHeight = 64;

		tail2 = new ModelRenderer(this, 45, 34);
		tail2.addBox(0F, 0F, 0F, 2, 2, 6);
		tail2.setRotationPoint(1F, -1F, 32F);
		tail2.setTextureSize(64, 32);
		tail2.mirror = true;
		setRotation(tail2, 0.1396263F, 0F, 0F);
		tail1 = new ModelRenderer(this, 45, 34);
		tail1.addBox(0F, 0F, 0F, 2, 2, 6);
		tail1.setRotationPoint(1F, -2F, 27F);
		tail1.setTextureSize(64, 32);
		tail1.mirror = true;
		setRotation(tail1, -0.1396263F, 0F, 0F);
		body = new ModelRenderer(this, 17, 0);
		body.addBox(0F, 0F, 0F, 6, 4, 14);
		body.setRotationPoint(-1F, -2F, 14F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		frontleg2 = new ModelRenderer(this, 19, 47);
		frontleg2.addBox(0F, 0F, 0F, 2, 5, 2);
		frontleg2.setRotationPoint(3F, 0F, 14F);
		frontleg2.setTextureSize(64, 32);
		frontleg2.mirror = true;
		setRotation(frontleg2, 0F, 0F, 0F);
		frontleg1 = new ModelRenderer(this, 19, 47);
		frontleg1.addBox(0F, 0F, 0F, 2, 5, 2);
		frontleg1.setRotationPoint(-1F, 0F, 14F);
		frontleg1.setTextureSize(64, 32);
		frontleg1.mirror = true;
		setRotation(frontleg1, 0F, 0F, 0F);
		backlegtip2 = new ModelRenderer(this, 0, 47);
		backlegtip2.addBox(0F, 0F, 0F, 3, 3, 6);
		backlegtip2.setRotationPoint(3F, 4F, 24F);
		backlegtip2.setTextureSize(64, 32);
		backlegtip2.mirror = true;
		setRotation(backlegtip2, 0F, 0F, 0F);
		backleg2 = new ModelRenderer(this, 45, 25);
		backleg2.addBox(0F, 0F, 0F, 3, 5, 3);
		backleg2.setRotationPoint(3F, 0F, 24F);
		backleg2.setTextureSize(64, 32);
		backleg2.mirror = true;
		setRotation(backleg2, 0F, 0F, 0F);
		backlegtip1 = new ModelRenderer(this, 0, 47);
		backlegtip1.addBox(0F, 0F, 0F, 3, 3, 6);
		backlegtip1.setRotationPoint(-2F, 4F, 24F);
		backlegtip1.setTextureSize(64, 32);
		backlegtip1.mirror = true;
		setRotation(backlegtip1, 0F, 0F, 0F);
		backleg1 = new ModelRenderer(this, 45, 25);
		backleg1.addBox(0F, 0F, 0F, 3, 5, 3);
		backleg1.setRotationPoint(-2F, 0F, 24F);
		backleg1.setTextureSize(64, 32);
		backleg1.mirror = true;
		setRotation(backleg1, 0F, 0F, 0F);
		lowerJaw = new ModelRenderer(this, 0, 6);
		lowerJaw.addBox(0F, 0F, 0F, 4, 1, 4);
		lowerJaw.setRotationPoint(0F, 0F, 0F);
		lowerJaw.setTextureSize(64, 32);
		lowerJaw.mirror = true;
		setRotation(lowerJaw, 0F, 0F, 0F);
		upperJaw = new ModelRenderer(this, 0, 0);
		upperJaw.addBox(0F, 0F, 0F, 4, 1, 4);
		upperJaw.setRotationPoint(0F, -1F, 0F);
		upperJaw.setTextureSize(64, 32);
		upperJaw.mirror = true;
		setRotation(upperJaw, 0F, 0F, 0F);
		head = new ModelRenderer(this, 0, 12);
		head.addBox(0F, 0F, 0F, 4, 4, 4);
		head.setRotationPoint(0F, -3F, 3F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		neck1 = new ModelRenderer(this, 45, 19);
		neck1.addBox(0F, 0F, 0F, 2, 2, 3);
		neck1.setRotationPoint(1F, -1F, 6F);
		neck1.setTextureSize(64, 32);
		neck1.mirror = true;
		setRotation(neck1, 0F, 0F, 0F);
		neck2 = new ModelRenderer(this, 45, 19);
		neck2.addBox(0F, 0F, 0F, 2, 2, 3);
		neck2.setRotationPoint(1F, -1F, 9F);
		neck2.setTextureSize(64, 32);
		neck2.mirror = true;
		setRotation(neck2, 0F, 0F, 0F);
		neck3 = new ModelRenderer(this, 45, 19);
		neck3.addBox(0F, 0F, 0F, 2, 2, 3);
		neck3.setRotationPoint(1F, -1F, 12F);
		neck3.setTextureSize(64, 32);
		neck3.mirror = true;
		setRotation(neck3, 0F, 0F, 0F);
		wing2 = new ModelRenderer(this, 0, 34);
		wing2.addBox(0F, 0F, 0F, 10, 0, 12);
		wing2.setRotationPoint(4F, -1.5F, 15F);
		wing2.setTextureSize(64, 32);
		wing2.mirror = true;
		setRotation(wing2, 0F, 0F, -0.3316126F);
		wingtip2 = new ModelRenderer(this, 0, 21);
		wingtip2.addBox(0F, 0F, 0F, 10, 0, 12);
		wingtip2.setRotationPoint(13F, -5F, 15F);
		wingtip2.setTextureSize(64, 32);
		wingtip2.mirror = true;
		setRotation(wingtip2, 0F, 0F, 0.3490659F);
		wing1 = new ModelRenderer(this, 0, 21);
		wing1.addBox(0F, 0F, 0F, 10, 0, 12);
		wing1.setRotationPoint(-10F, -5F, 15F);
		wing1.setTextureSize(64, 32);
		wing1.mirror = true;
		setRotation(wing1, 0F, 0F, 0.3490659F);
		wingtip1 = new ModelRenderer(this, 0, 34);
		wingtip1.addBox(0F, 0F, 0F, 10, 0, 12);
		wingtip1.setRotationPoint(-19F, -2F, 15F);
		wingtip1.setTextureSize(64, 32);
		wingtip1.mirror = true;
		setRotation(wingtip1, 0F, 0F, -0.3316126F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		tail2.render(f5);
		tail1.render(f5);
		body.render(f5);
		frontleg2.render(f5);
		frontleg1.render(f5);
		backlegtip2.render(f5);
		backleg2.render(f5);
		backlegtip1.render(f5);
		backleg1.render(f5);
		lowerJaw.render(f5);
		upperJaw.render(f5);
		head.render(f5);
		neck1.render(f5);
		neck2.render(f5);
		neck3.render(f5);
		wing2.render(f5);
		wingtip2.render(f5);
		wing1.render(f5);
		wingtip1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
//        wing1.rotateAngleZ = f2;
//        wing2.rotateAngleZ = f2;
//        wingtip2.rotateAngleZ = -f2;
//        wingtip1.rotateAngleZ = -f2;
	}

}
