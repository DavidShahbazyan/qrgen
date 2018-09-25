package am.davsoft.qrgen.controllers.subViews;

import am.davsoft.qrgen.controllers.MainViewController;
import am.davsoft.qrgenerator.api.QRData;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David.Shahbazyan
 * @since Aug 28, 2018
 */
public abstract class SubViewController implements Initializable {
    private MainViewController parentViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public MainViewController getParentViewController() {
        return parentViewController;
    }

    public void setParentViewController(MainViewController parentViewController) {
        this.parentViewController = parentViewController;
    }

    public void prepareForm() {

    }

    public abstract void resetForm();

    public abstract QRData getQRData();
}
