/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmsinsecondarymemory;

import io.InputStream;
import io.OutputStream;
import io.CharInputStream;
import io.CharOutputStream;
import io.LineInputStream;
import io.LineOutputStream;
import io.BufferedCharInputStream;
import io.BufferedCharOutputStream;
import io.MappedInputStream;
import io.MappedOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author webma
 */
public class Testing {
    // put here the path to the folder of input files (e.g: C:/Users/ali/desktop/dba/imdb/)
    private static final String INPUT_FOLDER_PATH = "C:\\Users\\webma\\Desktop\\BDMA\\Database Architecture\\Assignment\\";
    
    // put here the path to the folder of output files (e.g: C:/Users/ali/desktop/dba/outputs/)
    private static final String OUTPUT_FOLDER_PATH = "C:\\Users\\webma\\Documents\\NetBeansProjects\\AlgorithmsInSecondaryMemory\\";
    
    // put here the name of any file from imdb dataset in order to use it for testing
    private static final String INPUT_FILE_NAME ="keyword.csv";
    
    // put here the prefix you want to use for output files
    private static final String OUTPUT_FILE_NAME_PREFIX ="output";
    
    // put here the buffer size for method #3
    private static final int B1 = 5;
    
    // put here the buffer size for method #4
    private static final int B2 = 5;
    
    
   /** call this method with i = {1|2|3|4} to validate 
    * the correctness of the four methods by comparing their outputs**/
    public static void testMethod(int i){
        
        if(i<1 || i>4){
            System.out.println("The number of method to be tested should be in {1,2,3,4}");
            return;
        }
        
        InputStream r;
        OutputStream w;
        if(i==1){
            r = new CharInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME);
            w = new CharOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+i+".txt");
        } else if(i==2){
            r = new LineInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME);
            w = new LineOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+i+".txt");
        } else if(i==3){
            r = new BufferedCharInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME,B1);
            w = new BufferedCharOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+i+".txt", B1);
        } else{
            r = new BufferedCharInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME,B2);
            w = new BufferedCharOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+i+".txt", B2);            
        }
        
        /** This is a simple testing algorithm to validate the soundness of output
         * of each method, by called testMethod() on method's number, and checking
         * if the four methods generate identical files in the output.
         * 
         * In principal, it comprises the following steps:
         * 1. open input file
         * 2. seek to position 1000
         * 3. read til end of line
         * 4. write the read string into the output file
         * 5. seek to position 1500
         * 6. read til end of line
         * 7. write the read string into the output file
         * 8. seek to position 100
         * 9. read til end of line
         * 10. write the read string into the output file
         * 11. read each remaining line in input file and write
         * it to the output file
         * 12. close output file to save the results
         */
        System.out.println("==== Start of testing experiment ====");
        r.seek(1000); 
        w.writeln(r.readln());
        r.seek(1500);
        w.writeln(r.readln());
        r.seek(100);
        w.writeln(r.readln());
        while(!r.end_of_stream()){
            w.writeln(r.readln());
        }
        w.close();
        System.out.println("==== End of testing experiment ====");
    }
    
    
    public static void compareTwoFiles(String file1, String file2){
        InputStream r1 = new LineInputStream(INPUT_FOLDER_PATH+file1);
        InputStream r2 = new LineInputStream(INPUT_FOLDER_PATH+file2);

        Set<String> lines = new HashSet<>();
        while(!r1.end_of_stream()){
            String l=r1.readln();
            if(l.length()>0)
                lines.add(l);
        }
        while(!r2.end_of_stream()){
            String l=r2.readln();
            if(l.length()>0)
                if (!lines.contains(l))
                    System.out.println(l);
                
        }
    }
}
