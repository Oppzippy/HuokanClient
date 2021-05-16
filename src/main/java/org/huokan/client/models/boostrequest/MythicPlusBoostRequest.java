package org.huokan.client.models.boostrequest;

import java.math.BigDecimal;

public class MythicPlusBoostRequest implements BoostRequest {
    private Faction faction;
    private int level;
    private ArmorType armorStack;
    private Dungeon specificDungeon;
    private boolean isTimed;

    public MythicPlusBoostRequest(Faction faction, int level) {
        this.faction = faction;
        this.level = level;
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }

    @Override
    public void submit() {

    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("(").append(faction).append(")");
        text.append(" ").append(isTimed ? "Timed" : "Untimed");
        text.append(" +").append(level);
        if (specificDungeon != null) {
            text.append(" ").append(specificDungeon);
        }
        if (armorStack != null) {
            text.append(" ").append(armorStack).append(" stack");
        }

        return null;
    }
}
