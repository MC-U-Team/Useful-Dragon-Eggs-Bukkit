package info.u_team.useful_dragon_eggs_bukkit.nms;

import java.lang.reflect.Method;

import org.bukkit.*;

public class NmsUtil {
	
	public static final String NMS_VERSION = Bukkit.getServer().getClass().getPackage().getName().substring(23);
	
	private static final Class<?> CRAFT_WORLD_CLASS;
	private static final Class<?> WORLD_SERVER_CLASS;
	
	private static final Method CRAFT_WORLD_METHOD_GET_HANDLE;
	
	static {
		CRAFT_WORLD_CLASS = getNmsClass("org.bukkit.craftbukkit", "CraftWorld");
		WORLD_SERVER_CLASS = getNmsClass("net.minecraft.server", "WorldServer");
		
		CRAFT_WORLD_METHOD_GET_HANDLE = getNmsMethod(CRAFT_WORLD_CLASS, "getHandle");
	}
	
	public static void WorldServer$areChunksLoadedBetween(World world, Location location) {
		
		// Class.forName("org.bukkit.craftbukkit." + NMS_VERSION + ".CraftWorld").getMethod("getHandle");
		
		// final WorldServer world = ((CraftWorld) event.getEntity().getWorld()).getHandle();
		
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
}
