package tse.fise2.image3.cardmatcher.model;

import java.io.File;
import java.io.IOException;
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
import tse.fise2.image3.cardmatcher.util.FileUtil;

/*
 * Class permettant de gérer la navigation dans les bases
 *
 *
 * */

public class Base {

	private String path;
	public boolean correspondance=false;
	
	private Camera capture1 = new CameraTest();
	
	public boolean isCorrespondance() {
		return correspondance;
	}

	public void setCorrespondance(boolean correspondance) {
		this.correspondance = correspondance;
	}

	public String getPath() {
		return path;
	}
	
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

    public void initializeList(URL arg0, ResourceBundle arg1, ListView<String> mylistview,Label label_title, Label label_small_card,Label label_current_card, ImageView image_base, ImageView small_img_card,String baseName) {
        FileUtil.CreateFolder(baseName);
        ObservableList<String> listeArray = displayBase(baseName);
        mylistview.getItems().addAll(listeArray);
        // add listener to display image on click
        mylistview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                path = System.getProperty("user.dir")+ "/"+baseName+"/"+ mylistview.getSelectionModel().getSelectedItem();
                correspondance=false;
                displayImage(path, image_base);
                label_current_card.setText(mylistview.getSelectionModel().getSelectedItem());
				if (label_title!=null) {
					label_title.setText("Carte Selectionnée");
					displayCorres(label_title,label_small_card,label_current_card,image_base,small_img_card);			
					}
				}            
        });
    };
    
	public void displayCorres(Label label_title, Label label_small_card,Label label_current_card, ImageView image_base, ImageView small_img_card) {
    	if (correspondance) {
        	displayImage(getPath(),small_img_card);
        	label_title.setText("Carte Correspondante");
        	
        	// ici faut display image qui correspond le mieux 
        	label_current_card.setText("nom carte correspondante");
    	}
    	else {
    		small_img_card.setImage(null);
    		label_title.setText("Carte Selectionnée");
    	}
	}
	
	public void displayPtsInteretsCard(Label label_current_card, Label label_small_card, ImageView small_img_card, ImageView image_base) {
		if (this.isCorrespondance()) {
			// Xavier // mettre image avec les deux cartes comparées et leurs pts d'inétrets jsp quoi
			small_img_card.setImage(null);
			displayImage("C:/Users/sarah/git/projet_informatique/apprentissage/3coeur.png",image_base);
		}
		else {
	    	// Idisplay juste pts intérets carte selectionnée
	    	displayImage("C:/Users/sarah/git/projet_informatique/apprentissage/1.jpg", image_base);
	    	label_current_card.setText("carte avec pts interets");
		}
	}
    
    
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
