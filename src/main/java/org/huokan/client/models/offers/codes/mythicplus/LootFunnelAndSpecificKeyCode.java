package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;

public class LootFunnelAndSpecificKeyCode extends MythicPlusCode {
    public LootFunnelAndSpecificKeyCode() {
        super("loot&key");
        offerMatcher.setLootFunnel(true).setSpecificKey(true);
    }
}
