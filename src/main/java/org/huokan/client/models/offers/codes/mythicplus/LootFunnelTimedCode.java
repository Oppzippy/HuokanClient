package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;

public class LootFunnelTimedCode extends MythicPlusCode {
    public LootFunnelTimedCode() {
        super("lootfunneltimed");
        offerMatcher.setLootFunnel(true).setTimed(true);
    }
}
