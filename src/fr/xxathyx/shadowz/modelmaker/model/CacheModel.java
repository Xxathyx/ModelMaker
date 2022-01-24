package fr.xxathyx.shadowz.modelmaker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

public class CacheModel {
	
	public static Map<Player, ArrayList<ArmorStand>> holding = new HashMap<Player, ArrayList<ArmorStand>>();
}