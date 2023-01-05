package tse.fise2.image3.cardmatcher.sift;

import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SiftTest {

    @Test
    void getDescriptor() {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        Descriptor d= Sift.getDescriptor(Imgcodecs.imread("/Users/mac/Desktop/reference.png"),"name");
        assertEquals(128,d.getDescriptor().size(1));
        assertEquals("name", d.getImageName());

    }

    @Test
    void saveDescriptor() {
    }

    @Test
    void readDescriptor() throws IOException {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        List<Descriptor> descriptorList = Sift.readDescriptor();
        System.out.println(descriptorList.get(0).getImageName());
        System.out.println(descriptorList.get(0).getDescriptor().size().toString());
    }

    @Test
    void calculateProximityScore() {
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        Descriptor d1= Sift.getDescriptor(Imgcodecs.imread("/Users/mac/Desktop/reference.png"),"name");

        Descriptor d2= Sift.getDescriptor(Imgcodecs.imread("/Users/mac/Desktop/6.png"),"picture");
        System.out.println(Sift.calculateProximityScore(d1.getDescriptor(),d2.getDescriptor()));

        assertNotEquals(0,Sift.calculateProximityScore(d1.getDescriptor(),d2.getDescriptor()));
    }

    @Test
    void getImageBestScore() {


    }
}
