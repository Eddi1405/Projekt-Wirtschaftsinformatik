package thowl.wiprojekt.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class to describe a comment.
 */
@Entity
@Table(name="comments")
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    // TODO: oder lieber AuthorID wie beim Thread?
    @Column(name="author")
    private User author;

    @Column(name="date")
    private Date date;

    @Column(name="contents")
    private String contents;

    @Column(name="threadID")
    private long threadID;

    // @Column(name = "picture_path")
    // private String picturePath;

}
