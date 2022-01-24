package fr.xxathyx.shadowz.modelmaker.part;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.math.Quaternion;
import fr.xxathyx.shadowz.modelmaker.part.hitbox.Size;
import fr.xxathyx.shadowz.modelmaker.util.Content;
import fr.xxathyx.shadowz.modelmaker.util.YamlConfigurationManager;

public class Part {
	
	private File file;
	
	private Main plugin = Main.getPlugin(Main.class);
	private YamlConfigurationManager ycm = new YamlConfigurationManager();
	
	private FileConfiguration fileconfiguration;
	
	private Quaternion quaternion;
	
	public Part(File file) {
		this.file = file;
	}
		
	public Part() {
		
	}
	
	public void createPart(File file, String modelName, String modelDirectory, String owner, UUID uuid, int modelPart, int partID, int partData, Size partSize, double eulerAngleX,
			double eulerAngleY, double eulerAngleZ, double x, double y, double z) throws IOException {
		
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
	    fileconfiguration = new YamlConfiguration();
	    
	    fileconfiguration.set("model.name",  modelName);
	    fileconfiguration.set("model.directory", modelDirectory);
	    fileconfiguration.set("model.owner", owner);
	    fileconfiguration.set("model.uuid", uuid.toString());
	    
	    fileconfiguration.set("part.index", modelPart);
	    fileconfiguration.set("part.id", partID);
	    fileconfiguration.set("part.data", partData);
	    fileconfiguration.set("part.size", partSize.toString());
	    
	    fileconfiguration.set("eulerangle.x", eulerAngleX);
	    fileconfiguration.set("eulerangle.y", eulerAngleY);
	    fileconfiguration.set("eulerangle.z", eulerAngleZ);
	    
	    fileconfiguration.set("location.x", x);
	    fileconfiguration.set("location.y", y);
	    fileconfiguration.set("location.z", z);
	    
	    fileconfiguration.save(file);
	}
	
