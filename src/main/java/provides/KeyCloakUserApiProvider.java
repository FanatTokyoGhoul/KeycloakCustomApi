package provides;

import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.utils.MediaType;

import javax.ws.rs.*;
import java.util.List;
import java.util.stream.Collectors;

public class KeyCloakUserApiProvider implements RealmResourceProvider {
    private final KeycloakSession session; //Кейклок сессия к которой мы подключились

    public KeyCloakUserApiProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void close() {
    }

    @Override
    public Object getResource() {
        return this;
    }

    /**
     * Тут мы можем реализовать любое преобразование в данном случае
     * я преобразовываю UserModel -> UserRepresentation
     **/
    @GET
    @Path("users/search-by-attr")//Путь тоже может быть любой
    @NoCache
    @Produces({ MediaType.APPLICATION_JSON })
    @Encoded
    public List<UserRepresentation> searchUsersByAttribute(@QueryParam("attr") String attr,
                                                           @QueryParam("value") String value) {
        return session.users().searchForUserByUserAttributeStream(session.getContext().getRealm(), attr, value)
                .map(userModel -> ModelToRepresentation.toRepresentation(session, session.getContext().getRealm(), userModel))
                .collect(Collectors.toList());
    }
}
