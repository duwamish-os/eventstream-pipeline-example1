package com.event.pipeline1;

import com.ingestion.api.endpoints.JsonSchemaValidator;
import eventstream.producer.generic.GenericEventProducer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

/**
 * Created by prayagupd
 * on 2/6/17.
 */

@SpringBootApplication
public class EventIngestionPipeline1 {

    @Value("${eventstream.name}")
    String eventStreamName;

    @Bean
    GenericEventProducer eventProducer() {
        return new GenericEventProducer(eventStreamName);
    }

    @Bean
    Function<String, String> schemaEventType() {
        return payload -> new JSONObject(payload).getJSONObject("MessageHeader").getString("EventName");
    }

    @Bean
    JsonSchemaValidator jsonSchemaValidator(){
        return new JsonSchemaValidator();
    }

    public static void main(String[] args) {
        SpringApplication.run(EventIngestionPipeline1.class, args);
    }

}
