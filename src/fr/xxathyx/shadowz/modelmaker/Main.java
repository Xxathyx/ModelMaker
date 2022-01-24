package fr.xxathyx.shadowz.modelmaker;

import java.io.IOException;
import java.net.URISyntaxException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;

import fr.xxathyx.shadowz.modelmaker.commands.ModelCommands;
import fr.xxathyx.shadowz.modelmaker.commands.TranslationCommands;
import fr.xxathyx.shadowz.modelmaker.editor.Editor;
import fr.xxathyx.shadowz.modelmaker.editor.commands.CommandEditor;
import fr.xxathyx.shadowz.modelmaker.editor.construction.edit.listeners.EditPlacedPart;
import fr.xxathyx.shadowz.modelmaker.editor.construction.edit.listeners.Move;
import fr.xxathyx.shadowz.modelmaker.editor.construction.edit.listeners.Reset;
import fr.xxathyx.shadowz.modelmaker.editor.construction.edit.listeners.Resize;
import fr.xxathyx.shadowz.modelmaker.editor.construction.edit.listeners.Rotate;
import fr.xxathyx.shadowz.modelmaker.editor.construction.listeners.Destruct;
import fr.xxathyx.shadowz.modelmaker.editor.construction.listeners.Detect;
import fr.xxathyx.shadowz.modelmaker.editor.construction.listeners.Place;
import fr.xxathyx.shadowz.modelmaker.editor.construction.manage.listeners.Save;
import fr.xxathyx.shadowz.modelmaker.editor.construction.manage.listeners.Undo;
import fr.xxathyx.shadowz.modelmaker.editor.listeners.EditorCancels;
import fr.xxathyx.shadowz.modelmaker.editor.listeners.QuitEditor;
import fr.xxathyx.shadowz.modelmaker.listeners.PlaceModel;
import fr.xxathyx.shadowz.modelmaker.map.Map;
import fr.xxathyx.shadowz.modelmaker.model.listeners.EditPlacedModel;
import fr.xxathyx.shadowz.modelmaker.Interface.listeners.InterfacesListeners;
import fr.xxathyx.shadowz.modelmaker.actionbar.ActionBarVersion;
import fr.xxathyx.shadowz.modelmaker.profil.listeners.Creation;
import fr.xxathyx.shadowz.modelmaker.server.configuration.ServerConfiguration;
import fr.xxathyx.shadowz.modelmaker.translation.Translater;
import fr.xxathyx.shadowz.modelmaker.updater.Updater;
import fr.xxathyx.shadowz.modelmaker.updater.listeners.Uptodate;
import fr.xxathyx.shadowz.modelmaker.util.ActionBar;

public class Main extends JavaPlugin {
	
    private PluginManager pluginManager;
    private ActionBar actionBar;

    private ServerConfiguration serverConfiguration;
    private Translater translater;
    private Editor editor;
    private Updater updater;
    private Map map;

    public void onEnable() {
    	    	
        serverConfiguration = new ServerConfiguration();

        if (!serverConfiguration.getServerConfigurationFile().exists()) {
            try {
                serverConfiguration.createConfiguration();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                serverConfiguration.updateConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ActionBarVersion actionBarVersion = new ActionBarVersion();
        actionBar = actionBarVersion.getActionBar();

        pluginManager = Bukkit.getServer().getPluginManager();
        map = new Map();

        getCommand("editor").setExecutor(new CommandEditor());
        getCommand("translation").setExecutor(new TranslationCommands());
        getCommand("translations").setExecutor(new TranslationCommands());
        getCommand("mk").setExecutor(new ModelCommands());
        getCommand("model").setExecutor(new ModelCommands());

        pluginManager.registerEvents(new Creation(), this);
        pluginManager.registerEvents(new EditorCancels(), this);
        pluginManager.registerEvents(new QuitEditor(), this);
        pluginManager.registerEvents(new InterfacesListeners(), this);
        pluginManager.registerEvents(new Place(), this);
        pluginManager.registerEvents(new Destruct(), this);
        pluginManager.registerEvents(new PlaceModel(), this);
        pluginManager.registerEvents(new EditPlacedModel(), this);
        pluginManager.registerEvents(new EditPlacedPart(), this);
        pluginManager.registerEvents(new Detect(), this);
        pluginManager.registerEvents(new Move(), this);
        pluginManager.registerEvents(new Resize(), this);
        pluginManager.registerEvents(new Rotate(), this);
        pluginManager.registerEvents(new Reset(), this);
        pluginManager.registerEvents(new Save(), this);
        pluginManager.registerEvents(new Undo(), this);
        pluginManager.registerEvents(new Uptodate(), this);

        translater = new Translater();

        try {
            translater.createTranslationFile("GB");
            translater.createTranslationFile("AR");
            translater.createTranslationFile("DE");
            translater.createTranslationFile("ES");
            translater.createTranslationFile("FR");
            translater.createTranslationFile("IT");
            translater.createTranslationFile("RU");
            translater.createTranslationFile("TR");
        }catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        
        map.loadMapModels();
        
        updater = new Updater();
        
	    try {
	    	
	    	if(new ServerConfiguration().getAutoUpdater()) {
				updater.update();
	    	}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
				| UnknownDependencyException | URISyntaxException | IOException | InvalidConfigurationException
				| InvalidPluginException | InvalidDescriptionException e) {
			e.printStackTrace();
		}
    }

    public void onDisable() {

        editor = new Editor();

        for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
            if (editor.isInEditor((Player) Bukkit.getOnlinePlayers().toArray()[i])) {
                editor.removeFromEditor((Player) Bukkit.getOnlinePlayers().toArray()[i]);
            }
        }
    }

    public ActionBar getActionBar() {
        return actionBar;
    }
}