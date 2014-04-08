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
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
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
	
	public void togglegm(Player p){
		if (p.getGameMode() == org.bukkit.GameMode.CREATIVE) {
            p.setGameMode(org.bukkit.GameMode.SURVIVAL);
            p.sendMessage("Gamemode set to" + ChatColor.DARK_GREEN + " survival");
        } else {
            p.setGameMode(org.bukkit.GameMode.CREATIVE);
			p.sendMessage("Gamemode set to" + ChatColor.DARK_GREEN + " creative");
        }
	}

	public String formatMessage(String message){
		char ColourSymbol = '\u00A7';
		message = message.replaceAll("&0", ColourSymbol + "0");
		message = message.replaceAll("&1", ColourSymbol + "1");
		message = message.replaceAll("&2", ColourSymbol + "2");
		message = message.replaceAll("&3", ColourSymbol + "3");
		message = message.replaceAll("&4", ColourSymbol + "4");
		message = message.replaceAll("&5", ColourSymbol + "5");
    	message = message.replaceAll("&6", ColourSymbol + "6");
    	message = message.replaceAll("&7", ColourSymbol + "7");
    	message = message.replaceAll("&8", ColourSymbol + "8");
    	message = message.replaceAll("&9", ColourSymbol + "9");
    	message = message.replaceAll("&a", ColourSymbol + "a");
    	message = message.replaceAll("&b", ColourSymbol + "b");
    	message = message.replaceAll("&c", ColourSymbol + "c");
    	message = message.replaceAll("&d", ColourSymbol + "d");
    	message = message.replaceAll("&e", ColourSymbol + "e");
    	message = message.replaceAll("&f", ColourSymbol + "f");
    	message = message.replaceAll("&l", ColourSymbol + "l");
    	message = message.replaceAll("&m", ColourSymbol + "m");
    	message = message.replaceAll("&n", ColourSymbol + "n");
    	message = message.replaceAll("&o", ColourSymbol + "o");
    	message = message.replaceAll("&k", ColourSymbol + "k");
    	return message;
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args){
		Player player = (Player) sender;
		if(lbl.equalsIgnoreCase("dawn")|lbl.equalsIgnoreCase("day")|lbl.equalsIgnoreCase("noon")|lbl.equalsIgnoreCase("dusk")|lbl.equalsIgnoreCase("night")){
			int period = 0;
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
			if(args.length == 0 && player.hasPermission("frog.who.who")){
				StringBuilder online = new StringBuilder();
				Player[] players = Bukkit.getOnlinePlayers();
				for(Player listplayer : players){
					// If a player is hidden from the sender don't show them in the list
					if(sender instanceof Player && !((Player) sender).canSee(listplayer))
						continue;				              
						if(online.length() > 0){
							online.append(", ");
						}	 
						online.append(listplayer.getDisplayName());
					}
					sender.sendMessage(ChatColor.GREEN +"Online players " + ChatColor.RED + players.length + ChatColor.WHITE + " : " + online.toString());
					return true;
				}else{
					if(args.length == 1){
						if(args[0].equalsIgnoreCase("info") && player.hasPermission("frog.who.info")){
							StringBuilder infoonline = new StringBuilder();
							Player[] players = Bukkit.getOnlinePlayers();
							for(Player listplayer : players){
								// If a player is hidden from the sender don't show them in the list
								if(sender instanceof Player && !((Player) sender).canSee(listplayer))
									continue;				              
									if(infoonline.length() > 0){
										infoonline.append("\n");
									} 
									int x = (int) listplayer.getLocation().getX();
									int y = (int) listplayer.getLocation().getY();
									int z = (int) listplayer.getLocation().getZ();
									infoonline.append(listplayer.getDisplayName() + ChatColor.WHITE + ", world: " + listplayer.getWorld().getName() + ", Location: " + x+","+y+","+z);
									
								}
								sender.sendMessage(ChatColor.GREEN +"Online players " + ChatColor.RED + players.length + ChatColor.WHITE + " :\n" + infoonline.toString());
								return true;
						}else{
							if(args[0].equalsIgnoreCase("*") && player.hasPermission("frog.who.world")){
								StringBuilder online = new StringBuilder();
								Player[] players = Bukkit.getOnlinePlayers();
								for(Player listplayer : players){
									// If a player is hidden from the sender don't show them in the list
									if(sender instanceof Player && !((Player) sender).canSee(listplayer))
										continue;				              
										if(online.length() > 0){
											online.append(", ");
										}	 
											online.append(listplayer.getDisplayName() +ChatColor.WHITE+"["+listplayer.getWorld().getName()+"]");
										}
										sender.sendMessage(ChatColor.GREEN +"Online players " + ChatColor.RED + players.length + ChatColor.WHITE + " : " + online.toString());
										return true;
							}else{
								player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
								return true;
						}
					}
				}
			}
		}
		if(lbl.equalsIgnoreCase("gm")){
			if( args.length == 0 && player.hasPermission("frog.gamemode.self")){
		        togglegm(player);
			return true;
			}else{
				if( args.length == 1 && player.hasPermission("frog.gamemode.other")){
					Player playerto = Bukkit.getServer().getPlayer(args[0]);
					if(playerto !=null){
						togglegm(playerto);
						return true;
					}else{
						player.sendMessage(ChatColor.RED + "Could not find player " + args[0]);
						return true;
					}
				}else{
					player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
					return true;
				}
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
				if(player.hasPermission("frog.promo.secret") && args[2] == "secret"){
					getServer().broadcast(ChatColor.GRAY + "[SECRET PROMO]" + promoto + ChatColor.WHITE + " has been promoted to " + rank , "frog.promo.secret");
					return true;
				}else{
					getServer().broadcastMessage(promoto + ChatColor.WHITE + " has been promoted to " + rank);
					return true;
				}
			}
		}
		if(lbl.equalsIgnoreCase("world")){
			if(player.hasPermission("frog.world.self") && args.length == 0){
				player.sendMessage(ChatColor.DARK_GREEN+"You are in"+ChatColor.WHITE+": "+ChatColor.YELLOW+player.getWorld().getName());
			}else{
				if(player.hasPermission("frog.world.other") && args.length == 1){
					Player other = getServer().getPlayer(args[0]);
					if(other == null){
						player.sendMessage(ChatColor.RED + "ERROR: player not found");
						return true;
					}else{
						player.sendMessage(other.getDisplayName() + ChatColor.DARK_GREEN + " is in"+ChatColor.WHITE+": "+ChatColor.YELLOW+other.getWorld().getName());
						return true;
					}
				}else{
					player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
					return true;
				}
			}
		}
		if(lbl.equalsIgnoreCase("pos")){
			if(player.hasPermission("frog.pos.self")){
				int x = (int) player.getLocation().getX();
				int y = (int) player.getLocation().getY();
				int z = (int) player.getLocation().getZ();
				player.sendMessage(ChatColor.DARK_AQUA+"World: " + ChatColor.DARK_GREEN + player.getWorld().getName() + ChatColor.DARK_AQUA + " X: "+ChatColor.WHITE+x+ ChatColor.DARK_AQUA +" Y: "+ChatColor.WHITE+y+ ChatColor.DARK_AQUA +" Z: "+ChatColor.WHITE+z);
				return true;
			}else{
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
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
	    evt.setJoinMessage(colour + player.getName() + ChatColor.WHITE + " logged in, making " + ChatColor.RED + Bukkit.getOnlinePlayers().length + ChatColor.GREEN + plural);
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
	    evt.setQuitMessage(colour + player.getName() + ChatColor.WHITE + " quit, " + ChatColor.RED + (Bukkit.getOnlinePlayers().length - 1) + ChatColor.GREEN + plural + ChatColor.WHITE + " left");
	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent evt){
		String message = evt.getDeathMessage();
		if(message.startsWith("frogman786 was slain")){
			String messagemodifyed = (message + ", but actually wasn't, because nobody can kill the great frog.");
			evt.setDeathMessage(messagemodifyed);
		}
	}
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent evt){
		if(evt.getPlayer().hasPermission("frog.chat.formatting")){
			String message = evt.getMessage();
			String donemess = formatMessage(message);
			evt.setMessage(donemess);
		}
	}
}//comment so I can keep my github streak