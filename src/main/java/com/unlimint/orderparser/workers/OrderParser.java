package com.unlimint.orderparser.workers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.unlimint.orderparser.model.StopConverter;
import com.unlimint.orderparser.parsers.CSVFileReader;
import com.unlimint.orderparser.parsers.IFileReader;
import com.unlimint.orderparser.parsers.JsonFileReader;

/**
 * @author Mayur Sharma
 * This is a worker thread for all the files, 
 * - decide which type of file reader it requires
 * - parse the file records in same thread and put it in queue
 * - creates convertor thread to process the same queue 
 */
public class OrderParser implements Runnable{

    private String fileName;
    private StopConverter stopConverter;

    public OrderParser(String fileName) {
        // System.out.println("Worker Created for file " + fileName);
        this.fileName = fileName;
        this.stopConverter = new StopConverter();
    }

    @Override
    public void run() {
        // System.out.println("Worker Started for file " + fileName);
        BlockingQueue<Object> queue = new LinkedBlockingQueue<>();

        startConvertor(fileName, queue, stopConverter);
        IFileReader fileReader = getFileReader(fileName);
        try {
            
            if(fileReader == null) throw new IllegalArgumentException("Invalid file type, supported are json and csv");
            
            fileReader.initiallize();
        
            while(fileReader.hasNext()) {
                Object nextData = fileReader.next();
                // System.out.println("Next data is " + nextData);
                queue.offer(nextData);
            }
            // System.out.println("###########  No more records now for filename  " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        stopConverter.setStopConverter(true);
        // System.out.println("Worker Stopped for file " + fileName);
    }

    private void startConvertor(String fileName, BlockingQueue<Object> queue, StopConverter stopConverter) {
        OrderConverter converter = new OrderConverter(fileName, queue, stopConverter);
        new Thread(converter).start();
    }

    private IFileReader getFileReader(String fileName) {
        if(fileName != null && fileName.endsWith("csv"))
            return new CSVFileReader(fileName);
        else if(fileName != null && fileName.endsWith("json"))
            return new JsonFileReader(fileName);
        return null;
    }
    
}
