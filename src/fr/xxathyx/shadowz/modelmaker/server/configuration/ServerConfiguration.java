package fr.xxathyx.shadowz.modelmaker.server.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.util.Host;

public class ServerConfiguration {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private FileConfiguration fileconfiguration;
	private File configFile = new File(plugin.getDataFolder(), "config" + ".yml");
	
	private List<String> disabled_commands = new ArrayList<>();
	
	public void createConfiguration() throws IOException {
		
		disabled_commands.add("/disabled_command_go_here");
		
		if(!configFile.getParentFile().exists()) {
			configFile.getParentFile().mkdirs();
		}
		
		if(!configFile.exists()) {
			configFile.createNewFile();
		}
		
	    fileconfiguration = new YamlConfiguration();
	    
	    fileconfiguration.set("plugin.auto-updater",  true);
	    fileconfiguration.set("plugin.debug",  false);
	    fileconfiguration.set("plugin.model-gravity", true);
	    
	    Host host = new Host(Bukkit.getServer().getIp());
	    
	    if(host.getOfficials().contains(host.getCOUNTRYCODE())) {
	    	fileconfiguration.set("translation.server-langage", host.getCOUNTRYCODE());
	    }else {
	    	fileconfiguration.set("translation.server-langage", "GB");
	    }
	    fileconfiguration.set("translation.automatic-user-change", false);
	    
	    fileconfiguration.set("editor.events.block-break", false);
	    fileconfiguration.set("editor.events.block-place", false);
	    fileconfiguration.set("editor.events.drop-items", false);
	    fileconfiguration.set("editor.events.drop-tools", false);
	    fileconfiguration.set("editor.events.give-damage", false);
	    fileconfiguration.set("editor.events.receive-damage", false);
	    fileconfiguration.set("editor.events.pickup-items", false);
	    fileconfiguration.set("editor.events.portal-teleportation", false);
	    fileconfiguration.set("editor.events.hunger", false);
	    
	    fileconfiguration.set("editor.disabled-commands", disabled_commands);
	    
	    fileconfiguration.set("editor.give-tools", true);
	    fileconfiguration.set("editor.save-inventory", true);
	    fileconfiguration.set("editor.fly", false);
	    fileconfiguration.set("editor.speed", 1);
	    fileconfiguration.set("editor.gamemode", 1);
	    
	    fileconfiguration.save(configFile);
	}
	
	public void updateConfig() throws IOException {
		
    	boolean auto_updater = this.getAutoUpdater();
    	boolean debuger = this.getDebuger();
    	boolean model_gravity = this.getModelGravity();
    	
    	String server_langage = this.getServerLangage();
    	
    	boolean automatic_user_change = this.getAutomaticUserChange();
    	boolean block_break = this.getBlockBreak();
    	boolean block_place = this.getBlockPlace();
    	boolean drop_items = this.getDropItems();
    	boolean drop_tools = this.getDropTools();
    	boolean give_damage = this.getGiveDamage();
    	boolean receive_damage = this.getReceiveDamage();
    	boolean pickup_items = this.getPickupItems();
    	boolean portal_teleportation = this.getPortalTeleportation();
    	boolean hunger = this.getHunger();
    	
    	List<String> commands = this.getDisabledCommands();
    	
    	boolean give_tools = this.getGiveTools();
    	boolean save_inventory = this.getSaveInventory();
    	boolean fly = this.getFly();
    	int fly_speed = this.getFlySpeed();
    	
    	int gamemode = this.getGamemode();
    	
	    fileconfiguration = new YamlConfiguration();
	    
	    fileconfiguration.set("plugin.auto-updater", auto_updater);
	    fileconfiguration.set("plugin.debug", debuger);
	    fileconfiguration.set("plugin.model-gravity", model_gravity);
	    
	    fileconfiguration.set("translation.server-langage", server_langage);
	    fileconfiguration.set("translation.automatic-user-change", automatic_user_change);
	    
	    fileconfiguration.set("editor.events.block-break", block_break);
	    fileconfiguration.set("editor.events.block-place", block_place);
	    fileconfiguration.set("editor.events.drop-items", drop_items);
	    fileconfiguration.set("editor.events.drop-tools", drop_tools);
	    fileconfiguration.set("editor.events.give-damage", give_damage);
	    fileconfiguration.set("editor.events.receive-damage", receive_damage);
	    fileconfiguration.set("editor.events.pickup-items", pickup_items);
	    fileconfiguration.set("editor.events.portal-teleportation", portal_teleportation);
	    fileconfiguration.set("editor.events.hunger", hunger);
	    
	    fileconfiguration.set("editor.disabled-commands", commands);
	    
	    fileconfiguration.set("editor.give-tools", give_tools);
	    fileconfiguration.set("editor.save-inventory", save_inventory);
	    fileconfiguration.set("editor.fly", fly);
	    fileconfiguration.set("editor.fly.speed", fly_speed);
	    fileconfiguration.set("editor.gamemode", gamemode);
    	
	    if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
	    this.getServerConfigurationFile().delete();
	    
	    File newFile = new File(plugin.getDataFolder(), "config" + ".yml");
	    
		if(!newFile.exists()) {
			newFile.createNewFile();
		}
		
		fileconfiguration.save(newFile);
	}
	
