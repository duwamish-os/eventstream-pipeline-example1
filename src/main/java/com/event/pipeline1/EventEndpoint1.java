package com.event.pipeline1;

import com.ingestion.api.client.IngestionResponse;
import com.ingestion.api.endpoints.EventIngestionEndpoints;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by prayagupd
 * on 2/6/17.
 */

@RestController
public class EventEndpoint1 extends EventIngestionEndpoints {

    //FIXME how to override beans
}
