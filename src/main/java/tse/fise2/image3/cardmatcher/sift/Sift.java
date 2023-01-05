package tse.fise2.image3.cardmatcher.sift;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;

import org.opencv.xfeatures2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Sift {

    public Descriptor getDescriptor(Mat image, String name)
    {

        if (image.empty()) {
            System.out.println("Erreur lors du chargement de l'image");
            return null;
        }
//        Mat image = new Mat();
        SIFT detector = SIFT.create();
        SIFT extractor = SIFT.create();
//        Détectez les points clés et calculez les descripteurs:
        MatOfKeyPoint keypoints = new MatOfKeyPoint();
        detector.detect(image, keypoints);
        Mat descriptors = new Mat();
        extractor.compute(image, keypoints, descriptors);
        System.out.println(descriptors);
        Descriptor des = new Descriptor(name,descriptors);
        System.out.println(descriptors.rows());
        return des;
    }

    public static void saveDescriptor(Descriptor desc) throws IOException {

        System.out.println(desc);
        //     Enregistrez les descripteurs dans un fichier:
        BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/fichier.csv",true));

        // écrivez le nom
        writer.append(desc.getImageName());
        writer.append(",");

        for (int i = 0; i < desc.getDescriptor().rows(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j <desc.getDescriptor().cols(); j++) {
                sb.append(desc.getDescriptor().get(i, j)[0]).append(",");
            }
            sb.setLength(sb.length() - 1); // supprime la dernière virgule
            writer.append(sb.toString());
            writer.newLine();
        }

        writer.close();




    }

    public void readDescriptor()
    {

    }




}