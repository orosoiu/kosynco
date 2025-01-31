package ro.occam.kosynco.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import ro.occam.kosynco.dtos.UserDto;

import static ro.occam.kosynco.controllers.MediaType.APPLICATION_JSON_KOREADER;

public interface UserControllerApi {

    /**
     * Checks whether the user is authorized
     * This is a convenience validation method required by the KOreader sync API, the actual authorization
     * should be handled by Micronaut Security and {@link ro.occam.kosynco.security.HeaderAuthenticationFetcher}
     * @return 200 OK if user exists and is enabled, 401 Unauthorized otherwise
     */
    @Get(uri = "/auth", consumes = {MediaType.APPLICATION_JSON, APPLICATION_JSON_KOREADER})
    @Produces({MediaType.APPLICATION_JSON, APPLICATION_JSON_KOREADER})
    HttpResponse<String> authoriseUser();

    /**
     * Creates a new user
     * @param userDto user data
     * @return 402 for any error (user already exists, registration disabled), 201 if user created successfully
     */
    @Post(uri = "/create", consumes = {MediaType.APPLICATION_JSON, APPLICATION_JSON_KOREADER})
    @Produces({MediaType.APPLICATION_JSON, APPLICATION_JSON_KOREADER})
    HttpResponse<?> createUser(UserDto userDto);
}
