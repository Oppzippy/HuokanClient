package org.huokan.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.huokan.client.views.ViewFile;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class FXMLCache {
    private ConcurrentMap<ViewFile, ViewAndController> cache = new ConcurrentHashMap<>();
    private javafx.util.Callback<Class<?>, Object> controllerFactory;
    @Inject
    @Named("localization")
    private ResourceBundle localization;

    public void setControllerFactory(javafx.util.Callback<Class<?>, Object> controllerFactory) {
        this.controllerFactory = controllerFactory;
    }

    private ViewAndController getViewAndController(ViewFile viewFile) throws IOException {
        if (cache.containsKey(viewFile)) {
            return cache.get(viewFile);
        }
        var viewAndController = getViewNoCache(viewFile);
        cache.put(viewFile, viewAndController);
        return viewAndController;
    }

    public Parent getView(ViewFile viewFile) throws IOException {
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
        loader.setResources(localization);
        loader.setControllerFactory(controllerFactory);
        var node = loader.<Parent>load();
        var controller = loader.getController();
        return new ViewAndController(node, controller);
    }

    private class ViewAndController {
        private Parent view;
        private Object controller;

        private ViewAndController(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }
    }
}
