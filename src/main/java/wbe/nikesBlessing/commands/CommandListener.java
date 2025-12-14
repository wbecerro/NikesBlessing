package wbe.nikesBlessing.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wbe.nikesBlessing.NikesBlessing;

public class CommandListener implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("NikesBlessing")) {
            Player player = null;
            if(sender instanceof Player) {
                player = (Player) sender;
            }

            if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
                if(!sender.hasPermission("nikesblessing.command.help")) {
                    sender.sendMessage(NikesBlessing.messages.noPermission);
                    return false;
                }

                for(String line : NikesBlessing.messages.help) {
                    sender.sendMessage(line.replace("&", "§"));
                }
            }
        }
    }
}
