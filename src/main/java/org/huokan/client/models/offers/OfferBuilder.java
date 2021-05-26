package org.huokan.client.models.offers;

import org.huokan.client.models.wow.Faction;

import java.math.BigDecimal;

public abstract class OfferBuilder {
    public int numRuns = 1;
    public Faction faction;
    protected BigDecimal priceAdjustment = BigDecimal.ZERO;
    protected boolean isPaid = false;
    protected String notes = "";

    public abstract Offer build();

    public OfferBuilder setPriceAdjustment(BigDecimal priceAdjustment) {
        this.priceAdjustment = priceAdjustment;
        return this;
    }

    public OfferBuilder setPaid(boolean paid) {
        isPaid = paid;
        return this;
    }

    public OfferBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public OfferBuilder setNumRuns(int numRuns) {
        this.numRuns = numRuns;
        return this;
    }

    public OfferBuilder setFaction(Faction faction) {
        this.faction = faction;
        return this;
    }
}
