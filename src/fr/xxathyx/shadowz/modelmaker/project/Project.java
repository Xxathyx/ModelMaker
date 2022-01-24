package fr.xxathyx.shadowz.modelmaker.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.xxathyx.shadowz.modelmaker.model.Model;
import fr.xxathyx.shadowz.modelmaker.project.logs.Logs;
import fr.xxathyx.shadowz.modelmaker.util.YamlConfigurationManager;

public class Project {
		
	private YamlConfigurationManager yamlConfigurationManager;
	private FileConfiguration fileconfiguration;
	private File projectFile;
		
	public Project(File projectFile) {
		this.projectFile = projectFile;
	}
	
	public File getFile() {
		return projectFile;
	}
	
	public FileConfiguration getConfigFile() {
		
		fileconfiguration = new YamlConfiguration();
		
			try {
				fileconfiguration.load(projectFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		return fileconfiguration;
    }
	
	public Location getBase() throws FileNotFoundException, IOException, InvalidConfigurationException {
				
		fileconfiguration = new YamlConfiguration();	
		fileconfiguration.load(projectFile);
		
		double x = Double.parseDouble(fileconfiguration.getString("project.base.point.x"));
		double y = Double.parseDouble(fileconfiguration.getString("project.base.point.y"));
		double z = Double.parseDouble(fileconfiguration.getString("project.base.point.z"));
		
		return new Location(Bukkit.getWorld(fileconfiguration.getString("project.base.point.world")), x, y, z);
		
	}
	
	public Model getModel() {
		
		File modelFile = new File(this.getModelPath());
		
		Model model = new Model(modelFile);
		
		return model;
	}
	
	public File getLogsFile() {
		return new File(this.getFile().getParentFile() + "/edits/", "logs.txt");
	}
	
	public Logs getLogs() {
		return new Logs(getLogsFile());
	}
	
	public String getModelName() {
		return this.getConfigFile().getString("project.model.name");
	}
	
	public String getModelPath() {
		return this.getConfigFile().getString("project.model.path");
	}
	
	public String getLastEdition() {
		return this.getConfigFile().getString("project.edition.last");
	}
	
	public void setBase(Location location) {
		
		yamlConfigurationManager = new YamlConfigurationManager();
		
		yamlConfigurationManager.edit(projectFile, "project.base.point.x", String.valueOf(location.getX()));
		yamlConfigurationManager.edit(projectFile, "project.base.point.y", String.valueOf(location.getY()));
		yamlConfigurationManager.edit(projectFile, "project.base.point.z", String.valueOf(location.getZ()));
		yamlConfigurationManager.edit(projectFile, "project.base.point.world", location.getWorld().getName());
	}
}