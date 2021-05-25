package org.huokan.client.models.offers.codes;

import org.huokan.client.models.offers.MythicPlusOffer;
import org.huokan.client.models.offers.Offer;

import javax.inject.Inject;
import java.util.Set;

public class CodeFactoryImpl implements CodeFactory {
    private Set<MythicPlusCode> mythicPlusCodes;

    @Inject
    public CodeFactoryImpl(Set<MythicPlusCode> mythicPlusCodes) {
        this.mythicPlusCodes = mythicPlusCodes;
    }

    @Override
    public Code fromOffer(Offer offer) {
        if (offer instanceof MythicPlusOffer mythicPlusOffer) {
            var code = mythicPlusCodes.stream().filter(c -> c.matches(mythicPlusOffer)).findFirst();
            if (code.isPresent()) {
                return code.get();
            }
        }
        return null;
    }
}
