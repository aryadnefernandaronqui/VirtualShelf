package virtualShelf.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import virtualShelf.dtos.RegisterBook;
import virtualShelf.dtos.UpdateBook;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String author;
    private String publisher;
    private String genre;
    private String language;
    @OneToMany
    @JoinColumn(name = "book_id")
    private List<BookOnShelf> bookOnShelves;

    //MUITOS livros estão em MUITAS estantes
    //MANY books has MANY shelves


    public Book(RegisterBook newBook) {
        this.name = newBook.name();
        this.author = newBook.author();
        this.publisher = newBook.publisher();
        this.genre = newBook.genre();
        this.language = newBook.language();
    }

    public void updateBook(UpdateBook updatedBook){
        if(updatedBook.name() != null){
            name = updatedBook.name();
        }
        if(updatedBook.author() != null){
            author = updatedBook.author();
        }
        if(updatedBook.publisher() != null){
            publisher = updatedBook.publisher();
        }
        if(updatedBook.genre() != null){
            genre = updatedBook.genre();
        }
        if(updatedBook.language() != null){
            language = updatedBook.language();
        }
    }
}
