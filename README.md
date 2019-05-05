EventStream pipeline example
-----------------------------

uses [Ingestion pipeline]()

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
curl --user "ingestion-example:ingestion-example" -H "Content-Type: application/json" -X POST -d '{"eventType" : "TestIngestionEvent", "eventUuid": "1", "requiredField1" : "someValue1"}' localhost:8080/event-pipeline1/ingest
{"eventId":"1","responseCode":"SUCCESS","responseMessage":"Payload accepted"}

curl --user "ingestion-example:ingestion-example" -H "Content-Type: application/json" -X POST -d '{"eventType":"CampaignEvent","eventUuid":1,"userId":"00792-2B9CEB66-EC8C-4AED-95BA-A865744CAA6B","campaignId":"00792-2B9CEB66-EC8C-4AED-95BA-A865744CAA6B","campaignDate":"2019-05-04T15:53:10.250479-07:00[America/Los_Angeles]"}' localhost:8080/event-pipeline1/ingest
{"eventId":"1","responseCode":"SUCCESS","responseMessage":"Payload accepted"}
```


Eventstream
-----------

```
$ /usr/local/kafka_2.11-0.10.1.1/bin/kafka-console-consumer.sh --bootstrap-server localhost:2181 --topic "Pipeline1Stream" --from-beginning
{"MessageHeader" : { "EventName" : "TestIngestionEvent"}, "someField1" : "someValue1"}
```