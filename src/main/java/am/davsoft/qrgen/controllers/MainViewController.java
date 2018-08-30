package am.davsoft.qrgen.controllers;

import am.davsoft.qrgen.controllers.subViews.SubViewController;
import am.davsoft.qrgen.helpers.SubView;
import am.davsoft.qrgen.util.Dialogs;
import am.davsoft.qrgenerator.QRGenerator;
import am.davsoft.qrgenerator.api.QRData;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
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

    @FXML
    private VBox appMainVBoxPane;
    @FXML
    private JFXButton btnEmail, btnEvent, btnGeoLocation, btnMeCard, btnPhoneNumber, btnSMS, btnText, btnURL, btnVCard, btnWiFiNetwork;
    @FXML
    private Label lblSubViewTitle;
    @FXML
    private ScrollPane subViewContentBox;

    private ObjectProperty<SubView> currentSubView = new SimpleObjectProperty<>();
    private SubViewController currentSubViewController;
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
            currentSubViewController = loader.getController();
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

    @FXML
    protected void btnEmailAction(ActionEvent event) {
        setCurrentSubView(SubView.EMAIL);
    }

    @FXML
    protected void btnEventAction(ActionEvent event) {
        setCurrentSubView(SubView.EVENT);
    }

    @FXML
    protected void btnGeoLocationAction(ActionEvent event) {
        setCurrentSubView(SubView.GEO_LOCATION);
    }

    @FXML
    protected void btnMeCardAction(ActionEvent event) {
        setCurrentSubView(SubView.ME_CARD);
    }

    @FXML
    protected void btnPhoneNumberAction(ActionEvent event) {
        setCurrentSubView(SubView.PHONE_NUMBER);
    }

    @FXML
    protected void btnSMSAction(ActionEvent event) {
        setCurrentSubView(SubView.SMS);
    }

    @FXML
    protected void btnTextAction(ActionEvent event) {
        setCurrentSubView(SubView.TEXT);
    }

    @FXML
    protected void btnURLAction(ActionEvent event) {
        setCurrentSubView(SubView.URL);
    }

    @FXML
    protected void btnVCardAction(ActionEvent event) {
        setCurrentSubView(SubView.V_CARD);
    }

    @FXML
    protected void btnWiFiNetworkAction(ActionEvent event) {
        setCurrentSubView(SubView.WIFI_NETWORK);
    }

    @FXML
    protected void btnResetAction(ActionEvent event) {
        if (currentSubViewController != null) {
            currentSubViewController.resetForm();
        }
    }

    @FXML
    protected void btnGenerateAction(ActionEvent event) throws WriterException {
        if (currentSubViewController != null) {
            QRData qrData = currentSubViewController.getQRData();
            if (qrData != null) {
                final Stage dialog = new Stage();
                dialog.getIcons().setAll(new Image("images/icons/png/16x16.png"));
                dialog.setTitle(currentSubView.getValue().getSubViewTitle());

                QRGenerator qrGenerator = new QRGenerator();
                qrGenerator.setErrorCorrectionLevel(ErrorCorrectionLevel.M);
                qrGenerator.setQrCodeSize(250);
                qrGenerator.setMargin(0);

                BufferedImage image = qrGenerator.generateImage(qrData);
                ImageView imageView = new ImageView(SwingFXUtils.toFXImage(image, null));

                Paint btnSaveAsFillPaint = Paint.valueOf("WHITE");

                MaterialDesignIconView materialDesignIconView = new MaterialDesignIconView(MaterialDesignIcon.DOWNLOAD);
                materialDesignIconView.setGlyphSize(16);
                materialDesignIconView.setFill(btnSaveAsFillPaint);

                Button btnSaveAs = new Button("Save to File");
                btnSaveAs.setStyle("-fx-background-color: #ffa500;");
                btnSaveAs.setTextFill(btnSaveAsFillPaint);
                btnSaveAs.setGraphic(materialDesignIconView);
                btnSaveAs.setCursor(Cursor.HAND);
                btnSaveAs.setOnAction(e -> {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("GIF Image", "*.gif", "*.GIF"));
                    File file = fileChooser.showSaveDialog(dialog);
                    if (file != null) {
                        try {
                            ImageIO.write(image, "gif", file);
                            Dialogs.showInfoPopup("Success!", "The QR has been successfully saved to file.");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });

                HBox buttonBar = new HBox(btnSaveAs);
                buttonBar.setAlignment(Pos.CENTER_RIGHT);

                VBox root = new VBox(imageView, buttonBar);
                root.setAlignment(Pos.TOP_CENTER);
                root.setPadding(new Insets(15));
                root.setSpacing(15);

                Scene dialogScene = new Scene(root);
                dialog.setScene(dialogScene);
                dialog.setResizable(false);
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.show();
                dialog.requestFocus();
            } else {
                Dialogs.showErrorDialog("Error During QR Generation", "Ooops, it seems like something went wrong during the QR generation!\nPlease, check all the data you have entered and try again.");
            }
        }
    }

    @FXML
    protected void btnConnectByMailAction(ActionEvent event) throws Exception {
        String mailAddress = "d.shahbazyan@gmail.com";
        boolean sendingFailed = false;
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.MAIL)) {
//                URI mailto = new URI("mailto:d.shahbazyan@gmail.com?subject=Hello%20World");
                URI mailto = new URI("mailto:" + mailAddress);
                desktop.mail(mailto);
            } else {
                sendingFailed = true;
            }
        } else {
            sendingFailed = true;
        }
        if (sendingFailed) {
            Dialogs.showErrorDialog("Failed to sent an email", "It seems like I can not launch your mail client for some reason(s)." +
                    "\nPlease, feel free to send your email to \"" + mailAddress + "\".");
        }
//        try {
//            Desktop.getDesktop().browse(new URI("mailto:d.shahbazyan@gmail.com"));
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }
    }


}
