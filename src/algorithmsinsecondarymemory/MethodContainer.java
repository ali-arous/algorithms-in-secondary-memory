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
import io.InputStream;
import io.LineInputStream;
import io.LineOutputStream;
import io.MappedInputStream;
import io.MappedOutputStream;
import io.OutputStream;
import jdk.internal.util.xml.impl.Input;

import javax.xml.stream.FactoryConfigurationError;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author epcs
 */
public class MethodContainer {
    public static String INPUT_FOLDER_PATH = "C:\\Users\\epcs\\Downloads\\imdb\\";
    
    public static String OUTPUT_FOLDER_PATH = "C:\\Users\\epcs\\Downloads\\AlgorithmsInSecondaryMemory\\AlgorithmsInSecondaryMemory\\test\\";
    
    public static String INPUT_FILE_NAME ="keyword.csv";
    
    public static String OUTPUT_FILE_NAME_PREFIX ="output";
    
    public static int B1 = 30;
    
    public static int B2 = 5;

    public static int j = 0;

    public static void setInputFolderPath(String s){
        INPUT_FOLDER_PATH = s;
    }

    public static void setOutputFolderPath(String s){
        OUTPUT_FOLDER_PATH = s;
    }

    public static void setInputFileName(String s){
        INPUT_FILE_NAME = s;
    }

    public static void setOutputFileNamePrefix(String s){
        OUTPUT_FILE_NAME_PREFIX = s;
    }

    public static void setB1(int val){
        B1 = val;
    }

    public static void setB2(int val){
        B2 = val;
    }

    public static void setJ(int val){ j = val; }

    public static long getFileSize(String file){
        InputStream r = new LineInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME);
        return r.size();
    }

    public static int length(InputStream sreader){
        int sum = 0;
        while (!sreader.end_of_stream()) {
            sum = sum + sreader.readln().length();
        }
        sreader.close();
        return sum;
    }
    public static int lineRead() {
        InputStream r = new LineInputStream(INPUT_FOLDER_PATH + INPUT_FILE_NAME);
        return length(r);
    }

    public static long charRead() {
        InputStream r = new CharInputStream(INPUT_FOLDER_PATH + INPUT_FILE_NAME);
        return length(r);
    }

    public static long bufferRead(int b) {
        InputStream r = new BufferedCharInputStream(INPUT_FOLDER_PATH + INPUT_FILE_NAME, b);
        return length(r);
    }

    public static long mappingRead(int b) {
        InputStream r = new MappedInputStream(INPUT_FOLDER_PATH + INPUT_FILE_NAME, b);
        return length(r);
    }



// ======================================================================================= //
// ======================================================================================= //
// ======================================================================================= //


    public static int randjump(InputStream sreader){
        int sum = 0;
        Random rand = new Random();
        int fileLen = (int)sreader.size();

        for(int i=0;i<j;i++){
            int r = rand.nextInt(fileLen);
            sreader.seek(r);
            sum+=sreader.readln().length();
        }
        sreader.close();
        return sum;
    }

    public static int lineRandomRead() {
        InputStream r = new LineInputStream(INPUT_FOLDER_PATH + INPUT_FILE_NAME);
        return randjump(r);
    }
      
    public static int charRandomRead(){
        InputStream r = new CharInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME);
        return randjump(r);
    }

    public static int bufferRandomRead(int b) {
        InputStream r = new BufferedCharInputStream(INPUT_FOLDER_PATH + INPUT_FILE_NAME, b);
        return randjump(r);
    }

    public static int mappingRandomRead(int b) {
        InputStream r = new MappedInputStream(INPUT_FOLDER_PATH + INPUT_FILE_NAME, b);
        return randjump(r);
    }
       //we have to read all the files that we have and put them in one file with this method 
       //so we can look how to change tha path method


