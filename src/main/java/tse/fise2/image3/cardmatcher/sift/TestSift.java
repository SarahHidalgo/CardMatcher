package tse.fise2.image3.cardmatcher.sift;

import org.opencv.core.Core;

public class TestSift {
    public static void main(String[] args) {
    	
    	int a;
        a = Sift.descriptor("C:\\Users\\xavie\\OneDrive\\Bureau\\images_proj\\1.png","C:\\Users\\xavie\\OneDrive\\Bureau\\images_proj\\2.png");
        System.out.println(a);
        String b = Sift.descriptor_database("C:\\Users\\xavie\\OneDrive\\Bureau\\images_proj\\1.png","C:\\Users\\xavie\\OneDrive\\Bureau\\images_proj");
        System.out.println(b);
    }
}
