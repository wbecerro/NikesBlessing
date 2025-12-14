package wbe.nikesBlessing.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import wbe.nikesBlessing.NikesBlessing;
import wbe.nikesBlessing.config.PlayerPrestige;
import wbe.nikesBlessing.effects.PrestigeEffect;

import java.util.List;

public class PlayerQuitListeners implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePrestigeEffectsOnJoin(PlayerQuitEvent event) {
        NikesBlessing.utilities.savePlayerData(event.getPlayer());
        List<PlayerPrestige> prestiges = NikesBlessing.playerPrestiges.get(event.getPlayer());
        if(prestiges == null || prestiges.isEmpty()) {
            return;
        }

        for(PlayerPrestige prestige : prestiges) {
            for(PrestigeEffect effect : prestige.getPrestige().getEffects()) {
                effect.deactivateEffect(event.getPlayer(), prestige, null);
            }
        }

        NikesBlessing.playerPrestiges.remove(event.getPlayer());
    }
}
