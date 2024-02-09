package com.pawsitiveVibes.users.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="app_user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=200)
    private String name;
    @Size(min=10, max=200)
    @Pattern(regexp=".+@.+\\..+")
    private String email;
    @Size(min=2, max=40)
    private String username;
    @Size(min=8, max=200)
    @Pattern(regexp="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$")
    private String password;
    @NotEmpty
    private String image;


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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }
    public User(String name,
                @Size(min = 10, max = 200) @Pattern(regexp = ".+@.+\\..+") String email,
                @Size(min = 2, max = 40) String username,
                @Size(min = 8, max = 200) @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$") String password,
                @NotEmpty String image) {
        super();
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.image = image;
    }
    public User() {
        super();
    }
}
