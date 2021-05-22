package org.huokan.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.huokan.client.views.ViewFile;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class FXMLCache {
    private ConcurrentMap<ViewFile, ViewAndController> cache = new ConcurrentHashMap<>();
    private javafx.util.Callback<Class<?>, Object> controllerFactory;

    public void setControllerFactory(javafx.util.Callback<Class<?>, Object> controllerFactory) {
        this.controllerFactory = controllerFactory;
    }

    private ViewAndController getViewAndController(ViewFile viewFile) throws IOException {
        if (cache.containsKey(viewFile)) {
            System.out.println("It's cached!");
            return cache.get(viewFile);
        }
        System.out.println("Not cached, loading...");
        var viewAndController = getViewNoCache(viewFile);
        cache.put(viewFile, viewAndController);
        return viewAndController;
    }

    public Node getView(ViewFile viewFile) throws IOException {
        var viewAndController = getViewAndController(viewFile);
        return viewAndController.view;
    }

    public Object getController(ViewFile viewFile) {
        if (cache.containsKey(viewFile)) {
            return cache.get(viewFile).controller;
        }
        return null;
    }

    private ViewAndController getViewNoCache(ViewFile viewFile) throws IOException {
        var loader = new FXMLLoader(viewFile.getURL());
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
