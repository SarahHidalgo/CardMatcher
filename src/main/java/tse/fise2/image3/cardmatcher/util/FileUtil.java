package tse.fise2.image3.cardmatcher.util;

import java.io.File;

public class FileUtil {

    public static void CreateFolder(String x) {
        File dossier = new File(x);
        boolean res = dossier.mkdir();
        if (res) {
            System.out.println("Le dossier a été créé.");
        } else {
            System.out.println("Le dossier existe déja.");
        }
    }

}
