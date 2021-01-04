/**
 * Author: David Shahbazyan
 * Company: DavSoft
 * Date: Jan 03, 2021
 * Time: 7:30 PM
 */
module qrgen {
    requires com.google.zxing;
    requires org.jfree.jfreesvg;
    requires javafx.controls;
    requires javafx.swing;
    requires javafx.fxml;
    requires com.jfoenix;
    requires fontawesomefx;
    requires barcodegenerator;

    opens am.davsoft.qrgen;
    opens am.davsoft.qrgen.controllers;
    opens am.davsoft.qrgen.controllers.subViews;
    opens am.davsoft.qrgen.validators;
}