package me.ashtheking.dragons.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiButton;

import org.lwjgl.opengl.GL11;

public class GNGButton extends GuiButton {

	public GNGButton(int i, int j, int k, int l, int i1, String s) {
		super(i, j, k, l, i1, s);
	}

	public GNGButton(int i, int j, int k, String s) {
		this(i, j, k, 200, 20, s);
	}

	@Override
	public void drawButton(Minecraft minecraft, int i, int j) {
		if (!drawButton)
			return;
		FontRenderer fontrenderer = minecraft.fontRenderer;
		GL11.glBindTexture(3553 /* GL_TEXTURE_2D */, minecraft.renderEngine.getTexture("/dragons/gui/buttons.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		boolean flag = i >= xPosition && j >= yPosition && i < xPosition + i && j < yPosition + j;
		int k = getHoverState(flag);
		drawTexturedModalRect(xPosition, yPosition, 0, k * 20, field_52008_a / 2, field_52007_b);
		drawTexturedModalRect(xPosition + field_52008_a / 2, yPosition, 200 - i / 2, k * 20, field_52008_a / 2, field_52007_b);
		mouseDragged(minecraft, i, j);
		if (!enabled)
			drawCenteredString(fontrenderer, displayString, xPosition + field_52008_a / 2, yPosition + (field_52007_b - 8) / 2, 0xffa0a0a0);
		else if (flag)
			drawCenteredString(fontrenderer, displayString, xPosition + field_52008_a / 2, yPosition + (field_52007_b - 8) / 2, 0xffffa0);
		else
			drawCenteredString(fontrenderer, displayString, xPosition + field_52008_a / 2, yPosition + (field_52007_b - 8) / 2, 0xe0e0e0);
	}
}
