package wbe.nikesBlessing.effects;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.EquipmentSlotGroup;
import wbe.nikesBlessing.NikesBlessing;
import wbe.nikesBlessing.config.PlayerPrestige;

public class addModifierScaleEffect extends PrestigeEffect {

    private Attribute attribute;

    private NamespacedKey attributeKey;

    public addModifierScaleEffect(double value, String lore, Attribute attribute, String prestige) {
        super(value, lore);
        this.attribute = attribute;
        attributeKey = new NamespacedKey(NikesBlessing.getInstance(), "prestige" + attribute.getKey().getKey() + prestige);
    }

    public void activateEffect(Player player, PlayerPrestige prestige, Event event) {
        if(event != null) {
            return;
        }

        if(prestige.getPrestigeLevel() < 1) {
            return;
        }

        double attributeValue = value * prestige.getPrestigeLevel();
        AttributeModifier attributeModifier = new AttributeModifier(attributeKey, attributeValue,
                AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.ANY);

        AttributeModifier oldAttribute = NikesBlessing.utilities.searchModifier(player.getAttribute(attribute).getModifiers(), attributeKey);
        if(oldAttribute != null) {
            return;
        }
        player.getAttribute(attribute).addModifier(attributeModifier);
    }

    public void deactivateEffect(Player player, PlayerPrestige prestige, Event event) {
        if(event != null) {
            return;
        }

        if(prestige.getPrestigeLevel() < 1) {
            return;
        }

        AttributeModifier attributeModifier = NikesBlessing.utilities.searchModifier(player.getAttribute(attribute).getModifiers(), attributeKey);
        if(attributeModifier == null) {
            return;
        }

        player.getAttribute(attribute).removeModifier(attributeModifier);
    }

    @Override
    public String calculateLore(PlayerPrestige prestige) {
        return lore.replace("%value%", String.format("%.2f", value * prestige.getPrestigeLevel() * 100));
    }
}
