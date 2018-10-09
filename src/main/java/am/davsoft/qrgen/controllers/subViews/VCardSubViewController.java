package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgen.util.ValidatorFactory;
import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.impl.QRDataVCard;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class VCardSubViewController extends SubViewController {
    @FXML private JFXTextField txtFieldName, txtFieldCompany, txtFieldTitle, txtFieldPhone, txtFieldWebsite, txtFieldEmail, txtFieldAddress, txtFieldNote;

    @Override
    public void prepareForm() {
        super.prepareForm();
        super.initControlsForValidation(txtFieldName, txtFieldCompany, txtFieldTitle, txtFieldPhone, txtFieldEmail);
        txtFieldName.setValidators(ValidatorFactory.createRequiredFieldValidator("Name is required!"));
        txtFieldCompany.setValidators(ValidatorFactory.createRequiredFieldValidator("Company is required!"));
        txtFieldTitle.setValidators(ValidatorFactory.createRequiredFieldValidator("Title is required!"));
        txtFieldPhone.setValidators(ValidatorFactory.createRequiredFieldValidator("Phone is required!"));
        txtFieldEmail.setValidators(ValidatorFactory.createRequiredFieldValidator("Email is required!"));
    }

    @Override
    public void resetForm() {
        txtFieldName.setText("");
        txtFieldCompany.setText("");
        txtFieldTitle.setText("");
        txtFieldPhone.setText("");
        txtFieldWebsite.setText("");
        txtFieldEmail.setText("");
        txtFieldAddress.setText("");
        txtFieldNote.setText("");
    }

    @Override
    public QRData getQRData() {
        return new QRDataVCard()
                .setName(txtFieldName.getText())
                .setCompany(txtFieldCompany.getText())
                .setTitle(txtFieldTitle.getText())
                .setPhone(txtFieldPhone.getText())
                .setWebsite(txtFieldWebsite.getText())
                .setEmail(txtFieldEmail.getText())
                .setAddress(txtFieldAddress.getText())
                .setNote(txtFieldNote.getText());
    }
}
