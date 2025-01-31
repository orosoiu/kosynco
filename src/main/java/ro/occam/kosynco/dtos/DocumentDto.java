package ro.occam.kosynco.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Serdeable
public class DocumentDto {

    @NotNull @NotBlank
    @JsonProperty("document")
    private String documentHash;
    @NotNull @NotBlank
    private String progress;
    @NotNull @NotBlank
    private float percentage;
    @NotNull @NotBlank
    private String device;
    @NotNull @NotBlank
    @JsonProperty("device_id")
    private String deviceId;

    public DocumentDto(String documentHash, String progress, float percentage, String device, String deviceId) {
        this.documentHash = documentHash;
        this.progress = progress;
        this.percentage = percentage;
        this.device = device;
        this.deviceId = deviceId;
    }

    public @NotNull @NotBlank String getDocumentHash() {
        return documentHash;
    }

    public @NotNull @NotBlank String getProgress() {
        return progress;
    }

    @NotNull @NotBlank
    public float getPercentage() {
        return percentage;
    }

    public @NotNull @NotBlank String getDevice() {
        return device;
    }

    public @NotNull @NotBlank String getDeviceId() {
        return deviceId;
    }
}
