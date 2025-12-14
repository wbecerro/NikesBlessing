package wbe.nikesBlessing.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemDamageEvent;
import wbe.nikesBlessing.config.PlayerPrestige;

import java.util.Random;

public class durabilityEffect extends PrestigeEffect {

    public durabilityEffect(double value, String lore) {
        super(value, lore);
    }

    public void activateEffect(Player player, PlayerPrestige prestige, Event event) {
        if(!(event instanceof PlayerItemDamageEvent itemEvent)) {
            return;
        }

        if(prestige.getPrestigeLevel() < 1) {
            return;
        }

        double value = this.value * prestige.getPrestigeLevel();
        Random random = new Random();

        if(random.nextDouble(100) <= value) {
            itemEvent.setCancelled(true);
        }
    }

    public void deactivateEffect(Player player, PlayerPrestige prestige, Event event) {

    }
}
