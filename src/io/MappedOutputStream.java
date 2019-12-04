/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 *
 * @author webma
 */
public class MappedOutputStream extends OutputStream{

    private long B;
    private MappedByteBuffer buff;
    private FileChannel fc;
    private long round;
    private long pos;
    
    public MappedOutputStream(int buffer){
        this.round=0;
        this.pos=0;
        if(buffer>0)
            this.B = buffer;
        else
            System.out.println("Buffer size should be > 0");
    }
    
    public MappedOutputStream(String path, int buffer){
        this(buffer);
        this.create(path);
    }
    
    public void create(String path){
        try{
            File f = new File(path);
            if(!f.exists()){
                if(f.createNewFile()){
                    this.fc = FileChannel.open(f.toPath(),StandardOpenOption.READ,StandardOpenOption.WRITE);
                    System.out.println("File created successfully..");                    
                }
                else
                    throw new IOException("File creation failed!");
            } else{
                System.out.println("An existing file with the same name has been linked and will be overwritten..");
                //PrintWriter pw = new PrintWriter(f);
                this.writer = new FileWriter(f);
                this.writer.close();
                f = new File(path);
                this.fc = FileChannel.open(f.toPath(),StandardOpenOption.READ,StandardOpenOption.WRITE);
            }
                   
        } catch(IOException ex){
            System.err.println("The following error is encountred while creating the file:\n"+ex.getMessage());
        }
    }
    
    @Override
    public void writeln(String s) {
        if(s.length()==0)
            return;
        String ss=s;
        s+='\n';
        try {
            //byte[] bArr=sb.toByteArray();
            byte[] bArr = s.getBytes();
            //int len = sb.size();
            long len = bArr.length;
            while(len>=B){
                this.buff  = this.fc.map(FileChannel.MapMode.READ_WRITE, round*B+pos, B);                Arrays.copyOfRange(bArr, 0, (int) B);
                round++;
                buff.put(Arrays.copyOfRange(bArr, 0, (int)B));
                bArr=Arrays.copyOfRange(bArr, (int)B, bArr.length);
                len = bArr.length;
            }
            if(len>0 && len<B){
                this.buff  = this.fc.map(FileChannel.MapMode.READ_WRITE, round*B+pos, bArr.length);
                pos+=bArr.length;
                this.buff.put(bArr);
            }
        } catch (IOException ex) {
            System.err.println("The following error is encountred while writing to the file:\n"+ex.getMessage());
        }
        System.out.println("- wrote to file:  "+ss);
    }
    
    @Override
    public void close(){
        this.buff=null;
        System.gc();
    }
}
