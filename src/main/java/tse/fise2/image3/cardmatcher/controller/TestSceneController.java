package tse.fise2.image3.cardmatcher.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
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

    public void goLearn(ActionEvent actionEvent) throws IOException {
        capture1.setCameraActive(false);
        // stop the timer
        capture1.stopAcquisition();

        Parent backLoader = FXMLLoader.load(getClass().getResource("view/LearningScene.fxml"));

        back_btn.getScene().setRoot(backLoader);
    }
    public void about(ActionEvent actionEvent) {       
    	Stage stage = new Stage();
    	
    	//Creating a Text object 
    	Text title = new Text(); 
    	Text title2 = new Text();
    	Text title3 = new Text();
    	Text presentationText = new Text();
    	Text grabText = new Text();
    	Text matchText = new Text();
    	Group root = new Group();
        
        //Setting the text to be added. 
        title.setText("About test mode"); 
        title.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
        //setting the position of the text 
        title.setX(50); 
        title.setY(50);
        
        presentationText.setText("The CardMatcher test mode is a mode where you can grab a picture of a card to recognize it. Then, the app will show in output the name of the card showed\r\n"
        		+ "to the camera and the picture of a card in learning database with the highest proximity score.");
        presentationText.setWrappingWidth(500);
        presentationText.setTextAlignment(TextAlignment.JUSTIFY);
        
        presentationText.setX(50); 
        presentationText.setY(90);
        
        title2.setText("Grabbing a picture"); 
        title2.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
        //setting the position of the text 
        title2.setX(50); 
        title2.setY(160);
        
        grabText.setText("When you are ready to grab your card, just click on the red button to make your webcam start. Then, just place your card in the green rectangle, and click again on the red button. All that was inside the green rectangle is saved as a picture in your computer, and then matched with the pictures lying in your learning database.");
        grabText.setWrappingWidth(500);
        grabText.setTextAlignment(TextAlignment.JUSTIFY);
        
        grabText.setX(50); 
        grabText.setY(190);
        
        title3.setText("Matching and proximity score"); 
        title3.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
        //setting the position of the text 
        title3.setX(50); 
        title3.setY(270);
        
        matchText.setText("In this mode, you will be able to compare the picture you are grabbing with the pictures contained in your learning database. The matching functionality is done with the calcul of points of interest il all the pictures. The more points of interest of the tested card match those of one of the learning database cards, the more high proximity score you will get. In output, the app will display the card in your learning database with the highest proximity score, which is the same card as the one you tested.");
        matchText.setWrappingWidth(500);
        matchText.setTextAlignment(TextAlignment.JUSTIFY);
        
        matchText.setX(50); 
        matchText.setY(300);
        
        root.getChildren().add(title);
        root.getChildren().add(presentationText);
        root.getChildren().add(title2);
        root.getChildren().add(grabText);
        root.getChildren().add(title3);
        root.getChildren().add(matchText);
        //Creating a scene object
        Scene scene = new Scene(root, 600, 420);  
        
        //Setting title to the Stage 
        stage.setTitle("About Learning Mode"); 
           
        //Adding scene to the stage 
        stage.setScene(scene); 
           
        //Displaying the contents of the stage 
        stage.show(); 
     } 
}
