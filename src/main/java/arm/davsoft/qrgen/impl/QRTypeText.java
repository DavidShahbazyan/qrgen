package arm.davsoft.qrgen.impl;

import arm.davsoft.qrgen.api.QRType;

/**
 * @author David Shahbazyan
 * @since Mar 04, 2017
 */
public class QRTypeText implements QRType {
    private String text;

    public QRTypeText setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String getData() {
        return text;
    }
}
