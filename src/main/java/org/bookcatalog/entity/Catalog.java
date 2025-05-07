package org.bookcatalog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.bookcatalog.dto.CatalogDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "catalogs")
@Setter
@Getter
@ToString(exclude = "books")
@NoArgsConstructor
@EqualsAndHashCode
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalog_id")
    @SequenceGenerator(name = "catalog_id", sequenceName = "catalog_seq", allocationSize = 1)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String catalogName;

    private String description;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    @PrePersist
    @PreUpdate
    protected void onCreate() {
        creationDate = new Date();
    }

    public Catalog(CatalogDto catalogDto) {
        this.catalogName = catalogDto.catalogName();
        this.description = catalogDto.catalogDescription();
    }
}


