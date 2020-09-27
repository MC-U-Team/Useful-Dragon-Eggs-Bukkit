package info.u_team.useful_dragon_eggs_bukkit.event;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntitySpawnEvent;

import info.u_team.useful_dragon_eggs_bukkit.UsefulDragonEggsPlugin;
import info.u_team.useful_dragon_eggs_bukkit.nms.NmsUtil;

public class UsefulDragonEggsFallHandler implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntitySpawn(EntitySpawnEvent event) {
		final Entity entity = event.getEntity();
		
		if (!(entity instanceof FallingBlock)) {
			return;
		}
		
		if (!UsefulDragonEggsPlugin.getInstance().getConfig().getBoolean("bedrockBreaking")) {
			return;
		}
		
		final FallingBlock fallingBlockEntity = (FallingBlock) entity;
		final Material material = fallingBlockEntity.getBlockData().getMaterial();
		
		if (material != Material.DRAGON_EGG) {
			return;
		}
		
		// Replicate old lazy chunk behavior
		
		NmsUtil.doDragonEggLogic(entity.getWorld(), fallingBlockEntity, () -> event.setCancelled(true));
	}
}
