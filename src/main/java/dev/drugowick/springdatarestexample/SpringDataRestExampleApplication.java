package dev.drugowick.springdatarestexample;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.*;

@SpringBootApplication
public class SpringDataRestExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDataRestExampleApplication.class, args);
    }
}

@Entity @Data
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}

@Entity @Data
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}

@RepositoryRestResource(collectionResourceRel = "users", itemResourceRel = "user", path = "users")
interface PersonRepository extends JpaRepository<User, Long> {}

@RepositoryRestResource(collectionResourceRel = "roles", itemResourceRel = "role", path = "roles")
interface RoleRepository extends JpaRepository<Role, Long> {}