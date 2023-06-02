package thowl.wiprojekt.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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


    @CreationTimestamp
    @Column(name="date", nullable = false)
    private Date date;

    @Column(name="content", nullable = false)
    private String content;

    // @Column(name = "picture_path")
    // private String picturePath;

}
