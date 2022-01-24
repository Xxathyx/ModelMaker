package fr.xxathyx.shadowz.modelmaker.sound;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundPlayer {
	
	public void playSound(Player player, SoundType sound, boolean custom) {
		
		if(sound.equals(SoundType.NOTIFICATION_UP)) {
			
			if (!Bukkit.getVersion().contains("1.8")) {
			     player.playSound(player.getEyeLocation(), Sound.valueOf("BLOCK_NOTE_PLING"), 1, 1);
			} else {
			     player.playSound(player.getEyeLocation(), Sound.valueOf("NOTE_PLING"), 1, 1);
			}
		}
		
		if(sound.equals(SoundType.NOTIFICATION_DOWN)) {
			
			if (!Bukkit.getVersion().contains("1.8")) {
			     player.playSound(player.getEyeLocation(), Sound.valueOf("BLOCK_NOTE_BASS"), 1, 1);
			} else {
			     player.playSound(player.getEyeLocation(), Sound.valueOf("NOTE_BASS"), 1, 1);
			}
		}
		
		if(sound.equals(SoundType.CUSTOM) && custom) {
			
		}
	}
}