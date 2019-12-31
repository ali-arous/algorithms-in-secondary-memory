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
import io.BufferedCharInputStream;
import io.BufferedCharOutputStream;
import io.CharInputStream;
import io.CharOutputStream;
import io.LineInputStream;
import io.LineOutputStream;
import io.MappedInputStream;
import io.MappedOutputStream;
import algorithmsinsecondarymemory.ExperimentsSet;
import java.lang.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class AlgorithmsInSecondaryMemory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // set machine input folder path
        ExperimentsSet.init();

        // experiment #1
        ExperimentsSet.sequential_reading();






/**
 * uncomment the relevant testMethod() call when you want to check if a method
 * is working well. 
 * Uncomment all of them if you want to compare their outputs.
 */        
         //final long startTime = System.currentTimeMillis();
          // ExperimentsSet.sequential_reading(1);
           //ExperimentsSet.sequential_reading(2);
           //ExperimentsSet.sequential_reading(3);
           // ExperimentsSet.sequential_reading(4);
           //ExperimentsSet.Random_reading(1,1);
           //ExperimentsSet.Random_reading(2,2);
           //ExperimentsSet.Random_reading(3,2);
           //ExperimentsSet.Random_reading(4,2);
           //ExperimentsSet.combined_reading_and_writing(1);//this is for the one file read and write
           //ExperimentsSet.combined_reading_and_writing(2);//this is for the one file read and write
           //ExperimentsSet.combined_reading_and_writing(3);//this is for the one file read and write
           //ExperimentsSet.combined_reading_and_writing(4);//this is for the one file read and write
           //ExperimentsSet.combined_reading_and_writing(5);//this is for the All file read and write
           //ExperimentsSet.combined_reading_and_writing(6);//this is for the All file read and write
           //ExperimentsSet.combined_reading_and_writing(7);//this is for the All file read and write
           //ExperimentsSet.combined_reading_and_writing(8);//this is for the All file read and write
           
          //final long duration = System.currentTimeMillis() - startTime;
          //System.out.println("the running time is "+"<"+duration+">"+" milliseconds");
          
//          MultiWayMergeSort mwms = new MultiWayMergeSort("CharStream");
//          mwms.exsort("keyword.csv", 0, 1000, 5);
          //  System.out.println("Hello World");
          //MultiWayMergeSort mwms=new MultiWayMergeSort("LineStream");
          //mwms.exsort("keyword.csv", 0, 1000, 5);
          
          //MultiWayMergeSort mwms=new MultiWayMergeSort("BufferedCharStream",5);
          //mwms.exsort("keyword.csv", 0, 1000, 5);

          //MultiWayMergeSort mwms=new MultiWayMergeSort("MappedStream",5);
          //mwms.exsort("keyword.csv", 0, 1000, 5);
          //Testing.compareTwoFiles("partial_output_927.txt", "keyword.csv");




//        Testing.clearOutputFolder();
//        for (int i=1;i<=4;i+=1) {
//            System.out.println("\n\nExperiment #"+i+":");
//            long start = System.currentTimeMillis();
//            Testing.testMethod(i);
//            long end = System.currentTimeMillis();
//            System.out.println("Time elapsed: "+(end-start));
//        }

    }
    
}
