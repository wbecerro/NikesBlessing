package wbe.nikesBlessing.config;

import org.bukkit.entity.Player;
import wbe.nikesBlessing.effects.PrestigeEffect;

public class PlayerPrestige {

    private Prestige prestige;

    private Player player;

    private int prestigeLevel;

    public PlayerPrestige(Prestige prestige, Player player, int prestigeLevel) {
        this.prestige = prestige;
        this.player = player;
        this.prestigeLevel = prestigeLevel;
    }

    public Prestige getPrestige() {
        return prestige;
    }

    public void setPrestige(Prestige prestige) {
        this.prestige = prestige;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPrestigeLevel() {
        return prestigeLevel;
    }

    public void setPrestigeLevel(int prestigeLevel) {
        this.prestigeLevel = prestigeLevel;
    }

    public void activate() {
        for(PrestigeEffect effect : prestige.getEffects()) {
            effect.activateEffect(player, this, null);
        }
    }

    public void deactivate() {
        for(PrestigeEffect effect : prestige.getEffects()) {
            effect.deactivateEffect(player, this, null);
        }
    }

    public boolean levelUp() {
        int newLevel = prestigeLevel + 1;
        if(newLevel > prestige.getMaxPrestige()) {
            return false;
        }

        prestigeLevel = newLevel;

        deactivate();
        activate();

        return true;
    }

    public boolean levelDown() {
        int newLevel = prestigeLevel - 1;
        if(newLevel < 0) {
            newLevel = 0;
        }

        prestigeLevel = newLevel;

        deactivate();
        activate();

        return true;
    }

    public void reset() {
        prestigeLevel = 0;

        deactivate();
        activate();
    }
}
