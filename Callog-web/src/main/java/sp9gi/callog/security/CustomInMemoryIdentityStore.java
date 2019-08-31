package sp9gi.callog.security;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class CustomInMemoryIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

        if (login.getCaller().equals("admin") && login.getPasswordAsString().equals("ADMIN1234")) {
            return new CredentialValidationResult("admin", new HashSet<>(Arrays.asList("ADMIN")));
        } else if (login.getCaller().equals("SP9OZI") && login.getPasswordAsString().equals("USER1234")) {
            return new CredentialValidationResult("SP9OZI", new HashSet<>(Arrays.asList("USER")));
        } else {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }
}
