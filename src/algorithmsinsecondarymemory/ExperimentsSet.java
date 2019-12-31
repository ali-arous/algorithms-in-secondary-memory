/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmsinsecondarymemory;

/**
 *
 * @author webma
 */

import java.lang.invoke.MethodHandle;

/**
 * This class serves as a container for all experiment, where each experiment
 * is defined in side a static method, and called in the Main class.
 *
*/
public class ExperimentsSet {

    static String FolderPath = "C:\\Users\\webma\\Desktop\\BDMA\\Database Architecture\\Assignment\\";
    static String[] listOfFiles = {"keyword.csv","company_name.csv","title.csv", "cast_info.csv"};

    public static void init(){
        System.out.println("Initializing Experiments Set..");
        MethodContainer.setInputFolderPath(FolderPath);
    }


    public static void sequential_reading(){
        System.out.println("Running Experiment#1: [Sequential Reading]");

        for(String fileName:listOfFiles){
            MethodContainer.setInputFileName(fileName);
            long size = MethodContainer.getFileSize(fileName);

            System.out.println("\nConsidering File: ["+fileName+"]  Of Size: ["+size+"] Byte");
            long output=0, st=0, ed=0;

            st = System.currentTimeMillis(); output = MethodContainer.charRead(); ed = System.currentTimeMillis();
            System.out.println("\n    Method: [Char Reader]  |  OUPUT: "+output+",  Time: "+(ed-st));

            st = System.currentTimeMillis(); output = MethodContainer.lineRead(); ed = System.currentTimeMillis();
            System.out.println("\n    Method: [Line Reader]  |  OUPUT: "+output+",  Time: "+(ed-st));

            int[] bVal={0,0,0,0,0};
            bVal[0] = (int)((size/100.0) * 0.001);
            bVal[1] = (int)((size/100.0) * 0.1);
            bVal[2] = (int)((size/100.0) * 10);
            bVal[3] = (int)((size/100.0) * 50);
            bVal[4] = (int)((size/100.0) * 100);

            System.out.println("\n    Method: [Buffered Reader]");
            for(int b: bVal){
                MethodContainer.setB1(b);
                st = System.currentTimeMillis(); output = MethodContainer.bufferRead(); ed = System.currentTimeMillis();
                System.out.println("            | B = "+b+" |  OUPUT: "+output+",  Time: "+(ed-st));
            }

            System.out.println("\n    Method: [Mapped Reader]");
            for(int b: bVal){
                MethodContainer.setB2(b);
                st = System.currentTimeMillis(); output = MethodContainer.mappingRead(); ed = System.currentTimeMillis();
                System.out.println("            | B = "+b+" |  OUPUT: "+output+",  Time: "+(ed-st));
            }
            System.out.println("---------------------------------------------\n\n");
        }


//
//      if(i<1 || i>4){
//            System.out.println("The number of method to be tested should be in {1,2,3,4}");
//            return;
//        }
//       if(i==1){
//           int E1= MethodContainer.lineRead();
//           System.out.println("this method is the line reader");
//           System.out.println(E1);
//
//        } else if(i==2){
//          int E= MethodContainer.charRead();
//          System.out.println("this method is the char reader");
//           System.out.println(E);
//        } else if(i==3){
//         int E= MethodContainer.bufferRead();
//           System.out.println("this method is the buffer reader");
//           System.out.println(E);
//        } else{
//           int E= MethodContainer.mappingRead();
//           System.out.println("this method is the mappin reader");
//           System.out.println(E);
//        }



    }

    public static void Random_reading(int i,int j){
         if(i<1 || i>4){

            System.out.println("The number of method to be tested should be in {1,2,3,4}");
            return;
        }

       if(i==1){
           for (int k=1 ;k<=j;k++){
           int E1= MethodContainer.Line_readRandom();
           System.out.println("this method is the line reader random");
           System.out.println(E1);
           }

        } else if(i==2){
             for (int k=1 ;k<=j;k++){
          int E= MethodContainer.char_readRandom();
          System.out.println("this method is the char reader random");
           System.out.println(E);
             }
        } else if(i==3){
            for (int k=1 ;k<=j;k++){
                int E= MethodContainer.Buffer_readRandom();
                System.out.println("this method is the buffer reader random");
                System.out.println(E);}
        } else{
          for (int k=1 ;k<=j;k++){
           int E= MethodContainer.mapping_readRandom();
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
           MethodContainer.line_read_write();
           System.out.println("this method is the line reader wrieter with one file");

        } else if(i==2){
          MethodContainer.char_read_write();
          System.out.println("this method is the char reader wrieter with one file");
        } else if(i==3){
         MethodContainer.buffer_read_write();
           System.out.println("this method is the buffer reader wrieter with one file");

        } else if(i==4){
           MethodContainer.mapping_read_write();
           System.out.println("this method is the mappin wrieter with one file");

        }else if(i==5){
            MethodContainer.line_read_write_All();
            System.out.println("this method is the line reader wrieter Of ALL file");
        }else if(i==6){
             MethodContainer.char_read_write_All();
             System.out.println("this method is the char reader wrieter Of ALL file");
        }else if (i==7){
           MethodContainer.buffer_read_write_All();
           System.out.println("this method is the buffer reader wrieter Of ALL file");
        }else{
           MethodContainer.mapping_read_write_ALl();
           System.out.println("this method is the mappin wrieter Of ALL file");
        }
    }

    public static void Multi_way_merge(){
        // code for experiment 4
    }
}
