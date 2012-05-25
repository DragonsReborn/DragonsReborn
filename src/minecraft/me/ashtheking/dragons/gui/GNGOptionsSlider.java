package me.ashtheking.dragons.gui;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

public class GNGOptionsSlider extends GNGButton {


	private int type;

	private boolean dragging;

	public double sliderValue;

	public GNGOptionsSlider(int i, int j, int k, String s, double d, int type) {
		super(i, j, k, 150, 20, s);
		dragging = false;
		sliderValue = d;
		this.type = type;
	}

	@Override
	protected int getHoverState(boolean flag) {
		return 0;
	}

	@Override
	protected void mouseDragged(Minecraft minecraft, int i, int j) {
		if (!enabled)
			return;
		if (dragging) {
			sliderValue = (i - (xPosition + 4F)) / (i - 8F) * 16F;
			if (sliderValue < 0.0D)
				sliderValue = 0.0D;
			if (sliderValue > 16D)
				sliderValue = 16D;
//			if (type == 0)
//				npc.roamingDistance = (int) (sliderValue < 1 ? 0 : sliderValue);
//			if (type == 1)
//				npc.followDistance = (int) (sliderValue < 1 ? 0 : sliderValue);
			displayString = (int) sliderValue == 0 ? "Disabled" : Integer.toString((int) sliderValue);
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(xPosition + (int) (sliderValue * (i - 8)) / 16, yPosition, 0, 40, 4, 20);
		drawTexturedModalRect(xPosition + (int) (sliderValue * (i - 8)) / 16 + 4, yPosition, 196, 40, 4, 20);
	}

	@Override
	public boolean mousePressed(Minecraft minecraft, int i, int j) {
		if (super.mousePressed(minecraft, i, j)) {
			sliderValue = (i - (xPosition + 4F)) / (i - 8F) * 16F;
			if (sliderValue < 0.0D)
				sliderValue = 0.0D;
			if (sliderValue > 16D)
				sliderValue = 16D;
//			if (type == 0)
//				npc.roamingDistance = (int) sliderValue;
//			if (type == 1)
//				npc.followDistance = (int) sliderValue;
			displayString = (int) sliderValue == 0 ? "Disabled" : Integer.toString((int) sliderValue);
			dragging = true;
			return true;
		} else
			return false;
	}

	@Override
	public void mouseReleased(int i, int j) {
		dragging = false;
	}
}
