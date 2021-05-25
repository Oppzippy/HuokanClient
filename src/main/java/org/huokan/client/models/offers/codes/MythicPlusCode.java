package org.huokan.client.models.offers.codes;

import org.huokan.client.models.offers.MythicPlusOffer;
import org.huokan.client.models.offers.codes.offermatcher.MythicPlusOfferMatcher;

import javax.inject.Inject;

public abstract class MythicPlusCode extends Code {
    @Inject
    protected MythicPlusOfferMatcher offerMatcher;

    public MythicPlusCode(String code) {
        super(code);
    }

    public boolean matches(MythicPlusOffer offer) {
        return offerMatcher.matches(offer);
    }
}
