package fr.xxathyx.shadowz.modelmaker.model.listeners;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.Interfaces;
import fr.xxathyx.shadowz.modelmaker.map.Map;
import fr.xxathyx.shadowz.modelmaker.model.loaded.CacheLoaded;

public class EditPlacedModel implements Listener {
	
	private Interfaces interfaces = new Interfaces();
	private Map map = new Map();
	
	@EventHandler
	public void clickOnParts(PlayerInteractAtEntityEvent event) {
		
		if(event.getRightClicked() instanceof ArmorStand) {
			
			Location location = event.getRightClicked().getLocation();
						
			if(map.findModel(location) != null) {
								
				if(CacheLoaded.selected_loaded.containsKey(event.getPlayer())) {
					CacheLoaded.selected_loaded.replace(event.getPlayer(), map.findModel(location));
				}else {
					CacheLoaded.selected_loaded.put(event.getPlayer(), map.findModel(location));
				}
				
				if(map.findModel(location).getUUID() == event.getPlayer().getUniqueId() || event.getPlayer().hasPermission("modelmaker.admin")) {
					interfaces.loaded_interface(CacheLoaded.selected_loaded.get(event.getPlayer()), event.getPlayer());
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void editArmorStands(PlayerArmorStandManipulateEvent event) {
				
		if(map.findModel(event.getRightClicked().getLocation()) != null) {
			event.setCancelled(true);
		}
	}
}