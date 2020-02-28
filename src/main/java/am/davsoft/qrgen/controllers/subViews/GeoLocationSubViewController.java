package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.barcodegenerator.api.BarCodeData;
import am.davsoft.barcodegenerator.impl.BarCodeDataGeoLocation;
import am.davsoft.qrgen.util.ValidatorFactory;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

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
        super.resetForm();
        txtFieldLatitude.setText("");
        txtFieldLongitude.setText("");
        txtFieldQuery.setText("");
    }

    @Override
    public BarCodeData getQRData() {
        return BarCodeDataGeoLocation.newInstance()
                .withLatitude(txtFieldLatitude.getText())
                .withLongitude(txtFieldLongitude.getText())
                .withQuery(txtFieldQuery.getText());
    }
}
