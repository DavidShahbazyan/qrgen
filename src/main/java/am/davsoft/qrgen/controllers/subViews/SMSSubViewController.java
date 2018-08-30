package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.impl.QRDataSMS;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class SMSSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldPhoneNumber;
    @FXML private JFXTextArea txtAreaSMSText;

    @Override
    public void resetForm() {
        txtFieldPhoneNumber.setText("");
        txtAreaSMSText.setText("");
    }

    @Override
    public QRData getQRData() {
        return new QRDataSMS().setPhone(txtFieldPhoneNumber.getText()).setMessage(txtAreaSMSText.getText());
    }
}
