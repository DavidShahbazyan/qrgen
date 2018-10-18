package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgen.util.ValidatorFactory;
import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.impl.QRDataEmail;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class EmailSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldEmailAddress;

    @Override
    public void prepareForm() {
        super.prepareForm();
        super.initControlsForValidation(txtFieldEmailAddress);
        txtFieldEmailAddress.setValidators(ValidatorFactory.createRequiredFieldValidator("Email address is required!"));
    }

    @Override
    public void resetForm() {
        super.resetForm();
        txtFieldEmailAddress.setText("");
    }

    @Override
    public QRData getQRData() {
        return new QRDataEmail().setEmail(txtFieldEmailAddress.getText());
    }
}
