package fr.xxathyx.shadowz.modelmaker.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.profil.Profil;
import fr.xxathyx.shadowz.modelmaker.translation.CustomTranslation;

public class Flags {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	public ItemStack French(Player player) {
		
		ItemStack i = new ItemStack(Material.BANNER, 1);
	    BannerMeta iM = (BannerMeta) i.getItemMeta();

	    iM.setBaseColor(DyeColor.WHITE);

	    List<Pattern> patterns = new ArrayList<Pattern>();

	    patterns.add(new Pattern(DyeColor.BLUE, PatternType.STRIPE_LEFT));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_RIGHT));
	    
	    iM.setPatterns(patterns);
	    iM.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Français");
	    iM.setLore(Arrays.asList(new String[] { ChatColor.AQUA + "Cliquez ici pour changer la langue." }));
	    iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    
	    i.setItemMeta(iM);
	    
		return i;
	}
	
    public ItemStack Deutsch(Player player) {
		
		ItemStack i = new ItemStack(Material.BANNER, 1);
	    BannerMeta iM = (BannerMeta) i.getItemMeta();

	    iM.setBaseColor(DyeColor.WHITE);

	    List<Pattern> patterns = new ArrayList<Pattern>();

	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
	    patterns.add(new Pattern(DyeColor.YELLOW, PatternType.STRIPE_RIGHT));
	    patterns.add(new Pattern(DyeColor.YELLOW, PatternType.STRIPE_RIGHT));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_CENTER));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_CENTER));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_CENTER));
	    
	    iM.setPatterns(patterns);
	    iM.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Deutsch");
	    iM.setLore(Arrays.asList(new String[] { ChatColor.AQUA + "Klicken Sie hier, um die Sprache zu ändern" }));
	    iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    
	    i.setItemMeta(iM);
	    
		return i;
	}
    
    public ItemStack Arabian(Player player) {
		
		ItemStack i = new ItemStack(Material.BANNER, 1);
	    BannerMeta iM = (BannerMeta) i.getItemMeta();

	    iM.setBaseColor(DyeColor.WHITE);

	    List<Pattern> patterns = new ArrayList<Pattern>();

	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_LEFT));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_RIGHT));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_RIGHT));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_RIGHT));
	    
	    iM.setPatterns(patterns);
	    iM.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "عربي");
	    iM.setLore(Arrays.asList(new String[] { ChatColor.AQUA + "انقر هنا لتغيير اللغة" }));
	    iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    
	    i.setItemMeta(iM);
	    
		return i;
	}
    
    public ItemStack Russian(Player player) {
		
		ItemStack i = new ItemStack(Material.BANNER, 1);
	    BannerMeta iM = (BannerMeta) i.getItemMeta();

	    iM.setBaseColor(DyeColor.WHITE);

	    List<Pattern> patterns = new ArrayList<Pattern>();

	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_LEFT));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_LEFT));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_LEFT));
	    patterns.add(new Pattern(DyeColor.BLUE, PatternType.STRIPE_CENTER));
	    patterns.add(new Pattern(DyeColor.BLUE, PatternType.STRIPE_CENTER));
	    patterns.add(new Pattern(DyeColor.BLUE, PatternType.STRIPE_CENTER));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_RIGHT));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_RIGHT));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_RIGHT));

	    
	    iM.setPatterns(patterns);
	    iM.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Pусский");
	    iM.setLore(Arrays.asList(new String[] { ChatColor.AQUA + "Нажмите здесь, чтобы изменить язык" }));
	    iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    
	    i.setItemMeta(iM);
	    
		return i;
	}
    
    public ItemStack Spanish(Player player) {
		
		ItemStack i = new ItemStack(Material.BANNER, 1);
	    BannerMeta iM = (BannerMeta) i.getItemMeta();

	    iM.setBaseColor(DyeColor.ORANGE);

	    List<Pattern> patterns = new ArrayList<Pattern>();

	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_LEFT));
	    patterns.add(new Pattern(DyeColor.BROWN, PatternType.MOJANG));
	    patterns.add(new Pattern(DyeColor.BROWN, PatternType.FLOWER));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_LEFT));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_RIGHT));
	    
	    iM.setPatterns(patterns);
	    iM.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Espanol");
	    iM.setLore(Arrays.asList(new String[] { ChatColor.AQUA + "Haga clic aquí para cambiar el idioma." }));
	    iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    
	    i.setItemMeta(iM);
	    
		return i;
	}
    
    public ItemStack Italian(Player player) {
		
		ItemStack i = new ItemStack(Material.BANNER, 1);
	    BannerMeta iM = (BannerMeta) i.getItemMeta();

	    iM.setBaseColor(DyeColor.WHITE);

	    List<Pattern> patterns = new ArrayList<Pattern>();

	    patterns.add(new Pattern(DyeColor.GREEN, PatternType.STRIPE_TOP));
	    patterns.add(new Pattern(DyeColor.GREEN, PatternType.STRIPE_TOP));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_BOTTOM));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_BOTTOM));
	    
	    iM.setPatterns(patterns);
	    iM.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Italiano");
	    iM.setLore(Arrays.asList(new String[] { ChatColor.AQUA + "Clicca qui per cambiare la lingua" }));
	    iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    
	    i.setItemMeta(iM);
	    
		return i;
	}
    
    public ItemStack English(Player player) {
		
		ItemStack i = new ItemStack(Material.BANNER, 1);
	    BannerMeta iM = (BannerMeta) i.getItemMeta();

	    iM.setBaseColor(DyeColor.BLUE);

	    List<Pattern> patterns = new ArrayList<Pattern>();
	    
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_MIDDLE));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_CENTER));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_DOWNLEFT));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_DOWNRIGHT));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_DOWNRIGHT));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_DOWNLEFT));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_CENTER));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_CENTER));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_MIDDLE));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_MIDDLE));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRAIGHT_CROSS));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.CROSS));
	    
	    iM.setPatterns(patterns);
	    iM.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "English");
	    iM.setLore(Arrays.asList(new String[] { ChatColor.AQUA + "Click here to change the language" }));
	    iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    
	    i.setItemMeta(iM);
	    
		return i;
	}
    
    public ItemStack Turkish(Player player) {
		
		ItemStack i = new ItemStack(Material.BANNER, 1);
	    BannerMeta iM = (BannerMeta) i.getItemMeta();

	    iM.setBaseColor(DyeColor.RED);

	    List<Pattern> patterns = new ArrayList<Pattern>();
	    
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.HALF_VERTICAL));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_MIDDLE));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.CURLY_BORDER));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_TOP));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_BOTTOM));
	    patterns.add(new Pattern(DyeColor.RED, PatternType.CIRCLE_MIDDLE));
	    
	    iM.setPatterns(patterns);
	    iM.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Türk");
	    iM.setLore(Arrays.asList(new String[] { ChatColor.AQUA + "Dili degistirmek icin burayi tiklayin" }));
	    iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    
	    i.setItemMeta(iM);
	    
		return i;
	}
    
    public ItemStack Unknown(Player player) {
		
		File profilfile = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId() + ".yml");
		
		Profil profil = new Profil(profilfile);
    	
		File translationFile = new File(plugin.getDataFolder() + "/translations/", profil.getLangage() + ".yml");
		CustomTranslation ct = new CustomTranslation(translationFile);
		
		ItemStack i = new ItemStack(Material.BANNER, 1);
	    BannerMeta iM = (BannerMeta)i.getItemMeta();

	    iM.setBaseColor(DyeColor.BLACK);

	    List<Pattern> patterns = new ArrayList<Pattern>();
	    
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_TOP));
	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.RHOMBUS_MIDDLE));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_DOWNLEFT));
	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.HALF_HORIZONTAL_MIRROR));
	    patterns.add(new Pattern(DyeColor.WHITE, PatternType.TRIANGLE_BOTTOM));
	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_MIDDLE));
	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.STRIPE_BOTTOM));
	    patterns.add(new Pattern(DyeColor.BLACK, PatternType.BORDER));
	    
	    iM.setPatterns(patterns);
	    iM.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + ct.getName());
	    iM.setLore(Arrays.asList(new String[] { ChatColor.AQUA + ct.getDescription() }));
	    iM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    
	    i.setItemMeta(iM);
	    
		return i;
	}
}