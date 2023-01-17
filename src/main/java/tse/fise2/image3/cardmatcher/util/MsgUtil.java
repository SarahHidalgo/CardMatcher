package tse.fise2.image3.cardmatcher.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * This class contains a method which is useful to display alerts to the user
 *
 */

public class MsgUtil {
	
	/**
	 * This method allows to open an Dialog box which display an alert message.
	 * @param msg the message to be displayed
	 */
	
    public static void DisplayMsg(String msg) {

        Alert a1 = new Alert(Alert.AlertType.NONE,
                msg, ButtonType.CLOSE);

        // Show the dialog
        a1.show();
    }
}