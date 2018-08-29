package am.davsoft.qrgen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author David.Shahbazyan
 * @since Dec 12, 2016
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("/views/main.fxml"));
        primaryStage.setTitle("QR Generator");
        primaryStage.getIcons().setAll(new Image("images/icons/png/16x16.png"));
        primaryStage.setMinHeight(((HBox) root).getPrefHeight());
        primaryStage.setMinWidth(((HBox) root).getPrefWidth());
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
//        primaryStage.setResizable(false);
        primaryStage.requestFocus();
    }

}
