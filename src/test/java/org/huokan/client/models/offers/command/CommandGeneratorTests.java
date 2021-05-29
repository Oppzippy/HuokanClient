package org.huokan.client.models.offers.command;

import org.huokan.client.models.offers.MythicPlusOfferBuilder;
import org.huokan.client.models.offers.codes.CodeFactoryImpl;
import org.huokan.client.models.offers.codes.MythicPlusCode;
import org.huokan.client.models.offers.codes.mythicplus.BaseCode;
import org.huokan.client.models.offers.codes.offermatcher.MythicPlusOfferMatcher;
import org.huokan.client.models.wow.Faction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class CommandGeneratorTests {

    @Test
    public void testBase() {
        var commandGenerator = commandGeneratorWithBaseCode();

        var offer = basicOfferBuilder()
                .build();

        offer.accept(commandGenerator);
        Assertions.assertEquals("""
!offer
@YOURNAME TODO
unpaid
2
base
0
alliance
untimed
any
any
any
any""", commandGenerator.getCommand());
    }

    private CommandGenerator commandGeneratorWithBaseCode() {
        Set<MythicPlusCode> codes = Set.of(
                new BaseCode(new MythicPlusOfferMatcher())
        );
        return new CommandGenerator(new CodeFactoryImpl(codes));
    }

    private MythicPlusOfferBuilder basicOfferBuilder() {
        var offerBuilder = new MythicPlusOfferBuilder();
        offerBuilder.setLevel(2).setFaction(Faction.ALLIANCE);
        return offerBuilder;
    }
}
