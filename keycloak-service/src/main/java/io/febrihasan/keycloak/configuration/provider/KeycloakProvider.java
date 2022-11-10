package io.febrihasan.keycloak.configuration.provider;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

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
     * Get client credential keycloak from application.yml
     *
     * @version 1
     */
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    public KeycloakProvider() { /* constructor */ }

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
     * Endpoint realm keycloak
     */
    public String getRealmUrl() {
        return String.join("/", serverUrl, "realms", realm);
    }

}
