package tse.fise2.image3.cardmatcher.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button btn_test;
    @FXML
    private Button btn_app;

    @FXML
    public void onClickedApp(ActionEvent event) throws IOException {


        Parent modeTest = FXMLLoader.load(getClass().getResource("view/LearningScene.fxml"));

        btn_app.getScene().setRoot(modeTest);


    }

    public void onClickedtest (ActionEvent actionEvent)throws IOException {


        Parent modeTest = FXMLLoader.load(getClass().getResource("view/TestScene.fxml"));

        btn_test.getScene().setRoot(modeTest);
    }

    /**
     * @param actionEvent
     * Terminating the main thread
     */
    public void quit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
