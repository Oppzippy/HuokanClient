package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;
import org.huokan.client.models.offers.codes.offermatcher.MythicPlusOfferMatcher;

import javax.inject.Inject;

public class SpecificKeyTimedCode extends MythicPlusCode {
    @Inject
    public SpecificKeyTimedCode(MythicPlusOfferMatcher offerMatcher) {
        super("keytimed", offerMatcher);
        offerMatcher.setSpecificKey(true).setTimed(true);
    }
}
