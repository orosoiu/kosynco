package ro.occam.kosynco.dtos;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Serdeable
public class ErrorMessage {

    @NotNull @NotBlank
    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }

    public @NotNull @NotBlank String getMessage() {
        return message;
    }
}
