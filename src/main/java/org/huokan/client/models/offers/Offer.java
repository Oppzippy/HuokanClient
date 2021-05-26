package org.huokan.client.models.offers;

import org.huokan.client.models.wow.Faction;

import java.math.BigDecimal;

public abstract class Offer {
    private int numRuns;
    private BigDecimal priceAdjustment;
    private boolean isPaid;
    private String notes;
    private Faction faction;

    public Offer(OfferBuilder builder) {
        if (builder.faction == null) {
            throw new NullPointerException("Faction may not be null");
        }
        numRuns = builder.numRuns;
        priceAdjustment = builder.priceAdjustment;
        isPaid = builder.isPaid;
        notes = builder.notes;
        faction = builder.faction;
    }

    public int getNumRuns() {
        return numRuns;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String getNotes() {
        return notes;
    }

    public Faction getFaction() {
        return faction;
    }

    public BigDecimal getPriceAdjustment() {
        return priceAdjustment;
    }

    public abstract void accept(OfferVisitor visitor);
}
