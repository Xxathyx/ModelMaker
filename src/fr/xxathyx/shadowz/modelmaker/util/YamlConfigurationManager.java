package fr.xxathyx.shadowz.modelmaker.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class YamlConfigurationManager {
	
	private FileConfiguration fileconfiguration;
	
	public void edit(File file, String section, String newText) {
		
		fileconfiguration = new YamlConfiguration();
		
		try {
			fileconfiguration.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
		fileconfiguration.set(section,  newText);
		
		try {
			fileconfiguration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(File file, String section, String newText) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		fileconfiguration = new YamlConfiguration();
		
		fileconfiguration.load(file);
		
		try {
			fileconfiguration.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
		fileconfiguration.set(section,  newText);
		
		try {
			fileconfiguration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void add(File file, String section, String text) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		fileconfiguration = new YamlConfiguration();
		
		fileconfiguration.load(file);
		
		try {
			fileconfiguration.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
		fileconfiguration.set(section,  text);
		
		try {
			fileconfiguration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void remove(File file, String section) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		fileconfiguration = new YamlConfiguration();
		
		fileconfiguration.load(file);
		
		try {
			fileconfiguration.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
		fileconfiguration.set(section,  null);
		
		try {
			fileconfiguration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}