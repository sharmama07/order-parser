package com.unlimint.orderparser.parsers;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.opencsv.CSVReader;

public class CSVFileReader implements IFileReader<String[]> {

    private CSVReader reader;
    private String fileName;
    private String[] data;

    public CSVFileReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void initiallize() throws FileNotFoundException {

        try {
            reader = new CSVReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            // System.out.println("File not found with name " + fileName);
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean hasNext() throws Exception {
        return (data = reader.readNext()) != null;
    }

    @Override
    public String[] next() {
        return data;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }

    
     
}
