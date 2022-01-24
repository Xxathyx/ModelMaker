package fr.xxathyx.shadowz.modelmaker.Interface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.xxathyx.shadowz.modelmaker.Interface.interfaces.Interfaces;

public class Interface {
		
	private Interface Interface;
	private String interfaceName;
	
	private Inventory inventory;
	
	private int interfaceSize;
	
	public Interface getInterface() {
		return Interface;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void setName(String name) {
		this.interfaceName = name;
	}
	
	public String getName() {
		return interfaceName;
	}
	
	public void setSize(int size) {
		this.interfaceSize = size;
		inventory = Bukkit.createInventory(null, size, interfaceName);
	}
	
	public int getSize() {
		return interfaceSize;
	}
	
	public void addItem(int slot, ItemStack item) {
		inventory.setItem(slot, item);
	}
	
	@SuppressWarnings("deprecation")
	public void removeItem(int slot) {
		inventory.remove(slot);
	}
	
	public void getItem(int slot) {
		inventory.getItem(slot);
	}
	
	public void sortInventoryInterface(Inventory inventory) {
		
		int air_count = 0;
		
		for(int i = 0; i < inventory.getSize(); i++) {
			if(inventory.getItem(i) == null) {
				air_count++;
			}
		}
		
		ItemStack[] items = new ItemStack[air_count];
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		
		for(int i = 0; i < items.length; i++) {
			if(inventory.getItem(i) != null) {
				list.add(inventory.getItem(i));
			}
		}
		
		inventory.clear();
		
		for(int i = 0; i < list.size(); i++) {
			inventory.setItem(i, list.get(i));
		}
	}
	
	public void showInterface(Player player) {
		player.openInventory(inventory);
	}
	
	public static boolean isItemStackNull(InventoryClickEvent event) {
		
        if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            return true;
        }
        if (event.getCurrentItem().equals(null)) {
            return true;
        }
        if (!event.getCurrentItem().hasItemMeta()) {
            return true;
        }
        if (!event.getCurrentItem().getItemMeta().hasDisplayName()) {
            return true;
        }
        if (event.getCurrentItem().getType().equals(Material.AIR)) {
            return true;
        }
		return false;
	}
	
	public static Interface getCurrentInterface(Player player) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		Interfaces interfaces = new Interfaces();
		
		String interfaceName = player.getOpenInventory().getTitle();
		
		for(int i = 0; i < interfaces.getInterfaces(player).size(); i++) {
			if(interfaces.getInterfaces(player).get(i).getName().equals(interfaceName)) {
				return interfaces.getInterfaces(player).get(i);
			}
		}
		return null;
	}
}