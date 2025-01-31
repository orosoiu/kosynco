package ro.occam.kosynco.controllers.impl;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.annotation.security.PermitAll;
import ro.occam.kosynco.dtos.HealthCheckResponse;

@Controller("/healthcheck")
@ExecuteOn(TaskExecutors.IO)
@PermitAll
public class HealthCheckController {

    @Get(consumes = MediaType.ALL)
    @Produces(MediaType.APPLICATION_JSON)
    public HealthCheckResponse healthCheck() {
        return new HealthCheckResponse("OK");
    }
}
