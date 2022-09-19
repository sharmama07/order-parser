package com.unlimint.orderparser.util;

import java.util.concurrent.atomic.AtomicInteger;

public class GlobalIdCounter {
    
    private final static AtomicInteger globalId = new AtomicInteger(0);

    public static Integer getNextGlobalId() {
        return globalId.incrementAndGet();
    }
}
