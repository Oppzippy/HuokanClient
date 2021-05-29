package org.huokan.client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import org.huokan.client.models.localization.StringConverterFactory;
import org.huokan.client.models.wow.Dungeon;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SpecificDungeonPickerController implements Initializable {
    @FXML
    private VBox specificKeyContainer;

    @Inject
    private StringConverterFactory stringConverterFactory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setNumRuns(1);
    }

    public List<Dungeon> getSelectedDungeons() {
        return specificKeyContainer.getChildren()
                .stream()
                .map(dungeonPicker -> ((ComboBox<Dungeon>) dungeonPicker).getValue())
                .toList();
    }

    public void setNumRuns(int numRuns) {
        int numComboBoxes = getNumRuns();
        if (numRuns < numComboBoxes) {
            specificKeyContainer.getChildren().remove(numRuns - 1, numComboBoxes - 1);
        } else if (numRuns > numComboBoxes) {
            for (var i = numComboBoxes; i < numRuns; i++) {
                appendDungeonPicker();
            }
        }
    }

    public int getNumRuns() {
        return specificKeyContainer.getChildrenUnmodifiable().size();
    }

    private void appendDungeonPicker() {
        var dungeonPicker = createDungeonPicker();
        specificKeyContainer.getChildren().add(dungeonPicker);
    }

    private ComboBox<Dungeon> createDungeonPicker() {
        var dungeonPicker = new ComboBox<Dungeon>();
        dungeonPicker.setConverter(stringConverterFactory.create());
        var dungeons = FXCollections.observableArrayList(Dungeon.values());
        dungeonPicker.setItems(dungeons);
        dungeonPicker.valueProperty().setValue(dungeons.stream().findFirst().orElse(null));
        return dungeonPicker;
    }
}
