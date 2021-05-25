package org.huokan.client.models.offers.command;

import org.huokan.client.models.offers.MythicPlusOffer;
import org.huokan.client.models.offers.OfferVisitor;
import org.huokan.client.models.offers.codes.CodeFactory;

import javax.inject.Inject;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommandGenerator implements OfferVisitor {
    private Optional<String> command;
    private CodeFactory botCodeFactory;

    @Inject
    public CommandGenerator(CodeFactory botCodeFactory) {
        this.botCodeFactory = botCodeFactory;
    }

    @Override
    public void visit(MythicPlusOffer offer) {
        var sb = new StringBuilder("!offer");
        if (offer.getNumRuns() > 1) {
            sb.append(offer.getNumRuns());
        }
        sb.append("\n");

        var code = botCodeFactory.fromOffer(offer);
        var specificKeyArgs = "";
        if (offer.getSpecificKeys().isPresent()) {
            var keys = offer.getSpecificKeys().get();
            specificKeyArgs = keys.stream().map(d -> d.getShortName()).collect(Collectors.joining("\n"));
        }

        sb.append(offer.isPaid() ? "paid" : "unpaid").append("\n")
                .append(offer.getLevel()).append("\n")
                .append(code.toString()).append("\n")
                .append(0).append("\n") // TODO discount
                .append(offer.getFaction().toString().toLowerCase()).append("\n")
                .append(offer.isTimed() ? "timed" : "untimed").append("\n")
                .append(offer.getLootFunnelFilter().toCommandArgs()).append("\n")
                .append(offer.getNotes().replace("\n", " ")).append("\n")
                .append(specificKeyArgs).append("\n");
        command = Optional.ofNullable(sb.toString());
    }

    public String getCommand() {
        return command.get();
    }
}
