package org.huokan.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FXMLCache {
    private FXMLLoader loader = new FXMLLoader();
    private Map<String, Node> cache = new HashMap<>();

    public Node getView(InputStream inputStream, String name) throws IOException {
        if (cache.containsKey(name)) {
            return cache.get(name);
        }
        var node = getViewNoCache(inputStream);
        cache.put(name, node);
        return node;
    }


    public Node getViewNoCache(InputStream inputStream) throws IOException {
        var node = loader.<Node>load(inputStream);
        return (Node) node;
    }
}
