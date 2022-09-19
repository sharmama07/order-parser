# order-parser

How to build
mvn clean install

How to run (Sample csv or json files are present in resources folder)
java -jar order-parser.jar D:\work\workspace\order-parser\src\main\resources\order1.csv


Assumptions,
- my application is expecting the full path of files to be processed in command line argument
	For e.g. java -jar order-parser.jar D:\work\workspace\order-parser\src\main\resources\order1.csv
  
- In case of error records, service is replacing the actual values with the dummy value for that record
	For e.g., if one of the field is missing, I am keeping dummy values in records to show in output but giving proper error message
	Another e.g., if amount is not a number, replacing it with -1 with proper error message 

- Logs statement are commented out as asked only output records should go in stdout
