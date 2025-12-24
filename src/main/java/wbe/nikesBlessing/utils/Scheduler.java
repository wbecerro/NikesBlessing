package wbe.nikesBlessing.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import wbe.nikesBlessing.NikesBlessing;

public class Scheduler {

    private static NikesBlessing plugin;

    public static void startSchedulers() {
        plugin = NikesBlessing.getInstance();
        startDataSaveScheduler();
    }

    private static void startDataSaveScheduler() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for(Player player : NikesBlessing.playerPrestiges.keySet()) {
                    NikesBlessing.utilities.savePlayerData(player);
                }
            }
        }, 10L, 60 * 5 * 20L);
    }
}
