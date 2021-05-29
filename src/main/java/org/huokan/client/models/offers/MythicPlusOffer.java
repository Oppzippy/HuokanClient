package org.huokan.client.models.offers;

import org.huokan.client.models.wow.Dungeon;

import java.util.List;
import java.util.Optional;

public class MythicPlusOffer extends Offer {
    private int level;
    private boolean isTimed;
    private Optional<List<Dungeon>> specificDungeons;
    private LootFunnelFilter lootFunnelFilter;

    protected MythicPlusOffer(MythicPlusOfferBuilder builder) {
        super(builder);
        level = builder.level.orElseThrow();
        isTimed = builder.isTimed;
        specificDungeons = builder.specificDungeons;
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

    public boolean isSpecificDungeon() {
        return specificDungeons.isPresent();
    }

    public Optional<List<Dungeon>> getSpecificDungeons() {
        return specificDungeons;
    }

    public LootFunnelFilter getLootFunnelFilter() {
        return lootFunnelFilter;
    }

    public boolean isLootFunnel() {
        return !lootFunnelFilter.isEmpty();
    }

    @Override
    public void accept(OfferVisitor visitor) {
        visitor.visit(this);
    }
}
