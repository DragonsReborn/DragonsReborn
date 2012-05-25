package me.ashtheking.dragons.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.ashtheking.dragons.mob.Stormcloak;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.mod_Dragon;

public class HoldManager {

	private ArrayList<Hold> list = new ArrayList<Hold>();
	public File saveDir;
	private File saveData;
	public World world;
	private Random rand;

	public HoldManager(World world) {
		saveDir = new File("saves/"
				+ world.getSaveHandler().getSaveDirectoryName() + "/dragons/");
		list = new ArrayList<Hold>();
		rand = new Random();
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		saveData = new File(saveDir, "holds.data");
		try {
			load();
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.world = world;
	}

	public void addHold(Hold hold) {
		list.add(hold);
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to save: " + hold.name);
		}
	}

	public Hold getHold(Location l) {
		for (Hold h : list) {
			if (h == null)
				continue;
			if (h.center.distanceTo(l) < 60)
				return h;
		}
		return null;
	}

	public List<Hold> getHolds() {
		return list;
	}

	public void save() throws IOException {
		if (!saveData.exists()) {
			System.out.println(saveData.getAbsolutePath());
			saveData.createNewFile();
		}

		if (saveDir == null) {
			saveDir = new File("saves/"
					+ ModLoader.getMinecraftInstance().theWorld
							.getSaveHandler().getSaveDirectoryName()
					+ "/dragons/");
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
		}
		for (int x = 0; x < list.size(); x++) {
			Hold h = list.get(x);
			if (h == null)
				continue;
			// mod_Dragon.save(
			// h, saveDir.getAbsolutePath() + "/"
			// + h.name.replaceAll(" ", "-") + ".hold");
			h.save(saveDir.getAbsolutePath() + "/"
					+ h.name.replaceAll(" ", "-") + ".hold");
		}
		String[] f = saveDir.list();
		String str = "";
		for (String s : f)
			if (s.contains(".hold"))
				str += s.substring(s.indexOf("/") + 1, s.indexOf(".hold"))
						+ "\n";
		new PrintStream(new FileOutputStream(saveData.getAbsolutePath()))
				.println(str);
	}

	public void load() throws IOException {
		if (saveData.exists()) {
			BufferedReader read = new BufferedReader(new FileReader(saveData));
			String s = "";
			while ((s = read.readLine()) != null)
				if (!s.equals(""))
					list.add(Hold.load(saveDir.getAbsolutePath() + "/" + s
							+ ".hold"));
				else
					break;
		}
	}

	public void saveDefault() {
		try {
			File save = new File("mods/dragons");
			if (!save.exists())
				save.mkdir();
			File holds = new File(save, "holds.txt");
			if (!holds.exists())
				holds.createNewFile();
			mod_Dragon.save(
					"Eastmarch; Falkreath Hold; Haafingar; Hjaalmarch; The Pale; The Reach"
							+ "; The Rift; Whiterun Hold; Winterhold",
					"mods/dragons/holds.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String randomName() {
		String st = (String) mod_Dragon.load("mods/dragons/holds.txt");
		if (st == null)
			saveDefault();
		st = (String) mod_Dragon.load("mods/dragons/holds.txt");
		String[] names = st.split(";");
		try {
			for (String s : names) {
				boolean b = false;
				for (Hold h : list)
					if (h != null)
						if (h.name.equalsIgnoreCase(s))
							b = true;
				if (!b)
					return s.trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "OutOfNames" + new Random().nextInt(20) + " Hold";
	}

	public Hold getHold(String currentHold) {
		if (list.size() == 0)
			return null;
		for (Hold h : list)
			if (h != null)
				if (h.name.equalsIgnoreCase(currentHold))
					return h;
		return null;
	}

	public Hold loadHold(int posX, int posY, int posZ) {
		File[] files = saveDir.listFiles();
		for (File f : files)
			if (f.toString().endsWith(".hold")) {
				Hold h;
				try {
					h = Hold.load(f.getAbsolutePath());
					if (h == null)
						break;
					if (h.center == null)
						h.center = new Location(posX, posY, posZ);
					else if (h.center.distanceTo(new Location(posX, posY, posZ)) < 100) {
						addHold(h);
						return h;
					}
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		Hold h = new Hold(randomName(), rand.nextInt(1), new Location(posX,
				posY, posZ));
		addHold(h);
		return h;
	}

	public void updateHold(Hold hold, int posX, int posY, int posZ) {
		Hold h = new Hold(hold.name, hold.faction == Stormcloak.class ? 0 : 1,
				new Location(posX, posY, posZ));
		h.bounty = hold.bounty;
		h.citizens = hold.citizens;
		for(Hold ho : list) {
			if(ho.center.distanceTo(h.center) < 100)
				list.set(list.indexOf(ho), h);
		}
		if (list.contains(hold))
			list.set(list.indexOf(hold), h);
	}
}
