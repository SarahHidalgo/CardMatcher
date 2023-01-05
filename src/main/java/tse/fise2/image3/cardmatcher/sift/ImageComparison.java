package tse.fise2.image3.cardmatcher.sift;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.xfeatures2d.SIFT;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageComparison {
    static {
        // Load the OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static bestImage compareWithDatabase(Mat inputimage) {
    	// Read the reference image and the images in the database
        Mat referenceImage = Imgcodecs.imread("file:///C:/Users/Valentin/Desktop/Cours/Projet_info/Git/projet_informatique/test/cardname.jpg");
        List<Mat> databaseImages = new ArrayList<>();
        databaseImages.add(Imgcodecs.imread("image1.jpg"));
        databaseImages.add(Imgcodecs.imread("image2.jpg"));
        databaseImages.add(Imgcodecs.imread("image3.jpg"));

        // Create a SIFT feature detector and descriptor extractor
        SIFT siftDetector = SIFT.create();
        SIFT siftExtractor = SIFT.create();;

        // Detect SIFT keypoints and extract SIFT descriptors for the reference image
        MatOfKeyPoint referenceKeypoints = new MatOfKeyPoint();
        Mat referenceDescriptors = new Mat();
        siftDetector.detect(referenceImage, referenceKeypoints);
        siftExtractor.compute(referenceImage, referenceKeypoints, referenceDescriptors);
        
        /*Sift CardmatcherSift = new Sift();
        Descriptor referenceDescriptor = CardmatcherSift.getDescriptor(referenceImage, null);*/

        // Calculate the proximity scores for each image in the database
        List<Double> proximityScores = new ArrayList<>();
        for (Mat databaseImage : databaseImages) {
            // Detect SIFT keypoints and extract SIFT descriptors for the current image in the database
            MatOfKeyPoint databaseKeypoints = new MatOfKeyPoint();
            Mat databaseDescriptors = new Mat();
            siftDetector.detect(databaseImage, databaseKeypoints);
            siftExtractor.compute(databaseImage, databaseKeypoints, databaseDescriptors);

            // Calculate the proximity score for the current image in the database
            proximityScores.add(calculateProximityScore(referenceDescriptors, databaseDescriptors));
        }

        // Find the image in the database with the highest proximity score
        int indexOfBestMatch = 0;
        double bestProximityScore = 0.0;
        for (int i = 0; i < proximityScores.size(); i++) {
            if (proximityScores.get(i) > bestProximityScore) {
                indexOfBestMatch = i;
                bestProximityScore = proximityScores.get(i);
            }
        }

        // Display the image with the highest proximity score
        Mat bestMatch = databaseImages.get(indexOfBestMatch);
        bestImage result = new bestImage(bestMatch,bestProximityScore);
        System.out.println("Proximity score: " + bestProximityScore);
        return result;
        }

	private static double calculateProximityScore(Mat referenceDescriptors, Mat databaseDescriptors) {
		// Create a descriptor matcher
        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_L1);

        // Match the reference descriptors to the database descriptors
        MatOfDMatch matches = new MatOfDMatch();
        matcher.match(referenceDescriptors, databaseDescriptors, matches);

        // Calculate the proximity score
        double proximityScore = 0.0;
        for (DMatch match : matches.toList()) {
            proximityScore += match.distance;
        }
        proximityScore /= matches.size().height;

        return proximityScore;
    }
}



