package fr.xxathyx.shadowz.modelmaker.Interface.listeners;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.UnknownDependencyException;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.editor.CacheEditor;
import fr.xxathyx.shadowz.modelmaker.editor.Editor;
import fr.xxathyx.shadowz.modelmaker.items.ItemStacks;
import fr.xxathyx.shadowz.modelmaker.model.Model;
import fr.xxathyx.shadowz.modelmaker.model.loaded.CacheLoaded;
import fr.xxathyx.shadowz.modelmaker.model.loaded.Loaded;
import fr.xxathyx.shadowz.modelmaker.Interface.Interface;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.CacheInterfaces;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.Interfaces;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.pages.ModelsPages;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.pages.ModelsProgressPages;
import fr.xxathyx.shadowz.modelmaker.profil.Profil;
import fr.xxathyx.shadowz.modelmaker.sound.SoundPlayer;
import fr.xxathyx.shadowz.modelmaker.sound.SoundType;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;
import fr.xxathyx.shadowz.modelmaker.updater.Updater;

public class InterfacesListeners implements Listener {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private SoundPlayer soundPlayer = new SoundPlayer();
	
	private Interfaces interfaces = new Interfaces();
	private Editor editor = new Editor();
	
	@EventHandler
	public void InterfaceListener(InventoryClickEvent event) throws URISyntaxException, IOException, InvalidConfigurationException,
	NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownDependencyException, InvalidPluginException, InvalidDescriptionException {
		
	    Player player = (Player) event.getWhoClicked();
		
	    ItemStacks items = new ItemStacks(player);
	    
		File playerProfilFile = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId() + ".yml");
		
		Profil playerProfil = new Profil(playerProfilFile);
		
