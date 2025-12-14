package wbe.nikesBlessing.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Messages {

    private FileConfiguration config;

    public String noPermission;
    public String notEnoughArgs;
    public String reload;
    public String prestigeUp;
    public String prestigeDown;
    public String resetPrestige;
    public String resetPrestigeSender;
    public List<String> help;

    public Messages(FileConfiguration config) {
        this.config = config;

        noPermission = config.getString("Messages.noPermission").replace("&", "§");
        notEnoughArgs = config.getString("Messages.notEnoughArgs").replace("&", "§");
        reload = config.getString("Messages.reload").replace("&", "§");
        prestigeUp = config.getString("Messages.prestigeUp").replace("&", "§");
        prestigeDown = config.getString("Messages.prestigeDown").replace("&", "§");
        resetPrestige = config.getString("Messages.resetPrestige").replace("&", "§");
        resetPrestigeSender = config.getString("Messages.resetPrestigeSender").replace("&", "§");
        help = config.getStringList("Messages.help");
    }
}
