package wbe.nikesBlessing.config;

import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import wbe.nikesBlessing.effects.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Config {

    private FileConfiguration config;

    public HashMap<String, Prestige> prestiges = new HashMap<>();

    public Config(FileConfiguration config) {
        this.config = config;

        loadPrestiges();
    }

    private void loadPrestiges() {
        Set<String> configPrestiges = config.getConfigurationSection("Prestiges").getKeys(false);
        for(String configPrestige : configPrestiges) {
            PrimarySkillType skill = PrimarySkillType.valueOf(configPrestige.toUpperCase());
            int maxPrestige = config.getInt("Prestiges." + configPrestige + ".maxPrestige");
            List<PrestigeEffect> effects = loadEffects(configPrestige);
            prestiges.put(configPrestige, new Prestige(configPrestige, skill, maxPrestige, effects));
        }
    }

    private List<PrestigeEffect> loadEffects(String prestige) {
        List<PrestigeEffect> effects = new ArrayList<>();
        Set<String> configEffects = config.getConfigurationSection("Prestiges." + prestige + ".effects").getKeys(false);
        for(String configEffect : configEffects) {
            String lore = config.getString("Prestiges." + prestige + ".effects." + configEffect + ".lore").replace("&", "§");
            double value = config.getDouble("Prestiges." + prestige + ".effects." + configEffect + ".value");
            if(configEffect.toLowerCase().startsWith("addmodifierscale")) {
                effects.add(new addModifierScaleEffect(value, lore, Attribute.valueOf(config.getString("Prestiges." + prestige + ".effects." + configEffect + ".attribute")), prestige));
            } else if(configEffect.toLowerCase().startsWith("addmodifiervalue")) {
                effects.add(new addModifierValueEffect(value, lore, Attribute.valueOf(config.getString("Prestiges." + prestige + ".effects." + configEffect + ".attribute")), prestige));
            } else if(configEffect.toLowerCase().startsWith("fishingspeedmultiplier")) {
                effects.add(new fishingSpeedMultiplierEffect(value, lore));
            } else if(configEffect.toLowerCase().startsWith("saturationmultiplier")) {
                effects.add(new saturationMultiplierEffect(value, lore));
            } else if(configEffect.toLowerCase().startsWith("critmultiplier")) {
                effects.add(new critMultiplierEffect(value, lore));
            } else if(configEffect.toLowerCase().startsWith("arrowspeedmultiplier")) {
                effects.add(new arrowSpeedMultiplierEffect(value, lore));
            } else if(configEffect.toLowerCase().startsWith("doubledamage")) {
                effects.add(new doubleDamageEffect(value, lore));
            } else if(configEffect.toLowerCase().startsWith("underwaterdamagemultiplier")) {
                effects.add(new underwaterDamageMultiplierEffect(value, lore));
            } else if(configEffect.toLowerCase().startsWith("dodge")) {
                effects.add(new dodgeEffect(value, lore));
            } else if(configEffect.toLowerCase().startsWith("potiondurationmultiplier")) {
                effects.add(new potionDurationMultiplierEffect(value, lore));
            } else if(configEffect.toLowerCase().startsWith("durability")) {
                effects.add(new durabilityEffect(value, lore));
            }
        }

        return effects;
    }
}
