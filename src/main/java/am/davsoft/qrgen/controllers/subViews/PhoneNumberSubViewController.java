package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.impl.QRDataPhoneNumber;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class PhoneNumberSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldPhoneNumber;

    @Override
    public void resetForm() {
        txtFieldPhoneNumber.setText("");
    }

    @Override
    public QRData getQRData() {
        return new QRDataPhoneNumber().setPhone(txtFieldPhoneNumber.getText());
    }
}
