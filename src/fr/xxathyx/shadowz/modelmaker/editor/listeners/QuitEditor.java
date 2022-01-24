package fr.xxathyx.shadowz.modelmaker.editor.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.xxathyx.shadowz.modelmaker.editor.Editor;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;

public class QuitEditor implements Listener {
	
	private Editor editor = new Editor();
	
	@EventHandler
	public void quitEditor(PlayerInteractEvent event) {
		
		if(editor.isInEditor(event.getPlayer())) {
			if(event.getPlayer().getItemInHand().hasItemMeta()) {
				if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(Translater.getTranslationLangage(event.getPlayer().getUniqueId()).getMessage(
						Translater.getTranslationLangage(event.getPlayer().getUniqueId()).exit_editor(), event.getPlayer().getUniqueId()))) {
		    		editor.removeFromEditor(event.getPlayer());
		    		event.setCancelled(true);
				}
			}
		}
	}
}