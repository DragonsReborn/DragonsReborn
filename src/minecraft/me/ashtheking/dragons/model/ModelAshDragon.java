// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

   package me.ashtheking.dragons.model;

   import me.ashtheking.dragons.mob.Dragon;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            ModelBase, ModelRenderer, Dragon, EntityLiving, 
//            Entity

   public class ModelAshDragon extends ModelBase
   {
   
      private ModelRenderer modelHead;
      private ModelRenderer modelNeck;
      private ModelRenderer modelJaw;
      private ModelRenderer modelBody;
      private ModelRenderer modelRearLeg;
      private ModelRenderer modelFrontLeg;
      private ModelRenderer modelRearLegtip;
      private ModelRenderer modelFrontLegtip;
      private ModelRenderer modelRearFoot;
      private ModelRenderer modelFrontFoot;
      private ModelRenderer modelWing;
      private ModelRenderer modelWingtip;
      private float field_40317_s;
   
      public ModelAshDragon(float f)
      {
         textureWidth = 256;
         textureHeight = 256;
         setTextureOffset("body.body", 0, 0);
         setTextureOffset("wing.skin", -56, 88);
         setTextureOffset("wingtip.skin", -56, 144);
         setTextureOffset("rearleg.main", 0, 0);
         setTextureOffset("rearfoot.main", 112, 0);
         setTextureOffset("rearlegtip.main", 196, 0);
         setTextureOffset("head.upperhead", 112, 30);
         setTextureOffset("wing.bone", 112, 88);
         setTextureOffset("head.upperlip", 176, 44);
         setTextureOffset("jaw.jaw", 176, 65);
         setTextureOffset("frontleg.main", 112, 104);
         setTextureOffset("wingtip.bone", 112, 136);
         setTextureOffset("frontfoot.main", 144, 104);
         setTextureOffset("neck.box", 192, 104);
         setTextureOffset("frontlegtip.main", 226, 138);
         setTextureOffset("body.scale", 220, 53);
         setTextureOffset("head.scale", 0, 0);
         setTextureOffset("neck.scale", 48, 0);
         setTextureOffset("head.nostril", 112, 0);
         float f1 = -16F;
         modelHead = new ModelRenderer(this, "head");
         modelHead.addBox("upperlip", -6F, -1F, -8F + f1, 12, 5, 16);
         modelHead.addBox("upperhead", -8F, -8F, 6F + f1, 16, 16, 16);
         modelHead.mirror = true;
         modelHead.addBox("scale", -5F, -12F, 12F + f1, 2, 4, 6);
         modelHead.addBox("nostril", -5F, -3F, -6F + f1, 2, 2, 4);
         modelHead.mirror = false;
         modelHead.addBox("scale", 3F, -12F, 12F + f1, 2, 4, 6);
         modelHead.addBox("nostril", 3F, -3F, -6F + f1, 2, 2, 4);
         modelJaw = new ModelRenderer(this, "jaw");
         modelJaw.setRotationPoint(0.0F, 4F, 8F + f1);
         modelJaw.addBox("jaw", -6F, 0.0F, -16F, 12, 4, 16);
         modelHead.addChild(modelJaw);
         modelNeck = new ModelRenderer(this, "neck");
         modelNeck.addBox("box", -5F, -5F, -5F, 10, 10, 10);
         modelNeck.addBox("scale", -1F, -9F, -3F, 2, 4, 6);
         modelBody = new ModelRenderer(this, "body");
         modelBody.setRotationPoint(0.0F, 4F, 8F);
         modelBody.addBox("body", -12F, 0.0F, -16F, 24, 24, 64);
         modelBody.addBox("scale", -1F, -6F, -10F, 2, 6, 12);
         modelBody.addBox("scale", -1F, -6F, 10F, 2, 6, 12);
         modelBody.addBox("scale", -1F, -6F, 30F, 2, 6, 12);
         modelWing = new ModelRenderer(this, "wing");
         modelWing.setRotationPoint(-12F, 5F, 2.0F);
         modelWing.addBox("bone", -56F, -4F, -4F, 56, 8, 8);
         modelWing.addBox("skin", -56F, 0.0F, 2.0F, 56, 0, 56);
         modelWingtip = new ModelRenderer(this, "wingtip");
         modelWingtip.setRotationPoint(-56F, 0.0F, 0.0F);
         modelWingtip.addBox("bone", -56F, -2F, -2F, 56, 4, 4);
         modelWingtip.addBox("skin", -56F, 0.0F, 2.0F, 56, 0, 56);
         modelWing.addChild(modelWingtip);
         modelFrontLeg = new ModelRenderer(this, "frontleg");
         modelFrontLeg.setRotationPoint(-12F, 20F, 2.0F);
         modelFrontLeg.addBox("main", -4F, -4F, -4F, 8, 24, 8);
         modelFrontLegtip = new ModelRenderer(this, "frontlegtip");
         modelFrontLegtip.setRotationPoint(0.0F, 20F, -1F);
         modelFrontLegtip.addBox("main", -3F, -1F, -3F, 6, 24, 6);
         modelFrontLeg.addChild(modelFrontLegtip);
         modelFrontFoot = new ModelRenderer(this, "frontfoot");
         modelFrontFoot.setRotationPoint(0.0F, 23F, 0.0F);
         modelFrontFoot.addBox("main", -4F, 0.0F, -12F, 8, 4, 16);
         modelFrontLegtip.addChild(modelFrontFoot);
         modelRearLeg = new ModelRenderer(this, "rearleg");
         modelRearLeg.setRotationPoint(-16F, 16F, 42F);
         modelRearLeg.addBox("main", -8F, -4F, -8F, 16, 32, 16);
         modelRearLegtip = new ModelRenderer(this, "rearlegtip");
         modelRearLegtip.setRotationPoint(0.0F, 32F, -4F);
         modelRearLegtip.addBox("main", -6F, -2F, 0.0F, 12, 32, 12);
         modelRearLeg.addChild(modelRearLegtip);
         modelRearFoot = new ModelRenderer(this, "rearfoot");
         modelRearFoot.setRotationPoint(0.0F, 31F, 4F);
         modelRearFoot.addBox("main", -9F, 0.0F, -20F, 18, 6, 24);
         modelRearLegtip.addChild(modelRearFoot);
      }
   
      public void setLivingAnimations(EntityLiving entityliving, float f, float f1, float f2)
      {
         field_40317_s = f2;
      }
   
      public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
      {
         GL11.glPushMatrix();
         Dragon entitydragon = (Dragon)entity;
         float f6 = entitydragon.newVar + (entitydragon.oldVar - entitydragon.newVar) * field_40317_s;
         modelJaw.rotateAngleX = (float)(Math.sin(f6 * 3.141593F * 2.0F) + 1.0D) * 0.2F;
         float f7 = (float)(Math.sin(f6 * 3.141593F * 2.0F - 1.0F) + 1.0D);
         f7 = (f7 * f7 * 1.0F + f7 * 2.0F) * 0.05F;
         GL11.glTranslatef(0.0F, f7 - 2.0F, -3F);
         GL11.glRotatef(f7 * 2.0F, 1.0F, 0.0F, 0.0F);
         float f8 = -30F;
         float f9 = 22F;
         float f10 = 0.0F;
         float f11 = 1.5F;
         double ad[] = entitydragon.calculateSomething(6, field_40317_s);
         float f12 = func_40307_a(entitydragon.calculateSomething(5, field_40317_s)[0] - entitydragon.calculateSomething(10, field_40317_s)[0]);
         float f13 = func_40307_a(entitydragon.calculateSomething(5, field_40317_s)[0] + (double)(f12 / 2.0F));
         f8 += 2.0F;
         float f14 = 0.0F;
         float f15 = f6 * 3.141593F * 2.0F;
         f8 = 20F;
         f9 = -12F;
         for(int i = 0; i < 5; i++)
         {
            double ad3[] = entitydragon.calculateSomething(5 - i, field_40317_s);
            f14 = (float)Math.cos((float)i * 0.45F + f15) * 0.15F;
            modelNeck.rotateAngleY = ((func_40307_a(ad3[0] - ad[0]) * 3.141593F) / 180F) * f11;
            modelNeck.rotateAngleX = f14 + (((float)(ad3[1] - ad[1]) * 3.141593F) / 180F) * f11 * 5F;
            modelNeck.rotateAngleZ = ((-func_40307_a(ad3[0] - (double)f13) * 3.141593F) / 180F) * f11;
            modelNeck.rotationPointY = f8;
            modelNeck.rotationPointZ = f9;
            modelNeck.rotationPointX = f10;
            f8 = (float)((double)f8 + Math.sin(modelNeck.rotateAngleX) * 10D);
            f9 = (float)((double)f9 - Math.cos(modelNeck.rotateAngleY) * Math.cos(modelNeck.rotateAngleX) * 10D);
            f10 = (float)((double)f10 - Math.sin(modelNeck.rotateAngleY) * Math.cos(modelNeck.rotateAngleX) * 10D);
            modelNeck.render(f5);
         }
      
         modelHead.rotationPointY = f8;
         modelHead.rotationPointZ = f9;
         modelHead.rotationPointX = f10;
         double ad1[] = entitydragon.calculateSomething(0, field_40317_s);
         modelHead.rotateAngleY = ((func_40307_a(ad1[0] - ad[0]) * 3.141593F) / 180F) * 1.0F;
         modelHead.rotateAngleZ = ((-func_40307_a(ad1[0] - (double)f13) * 3.141593F) / 180F) * 1.0F;
         modelHead.render(f5);
         GL11.glPushMatrix();
         GL11.glTranslatef(0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-f12 * f11 * 1.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.0F, -1F, 0.0F);
         modelBody.rotateAngleZ = 0.0F;
         modelBody.render(f5);
         for(int j = 0; j < 2; j++)
         {
            GL11.glEnable(2884 /*GL_CULL_FACE*/);
            float f16 = f6 * 3.141593F * 2.0F;
            modelWing.rotateAngleX = 0.125F - (float)Math.cos(f16) * 0.2F;
            modelWing.rotateAngleY = 0.25F;
            modelWing.rotateAngleZ = (float)(Math.sin(f16) + 0.125D) * 0.8F;
            modelWingtip.rotateAngleZ = -(float)(Math.sin(f16 + 2.0F) + 0.5D) * 0.75F;
            modelRearLeg.rotateAngleX = 1.0F + f7 * 0.1F;
            modelRearLegtip.rotateAngleX = 0.5F + f7 * 0.1F;
            modelRearFoot.rotateAngleX = 0.75F + f7 * 0.1F;
            modelFrontLeg.rotateAngleX = 1.3F + f7 * 0.1F;
            modelFrontLegtip.rotateAngleX = -0.5F - f7 * 0.1F;
            modelFrontFoot.rotateAngleX = 0.75F + f7 * 0.1F;
            modelWing.render(f5);
            modelFrontLeg.render(f5);
            modelRearLeg.render(f5);
            GL11.glScalef(-1F, 1.0F, 1.0F);
            if(j == 0)
            {
               GL11.glCullFace(1028 /*GL_FRONT*/);
            }
         }
      
         GL11.glPopMatrix();
         GL11.glCullFace(1029 /*GL_BACK*/);
         GL11.glDisable(2884 /*GL_CULL_FACE*/);
         f14 = -(float)Math.sin(f6 * 3.141593F * 2.0F) * 0.0F;
         f15 = f6 * 3.141593F * 2.0F;
         f8 = 10F;
         f9 = 60F;
         f10 = 0.0F;
         ad = entitydragon.calculateSomething(11, field_40317_s);
         for(int k = 0; k < 12; k++)
         {
            double ad2[] = entitydragon.calculateSomething(12 + k, field_40317_s);
            f14 = (float)((double)f14 + Math.sin((float)k * 0.45F + f15) * 0.05000000074505806D);
            modelNeck.rotateAngleY = ((func_40307_a(ad2[0] - ad[0]) * f11 + 180F) * 3.141593F) / 180F;
            modelNeck.rotateAngleX = f14 + (((float)(ad2[1] - ad[1]) * 3.141593F) / 180F) * f11 * 5F;
            modelNeck.rotateAngleZ = ((func_40307_a(ad2[0] - (double)f13) * 3.141593F) / 180F) * f11;
            modelNeck.rotationPointY = f8;
            modelNeck.rotationPointZ = f9;
            modelNeck.rotationPointX = f10;
            f8 = (float)((double)f8 + Math.sin(modelNeck.rotateAngleX) * 10D);
            f9 = (float)((double)f9 - Math.cos(modelNeck.rotateAngleY) * Math.cos(modelNeck.rotateAngleX) * 10D);
            f10 = (float)((double)f10 - Math.sin(modelNeck.rotateAngleY) * Math.cos(modelNeck.rotateAngleX) * 10D);
            modelNeck.render(f5);
         }
      
         GL11.glPopMatrix();
      }
   
      public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
      {
         super.setRotationAngles(f, f1, f2, f3, f4, f5);
      }
   
      private float func_40307_a(double d)
      {
         for(; d >= 180D; d -= 360D) { }
         for(; d < -180D; d += 360D) { }
         return (float)d;
      }
   }
