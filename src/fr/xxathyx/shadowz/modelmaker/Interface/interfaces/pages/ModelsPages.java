package fr.xxathyx.shadowz.modelmaker.Interface.interfaces.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.xxathyx.shadowz.modelmaker.Main;
import fr.xxathyx.shadowz.modelmaker.Interface.Interface;
import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.Interfaces;
import fr.xxathyx.shadowz.modelmaker.model.Model;
import fr.xxathyx.shadowz.modelmaker.profil.Profil;

public class ModelsPages {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private ArrayList<Interface> interfaces = new ArrayList<Interface>();
	private ArrayList<List<Model>> packets = new ArrayList<List<Model>>();
	
	private List<Model> models = new ArrayList<Model>();
	
	private File playerProfilFile;
	private Profil playerProfil;
	
	private Player player;
	
	private Interfaces template = new Interfaces();
	
	private int pages;
	private int current = 0;
	
	private Model selected;
	
	public Interface getPage(int i) {
		return interfaces.get(i);
	}
	
	public void setSelected(Model model) {
		this.selected = model;
	}
	
	public Model getSelectedModel() {
		return selected;
	}
	
	public void previous() {
		current = current - 1;
	}
	
	public void actualize() {
		
	}
	
	public void next() {
		current = current + 1;
	}
	
	public int getCurrent() {
		return current;
	}
	
	public void pagesCreation() {
		
		  File modelsFolder = new File(plugin.getDataFolder() + "/data/" + player.getUniqueId() + "/models/");
		  
		  File[] listOfFiles = modelsFolder.listFiles();
		  
			for(int i = 0; i < listOfFiles.length; i++) {

				Model model = new Model(new File(listOfFiles[i].getPath() + "/" + listOfFiles[i].getName() + ".yml"));
				
				models.add(model);
			}
			
		  for(int i = 0; i < pages; i++) {
			  Interface Interface = template.getModelsInterface(player);
			  interfaces.add(i, Interface);
		  }
		  
			for(int i = 0, j = 1; i < interfaces.size(); i++, j++) {
				
				if(i == 0 && j == 1) {
					
					if(models.size() < 45) {
						List<Model> packetsM = models.subList(0, models.size());
						packets.add(packetsM);
					}else {
						List<Model> packetsM = models.subList(0, 45);
						packets.add(packetsM);
					}
				}
				
				if(i != 0 && j != 1 && j != interfaces.size()) {
					List<Model> packetsM = models.subList(45 * i, 45 * j);
					packets.add(packetsM);
				}
				
				if(i == interfaces.size()-1) {
					List<Model> packetsM = models.subList(45 * i, models.size());
					packets.add(packetsM);
				}
			}
			
		  for(int i = 0; i < interfaces.size(); i++) {
			  
			  for(int j = 0, slot = 0; slot < packets.get(i).size(); j++, slot++) {
				  interfaces.get(i).addItem(slot, packets.get(i).get(j).getIcon(packets.get(i).get(j).getParts()));
			  }
		  }
	}
	
	public void init(Player player) {
		this.setPlayer(player);
		this.countPages();
		this.pagesCreation();
	}
	
	public void countPages() {
		
		playerProfilFile = new File(plugin.getDataFolder() + "/profils/", player.getUniqueId() + ".yml");
		
		playerProfil = new Profil(playerProfilFile);
		
		pages = (playerProfil.getModelsCount() / 45) + 1;
	}
	
	public ArrayList<Interface> getInterfaces() {
		return interfaces;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
}