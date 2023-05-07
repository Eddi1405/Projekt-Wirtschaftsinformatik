package thowl.wiprojekt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Diese Klasse gibt die Informationen eines Bildes vor.
 * Die Annotation @Entity gibt an, dass es sich um eine JPA-Entity-Klasse handelt.
 * Die Annotation @Table gibt den Tabellennamen in der Datenbank an, auf die sich diese Entity bezieht.
 * Die Annotation @GeneratedValue gibt an, dass der Primärschlüssel automatisch generiert wird.
 * Die Annotation @Id gibt an, dass das Feld id der Primärschlüssel der Tabelle ist.
 * Die Annotationen @Getter und @Setter generieren die getter und setter Methoden.
 */
@Entity
@Table(name = "tag")
@Getter
@Setter
public class TagData {
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private int id;
    @Column(name = "description")
    private String description;

}
