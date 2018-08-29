package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgenerator.api.QRData;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public abstract class SubViewController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public abstract void resetForm();

    public abstract QRData getQRData();
}
