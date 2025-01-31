package ro.occam.kosynco.services;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import ro.occam.kosynco.dtos.DocumentDto;
import ro.occam.kosynco.entities.Document;
import ro.occam.kosynco.entities.User;
import ro.occam.kosynco.repositories.DocumentRepository;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Singleton
public class DocumentService {

    @Inject
    private DocumentRepository documentRepository;
    @Inject
    private UserService userService;

    public Optional<DocumentDto> get(String documentHash, String username) {
        Optional<Document> document = find(documentHash, username);
        if (document.isPresent()) {
            return Optional.of(new DocumentDto(
                    document.get().getDocumentHash(),
                    document.get().getProgress(),
                    document.get().getPercentage(),
                    document.get().getDevice(),
                    document.get().getDeviceId()));
        }
        return Optional.empty();
    }

    public void create(DocumentDto documentDto, String username) {
        Optional<User> user = userService.get(username);
        if (user.isPresent()) {
            Document document = new Document(
                    documentDto.getDocumentHash(),
                    documentDto.getProgress(),
                    documentDto.getPercentage(),
                    documentDto.getDevice(),
                    documentDto.getDeviceId(),
                    Date.from(Instant.now()),
                    user.get());
            documentRepository.save(document);
        }
    }

    public void update(DocumentDto documentDto, String username) {
        Optional<Document> documentOpt = find(documentDto.getDocumentHash(), username);
        if (documentOpt.isPresent()) {
            Document document = documentOpt.get();
            document.setProgress(documentDto.getProgress());
            document.setPercentage(documentDto.getPercentage());
            document.setDevice(documentDto.getDevice());
            document.setDeviceId(documentDto.getDeviceId());
            document.setTimestamp(Date.from(Instant.now()));
            documentRepository.update(document);
        }
    }

    public boolean exists(String documentHash, String username) {
        return documentRepository.exists(documentHash, username);
    }

    private Optional<Document> find(String documentHash, String username) {
        Optional<User> user = userService.get(username);
        if (user.isPresent()) {
            return documentRepository.findByDocumentHashAndUser(documentHash, user.get());
        }
        return Optional.empty();
    }
}
