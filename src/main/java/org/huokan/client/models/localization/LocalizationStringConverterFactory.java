package org.huokan.client.models.localization;

import javafx.util.StringConverter;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ResourceBundle;

public class LocalizationStringConverterFactory implements StringConverterFactory {
    @Inject
    @Named("localization")
    private ResourceBundle localization;

    public <T> StringConverter<T> create() {
        return new ResourceBundleStringConverter<T>(localization);
    }
}
