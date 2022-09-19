package com.unlimint.orderparser.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.unlimint.orderparser.workers.OrderParser;

/**
 * @author Mayur Sharma
 * This class is created to off load main application thread 
 * and start processing files in other threads
 */
@Component
public class OrderHandler {

    @Autowired
    @Qualifier("orderWorkerExecutor")
    private ExecutorService executorService;
    @Autowired(required = false)
    private List<Future> tasks = new ArrayList<Future>();

    public void submitFile(String fileName) {
        final Future<?> futureTask = executorService
                        .submit(new OrderParser(fileName));
        tasks.add(futureTask);
    }

    public boolean isOrderCompleted() {
        return tasks.stream().allMatch(futureTask -> futureTask.isDone());
    }

}
