package info.u_team.useful_dragon_eggs_bukkit.event;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntitySpawnEvent;

import net.minecraft.server.v1_16_R2.*;

public class UsefulDragonEggsFallHandler implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntitySpawn(EntitySpawnEvent event) {
		final Entity entity = event.getEntity();
		
		if (!(entity instanceof FallingBlock)) {
			return;
		}
		
		// TODO CONFIG
		
		final FallingBlock fallingBlockEntity = (FallingBlock) entity;
		final Material material = fallingBlockEntity.getBlockData().getMaterial();
		
		if (material != Material.DRAGON_EGG) {
			return;
		}
		
		// Replicate old lazy chunk behavior
		
		// TODO do proper nms
		
		final WorldServer world = ((CraftWorld) event.getEntity().getWorld()).getHandle();
		
		final BlockPosition blockPos = ((CraftEntity) event.getEntity()).getHandle().getChunkCoordinates();
		
		if (world.areChunksLoadedBetween(blockPos.b(-32, -32, -32), blockPos.b(32, 32, 32))) {
			return;
		}
		
		event.setCancelled(true);
		
		world.setTypeUpdate(blockPos, Blocks.AIR.getBlockData());
		
		BlockPosition fallPos;
		
		for (fallPos = blockPos; world.isEmpty(fallPos) && BlockFalling.canFallThrough(world.getType(fallPos)) && fallPos.getY() > 0; fallPos = fallPos.down()) {
		}
		
		if (fallPos.getY() > 0) {
			world.setTypeAndData(fallPos, Blocks.DRAGON_EGG.getBlockData(), 2);
		}
	}
	
}
