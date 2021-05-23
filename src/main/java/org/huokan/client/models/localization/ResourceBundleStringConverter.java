package org.huokan.client.models.localization;

import com.google.auto.factory.AutoFactory;
import javafx.util.StringConverter;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

@AutoFactory
public class ResourceBundleStringConverter<T> extends StringConverter<T> {
    private final ResourceBundle bundle;

    public ResourceBundleStringConverter(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public String toString(T o) {
        if (o == null) {
            return null;
        }
        try {
            return bundle.getString(o.getClass().getSimpleName() + "." + o);
        } catch (MissingResourceException ex) {
            ex.printStackTrace();
            return o.toString();
        }
    }

    @Override
    public T fromString(String s) {
        return null;
    }
}
