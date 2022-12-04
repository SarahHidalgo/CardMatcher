package tse.fise2.image3.cardmatcher.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import tse.fise2.image3.cardmatcher.model.Camera;

import tse.fise2.image3.cardmatcher.model.CameraTest;

import java.awt.*;
import java.io.IOException;

public class TestSceneController {

    public ImageView detect_frame;
    @FXML
    private Button start_btn;

    @FXML
    private ImageView testingFrame;
    @FXML
    private Button back_btn;

    @FXML
    private Label lab;

    public Camera capture1 = new CameraTest();

    // Event Listener on Button[#start_btn].onAction
    @FXML
    public void startCamera(ActionEvent event) throws InterruptedException {
        boolean testingmode = true;
        capture1.setTestingmode(testingmode);
        capture1.openCamera(testingFrame,start_btn);
        capture1.AddImageDetection(detect_frame);
    }

    /**
     * When this method is called, it will switch scene from Menu to MainScene
     */
    @FXML
    public void onClickedApp(ActionEvent event) throws IOException {
        capture1.setCameraActive(false);
        // stop the timer
        capture1.stopAcquisition();
//        System.out.println(getClass().getResource("view/TestScene.fxml"));
//        Parent modeApp = FXMLLoader.load(getClass().getResource("view/LearningScene.fxml"));
//        Parent modeTest = FXMLLoader.load(getClass().getResource("view/TestScene.fxml"));
//        btn_app.getScene().setRoot(modeApp);
//        btn_test.getScene().setRoot(modeTest);

    }

    public void back(ActionEvent actionEvent)  throws IOException {
        capture1.setCameraActive(false);
        // stop the timer
        capture1.stopAcquisition();

        Parent backLoader = FXMLLoader.load(getClass().getResource("view/Menu.fxml"));

        back_btn.getScene().setRoot(backLoader);
    }
}
