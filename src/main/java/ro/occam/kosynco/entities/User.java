package ro.occam.kosynco.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Serdeable
@Entity
public class User {

    @Id
    @NotNull @NotBlank
    private String username;
    @NotNull @NotBlank
    @Column
    private String passwordHash;
    @NotNull @NotBlank
    @Column
    private boolean isActive;
    @NotNull @NotBlank
    @Column
    private boolean isAdministrator;
    @OneToMany(mappedBy = "document")
    private List<Document> documents;

    public User(String username, String passwordHash, boolean isActive, boolean isAdministrator) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.isActive = isActive;
        this.isAdministrator = isAdministrator;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator(boolean isAdministrator) {
        this.isAdministrator = isAdministrator;
    }
}
