EventStream pipeline example
--

uses Ingestion pipeline

```xml
    <dependency>
      <groupId>com.event.ingestion.pipeline</groupId>
      <artifactId>event-ingestion-pipeline</artifactId>
      <version>1.0</version>
    </dependency>
```

Running
------

```bash
mvn -DskipTests=true clean package
cp target/event-pipeline1.war /usr/local/apache-tomcat-9.0.14/webapps/


```

Ingestion
---------

```bash
curl --user "ingestion-example:ingestion-example" -H "Content-Type: application/json" -X POST -d '{"eventName" : "TestIngestionEvent", "eventUuid": "1", requiredField1" : "someValue1"}' localhost:8080/event-pipeline1/ingest
{"eventId":"could not retrieve it","responseCode":"VALIDATION_ERROR","responseMessage":"error getting EventId"}
```


Eventstream
-----------

```
$ /usr/local/kafka_2.11-0.10.1.1/bin/kafka-console-consumer.sh --bootstrap-server localhost:2181 --topic "Pipeline1Stream" --from-beginning
{"MessageHeader" : { "EventName" : "TestIngestionEvent"}, "someField1" : "someValue1"}
```