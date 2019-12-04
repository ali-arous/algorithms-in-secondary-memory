/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 *
 * @author webma
 */
public class BufferedCharOutputStream extends OutputStream{
    
    private int B;
    
    public BufferedCharOutputStream(int buffer){
        this.B = buffer;
    }
    
    public BufferedCharOutputStream(String path, int buffer){
        super(path);
        this.B=buffer;
    }
    
    @Override
    public void writeln(String s) {
        if(s.length()==0)
            return;
        CharBuffer buff = CharBuffer.allocate(B);
        try{
            for(char c: s.toCharArray()){
                buff.put(c);
                //System.out.println("POS: "+buff.position());
                if(buff.position()==buff.capacity()){
                    buff.rewind();
                    while(buff.hasRemaining()){
                        char ck = buff.get();
                  //      System.out.println(ck);
                        this.writer.write(ck);
                    }
                    buff.rewind();
                }
             }
            if(buff.position() > 0 && buff.position() < buff.capacity()){
                int save=buff.position();
                int i=0;
                buff.rewind();
                while(i<save){
                    char c=buff.get();
                    //System.out.println(c);
                    this.writer.write(c);
                    i++;
                }
            }
            this.writer.write('\n');
            System.out.println("- wrote to file:  "+s);
         } catch(IOException ex){
             System.err.println("The following error is encountred while reading the file:\n"+ex.getMessage());
         }
    }
    
}
