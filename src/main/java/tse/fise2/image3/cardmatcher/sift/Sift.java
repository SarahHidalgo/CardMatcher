package tse.fise2.image3.cardmatcher.sift;



/*
 * 
 * Ce code en Java utilise la bibliothèque d'analyse d'image OpenCV pour comparer 
 * l'image d'un objet (carte à jouer) stockée dans la variable card1 avec l'image 
 * d'une carte stockée dans la variable card2. Le code utilise l'algorithme SIFT
 *  (Scale Invariant Feature Transform) pour détecter les points clés et les 
 *  descripteurs dans les deux images, puis utilise un algorithme de correspondance 
 *  de descripteurs pour trouver les points clés qui correspondent dans les deux 
 *  images. Si suffisamment de points clés correspondent, le code déclare que 
 *  l'objet a été trouvé dans la scène.
 
 * Le code commence par charger la bibliothèque OpenCV en fonction de l'environnement
 * d'exécution (Windows ou OS X). Ensuite, il charge les images de cartes à jouer
 * en utilisant la fonction "imread" de OpenCV. Il utilise ensuite l'algorithme SIFT
 * pour trouver les points clés et les descripteurs dans les deux images, puis utilise
 * un algorithme de correspondance de descripteurs pour trouver les points clés 
 * correspondants dans les deux images. Si suffisamment de points clés correspondent,
 * le code affiche un message indiquant que l'objet a été trouvé dans la scène et 
 * enregistre la photo de mise en correspondance des points.
 * 
 */


import org.opencv.calib3d.Calib3d;
import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.highgui.Highgui;

import java.io.File;
import java.util.LinkedList;
import java.util.List;


public class Sift {
	
	public static String descriptor_database(String card,String database) {
		
		File folder = new File(database);
		File[] files = folder.listFiles();
		int fileCount = files.length;
		System.out.println("Le dossier contient " + fileCount + " fichiers.");
	    File[] liste = folder.listFiles();
	    int max = 0;
	    String max_name = "";
	    
	    for(File item : liste){
	    	if(descriptor(card,item.getAbsolutePath())>max)
	    		{
	    		
	    		max = descriptor(card,item.getAbsolutePath());
	    		max_name = item.getAbsolutePath();
	    		};
	      
	    }
		return (max_name);
	}

    public static int descriptor(String card1,String card2) {

    	// Charge les fichiers et librairies 
        File lib = null;
        String os = System.getProperty("os.name");
        String bitness = System.getProperty("sun.arch.data.model");

        if (os.toUpperCase().contains("WINDOWS")) {
            if (bitness.endsWith("64")) {
                lib = new File("libs//x64//" + System.mapLibraryName("opencv_java2411"));
            } else {
                lib = new File("libs//x86//" + System.mapLibraryName("opencv_java2411"));
            }
        }

        System.out.println(lib.getAbsolutePath());
        System.load(lib.getAbsolutePath());

        

        Mat objectImage = Highgui.imread(card1, Highgui.CV_LOAD_IMAGE_COLOR);
        Mat sceneImage = Highgui.imread(card2, Highgui.CV_LOAD_IMAGE_COLOR);
        
        

        MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();
        FeatureDetector featureDetector = FeatureDetector.create(FeatureDetector.SIFT);
   
        featureDetector.detect(objectImage, objectKeyPoints);
        KeyPoint[] keypoints = objectKeyPoints.toArray();
        System.out.println(keypoints);
        
        // Calcule les keys points

        MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SIFT);
        
        // Compute les descriptor
        descriptorExtractor.compute(objectImage, objectKeyPoints, objectDescriptors);

        // Matrice image de sortie
        Mat outputImage = new Mat(objectImage.rows(), objectImage.cols(), Highgui.CV_LOAD_IMAGE_COLOR);
        Scalar newKeypointColor = new Scalar(255, 0, 0);

     
        Features2d.drawKeypoints(objectImage, objectKeyPoints, outputImage, newKeypointColor, 0);

        
        MatOfKeyPoint sceneKeyPoints = new MatOfKeyPoint();
        MatOfKeyPoint sceneDescriptors = new MatOfKeyPoint();
   
