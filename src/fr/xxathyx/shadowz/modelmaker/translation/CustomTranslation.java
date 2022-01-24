package fr.xxathyx.shadowz.modelmaker.translation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.xxathyx.shadowz.modelmaker.Main;

public class CustomTranslation {
	
	private File file;
	private Main plugin = Main.getPlugin(Main.class);
	
	public CustomTranslation(File file) {
		this.file = file;
	}
	
	public CustomTranslation() {
		
	}
	
	private FileConfiguration customConfig;
	
	public void createCustomTranslation(String langage, String description, String author, String version) throws URISyntaxException, IOException, InvalidConfigurationException {
		
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
		file = new File(plugin.getDataFolder() + "/translations/", langage + ".yml");
	    
		if(!file.exists()) {
			file.getParentFile().mkdir();
		}
		
		if(!file.exists()) {
			
			URI uri = Translater.class.getResource("translations/GB.yml").toURI();

			if("jar".equals(uri.getScheme())){
			    for (FileSystemProvider provider: FileSystemProvider.installedProviders()) {
			        if (provider.getScheme().equalsIgnoreCase("jar")) {
			            try {
			                provider.getFileSystem(uri);
			            } catch (FileSystemNotFoundException e) {
			                provider.newFileSystem(uri, Collections.emptyMap());
			            }
			        }
			    }
			}
			
			Path source = Paths.get(uri);
			
			System.out.print("Generating langage: " + langage);
			
			Files.copy(source, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		
		customConfig = new YamlConfiguration();
		customConfig.load(file);
		
		customConfig.set("translation.custom.name", langage);
		customConfig.set("translation.custom.description", description);
		customConfig.set("translation.custom.author", author);
		customConfig.set("translation.custom.version", version);
		
		customConfig.save(file);
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
	
	public String getName() {
		return this.getConfigFile().getString("translation.custom.name");
	}
	
	public String getDescription() {
		return this.getConfigFile().getString("translation.custom.description");
	}
	
	public String getAuthor() {
		return this.getConfigFile().getString("translation.custom.author");
	}
	
	public String getVersion() {
		return this.getConfigFile().getString("translation.custom.version");
	}
}