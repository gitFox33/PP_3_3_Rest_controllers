package ru.kata.spring.boot_security.demo.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDTO {


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

    private List<String> roles = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, Integer age, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public UserDTO(Long id, String name, String email, Integer age, String username, String password, List<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void addRole(String role) {this.roles.add(role);}

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(name, userDTO.name) && Objects.equals(email, userDTO.email) && Objects.equals(age, userDTO.age) && Objects.equals(username, userDTO.username) && Objects.equals(password, userDTO.password) && Objects.equals(roles, userDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, username, password, roles);
    }
}
