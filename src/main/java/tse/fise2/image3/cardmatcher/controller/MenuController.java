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

        System.out.println(getClass().getResource("view/LearningScene.fxml"));
        Parent modeTest = FXMLLoader.load(getClass().getResource("view/LearningScene.fxml"));

        btn_app.getScene().setRoot(modeTest);


    }
    public void onClickedtest (ActionEvent actionEvent)throws IOException {

        System.out.println(getClass().getResource("view/TestScene.fxml"));
        Parent modeTest = FXMLLoader.load(getClass().getResource("view/TestScene.fxml"));

        btn_test.getScene().setRoot(modeTest);
    }
}
