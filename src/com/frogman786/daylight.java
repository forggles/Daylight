package com.frogman786;

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
import org.bukkit.event.player.PlayerQuitEvent;
import net.milkbowl.vault.permission.Permission;

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
			if(player.hasPermission("frog.platform.single")){
			    Location loc = player.getPlayer().getLocation();
			    loc.setY(loc.getY() - 1);
			    Block b = loc.getBlock();
			    b.setType(Material.GLASS);
				player.sendMessage(ChatColor.DARK_GREEN + "Platform created.");
			return true;
			}else{
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
		}
		if(lbl.equalsIgnoreCase("promo")){
			if(player.hasPermission("frog.promo.*")){
				String promoto = Bukkit.getServer().getPlayer(args[1]).getName();
				String rank = args[0];
				if(player.hasPermission("frog.promo.secret") & args[2] == "secret"){
					getServer().broadcast(ChatColor.GRAY + "[SECRET PROMO]" + promoto + ChatColor.WHITE + " has been promoted to " + rank , "frog.promo.secret");
				}else{
					getServer().broadcastMessage(promoto + ChatColor.WHITE + " has been promoted to " + rank);
				}
			}
		}
		return false; 
	
	}
    
	@EventHandler //Tidy later, it's better now anyway
	public void onPlayerJoin(PlayerJoinEvent evt) {
	    Player player = evt.getPlayer();
	    Enum<ChatColor> colour = ChatColor.YELLOW;
	    String plural = " players";
	    if(Bukkit.getOnlinePlayers().length == 1){
	    	plural = " player";
	    }
	    if(player.hasPermission("nametags.color.dark_blue")){
	    	colour = ChatColor.DARK_BLUE;
	    }else{
	    	if(player.hasPermission("nametags.color.dark_green")){
		    	colour = ChatColor.DARK_GREEN;
		    }else{
		    	if(player.hasPermission("nametags.color.gold")){
		    		colour = ChatColor.GOLD;
			    }else{
			    	if(player.hasPermission("nametags.color.blue")){
			    		colour = ChatColor.BLUE;
				    }else{
				    	if(player.hasPermission("nametags.color.green")){
				    		colour = ChatColor.GREEN;
					    }else{
					    	if(player.hasPermission("nametags.color.aqua")){
					    		colour = ChatColor.AQUA;
						    }else{
						    	if(player.hasPermission("nametags.color.red")){
						    		colour = ChatColor.RED;
							    }else{
							    	if(player.hasPermission("nametags.color.light_purple")){
							    		colour = ChatColor.LIGHT_PURPLE;
								    }
							    }
						    }
					    }
				    }
			    }
		    }
		}
	    evt.setJoinMessage(colour + player.getDisplayName() + ChatColor.WHITE + " logged in, making " + ChatColor.RED + Bukkit.getOnlinePlayers().length + ChatColor.GREEN + plural);
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent evt) {
	    Player player = evt.getPlayer();
	    Enum<ChatColor> colour = ChatColor.YELLOW;
	    String plural = " players";
	    if(Bukkit.getOnlinePlayers().length-1 == 1){
	    	plural = " player";
	    }
	    if(player.hasPermission("nametags.color.dark_blue")){
	    	colour = ChatColor.DARK_BLUE;
	    }else{
	    	if(player.hasPermission("nametags.color.dark_green")){
		    	colour = ChatColor.DARK_GREEN;
		    }else{
		    	if(player.hasPermission("nametags.color.gold")){
		    		colour = ChatColor.GOLD;
			    }else{
			    	if(player.hasPermission("nametags.color.blue")){
			    		colour = ChatColor.BLUE;
				    }else{
				    	if(player.hasPermission("nametags.color.green")){
				    		colour = ChatColor.GREEN;
					    }else{
					    	if(player.hasPermission("nametags.color.aqua")){
					    		colour = ChatColor.AQUA;
						    }else{
						    	if(player.hasPermission("nametags.color.red")){
						    		colour = ChatColor.RED;
							    }else{
							    	if(player.hasPermission("nametags.color.light_purple")){
							    		colour = ChatColor.LIGHT_PURPLE;
								    }
							    }
						    }
					    }
				    }
			    }
		    }
		}
	    evt.setQuitMessage(colour + player.getPlayerListName() + ChatColor.WHITE + " quit " + ChatColor.RED + (Bukkit.getOnlinePlayers().length - 1) + ChatColor.GREEN + plural + ChatColor.WHITE + " left");
	}

}