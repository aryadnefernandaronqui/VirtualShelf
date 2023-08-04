package virtualShelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualShelf.models.BookOnShelf;

import java.util.UUID;

public interface BookOnShelfRepository extends JpaRepository<BookOnShelf, UUID> {
    boolean existsByBookIdAndShelfId(UUID bookId, UUID shelfId);
}
