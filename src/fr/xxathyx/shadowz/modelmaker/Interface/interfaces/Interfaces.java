package fr.xxathyx.shadowz.modelmaker.Interface.interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.google.common.io.Files;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.items.ItemStacks;
import fr.xxathyx.shadowz.modelmaker.items.Tools;
import fr.xxathyx.shadowz.modelmaker.model.Model;
import fr.xxathyx.shadowz.modelmaker.model.loaded.CacheLoaded;
import fr.xxathyx.shadowz.modelmaker.model.loaded.Loaded;
import fr.xxathyx.shadowz.modelmaker.Interface.Interface;
import fr.xxathyx.shadowz.modelmaker.profil.Profil;
import fr.xxathyx.shadowz.modelmaker.translation.CustomTranslation;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;
import fr.xxathyx.shadowz.modelmaker.util.Flags;

public class Interfaces {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	public void menu(Player player) {
		getMenuInterface(player).showInterface(player);
	}
	
	public void my_models(Player player) {
		getModelsInterface(player).showInterface(player);
	}
	
    public void my_models_progress(Player player) {
    	getModelsProgressInterface(player).showInterface(player);
	}
	
	public void parameters(Player player) {
		getParametersInterface(player).showInterface(player);
	}
	
    public void administration_panel(Player player) {
    	getAdministrationPanelInterface(player).showInterface(player);
	}
    
    public void olders_versions(Player player) throws IOException, InvalidConfigurationException {
    	getOlderVersionsInterface(player).showInterface(player);
	}
    
	public void help(Player player) {
		getHelpInterface(player).showInterface(player);
	}
	
	public void langages(Player player) {
		getLangagesInterface(player).showInterface(player);
	}
	
	public void models_progress_interface(Model model, Player player) {
		getModelProgressInGameParameters(model, player).showInterface(player);
	}
	
	public void models_interface(Model model, Player player) {
		getModelInGameParameters(model, player).showInterface(player);
	}
	
	public void loaded_interface(Loaded loaded, Player player) {
		getLoadedInterface(loaded, player).showInterface(player);
	}
	
	public void tools(Player player) {
		getToolsInterface(player).showInterface(player);
	}
	
	public void confirmation_model_supression(Model model, Player player) {
		getConfirmationModelSuppression(model, player).showInterface(player);
	}
	
	public void confirmation_loaded_supression(Loaded loaded, Player player) {
		getConfirmationLoadedSuppression(loaded, player).showInterface(player);
	}
	
	public Interface getMenuInterface(Player player) {
		
		ItemStacks translatedItems = new ItemStacks(player);
		
		Interface menuInterface = new Interface();
		
		menuInterface.setName(ChatColor.LIGHT_PURPLE + "MDK-MENU");
		menuInterface.setSize(54);
		menuInterface.addItem(10, translatedItems.my_models());
		menuInterface.addItem(12, translatedItems.my_models_progress());
		menuInterface.addItem(14, translatedItems.create_model());
		menuInterface.addItem(16, translatedItems.parameters());
		menuInterface.addItem(40, translatedItems.close());
		
		return menuInterface;
	}
	
	public Interface getModelsInterface(Player player) {
		
		File file = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId() + ".yml");
		
		Profil profil = new Profil(file);
		
		ItemStacks items = new ItemStacks(player);
		
	    Interface Interface = new Interface();
	  
