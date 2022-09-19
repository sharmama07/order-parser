package com.unlimint.orderparser.workers;

import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang3.math.NumberUtils;

import com.unlimint.orderparser.model.OrderData;
import com.unlimint.orderparser.model.StopConverter;
import com.unlimint.orderparser.util.GlobalIdCounter;
/**
 * @author Mayur Sharma
 * Convertor thread is responsible to covert as per the validity of the record
 */
public class OrderConverter implements Runnable{

    private final BlockingQueue queue;
    private final String fileName;
    private String errorMessage;
    private StopConverter stopConverter;

    public OrderConverter(String fileName, final BlockingQueue queue, final StopConverter stopConverter) {
        this.fileName = fileName;
        this.queue = queue;
        this.stopConverter = stopConverter;
    }

    @Override
    public void run() {
        // System.out.println("Converter started ..");
        int lineNo = 0;
        while(stopConverter.isStopConverter() == false || !queue.isEmpty()) {
            // System.out.println(" Stop COnverter" + stopConverter + ", q size " + queue.size());
            String[] data = null;
            
            Object d = queue.poll();
            if(d == null) continue;
            data = (String[]) d;

            // System.out.println("Converter recieved data " + data);
            if(isValidData(data)) {
                final OrderData orderData = new OrderData(GlobalIdCounter.getNextGlobalId(), 
                                    Integer.parseInt(data[0]), Double.parseDouble(data[1]),
                                    data[3], fileName, ++lineNo, "OK");
                System.out.println(orderData); 
            }
            else {
                if(data!=null && data.length!=4) {
                    final OrderData orderData = new OrderData(GlobalIdCounter.getNextGlobalId(), 
                                        -1, -1.0,"Dummy comment", fileName, ++lineNo, "Input data count should be 4");
                    System.out.println(orderData);
                }
                else {
                    final OrderData orderData = new OrderData(GlobalIdCounter.getNextGlobalId(), 
                                        Integer.parseInt(data[0]), Double.parseDouble(data[1]),
                                        data[3], fileName, ++lineNo, errorMessage);
                    System.out.println(orderData);
                }
            }
        }
        // System.out.println("Converter stopped ..");
    }

    private boolean isValidData(String[] data) {
        if(data == null || data.length != 4) {
            errorMessage = "Input data count should be 4"; 
            return false;
        }

        if(data[0] == null || false == NumberUtils.isParsable(data[0]) ) {
            data[0] = "-1";
            errorMessage = "Order ID should be number";
            return false;
        }
        if(data[1] == null || false == NumberUtils.isParsable(data[1]) ) {
            data[1] = "-1";
            errorMessage = "Order amount should be number";
            return false;
        }
        
        return true;

    }
    
}
