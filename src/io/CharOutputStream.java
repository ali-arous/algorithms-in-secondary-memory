/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.IOException;

/**
 *
 * @author webma
 */
public class CharOutputStream extends OutputStream{
    public CharOutputStream(){
        
    }
    public CharOutputStream(String path){
        super(path);
    }
    
    @Override
    public void writeln(String s){
        if(s.length()==0)
            return;
        try{
            int len=s.length();
            for(int i=0;i<len;i++){
                this.writer.write(s.charAt(i));
            }
            this.writer.write('\n');
            System.out.println("- wrote to file:  "+s);
        } catch(IOException ex){
            System.err.println("The following error is encountred while writing to the file:\n"+ex.getMessage());
        }
    }
}
