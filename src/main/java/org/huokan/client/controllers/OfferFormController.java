package org.huokan.client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.huokan.client.models.localization.LocalizedString;
import org.huokan.client.models.localization.Localizer;
import org.huokan.client.models.wow.ArmorType;
import org.huokan.client.models.wow.Dungeon;
import org.huokan.client.models.wow.PrimaryStat;
import org.huokan.client.models.wow.Role;

import javax.inject.Inject;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OfferFormController implements Initializable {
    @FXML
    private GridPane form;
    @FXML
    private CheckBox advertiserPaidCheckBox;
    @FXML
    private TextField priceAdjustmentField;
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
    private ComboBox<LocalizedString> armorTypeSelection;
    @FXML
    private ListView<Object> weaponTypeSelection;
    @FXML
    private ComboBox<PrimaryStat> primaryStatSelection;
    @FXML
    private ComboBox<Role> trinketTypeSelection;
    @FXML
    private TextField notesField;

    private List<Node> specificKeyNodes;
    private List<Node> lootFunnelNodes;
    @Inject
    private Localizer localizer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpGrid();

        specificKeyNodes = form.getChildren().filtered(node -> node.getStyleClass().contains("specific-key-section"));
        lootFunnelNodes = form.getChildren().filtered(node -> node.getStyleClass().contains("loot-funnel-section"));

        load();
        update();

        bindManagedToVisible(specificKeyNodes);
        bindManagedToVisible(lootFunnelNodes);
    }

    private void setUpGrid() {
        var col1 = new ColumnConstraints();
        col1.setPrefWidth(150);
        var col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        col2.setMinWidth(150);

        form.getColumnConstraints().addAll(col1, col2);
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

    private void load() {
        loadLevelSelection();
        loadSpecificKeys();
        loadArmorTypes();
        loadWeaponTypes();
        loadPrimaryStats();
        loadTrinketTypes();
    }

    private void loadLevelSelection() {
        var levels = FXCollections.<Integer>observableArrayList();
        IntStream.rangeClosed(2, 20).forEachOrdered(levels::add);
        levelSelection.setItems(levels);
        levelSelection.getSelectionModel().select(Integer.valueOf(15));
    }

    private void loadSpecificKeys() {
        var dungeons = FXCollections.observableArrayList(Dungeon.values());
        specificKeysSelection.setItems(dungeons);
        specificKeysSelection.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void loadArmorTypes() {
        var armorTypeKeys = Arrays.stream(ArmorType.values())
                .map(t -> t.name()).toList();
        var localizedArmorTypes = localizer.localize(armorTypeKeys);
        armorTypeSelection.setItems(FXCollections.observableList(localizedArmorTypes));
    }

    private void loadWeaponTypes() {
        // TODO implement
    }

    private void loadTrinketTypes() {
        var types = FXCollections.observableArrayList(Role.values());
        trinketTypeSelection.setItems(types);
    }

    private void loadPrimaryStats() {
        var primaryStats = FXCollections.observableArrayList(PrimaryStat.values());
        primaryStatSelection.setItems(primaryStats);
    }

    private void bindManagedToVisible(Collection<Node> nodes) {
        for (var node : nodes) {
            node.managedProperty().bind(node.visibleProperty());
        }
    }
}
