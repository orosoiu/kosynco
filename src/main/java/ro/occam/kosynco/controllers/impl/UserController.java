package ro.occam.kosynco.controllers.impl;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import ro.occam.kosynco.controllers.UserControllerApi;
import ro.occam.kosynco.dtos.ErrorMessage;
import ro.occam.kosynco.dtos.UserCreateResponse;
import ro.occam.kosynco.dtos.UserDto;
import ro.occam.kosynco.services.UserService;

import static ro.occam.kosynco.security.Roles.ROLE_USER;

@Controller(UserController.PREFIX)
@ExecuteOn(TaskExecutors.VIRTUAL)
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserController implements UserControllerApi {

    static final String PREFIX = "/users";

    // Note: user registration can be entirely disabled by setting the
    // environment variable {@systemProperty REGISTRATION_DISABLED} to true
    @Property(name = "REGISTRATION_DISABLED", defaultValue = "false")
    private boolean registrationDisabled;

    @Inject
    private UserService userService;

    /**
     * The authorization is handled by Micronaut security and {@link ro.occam.kosynco.security.HeaderAuthenticationFetcher}
     * Once this method is reached the user is assumed authorized, so simply return 200 OK
     */
    @Override
    @Secured({ROLE_USER})
    public HttpResponse<String> authoriseUser() {
        return HttpResponse.ok();
    }

    @Override
    @PermitAll
    public HttpResponse<?> createUser(@Body UserDto userDto) {
        if (registrationDisabled) {
            return HttpResponse.status(HttpStatus.PAYMENT_REQUIRED).body(new ErrorMessage("Registration is disabled"));
        }

        if (userService.exists(userDto.getUsername())) {
            return HttpResponse.status(HttpStatus.PAYMENT_REQUIRED).body(new ErrorMessage("User already exists"));
        }

        userService.createUser(userDto);
        return HttpResponse.status(HttpStatus.CREATED).body(new UserCreateResponse(userDto.getUsername()));
    }
}
