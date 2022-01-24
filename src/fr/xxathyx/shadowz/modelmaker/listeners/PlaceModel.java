package fr.xxathyx.shadowz.modelmaker.listeners;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.CacheInterfaces;
import fr.xxathyx.shadowz.modelmaker.map.Map;
import fr.xxathyx.shadowz.modelmaker.model.CacheModel;
import fr.xxathyx.shadowz.modelmaker.model.Model;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;
import fr.xxathyx.shadowz.modelmaker.util.FacingLocation;

public class PlaceModel implements Listener {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private Location location;
	private Map map;
	
	@EventHandler
	public void onMoveModel(PlayerMoveEvent event) {
		
		if(CacheModel.holding.containsKey(event.getPlayer())) {
			
			Player player = event.getPlayer();
			
			Model model = CacheInterfaces.selected_model.get(player);
						
			Location location = player.getEyeLocation();
			location = FacingLocation.getLocationFacing(location);
			
			this.location = location;
						
			for(int i = 0; i < CacheModel.holding.get(player).size(); i++) {
				
				ArmorStand as = CacheModel.holding.get(player).get(i);
								
				as.teleport(new Location(location.getWorld(), location.getX() + model.getParts().get(i).getX(), location.getY() + model.getParts().get(i).getY(),
						location.getZ() + model.getParts().get(i).getZ()));
			}
			plugin.getActionBar().send(player, Translater.getTranslationLangage(player.getUniqueId()).getMessage(
					Translater.getTranslationLangage(player.getUniqueId()).model_to_place(), player.getUniqueId()));
		}
	}
		
	@EventHandler
	public void placeModel(PlayerInteractEvent event) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		if(CacheModel.holding.containsKey(event.getPlayer())) {
						
			Player player = event.getPlayer();
			map = new Map();
			
			ArrayList<ArmorStand> as = CacheModel.holding.get(player);
			CacheModel.holding.remove(player, as);
												
			Model model = CacheInterfaces.selected_model.get(player);
			
			if(location != null) {
				model.createLoadedFile(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ()));
				
				plugin.getActionBar().send(player, Translater.getTranslationLangage(player.getUniqueId()).getMessage(
						Translater.getTranslationLangage(player.getUniqueId()).model_placed(), player.getUniqueId()));
			}else {
				Location location = player.getEyeLocation();
				location = FacingLocation.getLocationFacing(location);
				location = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
				
				model.createLoadedFile(location);
				
				plugin.getActionBar().send(player, Translater.getTranslationLangage(player.getUniqueId()).getMessage(
						Translater.getTranslationLangage(player.getUniqueId()).model_placed(), player.getUniqueId()));
				
			}
			map.updateMapModels();
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void placeOnArmorStandClick(PlayerInteractAtEntityEvent event) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		if(event.getRightClicked() instanceof ArmorStand) {
			if(CacheModel.holding.containsKey(event.getPlayer())) {
				
				Player player = event.getPlayer();
				map = new Map();
				
				ArrayList<ArmorStand> as = CacheModel.holding.get(player);
				CacheModel.holding.remove(player, as);
													
				Model model = CacheInterfaces.selected_model.get(player);;
				
				if(location != null) {
					model.createLoadedFile(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ()));
					
					plugin.getActionBar().send(player, Translater.getTranslationLangage(player.getUniqueId()).getMessage(
							Translater.getTranslationLangage(player.getUniqueId()).model_placed(), player.getUniqueId()));
					
				}else {
					Location location = player.getEyeLocation();
					location = FacingLocation.getLocationFacing(location);
					location = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
					
					model.createLoadedFile(location);
					
					plugin.getActionBar().send(player, Translater.getTranslationLangage(player.getUniqueId()).getMessage(
							Translater.getTranslationLangage(player.getUniqueId()).model_placed(), player.getUniqueId()));
				}
				map.updateMapModels();
				event.setCancelled(true);
			}
		}
	}
}