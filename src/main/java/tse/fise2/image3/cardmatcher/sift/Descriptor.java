package tse.fise2.image3.cardmatcher.sift;

import org.opencv.core.Mat;

public class Descriptor {
    String imageName;
    Mat descriptor;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Mat getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(Mat descriptor) {
        this.descriptor = descriptor;
    }

    public Descriptor(String name, Mat des)
    {
        imageName = name;
        descriptor = des;
    }
}