        featureDetector.detect(sceneImage, sceneKeyPoints);
        
        descriptorExtractor.compute(sceneImage, sceneKeyPoints, sceneDescriptors);
        

        Mat matchoutput = new Mat(sceneImage.rows() * 2, sceneImage.cols() * 2, Highgui.CV_LOAD_IMAGE_COLOR);
        Scalar matchestColor = new Scalar(0, 255, 0);

        List<MatOfDMatch> matches = new LinkedList<MatOfDMatch>();
        DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
       ;
        descriptorMatcher.knnMatch(objectDescriptors, sceneDescriptors, matches, 2);

       
        LinkedList<DMatch> goodMatchesList = new LinkedList<DMatch>();
        

        float nndrRatio = 0.7f;

        for (int i = 0; i < matches.size(); i++) {
            MatOfDMatch matofDMatch = matches.get(i);
            DMatch[] dmatcharray = matofDMatch.toArray();
            DMatch m1 = dmatcharray[0];
            DMatch m2 = dmatcharray[1];

            if (m1.distance <= m2.distance * nndrRatio) {
                goodMatchesList.addLast(m1);

            }
        }

        if (goodMatchesList.size() >= 7) {
            System.out.println("Objet trouvé, nombres de points communs : " + goodMatchesList.size());

            List<KeyPoint> objKeypointlist = objectKeyPoints.toList();
            List<KeyPoint> scnKeypointlist = sceneKeyPoints.toList();

            LinkedList<Point> objectPoints = new LinkedList<>();
            LinkedList<Point> scenePoints = new LinkedList<>();

            for (int i = 0; i < goodMatchesList.size(); i++) {
                objectPoints.addLast(objKeypointlist.get(goodMatchesList.get(i).queryIdx).pt);
                scenePoints.addLast(scnKeypointlist.get(goodMatchesList.get(i).trainIdx).pt);
            }

            MatOfPoint2f objMatOfPoint2f = new MatOfPoint2f();
            objMatOfPoint2f.fromList(objectPoints);
            MatOfPoint2f scnMatOfPoint2f = new MatOfPoint2f();
            scnMatOfPoint2f.fromList(scenePoints);

            Mat homography = Calib3d.findHomography(objMatOfPoint2f, scnMatOfPoint2f, Calib3d.RANSAC, 3);

            Mat obj_corners = new Mat(4, 1, CvType.CV_32FC2);
            Mat scene_corners = new Mat(4, 1, CvType.CV_32FC2);

            obj_corners.put(0, 0, new double[]{0, 0});
            obj_corners.put(1, 0, new double[]{objectImage.cols(), 0});
            obj_corners.put(2, 0, new double[]{objectImage.cols(), objectImage.rows()});
            obj_corners.put(3, 0, new double[]{0, objectImage.rows()});

            
            Core.perspectiveTransform(obj_corners, scene_corners, homography);

            Mat img = Highgui.imread(card2, Highgui.CV_LOAD_IMAGE_COLOR);

            Core.line(img, new Point(scene_corners.get(0, 0)), new Point(scene_corners.get(1, 0)), new Scalar(0, 255, 0), 4);
            Core.line(img, new Point(scene_corners.get(1, 0)), new Point(scene_corners.get(2, 0)), new Scalar(0, 255, 0), 4);
            Core.line(img, new Point(scene_corners.get(2, 0)), new Point(scene_corners.get(3, 0)), new Scalar(0, 255, 0), 4);
            Core.line(img, new Point(scene_corners.get(3, 0)), new Point(scene_corners.get(0, 0)), new Scalar(0, 255, 0), 4);

            
            MatOfDMatch goodMatches = new MatOfDMatch();
            goodMatches.fromList(goodMatchesList);

            Features2d.drawMatches(objectImage, objectKeyPoints, sceneImage, sceneKeyPoints, goodMatches, matchoutput, matchestColor, newKeypointColor, new MatOfByte(), 2);

            Highgui.imwrite("output//outputImage.jpg", outputImage);
            Highgui.imwrite("output//matchoutput.jpg", matchoutput);
            Highgui.imwrite("output//img.jpg", img);
            return(goodMatchesList.size());

        } else {
            return(-1);
        }

        
    }
    
}
