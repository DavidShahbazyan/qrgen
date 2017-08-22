package arm.davsoft.qrgen.impl;

import arm.davsoft.qrgen.api.QRType;

/**
 * @author David Shahbazyan
 * @since Mar 04, 2017
 */
public class QRTypeWiFiNetwork implements QRType {
    private String ssid;
    private String pass;
    private String type;
    private boolean hidden;

    public QRTypeWiFiNetwork setSsid(String ssid) {
        this.ssid = ssid;
        return this;
    }

    public QRTypeWiFiNetwork setPass(String pass) {
        this.pass = pass;
        return this;
    }

    /**
     * @param type The security type [WEP|WPA]
     * @return
     */
    public QRTypeWiFiNetwork setType(String type) {
        this.type = type;
        return this;
    }

    public QRTypeWiFiNetwork setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    @Override
    public String getData() {
        StringBuilder builder = new StringBuilder("WIFI:");
        builder.append("S:").append(ssid);
        if (type != null && !type.isEmpty()) {
            builder.append(';').append("T:").append(type);
        }
        builder.append(';').append("P:").append(pass);
        if (hidden) {
            builder.append(';').append("H:").append("true");
        }
        builder.append(";;");
        return builder.toString();
    }
}
