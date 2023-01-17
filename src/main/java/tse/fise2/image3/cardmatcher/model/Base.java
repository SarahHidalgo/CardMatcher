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

/**
 * This class is focused on the navigation in the databases for both learning and test modes.
 * We can find many methods managing the image displayed after clicking on the buttons in these modes.
 *
 */

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
	
	/**
	 * Displays an image in a given ImageView.
	 * This method is used to display the card of the database we want to focus on.
	 * @param path The path to the image file.
	 * @param img The ImageView in which to display the image.
	 */
	
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

    /**
     * Displays the files in a given folder.
     * @param baseName the name of the folder
     * @return list of the files in the folder
     */
    
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
                listeArray.add(name);
            }
            else if(item.isDirectory())
            {
                System.out.format("Nom du répertoir: %s%n", item.getName());
            }
        }
        return listeArray;
    }

    /**
     * Filters a ListView of strings based on the text entered by the user.
     * @param oldValue The previous search text.
     * @param newValue The current search text.
     * @param mylistview The ListView to be filtered.
     * @param baseName The name of the folder where the images are stored
     */
    
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
                String filterText = input;
                if(filterText.toUpperCase().contains(newValue)) {
                    subentries.add(input);
                }
            }
            mylistview.setItems(subentries);
        }
    }

    /**
     * Initializes a ListView of strings and sets up event handlers to display an image 
     * and update labels when an item is selected.
	 * 
     * @param mylistview The ListView to be initialized.
     * @param label_title The label where the title of the selected image is displayed
     * @param label_small_card The label where the name of the corresponding image is displayed
     * @param label_current_card The label where the name of the selected image is displayed
     * @param image_base The ImageView where the selected image is displayed
     * @param small_img_card The ImageView where the corresponding image is displayed
     * @param baseName The name of the folder where the images are stored
     */
    
    public void initializeList(URL arg0, ResourceBundle arg1, ListView<String> mylistview,Label label_title, Label label_small_card,Label label_current_card, ImageView image_base, ImageView small_img_card,String baseName) {
        FileUtil.CreateFolder(baseName);
        ObservableList<String> listeArray = displayBase(baseName);
        mylistview.getItems().addAll(listeArray);
        // Add listener to display image on click
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
    
    /**
     * Displays the recognized image and updates the labels.
     * If the correspondence attribute is true, the corresponding image is displayed.
     * Else the small image card is set to null and the title is set to "Carte Sélectionnée"
     * @param label_title The label where the title of the selected image is displayed
     * @param label_small_card The label where the name of the corresponding image is displayed
     * @param label_current_card The label where the name of the selected image is displayed
     * @param image_base The ImageView where the selected image is displayed
     * @param small_img_card The ImageView where the corresponding image is displayed
     */
    
	public void displayCorres(Label label_title, Label label_small_card,Label label_current_card, ImageView image_base, ImageView small_img_card) {
    	if (correspondance) {
        	displayImage(getPath(),small_img_card);
        	label_title.setText("Carte Correspondante");
        	
        	// ici faut display image qui correspond le mieux 
        	label_current_card.setText("nom carte correspondante");
    	}
    	else {
    		small_img_card.setImage(null);
    		label_title.setText("Carte Sélectionnée");
    	}
	}
	
	/**
	 * Displays the keypoints of the selected image and of the recognized image..
	 * 
	 * @param label_title The label where the title of the selected image is displayed
	 * @param label_small_card The label where the name of the corresponding image is displayed
	 * @param label_current_card The label where the name of the selected image is displayed
	 * @param image_base The ImageView where the selected image is displayed
	 * @param small_img_card The ImageView where the corresponding image is displayed
	 */
	
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
    
    
	/**
	 * Displays the points of interest of the selected image and the comparison 
	 * of the selected image with the corresponding image
	 * @param label_current_card The label where the name of the selected image is displayed
	 * @param label_small_card The label where the name of the corresponding image is displayed
	 * @param small_img_card The ImageView where the corresponding image is displayed
	 * @param image_base The ImageView where the selected image or the comparison of the images is displayed
	 */
	
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
