/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devanagari.to.braille.dtb;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AxisDroid
 */
public class SaveController  implements Initializable {

    /**
     * Initializes the controller class.
     * @param event
     */
    int[][] convText;
    
    @FXML
    public void SaveToSDcard(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter(".dtb", "*.dtb");
        fileChooser.getExtensionFilters().add(extension);
        
        File file = fileChooser.showSaveDialog(new Stage());
        
       convText = FXMLDocumentController.convertedTextArray;
      
        
       try{
        FileWriter fos = new FileWriter(file);
       
        for (int[] convText1 : convText) {
            for(int bit:convText1){
                 fos.write(String.valueOf(bit));
            }
        }
        fos.close();
        
        }catch(IOException exc){
            System.out.println(exc);
        }
      
    }
    
    @FXML
    public void StartPrinting(ActionEvent event){
        try{
      FXMLLoader fxml = new FXMLLoader(getClass().getResource("ArduinoCommander.fxml"));
      Parent root = (Parent) fxml.load();
      Stage stage = new Stage();
      stage.setTitle("Print file");
      stage.setResizable(false);
      Scene scene = new Scene(root);
      
      stage.setScene(scene);
      stage.show();
        }catch(IOException exc){
            System.out.println(exc);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
