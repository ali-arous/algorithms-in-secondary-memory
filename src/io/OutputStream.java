/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author webma
 */
public abstract class OutputStream {
    protected FileWriter writer;
    public OutputStream(){
        
    }
    public OutputStream(String path){
        this.create(path);
    }
    public void create(String path){
        try{
            File f = new File(path);
            if(!f.exists()){
                if(f.createNewFile()){
                    this.writer = new FileWriter(f);
                    System.out.println("File created successfully..");
                }
                else
                    throw new IOException("File creation failed!");

            } else{
                this.writer = new FileWriter(f);
                System.out.println("An existing file with the same name has been linked");
            }
        } catch(IOException ex){
            System.err.println("The following error is encountred while creating the file:\n"+ex.getMessage());
        }
    }
    public abstract void writeln(String s);
    
    public void close(){
        try{
            this.writer.close();
        } catch(IOException ex){
            System.err.println("The following error is encountred while closing the file:\n"+ex.getMessage());
        }
    }
    
}
