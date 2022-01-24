package fr.xxathyx.shadowz.modelmaker.actionbar;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.xxathyx.shadowz.modelmaker.util.ActionBar;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;

public class v1_8_R1 implements ActionBar {
	
	@Override
	public void send(Player player, String text) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(ChatSerializer.a("{\"text\": \"" + text + "\"}"), (byte) 2));
	}
}