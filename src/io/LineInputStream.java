/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author webma
 */
public class LineInputStream extends InputStream{
    
    private BufferedReader br;
    
    public LineInputStream(){
        
    }
    
    public LineInputStream(String path){
        super(path);
        this.br = new BufferedReader(this.reader);
    }

    @Override
    public void seek(long pos){
        super.seek(pos);
        this.br = new BufferedReader(this.reader);
    }
    
    @Override
    public boolean open(String path){
        if(super.open(path)){
            this.br = new BufferedReader(this.reader);
            return true;  
        }
        return false;
    }
    
    @Override
    public String readln() {
        String line = "";
        try{
            if( (line = this.br.readLine()) != null )
                return line;//+"\r";
            else
                this.endOfStream=true;
        } catch(IOException ex){
            System.err.println("The following error is encountred while reading the file:\n"+ex.getMessage());
        }
        return "";
    }

    public void close(){
        try{
            br.close();
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
        super.close();
    }
}