	public boolean getAutoUpdater() {
		return getConfigFile().getBoolean("plugin.auto-updater");
	}
	
	public boolean getDebuger() {
		return getConfigFile().getBoolean("plugin.debug");
	}
	
	public boolean getModelGravity() {
		return getConfigFile().getBoolean("plugin.model-gravity");

	}
	
	public String getServerLangage() {
		return getConfigFile().getString("translation.server-langage");
	}
	
	public boolean getAutomaticUserChange() {
		return getConfigFile().getBoolean("translation.automatic-user-change");
	}
	
	public boolean getBlockBreak() {
		return getConfigFile().getBoolean("editor.events.block-break");
	}
	
	public boolean getBlockPlace() {
		return getConfigFile().getBoolean("editor.events.block-place");
	}
	
	public boolean getDropItems() {
		return getConfigFile().getBoolean("editor.events.drop-items");
	}
	
	public boolean getDropTools() {
		return getConfigFile().getBoolean("editor.events.drop-tools");
	}
	
	public boolean getGiveDamage() {
		return getConfigFile().getBoolean("editor.events.give-damage");
	}
	
	public boolean getReceiveDamage() {
		return getConfigFile().getBoolean("editor.events.receive-damage");
	}
	
	public boolean getPickupItems() {
		return getConfigFile().getBoolean("editor.events.pickup-items");
	}
	
	public boolean getPortalTeleportation() {
		return getConfigFile().getBoolean("editor.events.portal-teleportation");
	}
	
	public boolean getHunger() {
		return getConfigFile().getBoolean("editor.events.hunger");
	}
	
	public List<String> getDisabledCommands() {		
		return this.getConfigFile().getStringList("editor.disabled-commands");
	}
	
	public boolean getGiveTools() {
		return getConfigFile().getBoolean("editor.give-tools");
	}
	
	public boolean getSaveInventory() {
		return getConfigFile().getBoolean("editor.save-inventory");
	}
	
	public boolean getFly() {
		return getConfigFile().getBoolean("editor.fly");
	}
	
	public int getFlySpeed() {
		return getConfigFile().getInt("editor.speed");
	}
	
	public int getGamemode() {
		return Integer.parseInt(this.getConfigFile().getString("editor.gamemode"));
	}
	
	public FileConfiguration getConfigFile() {
		
		fileconfiguration = new YamlConfiguration();
		
			try {
				fileconfiguration.load(configFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		return fileconfiguration;
    }
	
	public File getServerConfigurationFile() {
		return configFile;
	}
}