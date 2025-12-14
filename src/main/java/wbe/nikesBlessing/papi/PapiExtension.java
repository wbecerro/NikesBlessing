package wbe.nikesBlessing.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import wbe.nikesBlessing.NikesBlessing;

public class PapiExtension extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "wbe";
    }

    @Override
    public String getIdentifier() {
        return "NikesBlessing";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.contains("prestigelevel")) {
            String skillName = params.replace("prestigelevel", "");
            return String.valueOf(NikesBlessing.utilities.searchPrestige(player.getPlayer(), skillName).getPrestigeLevel());
        } else if(params.contains("prestigemaxlevel")) {
            String skillName = params.replace("prestigemaxlevel", "");
            return String.valueOf(NikesBlessing.config.prestiges.get(skillName).getMaxPrestige());
        }

        return null;
    }
}
