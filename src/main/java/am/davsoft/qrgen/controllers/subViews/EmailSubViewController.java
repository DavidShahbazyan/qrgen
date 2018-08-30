package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.impl.QRDataEmail;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class EmailSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldEmailAddress;

    @Override
    public void resetForm() {
        txtFieldEmailAddress.setText("");
    }

    @Override
    public QRData getQRData() {
        return new QRDataEmail().setEmail(txtFieldEmailAddress.getText());
    }
}
