package thowl.wiprojekt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

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
@Table(name = "thread")
@Getter
@Setter
public class ThreadData {
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "authorID", nullable = false)
    private UserData authorID;
    @ManyToMany
    Set<TagData> tagID;
    @Column(name = "header")
    private String header;
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    private Date date;

}
