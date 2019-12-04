/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmsinsecondarymemory;

import io.BufferedCharInputStream;
import io.BufferedCharOutputStream;
import io.CharInputStream;
import io.CharOutputStream;
import io.LineInputStream;
import io.LineOutputStream;
import io.MappedInputStream;
import io.MappedOutputStream;
import algorithmsinsecondarymemory.Method;
/**
 *
 * @author webma
 */

/**
 * This class serves as a container for all experiment, where each experiment
 * is defined in side a static method, and called in the Main class.
 *
*/
public class ExperimentsSet {
    
    public static void sequential_reading(int i){
      if(i<1 || i>4){
            System.out.println("The number of method to be tested should be in {1,2,3,4}");
            return;
        }
       if(i==1){
           int E1=Method.Line_read();
           System.out.println("this method is the line reader");
           System.out.println(E1);
           
        } else if(i==2){
          int E=Method.char_read();
          System.out.println("this method is the char reader");
           System.out.println(E);
        } else if(i==3){
         int E=Method.Buffer_read();
           System.out.println("this method is the buffer reader");
           System.out.println(E);
        } else{
           int E=Method.mapping_read();
           System.out.println("this method is the mappin reader");
           System.out.println(E);   
        }
  
     

    }
    
    public static void Random_reading(int i,int j){
         if(i<1 || i>4){
             
            System.out.println("The number of method to be tested should be in {1,2,3,4}");
            return;
        }
         
       if(i==1){
           for (int k=1 ;k<=j;k++){
           int E1=Method.Line_readRandom();
           System.out.println("this method is the line reader random");
           System.out.println(E1);
           }
           
        } else if(i==2){
             for (int k=1 ;k<=j;k++){
          int E=Method.char_readRandom();
          System.out.println("this method is the char reader random");
           System.out.println(E);
             }
        } else if(i==3){
            for (int k=1 ;k<=j;k++){
                int E=Method.Buffer_readRandom();
                System.out.println("this method is the buffer reader random");
                System.out.println(E);}
        } else{
          for (int k=1 ;k<=j;k++){  
           int E=Method.mapping_readRandom();
           System.out.println("this method is the mappin reader random");
           System.out.println(E);
          }
        }
    }
    
    public static void combined_reading_and_writing(int i){
         if(i<1 || i>4){
            System.out.println("The number of method to be tested should be in {1,2,3,4}");
            return;
        }
       if(i==1){
           Method.line_read_write();
           System.out.println("this method is the line reader wrieter with one file");
           
        } else if(i==2){
          Method.char_read_write();
          System.out.println("this method is the char reader wrieter with one file");
        } else if(i==3){
         Method.buffer_read_write();
           System.out.println("this method is the buffer reader wrieter with one file");
           
        } else if(i==4){
           Method.mapping_read_write();
           System.out.println("this method is the mappin wrieter with one file");
              
        }else if(i==5){
            Method.line_read_write_All();
            System.out.println("this method is the line reader wrieter Of ALL file");
        }else if(i==6){
             Method.char_read_write_All();
             System.out.println("this method is the char reader wrieter Of ALL file");
        }else if (i==7){
           Method.buffer_read_write_All();
           System.out.println("this method is the buffer reader wrieter Of ALL file");
        }else{
           Method.mapping_read_write_ALl();
           System.out.println("this method is the mappin wrieter Of ALL file");
        }
    }
    
    public static void Multi_way_merge(){
        // code for experiment 4
    }
}
