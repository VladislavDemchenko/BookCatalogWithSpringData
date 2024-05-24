package org.bookcatalog.bookcatalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.bookcatalog.bookcatalog.dto.NoteDto;

import java.util.Date;

@Entity
@Table(name = "notes")
@Setter
@Getter
@ToString(exclude = "book")
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note")
    @SequenceGenerator(name = "note", sequenceName = "note_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @NotEmpty
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

    public Note(NoteDto noteDto, Book book) {
        this.body = noteDto.body();
        this.book = book;
    }
}
