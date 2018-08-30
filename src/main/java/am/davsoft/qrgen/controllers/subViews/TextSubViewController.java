package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.impl.QRDataText;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class TextSubViewController extends SubViewController {
    @FXML private JFXTextArea txtAreaText;

    @Override
    public void resetForm() {
        txtAreaText.setText("");
    }

    @Override
    public QRData getQRData() {
        return new QRDataText().setText(txtAreaText.getText());
    }
}
