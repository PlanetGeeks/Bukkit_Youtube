package it.planetgeeks.bukkit.episodio2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Episodio2 extends JavaPlugin
{
	private static Episodio2 instance;
	
    public void onEnable()
    {
    	instance = this;
    	
    	registerCommands();
    	
    	getLogger().info("Il plugin e' stato caricato correttamente!");
    }
    
    private void registerCommands()
    {
    	MyCommandExecutor exec = new MyCommandExecutor();
    	
    	getCommand("basic2").setExecutor(exec);
    	getCommand("basic3").setExecutor(exec);
    }
    
    public static Episodio2 getInstance()
    {
    	return instance;
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[]args)
    {
    	boolean isPlayer = sender instanceof Player;

    	if(!isPlayer && cmd.getName().equalsIgnoreCase("basic"))
    	{
    		if(args.length > 0 && args[0].equals("ciao"))
    		{
    			sender.sendMessage("Ciao anche a te!");
    			return true;
    		}
    		
    		sender.sendMessage("Ciao! Hai chiamto il comando basic!");
    		return true;
    	}
    	
    	return false;
    }
}
