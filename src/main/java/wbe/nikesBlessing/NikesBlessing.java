package wbe.nikesBlessing;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import wbe.nikesBlessing.commands.CommandListener;
import wbe.nikesBlessing.commands.TabListener;
import wbe.nikesBlessing.config.Config;
import wbe.nikesBlessing.config.Messages;
import wbe.nikesBlessing.listeners.EventListeners;
import wbe.nikesBlessing.utils.Utilities;

import java.io.File;

public final class NikesBlessing extends JavaPlugin {

    private FileConfiguration configuration;

    private CommandListener commandListener;

    private TabListener tabListener;

    private EventListeners eventListeners;

    public static Config config;

    public static Messages messages;

    public static Utilities utilities;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("Nike's Blessing enabled correctly.");
        reloadConfiguration();

        commandListener = new CommandListener();
        getCommand("nikesblessing").setExecutor(commandListener);
        tabListener = new TabListener();
        getCommand("nikesblessing").setTabCompleter(tabListener);
        eventListeners = new EventListeners();
        eventListeners.initializeListeners();
    }

    @Override
    public void onDisable() {
        reloadConfig();
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

        reloadConfig();
        configuration = getConfig();
        config = new Config(configuration);
        messages = new Messages(configuration);
        utilities = new Utilities();
    }
}
