package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;
import org.huokan.client.models.offers.codes.offermatcher.MythicPlusOfferMatcher;

import javax.inject.Inject;

public class SpecificKeyCode extends MythicPlusCode {
    @Inject
    public SpecificKeyCode(MythicPlusOfferMatcher offerMatcher) {
        super("key", offerMatcher);
        offerMatcher.setSpecificKey(true);
    }
}
