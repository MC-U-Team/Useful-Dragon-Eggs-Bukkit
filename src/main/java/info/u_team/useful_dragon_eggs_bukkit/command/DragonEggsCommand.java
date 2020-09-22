package info.u_team.useful_dragon_eggs_bukkit.command;

import java.util.*;

import org.bukkit.command.*;

import info.u_team.useful_dragon_eggs_bukkit.UsefulDragonEggsPlugin;

public class DragonEggsCommand implements TabExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length >= 1 && args[0].equals("bedrock-breaking")) {
			if (args.length == 1) {
				final boolean bedrockBreaking = UsefulDragonEggsPlugin.getInstance().getConfig().getBoolean("bedrockBreaking");
				sender.sendMessage("Value bedrock-breaking is currently set to: " + bedrockBreaking);
				return true;
			} else if (args.length == 2) {
				final String argValue = args[1];
				
				final boolean valueTrue = argValue.equals("true");
				final boolean valueFalse = argValue.equals("false");
				
				if (valueTrue || valueFalse) {
					final boolean value = valueTrue ? true : false;
					UsefulDragonEggsPlugin.getInstance().getConfig().set("bedrockBreaking", value);
					UsefulDragonEggsPlugin.getInstance().saveConfig();
					final boolean bedrockBreaking = UsefulDragonEggsPlugin.getInstance().getConfig().getBoolean("bedrockBreaking");
					sender.sendMessage("Value bedrock-breaking is now set to: " + bedrockBreaking);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			return Arrays.asList("bedrock-breaking");
		} else if (args.length == 1 && args[0].equals("bedrock-breaking")) {
			return Arrays.asList("true", "false");
		}
		return null;
	}
	
}
