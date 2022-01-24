package fr.xxathyx.shadowz.modelmaker.editor.construction.edit.listeners;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import fr.xxathyx.shadowz.modelmaker.editor.CacheEditor;
import fr.xxathyx.shadowz.modelmaker.editor.Editor;
import fr.xxathyx.shadowz.modelmaker.items.Tools;
import fr.xxathyx.shadowz.modelmaker.math.Quaternion;
import fr.xxathyx.shadowz.modelmaker.part.Part;
import fr.xxathyx.shadowz.modelmaker.part.hitbox.HitBox;
import fr.xxathyx.shadowz.modelmaker.part.hitbox.Size;
import fr.xxathyx.shadowz.modelmaker.profil.Profil;

public class Rotate implements Listener {

    private Editor editor = new Editor();
    private Tools tools = new Tools();
    
    @EventHandler
    public void anglePart(PlayerInteractAtEntityEvent event) throws FileNotFoundException, IOException, InvalidConfigurationException {

        Player player = event.getPlayer();
                
        if (editor.isInEditor(player)) {

            if (event.getRightClicked() instanceof ArmorStand) {
            	            	
                ArmorStand clickedArmorStand = null;
                
                clickedArmorStand = (ArmorStand) event.getRightClicked();
                               
                HitBox clickedPartHibox = new HitBox(new Quaternion().fromEuler(((ArmorStand) event.getRightClicked()).getHeadPose()), Size.NORMAL);
                                
                if(clickedPartHibox.hitBox(event.getClickedPosition())) {
                	clickedArmorStand = (ArmorStand) event.getRightClicked();
                }else {
                	                	                	
           			for (double l = 0; l < 1; l = l + 0.01) {
        				
        				Vector clickedPosition = event.getClickedPosition();        				
        				clickedPosition.add(new Vector(l, l, l));
        				        				
        				if(clickedPartHibox.hitBox(clickedPosition)) {
        					clickedArmorStand = (ArmorStand) event.getRightClicked();
                    		l = 1;
                    	}else {
                    		event.setCancelled(true);
                    		return;
                    	}
        			}
                }
                
                if(clickedArmorStand != null) {
                	
                    Editor playerEditor = CacheEditor.editors.get(player);
                    
                    if (playerEditor.getModel() != null) {

                        ItemStack itemInHand = player.getItemInHand(); 

                        if (itemInHand.equals(tools.angle_x(player)) || itemInHand.equals(tools.angle_y(player)) || itemInHand.equals(tools.angle_z(player))) {

                            if (playerEditor.findPart(clickedArmorStand.getLocation()) != null) {

                                Part clickedPart = playerEditor.findPart(clickedArmorStand.getLocation());
                                Profil profil = new Profil(player);
                                 
                                if (itemInHand.equals(tools.angle_x(player))) {
                                	
                                	clickedPart.setAngleX(clickedPart.getAngleX() + profil.getPrecision());
                                	
                                	Quaternion quaternion = new Quaternion(new EulerAngle(clickedPart.getAngleX(), clickedPart.getAngleY(), clickedPart.getAngleZ()));
                                	EulerAngle eulerAngle = quaternion.toEuler();
                                	                                	
                                	clickedArmorStand.setHeadPose(new EulerAngle(eulerAngle.getX(), eulerAngle.getY(), eulerAngle.getZ()));
                                }
                                
                                if (itemInHand.equals(tools.angle_y(player))) {
                                	
                                	clickedPart.setAngleY(clickedPart.getAngleY() + profil.getPrecision());
                                	
                                	Quaternion quaternion = new Quaternion(new EulerAngle(clickedPart.getAngleX(), clickedPart.getAngleY(), clickedPart.getAngleZ()));
                                    EulerAngle eulerAngle = quaternion.toEuler();
                                	                                    
                                    clickedArmorStand.setHeadPose(new EulerAngle(eulerAngle.getX(), eulerAngle.getY(), eulerAngle.getZ()));
                                }
                                
                                if (itemInHand.equals(tools.angle_z(player))) {
                                	
                                	clickedPart.setAngleZ(clickedPart.getAngleZ() + profil.getPrecision());
                                	
                                	Quaternion quaternion = new Quaternion(new EulerAngle(clickedPart.getAngleX(), clickedPart.getAngleY(), clickedPart.getAngleZ()));
                                    EulerAngle eulerAngle = quaternion.toEuler();
                                	                                    
                                    clickedArmorStand.setHeadPose(new EulerAngle(eulerAngle.getX(), eulerAngle.getY(), eulerAngle.getZ()));
                                }
                                
                                Quaternion quaternion = new Quaternion(new EulerAngle(clickedPart.getAngleX(), clickedPart.getAngleY(), clickedPart.getAngleZ()));
                                
                                System.out.print("Quaternion -> W: " + quaternion.getW() + "X: " + quaternion.getX() + " Y: " + quaternion.getY() + " Z: " + quaternion.getZ());
                                System.out.print("Eulers Angles -> X: " + Math.toDegrees(clickedArmorStand.getHeadPose().getX()) + " Y: " + Math.toDegrees(clickedArmorStand.getHeadPose().getY()) + " Z: " + Math.toDegrees(clickedArmorStand.getHeadPose().getZ()));
                                
                                event.setCancelled(true);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}