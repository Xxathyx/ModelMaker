package fr.xxathyx.shadowz.modelmaker.items;

import java.io.File;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.editor.Editor;
import fr.xxathyx.shadowz.modelmaker.profil.Profil;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;

public class ItemStacks {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private Player player;
	
	public ItemStacks(Player player) {
		this.player = player;
	}
	
	public ItemStack my_models() {
		
		File file = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId().toString() + ".yml");
		
		Profil profil = new Profil(file);
		
		ItemStack my_models = new ItemStack(Material.ENDER_CHEST, profil.getModelsCount());
	    ItemMeta my_models_meta = my_models.getItemMeta();
	    
	    my_models_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).my_models(), player.getUniqueId()));
	    my_models_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).my_models_description(), player.getUniqueId())}));
	    my_models.setItemMeta(my_models_meta);
		
	    return my_models;
	}
	
	public ItemStack my_models_progress() {
		
		File file = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId().toString() + ".yml");
		
		Profil profil = new Profil(file);
		
		ItemStack my_models_progress = new ItemStack(Material.CHEST, profil.getModelsProgressCount());
	    ItemMeta my_models_progress_meta = my_models_progress.getItemMeta();
	    
	    my_models_progress_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).my_models_progress(), player.getUniqueId()));
	    my_models_progress_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).my_models_progress_description(), player.getUniqueId()) }));
	    my_models_progress.setItemMeta(my_models_progress_meta);
	    
		return my_models_progress;
	}
	
	public ItemStack create_model() {
		
	    ItemStack create_model = new ItemStack(Material.GOLD_AXE, 1);
	    ItemMeta create_model_meta = create_model.getItemMeta();
	    
	    create_model_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	    create_model_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).create_model(), player.getUniqueId()));
	    create_model_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).create_model_description(), player.getUniqueId()) }));
	    create_model.setItemMeta(create_model_meta);
	    
	    Editor editor = new Editor();
	    
  	    if(!editor.isInEditor(player)) {
  	    	create_model.removeEnchantment(Enchantment.DURABILITY);
  	    }else {
  	    	create_model.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
  	    }
  	    
  	    return create_model;
	}
	
	public ItemStack parameters() {
		
	    ItemStack parameters = new ItemStack(Material.REDSTONE_COMPARATOR, 1);
	    ItemMeta parameters_meta = parameters.getItemMeta();
	    
	    parameters_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).parametres(), player.getUniqueId()));
	    parameters_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).parametres_description(), player.getUniqueId()) }));
	    parameters.setItemMeta(parameters_meta);
		
	    return parameters;
	}
	
	public ItemStack close() {
		
	    ItemStack close = new ItemStack(Material.BARRIER, 1);
	    ItemMeta close_meta = close.getItemMeta();
	    
	    close_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).close_gui(), player.getUniqueId()));
	    close_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).close_gui_description(), player.getUniqueId()) }));
	    close.setItemMeta(close_meta);
		
	    return close;
	}
	
	public ItemStack glass() {
		
  	    ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
	    ItemMeta glass_meta = glass.getItemMeta();
	    
	    glass_meta.setDisplayName("§0.");
	    glass.setItemMeta(glass_meta);
		
	    return glass;
	}
	
	public ItemStack previous() {
		
	    ItemStack previous = new ItemStack(Material.PAPER, 1);
	    ItemMeta previous_meta = previous.getItemMeta();
	    
	    previous_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).previous_page(), player.getUniqueId()));
	    previous_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).previous_page_description(), player.getUniqueId()) }));
	    previous.setItemMeta(previous_meta);
	    
	    return previous;
	}
	
	public ItemStack refresh() {
		
	    ItemStack refresh = new ItemStack(Material.DOUBLE_PLANT, 1);
	    ItemMeta refresh_meta = refresh.getItemMeta();
	    
	    refresh_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).actualize(), player.getUniqueId()));
	    refresh_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).actualize_description(), player.getUniqueId()) }));
	    refresh.setItemMeta(refresh_meta);
		
	    return refresh;
	}
	
	public ItemStack next() {
		
	    ItemStack next = new ItemStack(Material.PAPER, 1);
	    ItemMeta next_meta = next.getItemMeta();
	    
	    next_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).next_page(), player.getUniqueId()));
	    next_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).next_page_description(), player.getUniqueId()) }));
	    next.setItemMeta(next_meta);
		
	    return next;
	}
	
	public ItemStack informations() {
		
	    ItemStack informations = new ItemStack(Material.MAP, 1);
	    ItemMeta informations_meta = informations.getItemMeta();
	    
	    informations_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).informations(), player.getUniqueId()));
	    informations_meta.setLore(Arrays.asList(new String[] {  Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				        Translater.getTranslationLangage(player.getUniqueId()).informations_description().get(0), player.getUniqueId()), Translater.getTranslationLangage(player.getUniqueId()).getMessage(
						Translater.getTranslationLangage(player.getUniqueId()).informations_description().get(1), player.getUniqueId()), Translater.getTranslationLangage(player.getUniqueId()).getMessage(
						Translater.getTranslationLangage(player.getUniqueId()).informations_description().get(2), player.getUniqueId()), Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	    				Translater.getTranslationLangage(player.getUniqueId()).informations_description().get(3), player.getUniqueId()), Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	    				Translater.getTranslationLangage(player.getUniqueId()).informations_description().get(4), player.getUniqueId()), Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	    				Translater.getTranslationLangage(player.getUniqueId()).informations_description().get(5), player.getUniqueId()), Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	    				Translater.getTranslationLangage(player.getUniqueId()).informations_description().get(6), player.getUniqueId())}));
	    informations_meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    informations.setItemMeta(informations_meta);
		
	    return informations;
	}
	
	public ItemStack models_parameters() {
		
	    ItemStack models_parameters = new ItemStack(Material.REDSTONE_COMPARATOR, 1);
	    ItemMeta models_parameters_meta = models_parameters.getItemMeta();
	    
	    models_parameters_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).models_parameter(), player.getUniqueId()));
	    models_parameters_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).models_parameter_description(), player.getUniqueId()) }));
	    models_parameters.setItemMeta(models_parameters_meta);
	    
	    return models_parameters;
	}
	
	public ItemStack publish() {
		
	    ItemStack publish = new ItemStack(Material.DIAMOND_AXE, 1);
	    ItemMeta publish_meta = publish.getItemMeta();
	    
	    publish_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	    publish_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).model_publish(), player.getUniqueId()));
	    publish_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).model_publish_description(), player.getUniqueId()) }));
	    
	    publish.setItemMeta(publish_meta);
	    
	    return publish;
	}
	
	public ItemStack edit() {
		
	    ItemStack edit = new ItemStack(Material.GOLD_AXE, 1);
	    ItemMeta edit_meta = edit.getItemMeta();
	    
	    edit_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	    edit_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).model_edit(), player.getUniqueId()));
	    edit_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).model_edit_description(), player.getUniqueId()) }));
	    
	    edit.setItemMeta(edit_meta);
		
	    return edit;
	}
	
	public ItemStack delete() {
		
		ItemStack delete = new ItemStack(Material.FLINT_AND_STEEL, 1);
	    ItemMeta delete_meta = delete.getItemMeta();
	    
	    delete_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).model_delete(), player.getUniqueId()));
	    delete_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).model_delete_description(), player.getUniqueId()) }));
	    delete.setItemMeta(delete_meta);
	    
	    delete.setItemMeta(delete_meta);
	    
	    return delete;
	}
	
	public ItemStack load() {
		
	    ItemStack load = new ItemStack(Material.DIODE, 1);
	    ItemMeta load_meta = load.getItemMeta();
	    
	    load_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	    load_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).load_model(), player.getUniqueId()));
	    load_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).load_model_description(), player.getUniqueId()) }));
	    
	    load.setItemMeta(load_meta);
	    
	    return load;
	}
	
	public ItemStack replace() {
		
	    ItemStack replace = new ItemStack(Material.COMPASS, 1);
	    ItemMeta replace_meta = replace.getItemMeta();
	    
	    replace_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).replace_loaded(), player.getUniqueId()));
	    replace_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).replace_loaded_description(), player.getUniqueId()) }));
	    
	    replace.setItemMeta(replace_meta);
		
	    return replace;
	}
	
	public ItemStack size() {
		
	    ItemStack size = new ItemStack(Material.ARMOR_STAND, 1);
	    ItemMeta size_meta = size.getItemMeta();
	    
	    size_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).size_loaded(), player.getUniqueId()));
	    size_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).size_loaded_description(), player.getUniqueId()) }));
	    
	    size.setItemMeta(size_meta);
		
	    return size;
	}
	
	public ItemStack confirm() {
		
  	    ItemStack confirm = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
	    ItemMeta confirm_meta = confirm.getItemMeta();
	    
	    confirm_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).delete_confirm(), player.getUniqueId()));
	    confirm_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
		        Translater.getTranslationLangage(player.getUniqueId()).delete_confirm_description().get(0), player.getUniqueId()), Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).delete_confirm_description().get(1), player.getUniqueId()) }));
	    confirm.setItemMeta(confirm_meta);
	    
	    confirm.setItemMeta(confirm_meta);
	    
	    return confirm;
	}
	
	public ItemStack confirm_loaded() {
		
  	    ItemStack confirm_loaded = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
	    ItemMeta confirm_loaded_meta = confirm_loaded.getItemMeta();
	    
	    confirm_loaded_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).delete_confirm_loaded(), player.getUniqueId()));
	    confirm_loaded_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
		        Translater.getTranslationLangage(player.getUniqueId()).delete_confirm_description().get(0), player.getUniqueId()),
	    		Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).delete_confirm_description().get(1), player.getUniqueId()) }));
	    confirm_loaded.setItemMeta(confirm_loaded_meta);
	    
	    confirm_loaded.setItemMeta(confirm_loaded_meta);
	    
	    return confirm_loaded;
	}
	
	public ItemStack administration_panel() {
		
  	    ItemStack administration_panel = new ItemStack(Material.ENDER_CHEST, 1);
	    ItemMeta administration_panel_meta = administration_panel.getItemMeta();
	    
	    administration_panel_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).administration_panel(), player.getUniqueId()));
	    administration_panel_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).administration_panel_descriptions(), player.getUniqueId()) }));
	    administration_panel.setItemMeta(administration_panel_meta);
	    
	    administration_panel.setItemMeta(administration_panel_meta);
	    
	    return administration_panel;
	}
	
	public ItemStack olders_versions() {
		
  	    ItemStack olders_versions = new ItemStack(Material.CHEST, 1);
	    ItemMeta olders_versions_meta = olders_versions.getItemMeta();
	    
	    olders_versions_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).archives(), player.getUniqueId()));
	    olders_versions_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).archives_description(), player.getUniqueId()) }));
	    olders_versions.setItemMeta(olders_versions_meta);
	    
	    olders_versions.setItemMeta(olders_versions_meta);
	    
	    return olders_versions;
	}
	
	public ItemStack spigot_page() {
		
	    ItemStack spigot_page = new ItemStack(Material.MAP, 1);
	    ItemMeta spigot_page_meta = spigot_page.getItemMeta();
	    
	    spigot_page_meta.setDisplayName(ChatColor.GOLD + "✔ " + ChatColor.RESET + Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).visit_spigot() + ChatColor.GOLD + " ✔", player.getUniqueId()));
	    spigot_page_meta.setLore(Arrays.asList(new String[] { ChatColor.YELLOW + "➥ " + ChatColor.RESET + Translater.getTranslationLangage(player.getUniqueId()).getMessage(
				Translater.getTranslationLangage(player.getUniqueId()).visit_spigot_description(), player.getUniqueId()) }));
	    spigot_page_meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    
	    spigot_page.setItemMeta(spigot_page_meta);
	    
	    return spigot_page;
	}
	
	public ItemStack tools() {
		
	     ItemStack tools = new ItemStack(Material.ENDER_CHEST, 1);
	     ItemMeta tools_meta = tools.getItemMeta();
		 
		 tools_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
			Translater.getTranslationLangage(player.getUniqueId()).tools(), player.getUniqueId()));
	     tools_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
	 			Translater.getTranslationLangage(player.getUniqueId()).tools_description(), player.getUniqueId()) }));
	     tools.setItemMeta(tools_meta);
		
	     return tools;
	}
	
	public ItemStack model_parameter() {
		
		 ItemStack model_parameter = new ItemStack(Material.REDSTONE_COMPARATOR, 1);
	     ItemMeta  model_parameter_meta = model_parameter.getItemMeta();
			    
	     model_parameter_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
			Translater.getTranslationLangage(player.getUniqueId()).models_parameter(), player.getUniqueId()));
	     model_parameter_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
			Translater.getTranslationLangage(player.getUniqueId()).models_parameter_description(), player.getUniqueId()) }));
		 model_parameter.setItemMeta(model_parameter_meta);
		
	     return model_parameter;
	}
	
	public ItemStack exit_editor() {
		
		 ItemStack exit_editor = new ItemStack(Material.BARRIER, 1);
		 ItemMeta exit_editor_meta = exit_editor.getItemMeta();
		    
		 exit_editor_meta.setDisplayName(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
			Translater.getTranslationLangage(player.getUniqueId()).exit_editor(), player.getUniqueId()));
		 exit_editor_meta.setLore(Arrays.asList(new String[] { Translater.getTranslationLangage(player.getUniqueId()).getMessage(
			Translater.getTranslationLangage(player.getUniqueId()).exit_editor_description(), player.getUniqueId()) }));
		 exit_editor.setItemMeta(exit_editor_meta);
		
	     return exit_editor;
	}
}