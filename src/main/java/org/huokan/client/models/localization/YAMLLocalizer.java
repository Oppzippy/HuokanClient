package org.huokan.client.models.localization;

import com.google.inject.assistedinject.Assisted;
import org.yaml.snakeyaml.Yaml;

import javax.inject.Inject;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class YAMLLocalizer implements Localizer {
    private final Locale locale;
    private YAMLLocalizer defaultLocalizer;
    private Map<String, String> localization;
    @Inject
    private LocalizedStringFactory localizedStringFactory;

    @Inject
    public YAMLLocalizer(@Assisted Locale locale, @Assisted InputStream yamlInput) {
        this.locale = locale;
        var yaml = new Yaml();
        localization = yaml.load(yamlInput);
    }

    public static InputStream getInputStreamForLocale(Locale locale) {
        StringBuilder path = new StringBuilder("/org/huokan/client/locales/");
        path.append(locale.getLanguage()).append(locale.getCountry()).append(".yaml");
        return YAMLLocalizer.class.getResourceAsStream(path.toString());
    }

    @Override
    public LocalizedString localize(String key) {
        if (localization.containsKey(key)) {
            return localizedStringFactory.create(key, localization.get(key));
        }
        if (defaultLocalizer != null) {
            return defaultLocalizer.localize(key);
        }
        return localizedStringFactory.create(key, key);
    }

    @Override
    public List<LocalizedString> localize(List<String> keys) {
        return keys.stream().map(this::localize).toList();
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    public void setDefaultLocalizer(YAMLLocalizer defaultLocalizer) {
        if (defaultLocalizer == this) {
            throw new RecursiveLocalizerException();
        }
        this.defaultLocalizer = defaultLocalizer;
    }
}
