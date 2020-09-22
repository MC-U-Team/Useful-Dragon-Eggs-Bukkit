package info.u_team.useful_dragon_eggs_bukkit.command;

import java.util.*;

import org.bukkit.command.*;

public class DragonEggsCommand implements TabExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length >= 1 && args[0].equals("bedrock-breaking")) {
			if (args.length == 1) {
				sender.sendMessage("Value bedrock-breaking is currently set to: xyz");
				return true;
			} else if (args.length == 2) {
				final String argValue = args[1];
				
				final boolean valueTrue = argValue.equals("true");
				final boolean valueFalse = argValue.equals("false");
				
				if (valueTrue || valueFalse) {
					final boolean value = valueTrue ? true : false;
					sender.sendMessage("Value bedrock-breaking is now set to: xyz");
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
