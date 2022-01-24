package fr.xxathyx.shadowz.modelmaker.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.EulerAngle;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.math.Quaternion;
import fr.xxathyx.shadowz.modelmaker.math.Values;
import fr.xxathyx.shadowz.modelmaker.part.Part;
import fr.xxathyx.shadowz.modelmaker.part.hitbox.Size;
import fr.xxathyx.shadowz.modelmaker.project.Project;
import fr.xxathyx.shadowz.modelmaker.project.logs.ActionType;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;
import fr.xxathyx.shadowz.modelmaker.util.FacingLocation;

public class Model {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private ArrayList<Part> parts = new ArrayList<Part>();
		
	private File modelFile;
	
	private FileConfiguration fileconfiguration;
	
	public Model(File modelFile) {
		this.modelFile = modelFile;
		this.initParts();
	}
		
	public Model() {
		
	}
	
	public Model getModel() {
		return this;
	}
	
	public FileConfiguration getConfigFile() {
		
		fileconfiguration = new YamlConfiguration();
		
			try {
				fileconfiguration.load(modelFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		return fileconfiguration;
    }
	
	public String getName() {
		return this.getConfigFile().getString("model.name");
	}
	
	public String getOwner() {
		return this.getConfigFile().getString("model.owner");
	}
	
	public UUID getUUID() {
		return UUID.fromString(this.getConfigFile().getString("model.uuid"));
	}
	
	public ArrayList<Part> getParts() {		
		return parts;
	}
	
	public String getPartsPath() {		
		return this.getConfigFile().getString("model.partsPath");
	}
	
	public void setParts(ArrayList<Part> parts) {
		this.parts = parts;
	}
	
	public String getCreationDate() {
		return this.getConfigFile().getString("model.created");
	}
	
	public String getLoadedTimes() {
		return this.getConfigFile().getString("model.loaded");
	}
	
	public File getModelFile() {
		return this.modelFile;
	}
	
	public void initParts() {
		
	    Path partsFolder = Paths.get(modelFile.getParentFile() + "//parts//");
	    
		File[] listOfFiles = partsFolder.toFile().listFiles();
		
		for(int i = 0; i < listOfFiles.length; i++) {
			
			Part part = new Part(listOfFiles[i]);
			
			parts.add(part);
		}
	}
	
	public File getProjectFile() {		
		return new File(this.getModelFile().getParentFile() + "/project/", "project.yml");
	}
	
	public Project getProject() {
		return new Project(getProjectFile());
	}
	
	@SuppressWarnings("deprecation")
	public void createModelFile(String name, String owner, UUID playerUUID, String date) throws IOException {
		
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
				
		modelFile = new File(plugin.getDataFolder() + "/data/" + playerUUID + "/models progress/" + name + "/", name + ".yml");
		
		if(!modelFile.exists()) {
			modelFile.createNewFile();
		}
		
		Path parts = Paths.get(plugin.getDataFolder() + "//data//" + playerUUID + "//models progress//" + name + "//parts//");
		
	    fileconfiguration = new YamlConfiguration();
	    
	    fileconfiguration.set("model.name", name);
	    fileconfiguration.set("model.owner", owner);
	    fileconfiguration.set("model.uuid", playerUUID.toString());
	    fileconfiguration.set("model.partsPath", parts.toString());
	    fileconfiguration.set("model.created", date);
	    
	    fileconfiguration.save(modelFile);
	    
	    File projectFolder = new File(modelFile.getParentFile() + "/project/");
		File projectFile = new File(projectFolder, "project.yml");
		
		File editsFolder = new File(projectFile.getParentFile() + "/edits/");
		File editsFile = new File(editsFolder, "logs.txt");
		
		File backupsFolder = new File(projectFile.getParentFile() + "/backups/");
		
		if(!projectFolder.exists()) {
			try {
				projectFolder.mkdir();
				editsFolder.mkdir();
				editsFile.createNewFile();
				backupsFolder.mkdir();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		fileconfiguration = new YamlConfiguration();
		
		fileconfiguration.set("project.model.name", this.getName());
		fileconfiguration.set("project.model.path", this.getModelFile().getPath());
		
		fileconfiguration.set("project.base.point.x", 0.0);
		fileconfiguration.set("project.base.point.y", 0.0);
		fileconfiguration.set("project.base.point.z", 0.0);
		
		if(Bukkit.getPlayer(playerUUID).isOnGround()) {
			fileconfiguration.set("project.base.point.world", Bukkit.getPlayer(playerUUID).getWorld().getName());
		}else {
			fileconfiguration.set("project.base.point.world", Bukkit.getWorlds().get(0).getName());
		}
		
		fileconfiguration.set("project.edition.last", date);
		
		fileconfiguration.save(projectFile);
		
		getProject().getLogs().write(ActionType.CREATE.toString());
	}
	
	public void exportModel() throws IOException, InvalidConfigurationException {
				
		Path origin = Paths.get(plugin.getDataFolder() + "//data//" + this.getUUID() + "//models progress//" + this.getName());
		Path destination = Paths.get(plugin.getDataFolder() + "//data//" + this.getUUID() + "//models//" + this.getName() + "//");
		
		Path parts = Paths.get(plugin.getDataFolder() + "//data//" + this.getUUID() + "//models//" + this.getName() + "//parts//");
		
		File modelFile = new File(destination.toString() + "//" + this.getName() + ".yml");
		
		if (!Files.exists(destination)) {
            try {
            	FileUtils.copyDirectory(origin.toFile(), destination.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
	    	FileConfiguration customConfig;
	    	customConfig = new YamlConfiguration();
    	    
	    	customConfig.set("model.name", this.getName());
	    	customConfig.set("model.owner", this.getOwner());
	    	customConfig.set("model.uuid", this.getUUID().toString());
	    	customConfig.set("model.partsPath", parts.toString());
	    	customConfig.set("model.created", this.getCreationDate());
	    	customConfig.set("model.loaded", 0);
    	    
    	    customConfig.save(modelFile);
    	    
    	    for(int i = 0; i < parts.toFile().listFiles().length; i++) {
    	    	
    	    	customConfig.load(parts.toFile().listFiles()[i]);
    	    	
    			customConfig.set("model.directory", parts.toString());
    			 
    			customConfig.save(parts.toFile().listFiles()[i]);
    	    }
        }
	}
	
	public void createPartsFolder(String name, UUID uuid) throws IOException {
		
		Path parts = Paths.get(plugin.getDataFolder() + "//data//" + uuid + "//models progress//" + name + "//parts");
		
		if (!Files.exists(parts)) {
			Files.createDirectories(parts);
        }
		
	}
	
	public void createLoadedFile(Location location) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		int times = Integer.valueOf(this.getLoadedTimes());
		
		File loadedFile = new File(plugin.getDataFolder() + "/data/" + this.getUUID() + "/models/" + this.getName() + "/loaded/", "loaded_" + times++ + ".yml");
		Path loadedFileParent = Paths.get(plugin.getDataFolder() + "//data//" + this.getUUID() + "//models//" + this.getName() + "//loaded");
		
		 FileConfiguration customConfig;
		 customConfig = new YamlConfiguration();
		 
		 customConfig.load(this.getModelFile());
		 		 
		 customConfig.set("model.loaded", times++);
		 
		 customConfig.save(this.getModelFile());
		 
		 if(!(loadedFileParent.toFile().exists())) {
			 loadedFileParent.toFile().mkdir();
		 }
		 
		 loadedFile.createNewFile();
		
		Path parts = Paths.get(plugin.getDataFolder() + "//data//" + this.getUUID() + "//models//" + this.getName() + "//parts");
		
		Date date = new Date();
		DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
		
        fileconfiguration = new YamlConfiguration();
	    
	    fileconfiguration.set("model.name", this.getName());
	    fileconfiguration.set("model.owner", this.getOwner());
	    fileconfiguration.set("model.uuid", this.getUUID().toString());
	    fileconfiguration.set("model.partsPath", parts.toFile().getPath());
	    fileconfiguration.set("model.created", shortDateFormat.format(date).toString());
	    fileconfiguration.set("model.index", times-2);
	    fileconfiguration.set("model.location.world", location.getWorld().getName());
	    fileconfiguration.set("model.location.x", location.getX());
	    fileconfiguration.set("model.location.y", location.getY());
	    fileconfiguration.set("model.location.z", location.getZ());
	    
	    fileconfiguration.save(loadedFile);
	}
	
	public void updateModelFile(File file) throws IOException {
		
		String modelFilePath = file.getPath();
		
		Model model = new Model(file);
		
    	String name = model.getName();
    	String owner = model.getOwner();
    	String partsPath = model.getPartsPath();
    	
	    fileconfiguration = new YamlConfiguration();
	    
	    fileconfiguration.set("model.name", name);
	    fileconfiguration.set("model.owner", owner);
	    fileconfiguration.set("model.partsPath", partsPath);
    	
	    if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
	    file.delete();
	    
	    File newfile = new File(modelFilePath, model.getName() + ".yml");
	    
		if(!newfile.exists()) {
			newfile.createNewFile();
		}
		
		fileconfiguration.save(newfile);
	}
	
	public void edit(String path, String data) throws IOException {
		
		String modelFilePath = this.modelFile.getPath();
		
		Model model = new Model(this.modelFile);
		
    	String name = model.getName();
    	String owner = model.getOwner();
    	String partsPath = model.getPartsPath();
    	
	    fileconfiguration = new YamlConfiguration();
	    
	    fileconfiguration.set("model.name", name);
	    fileconfiguration.set("model.owner", owner);
	    fileconfiguration.set("model.partsPath", partsPath);
	    fileconfiguration.set(path, data);
    	
	    if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
	    model.getModelFile().delete();
	    	    
	    File newfile = new File(modelFilePath, model.getName() + ".yml");
	    
		if(!newfile.exists()) {
			newfile.createNewFile();
		}
		
		fileconfiguration.save(newfile);
	}
	
	public void deleteModelFile() {
		this.modelFile.delete();
	}
	
	@SuppressWarnings("deprecation")
	public void load(Player player) {
		
		ArrayList<ArmorStand> holders = new ArrayList<ArmorStand>();
		
		Location location = player.getEyeLocation();
		location = FacingLocation.getLocationFacing(location);
		location = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
		
	    Path partsFolder = Paths.get(plugin.getDataFolder() + "//data//" + player.getUniqueId() + "//models//" + this.getName() + "//parts//");
	    
		File[] listOfFiles = partsFolder.toFile().listFiles();
		
		EulerAngle legs = new EulerAngle(3.14159, 0, 0);
		EulerAngle arms = new EulerAngle(-0.244346, 0.785398, 0);
		EulerAngle small_arms = new EulerAngle(2.4260076603, 3.8571776469, 3.4732052115);
		
		for(int i = 0; i < listOfFiles.length; i++) {
			
			Part part = new Part(listOfFiles[i]);
						
			if(part.getSize().equals(Size.NORMAL)) {
				
				ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX()+part.getX(), location.getY()+part.getY(),
						location.getZ()+part.getZ()), EntityType.ARMOR_STAND);
				
				as.setRemoveWhenFarAway(false);
				as.setGravity(false);
				as.setVisible(false);
				as.setBasePlate(false);
				as.setCanPickupItems(false);
				as.setLeftLegPose(legs);
				as.setRightLegPose(legs);
				                             
				Quaternion quaternion = new Quaternion(new EulerAngle(part.getAngleX(), part.getAngleY(), part.getAngleZ()));
				
	            as.setHeadPose(new EulerAngle(quaternion.getX(), quaternion.getY(), quaternion.getZ()));
	            
				as.setHelmet(new ItemStack(part.getID(), 1, (short) part.getData()));
				
				holders.add(as);
			}
			
			if(part.getSize().equals(Size.SMALL)) {
						
				ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX()+part.getX(), location.getY()+part.getY(),
						location.getZ()+part.getZ()), EntityType.ARMOR_STAND);
				
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
				as.setHelmet(new ItemStack(part.getID(), 1, (short) part.getData()));
				
				holders.add(as);
			}
			
			if(part.getSize().equals(Size.MINIMUM)) {
				
				ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX()+part.getX(), location.getY()+part.getY(),
						location.getZ()+part.getZ()), EntityType.ARMOR_STAND);
				
				as.setRemoveWhenFarAway(false);
				as.setGravity(false);
				as.setVisible(false);
				as.setBasePlate(false);
				as.setCanPickupItems(false);
				as.setLeftLegPose(legs);
				as.setRightLegPose(legs);
				as.setArms(true);
				
				as.setRightArmPose(arms);
				as.setItemInHand(new ItemStack(part.getID(), 1, (short) part.getData()));
				
				holders.add(as);
			}
			
			if(part.getSize().equals(Size.ATOMIQUE)) {
				
				ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX()+part.getX(), location.getY()+part.getY(),
						location.getZ()+part.getZ()), EntityType.ARMOR_STAND);
				
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
				as.setItemInHand(new ItemStack(part.getID(), 1, (short) part.getData()));
				
