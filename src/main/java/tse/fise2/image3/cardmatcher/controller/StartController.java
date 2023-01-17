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
import tse.fise2.image3.cardmatcher.util.Audio;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * This class allows to display a launching picture before to go to the menu
 *
 */

public class StartController {
    @FXML
    ImageView logo;

    /**
     * This method allows to launch the menu right after moving the mouse.
     * @param mouseEvent when the user move his mouse.
     * @throws IOException if the FXML file is not found.
     * @throws InterruptedException is something disturb the thread.
     */
    
    public void goMenu(MouseEvent mouseEvent) throws IOException, InterruptedException {

        Thread.sleep(1000);
        URL fxmlLocation = getClass().getResource("view/Menu.fxml");

        Parent menuLoader = FXMLLoader.load(fxmlLocation);
        logo.getScene().setRoot(menuLoader);

    }
}