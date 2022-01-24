package fr.xxathyx.shadowz.modelmaker.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CacheEditor {
	
	public static Map<Player, ItemStack[]> inventory = new HashMap<Player, ItemStack[]>();
	public static Map<Player, GameMode> gamemode = new HashMap<Player, GameMode>();
	
	public static Map<Player, Editor> editors = new HashMap<Player, Editor>();
		
	public static ArrayList<Player> editor = new ArrayList<Player>();
}