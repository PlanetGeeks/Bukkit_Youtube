package it.planetgeeks.bukkit.episodio3;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommandExecutor implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		boolean isPlayer = sender instanceof Player;
		
		if(isPlayer && cmd.getName().equals("tpa"))
		{
			if(args.length != 1)
				return false;
			
			String playerName = args[0];
			
			if(!Episodio3.isPlayerOnline(playerName))
			{
				sender.sendMessage("Il giocatore " + playerName + " non e' online!");
				return true;
			}
				
			Episodio3.sendRequest(sender.getName(), playerName);
			
			return true;
		}
		else if(isPlayer && (cmd.getName().equals("tpaccept") || cmd.getName().equals("tpdeny")))
		{
			String result = Episodio3.playerAcceptedRequest(cmd.getName().equals("tpaccept"), sender.getName());
			
			sender.sendMessage(result);
			
			return true;
		}

		return false;
	}

}
