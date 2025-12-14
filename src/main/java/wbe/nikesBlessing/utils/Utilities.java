package wbe.nikesBlessing.utils;

import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.locale.LocaleLoader;
import com.gmail.nossr50.skills.SkillManager;
import com.gmail.nossr50.util.player.UserManager;
import com.gmail.nossr50.util.text.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import wbe.nikesBlessing.NikesBlessing;
import wbe.nikesBlessing.config.PlayerPrestige;
import wbe.nikesBlessing.config.Prestige;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Utilities {

    public AttributeModifier searchModifier(Collection<AttributeModifier> modifiers, NamespacedKey key) {
        for(AttributeModifier modifier : modifiers) {
            if(modifier.getKey().equals(key)) {
                return modifier;
            }
        }

        return null;
    }

    public void addPlayerToData(Player player) {
        List<PlayerPrestige> prestiges = new ArrayList<>();
        for(Prestige prestige : NikesBlessing.config.prestiges.values()) {
            PlayerPrestige newPrestige = new PlayerPrestige(prestige, player, 0);
            prestiges.add(newPrestige);
        }

        NikesBlessing.playerPrestiges.put(player, prestiges);
    }

    public void savePlayerData(Player player) {
        try {
            File playerFile = new File(
                    NikesBlessing.getInstance().getDataFolder(), "saves/" + player.getUniqueId() + ".yml"
            );
            boolean fileCreated = playerFile.createNewFile();
            FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
            List<PlayerPrestige> prestiges = NikesBlessing.playerPrestiges.get(player);

            prestiges.forEach(prestige -> {
                String prestigeSkill = prestige.getPrestige().getId();
                playerConfig.set("prestiges." + prestigeSkill + ".level", prestige.getPrestigeLevel());
            });

            playerConfig.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while saving the " + player.getName() + " data.");
        }
    }

    public void loadPlayerData(Player player) {
        File playerFile = new File(
                NikesBlessing.getInstance().getDataFolder(), "saves/" + player.getUniqueId() + ".yml"
        );
        List<PlayerPrestige> prestiges = new ArrayList<>();
        if(!playerFile.exists()) {
            addPlayerToData(player);
            return;
        }

        FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
        if(playerConfig.getKeys(false).isEmpty()) {
            NikesBlessing.playerPrestiges.put(player, prestiges);
            return;
        }

        Set<String> skillIds = playerConfig.getConfigurationSection("prestiges").getKeys(false);
        for(String skillId : skillIds) {
            Prestige prestige = NikesBlessing.config.prestiges.get(skillId);
            if(prestige == null) {
                continue;
            }

            int level = playerConfig.getInt("prestiges." + skillId + ".level");
            PlayerPrestige playerPrestige = new PlayerPrestige(prestige, player, level);
            prestiges.add(playerPrestige);
        }

        NikesBlessing.playerPrestiges.put(player, prestiges);
    }

    public PlayerPrestige searchPrestige(Player player, String id) {
        for(PlayerPrestige prestige : NikesBlessing.playerPrestiges.get(player)) {
            if(prestige.getPrestige().getId().equalsIgnoreCase(id)) {
                return prestige;
            }
        }

        return null;
    }

    public void levelUpPrestige(Player player, PlayerPrestige prestige) {
        McMMOPlayer mcMMOPlayer = UserManager.getPlayer(player);
        if(mcMMOPlayer == null) {
            return;
        }

        int skillLevel = mcMMOPlayer.getSkillLevel(prestige.getPrestige().getSkill());
        if(skillLevel < 1000) {
            player.sendMessage(NikesBlessing.messages.notEnoughLevels);
            return;
        }

        if(!prestige.levelUp()) {
            player.sendMessage(NikesBlessing.messages.maxPrestigeReached);
            return;
        }

        mcMMOPlayer.modifySkill(prestige.getPrestige().getSkill(), skillLevel - 1000);
        Bukkit.broadcastMessage(NikesBlessing.messages.prestigeBroadcast
                .replace("%player%", player.getName())
                .replace("%level%", String.valueOf(prestige.getPrestigeLevel()))
                .replace("%skill%", LocaleLoader.getString(
                        StringUtils.getCapitalized(prestige.getPrestige().getId()) + ".SkillName")));
    }

    public void levelDownPrestige(Player player, PlayerPrestige prestige) {
        McMMOPlayer mcMMOPlayer = UserManager.getPlayer(player);
        if(mcMMOPlayer == null) {
            return;
        }

        prestige.levelDown();
    }

    public void resetPrestige(Player player, PlayerPrestige prestige) {
        McMMOPlayer mcMMOPlayer = UserManager.getPlayer(player);
        if(mcMMOPlayer == null) {
            return;
        }

        prestige.reset();
    }
}
