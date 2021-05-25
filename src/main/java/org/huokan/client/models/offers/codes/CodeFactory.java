package org.huokan.client.models.offers.codes;

import org.huokan.client.models.offers.Offer;

public interface CodeFactory {
    public Code fromOffer(Offer offer);
}
