package com.unlimint.orderparser.parsers;

import java.io.FileNotFoundException;

public interface IFileReader<T> {
    
    boolean hasNext() throws Exception;

    T next();

    void initiallize()  throws FileNotFoundException;

    void close() throws Exception;
}
