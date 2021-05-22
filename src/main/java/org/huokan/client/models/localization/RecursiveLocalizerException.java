package org.huokan.client.models.localization;

public class RecursiveLocalizerException extends RuntimeException {
    @Override
    public String getLocalizedMessage() {
        return "You may not set the default Localizer to itself.";
    }
}
