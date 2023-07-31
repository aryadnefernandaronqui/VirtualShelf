package virtualShelf.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import virtualShelf.dtos.CreateUser;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String password;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Shelf> shelves;

    // UM user tem MUITAS estantes
    // ONE user has MANY shelves
    // ONE to MANY
    //


    public User(CreateUser newUser) {
        name = newUser.name();
        email = newUser.email();
        password = newUser.password();
    }
}
