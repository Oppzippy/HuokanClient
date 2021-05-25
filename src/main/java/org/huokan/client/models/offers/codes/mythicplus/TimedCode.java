package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;
import org.huokan.client.models.offers.codes.offermatcher.MythicPlusOfferMatcher;

import javax.inject.Inject;

public class TimedCode extends MythicPlusCode {
    @Inject
    public TimedCode(MythicPlusOfferMatcher offerMatcher) {
        super("basetimed", offerMatcher);
        offerMatcher.setTimed(true);
    }
}
