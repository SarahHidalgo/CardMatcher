package tse.fise2.image3.cardmatcher.model;

import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import tse.fise2.image3.cardmatcher.model.Camera;
import tse.fise2.image3.cardmatcher.util.FileUtil;

public class CameraTest  extends Camera {
    @Override
    public void saveImage() {
        String userHome = System.getProperty("user.dir"); // return c:\Users\${current_user_name}
        //enregistrer dans le projet
        String folder = userHome + "/test";
        FileUtil.CreateFolder(folder);
        String pictureName = super.getLabel().getText();
        String file = folder + "/" + super.getPictureName() +".jpg" ;
        Rect rectCrop = new Rect(new Point(200, 80), new Point(440, 400));
        Mat crop_frame = new Mat(super.getFrame(),rectCrop);
        // Saving the image in the folder
        Imgcodecs.imwrite(file, crop_frame);


    }




}
