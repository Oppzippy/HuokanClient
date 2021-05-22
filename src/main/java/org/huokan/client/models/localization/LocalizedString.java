package org.huokan.client.models.localization;

import com.google.auto.factory.AutoFactory;
import com.google.inject.assistedinject.Assisted;

import java.util.Objects;

@AutoFactory
public class LocalizedString {
    private String key;
    private String translated;

    public LocalizedString(@Assisted String key, @Assisted String translated) {
        this.key = key;
        this.translated = translated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalizedString that = (LocalizedString) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return translated;
    }

    public String getKey() {
        return key;
    }
}
