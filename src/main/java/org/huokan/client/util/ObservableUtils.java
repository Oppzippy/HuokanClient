package org.huokan.client.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.List;

public class ObservableUtils {
    public static void addHandler(ChangeListener listener, List<ObservableValue> observables) {
        for (var observable : observables) {
            observable.addListener(listener);
        }
    }
}
