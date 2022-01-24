package fr.xxathyx.shadowz.modelmaker.profil.listeners;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.profil.Profil;
import fr.xxathyx.shadowz.modelmaker.server.configuration.ServerConfiguration;
import fr.xxathyx.shadowz.modelmaker.sound.SoundPlayer;
import fr.xxathyx.shadowz.modelmaker.sound.SoundType;
import fr.xxathyx.shadowz.modelmaker.util.Host;

public class Creation implements Listener {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private ServerConfiguration serverConfiguration = new ServerConfiguration();
	private SoundPlayer soundPlayer = new SoundPlayer();
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent event) {
		
		Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
			if(!new File(plugin.getDataFolder() + "/profils/").isFile()) {
				
				Player player = event.getPlayer();
				
				File file = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId() + ".yml");
				
				if(!file.exists()) {
					file.getParentFile().mkdir();
				}
				
				Date date = new Date();
				DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
				
				Profil profil = new Profil(file);
				
				if(profil.exists()) {
					if(serverConfiguration.getAutomaticUserChange()) {
						if(profil.isAdaptive()) {
							Host host = new Host(player.getAddress().getHostName());
							try {
								profil.setLangage(player, host.getCOUNTRYCODE());
							} catch (IOException | InvalidConfigurationException e) {
								e.printStackTrace();
							}
						}
					}
					try {
						profil.updateConfig(player);
						return;
					} catch (URISyntaxException | IOException e) {
						e.printStackTrace();
					}
				}
				try {
					profil.createProfil(shortDateFormat.format(date).toString(), player.getUniqueId(), player.getName(), player.getAddress().getHostName());
				} catch (IOException e) {
					e.printStackTrace();
				}
				soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
			}
			Profil profil = new Profil();
			try {
				profil.createDataFolder(event.getPlayer().getUniqueId());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}