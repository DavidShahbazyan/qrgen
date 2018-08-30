package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.impl.QRDataURL;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class URLSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldURL;

    @Override
    public void resetForm() {
        txtFieldURL.setText("");
    }

    @Override
    public QRData getQRData() {
        return new QRDataURL().setUrl(txtFieldURL.getText());
    }
}
