import com.jfoenix.controls.JFXChipView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ChipViewDemo extends Application {
    @Override
    public void start(Stage stage) {
        JFXChipView<String> chipView = new JFXChipView<>();
        chipView.getChips().addAll("WEF", "WWW", "JD");
//        chipView.getSuggestions().addAll("HELLO", "TROLL", "WFEWEF", "WEF");
//        chipView.setStyle("-fx-background-color: WHITE;");
        chipView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        StackPane pane = new StackPane();
        pane.getChildren().add(chipView);
//        StackPane.setMargin(chipView, new Insets(100));
        pane.setStyle("-fx-background-color:GRAY;");

        final Scene scene = new Scene(pane, 500, 500);
//        scene.getStylesheets().add(TagAreaDemo.class.getResource("/css/jfoenix-components.css").toExternalForm());
        stage.setTitle("JFX Button Demo");
        stage.setScene(scene);
        stage.show();
//        ScenicView.show(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
