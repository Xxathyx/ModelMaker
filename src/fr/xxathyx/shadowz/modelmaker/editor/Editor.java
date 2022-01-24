package fr.xxathyx.shadowz.modelmaker.editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.items.ItemStacks;
import fr.xxathyx.shadowz.modelmaker.model.Model;
import fr.xxathyx.shadowz.modelmaker.part.Part;
import fr.xxathyx.shadowz.modelmaker.profil.Profil;
import fr.xxathyx.shadowz.modelmaker.project.Project;
import fr.xxathyx.shadowz.modelmaker.server.configuration.ServerConfiguration;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;

public class Editor {
	
	public Editor(Player player) {
		this.player = player;
	}
		
	public Editor() {
		
	}
	
	private final Main plugin = Main.getPlugin(Main.class);
	
	private Model selectedModel;
	
	private Player player;
		
	private final ServerConfiguration serverConfiguration = new ServerConfiguration();
	
	public final ArrayList<Location> partsLocations = new ArrayList<Location>();
	
	private Part part;
	
	public void addToEditor(Player player) {
		
		if(!CacheEditor.editor.contains(player) | !(CacheEditor.inventory.containsKey(player) |
				!(CacheEditor.inventory.containsValue(CacheEditor.inventory.get(player))))) {
			
			CacheEditor.editor.add(player);
			CacheEditor.gamemode.put(player, player.getGameMode());
			CacheEditor.editors.put(player, this);
			
			if(serverConfiguration.getSaveInventory()) {
				setEditorInventory(player);
			}
			
			if(serverConfiguration.getFly()) {
				if(serverConfiguration.getFlySpeed() > 0 && serverConfiguration.getFlySpeed() < 10) {
					player.setFlying(true);
					player.setFlySpeed(serverConfiguration.getFlySpeed());
				}
			}
			
			if(serverConfiguration.getGamemode() == 0) {
				player.setGameMode(GameMode.SURVIVAL);
			}
			
			if(serverConfiguration.getGamemode() == 2) {
				player.setGameMode(GameMode.ADVENTURE);
			}
			
			if(serverConfiguration.getGamemode() == 1) {
				player.setGameMode(GameMode.CREATIVE);
			}
			
			if(serverConfiguration.getGamemode() == 3) {
				player.setGameMode(GameMode.SPECTATOR);
			}
			
			player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
					Translater.getTranslationLangage(player.getUniqueId()).editor_enter(), player.getUniqueId()));
			
		}else {
			
			player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
					Translater.getTranslationLangage(player.getUniqueId()).editor_in(), player.getUniqueId()));
		}
	}
	
	public void removeFromEditor(Player player) {
		
		if(CacheEditor.editor.contains(player) | CacheEditor.inventory.containsKey(player) |
				CacheEditor.inventory.containsValue(CacheEditor.inventory.get(player))) {
			
			player.setGameMode(CacheEditor.gamemode.get(player));
			
			CacheEditor.editor.remove(player);
			CacheEditor.gamemode.remove(player, player.getGameMode());
			CacheEditor.editors.remove(player, this);
			
			if(serverConfiguration.getSaveInventory()) {
				removeEditorInventory(player);
			}
			
			if(serverConfiguration.getFly()) {
				player.setFlying(false);
			}
			
			player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
					Translater.getTranslationLangage(player.getUniqueId()).editor_out(), player.getUniqueId()));
			
		}else {
			player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
					Translater.getTranslationLangage(player.getUniqueId()).editor_in(), player.getUniqueId()));
		}
	}
	
	public boolean isInEditor(Player player) {
		
		if(CacheEditor.editor.contains(player)) {
			return true;
		}
		return false;
	}
	
	public Model getModel() {
		 return selectedModel;
	 }
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Editor getEditor() {
		return this;
	}
	
	public double getPrecision() {
		
		File file = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId() + ".yml");	
		Profil profil = new Profil(file);
		
		return Double.parseDouble(profil.getConfigFile().getString("precision scale"));
	}
	
	public Part getCurrentPart() {
		return part;
	}
	
	 public void setModel(Model model) {
		 this.selectedModel = model;
	 }
	 
	 public void setCurrentPart(Part part) {
		 this.part = part;
	 }
	 
	 public void setPlayer(Player player) {
		 this.player = player;
	 }
	 
	 public void setEditorInventory(Player player) {
		 
		 ItemStack[] items = new ItemStack[40];
		 
		 ItemStacks itemstacks = new ItemStacks(player);
		 
		 CacheEditor.inventory.put(player, items);
		 
		 for(int slot = 0; slot < 36; slot++) {
				ItemStack item = player.getInventory().getItem(slot);
				if(item != null) {
					items[slot] = item;
				}
			}
			
			items[36] = player.getInventory().getHelmet();
			items[37] = player.getInventory().getChestplate();
			items[38] = player.getInventory().getLeggings();
			items[39] = player.getInventory().getBoots();
			
			player.getInventory().clear();
			
			player.getInventory().setHelmet(null);
			player.getInventory().setChestplate(null);
			player.getInventory().setLeggings(null);
			player.getInventory().setBoots(null);
			
			if(serverConfiguration.getGiveTools()) {
				player.getInventory().setItem(17, itemstacks.tools());
				player.getInventory().setItem(26, itemstacks.model_parameter());
				player.getInventory().setItem(8, itemstacks.exit_editor());
			}
	}
	 
	 public void removeEditorInventory(Player player) {
		 
			player.getInventory().clear();
			 
			ItemStack[] items = CacheEditor.inventory.get(player);
			 
			for(int slot = 0; slot < 36; slot++) {
				ItemStack item = items[slot];
				if(item != null) {
					player.getInventory().setItem(slot, item);
				}
			}
				
			player.getInventory().setHelmet(items[36]);
			player.getInventory().setChestplate(items[37]);
			player.getInventory().setLeggings(items[38]);
			player.getInventory().setBoots(items[39]);
				
			CacheEditor.inventory.remove(player, CacheEditor.inventory.get(player));
	 }
	 
	 public void exportPart(Part part) {
		 
		 
		 
	 }
	 
	 public void exportModel(Model model) {
		
		 
		 
	 }
	 
	 public void editModel(Player player, Model model) {
		 
		 this.setModel(model);
		 this.setPlayer(player);
		 
		 this.getModel().getProject().setBase(player.getLocation());
		 
		 DecimalFormat decimalFormat = new DecimalFormat("#.##");
		 
		 player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                 Translater.getTranslationLangage(player.getUniqueId()).defined_base_point(), player.getUniqueId()) + "X: " + decimalFormat.format(player.getLocation().getX()) +
				 " Y: " + decimalFormat.format(player.getLocation().getY()) + " Z: " + decimalFormat.format(player.getLocation().getZ()) + " W: " + player.getWorld().getName());
	 }
	 
	 public void loadModel(Location location) throws FileNotFoundException, IOException, InvalidConfigurationException {
	 
	 ArrayList<Part> parts = new ArrayList<Part>();
	 
	 int[] task = {0};
	 int[] loaded = {0};
	 loaded[0] = 0;
	    
	    File partsFolder = new File(selectedModel.getModelFile().getParentFile() + "/parts/");
	    
		File[] listOfFiles = partsFolder.listFiles();
		
		for(int i = 0; i < listOfFiles.length; i++) {
			
			Part part = new Part(listOfFiles[i]);
			
			 Project project = this.getModel().getProject();
			 
			 double x = project.getBase().getX()+part.getX();
			 double y = project.getBase().getY()+part.getY();
			 double z = project.getBase().getZ()+part.getZ();
			 
			 Location partLocation = new Location(project.getBase().getWorld(), x, y, z);
			
			partsLocations.add(partLocation);
			
			parts.add(part);
		}
		
	 task[0] = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				
				if(loaded[0] < parts.size()) {
					
					parts.get(loaded[0]).load(location);
					loaded[0]++;
					
					int percentage = (int) (((float)loaded[0]/parts.size())*100);
							
					plugin.getActionBar().send(player, Translater.getTranslationLangage(player.getUniqueId()).getMessage(
							Translater.getTranslationLangage(player.getUniqueId()).model_loading(), player.getUniqueId())
					+ ChatColor.GREEN + " " + loaded[0] + "/" + parts.size() + ", " + percentage + "%");
					
				}else if(loaded[0] == parts.size()) {
					
					Bukkit.getScheduler().cancelTask(task[0]);
					player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
							Translater.getTranslationLangage(player.getUniqueId()).model_loaded(), player.getUniqueId()));
				}
	          }
	        }, 0L, 0L);
	}
	 
	 public Part findPart(Location location) throws FileNotFoundException, IOException, InvalidConfigurationException {
		 		 
		 for(int i = 0; i < partsLocations.size(); i++) {
			 
			 if(partsLocations.get(i).equals(location)) {
				 return new Part(new File(this.getModel().getPartsPath()).listFiles()[i]);
			 }
		 }
		 return null;
	 }
	 
	 public void addPartLocation(Part part) throws FileNotFoundException, IOException, InvalidConfigurationException {
		 
			 Project project = this.getModel().getProject();
			 
			 double x = project.getBase().getX()+part.getX();
			 double y = project.getBase().getY()+part.getY();
			 double z = project.getBase().getZ()+part.getZ();
			 
			 Location partLocation = new Location(project.getBase().getWorld(), x, y, z);
						 
			partsLocations.add(partLocation);
	 }
	 
	 public void removePart(Entity entity, Part part) throws FileNotFoundException, IOException, InvalidConfigurationException {
		 
		 if(partsLocations.contains(entity.getLocation())) {
				partsLocations.remove(entity.getLocation());
				entity.remove();
				part.remove();
		 }
	 }
	 
	 public ArrayList<Location> getPartsLocations() {
		 return partsLocations;
	 }
	 
	 public void updatePartsLocations() throws FileNotFoundException, IOException, InvalidConfigurationException {
		 
		 partsLocations.clear();
		 
		    File partsFolder = new File(selectedModel.getModelFile().getParentFile() + "/parts/");
		    
			File[] listOfFiles = partsFolder.listFiles();
			for(int i = 0; i < listOfFiles.length; i++) {
				
				Part part = new Part(listOfFiles[i]);
				
				 Project project = this.getModel().getProject();
				 
				 double x = project.getBase().getX() + part.getX();
				 double y = project.getBase().getY() + part.getY();
				 double z = project.getBase().getZ() + part.getZ();
				 
				 Location partLocation = new Location(project.getBase().getWorld(), x, y, z);
				
				partsLocations.add(partLocation);
			}
	 }
}