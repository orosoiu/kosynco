package ro.occam.kosynco.security;

import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.filters.AuthenticationFetcher;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import ro.occam.kosynco.entities.User;
import ro.occam.kosynco.services.UserService;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ro.occam.kosynco.security.Roles.ROLE_ADMIN;
import static ro.occam.kosynco.security.Roles.ROLE_USER;

/**
 * Authorizes user based on the {@value ro.occam.kosynco.security.RequestUtils#X_AUTH_USER_HEADER}
 * and {@value ro.occam.kosynco.security.RequestUtils#X_AUTH_KEY_HEADER} headers
 */
public class HeaderAuthenticationFetcher implements AuthenticationFetcher<HttpRequest> {

    @Inject
    private UserService userService;

    @Override
    public Publisher<Authentication> fetchAuthentication(HttpRequest request) {
        return Mono.create(emitter -> {
            String username = RequestUtils.getUsername(request);
            String password = RequestUtils.getPassword(request);

            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                emitter.success();
                return;
            }

            Optional<User> user = userService.get(username);
            if (user.isEmpty() || !Objects.equals(user.get().getPasswordHash(), password) || !user.get().getIsActive()) {
                emitter.success();
                return;
            }

            Collection<String> roles = List.of(ROLE_USER);
            if (user.get().getIsAdministrator()) {
                roles.add(ROLE_ADMIN);
            }

            emitter.success(Authentication.build(username, roles));
        });
    }
}
