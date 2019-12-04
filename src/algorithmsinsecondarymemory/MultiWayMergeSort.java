/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmsinsecondarymemory;

import io.InputStream;
import io.OutputStream;
import io.BufferedCharInputStream;
import io.BufferedCharOutputStream;
import io.CharInputStream;
import io.CharOutputStream;
import io.LineInputStream;
import io.LineOutputStream;
import io.MappedInputStream;
import io.MappedOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 *
 * @author webma
 */
class LineOfStream{
    String text;
    int streamIndex;
    
    public LineOfStream(String text, int streamIndex){
        this.text=text;
        this.streamIndex=streamIndex;
    }
}
public class MultiWayMergeSort {
    
    private static final String INPUT_FOLDER_PATH = "C:\\Users\\webma\\Desktop\\BDMA\\Database Architecture\\Assignment\\";
    
    private static final String OUTPUT_FOLDER_PATH = "C:\\Users\\webma\\Desktop\\BDMA\\Database Architecture\\Assignment\\outputs\\";
    
    private static InputStream r;
    private static OutputStream w;
    
    public static void exsort(String f, int k, int M, int d){
        r = new LineInputStream(INPUT_FOLDER_PATH+f);
        String line=r.readln();
        ArrayList<String> lines = new ArrayList<>();
        //ArrayList<InputStream> chunkFiles = new ArrayList<>();
        Queue<InputStream> chunkFiles = new LinkedList<>();
        long chunkSize=0;
        int ofc=1;
        while(!r.end_of_stream()){
            if(chunkSize+line.getBytes().length<=M){
                chunkSize+=line.getBytes().length;
                //System.out.println("Added to lines: "+line);
                lines.add(line);
            }else{
                lines.sort((String l1, String l2)-> { return cmp(l1,l2,k); });
                String chunkFileName = "chunk"+(ofc++)+".txt";
                w = new LineOutputStream(OUTPUT_FOLDER_PATH+chunkFileName);
                for(String l:lines)
                    w.writeln(l);                
                w.close();
                chunkFiles.add(new LineInputStream(OUTPUT_FOLDER_PATH+chunkFileName));
                lines.clear();
                chunkSize=0;
                if(line.getBytes().length<=M){
                    lines.add(line);
                    chunkSize+=line.getBytes().length;
                }
                else{
                    System.out.println("There is a single line of size: "+line.getBytes().length+", which exceeds memory buffer size!");
                    System.out.println("Program is going to stop..");
                    break;
                }
            }
            line=r.readln();
        }
        
        if(lines.size()>0){
            lines.sort((String l1, String l2)-> {return cmp(l1,l2,k);});
            String chunkFileName = "chunk"+(ofc++)+".txt";
            w = new LineOutputStream(OUTPUT_FOLDER_PATH+chunkFileName);
            for(String l:lines)
                w.writeln(l);                
            w.close();
            chunkFiles.add(new LineInputStream(OUTPUT_FOLDER_PATH+chunkFileName));
        }
        System.out.println("Phase #1 done! Check generated chunk files..");
        
        int counter=1;
        while(chunkFiles.size()>1){
            merge(d, k, counter++, chunkFiles);
        }
        System.out.println("The last file written is the final sorted output file!");
    }
    
    
    private static void merge(int d, int k, int iter, Queue<InputStream> q){
        InputStream[] istreams;
        Queue<LineOfStream> buff = new PriorityQueue<LineOfStream>(  (LineOfStream line1, LineOfStream line2)-> {return cmp(line1.text,line2.text,k);} );
        int loopLimit = q.size() > d ? d : q.size();
        istreams=new LineInputStream[loopLimit];

        for(int i=0;i<loopLimit;i++){
            istreams[i]=q.poll();
        }
        
        boolean allFinished=false;
        
        String partialOutputFileName="partial_output_"+iter+".txt";
        OutputStream o=new LineOutputStream(OUTPUT_FOLDER_PATH+partialOutputFileName);
//        while(!allFinished){
//            allFinished=true;
            for(int i=0;i<loopLimit;i++){
                if(!istreams[i].end_of_stream()){
                    String l=istreams[i].readln();
                    if(l.length()>0)buff.add(new LineOfStream(l, i));
                }
//                allFinished = allFinished && istreams[i].end_of_stream();
            }

            while(!buff.isEmpty()){
                LineOfStream los = buff.poll();
                o.writeln(los.text);
                if(!istreams[los.streamIndex].end_of_stream()){
                    String l=istreams[los.streamIndex].readln();
                    if(l.length()>0) buff.add(new LineOfStream(l,los.streamIndex));
                }
            }
 //       }
        o.close();
        System.out.println("Finished writing file: "+partialOutputFileName);
        InputStream newis=new LineInputStream(OUTPUT_FOLDER_PATH+partialOutputFileName);
        q.add(newis);
        
    }
    
    
    private static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
    
    public static int cmp(String s1, String s2, int k){
        String[] arr1=s1.split(",");
        String[] arr2=s2.split(",");
        
        int len1 = arr1[k].length(), len2 = arr2[k].length();
        if(len1>0 && len2>0){
            if(isNumeric(arr1[k]) && isNumeric(arr2[k])){
                double num1 = Double.parseDouble(arr1[k]);
                double num2 = Double.parseDouble(arr2[k]);
                if(num1<num2)
                    return -1;
                else if(num1 > num2)
                    return +1;
                else
                    return 0;                
            }
            else
                return s2.compareTo(s1);
        } else 
            return Math.min(len1,len2)-Math.max(len1, len2);
    }
    
}
