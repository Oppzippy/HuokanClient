package org.huokan.client.models.offers.codes.offermatcher;

import org.huokan.client.models.offers.LootFunnelFilter;
import org.huokan.client.models.offers.MythicPlusOffer;
import org.huokan.client.models.offers.MythicPlusOfferBuilder;
import org.huokan.client.models.wow.ArmorType;
import org.huokan.client.models.wow.Dungeon;
import org.huokan.client.models.wow.Faction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class MythicPlusOfferMatcherTests {
    @Test
    public void isTimed() {
        var offerMatcher = new MythicPlusOfferMatcher();
        offerMatcher.setTimed(true);

        var offerBuilder = new MythicPlusOfferBuilder();
        offerBuilder.setLevel(2)
                .setTimed(true)
                .setFaction(Faction.ALLIANCE);

        Assertions.assertEquals(true, offerMatcher.matches((MythicPlusOffer) offerBuilder.build()));
        Assertions.assertEquals(false, offerMatcher.matches((MythicPlusOffer) offerBuilder.setTimed(false).build()));
    }

    @Test
    public void isLootFunnel() {
        var offerMatcher = new MythicPlusOfferMatcher();
        offerMatcher.setLootFunnel(true);

        var offerBuilder = new MythicPlusOfferBuilder();
        offerBuilder.setLevel(2)
                .setLootFunnelFilter(LootFunnelFilter.builder().setArmorType(ArmorType.PLATE).build())
                .setFaction(Faction.ALLIANCE);

        Assertions.assertEquals(true, offerMatcher.matches((MythicPlusOffer) offerBuilder.build()));
        Assertions.assertEquals(false, offerMatcher.matches((MythicPlusOffer) offerBuilder.setLootFunnelFilter(null).build()));
    }

    @Test
    public void isSpecificKey() {
        var offerMatcher = new MythicPlusOfferMatcher();
        offerMatcher.setSpecificKey(true);

        var offerBuilder = new MythicPlusOfferBuilder();
        offerBuilder.setLevel(2)
                .setSpecificKeys(Set.of(Dungeon.MISTS_OF_TIRNA_SCITHE))
                .setFaction(Faction.ALLIANCE);

        Assertions.assertEquals(true, offerMatcher.matches((MythicPlusOffer) offerBuilder.build()));
        Assertions.assertEquals(false, offerMatcher.matches((MythicPlusOffer) offerBuilder.setSpecificKeys(null).build()));
    }
}
