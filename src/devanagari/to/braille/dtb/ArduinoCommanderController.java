package devanagari.to.braille.dtb;

import com.fazecast.jSerialComm.SerialPort;
import com.sun.imageio.plugins.common.InputStreamAdapter;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author AxisDroid
 */
public class ArduinoCommanderController implements Initializable {

     @FXML
    private ChoiceBox<?> COM_ports;
    
    @FXML
    private Button ConnectButton;
    
    @FXML
    private TextArea Console;
    
  //add a observable list 
    ObservableList list = FXCollections.observableArrayList();
    //jserialCOmmSerialPOrt
    
    SerialPort comPort[];
    SerialPort chosenPort;
    
     private void loadData(){
        comPort = SerialPort.getCommPorts();
        
        list.removeAll(list);
        
        for(SerialPort coms:comPort){
            
            list.add(coms.getSystemPortName());
        }
        
        COM_ports.getItems().addAll(list);
          
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadData();
        
    }   
     int noOfBlocks=0;
     int convArray[][] = FXMLDocumentController.convertedTextArray;
     int TotalLines=0;
     //getRowChunk will take the no of blocks it can handle , recieves it from converted array 
     //in binary form and according to row argument it will handle its job
     String getRowChunk(int InitialBlock, int Finalblock,int row){
       //row can be 1st 2nd or 3rd
       int cA=0;
       int cB=0;
       switch(row){
           case 1:
               cA=0;
               cB=1;
               break;
           case 2:
               cA=2;
               cB=3;
               break;
           case 3:
               cA=4;
               cB=5;
               break;
               
       }
        String bufferVal="";
         for(int i=InitialBlock;i<=Finalblock;i++){
             for(int j=cA;j<=cB;j++){
                 bufferVal+=String.valueOf(convArray[i][j]);
             }
         }
         return bufferVal;
         
     }
     boolean jobAbort = true;
     
     void delayTime(int Timeout){
               try{
                      Thread.sleep(Timeout);
                      
                  }catch(Exception e){
                      System.out.println("will be invocation so it doesn't matter");
                      
              }
     }
     
