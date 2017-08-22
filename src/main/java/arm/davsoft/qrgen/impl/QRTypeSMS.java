package arm.davsoft.qrgen.impl;

import arm.davsoft.qrgen.api.QRType;

/**
 * @author David Shahbazyan
 * @since Mar 04, 2017
 */
public class QRTypeSMS implements QRType {
    private String phone;
    private String message;

    public QRTypeSMS setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public QRTypeSMS setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String getData() {
        return String.format("smsto:%s:%s", phone, message);
    }
}
