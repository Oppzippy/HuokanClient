package org.huokan.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.huokan.client.FXMLCache;
import org.huokan.client.models.boostrequest.BoostRequestType;
import org.huokan.client.models.localization.StringConverterCellFactoryProducer;
import org.huokan.client.models.localization.StringConverterFactory;
import org.huokan.client.views.ViewFile;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OfferFormController implements Initializable {
    @FXML
    private CheckBox advertiserPaidCheckBox;
    @FXML
    private TextField priceAdjustmentField;
    @FXML
    private ComboBox<BoostRequestType> offerType;
    @FXML
    private ContentSwitcherController offerTypeSwitcherController;

    @Inject
    private StringConverterFactory stringConverterFactory;
    @Inject
    private FXMLCache fxmlCache;

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
}
