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
    static String[] listOfFiles = {"cast_info.csv"};//"keyword.csv", "company_name.csv", "title.csv", "cast_info.csv"};

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
            System.out.println("\n    Method: [Char Reader]  |  OUTPUT : " + output + ",  Time: " + (ed - st));

            st = System.currentTimeMillis();
            output = MethodContainer.lineRead();
            ed = System.currentTimeMillis();
            System.out.println("\n    Method: [Line Reader]  |  OUTPUT : " + output + ",  Time: " + (ed - st));

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
                System.out.println("            | B = " + b + " |  OUTPUT : " + output + ",  Time: " + (ed - st));
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
                System.out.println("            | B = " + b + " |  OUTPUT : " + output + ",  Time: " + (ed - st));
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
            //System.out.println(fileName+"  "+size);

            int[] jVal={1000000};//50,1000,10000,50000, 100000, 500000};
//            jVal[0] = (int)((size/100.0) * 0.001);
//            jVal[1] = (int)((size/100.0) * 0.1);
//            jVal[2] = (int)((size/100.0) * 10);
//            jVal[3] = 100;//(int)((size/100.0) * 50);
//
            System.out.println("\nConsidering File: ["+fileName+"]  Of Size: ["+size+"] Byte");
            long output=0, st=0, ed=0;
//
//            System.out.println("\n    Method: [Char Reader]");
//            for(int j: jVal){
//                MethodContainer.setJ(j);
//                st = System.currentTimeMillis(); output = MethodContainer.charRandomRead(); ed = System.currentTimeMillis();
//                System.out.println("            | j = "+j+" |  OUPUT: "+output+",  Time: "+(ed-st));
//
//            }
////
//////
//            System.out.println("\n    Method: [Line Reader]");
//            for(int j: jVal){
//                MethodContainer.setJ(j);
//                st = System.currentTimeMillis(); output = MethodContainer.lineRandomRead(); ed = System.currentTimeMillis();
//                System.out.println("            | j = "+j+" |  OUPUT: "+output+",  Time: "+(ed-st));
//
//            }
////
//
            int[] bVal={1, 32, 64, 128, 1024, 10240, 82000};
            //int b = 35604;//(int)((size/100.0) * 0.1);

            System.out.println("\n    Method: [Buffered Reader]");
            for(int j: jVal){
                MethodContainer.setJ(j);
                for (int b: bVal){
                    b=128;
                    st = System.currentTimeMillis(); output = MethodContainer.bufferRandomRead(b); ed = System.currentTimeMillis();
//                    System.out.println("            | j = "+j+" |  OUPUT: "+output+",  Time: "+(ed-st));
                    System.out.println("            | B = "+b+" |  OUPUT: "+output+",  Time: "+(ed-st));
                    break;
                }

                //break;
            }
//
            System.out.println("\n    Method: [Mapped Reader]");
            for(int j: jVal){
                MethodContainer.setJ(j);
                for (int b: bVal){
                    b = 64;
                    st = System.currentTimeMillis(); output = MethodContainer.mappingRandomRead(b); ed = System.currentTimeMillis();
//                    System.out.println("            | j = "+j+" |  OUPUT: "+output+",  Time: "+(ed-st));
                    System.out.println("            | B = "+b+" |  OUPUT: "+output+",  Time: "+(ed-st));
                    break;
                }
                //break;
            }
//            System.out.println("---------------------------------------------\n\n");

        }
    }


    public static void compined_reading_writing(){
        System.out.println("Running Experiment#3: [Combined Reading & Writing]");
        String[] testingFiles = {"keyword.csv", "company_name.csv", "movie_info_idx.csv", "movie_companies.csv"};//, "title.csv", "cast_info.csv"};

        MethodContainer.setOutputFolderPath("C:\\Users\\webma\\Documents\\NetBeansProjects\\Ali\\");
        MethodContainer.clearOutputFolder();
        System.out.print("\nTested files: ");
        for(int i=0;i<testingFiles.length-1;i++)
            System.out.print(testingFiles[i]+", ");
        System.out.println(testingFiles[testingFiles.length-1]);

        long st=0, ed=0;
        MethodContainer.setB2(128);

//        st = System.currentTimeMillis();
//        MethodContainer.mappedReaderLineWriter(testingFiles);
//        ed = System.currentTimeMillis();
//        System.out.print("MappedReader | LineWriter: ");
//        System.out.println(""+(ed-st)/1000.0+" seconds");
//
//
//        st = System.currentTimeMillis();
//        MethodContainer.mappedReaderCharWriter(testingFiles);
//        ed = System.currentTimeMillis();
//        System.out.print("MappedReader | CharWriter: ");
//        System.out.println(""+(ed-st)/1000.0+" seconds");
//

        int[] bVal = { 64, 512, 2048, 8192, 819200, 8192000 };
        for(int b:bVal) {
            MethodContainer.clearOutputFolder();
            st = System.currentTimeMillis();
            int b1 = b;
            MethodContainer.mappedReaderBufferedWriter(testingFiles, b1);
            ed = System.currentTimeMillis();
            System.out.print("MappedReader | BufferedWriter [B=" + b1 + "]: ");
            System.out.println("" + (ed - st) / 1000.0 + " seconds");


            st = System.currentTimeMillis();
            int b2 = b;
            MethodContainer.mappedReaderMappedWriter(testingFiles, b2);
            ed = System.currentTimeMillis();
            System.out.print("MappedReader | MappedWriter [B=" + b2 + "]: ");
            System.out.println("" + (ed - st) / 1000.0 + " seconds");

            System.out.println("--------------------------------------------\n");
//        st = System.currentTimeMillis();
//        MethodContainer.lineReaderLineWriter(testingFiles);
//        ed = System.currentTimeMillis();
//        System.out.print("LineReader | LineWriter: ");
//        System.out.println(""+(ed-st)/1000.0+" seconds");
//
//
//        st = System.currentTimeMillis();
//        MethodContainer.lineReaderCharWriter(testingFiles);
//        ed = System.currentTimeMillis();
//        System.out.print("LineReader | CharWriter: ");
//        System.out.println(""+(ed-st)/1000.0+" seconds");
//
//
//        int[] bVal = { 819200 };//512, 2048, 8192, 819200, 8192000 };
//        for(int b:bVal) {
//            MethodContainer.clearOutputFolder();
//            st = System.currentTimeMillis();
//            int b1 = b;
//            MethodContainer.lineReaderBufferedWriter(testingFiles, b1);
//            ed = System.currentTimeMillis();
//            System.out.print("LineReader | BufferedWriter [B=" + b1 + "]: ");
//            System.out.println("" + (ed - st) / 1000.0 + " seconds");
//
//
//            st = System.currentTimeMillis();
//            int b2 = b;
//            MethodContainer.lineReaderMappedWriter(testingFiles, b2);
//            ed = System.currentTimeMillis();
//            System.out.print("LineReader | MappedWriter [B=" + b2 + "]: ");
//            System.out.println("" + (ed - st) / 1000.0 + " seconds");
//
//            System.out.println("--------------------------------------------\n");
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
