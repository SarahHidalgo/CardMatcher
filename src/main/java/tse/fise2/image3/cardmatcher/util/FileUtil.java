package tse.fise2.image3.cardmatcher.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static void CreateFile(String x,String ext) {
        File file = new File(x+"."+ext);

        try {
            if (file.createNewFile()) {
                System.out.println("Le fichier "+x+"."+ext+" a été créé");
            } else {
                System.out.println("Le fichier"+ x +"."+ext+" existe déjà");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param path
     * @throws IOException
     * this method aims to copy all the content of a certain directory in the learning database
     */
    public static void copyfolder(String path) throws IOException {

            String userHome = System.getProperty("user.dir"); // return c:\Users\${current_user_name}
            String folder = userHome + "/apprentissage";

            Path sourceDirectory = Paths.get(path);
            Path targetDirectory = Paths.get(folder);

            FileUtils.copyDirectory(new File(path), new File(folder), true);


    }

    public static void copyfile(String path) throws IOException {
        String userHome = System.getProperty("user.dir"); // return c:\Users\${current_user_name}
        String folder = userHome + "/apprentissage";

        Path targetDir = Paths.get(folder);
        FileUtils.copyFileToDirectory(new File(path), new File(folder));
    }



    }



