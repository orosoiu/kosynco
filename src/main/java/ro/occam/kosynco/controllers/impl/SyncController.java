package ro.occam.kosynco.controllers.impl;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import ro.occam.kosynco.controllers.SyncControllerApi;
import ro.occam.kosynco.dtos.DocumentDto;
import ro.occam.kosynco.dtos.DocumentSyncResponse;
import ro.occam.kosynco.security.RequestUtils;
import ro.occam.kosynco.services.DocumentService;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static ro.occam.kosynco.security.Roles.*;

@Controller(SyncController.PREFIX)
@ExecuteOn(TaskExecutors.IO)
@Secured(SecurityRule.IS_AUTHENTICATED)
public class SyncController implements SyncControllerApi {

    static final String PREFIX = "/syncs";

    @Inject
    private DocumentService documentService;

    @Override
    @Secured({ROLE_USER})
    public HttpResponse<?> syncProgress(HttpRequest<DocumentDto> httpRequest) {

        DocumentDto documentDto = httpRequest.getBody(DocumentDto.class).get();
        String username = RequestUtils.getUsername(httpRequest);
        if(documentService.exists(documentDto.getDocumentHash(), username)) {
            documentService.update(documentDto, username);
        } else {
            documentService.create(documentDto, username);
        }
        return HttpResponse.ok(new DocumentSyncResponse(documentDto.getDocumentHash(), Date.from(Instant.now())));
    }

    @Override
    @Secured({ROLE_USER})
    public HttpResponse<?> getDocumentProgress(@PathVariable String documentHash, HttpRequest<String> httpRequest) {

        String username = RequestUtils.getUsername(httpRequest);
        Optional<DocumentDto> documentDto = documentService.get(documentHash, username);
        return documentDto.isPresent() ?  HttpResponse.ok(documentDto.get()) : HttpResponse.ok();
    }
}
