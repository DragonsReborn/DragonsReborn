package me.ashtheking.dragons.world;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.World;

import org.bukkit.util.config.Configuration;
import org.bukkit.util.config.ConfigurationNode;

public class QuestHandler {

	Configuration config;
	public String name;
	public String statement;
	public String description;
	public String title;
	public String yesAnswer;
	public String noAnswer;
	public World world;
	public ArrayList<Section> sections;
	public int currentSection;

	public QuestHandler(World world, File file) {
		config = new Configuration(file);
		this.world = world;
		if (file.exists())
			load();
		else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			save();
			load();
		}
	}

	private void save() {
		try {
			config.setProperty("quest.var.currentSection", currentSection);
			config.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void load() {
		try {
			config.load();
			config.save();
			name = config.getString("quest.var.name");
			statement = config.getString("quest.var.statement");
			description = config.getString("quest.var.description");
			title = config.getString("quest.var.title");
			yesAnswer = config.getString("quest.var.yesAnswer");
			noAnswer = config.getString("quest.var.noAnswer");
			currentSection = config.getInt("quest.var.currentSection", -1);
			if(currentSection == -1)
					config.setProperty("quest.var.currentSection", 0);
			currentSection = config.getInt("quest.var.currentSection", 0);
			sections = new ArrayList<Section>();
			for (ConfigurationNode cn : config.getNodeList("quest.section",
					new ArrayList<ConfigurationNode>())) {
				try {
					Section s = new Section(cn);
					sections.add(s);
					System.out.println(s.description);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Failed to load file.");
		}
	}

	public class Section {

		public Location location;
		public ArrayList<Node> nodeList;
		public ConfigurationNode root;
		public boolean finished = false;
		public String description;

		public Section(ConfigurationNode root) throws Exception {
			this.root = root;
			if (root.getNode("spawn") != null)
				nodeList.add(new Spawn(root.getNode("spawn")));
			if (root.getNode("gen") != null)
				nodeList.add(new Gen(root.getNode("gen")));
			if (root.getBoolean("move", false))
				nodeList.add(new Move(root.getNode("move")));
			location = new Location(root.getInt("location.posX", 0),
					root.getInt("location.posY", 0), root.getInt("location.posZ", 0));
			if (location.x == 0 && location.y == 0 && location.z == 0) {
				throw new Exception("INVALID QUEST LOCATION!");
			}
			description = root.getString("description");
			for (Node n : nodeList)
				n.parent = this;
		}

		public void onPlayerNear(EntityPlayer player, World world, Random rand) {
			for (Node n : nodeList)
				if (!n.finished)
					n.onPlayerNear(player, world, rand);
			boolean b = true;
			for(Node n : nodeList)
				if(!n.finished)
					b = false;
			finished = b;
		}

		private abstract class Node {
			public boolean finished;
			public ConfigurationNode node;
			public Section parent;

			public abstract void onPlayerNear(EntityPlayer player, World world, Random rand);
		}

		public class Spawn extends Node {

			ArrayList<EntityLiving> spawn = new ArrayList<EntityLiving>();

			public Spawn(ConfigurationNode node) {
				this.node = node;
				for (ConfigurationNode cn : node.getNodeList("spawn",
						new ArrayList<ConfigurationNode>())) {
					try {
						String cl = cn.getString("class");
						Class clas = Class.forName(cl);

						if (clas == null) {
							System.out.println("Unable to parse spawnList for quest "
									+ " \nError:\n\t Class " + cl + " not found.");
							continue;
						}
						int amount = cn.getInt("amount", 1);
						if (!clas.isAssignableFrom(EntityLiving.class)) {
							System.out.println("Class " + cl + "is not a living entity!");
							return;
						}
						Constructor con = clas.getConstructor(World.class);
						EntityLiving el = (EntityLiving) con.newInstance(world);
						spawn.add(el);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onPlayerNear(EntityPlayer player, World world, Random rand) {
				for (Entity living : spawn) {
					living.setWorld(world);
					living.setLocationAndAngles(parent.location.x + rand.nextInt(3),
							parent.location.y + rand.nextInt(3),
							parent.location.z + rand.nextInt(3), 0, 0);
					world.spawnEntityInWorld(living);
				}
				finished = true;
			}

		}

		public class Gen extends Node {

			public ArrayList<String> list = new ArrayList<String>();

			public Gen(ConfigurationNode node) {
				this.node = node;
				for (ConfigurationNode cn : node.getNodeList("gen",
						new ArrayList<ConfigurationNode>())) {
					try {
						int id = cn.getInt("id", 0);
						int meta = cn.getInt("metadata", 0);
						int amount = cn.getInt("amount", 0);
						list.add("" + id + ":" + meta + ":" + amount);
					} catch (Exception e) {

					}
				}
			}

			@Override
			public void onPlayerNear(EntityPlayer player, World world, Random rand) {
				int x = 0;
				for (String s : list) {
					String[] split = s.split(":");
					int id = Integer.parseInt(split[0]);
					int meta = Integer.parseInt(split[1]);
					int amount = Integer.parseInt(split[2]);
					world.setBlock(parent.location.x, parent.location.y, parent.location.z,
							Block.chest.blockID);
					TileEntityChest b = (TileEntityChest) world.getBlockTileEntity(
							parent.location.x, parent.location.y, parent.location.z);
					b.setInventorySlotContents(x, new ItemStack(id, amount, meta));
				}
				finished = true;
			}

		}

		public class Move extends Node {

			public Move(ConfigurationNode node) {
				this.node = node;
			}

			@Override
			public void onPlayerNear(EntityPlayer player, World world, Random rand) {
				this.finished = true;
			}
		}
	}

}
