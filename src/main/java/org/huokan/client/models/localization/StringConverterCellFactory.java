package org.huokan.client.models.localization;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class StringConverterCellFactory<T> implements Callback<ListView<T>, ListCell<T>> {
    private StringConverter<T> stringConverter;

    public StringConverterCellFactory(StringConverter<T> stringConverter) {
        this.stringConverter = stringConverter;
    }

    @Override
    public ListCell<T> call(ListView<T> tListView) {
        var cell = new TextFieldListCell<T>();
        cell.setConverter(stringConverter);
        return cell;
    }
}
