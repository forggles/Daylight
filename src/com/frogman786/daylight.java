package com.frogman786;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class daylight extends JavaPlugin implements Listener {

	public final Logger Log = Logger.getLogger("Minecarft");
	public static daylight plugin;

	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.Log.info(pdfFile.getName() + " Has Been Disabled! ");
	}

	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.Log.info(pdfFile.getName() + pdfFile.getVersion() +  " Has Been Enabled! ");
	}

	
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args){
		Player player = (Player) sender;
		World world = player.getWorld();
		if(lbl.equalsIgnoreCase("dawn")){
			player.sendMessage(player.getPlayerListName() + ChatColor.WHITE +" Set " + ChatColor.DARK_GREEN + world + ChatColor.WHITE + "to dawn.");
			world.setTime(23000);
			return true;
		}
		if(lbl.equalsIgnoreCase("day")){
			player.sendMessage(player.getPlayerListName() + ChatColor.WHITE +" Set " + ChatColor.DARK_GREEN + world + ChatColor.WHITE + "to day.");
			world.setTime(0);
			return true;
		}
		if(lbl.equalsIgnoreCase("noon")){
			player.sendMessage(player.getPlayerListName() + ChatColor.WHITE +" Set " + ChatColor.DARK_GREEN + world + ChatColor.WHITE + "to noon.");
			world.setTime(7000);
			return true;
		}
		if(lbl.equalsIgnoreCase("dusk")){
			player.sendMessage(player.getPlayerListName() + ChatColor.WHITE +" Set " + ChatColor.DARK_GREEN + world + ChatColor.WHITE + "to dusk.");
			world.setTime(12500);
			return true;
		}
		if(lbl.equalsIgnoreCase("night")){
			player.sendMessage(player.getPlayerListName() + ChatColor.WHITE +" Set " + ChatColor.DARK_GREEN + world + ChatColor.WHITE + "to night.");
			world.setTime(15000);
			return true;
		}
		return false; 
	
	}

}