package it.planetgeeks.bukkit.episodio3;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Episodio3 extends JavaPlugin
{
	private static Episodio3 instance;

	private ConcurrentHashMap<String, String> requests = new ConcurrentHashMap<String, String>();

	public void onEnable()
	{
		instance = this;
		
		registerCommands();

		getLogger().info("Il plugin e' stato caricato correttamente!" + "[Plugin Name : " + this.getDescription().getName() + " , Plugin Version : " + this.getDescription().getVersion());
	}
	
	private void registerCommands()
	{
		TeleportCommandExecutor exec = new TeleportCommandExecutor();
		
		getCommand("tpa").setExecutor(exec);
		getCommand("tpaccept").setExecutor(exec);
		getCommand("tpdeny").setExecutor(exec);
	}

	public static Episodio3 getInstance()
	{
		return instance;
	}

	public static boolean isPlayerOnline(String username)
	{
		Player[] players = getInstance().getServer().getOnlinePlayers();

		for (Player player : players)
			if (player.getName().equals(username))
				return true;

		return false;
	}

	public static Player getPlayer(String username)
	{
		Player[] players = getInstance().getServer().getOnlinePlayers();

		for (Player player : players)
			if (player.getName().equals(username))
				return player;

		return null;
	}

	public static void sendRequest(String requester, String receiver)
	{
		getInstance().requests.put(receiver, requester);

		Player player = getPlayer(receiver);

		if (player != null)
			player.sendMessage("Il giocatore " + requester + " vuole teletrasportarsi da te! /tpaccept per accettare o /tpdeny per rifiutare");
	}
	
	public static String playerAcceptedRequest(boolean accepted, String player)
	{
		if(getInstance().requests.containsKey(player))
		{
			String sender = getInstance().requests.get(player);
			
			getInstance().requests.remove(player);
			
			if(!isPlayerOnline(sender))
				return "Il player non e' online";
			
			Player senderEntity = getPlayer(sender);
			
			Player playerEntity = getPlayer(player);
			
			senderEntity.sendMessage("Il giocatore " +  player + (accepted ? " ha accettato " : " ha rifiutato ") + "la tua richiesta");
			
			if(accepted && playerEntity != null)
				senderEntity.teleport(playerEntity);
			else
				return "Richiesta rifiutata correttamente";
			
			return "Richiesta accettata! Teletrasporto effettuato!";
		}
		else
			return "Nessuna richiesta in attesa!";
	}
}
