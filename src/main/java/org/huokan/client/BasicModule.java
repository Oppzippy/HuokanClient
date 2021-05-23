package org.huokan.client;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ResourceBundle;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FXMLCache.class).toInstance(new FXMLCache());
    }

    @Provides
    @Singleton
    @Named("localization")
    private ResourceBundle createLocalizer() {
        var bundle = ResourceBundle.getBundle("org.huokan.client.locales.localization");
        return bundle;
    }
}
