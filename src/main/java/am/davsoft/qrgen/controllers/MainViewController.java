package am.davsoft.qrgen.controllers;

import am.davsoft.qrgen.helpers.SubView;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class MainViewController implements Initializable {
    private static final String MENU_BUTTON_DEFAULT_STYLE = "-fx-border-radius: 0; -fx-background-radius: 0; -fx-padding: 12px; -fx-border-color: #868686; -fx-border-width: 0 0 1px 0;";
    private static final String MENU_BUTTON_ACTIVE_STYLE = "-fx-border-radius: 0; -fx-background-radius: 0; -fx-padding: 12px; -fx-border-color: transparent #ffa500  #868686 transparent; -fx-background-color: #777; -fx-border-width: 0 5px 1px 0;";

    @FXML private JFXButton btnEmail, btnEvent, btnGeoLocation, btnMeCard, btnPhoneNumber, btnSMS, btnText, btnURL, btnVCard, btnWiFiNetwork;
    @FXML private Label lblSubViewTitle;
    @FXML private ScrollPane subViewContentBox;

    private ObjectProperty<SubView> currentSubView = new SimpleObjectProperty<>();

    private Map<SubView, JFXButton> subViewButtonMappings = new HashMap<>(SubView.values().length);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        subViewButtonMappings.put(SubView.EMAIL, btnEmail);
        subViewButtonMappings.put(SubView.EVENT, btnEvent);
        subViewButtonMappings.put(SubView.GEO_LOCATION, btnGeoLocation);
        subViewButtonMappings.put(SubView.ME_CARD, btnMeCard);
        subViewButtonMappings.put(SubView.PHONE_NUMBER, btnPhoneNumber);
        subViewButtonMappings.put(SubView.SMS, btnSMS);
        subViewButtonMappings.put(SubView.TEXT, btnText);
        subViewButtonMappings.put(SubView.URL, btnURL);
        subViewButtonMappings.put(SubView.V_CARD, btnVCard);
        subViewButtonMappings.put(SubView.WIFI_NETWORK, btnWiFiNetwork);

        currentSubView.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                subViewButtonMappings.get(oldValue).setStyle(MENU_BUTTON_DEFAULT_STYLE);
            }
            if (newValue != null) {
                subViewButtonMappings.get(newValue).setStyle(MENU_BUTTON_ACTIVE_STYLE);
                switchSubViewTo(newValue);
            }
        });

        setCurrentSubView(SubView.EMAIL);
    }

    private void switchSubViewTo(SubView subView) {
        lblSubViewTitle.setText(subView.getSubViewTitle());
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream(subView.getSubViewFileName()));
            subViewContentBox.setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SubView getCurrentSubView() {
        return currentSubView.get();
    }

    public ObjectProperty<SubView> currentSubViewProperty() {
        return currentSubView;
    }

    private void setCurrentSubView(SubView currentSubView) {
        this.currentSubView.set(currentSubView);
    }

    @FXML protected void btnEmailAction(ActionEvent event) {
        setCurrentSubView(SubView.EMAIL);
    }

    @FXML protected void btnEventAction(ActionEvent event) {
        setCurrentSubView(SubView.EVENT);
    }

    @FXML protected void btnGeoLocationAction(ActionEvent event) {
        setCurrentSubView(SubView.GEO_LOCATION);
    }

    @FXML protected void btnMeCardAction(ActionEvent event) {
        setCurrentSubView(SubView.ME_CARD);
    }

    @FXML protected void btnPhoneNumberAction(ActionEvent event) {
        setCurrentSubView(SubView.PHONE_NUMBER);
    }

    @FXML protected void btnSMSAction(ActionEvent event) {
        setCurrentSubView(SubView.SMS);
    }

    @FXML protected void btnTextAction(ActionEvent event) {
        setCurrentSubView(SubView.TEXT);
    }

    @FXML protected void btnURLAction(ActionEvent event) {
        setCurrentSubView(SubView.URL);
    }

    @FXML protected void btnVCardAction(ActionEvent event) {
        setCurrentSubView(SubView.V_CARD);
    }

    @FXML protected void btnWiFiNetworkAction(ActionEvent event) {
        setCurrentSubView(SubView.WIFI_NETWORK);
    }

    @FXML protected void btnConnectByMailAction(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URI("mailto:d.shahbazyan@gmail.com"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