	if (event.getView().getTitle().equals(ChatColor.LIGHT_PURPLE + "MDK-MENU")) {
	    	
	        event.setCancelled(true);
	        
	        if(Interface.isItemStackNull(event)) {
	        	return;
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.my_models().getItemMeta().getDisplayName())) {

	            if (playerProfil.getModelsCount() != 0) {
	                Interface page = CacheInterfaces.interfaces_models.get(player).getPage(0);
	                
	                page.showInterface(player);
	                player.updateInventory();
	            } else {
	                interfaces.my_models(player);
	                player.updateInventory();
	            }
	            return;
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.my_models_progress().getItemMeta().getDisplayName())) {

	            if (playerProfil.getModelsProgressCount() != 0) {
	                Interface page = CacheInterfaces.interfaces_progress.get(player).getPage(0);
	                
	                page.showInterface(player);
	                player.updateInventory();
	            } else {
	                interfaces.my_models_progress(player);
	                player.updateInventory();
	            }
	            return;
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.create_model().getItemMeta().getDisplayName())) {

	            if (!(editor.isInEditor(player))) {
	            	
	                editor.addToEditor(player);
	                
	                ItemStack item = event.getCurrentItem();
	                item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
	                
	                player.updateInventory();
	            }else {
	            	
	                editor.removeFromEditor(player);
	                
	                ItemStack item = event.getCurrentItem();
	                item.removeEnchantment(Enchantment.DURABILITY);
	                
	                player.updateInventory();
	            }
	            return;
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.parameters().getItemMeta().getDisplayName())) {

	            interfaces.parameters(player);
                player.updateInventory();
	            return;
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.close().getItemMeta().getDisplayName())) {

	            player.closeInventory();
	            player.updateInventory();
	            return;
	        }
	    }
	    
	if (event.getView().getTitle().equals(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	            Translater.getTranslationLangage(player.getUniqueId()).my_models(), player.getUniqueId()) + " : " + playerProfil.getModelsCount()) ||
			event.getView().getTitle().contains(interfaces.getModelsInterface(player).getName())) {

	        event.setCancelled(true);

	        if(Interface.isItemStackNull(event)) {
	        	return;
	        }

	        String a = event.getCurrentItem().getItemMeta().getDisplayName().toString();

	        if (a.contains("§")) {
	            a = a.replaceAll("§", "");
	            a = a.substring(2);
	        }

	        File modelFile = new File(plugin.getDataFolder() + "/data/" + player.getUniqueId() + "/models/" + a + "/", a + ".yml");
	        
	        if (modelFile.exists()) {

	            Model model = new Model(modelFile);

	            if (CacheInterfaces.selected_model.containsKey(player)) {
	                CacheInterfaces.selected_model.replace(player, model);
	            } else {
	                CacheInterfaces.selected_model.put(player, model);
	            }
	            interfaces.models_interface(model, player);
                player.updateInventory();
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.previous().getItemMeta().getDisplayName())) {

	            if (playerProfil.getModelsProgressCount() == 0) {
	                player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                    Translater.getTranslationLangage(player.getUniqueId()).no_page_left(), player.getUniqueId()));
	                player.updateInventory();
	                return;
	            }

	            ModelsPages mp = CacheInterfaces.interfaces_models.get(player);

	            if (mp == null) {
	                player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                    Translater.getTranslationLangage(player.getUniqueId()).no_page_left(), player.getUniqueId()));
	                player.updateInventory();
	                return;
	            }
	            mp.previous();
	            
	            Interface page = mp.getPage(mp.getCurrent());
	            page.showInterface(player);
                player.updateInventory();
	            return;
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.next().getItemMeta().getDisplayName())) {

	            if (playerProfil.getModelsCount() == 0) {
	                player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                    Translater.getTranslationLangage(player.getUniqueId()).no_page_left(), player.getUniqueId()));
	                player.updateInventory();
	                return;
	            }

	            ModelsPages mp = CacheInterfaces.interfaces_models.get(player);

	            if (mp.getCurrent() == mp.getInterfaces().size() - 1) {
	                player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                    Translater.getTranslationLangage(player.getUniqueId()).no_page_left(), player.getUniqueId()));
	                player.updateInventory();
	                return;
	            }
	            mp.next();
	            
	            Interface page = mp.getPage(mp.getCurrent());
	            page.showInterface(player);
                player.updateInventory();
	            return;
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.refresh().getItemMeta().getDisplayName())) {

	            if (playerProfil.getModelsCount() == 0) {
	            	player.closeInventory();
	                interfaces.my_models(player);
	                player.updateInventory();
	                return;
	            }
	            ModelsPages mp = CacheInterfaces.interfaces_models.get(player);
	            Interface page = mp.getPage(mp.getCurrent());
	            
            	player.closeInventory();
	            page.showInterface(player);
                player.updateInventory();
	            return;
	        }
	    }
	      
	if (event.getView().getTitle().equals(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	            Translater.getTranslationLangage(player.getUniqueId()).my_models_progress(), player.getUniqueId()) + " : " + playerProfil.getModelsProgressCount()) 
			|| event.getView().getTitle().contains(interfaces.getModelsProgressInterface(player).getName())) {

	        event.setCancelled(true);

	        if(Interface.isItemStackNull(event)) {
	        	return;
	        }

	        String a = event.getCurrentItem().getItemMeta().getDisplayName();

	        if (a.contains("§")) {
	            a = a.replaceAll("§", "");
	            a = a.substring(2);
	        }

	        File modelFile;
	        modelFile = new File(plugin.getDataFolder() + "/data/" + player.getUniqueId() + "/models progress/" + a + "/", a + ".yml");

	        if (modelFile.exists()) {

	            Model model = new Model(modelFile);

	            if (CacheInterfaces.selected_model.containsKey(player)) {
	                CacheInterfaces.selected_model.replace(player, model);
	            } else {
	                CacheInterfaces.selected_model.put(player, model);
	            }

	            interfaces.models_progress_interface(model, player);
                player.updateInventory();
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.previous().getItemMeta().getDisplayName())) {

	            if (playerProfil.getModelsProgressCount() == 0) {
	                player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                    Translater.getTranslationLangage(player.getUniqueId()).no_page_left(), player.getUniqueId()));
	                player.updateInventory();
	                return;
	            }

	            ModelsProgressPages mpp = CacheInterfaces.interfaces_progress.get(player);

	            if (mpp.getCurrent() == 0) {
	                player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                    Translater.getTranslationLangage(player.getUniqueId()).no_page_left(), player.getUniqueId()));
	                player.updateInventory();
	                return;
	            }
	            mpp.previous();
	            
	            Interface page = mpp.getPage(mpp.getCurrent());
	            page.showInterface(player);
                player.updateInventory();
	            return;
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.next().getItemMeta().getDisplayName())) {

	            if (playerProfil.getModelsProgressCount() == 0) {
	                player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                    Translater.getTranslationLangage(player.getUniqueId()).no_page_left(), player.getUniqueId()));
	                player.updateInventory();
	                return;
	            }

	            ModelsProgressPages mpp = CacheInterfaces.interfaces_progress.get(player);

	            if (mpp.getCurrent() == mpp.getInterfaces().size() - 1) {
	                player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                    Translater.getTranslationLangage(player.getUniqueId()).no_page_left(), player.getUniqueId()));
	                player.updateInventory();
	                return;
	            }

	            mpp.next();
	            
	            Interface page = mpp.getPage(mpp.getCurrent());
	            page.showInterface(player);
                player.updateInventory();
	            return;
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.refresh().getItemMeta().getDisplayName())) {

	            if (playerProfil.getModelsProgressCount() == 0) {
	            	player.closeInventory();
	                interfaces.my_models_progress(player);
	                player.updateInventory();
	                return;
	            }
	            ModelsProgressPages mpp = CacheInterfaces.interfaces_progress.get(player);
	            Interface page = mpp.getPage(mpp.getCurrent());
	            
            	player.closeInventory();
	            page.showInterface(player);
                player.updateInventory();
	            return;
	        }
	    }
	    
	if (event.getView().getTitle().equals(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	            Translater.getTranslationLangage(player.getUniqueId()).parametres(), player.getUniqueId()) + " : " + playerProfil.getPlayerName())) {

	        event.setCancelled(true);

	        if(Interface.isItemStackNull(event)) {
	        	return;
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.close().getItemMeta().getDisplayName())) {
	            player.closeInventory();
	            player.updateInventory();
	            return;
	        }
	        
	        if (event.getCurrentItem().equals(Translater.getFlag(player))) {
	        	
	            interfaces.langages(player);
                player.updateInventory();
	            return;
	        }
	        
	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.models_parameters().getItemMeta().getDisplayName())) {
                player.updateInventory();
	            return;
	        }
	        
	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.administration_panel().getItemMeta().getDisplayName())) {
	        	interfaces.administration_panel(player);
                player.updateInventory();
	            return;
	        }
	    }
	    
	if (event.getView().getTitle().equals(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
			Translater.getTranslationLangage(player.getUniqueId()).administration_panel(), player.getUniqueId()))) {
	    	
	        event.setCancelled(true);

	        if(Interface.isItemStackNull(event)) {
	        	return;
	        }
	        
	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.olders_versions().getItemMeta().getDisplayName())) {
	        	interfaces.olders_versions(player);
                player.updateInventory();
	            return;
	        }
	        
	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.close().getItemMeta().getDisplayName())) {
	            player.closeInventory();
	            player.updateInventory();
	            return;
	        }
	    }
	    
	if (event.getView().getTitle().equals(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
			Translater.getTranslationLangage(player.getUniqueId()).archives_gui(), player.getUniqueId()))) {
	    	
	        event.setCancelled(true);

	        if(Interface.isItemStackNull(event)) {
	        	return;
	        }
	        
	        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Version ")) {
	        	
	        	String version = event.getCurrentItem().getItemMeta().getDisplayName().replace(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Version ", "");
	        	
	        	Updater updater = new Updater();
	        	
	        	player.closeInventory();
	        	soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
	        	player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	        			Translater.getTranslationLangage(player.getUniqueId()).change_version(), player.getUniqueId()) + ("+version+"));
	        	updater.changeVersion(version);
                player.updateInventory();
	            return;
	        }
	        
	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.spigot_page().getItemMeta().getDisplayName())) {
	        	player.closeInventory();
	        	player.sendMessage(ChatColor.YELLOW + "➥ https://www.spigotmc.org/resources/ShadowZ Model Maker.3742/");
                player.updateInventory();
	        	return;
	        }
	    }
	    
	if (event.getView().getTitle().equals(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	            Translater.getTranslationLangage(player.getUniqueId()).informations(), player.getUniqueId()))) {

	        event.setCancelled(true);

	        if(Interface.isItemStackNull(event)) {
	        	return;
	        }

	        if ((event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "Français"))) {
	        	playerProfil.setLangage(player, "FR");
	            interfaces.langages(player);
	            soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
	            player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                Translater.getTranslationLangage(player.getUniqueId()).update_langage(), player.getUniqueId()));
                player.updateInventory();
	            return;
	        }

	        if ((event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "Deutsch"))) {
	        	playerProfil.setLangage(player, "DE");
	            interfaces.langages(player);
	            soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
	            player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                Translater.getTranslationLangage(player.getUniqueId()).update_langage(), player.getUniqueId()));
                player.updateInventory();
	            return;
	        }

	        if ((event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "عربي"))) {
	        	playerProfil.setLangage(player, "AR");
	            interfaces.langages(player);
	            soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
	            player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                Translater.getTranslationLangage(player.getUniqueId()).update_langage(), player.getUniqueId()));
                player.updateInventory();
	            return;
	        }

	        if ((event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "Pусский"))) {
	        	playerProfil.setLangage(player, "RU");
	            interfaces.langages(player);
	            soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
	            player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                Translater.getTranslationLangage(player.getUniqueId()).update_langage(), player.getUniqueId()));
                player.updateInventory();
	            return;
	        }

	        if ((event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "Espanol"))) {
	        	playerProfil.setLangage(player, "ES");
	            interfaces.langages(player);
	            soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
	            player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                Translater.getTranslationLangage(player.getUniqueId()).update_langage(), player.getUniqueId()));
                player.updateInventory();
	            return;
	        }

	        if ((event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "Italiano"))) {
	        	playerProfil.setLangage(player, "IT");
	            interfaces.langages(player);
	            soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
	            player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                Translater.getTranslationLangage(player.getUniqueId()).update_langage(), player.getUniqueId()));
                player.updateInventory();
	            return;
	        }

	        if ((event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "English"))) {
	        	playerProfil.setLangage(player, "GB");
	            interfaces.langages(player);
	            soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
	            player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                Translater.getTranslationLangage(player.getUniqueId()).update_langage(), player.getUniqueId()));
                player.updateInventory();
	            return;
	        }

	        if ((event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "Türk"))) {
	        	playerProfil.setLangage(player, "TR");
	            interfaces.langages(player);
	            soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
	            player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                Translater.getTranslationLangage(player.getUniqueId()).update_langage(), player.getUniqueId()));
                player.updateInventory();
	            return;
	        }

	        String name = event.getCurrentItem().getItemMeta().getDisplayName();
	        
	        name = name.substring(name.length() - 4);
	        name = name.replaceAll("[()]", "");

	        if ((event.getCurrentItem().getItemMeta().getDisplayName().contains(name))) {

	        	playerProfil.setLangage(player, name);
	            interfaces.langages(player);
	            soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
	            player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	                Translater.getTranslationLangage(player.getUniqueId()).update_langage(), player.getUniqueId()));
                player.updateInventory();
	            return;
	        }
	    }
	    
	    if (event.getClickedInventory() == player.getInventory()) {

	        if(Interface.isItemStackNull(event)) {
	        	return;
	        }

	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.tools().getItemMeta().getDisplayName())) {
	        	
	            event.setCancelled(true);
	            
	            if(editor.isInEditor(player)) {
		            interfaces.tools(player);
	                player.updateInventory();
	            }
	            return;
	        }
	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.models_parameters().getItemMeta().getDisplayName())) {
	        	
	            event.setCancelled(true);
                player.updateInventory();
	            return;
	        }
	        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.exit_editor().getItemMeta().getDisplayName())) {
	        	
	            event.setCancelled(true);
	            editor.removeFromEditor(player);
                player.updateInventory();
	            return;
	        }
	    }
	    
	    String name = event.getView().getTitle();
	    String a = "";

	    int count = StringUtils.countMatches(event.getView().getTitle(), "§");

	    if (count == 1) {
	        if (name.contains("§")) {
	            a = name.replaceAll("§", "");
	            a = a.substring(1);
	        }
	    }

	    if (count == 2) {
	        if (name.contains("§")) {
	            a = name.replaceAll("§", "");
	            a = a.substring(2);
	        }
	    }

	    File modelProgressFile = new File(plugin.getDataFolder() + "/data/" + player.getUniqueId() + "/models progress/" + a + "/", a + ".yml");
	    File modelFile = new File(plugin.getDataFolder() + "/data/" + player.getUniqueId() + "/models/" + a + "/", a + ".yml");

	    if (CacheLoaded.selected_loaded.containsKey(player)) {

	        Loaded loaded = CacheLoaded.selected_loaded.get(player);

	        if (event.getView().getTitle().equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + loaded.getName())) {

	            event.setCancelled(true);

		        if(Interface.isItemStackNull(event)) {
		        	return;
		        }

	            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.replace().getItemMeta().getDisplayName())) {

	                player.closeInventory();

	                loaded.remove();
	                loaded.replace(player);
	                player.updateInventory();
	                return;
	            }

	            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.size().getItemMeta().getDisplayName())) {


	                return;
	            }

	            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.delete().getItemMeta().getDisplayName())) {

	                player.closeInventory();
	                interfaces.confirmation_loaded_supression(loaded, player);
	                player.updateInventory();
	                return;
	            }
	        }
	    }
		
		if (modelFile.exists()) {

			if (event.getView().getTitle().equals(name)) {

		        event.setCancelled(true);

		        if(Interface.isItemStackNull(event)) {
		        	return;
		        }

		        Model model = new Model(modelFile);

		        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.load().getItemMeta().getDisplayName())) {

		            player.closeInventory();
		            model.load(player);
	                player.updateInventory();
		            return;
		        }

		        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.edit().getItemMeta().getDisplayName())) {

		            if (!(editor.isInEditor(player))) {
		                editor.addToEditor(player);
		                player.updateInventory();
		            }
		            		            
		            if(CacheEditor.editors.get(player) != null) {
		            			            	
		            	Editor editor = CacheEditor.editors.get(player);
		            	
		            	Editor playerEditor = new Editor(player);
		            	
		            	CacheEditor.editors.remove(player, editor);
		            	CacheEditor.editors.put(player, playerEditor);
		            }
		            
		            Editor playerEditor = CacheEditor.editors.get(player);
		            
		            playerEditor.editModel(player, model);
		            playerEditor.loadModel(player.getLocation());
		            
		            player.closeInventory();
	                player.updateInventory();
		            return;
		        }

		        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.delete().getItemMeta().getDisplayName())) {

		            interfaces.confirmation_model_supression(model, player);
	                player.updateInventory();
		            return;
		        }
		    }
		}
		
		if (modelProgressFile.exists()) {

			if (event.getView().getTitle().equals(name)) {

		        event.setCancelled(true);

		        if(Interface.isItemStackNull(event)) {
		        	return;
		        }

		        Model model = new Model(modelProgressFile);

		        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.publish().getItemMeta().getDisplayName())) {

		            if (model.getParts().size() == 0) {
		                player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
		                    Translater.getTranslationLangage(player.getUniqueId()).insufficient_parts_export(), player.getUniqueId()));
		                soundPlayer.playSound(player, SoundType.NOTIFICATION_DOWN, false);
		                player.updateInventory();
		                return;
		            }

		            player.closeInventory();

		            soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
		            plugin.getActionBar().send(player, Translater.getTranslationLangage(player.getUniqueId()).getMessage(
			                Translater.getTranslationLangage(player.getUniqueId()).model_exported(), player.getUniqueId()));
		            model.exportModel();
		            FileUtils.deleteDirectory(modelProgressFile.getParentFile());
		            CacheInterfaces.selected_model.remove(player, model);
		            interfaces.menu(player);
	                player.updateInventory();
		            return;
		        }

		        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.edit().getItemMeta().getDisplayName())) {
		        	
		            if (!(editor.isInEditor(player))) {
		                editor.addToEditor(player);
		                player.updateInventory();
		            }
		            		            
		            if(CacheEditor.editors.get(player) != null) {
		            			            	
		            	Editor editor = CacheEditor.editors.get(player);
		            	
		            	Editor playerEditor = new Editor(player);
		            	
		            	CacheEditor.editors.remove(player, editor);
		            	CacheEditor.editors.put(player, playerEditor);
		            }
		            
		            Editor playerEditor = CacheEditor.editors.get(player);
		            
		            playerEditor.editModel(player, model);
		            playerEditor.loadModel(player.getLocation());
		            
		            player.closeInventory();
	                player.updateInventory();
		            return;
		        }

		        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.delete().getItemMeta().getDisplayName())) {

		            interfaces.confirmation_model_supression(model, player);
	                player.updateInventory();
		            return;
		        }
		    }
		}
		
		if (CacheLoaded.selected_loaded.containsKey(player)) {
		    if (CacheLoaded.selected_loaded.get(player) != null) {

		        Loaded loaded = CacheLoaded.selected_loaded.get(player);

		        if (event.getView().getTitle().equalsIgnoreCase(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
		                Translater.getTranslationLangage(player.getUniqueId()).delete_confirm_title_loaded(), player.getUniqueId()))) {

		            event.setCancelled(true);

			        if(Interface.isItemStackNull(event)) {
			        	return;
			        }

		            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.confirm_loaded().getItemMeta().getDisplayName())) {

		                player.closeInventory();

		                soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
		                plugin.getActionBar().send(player, Translater.getTranslationLangage(player.getUniqueId()).getMessage(
			                    Translater.getTranslationLangage(player.getUniqueId()).loaded_deleted(), player.getUniqueId()));
		                loaded.remove();
		                loaded.getLoadedFile().delete();
		                CacheLoaded.selected_loaded.remove(player, loaded);
		                player.updateInventory();
		                return;
		            }
		        }
		    }
		}
		
		if (CacheInterfaces.selected_model.containsKey(player)) {
		    if (CacheInterfaces.selected_model.get(player) != null) {

		        Model model = CacheInterfaces.selected_model.get(player);

		        if (event.getView().getTitle().equals(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
		                Translater.getTranslationLangage(player.getUniqueId()).delete_confirm_title(), player.getUniqueId()))) {

		            event.setCancelled(true);

			        if(Interface.isItemStackNull(event)) {
			        	return;
			        }

		            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(items.confirm().getItemMeta().getDisplayName())) {

		                File modelFolder = model.getModelFile().getParentFile();

		                try {

		                    player.closeInventory();

		                    Path loadedFolder = Paths.get(plugin.getDataFolder() + "//data//" + player.getUniqueId() + "//models//" + model.getName() + "//loaded");

		                    if (loadedFolder.toFile().exists()) {

		                        for (int i = 0; i < loadedFolder.toFile().listFiles().length; i++) {

		                            Loaded loaded = new Loaded(loadedFolder.toFile().listFiles()[i]);
		                            loaded.setupLocations();

		                            loaded.remove();
		                        }
		                    }
		                    
		                    soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
		                    plugin.getActionBar().send(player, Translater.getTranslationLangage(player.getUniqueId()).getMessage(
			                        Translater.getTranslationLangage(player.getUniqueId()).model_deleted(), player.getUniqueId()));
		                    
		                    FileUtils.deleteDirectory(modelFolder);
		                    CacheInterfaces.selected_model.remove(player, model);
		                    
		                    Bukkit.getServer().dispatchCommand(player, "mk menu");
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		                player.updateInventory();
		                return;
		            }
		        }
		    }
		}
	}
}