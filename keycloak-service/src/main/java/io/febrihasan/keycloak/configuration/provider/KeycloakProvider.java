package io.febrihasan.keycloak.configuration.provider;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author febrihasan
 */
@Slf4j
@Configuration
@Getter
public class KeycloakProvider {

    /**
     * Get realm keycloak from application.yml
     */
    @Value("${keycloak.realm}")
    private String realm;

    /**
     * Get server url keycloak from application.yml
     */
    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    /**
     * Get client id keycloak from application.yml
     *
     * @version 1
     */
    @Value("${keycloak.resource}")
    private String clientId;

    /**
     * Get client id keycloak from application.yml
     *
     * @version 1
     */
    @Value("${master.keycloak.resource}")
    private String clientIdAdmin;

    /**
     * Get client credential keycloak from application.yml
     *
     * @version 1
     */
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    /**
     * Get username keycloak from application.yml
     *
     * @version 1
     */
    @Value("${master.keycloak.username}")
    private String username;

    /**
     * Get password keycloak from application.yml
     *
     * @version 1
     */
    @Value("${master.keycloak.password}")
    private String password;

    public KeycloakProvider() { /* constructor */ }

    private Keycloak getInstance() {
        return KeycloakBuilder
                .builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .username(username)
                .password(password)
                .clientId(clientIdAdmin)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }

    /**
     * Access keycloak with credentials
     * To login user with client id and credential grant
     *
     * @param clientId
     * @param clientSecret
     * @return keycloak object
     */
    public Keycloak newKeycloakBuilderWithCredentials(String clientId, String clientSecret) {
        return KeycloakBuilder.builder()
                .realm(realm)
                .serverUrl(serverUrl)
                .clientId(clientId)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientSecret(clientSecret)
                .build();
    }

    /**
     * Access keycloak with password credentials
     * To login user with username and password access on user realm
     *
     * @param phoneNumber
     * @param password
     * @return keycloak object
     */
    public Optional<AccessTokenResponse> newKeycloakBuilderWithPasswordCredentials
    (String phoneNumber, String password) {
        try {
            Keycloak keycloak = KeycloakBuilder.builder()
                    .realm(realm)
                    .serverUrl(serverUrl)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(phoneNumber)
                    .password(password)
                    .build();

            TokenManager tokenManager = keycloak.tokenManager();
            return Optional.of(tokenManager.getAccessToken());
        } catch (Exception e) {
            log.error("Error found: {}", e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Access keycloak with password credentials
     * To login user with username and password access on user realm
     *
     * @param username
     * @param password
     * @param email
     * @return keycloak object
     */
    public UserRepresentation registerNewUser
    (String username, String password, String email) {
        log.info("Registration: {}, {}", username, email);
        try {
            UsersResource usersResource = getInstance().realm(realm).users();
            CredentialRepresentation credential = createPasswordCredentials(password);

            UserRepresentation user = new UserRepresentation();
            user.setUsername(username);
            user.setFirstName("First Name");
            user.setLastName("Last Name");
            user.setEmail(email);
            user.setEmailVerified(false);
            user.setEnabled(true);
            user.singleAttribute("customAttribute", "customAttribute");
            user.setCredentials(Arrays.asList(credential));

            Optional<Response> response = Optional.of(usersResource.create(user));
            if (response.isPresent()) {

                if (response.get().getStatus() == HttpStatus.CREATED.value()) {
                    List<UserRepresentation> userList = usersResource
                            .search(username)
                            .stream()
                            .filter(userResp -> userResp.getUsername().equals(username))
                            .collect(Collectors.toList());

                    log.info("User with id: " + userList.get(0).getId() + " created");
                    return userList.get(0);
                }

                throw new RuntimeException();
            }
        } catch (Exception e) {
            log.error("Error found: {}", e.getMessage());
        }

        return null;
    }

    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(OAuth2Constants.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    /**
     * Endpoint realm keycloak
     */
    public String getRealmUrl() {
        return String.join("/", serverUrl, "realms", realm);
    }

}
