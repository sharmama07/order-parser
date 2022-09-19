mvn clean install -DskipTest=true
cd target
java -jar order-parser.jar abc.csv xyz.json 