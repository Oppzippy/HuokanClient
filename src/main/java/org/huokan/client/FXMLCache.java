package org.huokan.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.huokan.client.views.ViewFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FXMLCache {
    private Map<ViewFile, ViewAndController> cache = new HashMap<>();
    private javafx.util.Callback<Class<?>, Object> controllerFactory;

    public Node getView(ViewFile view) throws IOException {
        if (cache.containsKey(view)) {
            return cache.get(view).view;
        }
        var viewAndController = getViewNoCache(view);
        cache.put(view, viewAndController);
        return viewAndController.view;
    }

    public void setControllerFactory(javafx.util.Callback<Class<?>, Object> controllerFactory) {
        this.controllerFactory = controllerFactory;
    }

    public Object getController(ViewFile view) {
        if (cache.containsKey(view)) {
            return cache.get(view).controller;
        }
        return null;
    }

    private ViewAndController getViewNoCache(ViewFile view) throws IOException {
        var loader = new FXMLLoader(view.getURL());
        loader.setControllerFactory(controllerFactory);
        var node = loader.<Node>load();
        var controller = loader.getController();
        return new ViewAndController(node, controller);
    }

    private class ViewAndController {
        private Node view;
        private Object controller;

        private ViewAndController(Node view, Object controller) {
            this.view = view;
            this.controller = controller;
        }
    }
}
