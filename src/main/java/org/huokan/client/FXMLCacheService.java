package org.huokan.client;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Node;
import org.huokan.client.views.ViewFile;

import java.io.IOException;

public class FXMLCacheService extends Service<Node> {
    private final ViewFile viewFile;
    private final FXMLCache cache;

    public FXMLCacheService(FXMLCache cache, ViewFile viewFile) {
        this.cache = cache;
        this.viewFile = viewFile;
    }


    @Override
    protected Task<Node> createTask() {
        return new Task<>() {
            @Override
            protected Node call() throws Exception {
                return cache.getView(viewFile);
            }
        };
    }
}
