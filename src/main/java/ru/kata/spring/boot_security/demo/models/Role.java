package ru.kata.spring.boot_security.demo.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String username;

    @Transient
    @ManyToMany(mappedBy = "authorities")
    private Collection<User> users;

    public Role() {
    }

    public Role(Long id, String username) {
        //this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = username;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        //return getName();
        return username;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", users=" + users +
                '}';
    }
}
