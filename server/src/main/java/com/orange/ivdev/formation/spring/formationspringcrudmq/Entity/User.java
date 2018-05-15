package com.orange.ivdev.formation.spring.formationspringcrudmq.Entity;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min=2, max=255, message="Please enter between {min} and {max} characters.")
    private String name;

    @NotNull
    @Email(message="Please enter a valid email.")
    private String email;

    @NotNull
    @Min(value=0, message="Please enter a valid age.")
    @Max(value=100, message="Please enter a valid age.")
    private Integer age;

    public User() {}

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
