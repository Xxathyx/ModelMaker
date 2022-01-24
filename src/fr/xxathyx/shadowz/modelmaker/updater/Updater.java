package fr.xxathyx.shadowz.modelmaker.updater;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Event;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.UnknownDependencyException;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;
import fr.xxathyx.shadowz.modelmaker.util.RandomToken;

public class Updater {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private FileConfiguration fileconfiguration;
	
	public void update() throws URISyntaxException, IOException, InvalidConfigurationException, NoSuchFieldException,
	SecurityException, IllegalArgumentException, IllegalAccessException, UnknownDependencyException, InvalidPluginException, InvalidDescriptionException {
				
		File accessFile = new File(plugin.getDataFolder() + "/updater/access.yml");
		
		if(!accessFile.exists()) {
			createFolders();
			extractToken();
		}
		
		if(accessFile.exists()) {
			if(isDefaultToken()) {
				generateToken();
				updateJar();
				sendToken();
			}
			
			if(isTokenRegistered()) {
				sendToken();
				if(isServerValid()) {
					if(toUpdate()) {
						download();
						updateJar();
						return;
					}
				}else {
					System.out.print(ChatColor.RED + "" + ChatColor.BOLD +  "This plugin is not more active cause of leaking, due to non-respect of privacy policy,");
					System.out.print(ChatColor.RED + "if you believe this to be an error, you can contact on Discord -> Xxathyx#5030.");
					Bukkit.getServer().getPluginManager().disablePlugin(plugin);
				}
			}else {
				System.out.print("Token seems to be not registered...");
				return;
			}
		}
	}
	
	public boolean toUpdate() throws UnknownHostException, IOException {
		
		String response;
		
		Socket s = new Socket("localhost", 3001);
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		
		dos.writeUTF(plugin.getDescription().getVersion());
		
		DataInputStream dis = new DataInputStream(s.getInputStream());
		response = dis.readUTF();
		
		if(Double.parseDouble(response) > Double.parseDouble(plugin.getDescription().getVersion())) {
			s.close();
			return true;
		}
		s.close();
		return false;
	}
	
