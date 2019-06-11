/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devanagari.to.braille.dtb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import javafx.stage.Stage;


/**
 *
 * @author ashish
 */
public class BrailleEmbosserGUI extends Application{
int[][] alphabets;
    public BrailleEmbosserGUI(int[][] alphabets) {
        this.alphabets = alphabets;
        
    }

    
    
    
    
  static Stage classStage=new Stage();
  
    Color color;
Pane drawingPane;




public void start(Stage myStage){
    //hack from stackOverflow
    classStage = myStage;
    myStage.setTitle("BrailleEmbosserDemo");
    
    drawingPane = new Pane();
    ScrollPane scroll = new ScrollPane();
    scroll.setContent(drawingPane);
    
    scroll.setPrefSize(900, 700);
    scroll.maxWidth(Double.MAX_VALUE);
    scroll.maxHeight(Double.MAX_VALUE);
    
    scroll.setFitToHeight(true);
    scroll.setFitToWidth(true);
    
    drawBlocks();
    
    
  
 
    Scene myScene = new Scene(scroll);
    myStage.setScene(myScene);
    myStage.setResizable(false);
    myStage.show();
    
    
    
    
}
private void drawBlocks(){
 /*   int alphabets[][]={{1,0,0,0,1,0},{0,1,0,0,0,1},{1,1,1,1,0,0},{1,0,1,0,0,1},{0,1,0,0,1,1},{1,1,0,0,0,0}
,{1,0,0,0,0,1},{0,1,1,1,0,0},{0,0,0,1,1,1},{0,0,1,1,0,0},{0,1,1,1,1,1},{0,1,1,1,0,1},{1,1,1,0,0,1}
,{1,1,1,1,1,1},{0,1,0,1,1,1},{0,1,1,1,1,0},{1,1,0,1,0,1},{1,1,0,1,0,0},{0,1,1,0,1,1},{1,1,0,1,1,0}
,{1,1,1,0,1,0},{0,0,1,1,1,0},{1,0,1,0,0,0},{0,1,0,1,0,0},{1,1,0,0,1,0},{1,1,0,1,1,1},{1,0,1,1,1,0}
,{1,0,1,0,1,0},{0,1,0,1,0,1},{1,0,1,0,1,1},{1,1,0,0,0,1},{1,1,1,0,1,1},{0,1,1,0,1,0},{1,0,1,1,1,0}
,{1,1,1,1,1,0},{1,0,0,1,0,1},{1,1,1,1,0,1},{1,1,1,0,0,0},{1,0,0,1,1,1}};
    */
  

   //2.5mm = 9.4488188976 px
    //.5mm = 3.779527559
    //3.75mm = 14.173228346
   /*
    This draws a braille block 
        gc.fillOval(0, 0,3.7795275591 , 3.7795275591);
    
        gc.fillOval(9.4488188976, 0,3.7795275591 , 3.7795275591);
        gc.fillOval(0, 9.4488188976,3.7795275591 , 3.7795275591);
        gc.fillOval(9.4488188976, 9.4488188976,3.7795275591 , 3.7795275591);
        gc.fillOval(0, 9.4488188976*2,3.7795275591 , 3.7795275591);
        gc.fillOval(9.4488188976, 9.4488188976*2,3.7795275591 , 3.7795275591);
*/    
   
   

  
   double SpacingCounterX = 10;
   double SpacingCounterY=10;
   double Xcordinate =SpacingCounterX;
   double Ycordinate =SpacingCounterY;
   
   
  for(int j=0;j<alphabets.length;j++){
       
   
       int counter = 0;
   for(int i =0;i<alphabets[j].length;i++){
       
       counter++;
       if(alphabets[j][i] == 1){
           color = Color.BLACK;
           
      }else{
           color = Color.WHITE;    
       }
       
       
       //for space
       if(counter % 2 == 1){
           Circle circ = new Circle(Xcordinate, Ycordinate,3.7795275591 , color);
           drawingPane.getChildren().add(circ);
           Xcordinate = SpacingCounterX + 9.4488188976; 
           
           
       }if(counter % 2 == 0){
           Circle circ = new Circle(Xcordinate, Ycordinate,3.7795275591 , color);
           drawingPane.getChildren().add(circ);
           Xcordinate = SpacingCounterX;
           Ycordinate +=  9.4488188976;
       }
      
       
       
       
   }
   SpacingCounterX+=25;
   Xcordinate =SpacingCounterX;
   Ycordinate =SpacingCounterY;
 
   if(j %30 == 0 && j != 0){ //change here
       System.out.println(j);
     SpacingCounterX = 10; 
       SpacingCounterY += 40;
       Xcordinate =SpacingCounterX;
      Ycordinate =SpacingCounterY;
   
  }
   }
 drawingPane.setMinSize(Xcordinate, Ycordinate);
}



}
