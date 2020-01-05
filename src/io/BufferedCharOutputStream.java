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
public class BufferedCharOutputStream extends OutputStream{
    
    private int B;
    CharBuffer buff;
    OutputStreamWriter swriter;
    public BufferedCharOutputStream(int buffer){
        this.setBufferSize(buffer);
        this.buff = CharBuffer.allocate(B);
        //System.out.println("BufferedCharOutputStream object initiated..");
    }
    
    public BufferedCharOutputStream(String path, int buffer){
        this(buffer);
        this.create(path);
    }

    public void setBufferSize(int buffer){
        if(buffer>0)this.B = buffer;
        else
            System.out.println("Buffer size should be > 0");
    }

    @Override
    public void create(String path){
        try{
            File f = new File(path);
            if(!f.exists()){
                if(f.createNewFile()){
                    this.swriter = new OutputStreamWriter(new FileOutputStream(f));
                    //System.out.println("File created successfully..");
                }
                else
                    throw new IOException("File creation failed!");

            } else{
                this.swriter = new OutputStreamWriter(new FileOutputStream(f));
                System.out.println("An existing file with the same name has been linked");
            }
        } catch(IOException ex){
            System.err.println("The following error is encountred while creating the file:\n"+ex.getMessage());
        }
    }

    @Override
    public void writeln(String s) {
        if(buff.position() == buff.capacity()){
            this.buff.rewind();
            char[] bchars = new char[B];
            this.buff.get(bchars,0,B);
            try{
                this.swriter.write(bchars,0,B);
                
                buff.clear();
                buff.rewind();
            }catch(IOException ex){
                System.err.println("The following error is encountered while writing the file:\n"+ex.getMessage());
            }
        }
        if(s.length()==0)
            return;
        s+="\r\n";

        for(char c: s.toCharArray()){
            buff.put(c);
            //System.out.println("buff.put("+c+")");
            //System.out.println("POS: "+buff.position());
        //    System.out.println("pos: "+buff.position()+", capacity:"+buff.capacity());

            if(buff.position()==buff.capacity()){
                char[] bchars = new char[B];
                buff.rewind();
                //System.out.println("length() = "+buff.length());
                this.buff.get(bchars,0,B);
                try{
                    this.swriter.write(bchars,0,B);
                    
                    buff.clear();
                    buff.rewind();
                }catch(IOException ex){
                    System.err.println("The following error is encountered while writing the file:\n"+ex.getMessage());
                }
            }
         }
        if(buff.position() == buff.capacity()){
            this.buff.rewind();
            char[] bchars = new char[B];
            this.buff.get(bchars,0,B);
            try{
                this.swriter.write(bchars,0,B);
                
                buff.clear();
                buff.rewind();
            }catch(IOException ex){
                System.err.println("The following error is encountered while writing the file:\n"+ex.getMessage());
            }
        }
        //System.out.print("- wrote to file:  "+s);
    }

    @Override
    public void close(){
        try{
            if(buff.position() > 0 && buff.position() < buff.capacity()){
                int len = buff.position();
                buff.rewind();
                char[] bchars1 = new char[len];
                this.buff.get(bchars1,0,len);
                this.swriter.write(bchars1, 0,len);
            }
            this.swriter.close();
        } catch(IOException ex){
            System.err.println("The following error is encountred while closing the file:\n"+ex.getMessage());
        }
    }
}
