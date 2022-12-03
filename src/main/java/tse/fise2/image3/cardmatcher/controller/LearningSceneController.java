package tse.fise2.image3.cardmatcher.controller;




import java.awt.Label;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;

import tse.fise2.image3.cardmatcher.model.Camera;
import tse.fise2.image3.cardmatcher.model.CameraLearning;


public class LearningSceneController {
    @FXML
    private Button start_btn;
    @FXML
    private Button back_btn;

    @FXML
    private ImageView learningFrame;
    @FXML
    private ImageView menuFrame;
    @FXML
    private Label lab;

    public Camera capture1 = new CameraLearning();

    // Event Listener on Button[#start_btn].onAction
    @FXML
    public void startCamera(ActionEvent event) {
        boolean learningmode = true;
        capture1.setLearningmode(learningmode);
        capture1.openCamera(learningFrame,start_btn);
    }


    public void back(ActionEvent actionEvent)  throws IOException{
        capture1.setCameraActive(false);
        // stop the timer
        capture1.stopAcquisition();
        System.out.println(getClass().getResource("view/Menu.fxml"));
        Parent backLoader = FXMLLoader.load(getClass().getResource("view/Menu.fxml"));

        back_btn.getScene().setRoot(backLoader);


    }

}
