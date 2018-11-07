package am.davsoft.qrgen.controllers;

import am.davsoft.qrgen.controllers.subViews.SubViewController;
import am.davsoft.qrgen.helpers.SubView;
import am.davsoft.qrgen.util.Dialogs;
import am.davsoft.qrgenerator.QRGenerator;
import am.davsoft.qrgenerator.api.QRData;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jfree.graphics2d.svg.SVGUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public class MainViewController implements Initializable {
    private static final String MENU_BUTTON_DEFAULT_STYLE = "-fx-border-radius: 0; -fx-background-radius: 0; -fx-padding: 12px; -fx-border-color: #868686; -fx-border-width: 0 0 1px 0;";
    private static final String MENU_BUTTON_ACTIVE_STYLE = "-fx-border-radius: 0; -fx-background-radius: 0; -fx-padding: 12px; -fx-border-color: transparent #ffa500  #868686 transparent; -fx-background-color: #777; -fx-border-width: 0 5px 1px 0;";
    private static final FileChooser.ExtensionFilter PNG_EXTENSION_FILTER = new FileChooser.ExtensionFilter("PNG Image", "*.png", "*.PNG");
    private static final FileChooser.ExtensionFilter SVG_EXTENSION_FILTER = new FileChooser.ExtensionFilter("SVG Image", "*.svg", "*.SVG");
    private static final List<FileChooser.ExtensionFilter> EXTENSION_FILTERS = Arrays.asList(PNG_EXTENSION_FILTER, SVG_EXTENSION_FILTER);

    private final FileChooser fileChooser = new FileChooser();
    private final QRGenerator qrGenerator = new QRGenerator();
    private static int qrCodeSideMargin = 0;
    private static int qrCodeSideSize = 250;

    @FXML
    private StackPane rootStackPane;
    @FXML
    private JFXButton btnEmail, btnEvent, btnGeoLocation, btnMeCard, btnPhoneNumber, btnSMS, btnText, btnURL, btnVCard, btnWiFiNetwork;
    @FXML
    private Label lblSubViewTitle;
    @FXML
    private ScrollPane subViewContentBox;
    @FXML
    private JFXCheckBox checkBoxAddLogo;

    private BufferedImage generatedQRImage;
    private ObjectProperty<SubView> currentSubView = new SimpleObjectProperty<>();
    private SubViewController currentSubViewController;
    private Map<SubView, JFXButton> subViewButtonMappings = new HashMap<>(SubView.values().length);
    private ObjectProperty<File> selectedLogoFile = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        fileChooser.getExtensionFilters().setAll(EXTENSION_FILTERS);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        qrGenerator.setErrorCorrectionLevel(ErrorCorrectionLevel.M);
        qrGenerator.setSize(qrCodeSideSize, qrCodeSideSize);
        qrGenerator.setMargin(qrCodeSideMargin);

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

        checkBoxAddLogo = new JFXCheckBox("Add a logo to the QR Code");
        checkBoxAddLogo.setSelected(false);

        selectedLogoFile.setValue(new File("C:\\Users\\David\\Desktop\\davsoftLogo.png"));
    }

    private void switchSubViewTo(SubView subView) {
        lblSubViewTitle.setText(subView.getSubViewTitle());
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream(subView.getSubViewFileName()));
            currentSubViewController = loader.getController();
            currentSubViewController.setParentViewController(this);
            currentSubViewController.prepareForm();
            subViewContentBox.setContent(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public QRGenerator getQrGenerator() {
        return qrGenerator;
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
    protected void btnGenerateAction(ActionEvent event) throws Exception {
        if (currentSubViewController != null && currentSubViewController.validateForm()) {
            QRData qrData = currentSubViewController.getQRData();
            if (qrData != null) {
                Runnable qrGenerationProcess = () -> {
                    try {
                        if (!checkBoxAddLogo.isSelected()) {
                            generatedQRImage = qrGenerator.generateImage(qrData);
                        } else if (checkBoxAddLogo.isSelected() && selectedLogoFile.get() != null) {
                            generatedQRImage = addLogoOverlay(qrGenerator.generateImage(qrData));
                        } else {
                            Dialogs.showErrorPopup(rootStackPane, "Error During QR Generation", "No logo file was specified.\nPlease, specify one and try again.");
                        }
                    } catch (WriterException | IOException ex) {
                        Dialogs.showErrorPopup(rootStackPane, "Error During QR Generation", ex.getMessage());
                        ex.printStackTrace();
                    }
                };

                qrGenerationProcess.run();
                ImageView imageView = new ImageView(SwingFXUtils.toFXImage(generatedQRImage, null));
                imageView.setFitHeight(350);
                imageView.setFitWidth(350);

                VBox containerVBox= new VBox(imageView);
                containerVBox.setAlignment(Pos.TOP_CENTER);
                containerVBox.setPadding(new Insets(15));
                containerVBox.setSpacing(15);

                JFXDialog dialog = Dialogs.createPopup(rootStackPane, currentSubView.getValue().getSubViewTitle(), containerVBox);

                MaterialDesignIconView materialDesignIconView = new MaterialDesignIconView(MaterialDesignIcon.DOWNLOAD);
                materialDesignIconView.setGlyphSize(20);
//                materialDesignIconView.setFill(Paint.valueOf("WHITE"));

                Label mainColorPickerLabel = new Label("QR:");
                JFXColorPicker mainColorPicker = new JFXColorPicker(new Color(qrGenerator.getMainColor().getRed() / 255.0, qrGenerator.getMainColor().getGreen() / 255.0, qrGenerator.getMainColor().getBlue() / 255.0, qrGenerator.getMainColor().getAlpha() / 255.0));
                mainColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                    qrGenerator.setMainColor(new java.awt.Color((float) newValue.getRed(), (float) newValue.getGreen(),
                            (float) newValue.getBlue(), (float) newValue.getOpacity()));
                    qrGenerationProcess.run();
                    imageView.setImage(SwingFXUtils.toFXImage(generatedQRImage, null));
                });
                HBox mainColorPickerBlock = new HBox(mainColorPickerLabel, mainColorPicker);
                mainColorPickerBlock.setSpacing(5);
                mainColorPickerBlock.setAlignment(Pos.CENTER);

                Label backgroundColorPickerLabel = new Label("BG:");
                JFXColorPicker backgroundColorPicker = new JFXColorPicker(new Color(qrGenerator.getBackgroundColor().getRed() / 255.0, qrGenerator.getBackgroundColor().getGreen() / 255.0, qrGenerator.getBackgroundColor().getBlue() / 255.0, qrGenerator.getBackgroundColor().getAlpha() / 255.0));
                backgroundColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                    qrGenerator.setBackgroundColor(new java.awt.Color((float) newValue.getRed(), (float) newValue.getGreen(),
                            (float) newValue.getBlue(), (float) newValue.getOpacity()));
                    qrGenerationProcess.run();
                    imageView.setImage(SwingFXUtils.toFXImage(generatedQRImage, null));
                });
                HBox backgroundColorPickerBlock = new HBox(backgroundColorPickerLabel, backgroundColorPicker);
                backgroundColorPickerBlock.setSpacing(5);
                backgroundColorPickerBlock.setAlignment(Pos.CENTER);

                HBox colorPickersBar = new HBox(mainColorPickerBlock, backgroundColorPickerBlock);
                colorPickersBar.setAlignment(Pos.CENTER);
                colorPickersBar.setSpacing(25);

                EventHandler<ActionEvent> exportToPNGEventHandler = e -> {
                    fileChooser.getExtensionFilters().setAll(PNG_EXTENSION_FILTER);
                    File file = fileChooser.showSaveDialog(dialog.getDialogContainer().getScene().getWindow());
                    if (file != null) {
                        try {
                            ImageIO.write(generatedQRImage, fileChooser.getSelectedExtensionFilter().getExtensions().get(0).substring(2), file);
                            Dialogs.showInfoPopup("Success!", "The QR has been successfully saved to file.");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                };

                EventHandler<ActionEvent> exportToSVGEventHandler = e -> {
                    fileChooser.getExtensionFilters().setAll(SVG_EXTENSION_FILTER);
                    File file = fileChooser.showSaveDialog(dialog.getDialogContainer().getScene().getWindow());
                    if (file != null) {
                        try {
                            SVGUtils.writeToSVG(file, qrGenerator.generateImageAsSVGString(qrData));
                            Dialogs.showInfoPopup("Success!", "The QR has been successfully saved to file.");
                        } catch (IOException | WriterException ex) {
                            ex.printStackTrace();
                        }
                    }
                };

                EventHandler<ActionEvent> exportToBase64EventHandler = e -> {
                    try {
                        JFXTextArea textArea = new JFXTextArea(qrGenerator.generateImageAsBase64(qrData));
                        textArea.setPrefSize(600, 250);
                        textArea.setMinSize(600, 250);
                        textArea.setMaxSize(600, 250);
                        textArea.setEditable(false);
                        JFXDialog popup = Dialogs.createPopup(rootStackPane, "QR image in Base64 format.", textArea);
                        JFXButton closeButton = new JFXButton("Close");
                        closeButton.getStyleClass().add("btn-grey-dark");
                        closeButton.setFocusTraversable(false);
                        closeButton.setCursor(Cursor.HAND);
                        closeButton.setOnAction(event1 -> popup.close());
                        ((JFXDialogLayout) popup.getContent()).setActions(closeButton);
                        popup.show();
                    } catch (WriterException | IOException ex) {
                        ex.printStackTrace();
                    }
                };

                JFXButton btnExportButtonsListToggler = new JFXButton("Export to:", materialDesignIconView);
                btnExportButtonsListToggler.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                btnExportButtonsListToggler.setTooltip(new Tooltip("Export to:"));
                btnExportButtonsListToggler.getStyleClass().addAll("animated-option-button", "btn-yellow");

                JFXButton btnExportToPNG = new JFXButton("PNG");
                btnExportToPNG.getStyleClass().addAll("animated-option-sub-button", "btn-yellow");
                btnExportToPNG.setTooltip(new Tooltip("Save as PNG file"));
                btnExportToPNG.setOnAction(exportToPNGEventHandler);

                JFXButton btnExportToSVG = new JFXButton("SVG");
                btnExportToSVG.getStyleClass().addAll("animated-option-sub-button", "btn-yellow");
                btnExportToSVG.setTooltip(new Tooltip("Save as SVG file"));
                btnExportToSVG.setOnAction(exportToSVGEventHandler);

                JFXButton btnExportToBase64 = new JFXButton("B64");
                btnExportToBase64.getStyleClass().addAll("animated-option-sub-button", "btn-yellow");
                btnExportToBase64.setTooltip(new Tooltip("Generate Base64 string"));
                btnExportToBase64.setOnAction(exportToBase64EventHandler);

                JFXNodesList exportButtonsList = new JFXNodesList();
                exportButtonsList.setSpacing(5);
                exportButtonsList.setRotate(180);
                exportButtonsList.addAnimatedNode(btnExportButtonsListToggler);
                exportButtonsList.addAnimatedNode(btnExportToPNG);
                exportButtonsList.addAnimatedNode(btnExportToSVG);
                exportButtonsList.addAnimatedNode(btnExportToBase64);

                Region region1 = new Region();
                HBox.setHgrow(region1, Priority.ALWAYS);
                Region region2 = new Region();
                HBox.setHgrow(region2, Priority.ALWAYS);

                ((JFXDialogLayout) dialog.getContent()).setActions(mainColorPickerBlock, region1, backgroundColorPickerBlock, region2, exportButtonsList);
                dialog.show();
            } else {
                Dialogs.showErrorPopup(rootStackPane, "Error During QR Generation", "Ooops, it seems like something went wrong during the QR generation!\nPlease, check all the data you have entered and try again.");
            }
        }
    }

    private BufferedImage addLogoOverlay(BufferedImage source) throws IOException {
        int qrCodeLogoSideSize = (int) (qrCodeSideSize / 3.5);
        java.awt.Image scaledInstance = ImageIO.read(selectedLogoFile.getValue()).getScaledInstance(qrCodeLogoSideSize, qrCodeLogoSideSize, java.awt.Image.SCALE_SMOOTH);
        BufferedImage overlay = new BufferedImage(qrCodeLogoSideSize, qrCodeLogoSideSize, BufferedImage.TYPE_INT_ARGB);
        Graphics overlayGraphics = overlay.getGraphics();
        overlayGraphics.drawImage(scaledInstance, 0, 0, null);
        overlayGraphics.dispose();

        // Calculate the delta height and width between QR code and logo
        int deltaHeight = source.getHeight() - overlay.getHeight();
        int deltaWidth = source.getWidth() - overlay.getWidth();

        // Initialize combined image
        BufferedImage combined = new BufferedImage(source.getHeight(), source.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) combined.getGraphics();

        // Write QR code to new image at position 0/0
        g.drawImage(source, 0, 0, null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // Write logo into combine image at position (deltaWidth / 2) and
        // (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
        // the same space for the logo to be centered
        g.drawImage(overlay, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

        return combined;
    }

    @FXML
    protected void btnConnectByMailAction(ActionEvent event) throws Exception {
        String mailAddress = "d.shahbazyan@gmail.com";
        boolean sendingFailed = false;
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.MAIL)) {
                URI mailto = new URI("mailto:" + mailAddress);
                desktop.mail(mailto);
            } else {
                sendingFailed = true;
            }
        } else {
            sendingFailed = true;
        }
        if (sendingFailed) {
            Dialogs.showErrorPopup(rootStackPane, "Failed to sent an email", "It seems like I can not launch your mail client for some reason(s)." +
                    "\nPlease, feel free to send your email to \"" + mailAddress + "\".");
        }
    }


}
