package wbe.nikesBlessing.listeners;

import org.bukkit.plugin.PluginManager;
import wbe.nikesBlessing.NikesBlessing;

public class EventListeners {

    public void initializeListeners() {
        NikesBlessing plugin = NikesBlessing.getInstance();
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListeners(), plugin);
        pluginManager.registerEvents(new PlayerQuitListeners(), plugin);
        pluginManager.registerEvents(new EntityDamageListeners(), plugin);
        pluginManager.registerEvents(new FoodLevelChangeListeners(), plugin);
        pluginManager.registerEvents(new PlayerFishListeners(), plugin);
        pluginManager.registerEvents(new PlayerItemConsumeListeners(), plugin);
        pluginManager.registerEvents(new PlayerItemDamageListeners(), plugin);
        pluginManager.registerEvents(new ProjectileLaunchListeners(), plugin);
    }
}