	public FileConfiguration getConfigFile() {
		
		fileconfiguration = new YamlConfiguration();
		
			try {
				fileconfiguration.load(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		return fileconfiguration;
    }
	
	public String getModelName() { 
		return this.getConfigFile().getString("model.name");
	}
	
	public String getModelDirectory() {
		return this.getConfigFile().getString("model.directory");
	}
	
	public String getModelOwner() {
		return this.getConfigFile().getString("model.owner");
	}
	
	public String getUUID() {
		return this.getConfigFile().getString("model.uuid");
	}
	
	public int getIndex() {
		return Integer.parseInt(this.getConfigFile().getString("part.index"));
	}
	
	public int getID() {
		return Integer.parseInt(this.getConfigFile().getString("part.id"));
	}
	
	public int getData() {		
		return Integer.parseInt(this.getConfigFile().getString("part.data"));
	}
	
	public Size getSize() {
		
		if(this.getConfigFile().getString("part.size").equalsIgnoreCase("NORMAL")) {
			return Size.NORMAL;
		}
		
		if(this.getConfigFile().getString("part.size").equalsIgnoreCase("SMALL")) {
			return Size.SMALL;
		}
		
		if(this.getConfigFile().getString("part.size").equalsIgnoreCase("MINIMUM")) {
			return Size.MINIMUM;
		}
		
		if(this.getConfigFile().getString("part.size").equalsIgnoreCase("ATOMIQUE")) {
			return Size.ATOMIQUE;
		}
		return null;
	}
		
	public Quaternion getQuaternion() {
		return this.quaternion;
	}
	
	public double getAngleX() {
		return Double.parseDouble(this.getConfigFile().getString("eulerangle.x"));
	}
	
	public double getAngleY() {
		return Double.parseDouble(this.getConfigFile().getString("eulerangle.y"));
	}
	
	public double getAngleZ() {		
		return Double.parseDouble(this.getConfigFile().getString("eulerangle.z"));
	}
	
	public double getX() {
		return Double.parseDouble(this.getConfigFile().getString("location.x"));
	}
	
	public double getY() {	
		return Double.parseDouble(this.getConfigFile().getString("location.y"));
	}
	
	public double getZ() {
		return Double.parseDouble(this.getConfigFile().getString("location.z"));
	}
	
	public void setModelName(String name) {
		ycm.edit(file, "model.name", name);
	}
	
	public void setModelDirectory(String directory) {
		ycm.edit(file, "model.directory", directory);
	}
	
	public void setModelOwner(String owner) {
		ycm.edit(file, "model.owner", owner);
	}
	
	public void setIndex(int index) {
		ycm.edit(file, "part.data", String.valueOf(index));
	}
	
	public void setID(int id) {
		ycm.edit(file, "part.id", String.valueOf(id));
	}
	
	public void setData(int data) {
		ycm.edit(file, "part.data", String.valueOf(data));
	}
	
	public void setSize(Size size) {
		ycm.edit(file, "part.size", size.toString());
	}
	
	public void setQuaternion(Quaternion quaternion) {
		this.quaternion = quaternion;
	}
	
	public void setAngleX(double x) {
		
		if(x >= Math.PI) {
			ycm.edit(file, "eulerangle.x", String.valueOf(0));
			return;
		}
		ycm.edit(file, "eulerangle.x", String.valueOf(x));
	}
	
	public void setAngleY(double y) {
		
		if(y >= Math.PI) {
			ycm.edit(file, "eulerangle.y", String.valueOf(0));
			return;
		}
		ycm.edit(file, "eulerangle.y", String.valueOf(y));
	}
	
	public void setAngleZ(double z) {
		
		if(z >= Math.PI) {
			ycm.edit(file, "eulerangle.z", String.valueOf(0));
			return;
		}
		ycm.edit(file, "eulerangle.z", String.valueOf(z));
	}
	
	public void setX(double x) {
		ycm.edit(file, "location.x", String.valueOf(x));
	}
	
	public void setY(double y) {
		ycm.edit(file, "location.y", String.valueOf(y));
	}
	
	public void setZ(double z) {
		ycm.edit(file, "location.z", String.valueOf(z));
	}
	
	public void setLocation(double x, double y, double z) {
		ycm.edit(file, "location.x", String.valueOf(x));
		ycm.edit(file, "location.y", String.valueOf(y));
		ycm.edit(file, "location.z", String.valueOf(z));
	}
	
	public void updatePart(File file) throws IOException {
		
		String partPath = file.getPath();
		
		Part part = new Part(file);
    	
	    if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
	    file.delete();
	    
	    File newfile = new File(partPath + part.getIndex() + ".yml");
		
	    fileconfiguration = new YamlConfiguration();
	    
	    fileconfiguration.set("model.name",  part.getModelName());
	    fileconfiguration.set("model.directory", part.getModelDirectory());
	    fileconfiguration.set("model.owner", part.getModelOwner());
	    
	    fileconfiguration.set("part.index", part.getIndex());
	    fileconfiguration.set("part.id", part.getID());
	    fileconfiguration.set("part.data", part.getData());
	    fileconfiguration.set("part.size", part.getSize());
	    
	    fileconfiguration.set("eulerangle.x", part.getAngleX());
	    fileconfiguration.set("eulerangle.y", part.getAngleY());
	    fileconfiguration.set("eulerangle.z", part.getAngleZ());
	    
	    fileconfiguration.set("location.x", part.getX());
	    fileconfiguration.set("location.y", part.getY());
	    fileconfiguration.set("location.z", part.getZ());
		
	    fileconfiguration.save(newfile);
	}
	
	@SuppressWarnings("deprecation")
	public void load(Location location) {
		
		location = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
		
		EulerAngle reversed = new EulerAngle(3.14159, 0, 0);
		EulerAngle arms = new EulerAngle(-0.244346, 0.785398, 0);
		EulerAngle small_arms = new EulerAngle(2.4260076603, 3.8571776469, 3.4732052115);
		
		ItemStack item = new ItemStack(this.getID(), 1, (short) this.getData());
		
		if(this.getSize().equals(Size.NORMAL)) {
			
			ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + getX(), location.getY() + getY(),
					location.getZ() + getZ()), EntityType.ARMOR_STAND);
			
			as.setRemoveWhenFarAway(false);
			as.setGravity(false);
			as.setVisible(false);
			as.setBasePlate(false);
			as.setCanPickupItems(false);
			as.setLeftLegPose(reversed);
			as.setRightLegPose(reversed);
			as.setBodyPose(reversed);
        	
			Quaternion quaternion = new Quaternion(new EulerAngle(this.getAngleX(), this.getAngleY(), this.getAngleZ()));
			
            as.setHeadPose(new EulerAngle(quaternion.getX(), quaternion.getY(), quaternion.getZ()));
            
			as.setHelmet(item);
		}
		
		if(this.getSize().equals(Size.SMALL)) {
					
			ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + getX(), location.getY() + getY(),
					location.getZ() + getZ()), EntityType.ARMOR_STAND);
			
			as.setRemoveWhenFarAway(false);
			as.setGravity(false);
			as.setVisible(false);
			as.setBasePlate(false);
			as.setCanPickupItems(false);
			as.setLeftLegPose(reversed);
			as.setRightLegPose(reversed);
			as.setBodyPose(reversed);
			
			as.setSmall(true);
			
			Quaternion quaternion = new Quaternion(new EulerAngle(this.getAngleX(), this.getAngleY(), this.getAngleZ()));
			
