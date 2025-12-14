package wbe.nikesBlessing.commands;

import com.gmail.nossr50.locale.LocaleLoader;
import com.gmail.nossr50.util.text.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wbe.nikesBlessing.NikesBlessing;
import wbe.nikesBlessing.config.PlayerPrestige;

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
            } else if(args[0].equalsIgnoreCase("prestige")) {
                if(!sender.hasPermission("nikesblessing.command.prestige")) {
                    sender.sendMessage(NikesBlessing.messages.noPermission);
                    return false;
                }

                String skill = args[1];
                if(args.length > 2) {
                    player = Bukkit.getServer().getPlayer(args[2]);
                }

                PlayerPrestige prestige = NikesBlessing.utilities.searchPrestige(player, skill);
                NikesBlessing.utilities.levelUpPrestige(player, prestige);
                player.sendMessage(NikesBlessing.messages.prestigeUp.replace("%skill%", LocaleLoader.getString(
                        StringUtils.getCapitalized(skill) + ".SkillName")));
            } else if(args[0].equalsIgnoreCase("deprestige")) {
                if(!sender.hasPermission("nikesblessing.command.deprestige")) {
                    sender.sendMessage(NikesBlessing.messages.noPermission);
                    return false;
                }

                String skill = args[1];
                if(args.length > 2) {
                    player = Bukkit.getServer().getPlayer(args[2]);
                }

                PlayerPrestige prestige = NikesBlessing.utilities.searchPrestige(player, skill);
                NikesBlessing.utilities.levelDownPrestige(player, prestige);
                player.sendMessage(NikesBlessing.messages.prestigeDown.replace("%skill%", LocaleLoader.getString(
                        StringUtils.getCapitalized(skill) + ".SkillName")));
            } else if(args[0].equalsIgnoreCase("reset")) {
                if(!sender.hasPermission("nikesblessing.command.reset")) {
                    sender.sendMessage(NikesBlessing.messages.noPermission);
                    return false;
                }

                String skill = args[1];
                if(args.length > 2) {
                    player = Bukkit.getServer().getPlayer(args[2]);
                }

                PlayerPrestige prestige = NikesBlessing.utilities.searchPrestige(player, skill);
                NikesBlessing.utilities.resetPrestige(player, prestige);
                player.sendMessage(NikesBlessing.messages.resetPrestige.replace("%skill%", LocaleLoader.getString(
                        StringUtils.getCapitalized(skill) + ".SkillName")));
                sender.sendMessage(NikesBlessing.messages.resetPrestigeSender.replace("%skill%", LocaleLoader.getString(
                        StringUtils.getCapitalized(skill) + ".SkillName"))
                        .replace("%player%", player.getName()));
            } else if(args[0].equalsIgnoreCase("reload")) {
                if(!sender.hasPermission("nikesblessing.command.reload")) {
                    sender.sendMessage(NikesBlessing.messages.noPermission);
                    return false;
                }

                NikesBlessing.getInstance().reloadConfiguration();
                sender.sendMessage(NikesBlessing.messages.reload);
            }
        }

        return  true;
    }
}
