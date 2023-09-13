package thowl.wiprojekt.entity;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import thowl.wiprojekt.objects.ContentType;

/**
 * This class specifies the information for a Message.
 * The annotation @Entity indicates that this is a JPA entity class.
 * The annotation @Table specifies the table name in the database to which this entity refers.
 * The annotation @GeneratedValue indicates that the primary key is generated automatically.
 * The annotation @Id indicates that the id field is the primary key of the table.
 * The annotations @Getter and @Setter generate the getter and setter methods.
 * The annotation @ManyToMany specifies that field is the foreign key of the table.
 * @Author Luca Hüpping
 */
@Entity
@Getter
@Setter
@Table(name = "message")
public class Message {

    // ID that is being generated by JPA for every Message. Every ID is unique.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Determines the Relation to the User who send the Message.
    // Contains the User ID of the User wo sent the Message.
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_ID", nullable = false)
    private User authorID;

    // Determines the Content Type of the Message. (Text/Image/...)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType contentType;

    // A Timestamp to save the time the Message was sent.
    @Column(name = "time", nullable = false)
    private Timestamp time;

    // Set of Users who have read the Message.
    @ManyToMany(fetch = FetchType.EAGER)
    Set<User> read;

    // Content Path defines the path for content (Images, ...) if needed.
    @Column(name = "content", nullable = false)
    private String contentPath;

    // Determines the parent.
    @Column(name ="parent")
    private Long parent;
}
