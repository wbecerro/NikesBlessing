package wbe.nikesBlessing.effects;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;
import wbe.nikesBlessing.config.PlayerPrestige;

public class arrowSpeedMultiplierEffect extends PrestigeEffect {

    public arrowSpeedMultiplierEffect(double value, String lore) {
        super(value, lore);
    }

    public void activateEffect(Player player, PlayerPrestige prestige, Event event) {
        if(!(event instanceof ProjectileLaunchEvent projectileEvent)) {
            return;
        }

        if(prestige.getPrestigeLevel() < 1) {
            return;
        }

        double value = this.value * prestige.getPrestigeLevel() + 1;
        Projectile projectile = projectileEvent.getEntity();

        Vector velocity = projectile.getVelocity().clone().multiply(value);
        projectile.setVelocity(velocity);
    }

    public void deactivateEffect(Player player, PlayerPrestige prestige, Event event) {

    }
}
