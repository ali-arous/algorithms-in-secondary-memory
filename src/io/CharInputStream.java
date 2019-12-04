/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author webma
 */
public class CharInputStream extends InputStream{
    public CharInputStream(){
        
    }
    public CharInputStream(String path){
        super(path);
    }
    
  
    @Override
    public String readln(){
        String buffer = "";
        try{
            int c = this.reader.read();
            while((char)c!='\n' && c!=-1){                
                buffer+=(char)c;
                c = this.reader.read();
            }
            if(c==-1)
                this.endOfStream=true;
        } catch(IOException ex){
             System.err.println("The following error is encountred while reading the file:\n"+ex.getMessage());
        }
        return buffer;
    }
}
