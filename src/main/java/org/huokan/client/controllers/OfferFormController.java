package org.huokan.client.controllers;

import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.huokan.client.FXMLCache;
import org.huokan.client.components.IntegerField;
import org.huokan.client.models.boostrequest.BoostType;
import org.huokan.client.models.localization.StringConverterFactory;
import org.huokan.client.models.offers.Offer;
import org.huokan.client.models.offers.OfferBuilder;
import org.huokan.client.models.offers.command.CommandGenerator;
import org.huokan.client.models.wow.Faction;
import org.huokan.client.transitions.ButtonClickedTransition;
import org.huokan.client.util.ObservableUtils;
import org.huokan.client.views.ViewFile;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

public class OfferFormController implements Initializable {
    @FXML
    public ComboBox<Integer> numberOfRunsSelection;
    @FXML
    private CheckBox advertiserPaidCheckBox;
    @FXML
    private IntegerField priceAdjustmentField;
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
    @FXML
    private Button copyButton;

    @Inject
    private StringConverterFactory stringConverterFactory;
    @Inject
    private FXMLCache fxmlCache;
    @Inject
    private Provider<CommandGenerator> commandGeneratorProvider;
    @Inject
    @Named("localization")
    private ResourceBundle localization;

    private MythicPlusOfferFormController mythicPlusController;
    private Transition copyButtonClickedTransition;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        factionSelection.setConverter(stringConverterFactory.create());
        offerTypeSelection.setConverter(stringConverterFactory.create());
        try {
            updateOfferType();
            updateCommand();
        } catch (IOException e) {
            // TODO check if this shows the IOException's stack trace
            throw new RuntimeException(e);
        }
        ObservableUtils.addObservableValueHandler(this::updateCommand, Arrays.asList(
                numberOfRunsSelection.valueProperty(),
                advertiserPaidCheckBox.selectedProperty(),
                factionSelection.valueProperty(),
                priceAdjustmentField.valueProperty(),
                offerTypeSelection.valueProperty(),
                notesField.textProperty()
        ));
        mythicPlusController.addChangeHandler(this::updateCommand);

        copyButtonClickedTransition = new ButtonClickedTransition(
                copyButton,
                copyButton.getText(),
                localization.getString("ok")
        );
    }

    @FXML
    private void updateOfferType() throws IOException {
        switch (offerTypeSelection.getValue()) {
            case MYTHIC_PLUS:
                offerTypeSwitcherController.setContent(fxmlCache.getView(ViewFile.MYTHIC_PLUS_OFFER_FORM));
                mythicPlusController = (MythicPlusOfferFormController) fxmlCache.getController(ViewFile.MYTHIC_PLUS_OFFER_FORM);
                break;
            default:
                offerTypeSwitcherController.clearContent();
        }
    }

    @FXML
    private void copyCommandToClipboard() {
        var command = getCommand();
        if (command.isPresent()) {
            var clipboard = Clipboard.getSystemClipboard();
            var content = new ClipboardContent();
            content.putString(command.get());
            clipboard.setContent(content);

            copyButtonClickedTransition.playFromStart();
        }
    }

    public void updateCommand() {
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
        switch (offerTypeSelection.getValue()) {
            case MYTHIC_PLUS:
                builder = mythicPlusController.offerBuilder();
                break;
            default:
                return null;
        }
        builder.setNumRuns(numberOfRunsSelection.getValue())
                .setNotes(notesField.getText())
                .setPaid(advertiserPaidCheckBox.isSelected())
                .setPriceAdjustment(new BigDecimal(priceAdjustmentField.getValue().orElse(0)))
                .setFaction(factionSelection.getValue());

        return builder.build();
    }
}
