package me.ashtheking.dragons.world;

import java.io.Serializable;

public class Location implements Serializable {

	public int x;
	public int y;
	public int z;

	public Location(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double distanceTo(Location l) {
		return Math.sqrt(((l.x - x) * (l.x - x)) + ((l.y - y) * (l.y - y)) + ((l.z - z) * (l.z - z)) );
	}
	
	public String toString() {
		return "X: " + x + "  Y: "+ y + "  Z: " + z;
	}
}
