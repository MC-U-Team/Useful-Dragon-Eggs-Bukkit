package info.u_team.useful_dragon_eggs_bukkit.nms;

import java.lang.reflect.Method;

import org.bukkit.*;
import org.bukkit.entity.Entity;

public class NmsUtil {
	
	public static final String NMS_VERSION = Bukkit.getServer().getClass().getPackage().getName().substring(23);
	
	private static final Class<?> CRAFT_WORLD_CLASS;
	private static final Class<?> CRAFT_ENTITY_CLASS;
	
	private static final Method CRAFT_WORLD_METHOD_GET_HANDLE;
	private static final Method CRAFT_ENTITY_METHOD_GET_HANDLE;
	
	static {
		CRAFT_WORLD_CLASS = getNmsClass("org.bukkit.craftbukkit", "CraftWorld");
		CRAFT_ENTITY_CLASS = getNmsClass("org.bukkit.craftbukkit", "CraftEntity");
		
		CRAFT_WORLD_METHOD_GET_HANDLE = getNmsMethod(CRAFT_WORLD_CLASS, "getHandle");
		CRAFT_ENTITY_METHOD_GET_HANDLE = getNmsMethod(CRAFT_ENTITY_CLASS, "getHandle");
	}
	
	public static void WorldServer$areChunksLoadedBetween(World world, Entity entity) {
		
		// Class.forName("org.bukkit.craftbukkit." + NMS_VERSION + ".CraftWorld").getMethod("getHandle");
		
		// final WorldServer world = ((CraftWorld) event.getEntity().getWorld()).getHandle();
		
		final Object worldNms = invokeNmsMethod(CRAFT_WORLD_METHOD_GET_HANDLE, world);
		final Object entitNms = invokeNmsMethod(CRAFT_ENTITY_METHOD_GET_HANDLE, entity);
		
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
	
	private static Method getNmsMethod(Class<?> clazz, String method, Class<?>... args) {
		try {
			return clazz.getMethod(method, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	private static Object invokeNmsMethod(Method method, Object instance, Object... args) {
		try {
			return method.invoke(instance, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
