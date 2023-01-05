package tse.fise2.image3.cardmatcher.sift;

import org.opencv.core.Mat;

public class bestImage {
    Mat image;
    double proximityscore;

    public Mat getImage() {
        return image;
    }

    public void setImage(Mat image) {
        this.image = image;
    }

    public double getproximityScore() {
        return proximityscore;
    }

    public void setproximityScore(double proximityscore) {
        this.proximityscore = proximityscore;
    }

    public bestImage(Mat im, double proximityScore)
    {
        image = im;
        proximityscore = proximityScore;
    }
}