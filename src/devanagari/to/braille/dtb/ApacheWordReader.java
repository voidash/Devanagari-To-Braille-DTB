package devanagari.to.braille.dtb;


import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.File;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AxisDroid
 */
//for opening word Files called by a method called Open WordFile in FXMLDocumentController
public class ApacheWordReader {
    private String text; //word text file
    
    ApacheWordReader(File file) {
        try{
    XWPFDocument doc = new XWPFDocument(new FileInputStream(file));
    XWPFWordExtractor we = new XWPFWordExtractor(doc);
    CreateStage(we.getText());
    }catch(IOException e){
            System.out.println(e);
    }
    }
    
    
    private void CreateStage(String text){
        this.text = text;
        
    }
    
    public String getText(){
        return this.text;
    }
    
}
