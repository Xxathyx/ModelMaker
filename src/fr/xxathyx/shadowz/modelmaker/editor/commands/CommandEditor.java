package fr.xxathyx.shadowz.modelmaker.editor.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.xxathyx.shadowz.modelmaker.editor.Editor;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;

public class CommandEditor implements CommandExecutor {
	
	private final Editor editor = new Editor();
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg3) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Translater.getServerLangage().getMessage(Translater.getServerLangage().invalid_sender()));
			return true;
		}
				
		if(cmd.getName().equalsIgnoreCase("editor")) {
			
			Player player = (Player) sender;
			
			if(arg3.length == 1) {
				if(arg3[0].equalsIgnoreCase("enter")) {
					 
					if(editor.isInEditor(player)) {
						player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
								Translater.getTranslationLangage(player.getUniqueId()).editor_in(), player.getUniqueId()));
						return false;
					}else {
						editor.addToEditor(player);
						return true;
					}
				 }
				
				if(arg3[0].equalsIgnoreCase("leave")) {
					if(editor.isInEditor(player)) {
						editor.removeFromEditor(player);
						return true;
					}else {
						player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
								Translater.getTranslationLangage(player.getUniqueId()).not_in_editor(), player.getUniqueId()));
						return false;
					}
				}
			}
		}
		return false;
	}
}