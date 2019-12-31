/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author webma
 */
public abstract class InputStream {
    protected long pos;
    protected RandomAccessFile f;
    protected FileReader reader;
    protected boolean endOfStream;
    
    public InputStream(){
        this.pos=0;
        this.endOfStream=false;
    }
    public InputStream(String path){
        this();
        if(this.open(path)){
            //System.out.println("File \'"+extractName(path)+"\' is open..");
        }
    }
    protected String extractName(String path){
        int loc = path.lastIndexOf('\\');
        if (loc==-1)
            loc = path.lastIndexOf('/');
        return path.substring(loc+1);
    }
    
    public boolean open(String path){
        try{
            f = new RandomAccessFile(path,"r");
            this.reader = new FileReader(f.getFD());
        } catch (IOException ex){
            System.err.println("The following error is encountred while opening the file:\n"+ex.getMessage());
        }
        return true;
    }
    
    public void seek(long pos){
        try{
            if(pos<0)
                throw new IOException("Seek position outside file boundaries.");            
            this.pos = pos;
            f.seek(pos);
            this.reader = new FileReader(f.getFD());
        }catch (IOException ex){
            System.err.println("The following error is encountred while seeking inside the file:\n"+ex.getMessage());
        }
    }
    public long size(){
        try {
            return this.f.length();
        }catch(IOException ex){
            System.err.println("The following error is encountred while returning the length of the file:\n"+ex.getMessage());
            return 0;
        }
    }
    
    public abstract String readln();
    
    public boolean end_of_stream(){
        return this.endOfStream;
    }
}
