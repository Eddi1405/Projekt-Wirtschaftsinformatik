package thowl.wiprojekt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
 * @Author Edward Siebert, Luca Hüpping
 */
//Edward Siebert
@Entity
@Table(name = "thread")
@Getter
@Setter
public class Thread {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_ID", nullable = false)
    private User authorID;

    @ManyToMany
    Set<Tag> tag;

    @Column(name = "header", nullable = false)
    private String header;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "picture_path")
    private String picturePath;

    @ManyToMany
    Set<Comment> comment;



}
