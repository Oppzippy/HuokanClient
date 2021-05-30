package org.huokan.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import org.huokan.client.util.ObservableUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.net.URL;
import java.util.*;
import java.util.stream.IntStream;

public class MythicPlusOfferFormController implements Initializable {
    @FXML
    private GridPane form;
    @FXML
    private ComboBox<Integer> levelSelection;
    @FXML
    private CheckBox timedCheckBox;
    @FXML
    private CheckBox specificDungeonCheckBox;
    @FXML
    private SpecificDungeonPickerController specificDungeonPickerController;
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

    @Inject
    @Named("localization")
    private ResourceBundle localization;
    @Inject
    private StringConverterFactory stringConverterFactory;
    @Inject
    private StringConverterCellFactoryProducer cellFactoryProducer;

    private List<Node> specificDungeonNodes;
    private List<Node> lootFunnelNodes;
    private Set<Runnable> changeHandlers = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        specificDungeonNodes = form.getChildren().filtered(node -> node.getStyleClass().contains("specific-dungeon-section"));
        lootFunnelNodes = form.getChildren().filtered(node -> node.getStyleClass().contains("loot-funnel-section"));
        bindManagedToVisible(specificDungeonNodes);
        bindManagedToVisible(lootFunnelNodes);

        weaponTypeSelection.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        armorTypeSelection.setConverter(stringConverterFactory.create());
        primaryStatSelection.setConverter(stringConverterFactory.create());
        trinketTypeSelection.setConverter(stringConverterFactory.create());
        weaponTypeSelection.setCellFactory(cellFactoryProducer.create());

        ObservableUtils.addObservableValueHandler(this::fireChangeEvent, Arrays.asList(
                levelSelection.valueProperty(),
                timedCheckBox.selectedProperty(),
                specificDungeonCheckBox.selectedProperty(),
                lootFunnelCheckBox.selectedProperty(),
                armorTypeSelection.valueProperty(),
                primaryStatSelection.valueProperty(),
                trinketTypeSelection.valueProperty()
        ));
        ObservableUtils.addObservableListHandler(this::fireChangeEvent, Arrays.asList(
                weaponTypeSelection.getSelectionModel().getSelectedItems(),
                specificDungeonPickerController.getSelectedDungeons()
        ));

        loadLevelSelection();
        update();
    }

    public void addChangeHandler(Runnable handler) {
        changeHandlers.add(handler);
    }

    public void removeChangeHandler(Runnable handler) {
        changeHandlers.remove(handler);
    }

    private void fireChangeEvent() {
        for (var handler : changeHandlers) {
            handler.run();
        }
    }

    private void update() {
        updateSpecificDungeonVisibility();
        updateLootFunnelVisibility();
    }

    @FXML
    private void updateSpecificDungeonVisibility() {
        var isSpecificDungeon = specificDungeonCheckBox.isSelected();
        specificDungeonNodes.stream().forEach(node -> node.setVisible(isSpecificDungeon));
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
                .setLootFunnelFilter(lootFunnelFilter())
                .setSpecificDungeons(specificDungeons());
        return builder;
    }

    private LootFunnelFilter lootFunnelFilter() {
        if (!lootFunnelCheckBox.isSelected()) {
            return null;
        }
        var builder = LootFunnelFilter.builder();
        var weaponTypes = new HashSet<>(weaponTypeSelection.getSelectionModel().getSelectedItems());
        builder.setArmorType(armorTypeSelection.getValue())
                .setPrimaryStat(primaryStatSelection.getValue())
                .setTrinketType(trinketTypeSelection.getValue())
                .setWeaponTypes(weaponTypes);
        return builder.build();
    }

    private List<Dungeon> specificDungeons() {
        if (!specificDungeonCheckBox.isSelected()) {
            return null;
        }
        return specificDungeonPickerController.getSelectedDungeons();
    }
}
