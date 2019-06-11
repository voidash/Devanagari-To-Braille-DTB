
package devanagari.to.braille.dtb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


/**
 *
 * @author AxisDroid
 */
public class FXMLDocumentController implements Initializable {
 
    
   private File file; 
   @FXML
   private TextArea textWord;
          
   @FXML
    private void newWordFile(ActionEvent event){
       
       FileChooser fileChooser = new FileChooser();
       //Setting Extension boundaries for letting it open only docx file
       FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DOCX files", "*.docx");
       fileChooser.getExtensionFilters().add(extFilter);
       
       fileChooser.setTitle("open word files");
        file = fileChooser.showOpenDialog(new Stage());
       
   
       ApacheWordReader wordReader= new ApacheWordReader(file);
       
        
       
       //calling new FXML document after opening wordFile
       try{
       FXMLLoader fxml = new FXMLLoader(getClass().getResource("WordFileViewer.fxml"));
       fxml.getNamespace().put("labelText", wordReader.getText());
       Parent root = (Parent) fxml.load();
       Stage stage = new Stage();
       stage.setTitle("Edit Word File");
       stage.setResizable(false);
       Scene scene = new Scene(root);
    
       stage.setScene(scene);
       stage.show();
      
      
  
       
       }catch(Exception e){
           System.out.println(e);
           
       }
        
      
       
       
       
    }
    
   
    
    public String WordTextAreaText(){
      return textWord.getText();
    }
    
    @FXML
    private void DevanagariToBraille1(ActionEvent event){
          
        ntb = new NepaliToBraille(WordTextAreaText());
      launchGUI();
       
              
        
    }
    
    
    @FXML
    private void newFile(ActionEvent event){
        try{
         
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("NewFile.fxml"));
        Parent root = (Parent) fxml.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("File");
        stage.setResizable(false);
        stage.show();
        }catch(IOException e){
            System.out.println(e);
        }
       
    }
    
    
    //Create New Document stage
    @FXML 
   TextArea DevanagariText; 
   
   @FXML
   private void save(ActionEvent event) throws IOException{
       //for saving devanagari text for future purposes if someone writes in text area
       FileChooser fileChooser = new FileChooser();
       FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DOCX file", "*.docx");
       fileChooser.getExtensionFilters().add(extFilter);
       
       File file = fileChooser.showSaveDialog(new Stage());
       if(file != null){
           
           String s = DevanagariText.getText();
           System.out.println(s);
           CreateWordFile(file,s);
       }
   }
   
   
   
   private void CreateWordFile(File file,String s){
       //use this to create word File
       try{
       //creating a document
       XWPFDocument document = new XWPFDocument();
       //Creating stream for later writing to that file
       FileOutputStream out = new FileOutputStream(file);
       //create paragraph
       XWPFParagraph paragraph = document.createParagraph();
       //its like setting cursor but it says Run so start running
       
       XWPFRun run = paragraph.createRun();
       //setting text
       run.setText(s);
       document.write(out);
       //closing stream
       out.close();
       
       }catch(IOException e){
           
       }
       
   }
   //integer array to store the converted text
   
    static int convertedTextArray[][];
           
   //for converting Nepali To Devanagari
    NepaliToBraille ntb;
   @FXML
   public void DevanagariToBraille(ActionEvent event){
       
      ntb = new NepaliToBraille(DevanagariTextAreaText());
      launchGUI();
   }
   
   public void launchGUI(){
        convertedTextArray =ntb.convert();
        BrailleEmbosserGUI bs = new BrailleEmbosserGUI(convertedTextArray);
        bs.start(BrailleEmbosserGUI.classStage);
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("Save.fxml"));
        try{
        Parent root = (Parent) fxml.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.show();
        }catch(IOException exc){
            System.out.println(exc);
        }
        
   }
   public static int[][] getArray(){
   return convertedTextArray;
   }
    //for the braille interface
   
  private String DevanagariTextAreaText(){
      return DevanagariText.getText();
  }
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
}
