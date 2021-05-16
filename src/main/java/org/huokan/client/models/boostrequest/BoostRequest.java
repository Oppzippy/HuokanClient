package org.huokan.client.models.boostrequest;

import java.math.BigDecimal;

public interface BoostRequest {
    public BigDecimal getPrice();

    public void submit();
}
