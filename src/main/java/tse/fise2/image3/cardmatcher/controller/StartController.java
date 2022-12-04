package tse.fise2.image3.cardmatcher.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class StartController {
    @FXML
    ImageView logo;

    public void goMenu(MouseEvent mouseEvent) throws IOException, InterruptedException {
       Thread.sleep(2000);
        URL fxmlLocation = getClass().getResource("view/Menu.fxml");

        Parent menuLoader = FXMLLoader.load(fxmlLocation);
        logo.getScene().setRoot(menuLoader);
    }



}
