package tse.fise2.image3.cardmatcher.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MsgUtil {
    public static void DisplayMsg(String msg) {

        Alert a1 = new Alert(Alert.AlertType.NONE,
                msg, ButtonType.CLOSE);

        // show the dialog
        a1.show();
    }

}
