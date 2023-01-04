package tse.fise2.image3.cardmatcher.model;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import tse.fise2.image3.cardmatcher.model.Camera;
import tse.fise2.image3.cardmatcher.sift.Descriptor;
import tse.fise2.image3.cardmatcher.sift.Sift;
import tse.fise2.image3.cardmatcher.util.FileUtil;

import java.io.IOException;

public class CameraLearning extends Camera {

    public void saveImage() throws IOException {
        String userHome = System.getProperty("user.dir"); // return c:\Users\${current_user_name}
        //enregistrer dans le projet
        String folder = userHome + "/apprentissage";
        FileUtil.CreateFolder(folder);
        String pictureName = super.getLabel().getText();
        String file = folder + "/" + super.getCard().getName()+".jpg";
        Rect rectCrop = new Rect(new Point(202, 82), new Point(438, 398));
        Mat crop_frame = new Mat(super.getFrame(),rectCrop);
        //calcul du descripteur de l'image
        Descriptor desc = Sift.getDescriptor(crop_frame,super.getCard().getName());
        //sauvegarder dans un fichier
        Sift.saveDescriptor(desc);
        // Saving the image in the folder
        Imgcodecs.imwrite(file, crop_frame);
    }
}
