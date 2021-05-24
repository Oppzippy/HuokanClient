package org.huokan.client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.huokan.client.models.localization.StringConverterCellFactoryProducer;
import org.huokan.client.models.localization.StringConverterFactory;
import org.huokan.client.models.offers.LootFunnelFilter;
import org.huokan.client.models.offers.MythicPlusOfferBuilder;
import org.huokan.client.models.offers.OfferBuilder;
import org.huokan.client.models.wow.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class MythicPlusOfferFormController implements Initializable {
    @FXML
    private GridPane form;
    @FXML
    private ComboBox<Integer> levelSelection;
    @FXML
    private CheckBox timedCheckBox;
    @FXML
    private CheckBox specificKeyCheckBox;
    @FXML
    private ListView<Dungeon> specificKeysSelection;
    @FXML
    private CheckBox lootFunnelCheckBox;
    @FXML
    private ComboBox<ArmorType> armorTypeSelection;
    @FXML
    private ListView<WeaponType> weaponTypeSelection;
    @FXML
    private ComboBox<PrimaryStat> primaryStatSelection;
    @FXML
    private ComboBox<Role> trinketTypeSelection;

    private List<Node> specificKeyNodes;
    private List<Node> lootFunnelNodes;
    @Inject
    @Named("localization")
    private ResourceBundle localization;
    @Inject
    private StringConverterFactory stringConverterFactory;
    @Inject
    private StringConverterCellFactoryProducer cellFactoryProducer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        specificKeyNodes = form.getChildren().filtered(node -> node.getStyleClass().contains("specific-key-section"));
        lootFunnelNodes = form.getChildren().filtered(node -> node.getStyleClass().contains("loot-funnel-section"));
        bindManagedToVisible(specificKeyNodes);
        bindManagedToVisible(lootFunnelNodes);

        weaponTypeSelection.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        armorTypeSelection.setConverter(stringConverterFactory.create());
        primaryStatSelection.setConverter(stringConverterFactory.create());
        trinketTypeSelection.setConverter(stringConverterFactory.create());
        weaponTypeSelection.setCellFactory(cellFactoryProducer.create());

        loadLevelSelection();
        update();
    }

    private void update() {
        updateSpecificKeyVisibility();
        updateLootFunnelVisibility();
    }

    @FXML
    private void updateSpecificKeyVisibility() {
        var isSpecificKey = specificKeyCheckBox.isSelected();
        specificKeyNodes.stream().forEach(node -> node.setVisible(isSpecificKey));
    }

    @FXML
    private void updateLootFunnelVisibility() {
        var isLootFunnel = lootFunnelCheckBox.isSelected();
        lootFunnelNodes.stream().forEach(node -> node.setVisible(isLootFunnel));
    }

    private void loadLevelSelection() {
        var levels = FXCollections.<Integer>observableArrayList();
        IntStream.rangeClosed(2, 20).forEachOrdered(levels::add);
        levelSelection.setItems(levels);
        levelSelection.getSelectionModel().select(Integer.valueOf(15));
    }

    private void bindManagedToVisible(Collection<Node> nodes) {
        for (var node : nodes) {
            node.managedProperty().bind(node.visibleProperty());
        }
    }

    public OfferBuilder offerBuilder() {
        var builder = new MythicPlusOfferBuilder();
        builder.setLevel(levelSelection.getValue())
                .setTimed(timedCheckBox.isSelected())
                .setLootFunnelFilter(lootFunnelFilter());
        return builder;
    }

    private LootFunnelFilter lootFunnelFilter() {
        if (!lootFunnelCheckBox.isSelected()) {
            return null;
        }
        var builder = LootFunnelFilter.builder();
        builder.setArmorType(armorTypeSelection.getValue())
                .setPrimaryStat(primaryStatSelection.getValue())
                .setTrinketType(trinketTypeSelection.getValue())
                .setWeaponTypes(weaponTypeSelection.getSelectionModel().getSelectedItems());
        return builder.build();
    }
}
