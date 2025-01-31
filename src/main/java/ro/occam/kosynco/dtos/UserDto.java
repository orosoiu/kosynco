package ro.occam.kosynco.dtos;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Serdeable
public class UserDto {

    @NotNull @NotBlank
    private String username;
    @NotNull @NotBlank
    private String password;

    public @NotNull @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotNull @NotBlank String username) {
        this.username = username;
    }

    public @NotNull @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @NotBlank String password) {
        this.password = password;
    }
}
