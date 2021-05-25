package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.MythicPlusOffer;
import org.huokan.client.models.offers.codes.MythicPlusCode;
import org.huokan.client.models.offers.codes.offermatcher.MythicPlusOfferMatcher;

import javax.inject.Inject;

public class LootFunnelCode extends MythicPlusCode {
    @Inject
    public LootFunnelCode(MythicPlusOfferMatcher offerMatcher) {
        super("lootfunnel", offerMatcher);
        offerMatcher.setLootFunnel(true);
    }
}
