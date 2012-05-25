package me.ashtheking.dragons.world;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import me.ashtheking.dragons.mob.Imperial;
import me.ashtheking.dragons.mob.Stormcloak;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.mod_Dragon;

public class Hold {

	public String name;
	public Class faction;
	public Location center;
	public List<EntityLiving> citizens;
	public int bounty;
	private String saveLoc;

	public Hold(String name, int faction, Location center) {
		this.name = name;
		this.faction = faction == 0 ? Stormcloak.class : Imperial.class;
		this.center = center;
		this.citizens = new ArrayList<EntityLiving>();
		bounty = 0;
	}

	public void save(String location) throws IOException {
		NBTTagCompound nbt = new NBTTagCompound(name);
		nbt.setString("hold.name", name);
		nbt.setInteger("hold.faction", faction == Stormcloak.class ? 0 : 1);
		nbt.setInteger("hold.center.posX", center.x);
		nbt.setInteger("hold.center.posY", center.y);
		nbt.setInteger("hold.center.posZ", center.z);
		nbt.setInteger("hold.bounty", bounty);
		CompressedStreamTools.writeCompressed(nbt, new FileOutputStream(new File(location)));
	}

	public static Hold load(String s) throws IOException {
		NBTTagCompound nbt = CompressedStreamTools.readCompressed(new FileInputStream(new File(s)));
		Hold h = new Hold("", 0, null);
		h.name = nbt.getString("hold.name");
		h.faction = (nbt.getInteger("hold.faction") == 0 ? Stormcloak.class
				: Imperial.class);
		h.center = new Location(nbt.getInteger("hold.center.posX"),
				nbt.getInteger("hold.center.posY"),
				nbt.getInteger("hold.center.posZ"));
		h.citizens = new ArrayList<EntityLiving>();
		h.bounty = nbt.getInteger("hold.bounty");
		return h;
	}
}
