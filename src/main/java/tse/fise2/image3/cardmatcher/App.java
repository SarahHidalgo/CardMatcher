package tse.fise2.image3.cardmatcher;


import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.opencv.core.Core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tse.fise2.image3.cardmatcher.controller.LearningSceneController;
import tse.fise2.image3.cardmatcher.controller.MenuController;
import tse.fise2.image3.cardmatcher.controller.StartController;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws InvocationTargetException {
        try {

            URL fxmlLocation = getClass().getResource("controller/view/start.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);

            VBox root = (VBox) loader.load();
            StartController controller = loader.getController();
            Scene scene =  new Scene(root);

            primaryStage.setTitle("CardMatcher");
            primaryStage.setScene(scene);
            //full screen automatic
            primaryStage.setMaximized(true);
            // window icon
            primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icons/CardMatcherLogo.png")));
//            primaryStage.setOnCloseRequest((new EventHandler<WindowEvent>() {
//                public void handle(WindowEvent we)
//                {
//                    controller.capture1.stopAcquisition();
//                }
//            }));
            primaryStage.show();
        }
        catch (Exception e) {
            // generic exception handling
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //new SURFFLANNMatching().run(args);
        launch(args);
    }
}
