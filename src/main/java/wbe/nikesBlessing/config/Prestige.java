package wbe.nikesBlessing.config;

import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import wbe.nikesBlessing.effects.PrestigeEffect;

import java.util.List;

public class Prestige {

    private PrimarySkillType skill;

    private int maxPrestige;

    private List<PrestigeEffect> effects;

    public Prestige(PrimarySkillType skill, int maxPrestige, List<PrestigeEffect> effects) {
        this.skill = skill;
        this.maxPrestige = maxPrestige;
        this.effects = effects;
    }

    public PrimarySkillType getSkill() {
        return skill;
    }

    public void setSkill(PrimarySkillType skill) {
        this.skill = skill;
    }

    public int getMaxPrestige() {
        return maxPrestige;
    }

    public void setMaxPrestige(int maxPrestige) {
        this.maxPrestige = maxPrestige;
    }

    public List<PrestigeEffect> getEffects() {
        return effects;
    }

    public void setEffects(List<PrestigeEffect> effects) {
        this.effects = effects;
    }
}
