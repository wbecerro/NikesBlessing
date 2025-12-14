package wbe.nikesBlessing.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import wbe.nikesBlessing.NikesBlessing;
import wbe.nikesBlessing.config.PlayerPrestige;
import wbe.nikesBlessing.effects.PrestigeEffect;

public class EntityDamageListeners implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void handleEntityDamageByEntityPrestigeEffects(EntityDamageByEntityEvent event) {
        if(event.isCancelled()) {
            return;
        }

        if(!(event.getDamager() instanceof Player player)) {
            return;
        }

        for(PlayerPrestige prestige : NikesBlessing.playerPrestiges.get(player)) {
            for(PrestigeEffect effect : prestige.getPrestige().getEffects()) {
                effect.activateEffect(player, prestige, event);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handleEntityDamagePrestigeEffects(EntityDamageEvent event) {
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
