package info.u_team.useful_dragon_eggs_bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import info.u_team.useful_dragon_eggs_bukkit.event.UsefulDragonEggsFallHandler;

public class UsefulDragonEggsPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new UsefulDragonEggsFallHandler(), this);
	}
	
	@Override
	public void onDisable() {
	}
	
}
