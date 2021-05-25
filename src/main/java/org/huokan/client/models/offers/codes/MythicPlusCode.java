package org.huokan.client.models.offers.codes;

import com.google.inject.assistedinject.Assisted;
import org.huokan.client.models.offers.MythicPlusOffer;
import org.huokan.client.models.offers.codes.offermatcher.MythicPlusOfferMatcher;

import javax.inject.Inject;

public abstract class MythicPlusCode extends Code {
    protected MythicPlusOfferMatcher offerMatcher;

    @Inject
    public MythicPlusCode(@Assisted String code, MythicPlusOfferMatcher offerMatcher) {
        super(code);
        this.offerMatcher = offerMatcher;
    }

    public boolean matches(MythicPlusOffer offer) {
        return offerMatcher.matches(offer);
    }
}
