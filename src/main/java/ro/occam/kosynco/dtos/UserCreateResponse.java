package ro.occam.kosynco.dtos;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Serdeable
public class UserCreateResponse {

    @NotNull
    @NotBlank
    private String username;

    public UserCreateResponse(String username) {
        this.username = username;
    }

    public @NotNull @NotBlank String getUsername() {
        return username;
    }
}
