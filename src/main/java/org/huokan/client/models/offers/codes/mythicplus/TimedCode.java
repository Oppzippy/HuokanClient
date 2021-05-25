package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;

public class TimedCode extends MythicPlusCode {
    public TimedCode() {
        super("basetimed");
        offerMatcher.setTimed(true);
    }
}
