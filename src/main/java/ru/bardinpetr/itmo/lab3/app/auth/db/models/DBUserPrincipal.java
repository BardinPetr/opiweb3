// Generated by delombok at Thu May 09 13:42:06 MSK 2024
package ru.bardinpetr.itmo.lab3.app.auth.db.models;

import jakarta.security.enterprise.CallerPrincipal;
import ru.bardinpetr.itmo.lab3.data.models.User;

public class DBUserPrincipal extends CallerPrincipal {
    private final User user;

    public DBUserPrincipal(User user) {
        super(user.getLogin());
        this.user = user;
    }

    @java.lang.SuppressWarnings("all")
    public User getUser() {
        return this.user;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof DBUserPrincipal)) return false;
        final DBUserPrincipal other = (DBUserPrincipal) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$user = this.getUser();
        final java.lang.Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof DBUserPrincipal;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "DBUserPrincipal(user=" + this.getUser() + ")";
    }
}
