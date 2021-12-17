package provides;

import org.keycloak.Config;
import org.keycloak.authorization.model.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

public class KeyCloakUserApiProviderFactory implements RealmResourceProviderFactory {
    public static final String ID = "userapi-rest";//Может быть любой

    public RealmResourceProvider create(KeycloakSession session) {
        return new KeyCloakUserApiProvider(session);
    }

    @Override
    public void init(Config.Scope config) {

    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {

    }

    public void close() {
    }

    public String getId() {
        return ID;
    }
}
