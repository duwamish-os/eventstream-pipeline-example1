package com.ingestion.api.config;

import com.eventstream.producer.EventProducer;
import com.eventstream.producer.EventProducerFactory;
import com.eventstream.state.EventStream;
import com.eventstream.state.factory.EventStreamFactory;
import com.ingestion.api.PipelineSetup;
import com.ingestion.api.validation.JsonSchemaValidator;
import com.ingestion.api.validation.Validator;
import com.ingestion.api.validation.rules.SchemaRule;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by prayagupd
 * on 2/6/17.
 */

@Configuration
@ComponentScan(basePackages = "com.*")
//@Profile("dev")
public class EventIngestionPipelineConfig1 {

    @Value("${stream.name}")
    String eventStreamName;

    @Bean
    EventProducer defaultEventProducer() {
        return new EventProducerFactory().create(eventStreamName);
    }

    @Bean
    @Primary
    @Qualifier("schemaEventTypeLambda")
    Function<String, String> schemaEventTypeLamda() {
        return payload -> new JSONObject(payload).getString("eventType");
    }

    @Bean
    @Primary
    @Qualifier("eventIdLambda")
    Function<String, String> eventIdLamda() {
        return payload -> new JSONObject(payload).getString("eventUuid");
    }

    @Bean
    @Primary
    @Qualifier("eventPartitionKey")
    Function<String, List<String>> eventPartitionKey() {
        return payload ->
                Collections.singletonList(new JSONObject(payload).getString("requiredField1"));
    }

    /**
     * provides eventstream interface for configured sriver,
     * it has stream operation capabilities (eg. create a stream, drop a stream etc)
     *
     * @return eventstream interface
     */
    @Bean
    EventStream eventStream() {
        return new EventStreamFactory().create();
    }

    @Bean
    Map<String, List<SchemaRule>> schemaRules() {
        return new HashMap<>();
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("templates/");
        return viewResolver;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propsConfig
                = new PropertySourcesPlaceholderConfigurer();
        propsConfig.setLocation(new ClassPathResource("build.properties"));
        propsConfig.setIgnoreResourceNotFound(true);
        propsConfig.setIgnoreUnresolvablePlaceholders(true);
        return propsConfig;
    }

    @Bean
    PipelineSetup pipelineSetup() {
        return new PipelineSetup();
    }


    @Bean
    Validator jsonSchemaValidator() {
        return new JsonSchemaValidator();
    }

}
