package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;

public class LootFunnelAndSpecificKeyTimedCode extends MythicPlusCode {
    public LootFunnelAndSpecificKeyTimedCode() {
        super("loot&keytimed");
        offerMatcher.setLootFunnel(true).setSpecificKey(true).setTimed(true);
    }
}
