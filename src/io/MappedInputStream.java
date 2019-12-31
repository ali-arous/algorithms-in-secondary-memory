/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 *
 * @author webma
 */
public class MappedInputStream extends InputStream{
    
    private int B;
    private MappedByteBuffer buff;
    private FileChannel fc;
    private long fileSize;
    private long round;
    public MappedInputStream(int buffer){
        super();
        this.round=1;
        this.fileSize=Long.MAX_VALUE;
        if(buffer>0) 
            this.B = buffer;
        else
            System.out.println("Buffer size should be > 0");
    }
    
    public MappedInputStream(String path, int buffer){
        this(buffer);
        this.open(path);
    }
    
    @Override
    public boolean open(String path){
        File f= new File(path);
        try{
            this.fc = FileChannel.open(f.toPath(), StandardOpenOption.READ);
            this.fileSize = fc.size();
            if(fileSize - B >=0)
                this.buff  = fc.map(FileChannel.MapMode.READ_ONLY, 0, B);
            else
                this.buff  = fc.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);
            //System.out.println("File \'"+extractName(path)+"\' is open..");
        } catch (IOException ex){
            System.err.println("The following error is encountred while opening the file:\n"+ex.getMessage());
        }        
        return true;
    }

    @Override
    public long size(){
        return this.fileSize;
    }

    @Override
    public void seek(long pos){
        try{
            if(pos<0)
                throw new IOException("Seek position outside file boundaries.");
            if(fileSize - (pos+B) >=0){
                this.buff  = fc.map(FileChannel.MapMode.READ_ONLY, pos, B);
            }
            else if (pos < fileSize){
                long segment = fileSize - pos;
                this.buff  = fc.map(FileChannel.MapMode.READ_ONLY, pos, segment);
                this.pos+=segment;
            }
            else
                throw new IOException("Seek position outside file boundaries.");
            this.pos = pos;
        }catch (IOException ex){
            System.err.println("The following error is encountred while seeking inside the file:\n"+ex.getMessage());
        }
        this.round=1;
    }
    
    @Override
    public String readln() {
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        byte c;
        long segment = -1;
        outerloop:
        while(true){
            while(buff.hasRemaining()){                 
                 c = buff.get();                 
                 if((char)c=='\n') break outerloop;
                 s.write(c);
            }
            try {
                segment = this.fileSize - (round*B+pos);
                if (segment>0 && segment<B)
                    this.buff  = fc.map(FileChannel.MapMode.READ_ONLY, round*B+pos, segment);
                else if(segment > B)
                    this.buff  = fc.map(FileChannel.MapMode.READ_ONLY, round*B+pos, B);
                else{
                    this.endOfStream=true;
                    break;
                }
                round++;
            } catch (IOException ex) {
            System.err.println("The following error is encountred while reading the file:\n"+ex.getMessage());
            }
        }
        if(this.fileSize - (round*B+pos-this.buff.remaining())==0)
            this.endOfStream=true;
        return new String(s.toByteArray());
    }
}