	public void download() throws FileNotFoundException, IOException, InvalidConfigurationException, URISyntaxException {
		
		File updateFolder = new File(plugin.getDataFolder().getParentFile() + "/update/");
		
		for(int i = 0; i < updateFolder.listFiles().length; i++) {
			File file = updateFolder.listFiles()[i];
			file.delete();
		}
		
		File accessFile = new File(plugin.getDataFolder() + "/updater/access.yml");
		
		File jar = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		File newJar = new File(plugin.getDataFolder().getParentFile() + "/update/" + jar.getName());
		
    	URL onlineJar = new URL("http://localhost/ShadowZ/modelmaker/update/ShadowZ Model Maker.jar");
    	FileUtils.copyURLToFile(onlineJar, newJar);
		
    	Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        
        URI uri = URI.create("jar:" + newJar.toURI());
                
       try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
    	   
            Path externalTxtFile = Paths.get(accessFile.getAbsolutePath());
            Path pathInZipfile = zipfs.getPath("/fr/xxathyx/shadowz/modelmaker/updater/access/access.yml");
            
            Files.copy(externalTxtFile, pathInZipfile, StandardCopyOption.REPLACE_EXISTING);
        }
	}
	
	public void updateJar() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException,
	UnknownDependencyException, InvalidPluginException, InvalidDescriptionException, URISyntaxException {
		
		File jar = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		
		System.out.print(Translater.getServerLangage().getMessage(Translater.getServerLangage().plugin_updating()));
		unload(plugin);
		load(jar);
		System.out.print(Translater.getServerLangage().getMessage(Translater.getServerLangage().plugin_updated()));
	}
	
	public boolean isServerValid() throws UnknownHostException, IOException, InvalidConfigurationException {
		
		String response;
		
		Socket s = new Socket("localhost", 3001);
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		
		dos.writeUTF("check " + getToken());
		
		DataInputStream dis = new DataInputStream(s.getInputStream());
		response = dis.readUTF();
		s.close();
		
		if(response.equals("PASS")) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isTokenRegistered() throws IOException, InvalidConfigurationException {
		
	    final URL url = new URL("https://youtube.com/"); //<-- + getToken()
	    HttpURLConnection huc = (HttpURLConnection) url.openConnection();
	    
	    int responseCode = huc.getResponseCode();
	    
	    if(responseCode == 200) {
	    	return true;
	    }
	    
	    if(responseCode == 404) {
	    	System.out.print(getToken() + " token not found.");
	    	return false;
	    }
		return true;
	}
	
	public void registerToken(String token) throws UnknownHostException, IOException, InvalidConfigurationException {
		
		Socket s = new Socket("localhost", 3001);
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		
		dos.writeUTF("register " + getToken());
		s.close();
	}
	
	public void sendToken() throws UnknownHostException, IOException, InvalidConfigurationException {
		
		Socket s = new Socket("localhost", 3001);
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		
		dos.writeUTF("server " + getToken());
		s.close();
	}
	
	public void createFolders() {
		
		File updaterFolder = new File(plugin.getDataFolder() + "/updater/");
		
		if(!(updaterFolder.exists())) {
			updaterFolder.mkdirs();
		}
		
		File receptionFolder = new File(plugin.getDataFolder().getParentFile() + "/update/");
		
		if(!(receptionFolder.exists())) {
			receptionFolder.mkdirs();
		}
	}
	
	public void generateToken() throws URISyntaxException, IOException, InvalidConfigurationException {
		
		File accessFile = new File(plugin.getDataFolder() + "/updater/access.yml");
		
		RandomToken randomToken = new RandomToken();
		
		fileconfiguration = new YamlConfiguration();
		
		fileconfiguration.set("token", randomToken.random(18));
		fileconfiguration.set("version", 1.0);
		
		fileconfiguration.save(accessFile);
		
		File jar = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		File newJar = new File(plugin.getDataFolder().getParentFile() + "/update/" + jar.getName());
		
		Files.copy(jar.toPath(), Paths.get(plugin.getDataFolder().getParentFile() + "/update/" + jar.getName()));
						
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        
        URI uri = URI.create("jar:" + newJar.toURI());
                
       try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
    	   
            Path externalTxtFile = Paths.get(accessFile.getAbsolutePath());
            Path pathInZipfile = zipfs.getPath("/fr/xxathyx/shadowz/modelmaker/updater/access/access.yml");
            
            Files.copy(externalTxtFile, pathInZipfile, StandardCopyOption.REPLACE_EXISTING );
        }
       registerToken(getToken());
    }
	
	public void extractToken() throws URISyntaxException, IOException {
		
		URI sourceAccessFile = Updater.class.getResource("access/access.yml").toURI();
		
		if("jar".equals(sourceAccessFile.getScheme())){
		    for (FileSystemProvider provider: FileSystemProvider.installedProviders()) {
		        if (provider.getScheme().equalsIgnoreCase("jar")) {
		            try {
		                provider.getFileSystem(sourceAccessFile);
		            } catch (FileSystemNotFoundException e) {
		                provider.newFileSystem(sourceAccessFile, Collections.emptyMap());
		            }
		        }
		    }
		}
		
		Path source = Paths.get(sourceAccessFile);
		Path out = Paths.get(plugin.getDataFolder() + "/updater/access.yml");
		
		Files.copy(source, out, StandardCopyOption.REPLACE_EXISTING);
	}
	
	public String getToken() throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		File accessFile = new File(plugin.getDataFolder() + "/updater/access.yml");
		
		fileconfiguration = new YamlConfiguration();
		fileconfiguration.load(accessFile);
		
		return fileconfiguration.getString("token");
	}
	
	public boolean isDefaultToken() throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		if(getToken().equals("bxgv5t3y4gw0tmsyuw")) {
			return true;
		}else {
			return false;
		}
	}
	
	public void changeVersion(String version) throws URISyntaxException, IOException, NoSuchFieldException,
	SecurityException, IllegalArgumentException, IllegalAccessException, UnknownDependencyException, InvalidPluginException, InvalidDescriptionException {
		
		File accessFile = new File(plugin.getDataFolder() + "/updater/access.yml");
		
		File jar = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		File newJar = new File(plugin.getDataFolder().getParentFile() + "/update/" + jar.getName());
		
    	URL changelog = new URL("http://localhost/ShadowZ/modelmaker/updates/" + version + "/ShadowZ Model Maker.jar");
    	FileUtils.copyURLToFile(changelog, newJar);
		
    	Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        
        URI uri = URI.create("jar:" + newJar.toURI());
                
       try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
    	   
            Path externalTxtFile = Paths.get(accessFile.getAbsolutePath());
            Path pathInZipfile = zipfs.getPath("/fr/xxathyx/shadowz/modelmaker/updater/access/access.yml");
            
            Files.copy(externalTxtFile, pathInZipfile, StandardCopyOption.REPLACE_EXISTING );
        }
		updateJar();
	}
	
	public void load(File pluginFile) throws UnknownDependencyException, InvalidPluginException, InvalidDescriptionException {
		
		Plugin target = Bukkit.getPluginManager().loadPlugin(pluginFile);
		
        target.onLoad();
        Bukkit.getPluginManager().enablePlugin(target);
	}
	
    @SuppressWarnings("unchecked")
    public void unload(Plugin plugin) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        //method code inspired by PlugMan unload method

        PluginManager pluginManager = Bukkit.getPluginManager();

        SimpleCommandMap commandMap = null;

        List <Plugin> plugins = null;

        Map <String, Plugin> names = null;
        Map <String, Command> commands = null;
        Map <Event, SortedSet <RegisteredListener>> listeners = null;

        boolean reloadlisteners = true;

        pluginManager.disablePlugin(plugin);

        Field pluginsField = Bukkit.getPluginManager().getClass().getDeclaredField("plugins");
        
        pluginsField.setAccessible(true);
        
        plugins = (List <Plugin>) pluginsField.get(pluginManager);

        Field lookupNamesField = Bukkit.getPluginManager().getClass().getDeclaredField("lookupNames");
        
        lookupNamesField.setAccessible(true);
        
        names = (Map <String, Plugin>) lookupNamesField.get(pluginManager);

        try {
            Field listenersField = Bukkit.getPluginManager().getClass().getDeclaredField("listeners");
            listenersField.setAccessible(true);
            listeners = (Map <Event, SortedSet <RegisteredListener>>) listenersField.get(pluginManager);
        } catch (Exception e) {
            reloadlisteners = false;
        }

        Field commandMapField = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
        
        commandMapField.setAccessible(true);
        
        commandMap = (SimpleCommandMap) commandMapField.get(pluginManager);

        Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
        
        knownCommandsField.setAccessible(true);
        
        commands = (Map <String, Command>) knownCommandsField.get(commandMap);

        pluginManager.disablePlugin(plugin);

        if (plugins != null && plugins.contains(plugin))
            plugins.remove(plugin);

        if (names != null && names.containsKey(plugin.getName()))
        	names.remove(plugin.getName());

        if (listeners != null && reloadlisteners) {
        	
            for (SortedSet <RegisteredListener> set: listeners.values()) {
                for (Iterator <RegisteredListener> it = set.iterator(); it.hasNext();) {
                	
                    RegisteredListener value = it.next();
                    
                    if (value.getPlugin() == plugin) {
                    	
                        it.remove();
                    }
                }
            }
        }

        if (commandMap != null) {
            for (Iterator <Map.Entry <String, Command>> it = commands.entrySet().iterator(); it.hasNext();) {
            	
                Map.Entry <String, Command> entry = it.next();
                
                if (entry.getValue() instanceof PluginCommand) {
                	
                    PluginCommand c = (PluginCommand) entry.getValue();
                    
                    if (c.getPlugin() == plugin) {
                    	
                        c.unregister(commandMap);
                        it.remove();
                    }
                }
            }
        }
    }
}