package fr.xxathyx.shadowz.modelmaker.editor.construction.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.editor.CacheEditor;
import fr.xxathyx.shadowz.modelmaker.editor.Editor;
import fr.xxathyx.shadowz.modelmaker.part.Part;
import fr.xxathyx.shadowz.modelmaker.util.FacingLocation;

public class Detect implements Listener {
	
	private Main plugin = Main.getPlugin(Main.class);
	private Editor editor = new Editor();
	private DecimalFormat decimalFormat = new DecimalFormat("#.##");
	
	@EventHandler (priority = EventPriority.HIGH)
	public void playerMove(PlayerMoveEvent event) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		ArmorStand clickedArmorStand = null;
		
		if(editor.isInEditor(event.getPlayer())) {
			
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
						
						plugin.getActionBar().send(event.getPlayer(), ChatColor.DARK_PURPLE + "Part: " + ChatColor.LIGHT_PURPLE +
								clickedPart.getIndex() + "/" + new File(playerEditor.getModel().getModelFile().getParentFile() + "/parts/").listFiles().length
								+ ChatColor.DARK_PURPLE + ", ID: " + ChatColor.LIGHT_PURPLE + clickedPart.getID() + ":" + clickedPart.getData() + ChatColor.DARK_PURPLE +
								", Size: " + ChatColor.LIGHT_PURPLE + clickedPart.getSize().toString());
						
						Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin, () -> {
							try {
								stillLookingAtPartInformations(event.getPlayer(), clickedPart);
							} catch (IOException | InvalidConfigurationException e) {
								e.printStackTrace();
							}
						}, 100L);
					}
				}
			}
		}
	}
	
	public void stillLookingAtPartInformations(Player player, Part part) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		ArmorStand clickedArmorStand = null;
		
		if(editor.isInEditor(player.getPlayer())) {
			
			Editor playerEditor = CacheEditor.editors.get(player.getPlayer());
			
			if(playerEditor.getModel() != null) {
				for (Entity entity : player.getPlayer().getWorld().getNearbyEntities(player.getPlayer().getLocation(), 3, 3, 3 )) {
					if(entity instanceof ArmorStand) {
						if(FacingLocation.getLookingAt(player.getPlayer(), (LivingEntity) entity)) {
							clickedArmorStand = (ArmorStand) entity;
						}
					}
				}
				
				if(clickedArmorStand != null) {
					if(playerEditor.findPart(clickedArmorStand.getLocation()) != null) {
						
						Part detectedPart = playerEditor.findPart(clickedArmorStand.getLocation());
						
						if(detectedPart.getIndex() == part.getIndex()) {
							plugin.getActionBar().send(player.getPlayer(), ChatColor.DARK_PURPLE + "Location: X: " + ChatColor.LIGHT_PURPLE +
									decimalFormat.format(detectedPart.getX()) + ChatColor.DARK_PURPLE + " Y: " + ChatColor.LIGHT_PURPLE + decimalFormat.format(detectedPart.getY()) +
									ChatColor.DARK_PURPLE + " Z: " + ChatColor.LIGHT_PURPLE + decimalFormat.format(detectedPart.getZ()) + ChatColor.DARK_PURPLE +
									", Angles: X: " + ChatColor.LIGHT_PURPLE + decimalFormat.format(Math.toDegrees(detectedPart.getAngleX())) + "°" + ChatColor.DARK_PURPLE + " Y: " +
									ChatColor.LIGHT_PURPLE + decimalFormat.format(Math.toDegrees(detectedPart.getAngleY())) + "°" + ChatColor.DARK_PURPLE + " Z: " + ChatColor.LIGHT_PURPLE +
									decimalFormat.format(Math.toDegrees(detectedPart.getAngleZ())) + "°");
						}
					}
				}
			}
		}
	}
}