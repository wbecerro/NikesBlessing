package wbe.nikesBlessing.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;
import wbe.nikesBlessing.config.PlayerPrestige;

import java.util.List;

public class potionDurationMultiplierEffect extends PrestigeEffect {

    public potionDurationMultiplierEffect(double value, String lore) {
        super(value, lore);
    }

    public void activateEffect(Player player, PlayerPrestige prestige, Event event) {
        if(!(event instanceof PlayerItemConsumeEvent consumeEvent)) {
            return;
        }

        if(prestige.getPrestigeLevel() < 1) {
            return;
        }

        if(!(consumeEvent.getItem().getItemMeta() instanceof PotionMeta meta)) {
            return;
        }

        double value = this.value * prestige.getPrestigeLevel() + 1;

        PotionType potionType = meta.getBasePotionType();
        List<PotionEffect> potionEffects = potionType.getPotionEffects();

        for(PotionEffect potionEffect : potionEffects) {
            PotionEffect newPotion = new PotionEffect(potionEffect.getType(),
                    (int) (potionEffect.getDuration() * value),
                    potionEffect.getAmplifier());
            player.addPotionEffect(newPotion);
        }
    }

    public void deactivateEffect(Player player, PlayerPrestige prestige, Event event) {

    }
}
