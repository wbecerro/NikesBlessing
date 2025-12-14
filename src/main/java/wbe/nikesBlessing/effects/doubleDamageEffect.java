package wbe.nikesBlessing.effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import wbe.nikesBlessing.config.PlayerPrestige;

import java.util.Random;

public class doubleDamageEffect extends PrestigeEffect {

    public doubleDamageEffect(double value, String lore) {
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

        double value = this.value * prestige.getPrestigeLevel();
        Random random = new Random();

        if(random.nextDouble(100) <= value) {
            damageEvent.setDamage(damageEvent.getDamage() * 2);
        }
    }

    public void deactivateEffect(Player player, PlayerPrestige prestige, Event event) {

    }
}
