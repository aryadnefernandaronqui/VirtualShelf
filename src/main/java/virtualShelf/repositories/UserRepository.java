package virtualShelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualShelf.models.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
    boolean existsById(UUID userId);
    User getReferenceByEmail(String email);
}
