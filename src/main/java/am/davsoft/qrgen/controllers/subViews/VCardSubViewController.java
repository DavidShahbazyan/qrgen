package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgen.util.ValidatorFactory;
import am.davsoft.qrgenerator.api.QRData;
import am.davsoft.qrgenerator.helper.PhoneNumberType;
import am.davsoft.qrgenerator.impl.QRDataVCard;
import am.davsoft.qrgenerator.model.ContactName;
import am.davsoft.qrgenerator.model.PhoneNumber;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class VCardSubViewController extends SubViewController {
    private static final ObservableList<PhoneNumberType> PHONE_NUMBER_TYPES = FXCollections.observableArrayList(PhoneNumberType.values());

    @FXML private JFXTextField txtFieldFirstName, txtFieldMiddleName, txtFieldLastName, txtFieldCompany, txtFieldTitle,
            txtFieldPhone, txtFieldWebsite, txtFieldEmail, txtFieldAddress, txtFieldNote;
    @FXML private JFXListView<PhoneNumber> listViewPhoneNumbers;
    @FXML private JFXComboBox<PhoneNumberType> comboBoxPhoneType;

    private ListProperty<PhoneNumber> phoneNumbers = new SimpleListProperty<>(FXCollections.observableArrayList());

    @Override
    public void prepareForm() {
        super.prepareForm();
        super.initControlsForValidation(txtFieldFirstName, txtFieldMiddleName, txtFieldLastName, txtFieldCompany, txtFieldTitle, /*txtFieldPhone, */txtFieldEmail);
        listViewPhoneNumbers.itemsProperty().bind(phoneNumbers);
        comboBoxPhoneType.setItems(PHONE_NUMBER_TYPES);
        comboBoxPhoneType.getSelectionModel().select(PhoneNumberType.CELL);
        txtFieldFirstName.setValidators(ValidatorFactory.createRequiredFieldValidator("First Name is required!"));
//        txtFieldMiddleName.setValidators(ValidatorFactory.createRequiredFieldValidator("Middle Name is required!"));
        txtFieldLastName.setValidators(ValidatorFactory.createRequiredFieldValidator("Last Name is required!"));
        txtFieldCompany.setValidators(ValidatorFactory.createRequiredFieldValidator("Company is required!"));
        txtFieldTitle.setValidators(ValidatorFactory.createRequiredFieldValidator("Job Title is required!"));
        txtFieldPhone.setValidators(ValidatorFactory.createRequiredFieldValidator("Phone is required!"),
                ValidatorFactory.createPhoneNumberValidator("Phone can not contain literals!"));
//        txtFieldEmail.setValidators(ValidatorFactory.createRequiredFieldValidator("Email is required!"));
    }

    @Override
    public void resetForm() {
        txtFieldFirstName.setText("");
        txtFieldMiddleName.setText("");
        txtFieldLastName.setText("");
        txtFieldCompany.setText("");
        txtFieldTitle.setText("");
        txtFieldWebsite.setText("");
        txtFieldEmail.setText("");
        txtFieldAddress.setText("");
        txtFieldNote.setText("");
        phoneNumbers.clear();
        resetPhoneAddForm();
    }

    private void resetPhoneAddForm() {
        comboBoxPhoneType.getSelectionModel().select(PhoneNumberType.CELL);
        txtFieldPhone.setText("");
    }

    @FXML
    protected void btnAddPhoneNumberAction(ActionEvent event) {
        if (txtFieldPhone.validate()) {
            phoneNumbers.add(new PhoneNumber().setType(comboBoxPhoneType.getValue()).setNumber(txtFieldPhone.getText()));
            resetPhoneAddForm();
        }
    }

    @FXML
    protected void btnRemoveSelectedPhoneNumberAction(ActionEvent event) {
        if (listViewPhoneNumbers.getSelectionModel().getSelectedItem() != null) {
            phoneNumbers.remove(listViewPhoneNumbers.getSelectionModel().getSelectedItem());
        }
    }

    @Override
    public QRData getQRData() {
        return new QRDataVCard()
                .setContactName(new ContactName()
                        .setFirstName(txtFieldFirstName.getText())
                        .setMiddleName(txtFieldMiddleName.getText())
                        .setLastName(txtFieldLastName.getText())
                )
                .setCompany(txtFieldCompany.getText())
                .setTitle(txtFieldTitle.getText())
                .addAllPhoneNumbers(phoneNumbers)
                .setWebsite(txtFieldWebsite.getText())
                .setEmail(txtFieldEmail.getText())
                .setAddress(txtFieldAddress.getText())
                .setNote(txtFieldNote.getText());
    }
}