				holders.add(as);
			}
			parts.add(part);
		}
		
		if(!(CacheModel.holding.containsKey(player))) {
			CacheModel.holding.put(player, holders);
		}else {
			CacheModel.holding.replace(player, holders);
		}
		player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).model_loaded(), player.getUniqueId()));
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack getIcon(ArrayList<Part> parts) {
		
		Values values = new Values();
		
		ItemStack[] ids = new ItemStack[parts.size()];
		
		for(int i = 0;  i < parts.size(); i++) {
			ids[i] = new ItemStack(parts.get(i).getID(), 1, (short) parts.get(i).getData());
		}
		
		File model = new File(plugin.getDataFolder() + "/data/" + this.getUUID() + "/models/" + this.getName() + "/", this.getName() + ".yml");
		File model_progress = new File(plugin.getDataFolder() + "/data/" + this.getUUID() + "/models progress/" + this.getName() + "/", this.getName() + ".yml");
		
		
		if(model.exists()) {
			
			Path loadedFolder = Paths.get(plugin.getDataFolder() + "//data//" + this.getUUID() + "//models//" + this.getName() + "//loaded");
			
			ItemStack item = values.most(Arrays.asList(ids));
			int id = item.getTypeId();
			
			if(loadedFolder.toFile().exists()) {
				
				int count = loadedFolder.toFile().listFiles().length;
				
				if(count > 0) {
					
					ItemStack icon = new ItemStack(id, count, item.getData().getData());
					ItemMeta icon_meta = icon.getItemMeta();
					
					icon_meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + this.getName());
					icon_meta.setLore(Arrays.asList(new String[] { ChatColor.LIGHT_PURPLE + "Owner : " + this.getOwner(), ChatColor.LIGHT_PURPLE + "UUID : " + this.getUUID(),
							ChatColor.LIGHT_PURPLE + "Model creation : " + this.getCreationDate() }));
					
					icon.setItemMeta(icon_meta);
					
					return icon;
				}
			}
			ItemStack icon = new ItemStack(id, 0, item.getData().getData());
			ItemMeta icon_meta = icon.getItemMeta();
			
			icon_meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + this.getName());
			icon_meta.setLore(Arrays.asList(new String[] { ChatColor.LIGHT_PURPLE + "Owner : " + this.getOwner(), ChatColor.LIGHT_PURPLE + "UUID : " + this.getUUID(),
					ChatColor.LIGHT_PURPLE + "Model creation : " + this.getCreationDate() }));
			
			icon.setItemMeta(icon_meta);
			
			return icon;
		}
		
		if(model_progress.exists()) {
			
			if(parts.size() > 0) {
				
				ItemStack item = new ItemStack(values.most(Arrays.asList(ids)));
				int id = item.getTypeId();
				
				ItemStack icon = new ItemStack(id, parts.size(), item.getData().getData());
				ItemMeta icon_meta = icon.getItemMeta();
				
				icon_meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + this.getName());
				icon_meta.setLore(Arrays.asList(new String[] { ChatColor.LIGHT_PURPLE + "Owner : " + this.getOwner(), ChatColor.LIGHT_PURPLE + "UUID : " + this.getUUID(),
						ChatColor.LIGHT_PURPLE + "Model creation : " + this.getCreationDate() }));
				
				icon.setItemMeta(icon_meta);
				
				return icon;
			}
			ItemStack icon = new ItemStack(Material.STONE, 0, (byte) 100);
			ItemMeta icon_meta = icon.getItemMeta();
			
			icon_meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + this.getName());
			icon_meta.setLore(Arrays.asList(new String[] { ChatColor.LIGHT_PURPLE + "Owner : " + this.getOwner(), ChatColor.LIGHT_PURPLE + "UUID : " + this.getUUID(),
					ChatColor.LIGHT_PURPLE + "Model creation : " + this.getCreationDate() }));
			
			icon.setItemMeta(icon_meta);
			
			return icon;
		}
		return null;
	}
}