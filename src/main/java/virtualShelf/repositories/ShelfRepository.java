package virtualShelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualShelf.models.Shelf;

import java.util.UUID;

public interface ShelfRepository extends JpaRepository<Shelf, UUID> {
    boolean existsByName(String name);
}
