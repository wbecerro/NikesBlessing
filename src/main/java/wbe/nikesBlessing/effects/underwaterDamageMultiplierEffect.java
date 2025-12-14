package wbe.nikesBlessing.effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import wbe.nikesBlessing.config.PlayerPrestige;

public class underwaterDamageMultiplierEffect extends PrestigeEffect {

    public underwaterDamageMultiplierEffect(double value, String lore) {
        super(value, lore);
    }

    public void activateEffect(Player player, PlayerPrestige prestige, Event event) {
        if(!(event instanceof EntityDamageByEntityEvent damageEvent)) {
            return;
        }

        if(prestige.getPrestigeLevel() < 1) {
            return;
        }

        if(!(damageEvent.getDamager() instanceof Player)) {
            return;
        }

        if(!(damageEvent.getEntity() instanceof LivingEntity)) {
            return;
        }

        if(!player.isInWater()) {
            return;
        }

        double value = this.value * prestige.getPrestigeLevel() + 1;
        damageEvent.setDamage(damageEvent.getDamage() * value);
    }

    public void deactivateEffect(Player player, PlayerPrestige prestige, Event event) {

    }
}
