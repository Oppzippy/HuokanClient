package org.huokan.client.models.offers;

import org.huokan.client.models.wow.Dungeon;
import org.huokan.client.models.wow.Faction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

public class MythicPlusOfferBuilderTests {
    @Test
    public void failsWithNoValues() {
        Assertions.assertThrows(NullPointerException.class, () -> new MythicPlusOfferBuilder().build());
    }

    @Test
    public void setsKeyLevel() {
        var offer = (MythicPlusOffer) basicOfferBuilder().setLevel(15).build();
        Assertions.assertEquals(15, offer.getLevel());
    }

    @Test
    public void setsTimed() {
        var offer = (MythicPlusOffer) basicOfferBuilder().setTimed(true).build();
        Assertions.assertEquals(true, offer.isTimed());
    }

    @Test
    public void setsSpecificKeys() {
        var specificKeys = Set.of(Dungeon.DE_OTHER_SIDE);
        var offer = (MythicPlusOffer) basicOfferBuilder().setSpecificDungeons(specificKeys).build();
        Assertions.assertEquals(true, offer.isSpecificDungeon());
        Assertions.assertEquals(specificKeys, offer.getSpecificDungeons().get());
    }

    @Test
    public void setsLootFunnelFilter() {
        var lootFunnelFilter = LootFunnelFilter.builder().build();
        var offer = (MythicPlusOffer) basicOfferBuilder().setLootFunnelFilter(lootFunnelFilter).build();
        Assertions.assertEquals(lootFunnelFilter, offer.getLootFunnelFilter());
    }

    @Test
    public void setsNumRuns() {
        var offer = (MythicPlusOffer) basicOfferBuilder().setNumRuns(4).build();
        Assertions.assertEquals(4, offer.getNumRuns());
    }

    @Test
    public void setsNotes() {
        var offer = (MythicPlusOffer) basicOfferBuilder().setNotes("test").build();
        Assertions.assertEquals("test", offer.getNotes());
    }

    @Test
    public void setsPaid() {
        var offer = (MythicPlusOffer) basicOfferBuilder().setPaid(true).build();
        Assertions.assertEquals(true, offer.isPaid());
    }

    @Test
    public void setsPriceAdjustment() {
        var offer = (MythicPlusOffer) basicOfferBuilder().setPriceAdjustment(new BigDecimal(10)).build();
        Assertions.assertEquals(new BigDecimal(10), offer.getPriceAdjustment());
    }

    @Test
    public void setsFaction() {
        var offer = (MythicPlusOffer) basicOfferBuilder().setFaction(Faction.HORDE).build();
        Assertions.assertEquals(Faction.HORDE, offer.getFaction());
    }

    private MythicPlusOfferBuilder basicOfferBuilder() {
        var offerBuilder = new MythicPlusOfferBuilder();
        offerBuilder.setLevel(2).setFaction(Faction.ALLIANCE);
        return offerBuilder;
    }
}
