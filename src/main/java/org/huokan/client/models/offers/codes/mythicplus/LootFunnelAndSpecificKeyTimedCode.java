package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;
import org.huokan.client.models.offers.codes.offermatcher.MythicPlusOfferMatcher;

import javax.inject.Inject;

public class LootFunnelAndSpecificKeyTimedCode extends MythicPlusCode {
    @Inject
    public LootFunnelAndSpecificKeyTimedCode(MythicPlusOfferMatcher offerMatcher) {
        super("loot&keytimed", offerMatcher);
        offerMatcher.setLootFunnel(true).setSpecificKey(true).setTimed(true);
    }
}
