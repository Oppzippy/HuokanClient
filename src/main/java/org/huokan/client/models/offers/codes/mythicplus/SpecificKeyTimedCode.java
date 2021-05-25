package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;

public class SpecificKeyTimedCode extends MythicPlusCode {
    public SpecificKeyTimedCode() {
        super("keytimed");
        offerMatcher.setSpecificKey(true).setTimed(true);
    }
}
