package me.ashtheking.dragons.render;

import me.ashtheking.dragons.mob.helper.Soldier;
import me.ashtheking.dragons.model.ModelSoldier;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.RenderBiped;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;

public class RenderSoldier extends RenderBiped {

	public RenderSoldier() {
		super(new ModelSoldier(), 0.5F);
		// TODO Auto-generated constructor stub
	}
	
	public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {
        Soldier s = (Soldier)entity;
        
        super.doRender(entity, d, d1, d2, f, f1);
    }
	
    
    protected void passSpecialRender(EntityLiving entityliving, double d, double d1, double d2)
    {
        renderName((Soldier)entityliving, d, d1, d2);
    }

	protected void renderName(Soldier entityplayer, double d, double d1, double d2)
	{
		if(Minecraft.isGuiEnabled())
		{
			float f = 1.6F;
			float f1 = 0.01666667F * f;
			String s = entityplayer.name;
			if(!entityplayer.isSneaking())
				renderLivingLabel(entityplayer, s, d, d1, d2, 64);
			else
			{
				FontRenderer fontrenderer = getFontRendererFromRenderManager();
				GL11.glPushMatrix();
				GL11.glTranslatef((float)d + 0.0F, (float)d1 + 2.3F, (float)d2);
				GL11.glNormal3f(0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
				GL11.glScalef(-f1, -f1, f1);
				GL11.glDisable(2896 /*GL_LIGHTING*/);
				GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
				GL11.glDepthMask(false);
				GL11.glEnable(3042 /*GL_BLEND*/);
				GL11.glBlendFunc(770, 771);
				Tessellator tessellator = Tessellator.instance;
				GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
				tessellator.startDrawingQuads();
				int i = fontrenderer.getStringWidth(s) / 2;
				tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
				tessellator.addVertex(-i - 1, -1D, 0.0D);
				tessellator.addVertex(-i - 1, 8D, 0.0D);
				tessellator.addVertex(i + 1, 8D, 0.0D);
				tessellator.addVertex(i + 1, -1D, 0.0D);
				tessellator.draw();
				GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
				GL11.glDepthMask(true);
				fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 0, 0x20ffffff);
				GL11.glEnable(2896 /*GL_LIGHTING*/);
				GL11.glDisable(3042 /*GL_BLEND*/);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glPopMatrix();
			}
		}
	}

}
