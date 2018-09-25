package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.impl.QRDataText;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class TextSubViewController extends SubViewController {
    @FXML private JFXTextArea txtAreaText;
    @FXML private Label lblSymbolsLeft;

    @Override
    public void prepareForm() {
        super.prepareForm();
        calculateSymbolsLeft();
        txtAreaText.textProperty().addListener(observable -> calculateSymbolsLeft());
    }

    private void calculateSymbolsLeft() {
        lblSymbolsLeft.setText(String.valueOf(getParentViewController().getQrGenerator().getMaxCharsCount() - txtAreaText.getText().length()));
    }

    @Override
    public void resetForm() {
        txtAreaText.setText("");
    }

    @Override
    public QRData getQRData() {
        return new QRDataText().setText(txtAreaText.getText());
    }
}
