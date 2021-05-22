package org.huokan.client;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.huokan.client.models.localization.*;

import javax.inject.Singleton;
import java.util.Locale;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FXMLCache.class).toInstance(new FXMLCache());
        bind(LocalizedStringFactory.class);
        install(new FactoryModuleBuilder().implement(Localizer.class, YAMLLocalizer.class)
                .build(YAMLLocalizerFactory.class));
    }

    @Provides
    @Singleton
    private Localizer createLocalizer(Injector injector) {
        var factory = injector.getInstance(YAMLLocalizerFactory.class);
        var defaultLocalizer = factory.create(new Locale("en", "us"),
                this.getClass().getResourceAsStream("/org/huokan/client/locales/enUS.yaml"),
                null);
        var localizer = factory.create(Locale.getDefault(),
                YAMLLocalizer.getInputStreamForLocale(Locale.getDefault()),
                defaultLocalizer);
        localizer.setDefaultLocalizer(defaultLocalizer);
        return localizer;
    }
}
