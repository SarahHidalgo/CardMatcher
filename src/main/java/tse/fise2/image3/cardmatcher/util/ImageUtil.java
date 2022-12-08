package tse.fise2.image3.cardmatcher.util;

import javafx.scene.effect.GaussianBlur;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;


public class ImageUtil {
    public static Boolean detectCard(Mat frame) {
        Mat bin= new Mat();
        Mat mGray= new Mat();
        Imgproc.cvtColor(frame,mGray,Imgproc.COLOR_RGB2GRAY);
        Imgproc.threshold(mGray,bin,180,255,Imgproc.THRESH_BINARY);
        int n=bin.rows();
        int m=bin.cols();
        int nb= 0;
        int  N= m*n;
//        HighGui.imshow("Image", bin);
//        HighGui.waitKey();
        for (int i =0; i<n;i++)
            for (int j =0; j<m;j++) {
               //detection des pixels blanc avec une marge d'erreur de 10
                if ( bin.get(i, j)[0]== 255)
                   nb++;
            }
//        System.out.println(Double.valueOf(nb)/(Double.valueOf(N))*100);
        if (Double.valueOf(nb)/(Double.valueOf(N))*100 > 90)
            return true;
            else return false;



    }
}
