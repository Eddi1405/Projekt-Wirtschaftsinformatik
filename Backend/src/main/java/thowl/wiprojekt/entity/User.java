package thowl.wiprojekt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * This class specifies the information of an image.
 * The annotation @Entity indicates that this is a JPA entity class.
 * The annotation @Table specifies the table name in the database to which this entity refers.
 * The annotation @GeneratedValue indicates that the primary key is generated automatically.
 * The annotation @Id indicates that the id field is the primary key of the table.
 * The annotations @Getter and @Setter generate the getter and setter methods.
 * The annotation @ManyToMany specifies that field is the foreign key of the table.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private String role;
    @Column(name = "learningtype")
    private String learningtype;
}