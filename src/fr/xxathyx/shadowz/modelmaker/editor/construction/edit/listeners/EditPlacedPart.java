package fr.xxathyx.shadowz.modelmaker.editor.construction.edit.listeners;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

import fr.xxathyx.shadowz.modelmaker.editor.CacheEditor;
import fr.xxathyx.shadowz.modelmaker.editor.Editor;

public class EditPlacedPart implements Listener {
	
	private Editor editor = new Editor();
	
	@EventHandler
	public void editArmorStands(PlayerArmorStandManipulateEvent event) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		if(editor.isInEditor(event.getPlayer())) {
			
			Editor playerEditor = CacheEditor.editors.get(event.getPlayer());
			
			if(playerEditor.getModel() != null) {
				
				if(playerEditor.findPart(event.getRightClicked().getLocation()) != null) {
					event.setCancelled(true);
				}
			}
		}
	}
}