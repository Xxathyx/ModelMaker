package fr.xxathyx.shadowz.modelmaker.updater.listeners;

import java.io.IOException;
import java.net.UnknownHostException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.xxathyx.shadowz.modelmaker.updater.Updater;

public class Uptodate implements Listener {
	
	private final Updater updater = new Updater();
	
	public void onJoin(PlayerJoinEvent event) throws UnknownHostException, IOException {
		
		if(event.getPlayer().hasPermission("modelmaker.admin")) {
						
			Player player = event.getPlayer();
			
			if(updater.toUpdate()) {
				player.sendMessage(ChatColor.RED + "La version utilis�e de ShadowZ Model Maker n'est pas � jour.");
				player.sendMessage(ChatColor.RED + "Veuillez installer la mise-�-jour en jeu ou depuis le site.");
			}
		}
	}
}