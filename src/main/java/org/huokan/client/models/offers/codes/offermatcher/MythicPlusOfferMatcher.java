package org.huokan.client.models.offers.codes.offermatcher;

import org.huokan.client.models.offers.MythicPlusOffer;

public class MythicPlusOfferMatcher {
    private boolean isTimed = false;
    private boolean isLootFunnel = false;
    private boolean isSpecificKey = false;

    public boolean matches(MythicPlusOffer offer) {
        return offer.isTimed() == isTimed
                && offer.isLootFunnel() == isLootFunnel
                && offer.isSpecificKey() == isSpecificKey;
    }

    public MythicPlusOfferMatcher setTimed(boolean timed) {
        isTimed = timed;
        return this;
    }

    public MythicPlusOfferMatcher setLootFunnel(boolean lootFunnel) {
        isLootFunnel = lootFunnel;
        return this;
    }

    public MythicPlusOfferMatcher setSpecificKey(boolean specificKey) {
        isSpecificKey = specificKey;
        return this;
    }
}
