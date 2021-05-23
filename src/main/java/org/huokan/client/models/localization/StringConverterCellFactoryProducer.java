package org.huokan.client.models.localization;

import javax.inject.Inject;

public class StringConverterCellFactoryProducer {
    @Inject
    private StringConverterFactory stringConverterFactory;

    public <T> StringConverterCellFactory<T> create() {
        return new StringConverterCellFactory<T>(stringConverterFactory.create());
    }
}
