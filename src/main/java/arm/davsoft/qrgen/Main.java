package arm.davsoft.qrgen;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.application.Application;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * @author David.Shahbazyan
 * @since Dec 12, 2016
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ObjectProperty<WritableImage> img = new SimpleObjectProperty<>();

        Label title = new Label("QR-Gen");
        title.setFont(new Font(40));
        title.setTextAlignment(TextAlignment.CENTER);

        int maxCharsCount = 2950;
        Label charsLeft = new Label("Characters left: " + maxCharsCount);

        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                charsLeft.setText("Characters left: " + (maxCharsCount - newValue.length()));
                if (newValue.length() > maxCharsCount) {
                    textArea.setText(newValue.substring(0, maxCharsCount));
                }
            }
        });

        Label resultTitle = new Label("Results");

        ImageView imageView = new ImageView();
        imageView.imageProperty().bind(img);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);

        Button btnSaveImage = new Button("Save to PC");
        btnSaveImage.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            fileChooser.setInitialFileName("qr.gif");
            fileChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Images", "*.GIF, *.gif"));
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(img.get(), null), "gif", file);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        VBox resultBlock = new VBox(5, resultTitle, imageView, btnSaveImage);
        resultBlock.setAlignment(Pos.TOP_CENTER);
        resultBlock.visibleProperty().bind(img.isNotNull());
        resultBlock.managedProperty().bind(img.isNotNull());

        Button btnReset = new Button("Reset");
        btnReset.setOnAction(event -> textArea.clear());

        Button btnStart = new Button("Start");
        btnStart.setOnAction(event -> img.set(SwingFXUtils.toFXImage(generateImage(textArea.getText()), null)));

        HBox btnBox = new HBox(5, btnReset, btnStart);
        btnBox.setAlignment(Pos.TOP_CENTER);

        VBox root = new VBox(5, title, resultBlock, charsLeft, textArea, btnBox);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(15));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.requestFocus();
    }

    private void saveImage(BufferedImage image) {
        try {
            ImageIO.write(image, "GIF", new File("./qr.gif"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private BufferedImage generateImage(String content) {
        try {
            int qrCodeSize = 1000;

            Hashtable<EncodeHintType, Object> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hintMap.put(EncodeHintType.MARGIN, 2);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);


            int matrixWidth = bitMatrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
            Graphics2D graphics = (Graphics2D) image.getGraphics();

            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, matrixWidth, matrixWidth);

            Color mainColor = new Color(0, 100, 0);
            graphics.setColor(mainColor);

            //Write Bit Matrix as image
            for (int i = 0; i < matrixWidth; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (bitMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            return image;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
