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
public class BufferedCharInputStream extends InputStream{
    
    private int B;
    
    public BufferedCharInputStream(int buffer){
        this.setBufferSize(buffer);
        this.endOfStream=false;
    }
    
    public BufferedCharInputStream(String path, int buffer){
        super(path);
        this.setBufferSize(buffer);
    }

    public void setBufferSize(int buffer){
        if(buffer>0)this.B = buffer;
        else
            System.out.println("Buffer size should be > 0");
    }
    
    @Override
    public String readln() {
        CharBuffer buff = CharBuffer.allocate(B);
        int c;
        String s="";
        try{
            char[] arr = new char[B];
            c = this.reader.read();
            outerloop:
            while((char)c!='\n' && c!=-1){
                while(buff.position() < buff.capacity()){
                    buff.put((char)c);
                    c = this.reader.read();
                    if((char)c=='\n' || c==-1) {
                        char[] arrVarLen=new char[buff.position()];
                        buff.rewind();
                        buff.get(arrVarLen);
                        buff.clear();
                        s+=String.valueOf(arrVarLen);
                        break outerloop;
                    }
                }
                if(c==-1)
                    this.endOfStream=true;
                buff.rewind();                
                buff.get(arr);
                buff.clear();
                s+=String.valueOf(arr);
            }
            if(c==-1)
                this.endOfStream=true;
        } catch(IOException ex){
            System.err.println("The following error is encountred while reading the file:\n"+ex.getMessage());
        }
        return s;
    }
}
