package fr.xxathyx.shadowz.modelmaker.model.loaded;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.CacheInterfaces;
import fr.xxathyx.shadowz.modelmaker.math.Quaternion;
import fr.xxathyx.shadowz.modelmaker.model.CacheModel;
import fr.xxathyx.shadowz.modelmaker.model.Model;
import fr.xxathyx.shadowz.modelmaker.part.Part;
import fr.xxathyx.shadowz.modelmaker.part.hitbox.Size;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;
import fr.xxathyx.shadowz.modelmaker.util.FacingLocation;

public class Loaded {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private File file;
	
	private FileConfiguration customConfig;
	
	private ArrayList<Location> partsLocations = new ArrayList<Location>();
	
	public Loaded(File file) {
		this.file = file;
	}
	
	public Loaded() {
		
	}
		
	public File getLoadedFile() {
		return this.file;
	}
	
	public FileConfiguration getConfigFile() {
		
		customConfig = new YamlConfiguration();
		
			try {
				customConfig.load(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		return customConfig;
    }
	
	public String getOriginalName() {
		return this.getConfigFile().getString("model.name");
	}
	
	public String getName() {
		return this.getConfigFile().getString("model.name") + " (" + this.getIndex() + ")";
	}
	
	public String getOwner() {
		return this.getConfigFile().getString("model.owner");
	}
	
	public UUID getUUID() {
		return UUID.fromString(this.getConfigFile().getString("model.uuid"));
	}
	
	public String getPartsPath() {
		return this.getConfigFile().getString("model.partsPath");
	}
	
	public String getDate() {
		return this.getConfigFile().getString("model.created");
	}
	
	public String getIndex() {
		return this.getConfigFile().getString("model.index");
	}
	
	public World getWorld() {
		return Bukkit.getWorld(this.getConfigFile().getString("model.location.world"));
	}
	
	public double getX() {
		return Double.parseDouble(this.getConfigFile().getString("model.location.x"));
	}
	
	public double getY() {
		return Double.parseDouble(this.getConfigFile().getString("model.location.y"));
	}
	
	public double getZ() {
		return Double.parseDouble(this.getConfigFile().getString("model.location.z"));
	}
	
	public ArrayList<Location> getLocations() {
		return partsLocations;
	}
	
	public Model getModel() {
		
		File file = new File(plugin.getDataFolder() + "/data/" + this.getUUID() + "/models/" + this.getOriginalName() + "/", this.getOriginalName() + ".yml");
		
		Model model = new Model(file);
		
		return model;
	}
	
	@SuppressWarnings("deprecation")
	public void replace(Player player) {
		
		if(CacheInterfaces.selected_model.containsKey(player)) {
			CacheInterfaces.selected_model.replace(player, this.getModel());
		}else {
			CacheInterfaces.selected_model.put(player, this.getModel());
		}
		
		ArrayList<Part> parts = new ArrayList<Part>();
		
		ArrayList<ArmorStand> holders = new ArrayList<ArmorStand>();
		
		Location location = player.getEyeLocation();
		location = FacingLocation.getLocationFacing(location);
		
	    Path partsFolder = Paths.get(plugin.getDataFolder() + "//data//" + player.getUniqueId() + "//models//" + this.getOriginalName() + "//parts//");
	    	    
		File[] listOfFiles = partsFolder.toFile().listFiles();
		
		EulerAngle legs = new EulerAngle(3.14159, 0, 0);
		EulerAngle arms = new EulerAngle(-0.244346, 0.785398, 0);
		EulerAngle small_arms = new EulerAngle(2.4260076603, 3.8571776469, 3.4732052115);
		
		for(int i = 0; i < listOfFiles.length; i++) {
			
			Part part = new Part(listOfFiles[i]);
			
			if(part.getSize().equals(Size.NORMAL)) {
				
				ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + part.getX(), location.getY() + part.getY(),
						location.getZ() + part.getZ()), EntityType.ARMOR_STAND);
				
				as.setRemoveWhenFarAway(false);
				as.setGravity(false);
				as.setVisible(false);
				as.setBasePlate(false);
				as.setCanPickupItems(false);
				as.setLeftLegPose(legs);
				as.setRightLegPose(legs);
				
				Quaternion quaternion = new Quaternion(new EulerAngle(part.getAngleX(), part.getAngleY(), part.getAngleZ()));
				
	            as.setHeadPose(new EulerAngle(quaternion.getX(), quaternion.getY(), quaternion.getZ()));
				as.setHelmet(new ItemStack(part.getID(), (byte) part.getData()));
				
				holders.add(as);
			}
			
			if(part.getSize().equals(Size.SMALL)) {
						
				ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + part.getX(), location.getY() + part.getY(),
						location.getZ() + part.getZ()), EntityType.ARMOR_STAND);
				
				as.setRemoveWhenFarAway(false);
				as.setGravity(false);
				as.setVisible(false);
				as.setBasePlate(false);
				as.setCanPickupItems(false);
				as.setLeftLegPose(legs);
				as.setRightLegPose(legs);
				
				as.setSmall(true);
				
				Quaternion quaternion = new Quaternion(new EulerAngle(part.getAngleX(), part.getAngleY(), part.getAngleZ()));
				
	            as.setHeadPose(new EulerAngle(quaternion.getX(), quaternion.getY(), quaternion.getZ()));
				as.setHelmet(new ItemStack(part.getID(), (byte) part.getData()));
				
				holders.add(as);
			}
			
			if(part.getSize().equals(Size.MINIMUM)) {
				
				ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + part.getX(), location.getY() + part.getY(),
						location.getZ() + part.getZ()), EntityType.ARMOR_STAND);
				
				as.setRemoveWhenFarAway(false);
				as.setGravity(false);
				as.setVisible(false);
				as.setBasePlate(false);
				as.setCanPickupItems(false);
				as.setLeftLegPose(legs);
				as.setRightLegPose(legs);
				as.setArms(true);
				
				as.setRightArmPose(arms);
				as.setItemInHand(new ItemStack(part.getID(), (byte) part.getData()));
				
				holders.add(as);
			}
			
			if(part.getSize().equals(Size.ATOMIQUE)) {
				
				ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + part.getX(), location.getY() + part.getY(),
						location.getZ() + part.getZ()), EntityType.ARMOR_STAND);
				
				as.setRemoveWhenFarAway(false);
				as.setGravity(false);
				as.setVisible(false);
				as.setBasePlate(false);
				as.setCanPickupItems(false);
				as.setLeftLegPose(legs);
				as.setRightLegPose(legs);
				as.setArms(true);
				
				as.setSmall(true);
				as.setRightArmPose(small_arms);
				as.setItemInHand(new ItemStack(part.getID(), (byte) part.getData()));
				
				holders.add(as);
			}
			parts.add(part);
		}
		
		if(!CacheModel.holding.containsKey(player)) {
			CacheModel.holding.put(player, holders);
		}else {
			CacheModel.holding.replace(player, holders);
		}
		player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).model_loaded(), player.getUniqueId()));
	}
	
	public void setupLocations() {
		
		File partsFolder = new File(this.getPartsPath());
		
		for(int i = 0; i < partsFolder.listFiles().length; i++) {
			
			Part part = new Part(partsFolder.listFiles()[i]);
			
			Location location = new Location(this.getWorld(), this.getX() + part.getX(), this.getY() + part.getY(), this.getZ() + part.getZ());
			partsLocations.add(location);
		}
	}
	
	public void remove() {
		
		for(Entity entity : this.getWorld().getEntities()) {
			if(partsLocations.contains(entity.getLocation())) {
				entity.remove();
			}
		}
	}
}