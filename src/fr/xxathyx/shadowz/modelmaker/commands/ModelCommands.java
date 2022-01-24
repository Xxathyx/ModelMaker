package fr.xxathyx.shadowz.modelmaker.commands;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.CacheInterfaces;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.Interfaces;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.pages.ModelsPages;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.pages.ModelsProgressPages;
import fr.xxathyx.shadowz.modelmaker.editor.CacheEditor;
import fr.xxathyx.shadowz.modelmaker.editor.Editor;
import fr.xxathyx.shadowz.modelmaker.model.Model;
import fr.xxathyx.shadowz.modelmaker.profil.Profil;
import fr.xxathyx.shadowz.modelmaker.sound.SoundPlayer;
import fr.xxathyx.shadowz.modelmaker.sound.SoundType;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ModelCommands implements CommandExecutor {
	
    private final Main plugin = Main.getPlugin(Main.class);

    private final SoundPlayer soundPlayer = new SoundPlayer();
    private final Interfaces interfaces = new Interfaces();

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg3) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Translater.getServerLangage().getMessage(Translater.getServerLangage().invalid_sender()));
            return false;
        }

        Player player = (Player) sender;

        Profil playerProfil = new Profil(new File(plugin.getDataFolder() + "/profils/", player.getUniqueId().toString() + ".yml"));
        
        if (cmd.getName().equalsIgnoreCase("mk")) {
            if (arg3.length == 1) {
                if (arg3[0].equalsIgnoreCase("menu")) {

                    interfaces.menu(player);

                    if (!CacheInterfaces.interfaces_progress.containsKey(player) && playerProfil.getModelsProgressCount() > 0) {

                        ModelsProgressPages mpp = new ModelsProgressPages();
                        mpp.init(player);

                        CacheInterfaces.interfaces_progress.put(player, mpp);
                    }
                    if (CacheInterfaces.interfaces_progress.containsKey(player)) {

                        ModelsProgressPages mpp = new ModelsProgressPages();
                        mpp.init(player);

                        CacheInterfaces.interfaces_progress.replace(player, CacheInterfaces.interfaces_progress.get(player), mpp);
                    }
                    if (!CacheInterfaces.interfaces_models.containsKey(player) && playerProfil.getModelsCount() > 0) {

                        ModelsPages mp = new ModelsPages();
                        mp.init(player);

                        CacheInterfaces.interfaces_models.put(player, mp);
                    }
                    if (CacheInterfaces.interfaces_models.containsKey(player)) {

                        ModelsPages mp = new ModelsPages();
                        mp.init(player);

                        CacheInterfaces.interfaces_models.replace(player, CacheInterfaces.interfaces_models.get(player), mp);
                    }
                }

                if (arg3[0].equalsIgnoreCase("help")) {
                    //Interface d'aide
                }
            }

            if (arg3.length == 0) {
                player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                    Translater.getTranslationLangage(player.getUniqueId()).invalid_commands_args(), player.getUniqueId()));
                return false;
            }
        }

        if (cmd.getName().equalsIgnoreCase("model")) {
            if (arg3.length == 2) {
                if (arg3[0].equalsIgnoreCase("create")) {
                	
                    if (arg3[1].length() > 16) {
                        soundPlayer.playSound(player, SoundType.NOTIFICATION_DOWN, false);
                        plugin.getActionBar().send(player, Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                            Translater.getTranslationLangage(player.getUniqueId()).model_long_name(), player.getUniqueId()));
                        return false;
                    }

                    File modelProgressFile = new File(plugin.getDataFolder() + "/data/" + player.getUniqueId() + "/models progress/" + arg3[1] + "/", arg3[1] + ".yml");
                    File modelFile = new File(plugin.getDataFolder() + "/data/" + player.getUniqueId() + "/models/" + arg3[1] + "/", arg3[1] + ".yml");

                    if (!modelProgressFile.exists() && !modelFile.exists()) {

                        Model selectedModel = new Model();

                        Date date = new Date();
                        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

                        try {
                        	selectedModel.createPartsFolder(arg3[1].toString(), player.getUniqueId());
                        	selectedModel.createModelFile(arg3[1].toString(), player.getName(), player.getUniqueId(), shortDateFormat.format(date));
                        }catch (IOException e) {
                            e.printStackTrace();
                        }

                        selectedModel = new Model(modelProgressFile);

                        if (CacheInterfaces.selected_model.containsKey(player)) {
                            CacheInterfaces.selected_model.replace(player, selectedModel);
                        }else {
                            CacheInterfaces.selected_model.put(player, selectedModel);
                        }

                        soundPlayer.playSound(player, SoundType.NOTIFICATION_UP, false);
                        plugin.getActionBar().send(player, Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                            Translater.getTranslationLangage(player.getUniqueId()).model_created(), player.getUniqueId()));
                        return true;
                    }else {

                        Model selectedModel = new Model();

                        if (modelProgressFile.exists()) {
                        	selectedModel = new Model(modelProgressFile);
                        }

                        if (modelFile.exists()) {
                        	selectedModel = new Model(modelFile);
                        }

                        if (CacheInterfaces.selected_model.containsKey(player)) {
                            CacheInterfaces.selected_model.replace(player, selectedModel);
                        }else {
                            CacheInterfaces.selected_model.put(player, selectedModel);
                        }

                        plugin.getActionBar().send(player, Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                            Translater.getTranslationLangage(player.getUniqueId()).model_exist(), player.getUniqueId()));

                        soundPlayer.playSound(player, SoundType.NOTIFICATION_DOWN, false);
                        return false;
                    }
                }

                if (arg3[0].equalsIgnoreCase("edit")) {

                    Editor editor = new Editor(player);

                    if (editor.isInEditor(player)) {

                        File modelFile = new File(plugin.getDataFolder() + "/data/" + player.getUniqueId() + "/models progress/" + arg3[1] + "/", arg3[1] + ".yml");

                        if (!modelFile.exists()) {

                            player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                                Translater.getTranslationLangage(player.getUniqueId()).model_not_found(), player.getUniqueId()));
                            player.sendMessage(" ");

                            TextComponent request = new TextComponent(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                                Translater.getTranslationLangage(player.getUniqueId()).model_creation_request(), player.getUniqueId()) + arg3[1] + " ?" + "\n");

                            TextComponent accept = new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "                      [" + Translater.getTranslationLangage(player.getUniqueId()).getMessage(Translater.getTranslationLangage(player.getUniqueId()).yes_question(), player.getUniqueId()) + "]");
                            accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "" + ChatColor.BOLD + "-> /model create " + arg3[1]).create()));
                            TextComponent blank1 = new TextComponent("\n");
                            TextComponent blank2 = new TextComponent("                ");

                            request.addExtra(blank1);
                            request.addExtra(accept);
                            request.addExtra(blank2);

                            accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/model create " + arg3[1]));

                            player.spigot().sendMessage(request);
                            return true;
                        }

                        Model selectedModel = new Model(modelFile);

                        if (CacheEditor.editors.get(player) != null) {

                            Editor oldEditor = CacheEditor.editors.get(player);

                            Editor playerEditor = new Editor(player);

                            CacheEditor.editors.remove(player, oldEditor);
                            CacheEditor.editors.put(player, playerEditor);
                        }

                        Editor playerEditor = CacheEditor.editors.get(player);

                        playerEditor.editModel(player, selectedModel);
                        
                        try {
                            playerEditor.loadModel(player.getLocation());
                        } catch (IOException | InvalidConfigurationException e) {
                            e.printStackTrace();
                        }
                        return true;
                    } else {
                        player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                            Translater.getTranslationLangage(player.getUniqueId()).not_in_editor(), player.getUniqueId()));
                        return false;
                    }
                }

                if (arg3[0].equalsIgnoreCase("delete")) {

                    String name = arg3[1];

                    File modelFile = new File(plugin.getDataFolder() + "/data/" + player.getUniqueId() + "/models/" + name + "/", name + ".yml");
                    File modelProgressFile = new File(plugin.getDataFolder() + "/data/" + player.getUniqueId() + "/models progress/" + name + "/", name + ".yml");

                    if (modelFile.exists()) {

                        Model model = new Model(modelFile);

                        interfaces.confirmation_model_supression(model, player);

                        if (CacheInterfaces.selected_model.containsKey(player)) {
                            CacheInterfaces.selected_model.replace(player, model);
                        } else {
                            CacheInterfaces.selected_model.put(player, model);
                        }
                        interfaces.confirmation_model_supression(model, player);
                        return true;
                    }

                    if (modelProgressFile.exists()) {

                        Model selectedModel = new Model(modelProgressFile);

                        if (CacheInterfaces.selected_model.containsKey(player)) {
                            CacheInterfaces.selected_model.replace(player, selectedModel);
                        } else {
                            CacheInterfaces.selected_model.put(player, selectedModel);
                        }

                        interfaces.confirmation_model_supression(selectedModel, player);
                        return true;
                    }
                    player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                            Translater.getTranslationLangage(player.getUniqueId()).model_not_found(), player.getUniqueId()));
                }
                return false;
            } else {
                player.sendMessage(Translater.getTranslationLangage(player.getUniqueId()).getMessage(
                    Translater.getTranslationLangage(player.getUniqueId()).invalid_commands_args(), player.getUniqueId()));
                return false;
            }
        }
        return false;
    }
}