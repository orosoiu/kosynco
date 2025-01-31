package ro.occam.kosynco.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Serdeable
public class DocumentSyncResponse {

    @NotNull @NotBlank
    @JsonProperty("document")
    private String documentHash;
    @NotNull @NotBlank
    private Date timestamp;

    public DocumentSyncResponse(String documentHash, Date timestamp) {
        this.documentHash = documentHash;
        this.timestamp = timestamp;
    }

    public @NotNull @NotBlank String getDocumentHash() {
        return documentHash;
    }

    public @NotNull @NotBlank Date getTimestamp() {
        return timestamp;
    }
}
