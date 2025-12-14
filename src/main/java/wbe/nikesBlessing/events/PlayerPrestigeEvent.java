package wbe.nikesBlessing.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import wbe.nikesBlessing.config.Prestige;

public class PlayerPrestigeEvent extends Event implements Cancellable {

    private Player player;

    private Prestige prestige;

    private int changeAmount;

    private boolean isCancelled = false;

    private static final HandlerList handlers = new HandlerList();

    public PlayerPrestigeEvent(Player player, Prestige prestige, int changeAmount) {
        this.player = player;
        this.prestige = prestige;
        this.changeAmount = changeAmount;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Prestige getPrestige() {
        return prestige;
    }

    public void setPrestige(Prestige prestige) {
        this.prestige = prestige;
    }

    public int getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(int changeAmount) {
        this.changeAmount = changeAmount;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
}
