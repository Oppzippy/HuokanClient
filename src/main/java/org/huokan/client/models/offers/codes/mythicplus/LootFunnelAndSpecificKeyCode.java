package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;
import org.huokan.client.models.offers.codes.offermatcher.MythicPlusOfferMatcher;

import javax.inject.Inject;

public class LootFunnelAndSpecificKeyCode extends MythicPlusCode {
    @Inject
    public LootFunnelAndSpecificKeyCode(MythicPlusOfferMatcher offerMatcher) {
        super("loot&key", offerMatcher);
        offerMatcher.setLootFunnel(true).setSpecificKey(true);
    }
}
