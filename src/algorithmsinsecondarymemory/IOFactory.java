/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmsinsecondarymemory;

/**
 *
 * @author webma
 */
import io.InputStream;
import io.OutputStream;
import io.BufferedCharInputStream;
import io.BufferedCharOutputStream;
import io.CharInputStream;
import io.CharOutputStream;
import io.LineInputStream;
import io.LineOutputStream;
import io.MappedInputStream;
import io.MappedOutputStream;


public class IOFactory {
    private String method;
    private int B;
    private long outFileSize = -1;
    public IOFactory(String method){
        this.method= method;
    }
    public IOFactory(String method, int B){
        this.method=method;
        this.B = B;
    }
    public IOFactory(String method, int B, long outFileSize){
        this.method=method;
        this.B = B;
        this.outFileSize = outFileSize;
    }
    public InputStream makeReader(String fullPath){
        if(method=="CharStream" || method=="1")
            return new CharInputStream(fullPath);
        else if(method=="LineStream" || method=="2")
            return new LineInputStream(fullPath);
        else if(method=="BufferedCharStream" || method=="3")
            return new BufferedCharInputStream(fullPath, B);
        else if(method=="MappedStream" || method=="4")
            return new MappedInputStream(fullPath, B);
        else{
            System.out.println("Error! No compatible InputStream matched..");
            return null;
        }
    }
    
    public OutputStream makeWriter(String fullPath){
        if(method=="CharStream" || method=="1")
            return new CharOutputStream(fullPath);
        else if(method=="LineStream" || method=="2")
            return new LineOutputStream(fullPath);
        else if(method=="BufferedCharStream" || method=="3")
            return new BufferedCharOutputStream(fullPath, B);
        else if(method=="MappedStream" || method=="4")
            if(this.outFileSize == -1)
                return new MappedOutputStream(fullPath, B);
            else
                return new MappedOutputStream(fullPath, B, outFileSize);
        else{
            System.out.println("Error! No compatible OutputStream matched..");
            return null;
        }
    }
}
