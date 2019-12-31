/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.*;
import java.nio.CharBuffer;

/**
 *
 * @author webma
 */
public class BufferedCharInputStream extends InputStream{

    private int B;
    CharBuffer buff;
    InputStreamReader sreader;
    boolean newRead = true;
    int currentCapacity;
    public BufferedCharInputStream(int buffer){
        super();
        this.setBufferSize(buffer);
        this.buff = CharBuffer.allocate(B);
    }

    public BufferedCharInputStream(String path, int buffer){
        this(buffer);
        if(this.open(path)){
            //System.out.println("File \'"+extractName(path)+"\' is open..");
        }
    }

    public void setBufferSize(int buffer){
        if(buffer>0){
            this.B = buffer;
            this.currentCapacity = buffer;
        }
        else
            System.out.println("Buffer size should be > 0");
    }

    @Override
    public boolean open(String path){
        try{
            f = new RandomAccessFile(path,"r");
            this.sreader = new InputStreamReader(new FileInputStream(f.getFD()));
        } catch (IOException ex){
            System.err.println("The following error is encountred while opening the file:\n"+ex.getMessage());
        }
        return true;
    }

    @Override
    public void seek(long pos){
        try{
            if(pos<0)
                throw new IOException("Seek position outside file boundaries.");
            this.newRead = true;
            this.pos = pos;
            f.seek(pos);
            this.sreader = new InputStreamReader(new FileInputStream(f.getFD()));
        }catch (IOException ex){
            System.err.println("The following error is encountred while seeking inside the file:\n"+ex.getMessage());
        }
    }
    @Override
    public String readln() {
        //System.out.println("[Read start]");
        if(buff.position()==currentCapacity || this.newRead){
            //System.out.println("[Segment#1]");
            this.newRead = false;
            buff.clear();
            char[] bchars = new char[B];
            try{
                int numOfReads = this.sreader.read(bchars,0,B);
                if(numOfReads==-1){
                    this.endOfStream = true;
                    return "";
                }
                else{
                    currentCapacity=numOfReads;
                    buff.rewind();
                    buff.put(bchars,0, numOfReads);
                    this.pos+=numOfReads;
                    buff.rewind();
                }
            } catch(IOException ex){
                System.err.println("The following error is encountred while reading the file:\n"+ex.getMessage());
            }
        }
        char c;
        String s="";
        //System.out.println("[Segment#2]");
        //char[] arr = new char[B];
        //c = this.reader.read();
        c = this.buff.get();
        //this.buff.
        outerloop:
        while(c!='\n') {
            s += c;
            while (buff.position() < currentCapacity) {
                c = this.buff.get();
                if (c != '\n')
                    s += c;
                else {
                    //System.out.println("[exit via break]");
                    break outerloop;
                }
            }
            //System.out.println("[Segment#3]");
            buff.clear();
            char[] bchars = new char[B];
            try {
                int numOfReads = this.sreader.read(bchars, 0, B);
                if (numOfReads == -1) {
                    this.endOfStream = true;
                    return s;
                } else {
                    buff.rewind();
                    buff.put(bchars, 0, numOfReads);
                    currentCapacity = numOfReads;
                    //System.out.println("[After segment#3: capacity = "+currentCapacity+"]");
                    //System.out.println("[current read = "+s.length()+"]: "+s);
                    this.pos+=numOfReads;
                    buff.rewind();
                }
            } catch (IOException ex) {
                System.err.println("The following error is encountred while reading the file:\n" + ex.getMessage());
            }
            c = this.buff.get();
        }
        //System.out.println("[Read end]");
        return s;
    }
}
