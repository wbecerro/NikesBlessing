package wbe.nikesBlessing.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;
import wbe.nikesBlessing.config.PlayerPrestige;

import java.util.Random;

public class dodgeEffect extends PrestigeEffect {

    public dodgeEffect(double value, String lore) {
        super(value, lore);
    }

    public void activateEffect(Player player, PlayerPrestige prestige, Event event) {
        if(!(event instanceof EntityDamageEvent damageEvent)) {
            return;
        }

        if(prestige.getPrestigeLevel() < 1) {
            return;
        }

        if(!(damageEvent.getEntity() instanceof Player)) {
            return;
        }

        double value = this.value * prestige.getPrestigeLevel();
        Random random = new Random();

        if(random.nextDouble(100) <= value) {
            damageEvent.setCancelled(true);
        }
    }

    public void deactivateEffect(Player player, PlayerPrestige prestige, Event event) {

    }
}
