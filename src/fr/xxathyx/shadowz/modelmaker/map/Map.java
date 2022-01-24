package fr.xxathyx.shadowz.modelmaker.map;

import org.bukkit.Location;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.model.loaded.Loaded;
import fr.xxathyx.shadowz.modelmaker.tasks.TaskAsyncLoadMapModels;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;

public class Map {
	
	private Main plugin = Main.getPlugin(Main.class);
		
	public void loadMapModels() {
		new TaskAsyncLoadMapModels().runTaskAsynchronously(plugin);
	}
	
	public void updateMapModels() {
		CacheMap.modelsLocations.clear();
		loadMapModels();
		System.out.print(Translater.getServerLangage().getMessage(Translater.getServerLangage().map_models_updated()));
	}
	
	public Loaded findModel(Location location) {
		
		for(int i = 0; i < CacheMap.modelsLocations.size(); i++) {
			
			if(CacheMap.modelsLocations.get(i).getLocations().contains(location)) {
				return CacheMap.modelsLocations.get(i).getLoaded();
			}
		}
		return null;
	}
}