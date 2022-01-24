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
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.CacheInterfaces;
import fr.xxathyx.shadowz.modelmaker.model.loaded.CacheLoaded;
import fr.xxathyx.shadowz.modelmaker.profil.Profil;
import fr.xxathyx.shadowz.modelmaker.server.configuration.ServerConfiguration;
import fr.xxathyx.shadowz.modelmaker.util.Flags;

public class Translater {
	
	private File file;
	
	public Translater(File file) {
		this.file = file;
	}
		
	public Translater() {
		
	}
	
	private static Main plugin = Main.getPlugin(Main.class);
	
	private FileConfiguration customConfig;
	
    public void createTranslationFile(String langage) throws URISyntaxException, IOException {
    	
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
		file = new File(plugin.getDataFolder() + "/translations/", langage + ".yml");
	    
		if(!file.exists()) {
			file.getParentFile().mkdir();
		}
		
		if(!file.exists()) {
			
			URI uri = Translater.class.getResource("translations/" + langage + ".yml").toURI();

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
			
			System.out.print("Installing langage: " + langage);
			
			Files.copy(source, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
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
	
	public String loading_map_models() {
		return this.getConfigFile().getString("server.loading_map_models");
	}
	
	public String loaded_map_models() {
		return this.getConfigFile().getString("server.loaded_map_models");
	}
	
	public String map_models_updated() {
		return this.getConfigFile().getString("server.map_models_updated");
	}
	
	public String installing_langage() {
		return this.getConfigFile().getString("server.installing_langage");
	}
	
	public String plugin_updating() {
		return this.getConfigFile().getString("server.updating");
	}
	
	public String plugin_updated() {
		return this.getConfigFile().getString("server.updated");
	}
	
	public String translates_limits_reach() {
		return this.getConfigFile().getString("server.translates_limits_reach");
	}
	
	public String change_version() {
		return this.getConfigFile().getString("server.change_version");
	}
	
	public String version_non_supported() {
		return this.getConfigFile().getString("server.version_non_supported");
	}
	
	public String invalid_sender() {
		return this.getConfigFile().getString("commands.invalid_sender");
	}
	
	public String invalid_commands_args() {
		return this.getConfigFile().getString("commands.invalid_commands_args");
	}
	
	public String editor_enter() {
		return this.getConfigFile().getString("editor.editor_enter");
	}
	
	public String editor_in() {
		return this.getConfigFile().getString("editor.editor_in");
	}
	
	public String editor_out() {
		return this.getConfigFile().getString("editor.editor_out");
	}
	
	public String not_in_editor() {
		return this.getConfigFile().getString("editor.not_in_editor");
	}
	
	public String model_long_name() {
		return this.getConfigFile().getString("editor.model_long_name");
	}
	
	public String model_created() {
		return this.getConfigFile().getString("editor.model_created");
	}
	
	public String model_exist() {
		return this.getConfigFile().getString("editor.model_exist");
	}
	
	public String model_not_found() {
		return this.getConfigFile().getString("editor.model_not_found");
	}
	
	public String model_creation_request() {
		return this.getConfigFile().getString("editor.model_creation_request");
	}
	
	public String model_loading() {
		return this.getConfigFile().getString("editor.model_loading");
	}
	
	public String model_loaded() {
		return this.getConfigFile().getString("editor.model_loaded");
	}
	
	public String model_deleted() {
		return this.getConfigFile().getString("editor.model_deleted");
	}
	
	public String model_exported() {
		return this.getConfigFile().getString("editor.model_exported");
	}
	
	public String model_to_place() {
		return this.getConfigFile().getString("editor.model_to_place");
	}
	
	public String model_placed() {
		return this.getConfigFile().getString("editor.model_placed");
	}
	
	public String loaded_deleted() {
		return this.getConfigFile().getString("editor.loaded_deleted");
	}
	
	public String insufficient_parts_export() {
		return this.getConfigFile().getString("editor.insufficient_parts_export");
	}
	
	public String defined_base_point() {
		return this.getConfigFile().getString("editor.defined_base_point");
	}
	
	public String yes_question() {
		return this.getConfigFile().getString("editor.yes_question");
	}
	
	public String no_question() {
		return this.getConfigFile().getString("editor.no_question");
	}
	
	public String command_disabled() {
		return this.getConfigFile().getString("editor.command_disabled");
	}
	
	public String my_models() {
		return this.getConfigFile().getString("gui.my_models");
	}
	
	public String my_models_progress() {
		return this.getConfigFile().getString("gui.my_models_progress");
	}
	
	public String create_model() {
		return this.getConfigFile().getString("gui.create_model");
	}
	
	public String parametres() {
		return this.getConfigFile().getString("gui.parametres");
	}
	
	public String close_gui() {
		return this.getConfigFile().getString("gui.close_gui");
	}
	
	public String previous_page() {
		return this.getConfigFile().getString("gui.previous_page");
	}
	
	public String next_page() {
		return this.getConfigFile().getString("gui.next_page");
	}
	
	public String actualize() {
		return this.getConfigFile().getString("gui.actualize");
	}
	
	public String no_page_left() {
		return this.getConfigFile().getString("gui.no_page_left");
	}
	
	public String change_langage() {
		return this.getConfigFile().getString("langage.change");
	}
	
	public String update_langage() {
		return this.getConfigFile().getString("langage.update");
	}
	
	public String informations() {
		return this.getConfigFile().getString("gui.informations");
	}
	
	public String models_parameter() {
		return this.getConfigFile().getString("gui.models_parameter");
	}
	
	public String tools() {
		return this.getConfigFile().getString("gui.tools");
	}
	
	public String exit_editor() {
		return this.getConfigFile().getString("gui.exit_editor");
	}
	
	public String location_x() {
		return this.getConfigFile().getString("gui.location_x");
	}
	
	public String location_y() {
		return this.getConfigFile().getString("gui.location_y");
	}
	
	public String location_z() {
		return this.getConfigFile().getString("gui.location_z");
	}
	
	public String angle_w() {
		return this.getConfigFile().getString("gui.angle_w");
	}
	
	public String angle_x() {
		return this.getConfigFile().getString("gui.angle_x");
	}
	
	public String angle_y() {
		return this.getConfigFile().getString("gui.angle_y");
	}
	
	public String angle_z() {
		return this.getConfigFile().getString("gui.angle_z");
	}
	
	public String size() {
		return this.getConfigFile().getString("gui.size");
	}
	
	public String reset_part_rotations() {
		return this.getConfigFile().getString("gui.reset_part_rotations");
	}
	
	public String undo() {
		return this.getConfigFile().getString("gui.undo");
	}
	
	public String save() {
		return this.getConfigFile().getString("gui.save");
	}
	
	public String model_publish() {
		return this.getConfigFile().getString("gui.model_publish");
	}
	
	public String model_edit() {
		return this.getConfigFile().getString("gui.model_edit");
	}
	
	public String model_delete() {
		return this.getConfigFile().getString("gui.model_delete");
	}
	
	public String delete_confirm() {
		return this.getConfigFile().getString("gui.delete_confirm");
	}
	
	public String delete_confirm_title() {
		return this.getConfigFile().getString("gui.delete_confirm_title");
	}
	
	public String load_model() {
		return this.getConfigFile().getString("gui.load_model");
	}
	
	public String replace_loaded() {
		return this.getConfigFile().getString("gui.replace_loaded");
	}
	
	public String size_loaded() {
		return this.getConfigFile().getString("gui.size_loaded");
	}
	
	public String delete_confirm_loaded() {
		return this.getConfigFile().getString("gui.delete_confirm_loaded");
	}
	
	public String delete_confirm_title_loaded() {
		return this.getConfigFile().getString("gui.delete_confirm_title_loaded");
	}
	
	public String administration_panel() {
		return this.getConfigFile().getString("gui.administration_panel");
	}
	
	public String archives_gui() {
		return this.getConfigFile().getString("gui.archives_gui");
	}
	
	public String archives() {
		return this.getConfigFile().getString("gui.archives");
	}
	
	public String visit_spigot() {
		return this.getConfigFile().getString("gui.visit_spigot");
	}
	
	public String my_models_description() {
		return this.getConfigFile().getString("descriptions.my_models");
	}
	
	public String my_models_progress_description() {
		return this.getConfigFile().getString("descriptions.my_models_progress");
	}
	
	public String create_model_description() {
		return this.getConfigFile().getString("descriptions.create_model");
	}
	
	public String parametres_description() {
		return this.getConfigFile().getString("descriptions.parametres");
	}
	
	public String close_gui_description() {
		return this.getConfigFile().getString("descriptions.close_gui");
	}
	
	public String previous_page_description() {
		return this.getConfigFile().getString("descriptions.previous_page");
	}
	
	public String next_page_description() {
		return this.getConfigFile().getString("descriptions.next_page");
	}
	
	public String actualize_description() {
		return this.getConfigFile().getString("descriptions.actualize");
	}
	
	public String tools_description() {
		return this.getConfigFile().getString("descriptions.tools");
	}
	
	public String exit_editor_description() {
		return this.getConfigFile().getString("descriptions.exit_editor");
	}
	
	public List<String> informations_description() {
		
		List<String> commands = this.getConfigFile().getStringList("descriptions.informations");
		
		return commands;
	}
	
	public String models_parameter_description() {
		return this.getConfigFile().getString("descriptions.models_parameter");
	}
	
	public String location_x_description() {
		return this.getConfigFile().getString("descriptions.location_x");
	}
	
	public String location_y_description() {
		return this.getConfigFile().getString("descriptions.location_y");
	}
	
	public String location_z_description() {
		return this.getConfigFile().getString("descriptions.location_z");
	}
	
	public String angle_w_description() {
		return this.getConfigFile().getString("descriptions.angle_w");
	}
	
	public String angle_x_description() {
		return this.getConfigFile().getString("descriptions.angle_x");
	}
	
	public String angle_y_description() {
		return this.getConfigFile().getString("descriptions.angle_y");
	}
	
	public String angle_z_description() {
		return this.getConfigFile().getString("descriptions.angle_z");
	}
	
	public String size_description() {
		return this.getConfigFile().getString("descriptions.size");
	}
	
	public String reset_part_rotations_description() {
		return this.getConfigFile().getString("descriptions.reset_part_rotations_description");
	}
	
	public String undo_description() {
		return this.getConfigFile().getString("descriptions.undo");
	}
	
	public String save_description() {
		return this.getConfigFile().getString("descriptions.save");
	}
	
	public String model_publish_description() {
		return this.getConfigFile().getString("descriptions.model_publish");
	}
	
	public String model_edit_description() {
		return this.getConfigFile().getString("descriptions.model_edit");
	}
	
	public String model_delete_description() {
		return this.getConfigFile().getString("descriptions.model_delete");
	}
	
	public String load_model_description() {
		return this.getConfigFile().getString("descriptions.load_model");
	}
	
	public String replace_loaded_description() {
		return this.getConfigFile().getString("descriptions.replace_loaded");
	}
	
	public String size_loaded_description() {
		return this.getConfigFile().getString("descriptions.size_loaded");
	}
	
	public String administration_panel_descriptions() {
		return this.getConfigFile().getString("descriptions.administration_panel");
	}
	
	public String archives_description() {
		return this.getConfigFile().getString("descriptions.archives");
	}
	
	public String added_description() {
		return this.getConfigFile().getString("descriptions.added");
	}
	
	public String fixed_description() {
		return this.getConfigFile().getString("descriptions.fixed");
	}
	
	public String deleted_description() {
		return this.getConfigFile().getString("descriptions.deleted");
	}
	
	public String install_description() {
		return this.getConfigFile().getString("descriptions.install");
	}
	
	public String visit_spigot_description() {
		return this.getConfigFile().getString("descriptions.visit_spigot");
	}
	
	public List<String> delete_confirm_description() {
		
		List<String> commands = this.getConfigFile().getStringList("descriptions.delete_confirm");
		
		return commands;
	}
	
	public static Translater getServerLangage() {
		
		ServerConfiguration serverConfiguration = new ServerConfiguration();
		
		File langagefile = new File(plugin.getDataFolder() + "/translations/", serverConfiguration.getServerLangage() + ".yml");
		Translater translater = new Translater(langagefile);
				
		return translater;
	}
	
	public String getMessage(String a) {
		return ChatColor.translateAlternateColorCodes ('&', a);
	}
	
	public String getMessage(String message, UUID uuid) {
				
		Player player = Bukkit.getPlayer(uuid);
		
		File file = new File(plugin.getDataFolder() + "/profils/", uuid + ".yml");
		
		Profil profil = new Profil(file);
		
		if(message.contains("%player_name%")) {
			message = message.replaceAll("%player_name%", player.getName());
		}
		
		if(message.contains("%models_count%")) {
			message = message.replaceAll("%models_count%", Integer.toString(profil.getModelsCount()));
		}
		
		if(message.contains("%models_progress_count%")) {
			message = message.replaceAll("%models_progress_count%", Integer.toString(profil.getModelsProgressCount()));
		}
		
		if(message.contains("%account_creation%")) {
			message = message.replaceAll("%account_creation%", profil.getCreationDate());
		}
		
		if(message.contains("%uuid%")) {
			message = message.replaceAll("%uuid%", profil.getPlayerUUID());
		}
		
		if(message.contains("%original_name%")) {
			message = message.replaceAll("%original_name%", profil.getPlayerName());
		}
		
		if(message.contains("%player_data_folder%")) {
			message = message.replaceAll("%player_data_folder%", profil.getModelsDirectory());
		}
		
		if(message.contains("%precision_scale%")) {
			message = message.replaceAll("%precision_scale%", Double.toString(profil.getPrecision()));
		}
		
		if(message.contains("%model_name%")) {
			message = message.replaceAll("%model_name%", CacheInterfaces.selected_model.get(player).getName());
		}
		
		if(message.contains("%loaded_name%")) {
			message = message.replaceAll("%loaded_name%", CacheLoaded.selected_loaded.get(player).getName());
		}
		return ChatColor.translateAlternateColorCodes ('&', message);
	}
	
	public static ItemStack getFlag(Player player) {
		
		Flags flags = new Flags();
		
		File profilfile = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId() + ".yml");
		
		Profil profil = new Profil(profilfile);
		
		if(profil.getLangage().equalsIgnoreCase("FR")) {
			return flags.French(player);
		}
		
		if(profil.getLangage().equalsIgnoreCase("DE")) {
			return flags.Deutsch(player);
		}
		
		if(profil.getLangage().equalsIgnoreCase("ES")) {
			return flags.Spanish(player);
		}
		
		if(profil.getLangage().equalsIgnoreCase("AR")) {
			return flags.Arabian(player);
		}
		
		if(profil.getLangage().equalsIgnoreCase("GB")) {
			return flags.English(player);
		}
		
		if(profil.getLangage().equalsIgnoreCase("IT")) {
			return flags.Italian(player);
		}
		
		if(profil.getLangage().equalsIgnoreCase("RU")) {
			return flags.Russian(player);
		}
		
		if(profil.getLangage().equalsIgnoreCase("TR")) {
			return flags.Turkish(player);
		}
		
	    File translationsFolder = new File(plugin.getDataFolder() + "/translations/");
	    
	    for(int i = 0; i < translationsFolder.listFiles().length; i++) {
	    	if(profil.getLangage().equalsIgnoreCase(FilenameUtils.removeExtension(translationsFolder.listFiles()[i].getName()))) {
	    		
	    		return flags.Unknown(player);
	    	}
	    }
		return flags.English(player);
	}
	
	public static Translater getTranslationLangage(UUID uuid) {
		
		File profilfile = new File(plugin.getDataFolder() + "/profils/", uuid + ".yml");
		
		Profil profil = new Profil(profilfile);
		
		File langagefile = new File(plugin.getDataFolder() + "/translations/", profil.getLangage() + ".yml");
		
		if(!(langagefile.exists())) {
			langagefile = new File(plugin.getDataFolder() + "/translations/", "GB" + ".yml");
		}
		
		Translater translater = new Translater(langagefile);
		
		return translater;
	}
	
    public void updateConfig(String TR) throws URISyntaxException {
    	
		File file = new File(this.getClass().getPackage() + "/Translations/" + TR + ".yml");
		    	
		customConfig = new YamlConfiguration();
		
	    try {
	    	customConfig.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	    
	    customConfig.set("new.section", "text");
	    
	    if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		
	    try {
	    	customConfig.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }	
}