package org.huokan.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FXMLCache {
    private FXMLLoader loader = new FXMLLoader();
    private Map<String, Node> cache = new HashMap<>();

    public Node getView(String name) throws IOException {
        var node = loader.<Node>load(
                this.getClass().getResourceAsStream("views" + "/" + name)
        );
        return (Node) node;
    }
}
