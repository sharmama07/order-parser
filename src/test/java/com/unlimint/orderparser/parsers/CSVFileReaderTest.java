package com.unlimint.orderparser.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.opencsv.CSVWriter;

@SpringBootTest(args = "D:\\work\\workspace\\order-parser\\src\\main\\resources\\order1.csv")
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application.properties")
public class CSVFileReaderTest {

    private static File csvFile;

    @BeforeAll
    public static void init() throws IOException {
        System.out.println("Initialize test data");
        csvFile = new File("order-data.csv");
        FileWriter outputfile = new FileWriter(csvFile);
  
        // create CSVWriter object filewriter object as parameter
        CSVWriter writer = new CSVWriter(outputfile);
  
        // adding header to csv
        String[] data = { "22", "12345", "USD", "order payment" };
        writer.writeNext(data);
        writer.close();
    }

    @Test
    public void testCSVReader() throws Exception {
        // init();
        CSVFileReader csvFileReader = new CSVFileReader(csvFile.getAbsolutePath());
        csvFileReader.initiallize();
        while(csvFileReader.hasNext()) {
            assertEquals("22",csvFileReader.next()[0]);
            break;
        }
    }
    
}
