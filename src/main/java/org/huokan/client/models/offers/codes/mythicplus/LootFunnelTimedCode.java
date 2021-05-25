package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;
import org.huokan.client.models.offers.codes.offermatcher.MythicPlusOfferMatcher;

import javax.inject.Inject;

public class LootFunnelTimedCode extends MythicPlusCode {
    @Inject
    public LootFunnelTimedCode(MythicPlusOfferMatcher offerMatcher) {
        super("lootfunneltimed", offerMatcher);
        offerMatcher.setLootFunnel(true).setTimed(true);
    }
}
