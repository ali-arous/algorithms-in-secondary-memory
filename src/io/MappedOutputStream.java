/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import sun.nio.ch.DirectBuffer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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
    private boolean bufferExist;
    private long fileToWriteLen;

    private File f;
    public MappedOutputStream(int buffer){
        this.round=0;
        this.pos=0;
        this.bufferExist=false;
        this.fileToWriteLen=-1;
        if(buffer>0)
            this.B = buffer;
        else
            System.out.println("Buffer size should be > 0");
    }
    
    public MappedOutputStream(String path, int buffer){
        this(buffer);
        System.out.println("MappedOutputStream object initiated..");
        this.create(path);
    }
    public MappedOutputStream(String path, int buffer, long len){
        this(path, buffer);
        //System.out.println("I got a file length of: "+len);
        this.setFileToWriteLen(len);
    }
    public void setFileToWriteLen(long len){
        if(len>0)
            this.fileToWriteLen = len;
        else
            System.out.println("File length should be > 0");
    }
    public void create(String path){
        try{
//            File f = new File(path);
            f = new File(path);
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
//                this.writer = new FileWriter(f);
//                this.writer.close();
                //f = new File(path);
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
        //System.out.println("\n\nTARGET: "+s);
        try {

            if(!bufferExist){
                long segment = Math.min(this.fileToWriteLen-(round*B+pos), B); //System.out.println("LOOK: "+(this.fileToWriteLen-(round*B+pos))+",  "+B);
                this.buff = this.fc.map(FileChannel.MapMode.READ_WRITE, round*B+pos, segment);
                bufferExist=true;
            }
            //byte[] bArr=sb.toByteArray();
            byte[] bArr = s.getBytes();
            //int len = sb.size();
            long len = bArr.length;

            // TODO: You have to consider if there was a buffer before..
            while(len>=B){
                //System.out.println("[SEGMENT#1]");
                if(this.buff.remaining() == this.buff.capacity()){
                    //Arrays.copyOfRange(bArr, 0, (int) B);
                    round++;
                    buff.put(Arrays.copyOfRange(bArr, 0, (int)B));
                    bArr=Arrays.copyOfRange(bArr, (int)B, bArr.length);
                    len = bArr.length;
                    long segment = Math.min(this.fileToWriteLen-(round*B+pos), B); //System.out.println("LOOK: "+(this.fileToWriteLen-(round*B+pos))+",  "+B);
                    this.buff = this.fc.map(FileChannel.MapMode.READ_WRITE, round*B+pos, segment);
                }else{
                    int rem = buff.remaining();
                    buff.put(Arrays.copyOfRange(bArr,0, rem));
                    bArr = Arrays.copyOfRange(bArr,rem, bArr.length);
                    len = bArr.length;
                    pos+=rem;
                    long segment = Math.min(this.fileToWriteLen-(round*B+pos), B); //System.out.println("LOOK: "+(this.fileToWriteLen-(round*B+pos))+",  "+B);
                    this.buff = this.fc.map(FileChannel.MapMode.READ_WRITE, round * B + pos, segment);
                }
            }
            while(len>0){
                //System.out.println("[SEGMENT#2]");
                if(len>this.buff.remaining()){
                    int rem = buff.remaining();
                    //System.out.println("[SEGMENT#4] PUT: "+new String(Arrays.copyOfRange(bArr,0, rem)));

                    buff.put(Arrays.copyOfRange(bArr,0, rem));
                    //System.out.println("POS: "+buff.position());
                    bArr = Arrays.copyOfRange(bArr,rem, bArr.length);
                    len = bArr.length;
                    pos+=rem;
                    //System.out.println("CURSOR: "+(round*B+pos));
                    long segment = Math.min(this.fileToWriteLen-(round*B+pos), B); //System.out.println("LOOK: "+(this.fileToWriteLen-(round*B+pos))+",  "+B);
                    this.buff = this.fc.map(FileChannel.MapMode.READ_WRITE, round * B + pos, segment);
                    //round++;
                } else {
                    //System.out.println("[SEGMENT#5] PUT: "+new String(bArr));

                    //System.out.println("CURRENT| "+String.valueOf(buff.asCharBuffer().array()));
                    pos += bArr.length;
                    this.buff.put(bArr);
                    //System.out.println("POS: "+buff.position());
                    //System.out.println("CURSOR: "+(round*B+pos));
                    len = 0;
                }
                //len = bArr.length;
            }
//            if(len>0 && len<B){System.out.println("[SEGMENT#2]");
////                this.buff  = this.fc.map(FileChannel.MapMode.READ_WRITE, round*B+pos, bArr.length);
//
//
//            }
        } catch (IOException ex) {
            System.err.println("The following error is encountred while writing to the file:\n"+ex.getMessage());
        }
        //System.out.println("- wrote to file:  "+ss);
    }
    
    @Override
    public void close(){
        //System.out.println("TOTAL: "+(round*B+pos));
        System.out.println("FINAL: "+(round*B+pos));
        this.buff.force();
        //unmap(this.buff);
        try {
            this.fc.close();
            if (fc.isOpen()) {
                System.out.println("WTF! It's still open!!");
            }
            System.gc();

            this.fc = FileChannel.open(f.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE);
            //TimeUnit.SECONDS.sleep(25);
            //this.fc.truncate(round * B + pos);
        }
//        catch(InterruptedException ex){
//            System.err.println("The following error is encountred while closing the file:\n"+ex.getMessage());
//        }
        catch(IOException ex){
            System.err.println("The following error is encountred while closing the file:\n"+ex.getMessage());
        }

    }
    public static void unmap(MappedByteBuffer buffer)
    {
        sun.misc.Cleaner cleaner = ((DirectBuffer) buffer).cleaner();
        cleaner.clean();
    }
}
