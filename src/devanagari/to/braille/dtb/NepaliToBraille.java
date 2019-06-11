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


import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;

/**
 *
 * @author AxisDroid
 */
public class NepaliToBraille {
     private String Sentence;
    public NepaliToBraille(String Sentence){
      this.Sentence = " "+ Sentence+"३  ";
      
    }
 

    public int[][] convert(){
           
      /*  
       used this code to yeild out raswos and dirgas in barha khari
       
       + used to test out ka kha ga or nepali alpabet in terms of integer 
       
         //barha khari
        // का की कि कु कू के कै को कौ क कं 
         //String BarhaKhari = "का की कि कु कू के कै को कौ क कं ";
        String vowelNepali = "का की कि कु कू के कै को कौ क कं ";
        char [] sosNum = sos.toCharArray();
        for(int i=0;i<sosNum.length;i++){
            if(!String.valueOf(sosNum[i]).equals("क")){
            System.out.print(sosNum[i]);
            }
        
    }
        */
      
     
       
      
      List AlphabetArray = new ArrayList();
      
       
       
      //braille consonant character
      int brailleAlphabets[][]=
      {{1,0,0,0,0,0,},{0,1,0,1,1,0},{0,1,1,0,0,0},{0,0,0,1,1,0},{1,0,0,0,1,1},{1,0,1,1,0,1}
  ,/*ri*/{1,0,1,1,1,0},/*chinise ri*/{0,0,0,0,0,0},/*chandrabindu ye*/{1,0,0,1,0,0}, {0,0,1,0,0,1},{1,0,0,1,0,0},
  {0,1,0,0,1,0},/*chandrabindu aa*/{0,0,0,0,0,0},{1,1,0,0,1,1},{1,0,0,1,1,0},{0,1,1,0,0,1},{1,0,0,0,1,0},{0,1,0,0,0,1},{1,1,1,1,0,0},{1,0,1,0,0,1},{0,1,0,0,1,1},
       {1,1,0,0,0,0},{1,0,0,0,0,1},{0,1,1,1,0,0},{0,0,0,1,1,1},{0,0,1,1,0,0},
       {0,1,1,1,1,1},{0,1,1,1,0,1},{1,1,1,0,0,1},{1,1,1,1,1,1},{0,1,0,1,1,1},
       {0,1,1,1,1,0},{1,1,0,1,0,1},{1,1,0,1,0,0},{0,1,1,0,1,1},{1,1,0,1,1,0},{0,0,0,0,0,0}/*ऩ*/,
       {1,1,1,0,1,0},{0,0,1,1,1,0},{1,0,1,0,0,0},{0,1,0,1,0,0},{1,1,0,0,1,0},
       {1,1,0,1,1,1},{1,0,1,1,1,0},{0,0,0,0,0,0}/*ऱ*/,{1,0,1,0,1,0},{0,1,0,1,0,1},{0,0,0,0,0,0}/*ऴ*/,{1,0,1,0,1,1},
       {1,1,0,0,0,1},{1,1,1,0,1,1},{0,1,1,0,1,0},{1,0,1,1,1,0},/*क्ष*/{1,1,1,1,1,0},/*ज्ञ*/{1,0,0,1,0,1},{1,1,1,1,0,1},{1,1,1,0,0,0},{1,0,0,1,1,1}}; //no क्ष and ज्ञ
    
        //note: क्ष and ज्ञ doesn't have literal String to char translation so a handler is required
        // *ड़ फ ज़ have their presence at the end of 300s
     
        
      
    //for codas (halant, anusvara ,visarga, chandrabindu etc
    int CodasCharacters[][]={{0,1,0,0,0,0},{0,0,0,1,0,1,},{0,0,0,0,0,1},{0,0,0,0,1,0},{0,0,1,0,0,0}};
   //for punctuation
   int PunctuationCharacters[][]={{0,0,1,0,0,0},{0,0,1,0,1,0},{0,0,1,1,0,0},{0,0,1,1,0,1},{0,0,1,0,1,1},{0,0,1,1,1,0},{0,0,1,0,1,1},{0,0,0,1,1,1},{0,0,0,0,1,0},{0,0,1,1,1,1},{0,1,0,0,0,1},{1,0,1,0,0,1},{0,1,0,0,0,1},{1,0,1,0,1,0},{0,1,0,1,0,1},{1,0,1,0,0,1},{0,1,0,1,0,1},{1,0,1,0,1,0},{0,0,0,0,1,1},{0,0,0,1,0,0},{0,0,0,1,1,0},{0,0,0,0,0,0}};
   int BrailleNumbers[][]={{0,1,1,1,0,0},{1,0,0,0,0,0},{1,0,1,0,0,0},{1,1,0,0,0,0},{1,1,0,1,0,0},{1,0,0,1,0,0},{1,1,1,0,0,0},{1,1,1,1,0,0},{1,0,1,1,0,0},{0,1,1,0,0,0},{0,1,0,1,1,1}};
    

        
    
               
   
    
   
            
//for converting vowels code to real vowels for example : सभी मनुष्यों to सभई मनउष्यओ
      char VowelsCode[] ={'ा','ि','ी','ु','ू','े','ै','ो','ौ'};
      //2366 2367 2368 2369 2370 2375 2376 2379 2380
      char Vowels[]={'आ','इ','ई','उ','ऊ','ए','ऐ','ओ','औ'};
      
      int finalArraySize=Sentence.length();
      
   
      char alphabet[] = Sentence.toCharArray();
      
     for(int j = 0;j<alphabet.length;j++){
       
       AlphabetArray.add(alphabet[j]);   
       for(int i =0;i<VowelsCode.length;i++){
          if(alphabet[j] == VowelsCode[i]){
              alphabet[j] =Vowels[i];
              AlphabetArray.remove(j);
              AlphabetArray.add(alphabet[j]);
          }
           
          
          if(alphabet[j]=='०'||alphabet[j]=='१'||alphabet[j]=='२'||alphabet[j]=='३'||alphabet[j]=='४'||alphabet[j]=='५'||alphabet[j]=='६'||alphabet[j]=='७'||alphabet[j]=='८'||alphabet[j]=='९'){
          AlphabetArray.add(j-1,' ');
              while((int)alphabet[j]>=2406 && (int)alphabet[j]<=2415){
             AlphabetArray.add(alphabet[j]);
           //increment j until all digits are counted so that we can add number sign
                  
           j++;
             
          }
         
          finalArraySize++;
          }
          
          //for dual block punctuations
          switch(alphabet[i]){
              case '[':
              case ']':
              case '{':
              case '}':
              
                  AlphabetArray.add(' ');
                  finalArraySize++;
              
                  
          }
      //for halant (vowel cancelling prefix.
      // only these letters have halant or ् "क् ख् ग् घ् च् ज् त् थ् ध् द् न् प् फ् ब् भ् म् ल् व् श् ष् स् ह् क्ष् ";
      /*  if(alphabet[j]=='्'){
            alphabet[j]=alphabet[j-1];
            alphabet[j-1]='्';
         
        }
*/
          
      
        
       }
       
}//end of loop
    
   
      
      //final array of braille
    int finalArray[][] = new int[finalArraySize][6];
    char SingleAlphabet[] = new char[finalArraySize];
      
    for(int p=0;p<finalArraySize;p++){
        
        SingleAlphabet[p]= (char) AlphabetArray.get(p);
        
        
    }
     
    
   
      //real conversion starts from here
      
         for(int i=0;i<SingleAlphabet.length;i++){
      
                     
             if((int)SingleAlphabet[i]>=2308 && (int)SingleAlphabet[i] <= 2361){
            
             finalArray[i] = brailleAlphabets[getAlphabetNumber(SingleAlphabet[i])];
                
            }
            //codas and punctuation
                         switch(SingleAlphabet[i]){
                 case '्':
                 finalArray[i] =CodasCharacters[0];
                 break;
                 case 'ं':                     
                 finalArray[i]=CodasCharacters[1];
                 break;
                 case 'ः':
                 finalArray[i]=CodasCharacters[2];
                 break;
                 case 'ँ':
                 finalArray[i]=CodasCharacters[3];
                 break; 
                 case 'ऽ':
                 finalArray[i]=CodasCharacters[4];
                 break;
                 //punctuation
                 case ',':
                 finalArray[i]=PunctuationCharacters[0];
                 break;
                 case ';':
                 finalArray[i]=PunctuationCharacters[1];
                 break;
                 case ':':
                 finalArray[i]=PunctuationCharacters[2];
                 break;
                 case '|':
                 case '.':
                 finalArray[i]=PunctuationCharacters[3];
                 break;
                 case '?':
                 finalArray[i]=PunctuationCharacters[4];
                 break;
                 case '!':
                 finalArray[i]=PunctuationCharacters[5];
                 break;
                 case '“':
                    finalArray[i]=PunctuationCharacters[6];
                    break;
                 case '”':
                     finalArray[i]=PunctuationCharacters[7];
                    
                 case '\'':
                     finalArray[i]=PunctuationCharacters[8];
                 case '(':
                 case ')':
                     finalArray[i]=PunctuationCharacters[9];
                 break;
                 case '[':
                 finalArray[i-1]=PunctuationCharacters[10];
                 finalArray[i]=PunctuationCharacters[11];
                 
                 break;
                 case ']':
                 finalArray[i-1]=PunctuationCharacters[12];
                 finalArray[i]=PunctuationCharacters[13];
                 
                 break;
                 case '{':
                 finalArray[i-1]=PunctuationCharacters[14];
                 finalArray[i]=PunctuationCharacters[15];
                
                 break;
                 case '}':
                 finalArray[i-1]=PunctuationCharacters[16];
                 finalArray[i]=PunctuationCharacters[17];
                 
                 break;
                 case '-':
                 finalArray[i]=PunctuationCharacters[18];
                 break;
                 case '*':
                 finalArray[i-1]=PunctuationCharacters[19];
                 finalArray[i]=PunctuationCharacters[20];
                 break;
                 //space
                 case ' ':
                     if(i<finalArraySize){
                 if((int)SingleAlphabet[i+1]>=2406 && (int)SingleAlphabet[i+1]<=2415){
                     finalArray[i]=BrailleNumbers[10];
                 }else{
                     finalArray[i]=PunctuationCharacters[21];
                 }
                     }
                 break;
                 }
         
         //numbers
          if((int)SingleAlphabet[i]>=2406 && (int)SingleAlphabet[i]<=2415){
             
              
          while((int)SingleAlphabet[i]>=2406 && (int)SingleAlphabet[i]<=2415){
           //increment j until all digits are counted so that we can add number sign
             
             
           switch(SingleAlphabet[i]){
               
               case '०':
                  
                  finalArray[i]=BrailleNumbers[0];
                  break;              
               case '१':
                 
                 finalArray[i]=BrailleNumbers[1];
                 break;    
               case '२':
                 
                   finalArray[i]=BrailleNumbers[2];
                 break; 
               case '३':
                 
                   finalArray[i]=BrailleNumbers[3];
                 break; 
               case '४':
                 
                   finalArray[i]=BrailleNumbers[4];
                 break; 
               case '५':
                  
                   finalArray[i]=BrailleNumbers[5];
                 break; 
               case '६':
                    
                   finalArray[i]=BrailleNumbers[6];
                 break; 
               case '७':
                    
                   finalArray[i]=BrailleNumbers[7];
                 break; 
               case '८':
                    
                   finalArray[i]=BrailleNumbers[8];
                 break;            
               case '९':
                    
                   finalArray[i]=BrailleNumbers[9];
                 break;        
          
           }
            i++;
              
          }
          
          }
          
          
             
   }
         int j=0;
       for (int[] finalArray1 : finalArray) {
           j++;
           System.out.print("{");
           for(int p=0;p<6;p++){
               if(p!=5){
           System.out.print(finalArray1[p]+",");
               }else{
                   System.out.print(finalArray1[p]);
               }
           }
           System.out.print("},");
           if(j%10==0){
               System.out.println(" ");
           }
           }
        
        return finalArray;
   }
    
    
    static   int getAlphabetNumber(char c){
         return (int)c-2309 ;  //starts from 2309
}
 static int getVowelPlace(char c){
    return (int)c>=2366 && (int)c<=2370?(int)c-2366:(int)c>=2375&&(int)c<=2376?(int)c-2370:(int)c>=2379&&(int)c<=2380?c-2372:0;
 }
}




