package org.huokan.client.models.offers;

import org.huokan.client.models.wow.Faction;

import java.math.BigDecimal;

public abstract class OfferBuilder {
    public int numRuns = 1;
    public Faction faction;
    protected BigDecimal price;
    protected boolean isPaid;
    protected String notes;

    public abstract Offer build();

    public OfferBuilder setPrice(BigDecimal price) {
        this.price = price;
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
