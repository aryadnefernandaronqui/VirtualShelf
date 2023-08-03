package virtualShelf.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import virtualShelf.dtos.RegisterBook;
import virtualShelf.dtos.ResponseError;
import virtualShelf.dtos.UpdateBook;
import virtualShelf.models.Book;
import virtualShelf.repositories.BookRepository;
import virtualShelf.repositories.UserRepository;

import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity getAll(){
        var getAllBooks = bookRepository.findAll();
        return ResponseEntity.ok().body(getAllBooks);
    }

    @PostMapping
    @Transactional
    public ResponseEntity registerBook(@RequestBody @Valid RegisterBook newBook){
        if(bookRepository.existsByName(newBook.name())){
            return ResponseEntity.ok().body(new ResponseError("books","Book title already exists."));
        }

        var book = bookRepository.save(new Book((newBook)));
        return ResponseEntity.ok().body(book);


    }

    @PutMapping("/{bookId}")
    @Transactional
    public ResponseEntity updateBook(@PathVariable UUID bookId,
                                     @RequestBody UpdateBook updatedBook){

        var optionalBook = bookRepository.findById(bookId);

        if(optionalBook == null){
            return ResponseEntity.ok().body(new ResponseError("books","Book not registered"));
        }

        var book = optionalBook.get();
        book.updateBook(updatedBook);

        return ResponseEntity.ok().body(book);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteBook(@PathVariable UUID bookId){
        var book = bookRepository.findById(bookId);

        if(book.isEmpty()){
            ResponseEntity.badRequest().body(new ResponseError("user/books", "Book not found."));
        }

        bookRepository.deleteById(bookId);
        return  ResponseEntity.noContent().build();
    }
}
