/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmsinsecondarymemory;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;
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
import java.io.File;
import java.util.Random;

/**
 *
 * @author epcs
 */
public class Method {
    public static final String INPUT_FOLDER_PATH = "C:\\Users\\epcs\\Downloads\\imdb\\";
    
    public static final String OUTPUT_FOLDER_PATH = "C:\\Users\\epcs\\Downloads\\AlgorithmsInSecondaryMemory\\AlgorithmsInSecondaryMemory\\test\\";
    
    public static final String INPUT_FILE_NAME ="keyword.csv";
    
    public static final String OUTPUT_FILE_NAME_PREFIX ="test";
    
    public static final int B1 = 30;
    
    public static final int B2 = 5;
    
    
    
    public static int Line_read (){
        InputStream r;
        r = new LineInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME);
         int length = 0 ;
         String OutString ; 
        while ( ! r.end_of_stream()){   
          length = length+ r.readln().length() ;
        }
        return length ;
        }
    
    public static int char_read(){
        InputStream r;
        r = new CharInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME);
        int length = 0 ;
        String OutString ;
        while ( ! r.end_of_stream()){
           length = length+ r.readln().length() ;
        }
         return length ;
    }
     public static int Buffer_read(){
        InputStream r;
        r = new BufferedCharInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME,B1);
        int length = 0 ;
        String OutString ;
        while ( ! r.end_of_stream()){
          length = length+ r.readln().length() ;
        }
         return length ;
    }
    
     public static int mapping_read(){
        InputStream r;
        r = new MappedInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME,B1);
        int length = 0 ;
        String OutString ;
        while ( ! r.end_of_stream()){
           length = length+ r.readln().length() ;
        }
         return length ;
         
    }
     public static int mapping_readRandom(){
        InputStream r;
        r = new MappedInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME,B1);
        int length = 0 ;
        String OutString ;
         Random x= new Random();
          long p=r.size();
          int X = x.nextInt((int)p);//we can change this 
          System.out.println(X);
         r.seek(X);
        while ( ! r.end_of_stream()){
            length = length+ r.readln().length() ;
        }
         return length ; 
         }
     
      public static int Line_readRandom (){
        InputStream r;
        r = new LineInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME);
        r.size();
         int length = 0 ;
         String OutString ;
          Random x= new Random();
          long p=r.size();
          int X = x.nextInt((int)p);//we can change this 
          System.out.println(X);
         r.seek(X);
        while ( ! r.end_of_stream()){
          length = length+ r.readln().length() ;
        }
        return length ;
        }
      
      public static int char_readRandom(){
        InputStream r;
        r = new CharInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME);
        int length = 0 ;
        String OutString ;
          Random x= new Random();
          long p=r.size();
          int X = x.nextInt((int)p);//we can change this 
          System.out.println(X);
         r.seek(X);
        while ( ! r.end_of_stream()){
            length = length+ r.readln().length() ;
        }
         return length ;
    } 
     
       public static int Buffer_readRandom(){
        InputStream r;
        r = new BufferedCharInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME,B1);
        int length = 0 ;
        String OutString ;
          Random x= new Random();
          long p=r.size();
          int X = x.nextInt((int)p);//we can change this 
          System.out.println(X);
         r.seek(X);
        while ( ! r.end_of_stream()){
            length = length+ r.readln().length() ;
        }
         return length ;
    }
       
       //we have to read all the files that we have and put them in one file with this method 
       //so we can look how to change tha path method
       
      public static void line_read_write(){
          InputStream r;
          OutputStream w;  
        r = new LineInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME);
        w= new LineOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+"new"+".txt"); 
        while(!r.end_of_stream()){
            w.writeln(r.readln());
        }
        w.close();
        
      }
      public static void line_read_write_All(){
          InputStream r;
          OutputStream w; 
          String M = "";
           File aDirectory = new File(INPUT_FOLDER_PATH);
           String[] filesInDir = aDirectory.list();
           for ( int i=0; i<filesInDir.length; i++ )
           {
              r = new LineInputStream(INPUT_FOLDER_PATH+filesInDir[i]);
              
            while(!r.end_of_stream()){
             // w.writeln(r.readln());
               M = M+"\n"+r.readln();  
            }
           }
           w = new LineOutputStream("C:\\Users\\epcs\\Downloads\\imdb\\test\\"+OUTPUT_FILE_NAME_PREFIX+"new"+".txt");
           w.writeln(M);
           w.close();
        
      }
       public static void char_read_write(){
          InputStream r;
          OutputStream w;
          r = new CharInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME);
          w = new CharOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+"new"+".txt");
           while(!r.end_of_stream()){
            w.writeln(r.readln());
        }
        w.close();
          
      }
      public static void char_read_write_All(){
          InputStream r;
          OutputStream w; 
          String M = "";
           File aDirectory = new File(INPUT_FOLDER_PATH);
           String[] filesInDir = aDirectory.list();
           for ( int i=0; i<filesInDir.length; i++ )
           {
              r = new CharInputStream(INPUT_FOLDER_PATH+filesInDir[i]);
              
            while(!r.end_of_stream()){
             // w.writeln(r.readln());
               M = M+"\n"+r.readln();  
            }
           }
           w = new CharOutputStream("C:\\Users\\epcs\\Downloads\\imdb\\test\\"+OUTPUT_FILE_NAME_PREFIX+"new2"+".txt");
           w.writeln(M);
           w.close();
      }
      public static void buffer_read_write(){
          InputStream r;
          OutputStream w;  
            r = new BufferedCharInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME,B1);
            w = new BufferedCharOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+"new3"+".txt", B1);
        while(!r.end_of_stream()){
            w.writeln(r.readln());
        }
        w.close();
        
      }
       public static void buffer_read_write_All(){
       InputStream r;
          OutputStream w; 
          String M = "";
           File aDirectory = new File(INPUT_FOLDER_PATH);
           String[] filesInDir = aDirectory.list();
           for ( int i=0; i<filesInDir.length; i++ )
           {
              r = new BufferedCharInputStream(INPUT_FOLDER_PATH+filesInDir[i],B1);
              
            while(!r.end_of_stream()){
             // w.writeln(r.readln());
               M = M+"\n"+r.readln();  
            }
           }
           w = new BufferedCharOutputStream("C:\\Users\\epcs\\Downloads\\imdb\\test\\"+OUTPUT_FILE_NAME_PREFIX+"new2"+".txt",B1);
           w.writeln(M);
           w.close();
        
      }
      public static void mapping_read_write(){
          InputStream r;
          OutputStream w;  
        r = new MappedInputStream(INPUT_FOLDER_PATH+INPUT_FILE_NAME,B1);
        w= new MappedOutputStream(OUTPUT_FOLDER_PATH+OUTPUT_FILE_NAME_PREFIX+"new3"+".txt",B1); 
        while(!r.end_of_stream()){
            w.writeln(r.readln());
        }
        w.close();
        
      }
       public static void mapping_read_write_ALl(){
          InputStream r;
          OutputStream w; 
          String M = "";
           File aDirectory = new File(INPUT_FOLDER_PATH);
           String[] filesInDir = aDirectory.list();
           for ( int i=0; i<filesInDir.length; i++ )
           {
              r = new MappedInputStream(INPUT_FOLDER_PATH+filesInDir[i],B1);
              
            while(!r.end_of_stream()){
             // w.writeln(r.readln());
               M = M+"\n"+r.readln();  
            }
           }
           w = new MappedOutputStream("C:\\Users\\epcs\\Downloads\\imdb\\test\\"+OUTPUT_FILE_NAME_PREFIX+"new2"+".txt",B1);
           w.writeln(M);
           w.close();
       }
       
      public static void extsort (){
          
      }
    public static void main(String[] args) {
      //int M =  Line_read();
      //int M2 = char_read();
      //int M3 = Buffer_read();
      //int M4= mapping_read();
        // System.out.println(M2);
         //System.out.println(M);
         //System.out.println(M3);
         //System.out.println(M4);
         
      //   line_read_write();
      
        //ListToString();
        //char_read_write();
        line_read_write_All();
         
    }
}


