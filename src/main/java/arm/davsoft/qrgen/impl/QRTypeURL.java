package arm.davsoft.qrgen.impl;

import arm.davsoft.qrgen.api.QRType;

/**
 * @author David Shahbazyan
 * @since Mar 04, 2017
 */
public class QRTypeURL implements QRType {
    private String url;

    public QRTypeURL setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String getData() {
        if (url != null && !url.isEmpty()) {
            if (!(url.startsWith("http://") || url.startsWith("https://")
                    || url.startsWith("ftp://") || url.startsWith("ftps://"))) {
                url = "http://" + url;
            }
        }
        return url;
    }
}
