package virtualShelf.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import virtualShelf.dtos.CreateShelf;
import virtualShelf.dtos.GetBooks;
import virtualShelf.enums.EStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book_on_shelf")
public class BookOnShelf {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private boolean favorite;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    @Column(name = "book_id")
    private UUID bookId;
    @Column(name = "shelf_id")
    private UUID shelfId;

    public BookOnShelf(GetBooks newBookonShelf) {
        this.bookId = newBookonShelf.bookId();
        this.shelfId = newBookonShelf.shelfId();

    }

}
