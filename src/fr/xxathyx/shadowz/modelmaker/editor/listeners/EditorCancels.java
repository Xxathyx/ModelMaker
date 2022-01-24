package fr.xxathyx.shadowz.modelmaker.editor.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.xxathyx.shadowz.modelmaker.editor.Editor;
import fr.xxathyx.shadowz.modelmaker.items.Tools;
import fr.xxathyx.shadowz.modelmaker.server.configuration.ServerConfiguration;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;

public class EditorCancels implements Listener {
	
	private Editor editor = new Editor();
	private ServerConfiguration serverConfiguration = new ServerConfiguration();
	
	@EventHandler
	public void portalEvent(PlayerPortalEvent event) {
		
		if(editor.isInEditor(event.getPlayer())) {
			if(!serverConfiguration.getPortalTeleportation()) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void pickupEvent(PlayerPickupItemEvent event) {
		
		if(editor.isInEditor(event.getPlayer())) {
			if(!serverConfiguration.getPickupItems()) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void dropItemEvent(PlayerDropItemEvent event) {
		
		if(editor.isInEditor(event.getPlayer())) {
			if(!serverConfiguration.getDropItems()) {
				event.setCancelled(true);
			}
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	public void dropToolsEvent(PlayerDropItemEvent event) {
		
		if(editor.isInEditor(event.getPlayer())) {
			if(new Tools().getTools(event.getPlayer()).contains(event.getItemDrop())) {
				if(!serverConfiguration.getDropTools()) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void foodLevelChangeEvent(FoodLevelChangeEvent event) {
		
		if(event.getEntityType().equals(EntityType.PLAYER)) {
			
			Player player = (Player) event.getEntity();
			
			if(editor.isInEditor(player)) {
				if(!serverConfiguration.getHunger()) {
					event.setCancelled(true);
				}
			}
		}		
	}
	
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
    	
		if(editor.isInEditor(event.getPlayer())) {
			if(!serverConfiguration.getBlockPlace()) {
				event.setCancelled(true);
			}
		}
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
    	
		if(editor.isInEditor(event.getPlayer())) {
			if(!serverConfiguration.getBlockBreak()) {
				event.setCancelled(true);
			}
		}
    }
    
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
    	
    	if(event.getEntityType() == EntityType.PLAYER) {
    		
    		Player player = (Player) event.getEntity();
    		
    		if(editor.isInEditor(player)) {
    			if(!serverConfiguration.getReceiveDamage()) {
    				event.setCancelled(true);
    			}
    		}
    	}
    }
    
    @EventHandler
    public void onHurt(EntityDamageByEntityEvent event) {
    	
    	if(event.getDamager() instanceof Player) {
    		
    		Player player = (Player) event.getDamager();
    		
    		if(editor.isInEditor(player)) {
    			if(!serverConfiguration.getGiveDamage()) {
    				event.setCancelled(true);
    			}
    		}
    	}
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
    	
    	if(editor.isInEditor(event.getPlayer())) {
    		editor.removeFromEditor(event.getPlayer());
    	}
    }
    
    @EventHandler
    public void onPreCommand(PlayerCommandPreprocessEvent event) {
    	
    	if(editor.isInEditor(event.getPlayer())) {
    		
    		ServerConfiguration serverConfiguration = new ServerConfiguration();
    		
    		if(serverConfiguration.getDisabledCommands().contains(event.getMessage())) {
    			event.getPlayer().sendMessage(Translater.getTranslationLangage(event.getPlayer().getUniqueId()).getMessage(
                        Translater.getTranslationLangage(event.getPlayer().getUniqueId()).command_disabled(), event.getPlayer().getUniqueId()));
    			event.setCancelled(true);
    		}
    	}
    }
}