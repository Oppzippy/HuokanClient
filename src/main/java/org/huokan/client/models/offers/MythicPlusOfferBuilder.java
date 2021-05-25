package org.huokan.client.models.offers;

import org.huokan.client.models.wow.Dungeon;

import java.util.List;
import java.util.Optional;

public class MythicPlusOfferBuilder extends OfferBuilder {
    protected int level;
    protected boolean isTimed;
    protected Optional<List<Dungeon>> specificKeys = Optional.empty();
    protected Optional<LootFunnelFilter> lootFunnelFilter = Optional.empty();

    @Override
    public Offer build() {
        return new MythicPlusOffer(this);
    }

    public MythicPlusOfferBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public MythicPlusOfferBuilder setTimed(boolean timed) {
        isTimed = timed;
        return this;
    }

    public MythicPlusOfferBuilder setSpecificKeys(List<Dungeon> specificKeys) {
        this.specificKeys = Optional.ofNullable(specificKeys);
        return this;
    }

    public MythicPlusOfferBuilder notSpecificKey() {
        this.specificKeys = null;
        return this;
    }

    public MythicPlusOfferBuilder setLootFunnelFilter(LootFunnelFilter lootFunnelFilter) {
        this.lootFunnelFilter = Optional.ofNullable(lootFunnelFilter);
        return this;
    }

    public MythicPlusOfferBuilder noLootFunnel() {
        this.lootFunnelFilter = null;
        return this;
    }
}
