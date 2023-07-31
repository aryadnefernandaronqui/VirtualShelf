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

import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ResponseEntity getAll(){
        var getAllBooks = bookRepository.findAll();
        return ResponseEntity.ok().body(getAllBooks);
    }

    @PostMapping
    @Transactional
    public ResponseEntity registerBook(@RequestBody @Valid RegisterBook newBook){
        if(bookRepository.existsByName(newBook.name())){
            return ResponseEntity.ok().body(new ResponseError("Book title already exists."));
        }

        var book = new Book((newBook));
        bookRepository.save(book);
        return ResponseEntity.ok().body(newBook);
    }

    @PutMapping("/{bookId}")
    @Transactional
    public ResponseEntity updateBook(@PathVariable UUID bookId,
                                     @RequestBody UpdateBook updatedBook){

        var optionalBook = bookRepository.findById(bookId);

        if(optionalBook == null){
            return ResponseEntity.ok().body(new ResponseError("Book not registered"));
        }

        var book = optionalBook.get();
        book.updateBook(updatedBook);

        return ResponseEntity.ok().body(updatedBook);
    }


}
