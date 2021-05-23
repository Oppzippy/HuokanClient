package org.huokan.client.models.localization;

import javafx.util.StringConverter;

public interface StringConverterFactory {
    public <T> StringConverter<T> create();
}
