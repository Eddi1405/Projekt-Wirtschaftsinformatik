package thowl.wiprojekt.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

/**
 * Entity class to describe a comment.
 */
@Entity
@Table(name="comment")
@Getter
@Setter
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_ID", nullable = false)
    private User authorID;

    @Column(name="date")
    private Date date;

    @Column(name="content")
    private String content;

    // @Column(name = "picture_path")
    // private String picturePath;

}
