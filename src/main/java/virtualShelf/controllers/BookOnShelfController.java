package virtualShelf.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import virtualShelf.dtos.GetBooks;
import virtualShelf.dtos.ResponseError;
import virtualShelf.dtos.UpdateBookOnShelf;
import virtualShelf.models.BookOnShelf;
import virtualShelf.repositories.BookOnShelfRepository;
import virtualShelf.repositories.UserRepository;


import java.util.UUID;

@RestController
@RequestMapping("/bookonshelf")
public class BookOnShelfController {
    @Autowired
    private BookOnShelfRepository bookOnShelfRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    private ResponseEntity getAll(){
        var getAllBooks = bookOnShelfRepository.findAll();
        return ResponseEntity.ok().body(getAllBooks);
    }

    @PostMapping("/{userId}")

    private ResponseEntity putBookOnShelf(@PathVariable UUID userId,
                                          @Valid
                                          @RequestBody GetBooks newBookOnShelf){

        var findBook = bookOnShelfRepository.existsByBookIdAndShelfId(newBookOnShelf.bookId(), newBookOnShelf.shelfId());

        if(findBook){
            return ResponseEntity.badRequest().body(new ResponseError(
                    "shelf/books", "Book already on this shelf"));
        }

        if(!userRepository.existsById(userId)){
            return ResponseEntity.badRequest().body(new ResponseError("user/books", "User not found."));
        }

        var bookOnShelf = bookOnShelfRepository.save(new BookOnShelf(newBookOnShelf));
        return ResponseEntity.ok().body(bookOnShelf);

    }

    @PutMapping("/{userId}/{bookOnShelfId}")
    private ResponseEntity updateBookOnShelf(@PathVariable UUID userId,
                                             @PathVariable UUID bookOnShelfId,
                                             @RequestBody UpdateBookOnShelf updateBook){
        if(!userRepository.existsById(userId)){
            return ResponseEntity.badRequest().body(new ResponseError("user/books", "User not found."));
        }
        var optionalBook = bookOnShelfRepository.findById(bookOnShelfId);

        if(optionalBook.isEmpty()){
            return ResponseEntity.badRequest().body(new ResponseError("books", "Book not found"));
        }

        var book = optionalBook.get();
        book.updateBookOnShelf(updateBook);
        bookOnShelfRepository.save(book);
        return ResponseEntity.ok().body(book);

    }


}
