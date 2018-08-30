package am.davsoft.qrgen.util;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
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
    private Dialogs() {}

    public static Window getParentWindow(Node node) {
        return node.getScene().getWindow();
    }

    public static File getUserHomeDir() { return new File(System.getProperty("user.home")); }

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

    public static void showAboutAppDialog(Window ownerWindow) {
//        AboutAppDialog.create(ownerWindow).show(true);
        showInfoPopup("About QR Generator", "The \"QR Generator\" application was developed by David Shahbazyan in 2018." +
                "\nThe program is completely intellectual property and belongs to its developer (David Shahbazyan)." +
                "\nEmail: d.shahbazyan@gmail.com");
    }
}
