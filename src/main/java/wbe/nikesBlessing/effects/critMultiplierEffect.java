package wbe.nikesBlessing.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import wbe.nikesBlessing.config.PlayerPrestige;

public class critMultiplierEffect extends PrestigeEffect {

    public critMultiplierEffect(double value, String lore) {
        super(value, lore);
    }

    public void activateEffect(Player player, PlayerPrestige prestige, Event event) {
        if(!(event instanceof EntityDamageByEntityEvent damageEvent)) {
            return;
        }

        if(prestige.getPrestigeLevel() < 1) {
            return;
        }

        double damage = damageEvent.getDamage();
        double value = this.value * prestige.getPrestigeLevel() + 1;

        if(player.getVelocity().getY() >= -0.1) {
            return;
        }

        damageEvent.setDamage(value * damage);
    }

    public void deactivateEffect(Player player, PlayerPrestige prestige, Event event) {

    }
}
