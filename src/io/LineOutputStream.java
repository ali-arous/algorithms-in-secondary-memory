/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author webma
 */
public class LineOutputStream extends OutputStream{

    private BufferedWriter bw;
    
    public LineOutputStream(){
        
    }
    
    public LineOutputStream(String path){
        super(path);
        this.bw = new BufferedWriter(this.writer);
    }
    
    @Override
    public void create(String path){
        super.create(path);
        this.bw = new BufferedWriter(this.writer);
    }
    
    @Override
    public void writeln(String s) {
        if(s.length()==0)
            return;
        try{
            this.bw.write(s);
            this.bw.newLine();
        }catch(IOException ex){
            System.err.println("The following error is encountred while writing to the file:\n"+ex.getMessage());
        }
        System.out.println("- wrote to file:  "+s);
    }

    @Override
    public void close(){
        try{
            this.bw.close();
        } catch(IOException ex){
            System.err.println("The following error is encountred while closing the file:\n"+ex.getMessage());
        }
    }
}