            as.setHeadPose(new EulerAngle(quaternion.getX(), quaternion.getY(), quaternion.getZ()));
			as.setHelmet(item);
		}
		
		if(this.getSize().equals(Size.MINIMUM)) {
			
			ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + getX(), location.getY() + getY(),
					location.getZ() + getZ()), EntityType.ARMOR_STAND);
			
			as.setRemoveWhenFarAway(false);
			as.setGravity(false);
			as.setVisible(false);
			as.setBasePlate(false);
			as.setCanPickupItems(false);
			as.setLeftLegPose(reversed);
			as.setRightLegPose(reversed);
			as.setBodyPose(reversed);
			as.setArms(true);
			
			as.setRightArmPose(arms);
			as.setItemInHand(item);
		}
		
		if(this.getSize().equals(Size.ATOMIQUE)) {
			
			ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + getX(), location.getY() + getY(),
					location.getZ() + getZ()), EntityType.ARMOR_STAND);
			
			as.setRemoveWhenFarAway(false);
			as.setGravity(false);
			as.setVisible(false);
			as.setBasePlate(false);
			as.setCanPickupItems(false);
			as.setLeftLegPose(reversed);
			as.setRightLegPose(reversed);
			as.setBodyPose(reversed);
			as.setArms(true);
			
			as.setSmall(true);
			as.setRightArmPose(small_arms);
			as.setItemInHand(item);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void load(Location location, boolean gravity) {
		
		location = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
		
		EulerAngle reversed = new EulerAngle(3.14159, 0, 0);
		EulerAngle arms = new EulerAngle(-0.244346, 0.785398, 0);
		EulerAngle small_arms = new EulerAngle(2.4260076603, 3.8571776469, 3.4732052115);
		
		ItemStack item = new ItemStack(this.getID(), 1, (short) this.getData());
		
		if(this.getSize().equals(Size.NORMAL)) {
			
			ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + getX(), (getAdjustmentY(new Location(location.getWorld(),
					location.getX() + getX(), location.getY() + getY(), location.getZ() + getZ())) + getY()), location.getZ() + getZ()), EntityType.ARMOR_STAND);
			
			as.setRemoveWhenFarAway(false);
			as.setGravity(false);
			as.setVisible(false);
			as.setBasePlate(false);
			as.setCanPickupItems(false);
			as.setLeftLegPose(reversed);
			as.setRightLegPose(reversed);
			as.setBodyPose(reversed);
        	
			Quaternion quaternion = new Quaternion(new EulerAngle(this.getAngleX(), this.getAngleY(), this.getAngleZ()));
			
            as.setHeadPose(new EulerAngle(quaternion.getX(), quaternion.getY(), quaternion.getZ()));
            
			as.setHelmet(item);
		}
		
		if(this.getSize().equals(Size.SMALL)) {
					
			ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + getX(), (getAdjustmentY(new Location(location.getWorld(),
					location.getX() + getX(), location.getY() + getY(), location.getZ() + getZ())) + getY()), location.getZ() + getZ()), EntityType.ARMOR_STAND);
			
			as.setRemoveWhenFarAway(false);
			as.setGravity(false);
			as.setVisible(false);
			as.setBasePlate(false);
			as.setCanPickupItems(false);
			as.setLeftLegPose(reversed);
			as.setRightLegPose(reversed);
			as.setBodyPose(reversed);
			
			as.setSmall(true);
			
			Quaternion quaternion = new Quaternion(new EulerAngle(this.getAngleX(), this.getAngleY(), this.getAngleZ()));
			
            as.setHeadPose(new EulerAngle(quaternion.getX(), quaternion.getY(), quaternion.getZ()));
			as.setHelmet(item);
		}
		
		if(this.getSize().equals(Size.MINIMUM)) {
			
			ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + getX(), (getAdjustmentY(new Location(location.getWorld(),
					location.getX() + getX(), location.getY() + getY(), location.getZ() + getZ())) + getY()), location.getZ() + getZ()), EntityType.ARMOR_STAND);
			
			as.setRemoveWhenFarAway(false);
			as.setGravity(false);
			as.setVisible(false);
			as.setBasePlate(false);
			as.setCanPickupItems(false);
			as.setLeftLegPose(reversed);
			as.setRightLegPose(reversed);
			as.setBodyPose(reversed);
			as.setArms(true);
			
			as.setRightArmPose(arms);
			as.setItemInHand(item);
		}
		
		if(this.getSize().equals(Size.ATOMIQUE)) {
			
			ArmorStand as = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + getX(), (getAdjustmentY(new Location(location.getWorld(),
					location.getX() + getX(), location.getY() + getY(), location.getZ() + getZ())) + getY()), location.getZ() + getZ()), EntityType.ARMOR_STAND);
			
			as.setRemoveWhenFarAway(false);
			as.setGravity(false);
			as.setVisible(false);
			as.setBasePlate(false);
			as.setCanPickupItems(false);
			as.setLeftLegPose(reversed);
			as.setRightLegPose(reversed);
			as.setBodyPose(reversed);
			as.setArms(true);
			
			as.setSmall(true);
			as.setRightArmPose(small_arms);
			as.setItemInHand(item);
		}
	}
	
	public double getAdjustmentY(Location location) {
		
		double y = location.getY();
		
		if(Content.getTranslucentBlocks().contains(location.getBlock().getType())) {
			return y;
		}
		
        while(new Location(location.getWorld(), location.getX(), y, location.getZ()).getBlock().getType() == Material.AIR) {
            y--;
        }
		return y;
	}
	
	public void remove() {
		file.delete();
	}
	
	public void replace(ArmorStand armorStand, Location location) {
		armorStand.remove();
		this.load(location);
	}
}