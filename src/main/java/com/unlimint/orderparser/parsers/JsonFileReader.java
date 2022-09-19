package com.unlimint.orderparser.parsers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;

public class JsonFileReader implements IFileReader<String[]> {

    private String fileName;
    private BufferedReader br;
    private String data;

    public JsonFileReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void initiallize() throws FileNotFoundException {
        br = new BufferedReader(new FileReader(fileName));
    }

    @Override
    public boolean hasNext() throws Exception{
        return ((data = br.readLine()) != null);
    }

    @Override
    public String[] next() {
        // System.out.println(" Next data is " + data);
        JSONObject json = new JSONObject(data);
        if(json.has("orderId") && json.has("amount") 
                && json.has("currency") && json.has("comment") ) {
            return new String[] {String.valueOf(json.get("orderId")), 
                        String.valueOf(json.get("amount")),
                        String.valueOf(json.get("currency")),
                        String.valueOf(json.get("comment"))};
        }
        else {
            return new String[]{""};
        }
    }
    
    @Override
    public void close() throws Exception {
        br.close();
    }
}
