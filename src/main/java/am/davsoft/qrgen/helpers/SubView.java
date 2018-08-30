package am.davsoft.qrgen.helpers;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public enum SubView {
    EMAIL        ("Email",        "/views/subViews/emailSubView.fxml"),
    EVENT        ("Event",        "/views/subViews/eventSubView.fxml"),
    GEO_LOCATION ("Geo Location", "/views/subViews/geoLocationSubView.fxml"),
    ME_CARD      ("MeCard",       "/views/subViews/meCardSubView.fxml"),
    PHONE_NUMBER ("Phone Number", "/views/subViews/phoneNumberSubView.fxml"),
    SMS          ("SMS",          "/views/subViews/smsSubView.fxml"),
    TEXT         ("Text",         "/views/subViews/textSubView.fxml"),
    URL          ("URL",          "/views/subViews/urlSubView.fxml"),
    V_CARD       ("VCard",        "/views/subViews/vCardSubView.fxml"),
    WIFI_NETWORK ("WiFi Network", "/views/subViews/wiFiNetworkSubView.fxml"),
    ;

    private final String subViewTitle;
    private final String subViewFileName;


    SubView(String subViewTitle, String subViewFileName) {
        this.subViewTitle = subViewTitle;
        this.subViewFileName = subViewFileName;
    }

    public String getSubViewTitle() {
        return subViewTitle;
    }

    public String getSubViewFileName() {
        return subViewFileName;
    }
}
