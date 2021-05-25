package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;

public class SpecificKeyCode extends MythicPlusCode {
    public SpecificKeyCode() {
        super("key");
        offerMatcher.setSpecificKey(true);
    }
}
