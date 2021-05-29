package org.huokan.client.models.offers;

import org.huokan.client.models.wow.Dungeon;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class MythicPlusOfferBuilder extends OfferBuilder {
    protected OptionalInt level = OptionalInt.empty();
    protected boolean isTimed = false;
    protected Optional<List<Dungeon>> specificDungeons = Optional.empty();
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

    public MythicPlusOfferBuilder setSpecificDungeons(List<Dungeon> specificDungeons) {
        this.specificDungeons = Optional.ofNullable(specificDungeons);
        return this;
    }

    public MythicPlusOfferBuilder notSpecificDungeon() {
        this.specificDungeons = null;
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
