package org.huokan.client.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.huokan.client.FXMLCache;
import org.huokan.client.models.boostrequest.BoostType;
import org.huokan.client.models.localization.StringConverterFactory;
import org.huokan.client.models.offers.Offer;
import org.huokan.client.models.offers.OfferBuilder;
import org.huokan.client.models.offers.command.CommandGenerator;
import org.huokan.client.models.wow.Faction;
import org.huokan.client.views.ViewFile;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class OfferFormController implements Initializable {
    @FXML
    private CheckBox advertiserPaidCheckBox;
    @FXML
    private TextField priceField;
    @FXML
    private ComboBox<Faction> factionSelection;
    @FXML
    private ComboBox<BoostType> offerTypeSelection;
    @FXML
    private ContentSwitcherController offerTypeSwitcherController;
    @FXML
    private TextField notesField;
    @FXML
    private TextArea commandText;

    @Inject
    private StringConverterFactory stringConverterFactory;
    @Inject
    private FXMLCache fxmlCache;
    @Inject
    private Provider<CommandGenerator> commandGeneratorProvider;

    private MythicPlusOfferFormController mythicPlusController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        factionSelection.setConverter(stringConverterFactory.create());
        offerTypeSelection.setConverter(stringConverterFactory.create());
        try {
            updateOfferType();
        } catch (IOException e) {
            // TODO check if this shows the IOException's stack trace
            throw new RuntimeException(e);
        }
        advertiserPaidCheckBox.addEventHandler(ActionEvent.ACTION, this::updateCommand);
        priceField.addEventHandler(ActionEvent.ACTION, this::updateCommand);
        offerTypeSelection.addEventHandler(ActionEvent.ACTION, this::updateCommand);
        notesField.addEventHandler(ActionEvent.ACTION, this::updateCommand);
    }

    @FXML
    private void updateOfferType() throws IOException {
        switch(offerTypeSelection.getValue()) {
            case MYTHIC_PLUS:
                offerTypeSwitcherController.setContent(fxmlCache.getView(ViewFile.MYTHIC_PLUS_OFFER_FORM));
                mythicPlusController = (MythicPlusOfferFormController) fxmlCache.getController(ViewFile.MYTHIC_PLUS_OFFER_FORM);
                break;
            default:
                offerTypeSwitcherController.clearContent();
        }
    }

    public void updateCommand(Event e) {
        var command = getCommand();
        if (command.isEmpty()) {
            commandText.clear();
            return;
        }
        commandText.setText(command.get());
    }

    private Optional<String> getCommand() {
        var offer = offer();
        if (offer == null) {
            return Optional.empty();
        }
        var generator = commandGeneratorProvider.get();
        offer.accept(generator);
        return Optional.ofNullable(generator.getCommand());
    }

    public Offer offer() {
        OfferBuilder builder;
        switch(offerTypeSelection.getValue()) {
            case MYTHIC_PLUS:
                builder = mythicPlusController.offerBuilder();
                break;
            default:
                return null;
        }
        builder.setNotes(notesField.getText())
            .setPaid(advertiserPaidCheckBox.isSelected())
            .setPrice(new BigDecimal(priceField.getText()))
            .setFaction(factionSelection.getValue());

        return builder.build();
    }
}
