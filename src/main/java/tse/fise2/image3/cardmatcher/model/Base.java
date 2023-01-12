package tse.fise2.image3.cardmatcher.model;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Class permettant de gérer la navigation dans les bases
 *
 *
 * */

public class Base {


    public void displayImage(String path, ImageView img) {
        File file = new File(path);
        String localUrl;
        try {
            localUrl = file.toURI().toURL().toString();
            Image myimage = new Image(localUrl);
            img.setImage(myimage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<String> displayBase(String baseName) {
        String userHome = System.getProperty("user.dir");
        String folder = userHome + "/"+baseName;
        //System.out.println(folder);
        File dir  = new File(folder);
        File[] liste = dir.listFiles();
        ObservableList<String> listeArray = FXCollections.observableArrayList();
        for(File item : liste){
            if(item.isFile())
            {
                String name = item.getName();
                //System.out.format("Nom du fichier: %s%n", item.getName());
                listeArray.add(name);
            }
            else if(item.isDirectory())
            {
                System.out.format("Nom du répertoir: %s%n", item.getName());
            }
        }
        //System.out.println(listeArray);
        return listeArray;
    }

    private void filterText(String oldValue, String newValue, ListView<String> mylistview,String baseName) {
        ObservableList<String> listeArray = displayBase(baseName);
        mylistview.setItems(listeArray);
        ObservableList<String> subentries = FXCollections.observableArrayList();
        if (oldValue != null && (newValue.length() < oldValue.length())) {
            mylistview.setItems(subentries);
            filterText(newValue, newValue, mylistview,baseName);
        }
        else {
            newValue = newValue.toUpperCase();
            for(String input : mylistview.getItems()) {
                //System.out.println(input);
                String filterText = input;
                if(filterText.toUpperCase().contains(newValue)) {
                    subentries.add(input);
                }
            }
            mylistview.setItems(subentries);
        }
    }

    public void initializeList(URL arg0, ResourceBundle arg1, ListView<String> mylistview, Label label_base,ImageView image_base,String baseName) {
        ObservableList<String> listeArray = displayBase(baseName);
        mylistview.getItems().addAll(listeArray);
        // add listener to display image on click
        mylistview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                String path = System.getProperty("user.dir")+ "/"+baseName+"/"+ mylistview.getSelectionModel().getSelectedItem();
                //System.out.println(path);
                displayImage(path, image_base);
                label_base.setText(mylistview.getSelectionModel().getSelectedItem());
            }
        });
    };
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void searchFieldProperty(TextField search_field, ListView<String> mylistview,String baseName) {
        search_field.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                mylistview.setItems(null);
                filterText((String) oldValue, (String) newValue,mylistview,baseName);
            }
        });
    }


}
