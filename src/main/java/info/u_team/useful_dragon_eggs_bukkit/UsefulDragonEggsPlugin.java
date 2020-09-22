package info.u_team.useful_dragon_eggs_bukkit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import info.u_team.useful_dragon_eggs_bukkit.command.DragonEggsCommand;
import info.u_team.useful_dragon_eggs_bukkit.event.UsefulDragonEggsFallHandler;

public class UsefulDragonEggsPlugin extends JavaPlugin {
	
	private static UsefulDragonEggsPlugin instance;
	
	private FileConfiguration config;
	
	@Override
	public void onLoad() {
		instance = this;
	}
	
	@Override
	public void onEnable() {
		config = getConfig();
		
		config.addDefault("bedrockBreaking", true);
		
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
