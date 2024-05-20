// Generated by delombok at Thu May 09 13:42:06 MSK 2024
package ru.bardinpetr.itmo.lab3.app.auth;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.bardinpetr.itmo.lab3.app.auth.db.utils.PasswordService;
import ru.bardinpetr.itmo.lab3.context.ContextProvider;
import ru.bardinpetr.itmo.lab3.data.dao.impl.UserDAO;
import ru.bardinpetr.itmo.lab3.data.models.User;
import ru.bardinpetr.itmo.lab3.navigation.NavigationController;
import static jakarta.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static jakarta.security.enterprise.AuthenticationStatus.SUCCESS;
import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@RequestScoped
@Named("loginBean")
public class LoginBean {
    @java.lang.SuppressWarnings("all")
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginBean.class);
    @Inject
    private NavigationController navigation;
    @Inject
    private ContextProvider contextReq;
    @Inject
    private UserDAO userDAO;
    @Inject
    private PasswordService passwordService;
    @Inject
    private Validator validator;
    @Inject
    private UserSession session;
    @NotNull(message = "Login should be provided")
    @Size(min = 3, message = "Login should be at last 3 characters")
    private String username = "username";
    @NotNull(message = "Password should be provided")
    @Size(min = 8, message = "Password should be at last 8 characters")
    private String password = "password";

    public String doLogin() {
        checkLoggedInUser();
        log.info("Started login authentication for {}", username);
        var cred = asCredential();
        if (cred == null) {
            log.error("User {} authentication failed", username);
            return null;
        }
        var status = contextReq.getSecurityContext().authenticate(contextReq.getRequest(), contextReq.getResponse(), withParams().newAuthentication(true).credential(cred));
        log.info("User {} login status {}", username, status);
        if (status.equals(SUCCESS)) {
            contextReq.getContext().responseComplete();
            return navigation.toHome();
        } else if (status.equals(SEND_FAILURE)) {
            contextReq.sendMessage(FacesMessage.SEVERITY_ERROR, "Invalid username/password");
            log.error("User {} authentication failed", username);
        }
        return null;
    }

    public String doRegister() {
        checkLoggedInUser();
        log.info("Registering user {}", username);
        if (userDAO.findByLogin(username).isPresent()) {
            log.warn("Tried to register existing user {}", username);
            return null;
        }
        var hash = passwordService.encode(password);
        var user = new User();
        user.setLogin(username);
        user.setPasswordHash(hash);
        if (userDAO.insert(user)) {
            userDAO.addRole(user, "user");
            log.info("Registered user {} successfully", username);
        } else {
            log.warn("Register user {} failed", username);
        }
        return doLogin();
    }

    private void checkLoggedInUser() {
        session.doLogout();
    }

    protected UsernamePasswordCredential asCredential() {
        return validator.validate(this).isEmpty() ? new UsernamePasswordCredential(username, new Password(password)) : null;
    }

    @java.lang.SuppressWarnings("all")
    public LoginBean() {
    }

    @java.lang.SuppressWarnings("all")
    public NavigationController getNavigation() {
        return this.navigation;
    }

    @java.lang.SuppressWarnings("all")
    public ContextProvider getContextReq() {
        return this.contextReq;
    }

    @java.lang.SuppressWarnings("all")
    public UserDAO getUserDAO() {
        return this.userDAO;
    }

    @java.lang.SuppressWarnings("all")
    public PasswordService getPasswordService() {
        return this.passwordService;
    }

    @java.lang.SuppressWarnings("all")
    public Validator getValidator() {
        return this.validator;
    }

    @java.lang.SuppressWarnings("all")
    public UserSession getSession() {
        return this.session;
    }

    @java.lang.SuppressWarnings("all")
    public String getUsername() {
        return this.username;
    }

    @java.lang.SuppressWarnings("all")
    public String getPassword() {
        return this.password;
    }

    @java.lang.SuppressWarnings("all")
    public void setNavigation(final NavigationController navigation) {
        this.navigation = navigation;
    }

    @java.lang.SuppressWarnings("all")
    public void setContextReq(final ContextProvider contextReq) {
        this.contextReq = contextReq;
    }

    @java.lang.SuppressWarnings("all")
    public void setUserDAO(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @java.lang.SuppressWarnings("all")
    public void setPasswordService(final PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @java.lang.SuppressWarnings("all")
    public void setValidator(final Validator validator) {
        this.validator = validator;
    }

    @java.lang.SuppressWarnings("all")
    public void setSession(final UserSession session) {
        this.session = session;
    }

    @java.lang.SuppressWarnings("all")
    public void setUsername(final String username) {
        this.username = username;
    }

    @java.lang.SuppressWarnings("all")
    public void setPassword(final String password) {
        this.password = password;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof LoginBean)) return false;
        final LoginBean other = (LoginBean) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$navigation = this.getNavigation();
        final java.lang.Object other$navigation = other.getNavigation();
        if (this$navigation == null ? other$navigation != null : !this$navigation.equals(other$navigation)) return false;
        final java.lang.Object this$contextReq = this.getContextReq();
        final java.lang.Object other$contextReq = other.getContextReq();
        if (this$contextReq == null ? other$contextReq != null : !this$contextReq.equals(other$contextReq)) return false;
        final java.lang.Object this$userDAO = this.getUserDAO();
        final java.lang.Object other$userDAO = other.getUserDAO();
        if (this$userDAO == null ? other$userDAO != null : !this$userDAO.equals(other$userDAO)) return false;
        final java.lang.Object this$passwordService = this.getPasswordService();
        final java.lang.Object other$passwordService = other.getPasswordService();
        if (this$passwordService == null ? other$passwordService != null : !this$passwordService.equals(other$passwordService)) return false;
        final java.lang.Object this$validator = this.getValidator();
        final java.lang.Object other$validator = other.getValidator();
        if (this$validator == null ? other$validator != null : !this$validator.equals(other$validator)) return false;
        final java.lang.Object this$session = this.getSession();
        final java.lang.Object other$session = other.getSession();
        if (this$session == null ? other$session != null : !this$session.equals(other$session)) return false;
        final java.lang.Object this$username = this.getUsername();
        final java.lang.Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final java.lang.Object this$password = this.getPassword();
        final java.lang.Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof LoginBean;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $navigation = this.getNavigation();
        result = result * PRIME + ($navigation == null ? 43 : $navigation.hashCode());
        final java.lang.Object $contextReq = this.getContextReq();
        result = result * PRIME + ($contextReq == null ? 43 : $contextReq.hashCode());
        final java.lang.Object $userDAO = this.getUserDAO();
        result = result * PRIME + ($userDAO == null ? 43 : $userDAO.hashCode());
        final java.lang.Object $passwordService = this.getPasswordService();
        result = result * PRIME + ($passwordService == null ? 43 : $passwordService.hashCode());
        final java.lang.Object $validator = this.getValidator();
        result = result * PRIME + ($validator == null ? 43 : $validator.hashCode());
        final java.lang.Object $session = this.getSession();
        result = result * PRIME + ($session == null ? 43 : $session.hashCode());
        final java.lang.Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final java.lang.Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "LoginBean(navigation=" + this.getNavigation() + ", contextReq=" + this.getContextReq() + ", userDAO=" + this.getUserDAO() + ", passwordService=" + this.getPasswordService() + ", validator=" + this.getValidator() + ", session=" + this.getSession() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ")";
    }
}