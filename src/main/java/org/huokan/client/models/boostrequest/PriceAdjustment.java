package org.huokan.client.models.boostrequest;

import java.math.BigDecimal;

public class PriceAdjustment {
    private PriceAdjustmentType type;
    private BigDecimal amount;

    public PriceAdjustment(PriceAdjustmentType type, BigDecimal amount) {
        if (type == null) {
            throw new NullPointerException("Type must not be null");
        }
        if (amount == null) {
            throw new NullPointerException("Amount must not be null");
        }
        this.type = type;
        this.amount = amount;
    }

    public BigDecimal getPriceAddition() {
        if (type == PriceAdjustmentType.DISCOUNT) {
            return amount.negate();
        }
        return amount;
    }
}
