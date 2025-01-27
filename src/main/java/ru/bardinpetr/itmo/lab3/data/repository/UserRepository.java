// Generated by delombok at Thu May 09 13:42:06 MSK 2024
package ru.bardinpetr.itmo.lab3.data.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ru.bardinpetr.itmo.lab3.data.dao.impl.UserDAO;
import ru.bardinpetr.itmo.lab3.data.models.Point;
import ru.bardinpetr.itmo.lab3.data.models.PointResult;
import ru.bardinpetr.itmo.lab3.data.models.User;
import ru.bardinpetr.itmo.lab3.data.models.AreaConfig;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

@Named("userRepo")
@ApplicationScoped
public class UserRepository implements Serializable {
    @Inject
    private UserDAO userDAO;

    @java.lang.SuppressWarnings("all")
    public UserRepository() {
    }

    @java.lang.SuppressWarnings("all")
    public UserDAO getUserDAO() {
        return this.userDAO;
    }

    @java.lang.SuppressWarnings("all")
    public void setUserDAO(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof UserRepository)) return false;
        final UserRepository other = (UserRepository) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$userDAO = this.getUserDAO();
        final java.lang.Object other$userDAO = other.getUserDAO();
        if (this$userDAO == null ? other$userDAO != null : !this$userDAO.equals(other$userDAO)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof UserRepository;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $userDAO = this.getUserDAO();
        result = result * PRIME + ($userDAO == null ? 43 : $userDAO.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "UserRepository(userDAO=" + this.getUserDAO() + ")";
    }
}
