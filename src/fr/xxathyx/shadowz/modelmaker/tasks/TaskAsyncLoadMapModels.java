package fr.xxathyx.shadowz.modelmaker.tasks;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.scheduler.BukkitRunnable;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.map.CacheMap;
import fr.xxathyx.shadowz.modelmaker.map.MapModel;
import fr.xxathyx.shadowz.modelmaker.model.loaded.Loaded;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;

public class TaskAsyncLoadMapModels extends BukkitRunnable {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	@Override
	public void run() {
		
		System.out.print(Translater.getServerLangage().getMessage(Translater.getServerLangage().loading_map_models()));
		
		File dataFolder = new File(plugin.getDataFolder() + "/data/");
		
		ArrayList<File> playerDataFolder = new ArrayList<File>();
		ArrayList<File> playerModelsFolder = new ArrayList<File>();
		ArrayList<File> modelsFolders = new ArrayList<File>();
		ArrayList<File> loadedFolders = new ArrayList<File>();
		ArrayList<File> loadedFiles = new ArrayList<File>();
		
		ArrayList<Loaded> loadedModels = new ArrayList<Loaded>();
		
		if(!(dataFolder.exists())) {
			return;
		}
		
		for(int a = 0; a < dataFolder.listFiles().length; a++) {
			playerDataFolder.add(dataFolder.listFiles()[a]);
		}
		
		for(int z = 0; z < playerDataFolder.size(); z++) {
			
			File modelsFoldersFile = new File(playerDataFolder.get(z).getPath() + "/models/");
			
			if(modelsFoldersFile.exists()) {
				playerModelsFolder.add(modelsFoldersFile);
			}
							
			for(int e = 0; e < playerModelsFolder.size(); e++) {
				for(int r = 0; r < playerModelsFolder.get(e).listFiles().length; r++) {
					
					modelsFolders.add(playerModelsFolder.get(e).listFiles()[r]);
					
					for(int t = 0; t < modelsFolders.size(); t++) {
						
						File loadedFoldersFile = new File(modelsFolders.get(t).getPath() + "/loaded/");
						
						if(loadedFoldersFile.exists()) {
							loadedFolders.add(loadedFoldersFile);
						}
						
						for(int y = 0; y < loadedFolders.size(); y++) {
							for(int u = 0; u < loadedFolders.get(y).listFiles().length; u++) {

								
								loadedFiles.add(loadedFolders.get(y).listFiles()[u]);
								
								for(int i = 0; i < loadedFiles.size(); i++) {
									
									Loaded loaded = new Loaded(loadedFiles.get(i));
									loaded.setupLocations();
									
									loadedModels.add(loaded);
									
									for(int o = 0; o < loadedModels.size(); o++) {
										
										MapModel mapModel = new MapModel();
										
										mapModel.setLoaded(loadedModels.get(o));
										mapModel.setLocations(loadedModels.get(o).getLocations());
										
										CacheMap.modelsLocations.add(mapModel);
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.print(Translater.getServerLangage().getMessage(Translater.getServerLangage().loaded_map_models()));
	}
}