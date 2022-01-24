 package fr.xxathyx.shadowz.modelmaker.editor.construction.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.xxathyx.shadowz.modelmaker.editor.CacheEditor;
import fr.xxathyx.shadowz.modelmaker.editor.Editor;
import fr.xxathyx.shadowz.modelmaker.items.Tools;
import fr.xxathyx.shadowz.modelmaker.model.Model;
import fr.xxathyx.shadowz.modelmaker.part.Part;
import fr.xxathyx.shadowz.modelmaker.part.hitbox.Size;
import fr.xxathyx.shadowz.modelmaker.project.Project;
import fr.xxathyx.shadowz.modelmaker.project.logs.ActionType;
import fr.xxathyx.shadowz.modelmaker.util.FacingLocation;

public class Place implements Listener {
	
	private Editor editor = new Editor();
	private Tools tools = new Tools();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void placePart(PlayerInteractEvent event) throws IOException, InvalidConfigurationException {
				
		if(editor.isInEditor(event.getPlayer()) == true) {
			
			Player player = event.getPlayer();
			
			Editor playerEditor = CacheEditor.editors.get(player);
			
			if(playerEditor.getModel() != null) {
				if(player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR) {
					if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
						
						ItemStack itemInHand = player.getItemInHand();
						
						if(!tools.getTools(event.getPlayer()).contains(itemInHand)) {
							
							Location playerLocation = player.getLocation();
							playerLocation = FacingLocation.getLocationFacing(playerLocation);
							
							Model selectedModel = playerEditor.getModel();
							Project Modelproject = selectedModel.getProject();
													
							int partNumber = new File(selectedModel.getModelFile().getParentFile() + "/parts/").listFiles().length;
							
							File createdPartFile = new File(selectedModel.getModelFile().getParentFile() + "/parts/", String.valueOf(partNumber++) + ".yml");
							
							Part createdPart = new Part(createdPartFile);
													
							Location baseOrigin = Modelproject.getBase();
							
							double differenceX = playerLocation.getX() - baseOrigin.getX();
							double differenceY = playerLocation.getY() - baseOrigin.getY();
							double differenceZ = playerLocation.getZ() - baseOrigin.getZ();
							
							if(itemInHand.getType().isBlock()) {
								createdPart.createPart(createdPartFile, selectedModel.getName(), selectedModel.getModelFile().getParent(),
										selectedModel.getOwner(), selectedModel.getUUID(), partNumber++, itemInHand.getTypeId(), itemInHand.getData().getData(), Size.NORMAL,
										0, 0, 0, differenceX, differenceY, differenceZ);
								selectedModel.getProject().getLogs().write(ActionType.PLACE, itemInHand.getTypeId() + ", " + itemInHand.getData().getData() + ", " + Size.NORMAL.toString()
								+ ", 0, 0, 0, " + differenceX + ", " + differenceY + ", " + differenceZ);
								
								playerEditor.addPartLocation(createdPart);
							}else {
								createdPart.createPart(createdPartFile, selectedModel.getName(), selectedModel.getModelFile().getParent(),
										selectedModel.getOwner(), selectedModel.getUUID(), partNumber++, itemInHand.getTypeId(), itemInHand.getData().getData(), Size.MINIMUM,
										0, 0, 0, differenceX, differenceY, differenceZ);
								selectedModel.getProject().getLogs().write(ActionType.PLACE, itemInHand.getTypeId() + ", " + itemInHand.getData().getData() + ", " + Size.MINIMUM.toString()
								+ ", 0, 0, 0, " + differenceX + ", " + differenceY + ", " + differenceZ);
								
								playerEditor.addPartLocation(createdPart);
							}
							createdPart.load(baseOrigin);
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void placePartOnEntity(PlayerInteractAtEntityEvent event) throws IOException, InvalidConfigurationException {
		
		Player player = event.getPlayer();
		
		if(editor.isInEditor(player) == true) {
			
			Editor playerEditor = CacheEditor.editors.get(player);
			
			if(playerEditor.getModel() != null) {
				if(player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR) {
					
					ItemStack itemInHand = player.getItemInHand();
					
					if(!tools.getTools(event.getPlayer()).contains(itemInHand)) {
						
						Location playerLocation = player.getLocation();
						playerLocation = FacingLocation.getLocationFacing(playerLocation);
						
						Model selectedModel = playerEditor.getModel();
						Project Modelproject = selectedModel.getProject();
												
						int partNumber = new File(selectedModel.getModelFile().getParentFile() + "/parts/").listFiles().length;
						
						File createdPartFile = new File(selectedModel.getModelFile().getParentFile() + "/parts/", String.valueOf(partNumber++) + ".yml");
						
						Part createdPart = new Part(createdPartFile);
												
						Location baseOrigin = Modelproject.getBase();
						
						double differenceX = playerLocation.getX() - baseOrigin.getX();
						double differenceY = playerLocation.getY() - baseOrigin.getY();
						double differenceZ = playerLocation.getZ() - baseOrigin.getZ();
						
						if(itemInHand.getType().isBlock()) {
							createdPart.createPart(createdPartFile, selectedModel.getName(), selectedModel.getModelFile().getParent(),
									selectedModel.getOwner(), selectedModel.getUUID(), partNumber++, itemInHand.getTypeId(), itemInHand.getData().getData(), Size.NORMAL,
									0, 0, 0, differenceX, differenceY, differenceZ);
							selectedModel.getProject().getLogs().write(ActionType.PLACE, itemInHand.getTypeId() + ", " + itemInHand.getData().getData() + ", " + Size.NORMAL.toString()
							+ ", 0, 0, 0, " + differenceX + ", " + differenceY + ", " + differenceZ);
							
							playerEditor.addPartLocation(createdPart);
						}else {
							createdPart.createPart(createdPartFile, selectedModel.getName(), selectedModel.getModelFile().getParent(),
									selectedModel.getOwner(), selectedModel.getUUID(), partNumber++, itemInHand.getTypeId(), itemInHand.getData().getData(), Size.MINIMUM,
									0, 0, 0, differenceX, differenceY, differenceZ);
							selectedModel.getProject().getLogs().write(ActionType.PLACE, itemInHand.getTypeId() + ", " + itemInHand.getData().getData() + ", " + Size.MINIMUM.toString()
							+ ", 0, 0, 0, " + differenceX + ", " + differenceY + ", " + differenceZ);
							
							playerEditor.addPartLocation(createdPart);
						}
						createdPart.load(baseOrigin);
						event.setCancelled(true);
					}
				}
			}
		}
	}
}