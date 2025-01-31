package ro.occam.kosynco.dtos;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Serdeable
public class HealthCheckResponse {

    @NotNull @NotBlank
    private String status;

    public HealthCheckResponse(String status) {
        this.status = status;
    }

    public @NotNull @NotBlank String getStatus() {
        return status;
    }
}
