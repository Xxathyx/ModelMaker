package fr.xxathyx.shadowz.modelmaker.Interface.interfaces;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.pages.ModelsPages;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.pages.ModelsProgressPages;
import fr.xxathyx.shadowz.modelmaker.model.Model;

public class CacheInterfaces {
	
	public static Map<Player, ModelsProgressPages> interfaces_progress = new HashMap<Player, ModelsProgressPages>();
	public static Map<Player, ModelsPages> interfaces_models = new HashMap<Player, ModelsPages>();
	
	public static Map<Player, Model> selected_model = new HashMap<Player, Model>();
}