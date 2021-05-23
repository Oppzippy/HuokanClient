package org.huokan.client;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.huokan.client.models.localization.LocalizationStringConverterFactory;
import org.huokan.client.models.localization.StringConverterFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ResourceBundle;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FXMLCache.class).toInstance(new FXMLCache());
        bind(StringConverterFactory.class).to(LocalizationStringConverterFactory.class);
    }

    @Provides
    @Singleton
    @Named("localization")
    private ResourceBundle createLocalizer() {
        return ResourceBundle.getBundle("org.huokan.client.locales.localization");
    }
}
