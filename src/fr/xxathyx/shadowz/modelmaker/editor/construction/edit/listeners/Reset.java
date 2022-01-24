package fr.xxathyx.shadowz.modelmaker.editor.construction.edit.listeners;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import fr.xxathyx.shadowz.modelmaker.editor.CacheEditor;
import fr.xxathyx.shadowz.modelmaker.editor.Editor;
import fr.xxathyx.shadowz.modelmaker.items.Tools;
import fr.xxathyx.shadowz.modelmaker.part.Part;
import fr.xxathyx.shadowz.modelmaker.part.hitbox.Size;
import fr.xxathyx.shadowz.modelmaker.util.FacingLocation;

public class Reset implements Listener {
	
    private Editor editor = new Editor();
    private Tools tools = new Tools();

    @EventHandler
    public void movePart(PlayerInteractAtEntityEvent event) throws FileNotFoundException, IOException, InvalidConfigurationException {

        ArmorStand armorStand = null;

        if (editor.isInEditor(event.getPlayer())) {

            Editor playerEditor = CacheEditor.editors.get(event.getPlayer());

            ItemStack itemInHand = event.getPlayer().getItemInHand();

            if (itemInHand.equals(tools.reset_part_rotations(event.getPlayer()))) {
                if (playerEditor.getModel() != null) {
                    for (Entity entity: event.getPlayer().getWorld().getNearbyEntities(event.getPlayer().getLocation(), 3, 3, 3)) {
                        if (entity instanceof ArmorStand) {
                            if (FacingLocation.getLookingAt(event.getPlayer(), (LivingEntity) entity)) {
                                armorStand = (ArmorStand) entity;
                            }
                        }
                    }
                }
            }

            if (armorStand != null) {
                if (playerEditor.findPart(armorStand.getLocation()) != null) {

                    Part clickedPart = playerEditor.findPart(armorStand.getLocation());
                    
                    if(clickedPart.getSize().equals(Size.NORMAL)) {
                        clickedPart.setAngleX(0);
                        clickedPart.setAngleY(0);
                        clickedPart.setAngleZ(0);
                    }
                    
                    armorStand.setHeadPose(new EulerAngle(clickedPart.getAngleX(), clickedPart.getY(), clickedPart.getAngleZ()));
                    
                    playerEditor.updatePartsLocations();
                    event.setCancelled(true);
                }
            }
        }
    }
}