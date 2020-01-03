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

/**
 * This class serves as a container for all experiment, where each experiment
 * is defined in side a static method, and called in the Main class.
 *
*/
public class ExperimentsSet {

    static String FolderPath = "C:\\Users\\webma\\Desktop\\BDMA\\Database Architecture\\Assignment\\";
    static String[] listOfFiles = {"keyword.csv","company_name.csv"};//,"title.csv", "cast_info.csv"};

    public static void init(){
        System.out.println("Initializing Experiments Set..");
        MethodContainer.setInputFolderPath(FolderPath);
    }


    public static void sequential_reading(){
        System.out.println("Running Experiment#1: [Sequential Reading]");

        for(String fileName:listOfFiles) {
            MethodContainer.setInputFileName(fileName);
            long size = MethodContainer.getFileSize(fileName);

            System.out.println("\nConsidering File: [" + fileName + "]  Of Size: [" + size + "] Byte");
            long output = 0, st = 0, ed = 0;

            st = System.currentTimeMillis();
            output = MethodContainer.charRead();
            ed = System.currentTimeMillis();
            System.out.println("\n    Method: [Char Reader]  |  OUPUT: " + output + ",  Time: " + (ed - st));

            st = System.currentTimeMillis();
            output = MethodContainer.lineRead();
            ed = System.currentTimeMillis();
            System.out.println("\n    Method: [Line Reader]  |  OUPUT: " + output + ",  Time: " + (ed - st));

            int[] bVal = {0, 0, 0, 0, 0, 0};
            //int[] bVal={88633571,44316785,22158392,11079196,5539598,346224};
            bVal[0] = (int) ((size / 100.0) * 0.001);
            bVal[1] = (int) ((size / 100.0) * 0.1);
            bVal[2] = (int) ((size / 100.0) * 10) + 1;
            bVal[3] = (int) ((size / 100.0) * 50) + 1;
            bVal[4] = (int) ((size / 100.0) * 100);
            bVal[5] = 1024;

            System.out.println("\n    Method: [Buffered Reader]");
            for (int b : bVal) {
                //b = 709068570 ;
                //b = 354534285;
                //b = 177267142;
                //b = 204800;
                st = System.currentTimeMillis();
                output = MethodContainer.bufferRead(b);
                ed = System.currentTimeMillis();
                System.out.println("            | B = " + b + " |  OUPUT: " + output + ",  Time: " + (ed - st));
                //break;
            }

            System.out.println("\n    Method: [Mapped Reader]");
            for (int b : bVal) {
                //b = 1418137140;
                //b = 709068570;
                //b = 354534285;
                //b = 177267142;
                //b = 88633571;
                //b = 1418;
                st = System.currentTimeMillis();
                output = MethodContainer.mappingRead(b);
                ed = System.currentTimeMillis();
                System.out.println("            | B = " + b + " |  OUPUT: " + output + ",  Time: " + (ed - st));
                //break;
            }
            System.out.println("---------------------------------------------\n\n");
            break;
        }
    }

    public static void random_reading(){
        System.out.println("Running Experiment#2: [Random Reading]");

        for(String fileName:listOfFiles){
            MethodContainer.setInputFileName(fileName);
            long size = MethodContainer.getFileSize(fileName);

            int[] jVal={0,0,0,0};
            jVal[0] = (int)((size/100.0) * 0.001);
            jVal[1] = (int)((size/100.0) * 0.1);
            jVal[2] = (int)((size/100.0) * 10);
            jVal[3] = (int)((size/100.0) * 50);

            System.out.println("\nConsidering File: ["+fileName+"]  Of Size: ["+size+"] Byte");
            long output=0, st=0, ed=0;

            System.out.println("\n    Method: [Char Reader]");
            for(int j: jVal){
                MethodContainer.setJ(j);
                st = System.currentTimeMillis(); output = MethodContainer.charRandomRead(); ed = System.currentTimeMillis();
                System.out.println("            | j = "+j+" |  OUPUT: "+output+",  Time: "+(ed-st));

            }

//
            System.out.println("\n    Method: [Line Reader]");
            for(int j: jVal){
                MethodContainer.setJ(j);
                st = System.currentTimeMillis(); output = MethodContainer.lineRandomRead(); ed = System.currentTimeMillis();
                System.out.println("            | j = "+j+" |  OUPUT: "+output+",  Time: "+(ed-st));

            }


            int b = (int)((size/100.0) * 0.1);
            System.out.println("\n    Method: [Buffered Reader]");
            for(int j: jVal){
                MethodContainer.setJ(j);
                st = System.currentTimeMillis(); output = MethodContainer.bufferRandomRead(b); ed = System.currentTimeMillis();
                System.out.println("            | j = "+j+" |  OUPUT: "+output+",  Time: "+(ed-st));
                //break;
            }

            System.out.println("\n    Method: [Mapped Reader]");
            for(int j: jVal){
                MethodContainer.setJ(j);
                st = System.currentTimeMillis(); output = MethodContainer.mappingRandomRead(b); ed = System.currentTimeMillis();
                System.out.println("            | j = "+j+" |  OUPUT: "+output+",  Time: "+(ed-st));
                //break;
            }
            System.out.println("---------------------------------------------\n\n");

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
