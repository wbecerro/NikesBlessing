package wbe.nikesBlessing;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import wbe.nikesBlessing.commands.CommandListener;
import wbe.nikesBlessing.commands.TabListener;
import wbe.nikesBlessing.config.Config;
import wbe.nikesBlessing.config.Messages;
import wbe.nikesBlessing.config.PlayerPrestige;
import wbe.nikesBlessing.listeners.EventListeners;
import wbe.nikesBlessing.papi.PapiExtension;
import wbe.nikesBlessing.utils.Scheduler;
import wbe.nikesBlessing.utils.Utilities;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public final class NikesBlessing extends JavaPlugin {

    private FileConfiguration configuration;

    private CommandListener commandListener;

    private TabListener tabListener;

    private EventListeners eventListeners;

    private PapiExtension papiExtension;

    public static Config config;

    public static Messages messages;

    public static Utilities utilities;

    public static HashMap<Player, List<PlayerPrestige>> playerPrestiges = new HashMap<>();

    @Override
    public void onEnable() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            papiExtension = new PapiExtension();
            papiExtension.register();
        }
        saveDefaultConfig();
        getLogger().info("Nike's Blessing enabled correctly.");
        reloadConfiguration();

        commandListener = new CommandListener();
        getCommand("nikesblessing").setExecutor(commandListener);
        tabListener = new TabListener();
        getCommand("nikesblessing").setTabCompleter(tabListener);
        eventListeners = new EventListeners();
        eventListeners.initializeListeners();
        Scheduler.startSchedulers();
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        reloadConfig();
        for(Player player : playerPrestiges.keySet()) {
            utilities.savePlayerData(player);
        }
        getLogger().info("Nike's Blessing disabled correctly.");
    }

    public static NikesBlessing getInstance() {
        return getPlugin(NikesBlessing.class);
    }

    public void reloadConfiguration() {
        if(!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        new File(getDataFolder(), "saves").mkdir();
        for(Player player : playerPrestiges.keySet()) {
            utilities.savePlayerData(player);
        }

        reloadConfig();
        configuration = getConfig();
        config = new Config(configuration);
        messages = new Messages(configuration);
        utilities = new Utilities();
        for (Player player : Bukkit.getOnlinePlayers()) {
            utilities.loadPlayerData(player);
        }
    }
}
