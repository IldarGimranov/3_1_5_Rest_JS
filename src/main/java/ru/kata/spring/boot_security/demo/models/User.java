package ru.kata.spring.boot_security.demo.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.utils.UserUtil;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(min = 2, max = 50, message = "Слишком короткое или длинное имя")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Size(min = 2, max = 50, message = "Слишком короткая или длинная фамилия")
    @Column(name = "lastname")
    private String lastName;

    @Min(value = 0, message = "Год не может быть меньше 0")
    @Column(name = "year")
    private int year;

    @Size(min = 3, message = "Не менее 3-х знаков")
    @Column(name = "password")
    private String password;

    @Size(min = 5, message = "Слишком короткий e'mail")
    @Column(name = "email")
    private String email;

    public User(Long id, String username, String lastName, int year, String password, Collection<Role> authorities, String email) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.year = year;
        this.password = password;
        this.authorities = authorities;
        this.email = email;
    }

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> authorities;

    public User() {
    }

    @Override
    public Collection<Role> getAuthorities() {
        return authorities; //из пачки ролей делает пачку авторитис с такими же строками
    }

    public String getStringRole() {
        return UserUtil.getStringRoles(authorities);
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                ", year=" + year +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
