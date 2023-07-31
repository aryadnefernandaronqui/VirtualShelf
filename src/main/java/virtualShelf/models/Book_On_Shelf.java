package virtualShelf.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import virtualShelf.enums.EStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book_on_shelf")
public class Book_On_Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private boolean favorite;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    @Column(name = "book_id")
    private UUID book_id;
    @Column(name = "shelf_id")
    private UUID shelf_id;

}
