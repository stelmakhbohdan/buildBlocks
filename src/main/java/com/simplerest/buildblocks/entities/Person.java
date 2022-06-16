package com.simplerest.buildblocks.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
//@JsonIgnoreProperties({"firstname","lastname"})
@Table(name = "persons")
//@JsonFilter(value = "userFilter")
public class Person extends RepresentationModel<Person> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.External.class)
    private Long id;

    @NotEmpty(message = "Username is mandatory field.Please provide username")
    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
    @JsonView(Views.External.class)
    private String username;

    @Size(min = 2,message = "Firstname should have atleast 2 characters")
    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String firstname;

    @Column(name = "LAST_NAME", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String lastname;

    @Column(name = "EMAIL", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String email;

    @JsonView(Views.Internal.class)
    @Column(name = "ROLE", length = 50, nullable = false)
    private String role;

//    @JsonIgnore
    @Column(name = "SSN", length = 50,nullable = false,unique = true)
    @JsonView(Views.Internal.class)
    private String ssn;

    @OneToMany(mappedBy = "user")
    @JsonView(Views.Internal.class)
    private List<Order> orders;
}