package ro.occam.kosynco.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Serdeable
@Entity
public class Document {

    @Id
    @GeneratedValue
    @Nullable
    private Integer id;
    @NotNull
    @NotBlank
    @Column
    private String documentHash;
    @NotNull
    @NotBlank
    @Column
    private String progress;
    @NotNull
    @NotBlank
    @Column
    private float percentage;
    @NotNull
    @NotBlank
    @Column
    private String device;
    @NotNull
    @NotBlank
    @Column
    private String deviceId;
    @NotNull
    @NotBlank
    @Column
    private Date timestamp;
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;

    public Document(String documentHash, String progress, float percentage, String device, String deviceId, Date timestamp, User user) {
        this.documentHash = documentHash;
        this.progress = progress;
        this.percentage = percentage;
        this.device = device;
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentHash() {
        return documentHash;
    }

    public void setDocumentHash(String documentHash) {
        this.documentHash = documentHash;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
