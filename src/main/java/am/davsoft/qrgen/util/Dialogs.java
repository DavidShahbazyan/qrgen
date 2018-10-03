package am.davsoft.qrgen.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * @author David.Shahbazyan
 * @since Aug 30, 2018
 */
public final class Dialogs {
    private enum PopupType {INFORMATION, WARNING, CONFIRMATION, ERROR, NONE}

    private Dialogs() {}

    public static Window getParentWindow(Node node) {
        return node.getScene().getWindow();
    }

    public static File getUserHomeDir() { return new File(System.getProperty("user.home")); }

    private static void showPopup(StackPane owner, String header, String content, PopupType popupType) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Label(header));
        Text text = new Text(content);
//        text.setTextAlignment(TextAlignment.JUSTIFY);
//        text.setFont(Font.font("System", 13));
        JFXDialog dialog = new JFXDialog(owner, dialogLayout, JFXDialog.DialogTransition.CENTER, false);

        JFXButton okButton = new JFXButton("Ok");
        okButton.setOnAction(event -> dialog.close());
        okButton.setFocusTraversable(false);
        okButton.setCursor(Cursor.HAND);

        JFXButton yesButton = new JFXButton("Yes");
        okButton.setOnAction(event -> dialog.close());
        okButton.setFocusTraversable(false);
        okButton.setCursor(Cursor.HAND);

        JFXButton noButton = new JFXButton("No");
        okButton.setOnAction(event -> dialog.close());
        okButton.setFocusTraversable(false);
        okButton.setCursor(Cursor.HAND);

        HBox container = new HBox();
        container.setSpacing(5);
        container.setAlignment(Pos.TOP_LEFT);
        dialogLayout.setBody(container);
        switch (popupType) {
            case INFORMATION:
                container.getChildren().setAll(new ImageView(new Image("images/icons/png/icon-info.png")), text);
                dialogLayout.setActions(okButton);
                break;
            case WARNING:
                container.getChildren().setAll(new ImageView(new Image("images/icons/png/icon-error.png")), text);
                dialogLayout.setActions(okButton);
                break;
            case CONFIRMATION:
                container.getChildren().setAll(new ImageView(new Image("images/icons/png/icon-help.png")), text);
                dialogLayout.setActions(noButton, yesButton);
                break;
            case ERROR:
                container.getChildren().setAll(new ImageView(new Image("images/icons/png/icon-high-risk.png")), text);
                dialogLayout.setActions(okButton);
                break;
            case NONE:
                container.getChildren().setAll(new ImageView(new Image("images/icons/png/icon-info.png")), text);
                dialogLayout.setActions(okButton);
                break;
        }
        dialog.show();
    }


    public static void showInfoPopup(String title, String content) {
        showInfoPopup(title, null, content);
    }

    public static void showInfoPopup(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().setAll(new Image("images/icons/png/16x16.png"));
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    public static void showWarningPopup(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().setAll(new Image("images/icons/png/16x16.png"));
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    public static boolean showConfirmPopup(String title, String header, String content) {
        return showConfirmPopup(null, title, header, content);
    }

    public static boolean showConfirmPopup(Node graphic, String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, content, ButtonType.YES, ButtonType.NO);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().setAll(new Image("images/icons/png/16x16.png"));
        if (graphic != null) {
            alert.setGraphic(graphic);
        }
        alert.setTitle(title);
        alert.setHeaderText(header);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.YES;
    }

    public static <T> T showChoicePopup(String title, String header, String content, List<T> choiceList) {
        T retVal = null;
        ChoiceDialog<T> dialog = new ChoiceDialog<>(choiceList.get(0), choiceList);
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().setAll(new Image("images/icons/png/16x16.png"));
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        Optional<T> result = dialog.showAndWait();
        if (!result.equals(Optional.<T>empty())) {
            retVal = result.get();
        }
        return retVal;
    }

    public static void showErrorDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().setAll(new Image("images/icons/png/16x16.png"));
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showAboutAppDialog(StackPane ownerStackPane) {
        showPopup(ownerStackPane, "About QR Generator", "The \"QR Generator\" application was developed by David Shahbazyan in 2018." +
                "\nThe program is completely intellectual property and belongs to its developer (David Shahbazyan)." +
                "\nEmail: d.shahbazyan@gmail.com", PopupType.INFORMATION);
    }
}
