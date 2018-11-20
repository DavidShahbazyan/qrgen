package am.davsoft.qrgen.uiComponents;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.value.WritableValue;
import javafx.css.StyleableProperty;
import javafx.scene.AccessibleRole;
import javafx.scene.Cursor;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

import java.util.function.Consumer;

public class Tag extends Label {
    private final Consumer<Tag> deleteConsumer;
    private final JFXButton deleteButton = new JFXButton();

    public Tag(Consumer<Tag> deleteConsumer) {
        this.deleteConsumer = deleteConsumer;
        initialize();
    }

    public Tag(String text, Consumer<Tag> deleteConsumer) {
        super(text);
        this.deleteConsumer = deleteConsumer;
        initialize();
    }

    private void initialize() {
        initStyles();
        initDeleteButton();
    }

    private void initStyles() {
        getStyleClass().setAll("tag");
        setAccessibleRole(AccessibleRole.TEXT);
        // Labels are not focus traversable, unlike most other UI Controls.
        // focusTraversable is styleable through css. Calling setFocusTraversable
        // makes it look to css like the user set the value and css will not
        // override. Initializing focusTraversable by calling set on the
        // CssMetaData ensures that css will be able to override the value.
        ((StyleableProperty<Boolean>)(WritableValue<Boolean>)focusTraversableProperty()).applyStyle(null, Boolean.FALSE);
    }

    private void initDeleteButton() {
        MaterialDesignIconView materialDesignIconView = new MaterialDesignIconView(MaterialDesignIcon.CLOSE);
        materialDesignIconView.setGlyphSize(20);
        materialDesignIconView.setFill(Paint.valueOf("WHITE"));
        this.deleteButton.setGraphic(materialDesignIconView);
        this.deleteButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.deleteButton.setOnAction(e -> deleteConsumer.accept(this));
        this.deleteButton.setCursor(Cursor.HAND);
        this.deleteButton.setStyle("-fx-padding: 0; -fx-background-radius: 50; -fx-border-radius: 50; -fx-background-color: #555;");
        setGraphic(deleteButton);
        setContentDisplay(ContentDisplay.RIGHT);
        setStyle("-fx-padding: 5px; -fx-background-radius: 50; -fx-border-radius: 50; -fx-background-color: #777; -fx-text-fill: #fff");
    }
}
