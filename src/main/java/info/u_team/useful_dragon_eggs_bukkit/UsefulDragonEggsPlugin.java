package info.u_team.useful_dragon_eggs_bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import info.u_team.useful_dragon_eggs_bukkit.command.DragonEggsCommand;
import info.u_team.useful_dragon_eggs_bukkit.event.UsefulDragonEggsFallHandler;

public class UsefulDragonEggsPlugin extends JavaPlugin {
	
	private static UsefulDragonEggsPlugin INSTANCE;
	
	@Override
	public void onLoad() {
		INSTANCE = this;
	}
	
	@Override
	public void onEnable() {
		getConfig().addDefault("bedrockBreaking", true);
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		getServer().getPluginManager().registerEvents(new UsefulDragonEggsFallHandler(), this);
		getServer().getPluginCommand("dragoneggs").setExecutor(new DragonEggsCommand());
	}
	
	public static UsefulDragonEggsPlugin getInstance() {
		return INSTANCE;
	}
	
}
