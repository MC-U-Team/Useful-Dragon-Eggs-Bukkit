package info.u_team.useful_dragon_eggs_bukkit.nms;

import java.lang.reflect.*;

import org.bukkit.*;
import org.bukkit.entity.Entity;

public class NmsUtil {
	
	public static final String NMS_VERSION = Bukkit.getServer().getClass().getPackage().getName().substring(23);
	
	private static final Class<?> CRAFT_WORLD_CLASS;
	private static final Class<?> CRAFT_ENTITY_CLASS;
	
	private static final Class<?> NMS_WORLD_SERVER_CLASS;
	private static final Class<?> NMS_WORLD_CLASS;
	private static final Class<?> NMS_ENTITY_CLASS;
	private static final Class<?> NMS_BLOCK_POSITION_CLASS;
	private static final Class<?> NMS_I_BLOCK_DATA_CLASS;
	private static final Class<?> NMS_BLOCK_CLASS;
	private static final Class<?> NMS_BLOCKS_CLASS;
	private static final Class<?> NMS_I_WORLD_READER_CLASS;
	private static final Class<?> NMS_BLOCK_FALLING_CLASS;
	
	private static final Field NMS_BLOCKS_FIELD_AIR;
	private static final Field NMS_BLOCKS_FIELD_DRAGON_EGG;
	
	private static final Method CRAFT_WORLD_METHOD_GET_HANDLE;
	private static final Method CRAFT_ENTITY_METHOD_GET_HANDLE;
	
	private static final Method NMS_ENTITY_METHOD_GET_CHUNK_COORDINATES;
	private static final Method NMS_BLOCK_POSITION_METHOD_B;
	private static final Method NMS_BLOCK_POSITION_METHOD_GET_Y;
	private static final Method NMS_BLOCK_POSITION_METHOD_DOWN;
	private static final Method NMS_WORLD_SERVER_METHOD_ARE_CHUNKS_LOADED_IN_BETWEEN;
	private static final Method NMS_WORLD_SERVER_METHOD_SET_TYPE_UPDATE;
	private static final Method NMS_WORLD_SERVER_METHOD_SET_TYPE_AND_DATA;
	private static final Method NMS_WORLD_SERVER_METHOD_GET_TYPE;
	private static final Method NMS_BLOCK_METHOD_GET_BLOCK_DATA;
	private static final Method NMS_I_WORLD_READER_METHOD_IS_EMPTY;
	private static final Method NMS_BLOCK_FALLING_METHOD_CAN_FALL_THROUGH;
	
	static {
		CRAFT_WORLD_CLASS = getNmsClass("org.bukkit.craftbukkit", "CraftWorld");
		CRAFT_ENTITY_CLASS = getNmsClass("org.bukkit.craftbukkit", "entity.CraftEntity");
		
		NMS_WORLD_SERVER_CLASS = getNmsClass("net.minecraft.server", "WorldServer");
		NMS_WORLD_CLASS = getNmsClass("net.minecraft.server", "World");
		NMS_ENTITY_CLASS = getNmsClass("net.minecraft.server", "Entity");
		NMS_BLOCK_POSITION_CLASS = getNmsClass("net.minecraft.server", "BlockPosition");
		NMS_I_BLOCK_DATA_CLASS = getNmsClass("net.minecraft.server", "IBlockData");
		NMS_BLOCK_CLASS = getNmsClass("net.minecraft.server", "Block");
		NMS_BLOCKS_CLASS = getNmsClass("net.minecraft.server", "Blocks");
		NMS_I_WORLD_READER_CLASS = getNmsClass("net.minecraft.server", "IWorldReader");
		NMS_BLOCK_FALLING_CLASS = getNmsClass("net.minecraft.server", "BlockFalling");
		
		NMS_BLOCKS_FIELD_AIR = getNmsField(NMS_BLOCKS_CLASS, "AIR");
		NMS_BLOCKS_FIELD_DRAGON_EGG = getNmsField(NMS_BLOCKS_CLASS, "DRAGON_EGG");
		
		CRAFT_WORLD_METHOD_GET_HANDLE = getNmsMethod(CRAFT_WORLD_CLASS, "getHandle");
		CRAFT_ENTITY_METHOD_GET_HANDLE = getNmsMethod(CRAFT_ENTITY_CLASS, "getHandle");
		
		NMS_ENTITY_METHOD_GET_CHUNK_COORDINATES = getNmsMethod(NMS_ENTITY_CLASS, "getChunkCoordinates");
		NMS_BLOCK_POSITION_METHOD_B = getNmsMethod(NMS_BLOCK_POSITION_CLASS, "b", int.class, int.class, int.class);
		NMS_BLOCK_POSITION_METHOD_GET_Y = getNmsMethod(NMS_BLOCK_POSITION_CLASS, "getY");
		NMS_BLOCK_POSITION_METHOD_DOWN = getNmsMethod(NMS_BLOCK_POSITION_CLASS, "down");
		NMS_WORLD_SERVER_METHOD_ARE_CHUNKS_LOADED_IN_BETWEEN = getNmsMethod(NMS_WORLD_SERVER_CLASS, "areChunksLoadedBetween", NMS_BLOCK_POSITION_CLASS, NMS_BLOCK_POSITION_CLASS);
		NMS_WORLD_SERVER_METHOD_SET_TYPE_UPDATE = getNmsMethod(NMS_WORLD_CLASS, "setTypeUpdate", NMS_BLOCK_POSITION_CLASS, NMS_I_BLOCK_DATA_CLASS);
		NMS_WORLD_SERVER_METHOD_SET_TYPE_AND_DATA = getNmsMethod(NMS_WORLD_CLASS, "setTypeAndData", NMS_BLOCK_POSITION_CLASS, NMS_I_BLOCK_DATA_CLASS, int.class);
		NMS_WORLD_SERVER_METHOD_GET_TYPE = getNmsMethod(NMS_WORLD_CLASS, "getType", NMS_BLOCK_POSITION_CLASS);
		NMS_BLOCK_METHOD_GET_BLOCK_DATA = getNmsMethod(NMS_BLOCK_CLASS, "getBlockData");
		NMS_I_WORLD_READER_METHOD_IS_EMPTY = getNmsMethod(NMS_I_WORLD_READER_CLASS, "isEmpty", NMS_BLOCK_POSITION_CLASS);
		NMS_BLOCK_FALLING_METHOD_CAN_FALL_THROUGH = getNmsMethod(NMS_BLOCK_FALLING_CLASS, "canFallThrough", NMS_I_BLOCK_DATA_CLASS);
	}
	