	    Interface.setName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).my_models(), player.getUniqueId()) +  " : " + profil.getModelsCount());
	    Interface.setSize(54);
	    Interface.addItem(45, items.glass());
	    Interface.addItem(46, items.glass());
	    Interface.addItem(47, items.glass());
	    Interface.addItem(51, items.glass());
	    Interface.addItem(52, items.glass());
	    Interface.addItem(53, items.glass());
	    Interface.addItem(48, items.previous());
	    Interface.addItem(49, items.refresh());
	    Interface.addItem(50, items.next());
	    
	    return Interface;
	}
	
	public Interface getModelsProgressInterface(Player player) {
		
		File file = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId() + ".yml");
		
		Profil profil = new Profil(file);
		
		ItemStacks items = new ItemStacks(player);
	  
	    Interface Interface = new Interface();
	  
	    Interface.setName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).my_models_progress(), player.getUniqueId()) + " : " + profil.getModelsProgressCount());
	    Interface.setSize(54);
	    Interface.addItem(45, items.glass());
	    Interface.addItem(46, items.glass());
	    Interface.addItem(47, items.glass());
	    Interface.addItem(51, items.glass());
	    Interface.addItem(52, items.glass());
	    Interface.addItem(53, items.glass());
	    Interface.addItem(48, items.previous());
	    Interface.addItem(49, items.refresh());
	    Interface.addItem(50, items.next());
	  
	    return Interface;
	}
	
	public Interface getParametersInterface(Player player) {
		
		File playerProfilFile = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId() + ".yml");
		
		Profil playerProfil = new Profil(playerProfilFile);
		
		ItemStacks translatedItems = new ItemStacks(player);
		
		Interface parametersInterface = new Interface();
		  
		parametersInterface.setName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).parametres(), player.getUniqueId()) +  " : " + playerProfil.getPlayerName());
		parametersInterface.setSize(9);
	    
	    if(player.hasPermission("modelmaker.admin")) {
	    	parametersInterface.addItem(0, Translater.getFlag(player));
	    	parametersInterface.addItem(2, translatedItems.informations());
	    	parametersInterface.addItem(4, translatedItems.models_parameters());
	    	parametersInterface.addItem(6, translatedItems.administration_panel());
	    	parametersInterface.addItem(8, translatedItems.close());
	    }else {
	    	parametersInterface.addItem(1, Translater.getFlag(player));
	    	parametersInterface.addItem(3, translatedItems.informations());
	    	parametersInterface.addItem(5, translatedItems.models_parameters());
	    	parametersInterface.addItem(8, translatedItems.close());
	    }
	    return parametersInterface;
	}
	
	public Interface getAdministrationPanelInterface(Player player) {
		
		ItemStacks translatedItems = new ItemStacks(player);
		
	    Interface administrationPanelInterface = new Interface();
	  
	    administrationPanelInterface.setName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).administration_panel(), player.getUniqueId()));
	    administrationPanelInterface.setSize(54);
	    administrationPanelInterface.addItem(10, translatedItems.olders_versions());
	    administrationPanelInterface.addItem(40, translatedItems.close());
	    
	    return administrationPanelInterface;
	}
	
	public Interface getOlderVersionsInterface(Player player) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
    	ItemStacks translatedItems = new ItemStacks(player);
    	
	    Interface oldersVesionsInterface = new Interface();
	  
	    oldersVesionsInterface.setName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).archives_gui(), player.getUniqueId()));
	    oldersVesionsInterface.setSize(54);
	    
	    double pluginVersion = Double.parseDouble(plugin.getDescription().getVersion());
	    
	    File changelogsFolder = new File(plugin.getDataFolder() + "/updater/changelogs");
	    
	    if(!(changelogsFolder.exists())) {
	    	
	    	changelogsFolder.mkdirs();
	    	
	    	for(double d = 1.0; d < pluginVersion; d = d + 0.1) {
		    	URL changelog = new URL("http://localhost/ShadowZ/modelmaker/updates/" + d + "/changelog.yml");
		    	FileUtils.copyURLToFile(changelog, new File(changelogsFolder + "/" + d + ".yml"));
	    	}
	    }
	    
	    int slot = 0;
	    
	    for(double d = 1.0; d < pluginVersion; d = d + 0.1) {
	    	
	    	File changelogFile = new File(changelogsFolder + "/" + d + ".yml");
	    	
	    	FileConfiguration fileconfiguration = new YamlConfiguration();
	    	fileconfiguration.load(changelogFile);
	    	
	    	List<String> features = fileconfiguration.getStringList("features");
	    	List<String> fix = fileconfiguration.getStringList("fix");
	    	List<String> removed = fileconfiguration.getStringList("removed");
	    	
	    	List<String> changes = new ArrayList<String>();
	    	
	    	changes.add(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
					Translater.getTranslationLangage(player.getUniqueId()).added_description(), player.getUniqueId()));
	    	
	    	for(int i = 0; i < features.size(); i++) {
	    		changes.add(ChatColor.GREEN + "[+] " + features.get(i));
	    	}
	    	
	    	changes.add("");
	    	
	    	changes.add(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
					Translater.getTranslationLangage(player.getUniqueId()).fixed_description(), player.getUniqueId()));
	    	
	    	for(int i = 0; i < fix.size(); i++) {
	    		changes.add(ChatColor.YELLOW + "[/] " + fix.get(i));
	    	}
	    	
	    	changes.add("");
	    	
	    	changes.add(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
					Translater.getTranslationLangage(player.getUniqueId()).deleted_description(), player.getUniqueId()));
	    	
	    	for(int i = 0; i < removed.size(); i++) {
	    		changes.add(ChatColor.RED + "[-] " + removed.get(i));
	    	}
	    	
	    	changes.add("");
	    	
	    	changes.add(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
					Translater.getTranslationLangage(player.getUniqueId()).install_description(), player.getUniqueId()));
	    	
			ItemStack icon = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta icon_meta = (SkullMeta) icon.getItemMeta();
			
			((SkullMeta) icon_meta).setOwner("Chest");
			icon_meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Version " + d);
			icon_meta.setLore(changes);
			if(d == pluginVersion) {
				icon.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
			}
			icon.setItemMeta(icon_meta);
			
			oldersVesionsInterface.addItem(slot++, icon);
	    }
	    oldersVesionsInterface.addItem(40, translatedItems.spigot_page());
	    return oldersVesionsInterface;
	}
	
	public Interface getHelpInterface(Player player) {
		
		ItemStacks translatedItems = new ItemStacks(player);
		
		Interface helpInterface = new Interface();
		  
		helpInterface.setName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).informations(), player.getUniqueId()));
		helpInterface.setSize(27);
		helpInterface.addItem(22, translatedItems.close());
		
		return helpInterface;
	}
	
	public Interface getLangagesInterface(Player player) {
		Flags flags = new Flags();
		Interface langagesInterface = new Interface();
						
		ArrayList<String> officialsLangages = new ArrayList<String>();
		
		langagesInterface.setName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).informations(), player.getUniqueId()));
		langagesInterface.setSize(27);
		langagesInterface.addItem(0, flags.French(player));
		officialsLangages.add("FR");
	    langagesInterface.addItem(1, flags.Deutsch(player));
	    officialsLangages.add("DE");
	    langagesInterface.addItem(2, flags.Russian(player));
	    officialsLangages.add("RU");
	    langagesInterface.addItem(3, flags.Spanish(player));
	    officialsLangages.add("ES");
	    langagesInterface.addItem(4, flags.Italian(player));
	    officialsLangages.add("IT");
	    langagesInterface.addItem(5, flags.English(player));
	    officialsLangages.add("GB");
	    langagesInterface.addItem(6, flags.Arabian(player));
	    officialsLangages.add("AR");
	    langagesInterface.addItem(7, flags.Turkish(player));
	    officialsLangages.add("TR");
	    
	    File translationsFolder = new File(plugin.getDataFolder() + "/translations/");
	    int start = 7;
	    
	    if(langagesInterface.getInventory().getContents().length == 26) {
	    	System.out.print(Translater.getServerLangage().getMessage(Translater.getServerLangage().translates_limits_reach()));
	    	return langagesInterface;
	    }
	    
	    for(int i = 0; i < translationsFolder.listFiles().length; i++) {	  
	    	
	    	String langageName = Files.getNameWithoutExtension(translationsFolder.listFiles()[i].getName());
	    	
	    	if(!(officialsLangages.contains(langageName))) {
	    		
	    		start = start++;
	    		
	    		File translationFile = new File(plugin.getDataFolder() + "/translations/", translationsFolder.listFiles()[i].getName());
	    		CustomTranslation customTranslation = new CustomTranslation(translationFile);
	    		
	    		ItemStack banner = new ItemStack(Material.BANNER, 1);
	    	    BannerMeta banner_meta = (BannerMeta) banner.getItemMeta();

	    	    banner_meta.setBaseColor(DyeColor.BLACK);

	    	    List<Pattern> patterns = new ArrayList<Pattern>();
	    	    
	    	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_TOP));
	    	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.RHOMBUS_MIDDLE));
	    	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_DOWNLEFT));
	    	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.HALF_HORIZONTAL_MIRROR));
	    	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.TRIANGLE_BOTTOM));
	    	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
	    	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
	    	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.BORDER));
	    	    
	    	    banner_meta.setPatterns(patterns);
	    	    banner_meta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + customTranslation.getName() + " (" + langageName + ")");
	    	    banner_meta.setLore(Arrays.asList(new String[] { ChatColor.AQUA + customTranslation.getDescription(), ChatColor.AQUA + "Author: " +
	    	    customTranslation.getAuthor(), ChatColor.AQUA + "Version: " + customTranslation.getVersion() }));
	    	    banner_meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    	    
	    	    banner.setItemMeta(banner_meta);
	    		
	    	    langagesInterface.addItem(start, banner);
	    	}
	    }
	    return langagesInterface;
	}
	
	public Interface getModelProgressInGameParameters(Model model, Player player) {
		
		Interface modelsProgressInterface = new Interface();
		
		ItemStacks translatedItems = new ItemStacks(player);
		
		modelsProgressInterface.setName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + model.getName());
	    modelsProgressInterface.setSize(27);
	    modelsProgressInterface.addItem(11, translatedItems.publish());
	    modelsProgressInterface.addItem(13, translatedItems.edit());
	    modelsProgressInterface.addItem(15, translatedItems.delete());
	    
	    return modelsProgressInterface;
	}
	
	public Interface getModelInGameParameters(Model model, Player player) {
		
		Interface modelsInterface = new Interface();
		
		ItemStacks translatedItems = new ItemStacks(player);
	    		
		modelsInterface.setName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + model.getName());
		modelsInterface.setSize(27);
		modelsInterface.addItem(11, translatedItems.load());
		modelsInterface.addItem(13, translatedItems.edit());
		modelsInterface.addItem(15, translatedItems.delete());
	    
		return modelsInterface;
	}
	
	public Interface getLoadedInterface(Loaded loaded, Player player) {
		
		Interface loadedInterface = new Interface();
		
		ItemStacks translatedItems = new ItemStacks(player);
		
		loadedInterface.setName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + loaded.getName());
		loadedInterface.setSize(27);
		loadedInterface.addItem(11, translatedItems.replace());
		loadedInterface.addItem(13, translatedItems.size());
		loadedInterface.addItem(15, translatedItems.delete());
	    		
		return loadedInterface;
	}
	
	public Interface getToolsInterface(Player player) {
		
		Interface toolsInterface = new Interface();
		Tools tools = new Tools();
				
		toolsInterface.setName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).tools(), player.getUniqueId()));
		toolsInterface.setSize(54);
		toolsInterface.addItem(10, tools.location_x(player));
		toolsInterface.addItem(11, tools.location_y(player));
		toolsInterface.addItem(12, tools.location_z(player));
		toolsInterface.addItem(19, tools.angle_x(player));
		toolsInterface.addItem(20, tools.angle_y(player));
		toolsInterface.addItem(21, tools.angle_z(player));
		toolsInterface.addItem(14, tools.size(player));
		toolsInterface.addItem(23, tools.reset_part_rotations(player));
		toolsInterface.addItem(16, tools.undo(player));
		toolsInterface.addItem(25, tools.save(player));
		toolsInterface.addItem(40, tools.close(player));
		
		return toolsInterface;
	}
	
	public Interface getConfirmationModelSuppression(Model model, Player player) {
		
		if(CacheInterfaces.selected_model.containsKey(player)) {
			CacheInterfaces.selected_model.replace(player, model);
		}else {
			CacheInterfaces.selected_model.put(player, model);
		}
		
		Interface confirmationModelSuppressionInterface = new Interface();
		
		ItemStacks translatedItems = new ItemStacks(player);
		
		confirmationModelSuppressionInterface.setName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).delete_confirm_title(), player.getUniqueId()));
		confirmationModelSuppressionInterface.setSize(27);
		confirmationModelSuppressionInterface.addItem(13, translatedItems.confirm());
	    
		return confirmationModelSuppressionInterface;
	}
	
	public Interface getConfirmationLoadedSuppression(Loaded loaded, Player player) {
		
		if(CacheLoaded.selected_loaded.containsKey(player)) {
			CacheLoaded.selected_loaded.replace(player, loaded);
		}else {
			CacheLoaded.selected_loaded.put(player, loaded);
		}
		
		Interface confirmationLoadedSuppressionInterface = new Interface();
		
		ItemStacks translatedItems = new ItemStacks(player);
		
		confirmationLoadedSuppressionInterface.setName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).delete_confirm_title_loaded(), player.getUniqueId()));
		confirmationLoadedSuppressionInterface.setSize(27);
		confirmationLoadedSuppressionInterface.addItem(13, translatedItems.confirm_loaded());
		
		return confirmationLoadedSuppressionInterface;
	}
	
	public ArrayList<Interface> getInterfaces(Player player) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		ArrayList<Interface> interfaces = new ArrayList<Interface>();
		
		interfaces.add(getMenuInterface(player));
		interfaces.add(getModelsInterface(player));
		interfaces.add(getModelsProgressInterface(player));
		interfaces.add(getParametersInterface(player));
		interfaces.add(getAdministrationPanelInterface(player));
		interfaces.add(getOlderVersionsInterface(player));
		interfaces.add(getHelpInterface(player));
		interfaces.add(getLangagesInterface(player));
		interfaces.add(getToolsInterface(player));
		
		return interfaces;
	}
}