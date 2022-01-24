package fr.xxathyx.shadowz.modelmaker.map;

import java.util.ArrayList;

import org.bukkit.Location;

import fr.xxathyx.shadowz.modelmaker.model.loaded.Loaded;

public class MapModel {
	
	private ArrayList<Location> locations = new ArrayList<Location>();
	private Loaded loaded;
	
	public Loaded getLoaded() {
		return loaded;
	}
	
	public ArrayList<Location> getLocations() {
		return locations;
	}
	
	public void setLoaded(Loaded loaded) {
		this.loaded = loaded;
	}
	
	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}
}