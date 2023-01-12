package tse.fise2.image3.cardmatcher.model;

import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import tse.fise2.image3.cardmatcher.model.Camera;
import tse.fise2.image3.cardmatcher.sift.Descriptor;
import tse.fise2.image3.cardmatcher.sift.Sift;
import tse.fise2.image3.cardmatcher.util.FileUtil;

public class CameraTest  extends Camera {
    @Override
    public void saveImage() {


        String userHome = System.getProperty("user.dir"); // return c:\Users\${current_user_name}
        //enregistrer dans le projet
        String folder = userHome + "/test";
        FileUtil.CreateFolder(folder);
        String pictureName = super.getLabel().getText();
        String file = folder + "/" + super.getCard().getName() +"Test.png" ;
        String SE = System.getProperty("os.name").toLowerCase();
        if (SE.indexOf("win") >= 0) {
        	Rect rectCrop = new Rect(new Point(202, 82), new Point(438, 398));
        	Mat crop_frame = new Mat(super.getFrame(),rectCrop);
        	Imgcodecs.imwrite(file, crop_frame);
        }
        else {
        	Rect rectCrop = new Rect(new Point(202, 52), new Point(598, 648));
        	Mat crop_frame = new Mat(super.getFrame(),rectCrop);
        	Imgcodecs.imwrite(file, crop_frame);
        }
        


//        Descriptor desc = Sift.getDescriptor(crop_frame,super.getCard().getName());
//        super.setDescCard(desc);


        // Saving the image in the folder
        


    }




}
