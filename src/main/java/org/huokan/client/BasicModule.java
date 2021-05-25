package org.huokan.client;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import org.huokan.client.models.localization.LocalizationStringConverterFactory;
import org.huokan.client.models.localization.StringConverterFactory;
import org.huokan.client.models.offers.codes.CodeFactory;
import org.huokan.client.models.offers.codes.CodeFactoryImpl;
import org.huokan.client.models.offers.codes.MythicPlusCode;
import org.huokan.client.models.offers.codes.mythicplus.*;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FXMLCache.class).asEagerSingleton();
        bind(StringConverterFactory.class).to(LocalizationStringConverterFactory.class);
        bind(CodeFactory.class).to(CodeFactoryImpl.class);
    }

    @Provides
    @Singleton
    @Named("localization")
    private ResourceBundle createLocalizer() {
        return ResourceBundle.getBundle("org.huokan.client.locales.localization");
    }

    @Provides
    private Set<MythicPlusCode> createMythicPlusCodes(Injector injector) {
        return Stream.of(
                injector.getInstance(BaseCode.class),
                injector.getInstance(LootFunnelAndSpecificKeyTimedCode.class),
                injector.getInstance(LootFunnelAndSpecificKeyCode.class),
                injector.getInstance(LootFunnelCode.class),
                injector.getInstance(LootFunnelTimedCode.class),
                injector.getInstance(SpecificKeyCode.class),
                injector.getInstance(SpecificKeyTimedCode.class),
                injector.getInstance(TimedCode.class)
        ).collect(Collectors.toSet());
    }
}