// ======================================================================================= //
// ======================================================================================= //
// ======================================================================================= //


    public static void rrmerge(ArrayList<InputStream> readers, OutputStream w){
        boolean allExhausted = false;
        while(!allExhausted){
            allExhausted = true;
            Iterator<InputStream> i = readers.iterator();
            while(i.hasNext()){
                InputStream r = i.next();
                if(!r.end_of_stream()){
                    w.writeln(r.readln());
                    allExhausted = false;
                } else {
                    r.close();
                    i.remove();
                }
            }
        }
        w.close();
    }


    public static void lineReaderLineWriter(String[] files){
        OutputStream w = new LineOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+"_ll.txt");
        ArrayList<InputStream> readers = new ArrayList<>();
        for(String fileName: files){
            InputStream r = new LineInputStream(INPUT_FOLDER_PATH+fileName);
            readers.add(r);
        }
        rrmerge(readers, w);
    }

    public static void lineReaderCharWriter(String[] files){
        OutputStream w = new CharOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+"_lc.txt");
        ArrayList<InputStream> readers = new ArrayList<>();
        for(String fileName: files){
            InputStream r = new LineInputStream(INPUT_FOLDER_PATH+fileName);
            readers.add(r);
        }
        rrmerge(readers, w);
    }

    public static void lineReaderBufferedWriter(String[] files, int b){
        OutputStream w = new BufferedCharOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+"_lb.txt", b);
        ArrayList<InputStream> readers = new ArrayList<>();
        for(String fileName: files){
            InputStream r = new LineInputStream(INPUT_FOLDER_PATH+fileName);
            readers.add(r);
        }
        rrmerge(readers, w);
    }

    public static void lineReaderMappedWriter(String[] files, int b){
        OutputStream w = new MappedOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+"_lm.txt", b);
        ArrayList<InputStream> readers = new ArrayList<>();
        for(String fileName: files){
            InputStream r = new LineInputStream(INPUT_FOLDER_PATH+fileName);
            readers.add(r);
        }
        rrmerge(readers, w);
    }


    public static void mappedReaderLineWriter(String[] files){
        OutputStream w = new LineOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+"_ml.txt");
        ArrayList<InputStream> readers = new ArrayList<>();
        for(String fileName: files){
            InputStream r = new MappedInputStream(INPUT_FOLDER_PATH+fileName, B2);
            readers.add(r);
        }
        rrmerge(readers, w);
    }


    public static void mappedReaderCharWriter(String[] files){
        OutputStream w = new CharOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+"_mc.txt");
        ArrayList<InputStream> readers = new ArrayList<>();
        for(String fileName: files){
            InputStream r = new MappedInputStream(INPUT_FOLDER_PATH+fileName, B2);
            readers.add(r);
        }
        rrmerge(readers, w);
    }


    public static void mappedReaderBufferedWriter(String[] files, int b){
        OutputStream w = new BufferedCharOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+"_mb.txt", b);
        ArrayList<InputStream> readers = new ArrayList<>();
        for(String fileName: files){
            InputStream r = new MappedInputStream(INPUT_FOLDER_PATH+fileName, B2);
            readers.add(r);
        }
        rrmerge(readers, w);
    }


    public static void mappedReaderMappedWriter(String[] files, int b){
        OutputStream w = new MappedOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+"_mm.txt", b);
        ArrayList<InputStream> readers = new ArrayList<>();
        for(String fileName: files){
            InputStream r = new MappedInputStream(INPUT_FOLDER_PATH+fileName, B2);
            readers.add(r);
        }
        rrmerge(readers, w);
    }



