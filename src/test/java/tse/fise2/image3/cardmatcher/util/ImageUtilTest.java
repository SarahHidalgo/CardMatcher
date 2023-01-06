package tse.fise2.image3.cardmatcher.util;

import org.junit.jupiter.api.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class ImageUtilTest {

    @Test
    void detectCard() throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Imgcodecs imageCodecs = new Imgcodecs();
        Mat matrix = imageCodecs.imread("/Users/mac/Desktop/purple.png");
        assertEquals(false,ImageUtil.detectCard(matrix));
       matrix = imageCodecs.imread("/Users/mac/Desktop/white.png");
        assertEquals(true,ImageUtil.detectCard(matrix));
        matrix = imageCodecs.imread("/Users/mac/Desktop/mix.png");
        assertEquals(true,ImageUtil.detectCard(matrix));
    }
}
