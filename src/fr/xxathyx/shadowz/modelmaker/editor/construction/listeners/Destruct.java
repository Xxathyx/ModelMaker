package fr.xxathyx.shadowz.modelmaker.editor.construction.listeners;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.xxathyx.shadowz.modelmaker.editor.CacheEditor;
import fr.xxathyx.shadowz.modelmaker.editor.Editor;
import fr.xxathyx.shadowz.modelmaker.part.Part;
import fr.xxathyx.shadowz.modelmaker.part.hitbox.Size;
import fr.xxathyx.shadowz.modelmaker.util.FacingLocation;

public class Destruct implements Listener {
	
	private Editor editor = new Editor();
	
	@EventHandler
	public void destroyNormalPart(PlayerInteractEvent event) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		ArmorStand clickedArmorStand = null;
		
		if(editor.isInEditor(event.getPlayer())) {
			if(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
				
				Editor playerEditor = CacheEditor.editors.get(event.getPlayer());
				
				if(playerEditor.getModel() != null) {
					for (Entity entity : event.getPlayer().getWorld().getNearbyEntities(event.getPlayer().getLocation(), 3, 3, 3 )) {
						if(entity instanceof ArmorStand) {
							if(FacingLocation.getLookingAt(event.getPlayer(), (LivingEntity) entity)) {
								clickedArmorStand = (ArmorStand) entity;
							}
						}
					}
					
					if(clickedArmorStand != null) {
						if(playerEditor.findPart(clickedArmorStand.getLocation()) != null) {
							
							Part clickedPart = playerEditor.findPart(clickedArmorStand.getLocation());
							
							if(clickedPart.getSize().equals(Size.NORMAL) || clickedPart.getSize().equals(Size.SMALL)) {
								playerEditor.removePart(clickedArmorStand, clickedPart);
								event.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void destroyPart(EntityDamageByEntityEvent event) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		if(event.getEntity() instanceof ArmorStand) {			
			if(event.getDamager() instanceof Player) {
				
				Player player = (Player) event.getDamager();
				
				if(editor.isInEditor(player)) {
					
					Editor playerEditor = CacheEditor.editors.get(player);
					
					if(playerEditor.getModel() != null) {	
						if(playerEditor.findPart(event.getEntity().getLocation()) != null) {
							
							Part clickedPart = playerEditor.findPart(event.getEntity().getLocation());
							playerEditor.removePart(event.getEntity(), clickedPart);
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}
}