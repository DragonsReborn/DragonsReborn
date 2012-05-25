package me.ashtheking.dragons.world;

import java.util.ArrayList;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraft.src.mod_Dragon;

public class QuestManager {

	public World world;
	public ArrayList<QuestHandler> quests;

	public QuestManager(World world) {
		this.world = world;
		quests = new ArrayList<QuestHandler>();
	}

	public void addQuest(EntityPlayer player, QuestHandler q) {
		quests.add(q);
		mod_Dragon.gui.startQuest(q);
	}
}
