package ru.kata.spring.boot_security.demo.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 30, message = "Name должно содержать от 2 до 30 символов")
    @NotBlank(message = "Name не модет быть пустым")
    private String name;
    @Email(message = "Некорректный email")
    private String email;
    @NotNull(message = "Age не должен быть пустым")
    @Min(value = 0, message = "Age не может быть отрицательным")
    private Integer age;
    @Column(unique = true)
    private String username;
    @Size(min = 1, max = 60, message = "Password должен быть от 1 до 60 символов")
    @NotBlank(message = "Password не модет быть пустым")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String name, String email, Integer age, String username, String password) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public User(String name, String email, Integer age, String username, String password, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {this.roles.add(role);}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(age, user.age) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, username, password, roles);
    }
}
























