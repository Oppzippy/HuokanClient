package org.huokan.client.models.localization;

import java.io.InputStream;
import java.util.Locale;

public interface YAMLLocalizerFactory {
    public YAMLLocalizer create(Locale locale, InputStream yamlInput, YAMLLocalizer defaultLocalizer);
}
