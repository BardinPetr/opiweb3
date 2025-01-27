// Generated by delombok at Thu May 09 13:42:06 MSK 2024
package ru.bardinpetr.itmo.lab3.app.auth.db;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import ru.bardinpetr.itmo.lab3.app.auth.db.models.DBUserPrincipal;
import ru.bardinpetr.itmo.lab3.app.auth.db.utils.PasswordService;
import ru.bardinpetr.itmo.lab3.data.dao.impl.UserDAO;
import java.util.Set;

@ApplicationScoped
public class DBIdentityStore implements IdentityStore {
    @java.lang.SuppressWarnings("all")
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DBIdentityStore.class);
    @Inject
    private PasswordService passwordService;
    @Inject
    private UserDAO dao;

    @Override
    public CredentialValidationResult validate(Credential inputCredential) {
        if (inputCredential instanceof UsernamePasswordCredential cred) {
            log.debug("Validating {} over DB users", cred.getCaller());
            var username = cred.getCaller();
            var srcUser = dao.findByLogin(username);
            if (srcUser.isEmpty()) return CredentialValidationResult.INVALID_RESULT;
            var user = srcUser.get();
            if (!passwordService.check(user.getPasswordHash(), cred.getPasswordAsString())) return CredentialValidationResult.INVALID_RESULT;
            return new CredentialValidationResult(new DBUserPrincipal(user), dao.getRoles(user));
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        return validationResult.getCallerGroups();
    }
}
