package tse.fise2.image3.cardmatcher.sift;
import org.opencv.core.*;

import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;
import tse.fise2.image3.cardmatcher.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sift {

    /**
     * @param image
     * @param name
     * @return Ce code utilise l'algorithme ORB pour détecter les points clés et calculer les descripteurs .
     */
    public  static Descriptor getDescriptor(Mat image, String name)
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

        return des;
    }

    public static void saveDescriptor(Descriptor desc) throws IOException {

        System.out.println(desc);
        //     Enregistrez les descripteurs dans un fichier:
        FileUtil.CreateFile("descriptorsDB","csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/descriptorsDB.csv",true));

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

    public static List<Descriptor> readDescriptor() throws IOException {
        List<Descriptor> descriptorList = new ArrayList<>();


        int i=0;
        //k sert a preciser si la ligne contient le nom de l'image
        int k=0;

        boolean t=false;
        // Ouvrez le fichier en utilisant BufferedReader
        FileUtil.CreateFile("descriptorsDB","csv");
        BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/descriptorsDB.csv"));

        Descriptor des =new Descriptor();
        descriptorList.add(des);

        // Lisez chaque ligne du fichier
        String line;
        // Créez une liste pour stocker les données
        List<float[]> data = new ArrayList<>();

        while ((line = reader.readLine()) != null) {

             k=0;
            // Séparez la ligne en différentes valeurs numériques
            String[] values = line.split(",");


            if ( t && !values[0].matches("-?\\d+(\\.\\d+)?")  ) {
                // Créez une matrice à partir de la liste de données
                Mat mat = new Mat(data.size(), data.get(0).length, CvType.CV_32F);
                for (int j = 0; j < data.size(); j++) {
                    mat.put(j, 0, data.get(j));
                }
                des.setDescriptor(mat);
                descriptorList.add(des);

                i++;
                des = new Descriptor();
                //reinisialiser data pour le nouveau descripteur
                data = new ArrayList<>();

            }





            if(!values[0].matches("-?\\d+(\\.\\d+)?"))
            {
                des.setImageName(values[0]);
                k=1;
                t=true;


            }



            // Convertissez chaque valeur en un nombre flottant et ajoutez-le à la liste
            int n;
            if (k==1)
            {
                n= values.length-1;

            }else {
                 n = values.length;
            }

            float[] rowData = new float[n];
            for (int j = 0; j < n; j++) {

                if(k==1) {
                    rowData[j] = Float.parseFloat(values[j+1]);
                }else
                    rowData[j] = Float.parseFloat(values[j]);
            }
            data.add(rowData);


        }


        // pour ajouter le dernier descripyeur
        if(i>0) {
            Mat mat = new Mat(data.size(), data.get(0).length, CvType.CV_32F);
            for (int j = 0; j < data.size(); j++) {
                mat.put(j, 0, data.get(j));
            }
            des.setDescriptor(mat);
            descriptorList.add(des);
        }


        // Fermez le reader
        reader.close();
       return descriptorList;

    }


    /**
     * @param referenceDescriptors
     * @param databaseDescriptors
     * @return  Il utilise l'algorithme de recherche de correspondance de flot de Foster (BRUTEFORCE_HAMMING) pour trouver le score de c orrespondance
     */
    public static double calculateProximityScore(Mat referenceDescriptors, Mat databaseDescriptors) {
//        // Create a descriptor matcher
//        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_L1);
//        // Match the reference descriptors to the database descriptors
//        MatOfDMatch matches = new MatOfDMatch();
//        matcher.match(referenceDescriptors, databaseDescriptors, matches);
//        // Calculate the proximity score
//        double proximityScore = 0.0;
//        for (DMatch match : matches.toList()) {
//            proximityScore += match.distance;
//        }
//        proximityScore /= matches.size().height;
//        return proximityScore;


        // Trouver les correspondances entre les descripteurs
        try {
            MatOfDMatch matches = new MatOfDMatch();
            DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
            matcher.match(referenceDescriptors, databaseDescriptors, matches);

            // Calculer le score de correspondance
            double max_dist = 0;
            double min_dist = 100;
            DMatch[] match = matches.toArray();
            for (int i = 0; i < referenceDescriptors.rows(); i++) {
                double dist = match[i].distance;
                if (dist < min_dist) min_dist = dist;
                if (dist > max_dist) max_dist = dist;
            }
            double proximityScore = (1 - (min_dist / max_dist));
            return proximityScore;
        } catch (RuntimeException exp)
        {
            return 0;
        }
//        System.out.println("Score de correspondance: " + (1 - (min_dist / max_dist)));
    }



    public static String getImageBestScore(Mat referenceDescriptors) throws IOException {

        List<Descriptor> descriptorList = readDescriptor();
        String rslt ="";
        double max =0;

        for (Descriptor d :descriptorList )
        {
            if (calculateProximityScore( referenceDescriptors, d.getDescriptor())>=max )
            {
                max = calculateProximityScore( referenceDescriptors, d.getDescriptor());
                rslt = d.getImageName();

            }


        }
        return rslt;

    }


}
