package org.huokan.client.models.offers.codes.mythicplus;

import org.huokan.client.models.offers.codes.MythicPlusCode;
import org.huokan.client.models.offers.codes.offermatcher.MythicPlusOfferMatcher;

import javax.inject.Inject;

public class BaseCode extends MythicPlusCode {
    @Inject
    public BaseCode(MythicPlusOfferMatcher offerMatcher) {
        super("base", offerMatcher);
    }
}
