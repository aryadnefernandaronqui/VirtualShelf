package virtualShelf.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import virtualShelf.dtos.CreateShelf;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shelves")
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "user_id")
    private UUID userId;
    private String name;
    @OneToMany
    @JoinColumn(name = "shelf_id")
    private List<BookOnShelf> bookOneShelves;

    public Shelf(CreateShelf newShelf, UUID userId) {
        this.name = newShelf.name();
        this.userId = userId;
    }
}
