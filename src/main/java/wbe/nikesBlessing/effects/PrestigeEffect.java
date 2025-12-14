package wbe.nikesBlessing.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import wbe.nikesBlessing.config.PlayerPrestige;

public abstract class PrestigeEffect {

    protected double value;

    protected String lore;

    public PrestigeEffect(double value, String lore) {
        this.value = value;
        this.lore = lore;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public abstract void activateEffect(Player player, PlayerPrestige prestige, Event event);

    public abstract void deactivateEffect(Player player, PlayerPrestige prestige, Event event);

    public String calculateLore(PlayerPrestige prestige) {
        return lore.replace("%value%", String.format("%.2f", value * prestige.getPrestigeLevel()));
    }
}
