package fr.xxathyx.shadowz.modelmaker.util;

import java.util.ArrayList;

import org.bukkit.Material;

public class Content {
	
	public static ArrayList<Material> getTranslucentBlocks() {
		
		ArrayList<Material> blocks = new ArrayList<Material>();
		
		blocks.add(Material.SAPLING);
		blocks.add(Material.CROPS);
		blocks.add(Material.DOUBLE_PLANT);
		blocks.add(Material.LONG_GRASS);
		blocks.add(Material.DEAD_BUSH);
		blocks.add(Material.YELLOW_FLOWER);
		blocks.add(Material.RED_ROSE);
		blocks.add(Material.BROWN_MUSHROOM);
		blocks.add(Material.RED_MUSHROOM);
		blocks.add(Material.TORCH);
		blocks.add(Material.SNOW);
		blocks.add(Material.CARPET);
		blocks.add(Material.WOOD_BUTTON);
		blocks.add(Material.STONE_BUTTON);
		blocks.add(Material.LEVER);
		blocks.add(Material.WOOD_PLATE);
		blocks.add(Material.STONE_PLATE);
		blocks.add(Material.IRON_PLATE);
		blocks.add(Material.GOLD_PLATE);
		blocks.add(Material.TRAP_DOOR);
		blocks.add(Material.IRON_TRAPDOOR);
		blocks.add(Material.DAYLIGHT_DETECTOR);
		blocks.add(Material.DAYLIGHT_DETECTOR_INVERTED);
		blocks.add(Material.REDSTONE_WIRE);
		blocks.add(Material.REDSTONE_COMPARATOR);
		blocks.add(Material.REDSTONE_COMPARATOR_OFF);
		blocks.add(Material.REDSTONE_COMPARATOR_ON);
		blocks.add(Material.REDSTONE_LAMP_OFF);
		blocks.add(Material.REDSTONE_LAMP_ON);
		blocks.add(Material.DIODE_BLOCK_OFF);
		blocks.add(Material.DIODE_BLOCK_ON);
		blocks.add(Material.SUGAR_CANE_BLOCK);
		blocks.add(Material.RAILS);
		blocks.add(Material.ACTIVATOR_RAIL);
		blocks.add(Material.DETECTOR_RAIL);
		blocks.add(Material.POWERED_RAIL);
		blocks.add(Material.WATER);
		blocks.add(Material.STATIONARY_WATER);
		blocks.add(Material.LAVA);
		blocks.add(Material.STATIONARY_LAVA);
		blocks.add(Material.SIGN_POST);
		
		return blocks;
	}
	
	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
  
        ArrayList<T> newList = new ArrayList<T>();
  
        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }
}