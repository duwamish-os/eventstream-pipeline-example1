uses ingestion pipeline

```
    <dependency>
      <groupId>com.ingestion.pipeline</groupId>
      <artifactId>ingestion-api</artifactId>
      <version>0.1.0</version>
    </dependency>
```


Ingestion
---------

```
$ curl -H "Content-Type: application/json" -X POST -d '{"MessageHeader" : { "EventName" : "TestIngestionEvent"}, "someField1" : "someValue1"}' localhost:9090/ingest
{"eventId":"141a4f3f-19aa-42a7-aa51-51ea997449c8","responseCode":"API-002","responseMessage":"Payload accepted"}
```


Eventstream
-----------

```
$ /usr/local/kafka_2.11-0.10.1.1/bin/kafka-console-consumer.sh --bootstrap-server localhost:2181 --topic "Pipeline1Stream" --from-beginning
{"MessageHeader" : { "EventName" : "TestIngestionEvent"}, "someField1" : "someValue1"}
```