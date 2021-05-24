package org.huokan.client.controllers;

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
import org.huokan.client.views.ViewFile;

import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class OfferFormController implements Initializable {
    @FXML
    private CheckBox advertiserPaidCheckBox;
    @FXML
    private TextField priceField;
    @FXML
    private ComboBox<BoostType> offerType;
    @FXML
    private ContentSwitcherController offerTypeSwitcherController;
    @FXML
    private TextField notes;
    @FXML
    private TextArea commandText;

    @Inject
    private StringConverterFactory stringConverterFactory;
    @Inject
    private FXMLCache fxmlCache;

    private MythicPlusOfferFormController mythicPlusController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        offerType.setConverter(stringConverterFactory.create());
        try {
            updateOfferType();
        } catch (IOException e) {
            // TODO check if this shows the IOException's stack trace
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void updateOfferType() throws IOException {
        switch(offerType.getValue()) {
            case MYTHIC_PLUS:
                offerTypeSwitcherController.setContent(fxmlCache.getView(ViewFile.MYTHIC_PLUS_OFFER_FORM));
                break;
            default:
                offerTypeSwitcherController.clearContent();
        }
    }

    public void updateCommand() {
        var offer = offer();
        if (offer == null) {
            commandText.clear();
            return;
        }
        commandText.setText(offer.getCommand());
    }

    public Offer offer() {
        OfferBuilder builder;
        switch(offerType.getValue()) {
            case MYTHIC_PLUS:
                builder = mythicPlusController.offerBuilder();
                break;
            default:
                return null;
        }
        builder.setNotes(notes.getText())
            .setPaid(advertiserPaidCheckBox.isSelected())
            .setPrice(new BigDecimal(priceField.getText()));

        return builder.build();
    }
}
