package org.bookcatalog.bookcatalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.bookcatalog.bookcatalog.dto.BookDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "books")
@Setter
@Getter
@ToString(exclude = "catalog")
@NoArgsConstructor
@EqualsAndHashCode
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book")
    @SequenceGenerator(name = "book", sequenceName = "book_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String body;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "catalog_fk")
    private Catalog catalog;

    @JsonIgnore
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>(); // user note for current book

    @PrePersist
    @PreUpdate
    protected void onCreate() {
        creationDate = new Date();
    }

    public Book(BookDto bookDto, Catalog catalog) {
        this.name = bookDto.name();
        this.body = bookDto.body();
        this.catalog = catalog;
    }
}
