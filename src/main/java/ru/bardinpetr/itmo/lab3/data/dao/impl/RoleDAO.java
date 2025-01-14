// Generated by delombok at Thu May 09 13:42:06 MSK 2024
package ru.bardinpetr.itmo.lab3.data.dao.impl;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ru.bardinpetr.itmo.lab3.data.util.EntityManagerProvider;
import ru.bardinpetr.itmo.lab3.data.dao.DAO;
import ru.bardinpetr.itmo.lab3.data.models.Role;
import java.io.Serializable;

@Named("roleDAO")
@ApplicationScoped
public class RoleDAO extends DAO<String, Role> implements Serializable {
    @java.lang.SuppressWarnings("all")
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RoleDAO.class);
    @Inject
    private EntityManagerProvider entityManagerProvider;

    public RoleDAO() {
        super(Role.class);
    }

    @PostConstruct
    public void init() {
        setManager(entityManagerProvider.getEntityManager());
    }

    public Role instance(String value) {
        return find(value).orElseGet(() -> {
            var newRole = Role.of(value);
            insert(newRole);
            return newRole;
        });
    }

    @java.lang.SuppressWarnings("all")
    public EntityManagerProvider getEntityManagerProvider() {
        return this.entityManagerProvider;
    }

    @java.lang.SuppressWarnings("all")
    public void setEntityManagerProvider(final EntityManagerProvider entityManagerProvider) {
        this.entityManagerProvider = entityManagerProvider;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof RoleDAO)) return false;
        final RoleDAO other = (RoleDAO) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$entityManagerProvider = this.getEntityManagerProvider();
        final java.lang.Object other$entityManagerProvider = other.getEntityManagerProvider();
        if (this$entityManagerProvider == null ? other$entityManagerProvider != null : !this$entityManagerProvider.equals(other$entityManagerProvider)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof RoleDAO;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $entityManagerProvider = this.getEntityManagerProvider();
        result = result * PRIME + ($entityManagerProvider == null ? 43 : $entityManagerProvider.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    public java.lang.String toString() {
        return "RoleDAO(entityManagerProvider=" + this.getEntityManagerProvider() + ")";
    }
}
