package tse.fise2.image3.cardmatcher.controller;




import java.awt.Label;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;

import tse.fise2.image3.cardmatcher.Camera;


public class MainSceneController {
    @FXML
    private Button start_btn;
    @FXML
    private Button btn_test;
    @FXML
    private Button btn_app;
    @FXML
    private ImageView learningFrame;
    @FXML
    private ImageView menuFrame;
    @FXML
    private Label lab;

    public Camera capture1 = new Camera();

    // Event Listener on Button[#start_btn].onAction
    @FXML
    public void startCamera(ActionEvent event) {
        boolean learningmode = true;
        capture1.setLearningmode(learningmode);
        capture1.openCamera(learningFrame,start_btn);
    }

    /**
     * When this method is called, it will switch scene from Menu to MainScene
     */
    @FXML
    public void onClickedApp(ActionEvent event) throws IOException {
        capture1.setCameraActive(false);
        // stop the timer
        capture1.stopAcquisition();
        System.out.println(getClass().getResource("view/MainScene.fxml"));
        Parent modeTest = FXMLLoader.load(getClass().getResource("view/MainScene.fxml"));

        btn_test.getScene().setRoot(modeTest);

    }

}
