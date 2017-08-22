package arm.davsoft.qrgen.impl;

import arm.davsoft.qrgen.api.QRType;

/**
 * @author David Shahbazyan
 * @since Mar 04, 2017
 */
public class QRTypeGeoLocation implements QRType {
    private String latitude;
    private String longitude;
    private String query;

    public QRTypeGeoLocation setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public QRTypeGeoLocation setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public QRTypeGeoLocation setQuery(String query) {
        this.query = query;
        return this;
    }

    @Override
    public String getData() {
        if (query != null && !query.isEmpty()) {
            return String.format("geo:%s,%s?q=%s", latitude, longitude, query);
        } else {
            return String.format("geo:%s,%s", latitude, longitude);
        }
    }
}
