package ro.occam.kosynco.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import ro.occam.kosynco.dtos.DocumentDto;

import static ro.occam.kosynco.controllers.MediaType.APPLICATION_JSON_KOREADER;

public interface SyncControllerApi {

    /**
     * Syncs the progress for a document. If the document does not exist, it should be created.
     */
    @Put(uri = "/progress", consumes = {MediaType.APPLICATION_JSON, APPLICATION_JSON_KOREADER})
    @Produces({MediaType.APPLICATION_JSON, APPLICATION_JSON_KOREADER})
    HttpResponse<?> syncProgress(HttpRequest<DocumentDto> httpRequest);

    /**
     * Returns data for a document identified by {@code documentHash}
     * @return 200 OK and document data in response body, if found
     */
    @Get(uri = "/progress/{documentHash}", consumes = {MediaType.APPLICATION_JSON, APPLICATION_JSON_KOREADER})
    @Produces({MediaType.APPLICATION_JSON, APPLICATION_JSON_KOREADER})
    HttpResponse<?> getDocumentProgress(String documentHash, HttpRequest<String> httpRequest);
}
