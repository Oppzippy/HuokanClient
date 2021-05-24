package org.huokan.client.models.offers;

import org.huokan.client.models.wow.Dungeon;
import org.huokan.client.models.wow.WeaponType;

import java.util.List;
import java.util.stream.Collectors;

public class MythicPlusOffer extends Offer {
    private int level;
    private boolean isTimed;
    private List<Dungeon> specificKeys;
    private LootFunnelFilter lootFunnelFilter;

    protected MythicPlusOffer(MythicPlusOfferBuilder builder) {
        super(builder);
        level = builder.level;
        isTimed = builder.isTimed;
        specificKeys = builder.specificKeys;
        lootFunnelFilter = builder.lootFunnelFilter;
    }

    @Override
    public String getCommand() {
        boolean hasLootFunnelFilter = lootFunnelFilter != null;
        var sb = new StringBuilder("!offer");
        if (getNumRuns() > 1) {
            sb.append(getNumRuns());
        }
        sb.append(isPaid() ? "paid" : "unpaid").append("\n")
                .append(getLevel()).append("\n")
                .append("TODO type").append("\n")
                .append("discount?").append("\n")
                .append(getFaction().toString().toLowerCase()).append("\n")
                .append(isTimed ? "timed" : "untimed").append("\n")
                .append(lootFunnelFilter.toCommandArgs()).append("\n")
                .append(getNotes())
                .append(getSpecificKeys().stream().map(d -> d.getShortName()).collect(Collectors.joining("\n")));
    }

    public int getLevel() {
        return level;
    }

    public boolean isTimed() {
        return isTimed;
    }

    public boolean isSpecificKey() {
        return specificKeys != null;
    }

    public List<Dungeon> getSpecificKeys() {
        return specificKeys;
    }

    public LootFunnelFilter getLootFunnelFilter() {
        return lootFunnelFilter;
    }
}
