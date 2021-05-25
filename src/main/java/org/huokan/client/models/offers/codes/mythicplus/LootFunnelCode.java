package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.MythicPlusOffer;
import org.huokan.client.models.offers.codes.MythicPlusCode;

public class LootFunnelCode extends MythicPlusCode {
    public LootFunnelCode() {
        super("lootfunnel");
        offerMatcher.setLootFunnel(true);
    }
}
