package virtualShelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualShelf.models.Book;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    boolean existsByName(String name);
}
