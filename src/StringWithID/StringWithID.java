package StringWithID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringReader;

/**
 *
 * @author C
 */
public class StringWithID  implements Serializable{

    private final int id;
    private final String str;

    public StringWithID(int id, String str) {
	this.id = id;
	this.str = str;
    }

    public StringWithID(int id, BufferedReader br) throws IOException {
	this.id = id;
	this.str = br.readLine();
    }

    public StringWithID createDataID(int id, String str)
    {
	return new StringWithID(id,str);
    }
    
    public StringWithID createDataID(int id, BufferedReader br) throws IOException
    {
	return new StringWithID(id, br);
    }
    
    @Override
    public String toString() {
	return this.str;
    }

    public int toInt() {
	return this.id;
    }

    
    public final InputStream toInputStream() {
	return new ByteArrayInputStream(this.str.getBytes());
    }
    
    
    public final BufferedReader toBufferedReader() {
	return new BufferedReader(new StringReader(this.str));
    }
    
    public static byte[] serialize(StringWithID obj) throws IOException {
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(obj);
            }
            return b.toByteArray();
        }
    }

    public static StringWithID deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream o = new ObjectInputStream(b)){
                return (StringWithID) o.readObject();
            }
        }
    }

}
