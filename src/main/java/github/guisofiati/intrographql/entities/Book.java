package github.guisofiati.intrographql.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "TB_BOOKS")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private Integer pages;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