    @FXML
    private void Initiate(ActionEvent event){
       
        
        
       
        Console.setText(COM_ports.getValue().toString() +" chosen");
        chosenPort = SerialPort.getCommPort(COM_ports.getValue().toString());
        chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);
        chosenPort.setBaudRate(9600);
        chosenPort.openPort();
        if(chosenPort.isOpen()){
            //disble the choice box 
            COM_ports.setDisable(true);
            
            //multithreading
            Thread thread = new Thread(){
              @Override
              public void run(){
                 delayTime(100);
                  PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
                   InputStream in = chosenPort.getInputStream();
                  
               
                  delayTime(100);
                  
                 
               
                 //recieve no of words per line
                 int counter =0;
                  
                while(noOfBlocks==0){
                    counter++;
                 output.print('r');
                 output.flush();
                  
                 Console.setText(Console.getText()+"\n"+"sending signal Attempt "+counter);
                    delayTime(100);
                      output.print('r');
                      Scanner scan = new Scanner(in);
                      output.flush();
                       if(scan.hasNextInt()){
                           delayTime(100);
                       noOfBlocks = scan.nextInt();
                       //count total lines 
                       Console.setText(Console.getText()+"\n"+convArray.length);
                       TotalLines = (convArray.length%noOfBlocks==0)?convArray.length/noOfBlocks:convArray.length/noOfBlocks+1;
                       Console.setText(Console.getText()+"\n"+noOfBlocks+ " blocks and "+ TotalLines+ " Lines");
                       scan.close();  
                       jobAbort = false;
                       
                       }
                    delayTime(200);
               
                 }
                //check whether the printer is ready or nots
                //after readiness
            int noOfLinesCounter=0;
            int InitialBlock =0;
            int FinalBlock = noOfBlocks-1;
            String recievedString="";
            boolean printInitiated=false;
            //start printing
            while(!jobAbort){
                   
                   Console.setText(Console.getText()+"\n"+"Print initiated");
                   
                  while(!recievedString.equals("ready to recieve")){
                      delayTime(100);
                      output.print('p');
                      Scanner scan = new Scanner(in);
                      output.flush();
                      if(scan.hasNextLine()){
                      recievedString = scan.nextLine();
     
                      scan.close();
                      }
                      
                  }
                  
                  if(recievedString.equals("ready to recieve")){
                      Console.setText(Console.getText()+"\n"+"Recieve service approved");
                      printInitiated = true;
                  }
                  delayTime(200);
                  output.print('o');
                  output.flush();
                  delayTime(8000);
                   
                   while(printInitiated){
                       Console.setText(Console.getText()+"\n"+"Printing initiated");
                       while(noOfLinesCounter < TotalLines){
                           noOfLinesCounter++;
                           Console.setText(Console.getText()+"\n"+"Printing "+noOfLinesCounter+" line");
                           //each block has 3 rows 
                           if(TotalLines-noOfLinesCounter==0){
                               //after approaching final line end this loop 
                               printInitiated = false;
                               jobAbort = true;
                               FinalBlock = InitialBlock+(convArray.length % noOfBlocks)-1;
                           }
                           for(int row =1;row<=3;row++){
                               //after i code for the stepper motors 
                               //add some wait time so stepper can finish its job
                           Console.setText(Console.getText()+"\n"+" Initial Block "+ InitialBlock);  
                           Console.setText(Console.getText()+"\n"+" Final Block "+ FinalBlock);  
                           String chunk = getRowChunk(InitialBlock, FinalBlock, row);
                           Console.setText(Console.getText()+"\n"+chunk);
                           //send initiate sending sequence repeadetly
                           while(!recievedString.equals("initiate sending")){
                               
                               output.print('q');
                               delayTime(200);
                               Scanner scan = new Scanner(in);
                               
                               output.flush();
                               
                                if(scan.hasNextLine()){
                                     
                                     recievedString = scan.nextLine();
                                     Console.setText(Console.getText()+"\n"+recievedString);
                                     scan.close();
                                 }
                               delayTime(100);
                           }
                           delayTime(100);
                           
                           delayTime(300);
                          
                           for(int i=0;i<chunk.length();i++){
                             
                              output.print(chunk.charAt(i));
                              output.flush();
                              delayTime(100);                              
                           }
                         
                           }
                           
                           InitialBlock= FinalBlock+1;
                           FinalBlock += noOfBlocks;
                          //स्टार्ट the print job right here
                          
                           Console.setText(Console.getText()+"\n"+"Waiting for print");
                          
                           output.print('s');
                            delayTime(200);
                           
                           output.flush();
                          if(!(TotalLines-noOfLinesCounter==0)){
                           while(!recievedString.equals("proceed for next")){
                               Scanner checkScanner = new Scanner(in);
                               delayTime(100);
                               if(checkScanner.hasNext()){
                                delayTime(100);   
                                         
                               recievedString= checkScanner.nextLine();
                               if(recievedString.equals("wait")){
                                   Console.setText(Console.getText()+"\n"+recievedString);
                               }
                                if(recievedString.equals("proceed for next")){
                                  delayTime(3000);
                               }
                               
                                Console.setText(Console.getText()+"\n"+recievedString);
                               checkScanner.close();
                               }else{
                                   output.print('s');
                                   delayTime(200);
                           
                                   output.flush();
                               }
                           }
                           
                          }
                       }
                   }
             }
                Console.setText(Console.getText()+"\n"+"Print Job Finished");
                 chosenPort.closePort();
                COM_ports.setDisable(false);
           
                 
                 /* while(true){
                      
                   output.print('1');
                   output.flush();
                 if(scan.hasNextLine()){
                 Console.setText(Console.getText()+"\n"+ scan.nextLine());
                 
                 }
                   Console.setText(Console.getText()+"\n"+ '1');
                    
                         */ 
                         
                 
                       
                     delayTime(100);
                  }
              
                
              };
           
            thread.start();
            
        }else{
            System.out.println("couldn't open");
           
            
            chosenPort.closePort();
            COM_ports.setDisable(false);
            
        }
    }
    

    
}