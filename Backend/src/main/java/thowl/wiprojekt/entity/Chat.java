package thowl.wiprojekt.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import thowl.wiprojekt.objects.ChatType;

@Entity
@Getter
@Setter
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "chatName")
    private String chatName;

    @Column(name = "theme")
    private String theme;

    @Enumerated(EnumType.STRING)
    private ChatType chatType;

    @ManyToMany
    Set<Message> message;

    @ManyToMany
    Set<User> users;

}
