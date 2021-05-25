package org.huokan.client.models.offers;

import org.huokan.client.models.wow.Dungeon;

import java.util.List;
import java.util.Optional;

public class MythicPlusOffer extends Offer {
    private int level;
    private boolean isTimed;
    private Optional<List<Dungeon>> specificKeys;
    private LootFunnelFilter lootFunnelFilter;

    protected MythicPlusOffer(MythicPlusOfferBuilder builder) {
        super(builder);
        level = builder.level;
        isTimed = builder.isTimed;
        specificKeys = builder.specificKeys;
        if (builder.lootFunnelFilter.isPresent()) {
            lootFunnelFilter = builder.lootFunnelFilter.get();
        } else {
            lootFunnelFilter = LootFunnelFilter.empty();
        }
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

    public Optional<List<Dungeon>> getSpecificKeys() {
        return specificKeys;
    }

    public LootFunnelFilter getLootFunnelFilter() {
        return lootFunnelFilter;
    }

    public boolean isLootFunnel() {
        return lootFunnelFilter.isEmpty();
    }

    @Override
    public void accept(OfferVisitor visitor) {
        visitor.visit(this);
    }
}