	public static void doDragonEggLogic(World world, Entity entity, Runnable callback) {
		final Object worldNms = invokeMethod(CRAFT_WORLD_METHOD_GET_HANDLE, world);
		final Object entityNms = invokeMethod(CRAFT_ENTITY_METHOD_GET_HANDLE, entity);
		
		final Object posNms = invokeMethod(NMS_ENTITY_METHOD_GET_CHUNK_COORDINATES, entityNms);
		
		final Object leftPosNms = invokeMethod(NMS_BLOCK_POSITION_METHOD_B, posNms, -32, -32, -32);
		final Object rightPosNms = invokeMethod(NMS_BLOCK_POSITION_METHOD_B, posNms, 32, 32, 32);
		
		final boolean chunksLoaded = invokeMethod(NMS_WORLD_SERVER_METHOD_ARE_CHUNKS_LOADED_IN_BETWEEN, worldNms, leftPosNms, rightPosNms);
		
		if (chunksLoaded) {
			return;
		}
		
		callback.run();
		
		final Object airBlockNms = getField(NMS_BLOCKS_FIELD_AIR, null);
		final Object airBlockStateNms = invokeMethod(NMS_BLOCK_METHOD_GET_BLOCK_DATA, airBlockNms);
		
		invokeMethod(NMS_WORLD_SERVER_METHOD_SET_TYPE_UPDATE, worldNms, posNms, airBlockStateNms);
		
		Object fallPosNms;
		
		for (fallPosNms = posNms; (boolean) invokeMethod(NMS_I_WORLD_READER_METHOD_IS_EMPTY, worldNms, fallPosNms) && (boolean) invokeMethod(NMS_BLOCK_FALLING_METHOD_CAN_FALL_THROUGH, null, (Object) invokeMethod(NMS_WORLD_SERVER_METHOD_GET_TYPE, worldNms, fallPosNms)) && (int) invokeMethod(NMS_BLOCK_POSITION_METHOD_GET_Y, fallPosNms) > 0; fallPosNms = invokeMethod(NMS_BLOCK_POSITION_METHOD_DOWN, fallPosNms)) {
		}
		
		if ((int) invokeMethod(NMS_BLOCK_POSITION_METHOD_GET_Y, fallPosNms) > 0) {
			final Object dragonEggBlockNms = getField(NMS_BLOCKS_FIELD_DRAGON_EGG, null);
			final Object dragonEggBlockStateNms = invokeMethod(NMS_BLOCK_METHOD_GET_BLOCK_DATA, dragonEggBlockNms);
			
			invokeMethod(NMS_WORLD_SERVER_METHOD_SET_TYPE_AND_DATA, worldNms, fallPosNms, dragonEggBlockStateNms, 2);
		}
	}
	
	private static Class<?> getNmsClass(String basePackage, String subPackage) {
		try {
			return Class.forName(getNmsPackage(basePackage, subPackage));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	private static String getNmsPackage(String basePackage, String subPackage) {
		return basePackage + "." + NMS_VERSION + "." + subPackage;
	}
	
	private static Field getNmsField(Class<?> clazz, String field) {
		try {
			return clazz.getField(field);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	private static Method getNmsMethod(Class<?> clazz, String method, Class<?>... args) {
		try {
			return clazz.getMethod(method, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Object> T getField(Field field, Object instance) {
		try {
			return (T) field.get(instance);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Object> T invokeMethod(Method method, Object instance, Object... args) {
		try {
			return (T) method.invoke(instance, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