// ======================================================================================= //
// ======================================================================================= //
// ======================================================================================= //



    public static void line_read_write() {
        InputStream r;
        OutputStream w;
        r = new LineInputStream(INPUT_FOLDER_PATH + INPUT_FILE_NAME);
        w = new LineOutputStream(OUTPUT_FOLDER_PATH + OUTPUT_FILE_NAME_PREFIX + "new" + ".txt");
        while (!r.end_of_stream()) {
            w.writeln(r.readln());
        }
        w.close();

    }

    public static void line_read_write_All() {
        InputStream r;
        OutputStream w;
        String M = "";
        File aDirectory = new File(INPUT_FOLDER_PATH);
        String[] filesInDir = aDirectory.list();
        for (int i = 0; i < filesInDir.length; i++) {
            r = new LineInputStream(INPUT_FOLDER_PATH + filesInDir[i]);

            while (!r.end_of_stream()) {
                // w.writeln(r.readln());
                M = M + "\n" + r.readln();
            }
        }
        w = new LineOutputStream("C:\\Users\\epcs\\Downloads\\imdb\\test\\" + OUTPUT_FILE_NAME_PREFIX + "new" + ".txt");
        w.writeln(M);
        w.close();

    }

    public static void char_read_write() {
        InputStream r;
        OutputStream w;
        r = new CharInputStream(INPUT_FOLDER_PATH + INPUT_FILE_NAME);
        w = new CharOutputStream(OUTPUT_FOLDER_PATH + OUTPUT_FILE_NAME_PREFIX + "new" + ".txt");
        while (!r.end_of_stream()) {
            w.writeln(r.readln());
        }
        w.close();

    }

    public static void char_read_write_All() {
        InputStream r;
        OutputStream w;
        String M = "";
        File aDirectory = new File(INPUT_FOLDER_PATH);
        String[] filesInDir = aDirectory.list();
        for (int i = 0; i < filesInDir.length; i++) {
            r = new CharInputStream(INPUT_FOLDER_PATH + filesInDir[i]);

            while (!r.end_of_stream()) {
                // w.writeln(r.readln());
                M = M + "\n" + r.readln();
            }
        }
        w = new CharOutputStream("C:\\Users\\epcs\\Downloads\\imdb\\test\\" + OUTPUT_FILE_NAME_PREFIX + "new2" + ".txt");
        w.writeln(M);
        w.close();
    }

    public static void buffer_read_write() {
        InputStream r;
        OutputStream w;
        r = new BufferedCharInputStream(INPUT_FOLDER_PATH + INPUT_FILE_NAME, B1);
        w = new BufferedCharOutputStream(OUTPUT_FOLDER_PATH + OUTPUT_FILE_NAME_PREFIX + "new3" + ".txt", B1);
        while (!r.end_of_stream()) {
            w.writeln(r.readln());
        }
        w.close();

    }

    public static void buffer_read_write_All() {
        InputStream r;
        OutputStream w;
        String M = "";
        File aDirectory = new File(INPUT_FOLDER_PATH);
        String[] filesInDir = aDirectory.list();
        for (int i = 0; i < filesInDir.length; i++) {
            r = new BufferedCharInputStream(INPUT_FOLDER_PATH + filesInDir[i], B1);

            while (!r.end_of_stream()) {
                // w.writeln(r.readln());
                M = M + "\n" + r.readln();
            }
        }
        w = new BufferedCharOutputStream("C:\\Users\\epcs\\Downloads\\imdb\\test\\" + OUTPUT_FILE_NAME_PREFIX + "new2" + ".txt", B1);
        w.writeln(M);
        w.close();

    }

    public static void mapping_read_write() {
        InputStream r;
        OutputStream w;
        r = new MappedInputStream(INPUT_FOLDER_PATH + INPUT_FILE_NAME, B1);
        w = new MappedOutputStream(OUTPUT_FOLDER_PATH + OUTPUT_FILE_NAME_PREFIX + "new3" + ".txt", B1);
        while (!r.end_of_stream()) {
            w.writeln(r.readln());
        }
        w.close();

    }

    public static void mapping_read_write_ALl() {
        InputStream r;
        OutputStream w;
        String M = "";
        File aDirectory = new File(INPUT_FOLDER_PATH);
        String[] filesInDir = aDirectory.list();
        for (int i = 0; i < filesInDir.length; i++) {
            r = new MappedInputStream(INPUT_FOLDER_PATH + filesInDir[i], B1);

            while (!r.end_of_stream()) {
                // w.writeln(r.readln());
                M = M + "\n" + r.readln();
            }
        }
        w = new MappedOutputStream("C:\\Users\\epcs\\Downloads\\imdb\\test\\" + OUTPUT_FILE_NAME_PREFIX + "new2" + ".txt", B1);
        w.writeln(M);
        w.close();
    }


    public static void clearOutputFolder(){
        File f = new File(OUTPUT_FOLDER_PATH);
        File[] files = f.listFiles();
        for(File x: files)
            x.delete();
    }

    public static void extsort() {

    }
}


