package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgen.util.ValidatorFactory;
import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.impl.QRDataGeoLocation;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class GeoLocationSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldLatitude, txtFieldLongitude, txtFieldQuery;

    @Override
    public void prepareForm() {
        super.prepareForm();
        super.initControlsForValidation(txtFieldLatitude, txtFieldLongitude);
        txtFieldLatitude.setValidators(ValidatorFactory.createRequiredFieldValidator("Latitude is required!"));
        txtFieldLongitude.setValidators(ValidatorFactory.createRequiredFieldValidator("Longitude is required!"));
    }

    @Override
    public void resetForm() {
        txtFieldLatitude.setText("");
        txtFieldLongitude.setText("");
        txtFieldQuery.setText("");
    }

    @Override
    public QRData getQRData() {
        return new QRDataGeoLocation()
                .setLatitude(txtFieldLatitude.getText())
                .setLongitude(txtFieldLongitude.getText())
                .setQuery(txtFieldQuery.getText());
    }
}
