package org.huokan.client.models.offers;

import org.huokan.client.models.wow.Dungeon;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

public class MythicPlusOfferBuilder extends OfferBuilder {
    protected OptionalInt level = OptionalInt.empty();
    protected boolean isTimed = false;
    protected Optional<Set<Dungeon>> specificKeys = Optional.empty();
    protected Optional<LootFunnelFilter> lootFunnelFilter = Optional.empty();

    @Override
    public Offer build() {
        return new MythicPlusOffer(this);
    }

    public MythicPlusOfferBuilder setLevel(int level) {
        this.level = OptionalInt.of(level);
        return this;
    }

    public MythicPlusOfferBuilder setTimed(boolean timed) {
        isTimed = timed;
        return this;
    }

    public MythicPlusOfferBuilder setSpecificKeys(Set<Dungeon> specificKeys) {
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
