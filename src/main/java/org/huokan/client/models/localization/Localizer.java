package org.huokan.client.models.localization;

import java.util.List;
import java.util.Locale;

public interface Localizer {
    public LocalizedString localize(String key);
    public List<LocalizedString> localize(List<String> keys);
    public Locale getLocale();
}
