package com.ingestion.eventpipeline1;

import com.ingestion.api.config.EventIngestionPipelineConfig1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(EventIngestionPipelineConfig1.class)
public class EventIngestionPipeline1 extends SpringBootServletInitializer {

    /**
     * entry point of the http server
     * @param args empty
     */
    public static void main(String[] args) {
        SpringApplication.run(EventIngestionPipeline1.class, args);
    }
}
