package wbe.nikesBlessing.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.FoodComponent;
import wbe.nikesBlessing.config.PlayerPrestige;

public class saturationMultiplierEffect extends PrestigeEffect {

    public saturationMultiplierEffect(double value, String lore) {
        super(value, lore);
    }

    public void activateEffect(Player player, PlayerPrestige prestige, Event event) {
        if(!(event instanceof FoodLevelChangeEvent foodEvent)) {
            return;
        }

        if(prestige.getPrestigeLevel() < 1) {
            return;
        }

        double value = this.value * prestige.getPrestigeLevel() + 1;

        ItemStack food = foodEvent.getItem();
        ItemMeta foodMeta = food.getItemMeta();
        FoodComponent foodComponent = foodMeta.getFood();
        foodComponent.setSaturation((float) (foodComponent.getSaturation() * value));
        foodMeta.setFood(foodComponent);
        food.setItemMeta(foodMeta);
    }

    public void deactivateEffect(Player player, PlayerPrestige prestige, Event event) {

    }
}
