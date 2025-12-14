package wbe.nikesBlessing.effects;

import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerFishEvent;
import wbe.nikesBlessing.config.PlayerPrestige;

public class fishingSpeedMultiplierEffect extends PrestigeEffect {

    public fishingSpeedMultiplierEffect(double value, String lore) {
        super(value, lore);
    }

    public void activateEffect(Player player, PlayerPrestige prestige, Event event) {
        if(!(event instanceof PlayerFishEvent fishEvent)) {
            return;
        }

        if(prestige.getPrestigeLevel() < 1) {
            return;
        }

        double value = 1 - this.value * prestige.getPrestigeLevel();
        FishHook hook = fishEvent.getHook();

        int minWaitTime = (int) (hook.getMinWaitTime() * value);
        int maxWaitTime = (int) (hook.getMaxWaitTime() * value);
        int minLureTime = (int) (hook.getMinLureTime() * value);
        int maxLureTime = (int) (hook.getMaxLureTime() * value);

        hook.setMinWaitTime(minWaitTime);
        hook.setMaxWaitTime(maxWaitTime);
        hook.setMinLureTime(minLureTime);
        hook.setMaxLureTime(maxLureTime);
    }

    public void deactivateEffect(Player player, PlayerPrestige prestige, Event event) {

    }
}
