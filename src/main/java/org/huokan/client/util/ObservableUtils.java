package org.huokan.client.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;

public class ObservableUtils {
    public static void addObservableValueHandler(ChangeListener listener, List<ObservableValue<?>> observables) {
        for (var observable : observables) {
            observable.addListener(listener);
        }
    }

    public static void addObservableValueHandler(Runnable listener, List<ObservableValue<?>> observables) {
        addObservableValueHandler((o, oldValue, newValue) -> listener.run(), observables);
    }

    public static void addObservableListHandler(ListChangeListener listener, List<ObservableList<?>> observables) {
        for (var observable : observables) {
            observable.addListener(listener);
        }
    }

    public static void addObservableListHandler(Runnable listener, List<ObservableList<?>> observables) {
        addObservableListHandler((ListChangeListener<?>) changedItem -> listener.run(), observables);
    }
}
