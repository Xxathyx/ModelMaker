package fr.xxathyx.shadowz.modelmaker.util;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class FacingLocation {
	
	public static Location getLocationFacing(Location original) {
	
	    Location location = original.clone();
	    Vector vector = original.clone().getDirection();
	    
	    vector.multiply(3);
	    location.add(vector);
	    location.setYaw(location.getYaw() + 180.0F);
	    location.setPitch(location.getPitch() + 90.0F);
	    
	    boolean ok = false;
	    
	    for (int i = 0; i < 5; i++) {
	      if (location.getBlock().getType().isSolid()) {
	    	  location.add(0.0D, 1.0D, 0.0D);
	        
	      }else {
	        ok = true;
	        break;
	      }
	    }
	    
	    if (!ok) {
	    	location.subtract(0.0D, 5.0D, 0.0D);
	    }
	    return location;
	  }
	
	public static boolean getLookingAt(Player player, LivingEntity livingEntity) {
		
	    Location eye = player.getEyeLocation();
	    Vector toEntity = livingEntity.getEyeLocation().toVector().subtract(eye.toVector());
	    double dot = toEntity.normalize().dot(eye.getDirection());

	    return dot > 0.99D;
	}
}