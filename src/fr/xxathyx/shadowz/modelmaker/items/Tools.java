package fr.xxathyx.shadowz.modelmaker.items;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.xxathyx.shadowz.modelmaker.translation.Translater;

public class Tools {
	
	public ItemStack tools_box(Player player) {
		return new ItemStacks(player).tools();
	}
	
	public ItemStack model_settings(Player player) {
		return new ItemStacks(player).models_parameters();
	}
	
	public ItemStack leave_editor(Player player) {
		return new ItemStacks(player).exit_editor();
	}
	
	public ItemStack location_x(Player player) {
		
	    ItemStack location_x = new ItemStack(Material.ARROW, 1);
	    ItemMeta location_x_meta = location_x.getItemMeta();
	    
	    location_x_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).location_x(), player.getUniqueId()));
	    location_x_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).location_x_description(), player.getUniqueId()) }));
	    location_x.setItemMeta(location_x_meta);
	    
		return location_x;
	}
	
	public ItemStack location_y(Player player) {
		
	    ItemStack location_y = new ItemStack(Material.ARROW, 1);
	    ItemMeta location_y_meta = location_y.getItemMeta();
	    
	    location_y_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).location_y(), player.getUniqueId()));
	    location_y_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).location_y_description(), player.getUniqueId()) }));
	    location_y.setItemMeta(location_y_meta);
	    
		return location_y;
	}
	
	public ItemStack location_z(Player player) {
		
	    ItemStack location_z = new ItemStack(Material.ARROW, 1);
	    ItemMeta location_z_meta = location_z.getItemMeta();
	    
	    location_z_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).location_z(), player.getUniqueId()));
	    location_z_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).location_z_description(), player.getUniqueId()) }));
	    location_z.setItemMeta(location_z_meta);
	    
		return location_z;
	}
	
	public ItemStack angle_w(Player player) {
		
	    ItemStack angle_w = new ItemStack(Material.STICK, 1);
	    ItemMeta angle_w_meta = angle_w.getItemMeta();
	    
	    angle_w_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).angle_w(), player.getUniqueId()));
	    angle_w_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).angle_w_description(), player.getUniqueId()) }));
	    angle_w.setItemMeta(angle_w_meta);
	    
		return angle_w;
	}
	
	public ItemStack angle_x(Player player) {
		
	    ItemStack angle_x = new ItemStack(Material.STICK, 1);
	    ItemMeta angle_x_meta = angle_x.getItemMeta();
	    
	    angle_x_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).angle_x(), player.getUniqueId()));
	    angle_x_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).angle_x_description(), player.getUniqueId()) }));
	    angle_x.setItemMeta(angle_x_meta);
	    
		return angle_x;
	}
	
	public ItemStack angle_y(Player player) {
		
	    ItemStack angle_y = new ItemStack(Material.STICK, 1);
	    ItemMeta angle_y_meta = angle_y.getItemMeta();
	    
	    angle_y_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).angle_y(), player.getUniqueId()));
	    angle_y_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).angle_y_description(), player.getUniqueId()) }));
	    angle_y.setItemMeta(angle_y_meta);
	    
		return angle_y;
	}
	
	public ItemStack angle_z(Player player) {
		
	    ItemStack angle_z = new ItemStack(Material.STICK, 1);
	    ItemMeta angle_z_meta = angle_z.getItemMeta();
	    
	    angle_z_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).angle_z(), player.getUniqueId()));
	    angle_z_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).angle_z_description(), player.getUniqueId()) }));
	    angle_z.setItemMeta(angle_z_meta);
	    
		return angle_z;
	}
	
	public ItemStack size(Player player) {
		
	    ItemStack size = new ItemStack(Material.ARMOR_STAND, 1);
	    ItemMeta size_meta = size.getItemMeta();
	    
	    size_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).size(), player.getUniqueId()));
	    size_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).size_description(), player.getUniqueId()) }));
	    size.setItemMeta(size_meta);
	    
		return size;
	}
	
	public ItemStack reset_part_rotations(Player player) {
		
	    ItemStack reset_part_rotations = new ItemStack(Material.FLINT_AND_STEEL, 1);
	    ItemMeta reset_part_rotations_meta = reset_part_rotations.getItemMeta();
	    
	    reset_part_rotations_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).reset_part_rotations(), player.getUniqueId()));
	    reset_part_rotations_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).reset_part_rotations_description(), player.getUniqueId()) }));
	    reset_part_rotations.setItemMeta(reset_part_rotations_meta);
	    
		return reset_part_rotations;
	}
	
	public ItemStack undo(Player player) {
		
	    ItemStack undo = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
	    ItemMeta undo_meta = undo.getItemMeta();
	    
	    undo_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).undo(), player.getUniqueId()));
	    undo_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).undo_description(), player.getUniqueId()) }));
	    undo.setItemMeta(undo_meta);
	    
		return undo;
	}
	
	public ItemStack save(Player player) {
		
	    ItemStack save = new ItemStack(Material.TORCH, 1);
	    ItemMeta save_meta = save.getItemMeta();
	    
	    save_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).save(), player.getUniqueId()));
	    save_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).save_description(), player.getUniqueId()) }));
	    save.setItemMeta(save_meta);
	    
		return save;
	}
	
	public ItemStack close(Player player) {
		
		ItemStack close = new ItemStack(Material.BARRIER, 1);
	    ItemMeta close_meta = close.getItemMeta();
	    
	    close_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).close_gui(), player.getUniqueId()));
	    close_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).close_gui_description(), player.getUniqueId()) }));
	    close.setItemMeta(close_meta);
	    
		return close;
	}
	
	public ArrayList<ItemStack> getTools(Player player) {
		
		ArrayList<ItemStack> tools = new ArrayList<ItemStack>();
		
		tools.add(location_x(player));
		tools.add(location_y(player));
		tools.add(location_z(player));
		tools.add(angle_w(player));
		tools.add(angle_x(player));
		tools.add(angle_y(player));
		tools.add(angle_z(player));
		tools.add(size(player));
		tools.add(reset_part_rotations(player));
		tools.add(undo(player));
		tools.add(save(player));
		tools.add(close(player));
		
		return tools;
	}
}