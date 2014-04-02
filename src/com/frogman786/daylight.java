package com.frogman786;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class daylight extends JavaPlugin implements Listener {

	public final Logger Log = Logger.getLogger("Minecarft");
	public static daylight plugin;

	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.Log.info(pdfFile.getName() + " Has Been Disabled! ");
	}

	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		PluginDescriptionFile pdfFile = this.getDescription();
		this.Log.info(pdfFile.getName() + " " + pdfFile.getVersion() +  " Has Been Enabled! ");
	}

//	public HashMap<String, Boolean> platform = new HashMap<String, Boolean>();
	
//	public void togglePlatform(Player player) {
//	    String playerName = player.getName();
//	    if (platform.containsKey(playerName)) {
//	        if (platform.get(playerName)) {
//	        	platform.put(playerName, false);
//	            player.sendMessage("Platform Toggle off");
//	        } else {
//	            platform.put(playerName, true);
//	            player.sendMessage("Platform Toggle on");
//	        }
//	    } else {
//	        platform.put(playerName, false); //If you want plugin disabled by default change this value to false.
//	        player.sendMessage("Platform Toggle off");
//	    }
//	}
	
	public void time(Player player, String period, Integer time){
		World world = player.getWorld();
		getServer().broadcastMessage(player.getPlayerListName() + ChatColor.WHITE +" Set " + ChatColor.DARK_GREEN + world.getName() + ChatColor.WHITE + " to " + period + ".");
		world.setTime(time);
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args){
		Player player = (Player) sender;
		int period = 0;
		
		if(lbl.equalsIgnoreCase("dawn")|lbl.equalsIgnoreCase("day")|lbl.equalsIgnoreCase("noon")|lbl.equalsIgnoreCase("dusk")|lbl.equalsIgnoreCase("night")){
			if(player.hasPermission("frog.time." + lbl)){
				if(lbl.equalsIgnoreCase("dawn")){
					period = 23000;
				}else{
					if(lbl.equalsIgnoreCase("day")){
						period = 0;
					}else{
						if(lbl.equalsIgnoreCase("noon")){
							period = 7000;
						}else{
							if(lbl.equalsIgnoreCase("dusk")){
								period = 12500;
							}else{
								period = 15000;
							}
						}
					}
				}
			time(player, lbl, period);
			return true;
			}else{
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
		}

		if(lbl.equalsIgnoreCase("who")){
			if(player.hasPermission("frog.who.who")){
				player.sendMessage("there are some people online");
			return true;
			}else{
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
		}
		if(lbl.equalsIgnoreCase("gm")){
			if(player.hasPermission("frog.gamemode.self")){
				player.sendMessage("Gamemode set to" + ChatColor.DARK_GREEN + "nothing");
			return true;
			}else{
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
		}
		//NEED TO ADD REGION AND AIR CHECKING TO THIS
		if(lbl.equalsIgnoreCase("platform")){
			if(player.hasPermission("frog.platform.single") & args[0] == null){
			    Location loc = player.getPlayer().getLocation();
			    loc.setY(loc.getY() - 1);
			    Block b = loc.getBlock();
			    b.setType(Material.GLASS);
				player.sendMessage(ChatColor.DARK_GREEN + "Platform created.");
			return true;
//			}
//		    if(player.hasPermission("frog.platform.toggle") & args[0] == "toggle"){
//				togglePlatform(player);
//				return true;
			}else{
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
		}
		return false; 
	
	}
    
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt) {
	    Player player = evt.getPlayer();
	    if(player.hasPermission("nametags.color.dark_blue")){
	    	evt.setJoinMessage(ChatColor.DARK_BLUE + player.getDisplayName() + ChatColor.WHITE + " logged in, making " + ChatColor.RED + Bukkit.getOnlinePlayers().length + ChatColor.GREEN + " players");
	    }else{
	    	if(player.hasPermission("nametags.color.dark_green")){
		    	evt.setJoinMessage(ChatColor.DARK_GREEN + player.getDisplayName() + ChatColor.WHITE + " logged in, making " + ChatColor.RED + Bukkit.getOnlinePlayers().length + ChatColor.GREEN + " players");
		    }else{
		    	if(player.hasPermission("nametags.color.gold")){
			    	evt.setJoinMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.WHITE + " logged in, making " + ChatColor.RED + Bukkit.getOnlinePlayers().length + ChatColor.GREEN + " players");
			    }else{
			    	if(player.hasPermission("nametags.color.blue")){
				    	evt.setJoinMessage(ChatColor.BLUE + player.getDisplayName() + ChatColor.WHITE + " logged in, making " + ChatColor.RED + Bukkit.getOnlinePlayers().length + ChatColor.GREEN + " players");
				    }else{
				    	if(player.hasPermission("nametags.color.green")){
					    	evt.setJoinMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.WHITE + " logged in, making " + ChatColor.RED + Bukkit.getOnlinePlayers().length + ChatColor.GREEN + " players");
					    }else{
					    	if(player.hasPermission("nametags.color.aqua")){
						    	evt.setJoinMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.WHITE + " logged in, making " + ChatColor.RED + Bukkit.getOnlinePlayers().length + ChatColor.GREEN + " players");
						    }else{
						    	if(player.hasPermission("nametags.color.red")){
							    	evt.setJoinMessage(ChatColor.RED + player.getDisplayName() + ChatColor.WHITE + " logged in, making " + ChatColor.RED + Bukkit.getOnlinePlayers().length + ChatColor.GREEN + " players");
							    }else{
							    	if(player.hasPermission("nametags.color.light_purple")){
								    	evt.setJoinMessage(ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.WHITE + " logged in, making " + ChatColor.RED + Bukkit.getOnlinePlayers().length + ChatColor.GREEN + " players");
								    }else{
									    evt.setJoinMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + " logged in, making " + ChatColor.RED + Bukkit.getOnlinePlayers().length + ChatColor.GREEN + " players");
									}
							    }
						    }
					    }
				    }
			    }
		    }
		}
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent evt) {
	    Player player = evt.getPlayer();
	    evt.setQuitMessage(player.getPlayerListName() + ChatColor.WHITE + " quit " + ChatColor.RED + (Bukkit.getOnlinePlayers().length - 1) + ChatColor.GREEN + " players" + ChatColor.WHITE + " left");
	}
//	@EventHandler
//	public void onPlayerMove(PlayerMoveEvent evt) {
//	    Player player = (Player) evt.getPlayer();
//	    if(platform.containsKey(player) & platform.get(player)){
//	    	Location loc = evt.getPlayer().getLocation();
//	    	loc.setY(loc.getY() - 1);
//	    	Block b = loc.getBlock();
//	    	b.setType(Material.GLASS);
//		}
//	}
}