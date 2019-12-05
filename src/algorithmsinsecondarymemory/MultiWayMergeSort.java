/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmsinsecondarymemory;

import io.InputStream;
import io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 *
 * @author webma
 */

// New DataType to store a line next to its stream index. (Useful for 'merge' method)
// So whenever a line is written to desk, its corresponding stream is read again.
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
    
    private InputStream r;
    private OutputStream w;
    IOFactory factory; // this object has methods that create input/output streams, according to a given method out of the 4 available
    
    // Use this constructor with the parameter: "CharStream" /or/ "1" for the first method, 
    // and with the parameter "LineStream" /or/ "2" for the second method.
    // DO NOT USE IT FOR METHODS 3 and 4!
    public MultiWayMergeSort(String method){
        factory = new IOFactory(method);
    }

        
    // Use this constructor with the parameters:
    // method = "BufferedCharStream" or "3" for the third method.
    // method = "MappedStream" or "4" for the fourth method.
    // B = the size of the buffer (for both third and fourth methods).
    public MultiWayMergeSort(String method, int B){
        factory = new IOFactory(method, B);
    }
    
    public void exsort(String f, int k, int M, int d){
        // open input file for reading
        r = factory.makeReader(INPUT_FOLDER_PATH+f);
        //r = new LineInputStream(INPUT_FOLDER_PATH+f);
        ArrayList<String> lines = new ArrayList<>();
        //ArrayList<InputStream> chunkFiles = new ArrayList<>();
        Queue<InputStream> chunkFiles = new LinkedList<>();
        long chunkSize=0;
        int ofc=1;
        
        String line=r.readln();
        // repeat until end of file
        while(!r.end_of_stream()){
            
            // we keep in memory as many lines as their total size in bytes <=M
            if(chunkSize+line.getBytes().length<=M){
                chunkSize+=line.getBytes().length;
                //System.out.println("Added to lines: "+line);
                lines.add(line);
            }else{
                // if the next read line is going to overflow the memory,
                // we write the memory content of lines (sorted) to disk
                lines.sort((String l1, String l2)-> { return cmp(l1,l2,k); });
                String chunkFileName = "chunk"+(ofc++)+".txt";
                w = factory.makeWriter(OUTPUT_FOLDER_PATH+chunkFileName);
                //w = new LineOutputStream(OUTPUT_FOLDER_PATH+chunkFileName);
                for(String l:lines)
                    w.writeln(l);                
                w.close();
                
                // we record the generated file, so that we can read it again in merge step.
                chunkFiles.add(factory.makeReader(OUTPUT_FOLDER_PATH+chunkFileName));
                //chunkFiles.add(new LineInputStream(OUTPUT_FOLDER_PATH+chunkFileName));
                lines.clear();
                chunkSize=0;
                
                // after clearing the memory, we can accept the new read line and loop again.
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
        
        // if the input stream reached end of file (while loop break condition) 
        // but there were remaining lines in memory, we write them to disk and clear the memory.
        if(lines.size()>0){
            lines.sort((String l1, String l2)-> { return cmp(l1,l2,k); });
            String chunkFileName = "chunk"+(ofc++)+".txt";
            w = factory.makeWriter(OUTPUT_FOLDER_PATH+chunkFileName);
            //w = new LineOutputStream(OUTPUT_FOLDER_PATH+chunkFileName);
            for(String l:lines)
                w.writeln(l);                
            w.close();
            chunkFiles.add(factory.makeReader(OUTPUT_FOLDER_PATH+chunkFileName));
            //chunkFiles.add(new LineInputStream(OUTPUT_FOLDER_PATH+chunkFileName));
        }
        System.out.println("Phase #1 done! Check generated chunk files..");
        
        // here we loop over the list of generated files, and merge each d of them at once
        // until only one file remains (the final output file of sorted lines)
        int counter=1;
        while(chunkFiles.size()>1){
            merge(d, k, counter++, chunkFiles);
        }
        System.out.println("The last file written is the final sorted output file!");
    }
    
    
    private void merge(int d, int k, int iter, Queue<InputStream> q){
        InputStream[] istreams;
        Queue<LineOfStream> buff = new PriorityQueue<LineOfStream>(  (LineOfStream line1, LineOfStream line2)-> {return cmp(line1.text,line2.text,k);} );
        
        // we take first d lines from the queue or as many as left if their count is < d.
        int loopLimit = q.size() > d ? d : q.size();
        istreams=new InputStream[loopLimit];

        for(int i=0;i<loopLimit;i++){
            istreams[i]=q.poll();
        }
        
//        boolean allFinished=false;
        
        String partialOutputFileName="partial_output_"+iter+".txt";
        OutputStream o = factory.makeWriter(OUTPUT_FOLDER_PATH+partialOutputFileName);
        //OutputStream o=new LineOutputStream(OUTPUT_FOLDER_PATH+partialOutputFileName);
//        while(!allFinished){
//            allFinished=true;
            for(int i=0;i<loopLimit;i++){
                if(!istreams[i].end_of_stream()){
                    // from each stream, read a line and add it to the priority queue
                    String l=istreams[i].readln();
                    if(l.length()>0)buff.add(new LineOfStream(l, i));
                }
//                allFinished = allFinished && istreams[i].end_of_stream();
            }

            // as long as the priority queue has content, write Line_h (the line at its head) to disk
            // and read a new line from Line_h's same stream. (if its stream it not exhausted already!).
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
        
        // after we merged the first d line and stored their result to disk, 
        // we read their merge result back from disk with an input stream 
        // and add it to streams queue again.       
        System.out.println("Finished writing file: "+partialOutputFileName);
        InputStream newis = factory.makeReader(OUTPUT_FOLDER_PATH+partialOutputFileName);
        //InputStream newis=new LineInputStream(OUTPUT_FOLDER_PATH+partialOutputFileName);
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
    
    private static int cmp(String s1, String s2, int k){
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
