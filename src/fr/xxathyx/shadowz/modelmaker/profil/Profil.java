package fr.xxathyx.shadowz.modelmaker.profil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.util.Host;

public class Profil{
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private File file;
	private double precision = 0.30;
	
	private FileConfiguration fileconfiguration;
	
	public Profil(File file) {
		this.file = file;
	}
	
	public Profil(Player player) {
		File profilFile = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId() + ".yml");
		this.file = profilFile;
	}
		
	public Profil() {
		
	}
	
	public boolean exists() {
        return file.exists();
    }
	
	public void createProfil(String date, UUID uuid, String name, String ipAdress) throws IOException {
		
		Host host = new Host(ipAdress);
		
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
		file = new File(plugin.getDataFolder() + "/profils/", uuid + ".yml");
		
	    fileconfiguration = new YamlConfiguration();
	    
	    fileconfiguration.set("created", date);
	    fileconfiguration.set("player.uuid",  uuid.toString());
	    fileconfiguration.set("player.name", name);
	    if(host.getOfficials().contains(host.getCOUNTRYCODE())) {
		    fileconfiguration.set("translation.langage", host.getCOUNTRYCODE());
	    }else {
		    fileconfiguration.set("translation.langage", "GB");
	    }
	    fileconfiguration.set("translation.adaptive", false);
	    
	    fileconfiguration.set("models.directory", uuid.toString() + "/models/");
	    fileconfiguration.set("count.models", 0);
	    fileconfiguration.set("count.models_progress", 0);
	    fileconfiguration.set("precision scale", precision);
	    
	    fileconfiguration.save(file);
	}
	
	public void createDataFolder(UUID uuid) throws IOException {
		
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
		Path models = Paths.get(plugin.getDataFolder() + "//data//", uuid + "//models");
		
		if (!Files.exists(models)) {
			Files.createDirectories(models);
        }
		
		Path models_progress = Paths.get(plugin.getDataFolder() + "//data//", uuid + "//models progress");
		
		if (!Files.exists(models_progress)) {
			Files.createDirectories(models_progress);
        }
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
	
	public String getCreationDate() {
		return this.getConfigFile().getString("created");
	}
	
	public String getPlayerUUID() {		
		return this.getConfigFile().getString("player.uuid");
	}
	
	public String getPlayerName() {
		return this.getConfigFile().getString("player.name");
	}
	
	public String getModelsDirectory() {		
		return this.getConfigFile().getString("models.directory");
	}
	
    public double getPrecision() {	
		return Double.parseDouble(this.getConfigFile().getString("precision scale"));
	}
    
	public String getLangage() {
		return this.getConfigFile().getString("translation.langage");
	}
	
	public boolean isAdaptive() {
		return getConfigFile().getBoolean("langage.adaptive");
	}
	
	public Profil getProfil() {
		return this;
	}
	
	public int getModelsCount() {
		
		long count = 0;
		
		try {
			 count = Files.find(Paths.get(plugin.getDataFolder() + "//data//", this.getPlayerUUID() + "//models//"), 1,
					 (path, attributes) -> attributes.isDirectory()).count() - 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int i = (int) count;
		
		return i;
	}
	
    public int getModelsProgressCount() {
		
		long count = 0;
		
		try {
			count = Files.find(Paths.get(plugin.getDataFolder() + "//data//", this.getPlayerUUID() + "//models progress//"),
					 1,  (path, attributes) -> attributes.isDirectory()).count() - 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int i = (int) count;
		
		return i;
	}
    
    public void setLangage(Player player, String countryCode) throws IOException, InvalidConfigurationException {
    	
		File profilFile = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId() + ".yml");
		
	    fileconfiguration = new YamlConfiguration();
	    
	    fileconfiguration.load(profilFile);
	    
	    fileconfiguration.set("translation.langage", countryCode);
	    
	    fileconfiguration.save(profilFile);
	}
    
    public void updateConfig(Player player) throws URISyntaxException, IOException {
		
        File file = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId() + ".yml");
		
		Profil profil = new Profil(file);
    	
    	String creation_date = profil.getCreationDate();
    	String player_name = profil.getPlayerName();
    	String player_uuid = profil.getPlayerUUID();
    	
    	String model_directory = profil.getModelsDirectory();
    	
    	String countryCode = profil.getLangage();
    	
    	int modelsCount = profil.getModelsCount();
    	int modelsProgressCount = profil.getModelsProgressCount();
    	
    	double precision_scale = profil.getPrecision();
    	
	    fileconfiguration = new YamlConfiguration();
	    
	    fileconfiguration.set("created", creation_date);
	    fileconfiguration.set("player.uuid",  player_uuid);
	    fileconfiguration.set("player.name", player_name);
	    fileconfiguration.set("langage", countryCode);
	    fileconfiguration.set("models.directory", model_directory);
	    fileconfiguration.set("count.models", modelsCount);
	    fileconfiguration.set("count.models_progress", modelsProgressCount);
	    fileconfiguration.set("precision scale", precision_scale);
    	
	    if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
	    file.delete();
	    
	    File newfile = new File(plugin.getDataFolder() + "/profils/", player_uuid + ".yml");
		
		fileconfiguration.save(newfile);
    }
}