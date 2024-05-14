package org.bookcatalog.bookcatalog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "notes")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note")
    @SequenceGenerator(name = "note", sequenceName = "note_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String body;

    private Date creationDate;

    @ManyToOne(optional = false) //note can`t be without Book
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "book_fk"))
    private Book book;

    @PreUpdate
    @PrePersist
    protected void onCreate(){
        creationDate = new Date();
    }

}
