package info.u_team.useful_dragon_eggs_bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import info.u_team.useful_dragon_eggs_bukkit.command.DragonEggsCommand;
import info.u_team.useful_dragon_eggs_bukkit.event.UsefulDragonEggsFallHandler;

public class UsefulDragonEggsPlugin extends JavaPlugin {
	
	private static UsefulDragonEggsPlugin instance;
	
	@Override
	public void onLoad() {
		instance = this;
	}
	
	@Override
	public void onEnable() {
		getConfig().addDefault("bedrockBreaking", true);
		
		getServer().getPluginManager().registerEvents(new UsefulDragonEggsFallHandler(), this);
		getServer().getPluginCommand("dragoneggs").setExecutor(new DragonEggsCommand());
	}
	
	@Override
	public void onDisable() {
	}
	
	public static UsefulDragonEggsPlugin getInstance() {
		return instance;
	}
	
}
