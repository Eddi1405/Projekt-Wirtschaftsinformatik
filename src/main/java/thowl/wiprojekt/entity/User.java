package thowl.wiprojekt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import thowl.wiprojekt.objects.LearningType;
import thowl.wiprojekt.objects.Role;

/**
 * This class specifies the information of a User.
 * The annotation @Entity indicates that this is a JPA entity class.
 * The annotation @Table specifies the table name in the database to which this entity refers.
 * The annotation @GeneratedValue indicates that the primary key is generated automatically.
 * The annotation @Id indicates that the id field is the primary key of the table.
 * The annotations @Getter and @Setter generate the getter and setter methods.
 * The annotation @ManyToMany specifies that field is the foreign key of the table.
 *
 * @Author Luca Hüpping
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    // ID that is being generated by JPA for every User. Every ID is unique.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    // A Username for the User. Username is required and has to be unique.
    @Column(name = "username", unique = true)
    private String username;

    // A Password for the User. A Password is required.
    @Column(name = "password")
    private String password;

    // E-Mail for the User. The E-Mail is required and has to be unique.
    @Column(name = "email", unique = true)
    private String email;

    // Boolean value for the User which determines if dark mode is enabled or not.
    // The default is false.
    @Column(name = "dark_mode")
    private boolean darkmode = false;

    // Boolean value for the User which determines, if german or english is used.
    // This can later be extendet to include other languages, for now only German and English are supported by this API.
    @Column(name = "de_en")
    private boolean deEn = false;

    // Boolean value if the contrast Setting for the User is toggled or not.
    @Column(name = "contrast")
    private boolean contrast = false;

    // Int value for the Font size for the User.
    @Column(name = "font_size")
    private int fontSize;

    // Boolean Value to determine if the User needs Eye-Tracking or not.
    @Column(name = "Eye_tracking")
    private boolean eyeTracking = false;

    // Int value to change between different pre-sets of color-Blindness Color patterns.
    @Column(name = "color_blindness")
    private int colorBlindness;

    // Voolean value to determine fi the User needs Sign-Language support or not.
    @Column(name = "sign_language")
    private boolean signLanguage = false;

    // Enum to determine which Role a User has. A standard role will be provided in the Register functionality
    @Enumerated(EnumType.STRING)
    private Role role;

    // double Value to save the percentage of how much a User is the Learning type Visual
    @Column(name = "learningtypeVisual")
    private double learningtypeVisual;

    // double Value to save the percentage of how much a User is the Learning type Aural
    @Column(name = "learningtypeAural")
    private double learningtypeAural;

    // double Value to save the percentage of how much a User is the Learning type Read / Write
    @Column(name = "learningtypeReadwrite")
    private double learningtypeReadwrite;

    // double Value to save the percentage of how much a User is the Learning type Kinesthetic
    @Column(name = "learningtypeKinesthetic")
    private double learningtypeKinesthetic;

}