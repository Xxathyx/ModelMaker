package fr.xxathyx.shadowz.modelmaker.commands;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FilenameUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.profil.Profil;
import fr.xxathyx.shadowz.modelmaker.server.configuration.ServerConfiguration;
import fr.xxathyx.shadowz.modelmaker.translation.CustomTranslation;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;

public class TranslationCommands implements CommandExecutor {
	
    private final Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg3) {
		        
        if(cmd.getName().equalsIgnoreCase("translation")) {
        	if(arg3.length > 1) {
        		if(arg3[0].equalsIgnoreCase("create")) {
        			if(arg3.length == 2) {
            			try {
            				new CustomTranslation().createCustomTranslation(arg3[1], "Custom translation : " + arg3[1], sender.getName(), String.valueOf(1.0));
            				return true;
    					} catch (URISyntaxException | IOException | InvalidConfigurationException e) {
    						e.printStackTrace();
    					}
        			}else {
                		if(sender instanceof Player) {
                			
                			Player player = (Player) sender;
                			
                            player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                                    Translater.getTranslationLangage(player.getUniqueId()).invalid_commands_args(), player.getUniqueId()));
                            return false;
                		}
                		sender.sendMessage(Translater.getServerLangage().getMessage(Translater.getServerLangage().invalid_commands_args()));
                		return false;
        			}
        		}else {
            		if(sender instanceof Player) {
            			
            			Player player = (Player) sender;
            			
                        player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                                Translater.getTranslationLangage(player.getUniqueId()).invalid_commands_args(), player.getUniqueId()));
                        return false;
            		}
            		sender.sendMessage(Translater.getServerLangage().getMessage(Translater.getServerLangage().invalid_commands_args()));
            		return false;
        		}
        		
        		if(arg3[0].equalsIgnoreCase("reset")) {
        			if(arg3.length == 2) {
            			try {
            				new CustomTranslation().createCustomTranslation(arg3[1], "Custom translation : " + arg3[1], sender.getName(), String.valueOf(1.0));
            				return true;
    					} catch (URISyntaxException | IOException | InvalidConfigurationException e) {
    						e.printStackTrace();
    					}
        			}
        		}
        		
        		if(arg3[0].equalsIgnoreCase("edit")) {
        			if(arg3.length == 2) {
        				
        				//
        				
        			}
        		}
        	}else {
        		if(sender instanceof Player) {
        			
        			Player player = (Player) sender;
        			
                    player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                            Translater.getTranslationLangage(player.getUniqueId()).invalid_commands_args(), player.getUniqueId()));
                    return false;
        		}
        		sender.sendMessage(Translater.getServerLangage().getMessage(Translater.getServerLangage().invalid_commands_args()));
        		return false;
        	}
        }
        
        if(cmd.getName().equalsIgnoreCase("translations")) {
        	
        	String translationsList = ChatColor.YELLOW + "Liste des traductions disponible:\n ";
        	
        	File translationsFolder = new File(plugin.getDataFolder() + "/translations/");
        	
    		for (int i = 0; i < translationsFolder.listFiles().length; i++){
    			
    			String translationCountryCode = FilenameUtils.removeExtension(translationsFolder.listFiles()[i].getName());
    			String translation = ChatColor.YELLOW + FilenameUtils.removeExtension(translationsFolder.listFiles()[i].getName()) + "\n";
    			
    			if(sender instanceof Player) {
    				
    				Profil playerProfil = new Profil((Player) sender);
    				
        			if(translationCountryCode.equals(playerProfil.getLangage())) {
        				translation = ChatColor.GREEN + FilenameUtils.removeExtension(translationsFolder.listFiles()[i].getName()) + " (Used)\n";
        			}
    			}
    			
    			if(translationCountryCode.equals(new ServerConfiguration().getServerLangage())) {
    				translation = ChatColor.BLUE + FilenameUtils.removeExtension(translationsFolder.listFiles()[i].getName()) + " (Server)\n";
    			}
    			translationsList = (translationsList + "\n" + translation);
            }
    		sender.sendMessage(translationsList);
        }
		return false;
	}
}