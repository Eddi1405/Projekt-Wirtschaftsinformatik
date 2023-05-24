package thowl.wiprojekt.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import thowl.wiprojekt.objects.LearningType;
import thowl.wiprojekt.objects.Role;

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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "dark_mode")
    private boolean darmode = false;

    @Column(name = "de_en")
    private boolean deEn = false;

    @Column(name = "contrast")
    private boolean contrast = false;

    // TODO: default font size as default value
    @Column(name = "font_size")
    private int fontSize;

    @Column(name = "Eye_tracking")
    private boolean eyeTracking = false;

    // TODO: How many different presets?
    @Column(name = "color_blindness")
    private int colorBlindness;

    @Column(name = "sign_language")
    private boolean signLanguage = false;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private LearningType learningtype;

    @ManyToMany
    Set<Chat> chats;

}
