package wbe.nikesBlessing.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import wbe.nikesBlessing.NikesBlessing;
import wbe.nikesBlessing.config.PlayerPrestige;
import wbe.nikesBlessing.effects.PrestigeEffect;

public class ProjectileLaunchListeners implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void handleProjectileShootPrestigeEffects(EntityShootBowEvent event) {
        if(event.isCancelled()) {
            return;
        }

        if(!(event.getEntity() instanceof Player player)) {
            return;
        }

        for(PlayerPrestige prestige : NikesBlessing.playerPrestiges.get(player)) {
            for(PrestigeEffect effect : prestige.getPrestige().getEffects()) {
                effect.activateEffect(player, prestige, event);
            }
        }
    }
}
