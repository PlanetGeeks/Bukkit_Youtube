package it.planetgeeks.bukkit.episodio2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MyCommandExecutor implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("basic2"))
		{
			sender.sendMessage("Hai chiamto il comando basic2!");
			return true;
		}
		
		return false;
	}

}
